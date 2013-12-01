/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller;

import cz.cvut.hotkomar.form.Form;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Maru
 */

public interface AdminControllerImp {
    
    public String view(@RequestParam(defaultValue ="1", value = "page",required = true) Integer page,ModelMap m,HttpSession session);
    public String addGET  (ModelMap m);
    //public String addPOST  (@ModelAttribute("form") Form form, BindingResult errors,ModelMap m);
    public String info (@RequestParam(value = "id", required =true)Long id, ModelMap m); 
    public String editGET (@RequestParam (value ="id", required = true)Long id,ModelMap m);
   // public String editPOST (@ModelAttribute("form") Form form, BindingResult errors,ModelMap m);
    public String remove (@RequestParam(value="id", required= true)Long id,@RequestParam(value="page", required=true)Integer page);
   // public String changePassGET(@RequestParam(value ="id", required = true)Long id,ModelMap m);
   // public String changePassPOST(@ModelAttribute("form") Form form, BindingResult errors,ModelMap m);
    
    
}
