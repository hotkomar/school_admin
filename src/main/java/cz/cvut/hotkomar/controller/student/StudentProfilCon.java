/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.student;

import com.google.inject.Binding;

import cz.cvut.hotkomar.form.ChangePassForm;
import cz.cvut.hotkomar.form.EditProfilForm;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Marie Hotkova
 */
@Controller
public class StudentProfilCon {
    
    private StudentMan studentMan;
    private FormMessage message;
    private  TeacherMan teacherMan;
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

    
    @Autowired
    public void setStudentMan(StudentMan studentMan) {
        this.studentMan = studentMan;
    }
@Autowired
    public void setTeacherMan(TeacherMan teacherMan) {
        this.teacherMan = teacherMan;
    }
    
    
    
    @RequestMapping(value = "/student/personInfo.htm")
    public String getPersonInfo (ModelMap m)
    {
        Student findById = studentMan.findById(Long.valueOf("1"));
        
        m.addAttribute("students", findById);
        m.addAttribute("profil",true);
        return"student/profil/personInfo";
    }
    @RequestMapping(value = "/student/editKontakt.htm")
    public String kontaktGET (ModelMap m)
    {
        Student findById = studentMan.findById(Long.valueOf("1"));
        EditProfilForm editProfilForm = new EditProfilForm();
        editProfilForm.setMail(findById.getMail());
        editProfilForm.setMobilePhone(findById.getMobilePhone());
        editProfilForm.setPhoneNumber(findById.getPhoneNumber());
        m.addAttribute("form",editProfilForm);
         m.addAttribute("profil",true);
        return "student/profil/editProfil";
    }
    
    @RequestMapping(value = "/student/editKontakt.htm", method = RequestMethod.POST)
    public String kontaktPOST (@Valid @ModelAttribute("form") EditProfilForm form, BindingResult errors, ModelMap m)
    {
        if(errors.hasErrors())
        {
            
          m.addAttribute("form",form);
           m.addAttribute("profil",true);
        return "student/profil/editProfil";
        }
        Student findById = studentMan.findById(Long.valueOf("1"));
        
        findById.setMail(form.getMail());
        findById.setMobilePhone(form.getMobilePhone());
        findById.setPhoneNumber(form.getPhoneNumber());
       
        studentMan.edit(findById, true);
        
        return "redirect:personInfo.htm";
    }
    
    @RequestMapping (value = "/student/changePass.htm", method = RequestMethod.GET)
    public String changePass (ModelMap m)
    {
        m.addAttribute("profil",true);
        m.addAttribute("form", new  ChangePassForm());
        m.addAttribute("post","studentPass.htm");
        return"student/profil/pass";
    }
    @RequestMapping (value="/student/studentPass.htm", method = RequestMethod.POST)
    public String changePassPost (@Valid@ModelAttribute("form") ChangePassForm form, BindingResult errors, ModelMap m)
    {
        if(errors.hasErrors())
        {
           m.addAttribute("profil",true);
        m.addAttribute("form", form);
        m.addAttribute("post","studentPass.htm");
        return"student/profil/pass"; 
        }
        return "redirect:personInfo.htm";
    }
    
    
    @RequestMapping (value = "/student/headTeacher.htm")
    public  String headTeacher(ModelMap m)
    {
        System.out.println("zobrazím třídního učitelem");
        Student findById = studentMan.findById(Long.valueOf("1"));
        Teacher findById1 = teacherMan.findById(findById.getId());
        if(findById1==null)
        {
            message.setNegativeMes("Učitel nebyl nalezen");
             return "redirect:personInfo.htm";
        }
        m.addAttribute("teachers",findById1);
        m.addAttribute("profil",true);
        return "student/profil/headTeacher";
    }
}
