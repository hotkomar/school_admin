/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Marie Hotkova
 */
@Controller
public class TeacherProfil {
  
    @RequestMapping(value="/teacher/personInfo")
    public String getPersonInfo (ModelMap m)
    {
        return"teacher/profil/personInfo";
    }
}
