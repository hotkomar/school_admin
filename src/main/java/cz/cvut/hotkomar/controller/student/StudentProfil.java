/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Marie Hotkova
 */
@Controller
public class StudentProfil {
    @RequestMapping(value = "/student/personInfo.htm")
    public String getPersonInfo (ModelMap m)
    {
        return"student/profil/personInfo";
    }
    
}
