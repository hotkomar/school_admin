/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.security;

import cz.cvut.hotkomar.model.entity.AbstractUser;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.StudentParentMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maru
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
    private StudentMan studentMan;
    private TeacherMan teacherMan;
    private StudentParentMan studentParentMan;
    private Assembler assembler;

    /**
     *
     * @param studentMan
     */
    @Autowired
    public void setStudentMan(StudentMan studentMan) {
        this.studentMan = studentMan;
    }

    /**
     *
     * @param teacherMan
     */
    @Autowired
    public void setTeacherMan(TeacherMan teacherMan) {
        this.teacherMan = teacherMan;
    }

    /**
     *
     * @param assembler
     */
    @Autowired
    public void setAssembler(Assembler assembler) {
        this.assembler = assembler;
    }

    /**
     *
     * @param studentParentMan
     */
    @Autowired
    public void setStudentParentMan(StudentParentMan studentParentMan) {
        this.studentParentMan = studentParentMan;
    }

    /**
     * login function, find user by chosen login
     *
     * @param string
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        AbstractUser userAbs = null;
        System.out.println("SECURITY");
string=string.toLowerCase();
        userAbs = studentMan.findByLogin(string);
        if (userAbs == null) {
            System.out.println("TEACHER");
           // System.out.println("teacher login "+string);
            userAbs = teacherMan.findByLogin(string);
           // System.out.println("teacher je "+userAbs);
        }
        if (userAbs == null) {
            System.out.println("PARENT");
            userAbs = studentParentMan.findByLogin(string);
        }
        System.out.println("HLEDAM " + string + " " + userAbs);
        if (userAbs == null) {
            throw new UsernameNotFoundException("");
        }

        return assembler.buildUserFromUserEntity(userAbs);

    }
}
