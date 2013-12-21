/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller;

import cz.cvut.hotkomar.form.EditSchoolForm;
import cz.cvut.hotkomar.model.entity.School;
import cz.cvut.hotkomar.service.manager.SchoolMan;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Maru
 */
@Controller
public class SchoolCon {
 private SchoolMan schoolMan;
@Autowired
    public void setSchoolMan(SchoolMan schoolMan) {
        this.schoolMan = schoolMan;
    }
 

 
 
 
    
    @RequestMapping(value = "/school.htm")
    public String view (ModelMap m)
    {
     School findById = schoolMan.findById(Long.valueOf(1));
     if(findById==null)
     {
         return"admin/errorHups";
     }
     
     m.addAttribute("schoolInfo",findById);
        m.addAttribute("school",true);
        return "school/adminView";
    }

    @RequestMapping (value = "/admin/editSchool.htm", method = RequestMethod.GET)
    public String editGET (ModelMap m)
    {
        School findById = schoolMan.findById(Long.valueOf(1));
     if(findById==null)
     {
         return"admin/errorHups";
     }
     EditSchoolForm form = new EditSchoolForm();
     form.setId(findById.getId());
     form.setCity(findById.getCity());
     form.setFax(findById.getFax());
     form.setMail(findById.getMail());
     form.setMobilePhone(findById.getMobilePhone());
     form.setPhoneNumber(findById.getPhoneNumber());
     form.setPsc(findById.getPsc());
     form.setSchoolName(findById.getSchoolName());
     form.setStreetName(findById.getStreetName());
     m.addAttribute("form",form);
        m.addAttribute("school",true);
        return "school/adminEdit";
    }
    @RequestMapping(value = "/admin/editSchool.htm", method = RequestMethod.POST)
    public String editPOST(@Valid@ModelAttribute(value = "form") EditSchoolForm form, BindingResult error,ModelMap m)
    {
        if(error.hasErrors())
        {
          m.addAttribute("form",form);
        m.addAttribute("school",true);
        return "school/adminEdit";  
        }
     School findById = schoolMan.findById(Long.valueOf(1));
     if(findById==null)
     {
         return"admin/errorHups";
     }
     
     findById.setCity(form.getCity());
     findById.setFax(form.getFax());
     findById.setMail(form.getMail());
     findById.setMobilePhone(form.getMobilePhone());
     findById.setPhoneNumber(form.getPhoneNumber());
     findById.setPsc(form.getPsc());
     findById.setSchoolName(form.getSchoolName());
     findById.setStreetName(form.getStreetName());
     schoolMan.add(findById, true);
       return"redirect:/school.htm"; 
    }
}
