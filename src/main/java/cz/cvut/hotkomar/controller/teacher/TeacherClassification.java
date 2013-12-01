/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.teacher;

import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Maru
 */
@Controller
public class TeacherClassification {
    private TeacherMan teacherMan;
    private StudentClassMan studentClassMan;
    private StudentMan studentMan;
    private SubjectOfClassMan subjectOfClassMan;
    private SubjectMan subjectMan;
    private TestResultMan testResultMan;
    private DateFunction dateFunction;
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
    public void setStudentClassMan(StudentClassMan studentClassMan) {
        this.studentClassMan = studentClassMan;
    }
@Autowired
    public void setStudentMan(StudentMan studentMan) {
        this.studentMan = studentMan;
    }
@Autowired
    public void setSubjectOfClassMan(SubjectOfClassMan subjectOfClassMan) {
        this.subjectOfClassMan = subjectOfClassMan;
    }
@Autowired
    public void setSubjectMan(SubjectMan subjectMan) {
        this.subjectMan = subjectMan;
    }
@Autowired
    public void setTestResultMan(TestResultMan testResultMan) {
        this.testResultMan = testResultMan;
    }
@Autowired
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }
    
    
    
    
    @RequestMapping(value = "/teacher/classification.htm")
    public String view (ModelMap m)
    {
        Teacher teacher = teacherMan.findById(Long.valueOf("1"));
        List<SubjectOfClass> findByTeacher = subjectOfClassMan.findSubjectByTeacher(teacher);
        if(teacher==null){
            return"admin/errorHups";
        }
        m.addAttribute("subjectClass", findByTeacher);
        m.addAttribute("classification",true);
        return "teacher/classification/view";
    }
    @RequestMapping(value = "/teacher/classification/classView.htm")
    public String viewClass(@RequestParam(value = "idSubject", required = true)Long id,ModelMap m)
    {
        Teacher teacher = teacherMan.findById(Long.valueOf("1"));
       // SubjectOfClass findById = subjectOfClassMan.findById(id);
        List<SubjectOfClass> subject = subjectOfClassMan.findBySubjectTeacherID(id, teacher);
        if(subject.isEmpty() )
        {
            return"admin/errorHups";
        }
       
        m.addAttribute("classList", subject);
        m.addAttribute("classification",true);
        return"teacher/classification/viewClass";
    }
    /**
     *
     * @param id
     * @param m
     * @return
     */
    @RequestMapping(value = "/teacher/classification/studentView.htm")
    public String viewStudent(@RequestParam(value = "idSubject", required = true)Long id,@RequestParam(value = "idClass", required = true)Long studentClass,ModelMap m)
    {
        Teacher teacher = teacherMan.findById(Long.valueOf("1"));
        StudentClass sc = studentClassMan.findById(studentClass);
        //SubjectOfClass findById = subjectOfClassMan.findById(studentClass);
        //  List<SubjectOfClass> subject = subjectOfClassMan.findByClassSubjectTeacherVisibleID(sc,findById.getId_subject(), teacher);
        SubjectOfClass subject = subjectOfClassMan.findByClassSubjectTeacherVisibleID(studentClass,id,teacher.getId());
       
        if( subject ==null )
        {
            return"admin/errorHups";
        }
        List<Student> findByClass_id = studentMan.findByClass_id(sc);
        m.addAttribute("idSubject",id);
        m.addAttribute("students", findByClass_id);
        m.addAttribute("classification",true);
        return"teacher/classification/viewStudents";
    }
    
    @RequestMapping(value = "/teacher/classification/studentInfo.htm")
    public String studentInfo(@RequestParam(value = "idSubject", required = true)Long idSubject,@RequestParam(value = "idStudent",required = true)Long idStudent,ModelMap m)
    {
        Teacher findById = teacherMan.findById(Long.valueOf("1"));
        Student findById1 = studentMan.findById(idStudent);
        SubjectOfClass subject = subjectOfClassMan.findByClassSubjectTeacherVisibleID(findById1.getId_class().getId(), idSubject,findById.getId());
        if(subject ==null)
        {
            return"admin/errorHups";
        }
        Calendar findByStudentMaxDate = testResultMan.findByStudentMaxDate(findById1, subject.getId_subject());
        Calendar findByStudentMINDate = testResultMan.findByStudentMINDate(findById1, subject.getId_subject());
        List<TestResult> findByStudentBetween = testResultMan.findByStudentBetween(findById1,subject.getId_subject() , findByStudentMINDate, findByStudentMaxDate);
        
        
            System.out.println("");
       String  dateString = dateFunction.getDateString(findByStudentMaxDate);
        String dateString1 = dateFunction.getDateString(findByStudentMINDate);
        
        m.addAttribute("maxDate", dateString);
        m.addAttribute("minDate", dateString1);
        m.addAttribute("pokus", findByStudentBetween);
        m.addAttribute("classification",true);
        return"teacher/classification/studentInfo";
    }
}
