/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.admin;

import cz.cvut.hotkomar.form.admin.AddStudentForm;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.service.manager.StudentMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Marie Hotkova
 */

@Controller
public class StudentsCon {
    private StudentMan studentMan;
    
    @Autowired
    public void setStudentMan (StudentMan studentMan)
    {
        this.studentMan=studentMan;
    }
    
    /**
     * view list of all students
     * @param m
     * @return
     */
    @RequestMapping (value="/admin/students.htm")
    public String StudentsGET (ModelMap m)
    {
        m.addAttribute("student",true);
        return "admin/student/viewStudent";
    }
    
    /**
     * view form for add new student
     * @param m
     * @return
     */
    @RequestMapping (value="/admin/addStudent.htm")
    public String newStudentGET (ModelMap m)
    {
        
        m.addAttribute("form", new AddStudentForm());
        m.addAttribute("student",true);
        return "admin/student/addStudent";
    }
    
    @RequestMapping(value="/admin/adminNewStudent.htm")
    public String NewStudentPOST (@ModelAttribute("form") AddStudentForm form, BindingResult errors,ModelMap m)
    {
        System.out.println("budu vytvářet studenta");
        Student student = new Student();
        student.setLogin(form.getLogin());
        student.setPassword(form.getPassword());
        student.setName(form.getName());
        student.setSurname(form.getSurname());
       // student.setDateOfBorn(form.getDateOfBorn());
        student.setIdentificationNumber(form.getIdentificationNumber());
        student.setPlaceOfBorn(form.getPlaceOfBorn());
        student.setMotherName(form.getMotherName());
        student.setMotherSurname(form.getMotherSurname());
        student.setMotherPhone(form.getMotherPhone());
        student.setMotherMail(form.getMotherMail());
        student.setFatherName(form.getFatherName());
        student.setFatherSurname(form.getFatherSurname());
        student.setFatherPhone(form.getFatherPhone());
        student.setFatherMail(form.getFatherMail());
        student.setMail(form.getMail());
        student.setMobilePhone(form.getMobilePhone());
        student.setPhoneNumber(form.getPhoneNumber());
        student.setFax(form.getFax());
        //student role
        System.out.println("student "+student.getLogin()+" "+student.getPassword()+" "+student.getName());
        studentMan.add(student, true);
        
        
        
        return"redirect:students.htm";
    }
    
}
