/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.student;

import cz.cvut.hotkomar.form.student.NewMarkStudentForm;
import cz.cvut.hotkomar.form.teacher.TestResultClassificationForm;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Test;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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
@Secured(value = {"ROLE_STUDENT"})
public class StudentClassification {
    private StudentMan studentMan;
    private SubjectOfClassMan subjectOfClassMan;
    private  TestResultMan testResultMan;
    private DateFunction dateFunction;
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
@Autowired
    public void setStudentMan(StudentMan studentMan) {
        this.studentMan = studentMan;
    }
@Autowired
    public void setSubjectOfClassMan(SubjectOfClassMan subjectOfClassMan) {
        this.subjectOfClassMan = subjectOfClassMan;
    }
@Autowired
    public void setTestResultMan(TestResultMan testResultMan) {
        this.testResultMan = testResultMan;
    }
@Autowired
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }
  
    
    
    @RequestMapping(value = "/student/classification.htm")
    public String view (ModelMap m,Authentication auth)
    {
         if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Student findById  = studentMan.findByLogin(login);
        if(findById == null)
        {
            return "admin/errorHups";
        }
        List<SubjectOfClass> findByClass = subjectOfClassMan.findByClass(findById.getId_class());
        m.addAttribute("subjects", findByClass);
        m.addAttribute("classification",true);
        return "student/classification/viewSubject";
    }
    @RequestMapping (value = "/student/classification/view.htm")
    public String viewSubject (@RequestParam(value = "idSubject", required = true)Long id,@RequestParam(value = "semester", required = false)String semester, ModelMap m,Authentication auth)
    {if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Student student  = studentMan.findByLogin(login);
        if(student == null)
        {
            return "admin/errorHups";
        }
        SubjectOfClass subject = subjectOfClassMan.findByClassID(student.getId_class(), id);
        if(subject==null)
        {
            System.out.println("nenašla jsem předmět");
            return "admin/errorHups";
        }
        Calendar findByStudentMINDate = testResultMan.findByStudentMINDate(student, subject.getId_subject());
       //  List <TestResultClassificationForm> list = new ArrayList<TestResultClassificationForm>();
        List<String> listOfSemester = new ArrayList<String>();
        List<TestResult> secondSemester = new ArrayList<TestResult>();
        List<TestResult> firstSemester = new ArrayList<TestResult>();
        if(findByStudentMINDate!=null){
            System.out.println("datum minima je "+ dateFunction.getDateString(findByStudentMINDate));
        listOfSemester= dateFunction.listOfSemester(findByStudentMINDate,student.getId_class().getYearOfFoundation());
       //nebyl zadaný semester
        String[] split;
        if(semester==null ||!listOfSemester.contains(semester) )
        {
            String lastSemester = listOfSemester.get(listOfSemester.size()-1);
            System.out.println("last semester "+listOfSemester);
             split = lastSemester.split("/");
        }
        else{           
          
             split = semester.split("/");
        }
        System.out.println("plist "+split[0]+" "+split[1]);
            Calendar first = dateFunction.setDBDate(1, 9, Integer.parseInt(split[0]));
            Calendar last = dateFunction.setDBDate(31, 1, Integer.parseInt(split[1]));
            firstSemester = testResultMan.findByStudentBetween(student,subject.getId_subject(),first, last);
            System.out.println("firstSemester "+firstSemester);
            Calendar secondFirst = dateFunction.setDBDate(1,2, Integer.parseInt(split[1]));
            Calendar secondLast = dateFunction.setDBDate(31,8, Integer.parseInt(split[1]));
            secondSemester = testResultMan.findByStudentBetween(student, subject.getId_subject(),secondFirst, secondLast);
        }
         
        List<TestResultClassificationForm> firstForm = new ArrayList<TestResultClassificationForm>();
         List<TestResultClassificationForm> secondForm = new ArrayList<TestResultClassificationForm>();
         Double firstAVG=0.0;
         Double secondAVG=0.0;
         int count=0;
         if(!firstSemester.isEmpty()){
         for(TestResult r :firstSemester)
        {
          firstForm.add(  resultToForm(new TestResultClassificationForm(), r));
          if(r.getClassified().equals(Short.valueOf("0")))
          {
              firstAVG+=r.getMark();
              count++;
          }
          
        }
         if(firstAVG>0){
        firstAVG= firstAVG/count;
         }
        count=0;}
         if(!secondSemester.isEmpty()){
       for(TestResult r :secondSemester)
        {
          secondForm.add(  resultToForm(new TestResultClassificationForm(), r));
          if(r.getClassified().equals(Short.valueOf("0")))
          {
              secondAVG+=r.getMark();
              count++;
          }
          
        }}
         if(secondAVG>0){
       secondAVG=secondAVG/count;
         }
        m.addAttribute("listSemester", listOfSemester);
          m.addAttribute("first", firstForm);
        m.addAttribute("second", secondForm);
         m.addAttribute("firstAVG", firstAVG);
        m.addAttribute("secondAVG", secondAVG);
         m.addAttribute("classification", true);
         
        return"student/classification/view";
    }
    

  private TestResultClassificationForm resultToForm (TestResultClassificationForm form, TestResult testResult)
    {
        form.setId(testResult.getId());
        System.out.println("getClassified is "+testResult.getClassified());
        if(testResult.getClassified().equals(Short.valueOf("0"))){
            System.out.println("známka je na řídku 291 "+testResult.getMark());
        form.setMark(testResult.getMark().toString());
            System.out.println("známka je "+form.getMark());
        }
        else{
            if(testResult.getClassified().equals(Short.valueOf("1")))
            {
                form.setMark("neklasifikováno");
            }
            else{
                form.setMark("absence");
            }
        }
        
        form.setTestName(testResult.getTest().getName());
        form.setWebTest(testResult.getWebTest());
        form.setDateMark(dateFunction.getDateString(testResult.getTestDate()));
        form.setChangeMark(testResult.getTeacher());
        form.setTeacher(testResult.getTest().getId_teacher());
       
        
        return form;
    }  
   
}
