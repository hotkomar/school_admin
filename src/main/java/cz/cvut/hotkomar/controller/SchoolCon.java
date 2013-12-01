/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Maru
 */
@Controller
public class SchoolCon {
 
    
    @RequestMapping(value = "/school.htm")
    public String view(ModelMap m)
    {
     m.addAttribute("school",true);
     return "school/view";
    }
}
