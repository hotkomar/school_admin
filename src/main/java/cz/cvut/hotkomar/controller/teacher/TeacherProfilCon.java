/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.teacher;

import cz.cvut.hotkomar.form.ChangePassForm;
import cz.cvut.hotkomar.form.EditProfilForm;
import cz.cvut.hotkomar.form.admin.NewClassForm;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Marie Hotkova
 */
@Controller
@Secured(value = {"ROLE_ADMIN","ROLE_TEACHER"})
public class TeacherProfilCon {
  private TeacherMan teacherMan;
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
    public void setTeacherMan(TeacherMan teacherMan) {
        this.teacherMan = teacherMan;
    }
  
  @Autowired
    private ShaPasswordEncoder passwordEncoder; 
    
    @RequestMapping(value="/teacher/personInfo.htm")
    public String getPersonInfo (ModelMap m,Authentication auth)
    {if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher findById  = teacherMan.findByLogin(login);
        if(findById == null)
        {
            return "admin/errorHups";
        }
      
      m.addAttribute("teachers",findById);
        m.addAttribute("profil",true);
        return"teacher/profil/personInfo";
    }
    @RequestMapping(value = "/teacher/editTeacher.htm")
    public String getEdit(ModelMap m,Authentication auth)
    {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher findById  = teacherMan.findByLogin(login);
        if(findById == null)
        {
            return "admin/errorHups";
        }
      EditProfilForm editProfilForm = new EditProfilForm();
      editProfilForm.setId(findById.getId());
      editProfilForm.setMail(findById.getMail());
      editProfilForm.setMobilePhone(findById.getMobilePhone());
      editProfilForm.setPhoneNumber(findById.getPhoneNumber());
      editProfilForm.setFax(findById.getFax());
      m.addAttribute("form",editProfilForm);
        m.addAttribute("profil",true);
        return"teacher/profil/editProfil";
    }
    @RequestMapping(value = "/teacher/editTeacher.htm", method = RequestMethod.POST)
    public String postEdit(@Valid @ModelAttribute("form") EditProfilForm form, BindingResult errors, ModelMap m)
    {
        if(errors.hasErrors())
        {
            m.addAttribute("form",form);
        m.addAttribute("profil",true);
        return"teacher/profil/editProfil";
        }
        
      Teacher findById = teacherMan.findById(form.getId());
        if (findById==null) {
            return"admin/errorHups";
        }
        findById.setMail(form.getMail());
        findById.setMobilePhone(form.getMobilePhone());
        findById.setPhoneNumber(form.getPhoneNumber());
        findById.setFax(form.getFax());
        teacherMan.edit(findById, true);
        return "redirect:personInfo.htm";
    }
    
    @RequestMapping(value = "/teacher/changePass.htm",method = RequestMethod.GET)
    public String chnagePassGET (ModelMap m,Authentication auth)
    {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher findById  = teacherMan.findByLogin(login);
        if(findById == null)
        {
            return "admin/errorHups";
        }
      ChangePassForm form = new  ChangePassForm();
      form.setId(findById.getId());
        m.addAttribute("profil",true);
        m.addAttribute("form",form );
        m.addAttribute("post","teacherPass.htm");
        return"teacher/profil/pass";
    }
    
    @RequestMapping (value="/teacher/teacherPass.htm", method = RequestMethod.POST)
    public String changePassPost (@Valid@ModelAttribute("form") ChangePassForm form, BindingResult errors, ModelMap m)
    {
         Teacher findById = teacherMan.findById(form.getId());
        if(findById==null)
        {
            return"admin/errorHups";
        }
        String hash4 = passwordEncoder.encodePassword(form.getActualPass(),findById.getLogin());
        boolean equals = findById.getPassword().equals(hash4);
        if(errors.hasErrors()|| !equals)
        {
            if(!equals)
            {
                message.setNegativeMes("Aktuální heslo není shodné s Vaším heslem!");
            }
           m.addAttribute("profil",true);
        m.addAttribute("form", form);
        m.addAttribute("post","teacherPass.htm");
        return"student/profil/pass"; 
        }
       findById.setPassword(passwordEncoder.encodePassword(form.getNewPass(),findById.getLogin()));
       teacherMan.edit(findById, true);
        
        return "redirect:personInfo.htm";
    }
}
