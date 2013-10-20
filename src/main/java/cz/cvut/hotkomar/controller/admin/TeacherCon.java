/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Marie Hotkova
 */
@Controller
public class TeacherCon {
    
    
    @RequestMapping (value="/admin/teachers.htm")
    public String teachersGet (ModelMap m)
    {
        m.addAttribute("teacher",true);
        return "/admin/teacher/viewTeacher";
    }
    
}
