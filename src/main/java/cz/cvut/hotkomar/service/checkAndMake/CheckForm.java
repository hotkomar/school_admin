/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.checkAndMake;

import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.TestMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Maru
 */
@Service
public class CheckForm {
    private StudentMan studentMan;
    private TeacherMan teacherMan;
    private TestMan testMan;
    private FormMessage message;
    
    @ModelAttribute("message")
    public FormMessage getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    @Autowired
    public void setMessage(FormMessage message) {
        this.message = message;
    }
@Autowired
    public void setStudentMan(StudentMan studentMan) {
        this.studentMan = studentMan;
    }
@Autowired
    public void setTeacherMan(TeacherMan teacherMan) {
        this.teacherMan = teacherMan;
    }
@Autowired
    public void setTeMan(TestMan teMan) {
        this.testMan = teMan;
    }
    
    
    
    public boolean uniqueLogin(String login) {
        if (studentMan.findByLogin(login.toLowerCase()) == null && teacherMan.findByLogin(login.toLowerCase()) == null ) {
            return true;
        }
       // message.setNegativeMes("Uživatel s tímto přihlašovacím jménem již existuje.");
        return false;
    }
    
    public boolean validFile (CommonsMultipartFile file) 
    {
        String[] split = file.getContentType().split("/");
        if(file.getSize()<=204800 && split[0].equals("image") )
        {
            return true;
        }
        
        //message.setNegativeMes("Soubor není obrázek nebo je příliš velký");
        return false;
    }
    public boolean corectPassword (String formPass,String userPass)
    {
        System.out.println("první je "+formPass+"  druhé je "+userPass);
        if(formPass.equals(userPass))
        {
            return true;
        }
        message.setNegativeMes("Uživatelské heslo bylo špatně zadáno.");
        return false;
    }
    
    
    public boolean uniqueTestPass (String pass)
    {
        if (testMan.findBypass(pass) == null) {
            return true;
        }
       // message.setNegativeMes("Toto heslo už má jiný test. ");
        return false;
    }
    
}
