/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller;

import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentParent;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.StudentParentMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Maru
 */
@Controller
@Secured(value = {"ROLE_STUDENT","ROLE_TEACHER","ROLE_ADMIN","ROLE_PARENT"})
public class RedirectCon {
    private StudentMan studentMan;
    private StudentParentMan studentParentMan;
    private TeacherMan teacherMan;
@Autowired
    public void setStudentMan(StudentMan studentMan) {
        this.studentMan = studentMan;
    }
@Autowired
    public void setStudentParentMan(StudentParentMan studentParentMan) {
        this.studentParentMan = studentParentMan;
    }
@Autowired
    public void setTeacherMan(TeacherMan teacherMan) {
        this.teacherMan = teacherMan;
    }
    
    
    
    @RequestMapping(value = "/redirecRole.htm")
    public String redirectRole(Authentication auth)
    {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        System.out.println("*************************");
         System.out.println("*************************");
          System.out.println("*************************");
        System.out.println("login je "+login);
         System.out.println("*************************");
          System.out.println("*************************");
           System.out.println("*************************");
        Student findByLogin = studentMan.findByLogin(login);
        if(findByLogin!=null){
        return"redirect:/student/personInfo.htm";
        }
        StudentParent findByLogin1 = studentParentMan.findByLogin(login);
        if(findByLogin1!= null)
        {
            return"redirect:/parent/personInfo.htm";
        }
        Teacher findByLogin2 = teacherMan.findByLogin(login);
        if(findByLogin2!= null)
        {
            return"redirect:/teacher/personInfo.htm";
        }
        return"redirect:/school.htm";
    }
}
