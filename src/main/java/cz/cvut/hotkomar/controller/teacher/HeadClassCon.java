/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.teacher;

import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Maru
 */
@Controller
@Secured(value = {"ROLE_TEACHER"})
public class HeadClassCon {
  private TeacherMan teacherMan;
  private StudentMan studentMan;
  
  private SubjectOfClassMan subjectOfClassMan;

  
  
  @Autowired
    public void setSubjectOfClassMan(SubjectOfClassMan subjectOfClassMan) {
        this.subjectOfClassMan = subjectOfClassMan;
    }

  
  
  
  
  @Autowired
    public void setSutudentMan(StudentMan sutMan) {
        this.studentMan = sutMan;
    }

  @Autowired
    public void setTeacherMan(TeacherMan teacherMan) {
        this.teacherMan = teacherMan;
    }
  
  
    
    @RequestMapping(value = "/teacher/headClass.htm")
    public String view (ModelMap m,Authentication auth)
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
      List<Student> findByClass_id = studentMan.findByClass_id(findById.getId_class());
      // List<SubjectOfClass> findByIdClass = subjectOfClassMan.findByIdClass(findById.getId_class().getId());
      List<SubjectOfClass> findByClass = subjectOfClassMan.findByClass(findById.getId_class());
        m.addAttribute("head",true);
        m.addAttribute("headClazz",findById.getId_class());
        m.addAttribute("students",findByClass_id);
        m.addAttribute("subjectsClass",findByClass.size());
       m.addAttribute("studentC",true);
        return"teacher/headClass/students";
    }
    @RequestMapping(value = "/teacher/headClassS.htm")
    public String viewStudent (@RequestParam (value = "id", required = true)Long id, ModelMap m,Authentication auth)
    {
      Student student = studentMan.findById(id);
        if(student==null)
        {
            return"admin/errorHups";
        }
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
     // List<Student> findByClass_id = studentMan.findByClass_id(findById.getId_class());
      List<SubjectOfClass> findByIdClass = subjectOfClassMan.findByIdClass(findById.getId_class().getId());
      
        m.addAttribute("head",true);
        m.addAttribute("headClazz",findById.getId_class());
        m.addAttribute("students",student);
        m.addAttribute("subjectsClass",findByIdClass.size());
       m.addAttribute("studentC",true);
        return"teacher/headClass/student";
    }
    @RequestMapping(value = "/teacher/infoClassS.htm")
    public String viewSubject (ModelMap m,Authentication auth)
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
    // List<Student> findByClass_id = studentMan.findByClass_id(findById.getId_class());
      List<SubjectOfClass> findByIdClass = subjectOfClassMan.findByIdClass(findById.getId_class().getId());
      
        m.addAttribute("head",true);
        m.addAttribute("headClazz",findById.getId_class());
        m.addAttribute("subjects",findByIdClass);
        m.addAttribute("subjectsClass",findByIdClass.size());
       m.addAttribute("subjectC",true);
        return"teacher/headClass/subjects";
    }
}
