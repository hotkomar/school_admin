/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.student;

import cz.cvut.hotkomar.service.message.FormMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Maru
 */
@Controller
public class StudentClassification {
  private FormMessage message;
    /**
     *
     * @return
     */
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
  
    
    
    @RequestMapping(value = "/student/classification.htm")
    public String view (ModelMap m)
    {
        m.addAttribute("classification", true);
        return "student/classification/view";
    }
}
