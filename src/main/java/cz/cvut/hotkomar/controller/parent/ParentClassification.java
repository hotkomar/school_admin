/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.parent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Marie Hotkova
 */
@Controller
public class ParentClassification {
    
    @RequestMapping(value="/parent/classification")
    public String getClassification (ModelMap m)
    {
        return "parent/classification";
    }
    
}
