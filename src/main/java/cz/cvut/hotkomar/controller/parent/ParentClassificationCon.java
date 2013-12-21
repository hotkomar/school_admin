/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.parent;

import cz.cvut.hotkomar.form.ChangePassForm;
import cz.cvut.hotkomar.form.parent.ChangeParentPassForm;
import cz.cvut.hotkomar.form.parent.EditParentForm;
import cz.cvut.hotkomar.form.teacher.TestResultClassificationForm;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentParent;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.checkAndMake.CheckForm;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.StudentParentMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Marie Hotkova
 */
@Controller
@Secured(value = {"ROLE_PARENT"})
public class ParentClassificationCon {
    private StudentParentMan studentParentMan;
    private StudentMan studentMan;
    private SubjectOfClassMan subjectOfClassMan;
    private DateFunction dateFunction;
    private TestResultMan testResultMan;
    private CheckForm checkForm;
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
    public void setStudentParentMan(StudentParentMan studentParentMan) {
        this.studentParentMan = studentParentMan;
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
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }
@Autowired
    public void setTestResultMan(TestResultMan testResultMan) {
        this.testResultMan = testResultMan;
    }
    @Autowired
    private ShaPasswordEncoder passwordEncoder; 
@Autowired
    public void setCheckForm(CheckForm checkForm) {
        this.checkForm = checkForm;
    }
    
    
    
    @RequestMapping(value = "/parent/personInfo.htm", method = RequestMethod.GET)
    public String profilView(ModelMap m,Authentication auth)
    {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        StudentParent findById  = studentParentMan.findByLogin(login);
        if(findById == null)
        {
            return "admin/errorHups";
        }
      m.addAttribute("students", findById.getStudent());
      
        m.addAttribute("profil", true);
        return"parent/studentProfil";
    }
    @RequestMapping(value = "/parent/editKontakt.htm", method = RequestMethod.GET)
    public String editProfilGET (ModelMap m,Authentication auth)
    {if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        StudentParent findById  = studentParentMan.findByLogin(login);
        if(findById == null)
        {
            return "admin/errorHups";
        }
        EditParentForm studentToForm = studentToForm(findById.getStudent(),findById.getId());
        m.addAttribute("form", studentToForm);
        m.addAttribute("profil",true);
        return"parent/editProfil";
    }
    @RequestMapping(value = "/parent/editKontak.htm", method = RequestMethod.POST)
    public String editProfilPost(@Valid@ModelAttribute(value = "form") EditParentForm form, BindingResult error,ModelMap m)
    {
        if(error.hasErrors())
        {
           m.addAttribute("form", form);
        m.addAttribute("profil",true);
        return"parent/editProfil"; 
        }
        StudentParent findById = studentParentMan.findById(form.getIdParent());
        if(findById==null)
        {
            return"admin/errorHups";
        }
        Student student = findById.getStudent();
        student.setFatherMail(form.getFatherMail());
        student.setFatherPhone(form.getFatherPhone());
        student.setMotherPhone(form.getMotherPhone());
        student.setMotherMail(form.getMotherMail());
        studentMan.edit(student, true);
        
        return"redirect:personInfo.htm";
    }
    @RequestMapping(value = "/parent/changePassword.htm", method = RequestMethod.GET)
    public String changePassGET(ModelMap m,Authentication auth)
    {if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        StudentParent findById  = studentParentMan.findByLogin(login);
        if(findById == null)
        {
            return "admin/errorHups";
        }
        ChangePassForm form = new ChangePassForm();
        form.setId(findById.getId());
        m.addAttribute("profil",true);
        m.addAttribute("form", form);
        m.addAttribute("post","changePassword.htm");
        
        return"parent/changePass";
    }
      @RequestMapping(value = "/parent/changePassword.htm", method = RequestMethod.POST)
      public String changePassPOST(@Valid@ModelAttribute(value = "form") ChangePassForm form, BindingResult error,ModelMap m)
      {
        StudentParent findById = studentParentMan.findById(form.getId());
        if(findById==null)
        {
            return"admin/errorHups";
        }
        String hash4 = passwordEncoder.encodePassword(form.getActualPass(),findById.getLogin());
        Boolean equals=checkForm.corectPassword(findById.getPassword(), hash4);
        if(error.hasErrors() || !equals  )
        {
           
            m.addAttribute("form",form);
        m.addAttribute("profil",true);
        return"parent/changePass";
            
        }
        hash4=passwordEncoder.encodePassword(form.getNewPass(),findById.getLogin());
        findById.setPassword(hash4);
        studentParentMan.edit(findById,true);
          return"redirect:personInfo.htm";
      }
      @RequestMapping(value = "/parent/classification.htm")
    public String view (ModelMap m,Authentication auth)
    {if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        StudentParent findById  = studentParentMan.findByLogin(login);
        if(findById == null)
        {
            return "admin/errorHups";
        }
        List<SubjectOfClass> findByClass = subjectOfClassMan.findByClass(findById.getStudent().getId_class());
        m.addAttribute("subjects", findByClass);
        m.addAttribute("classification",true);
        return "parent/viewSubject";
    }
      @RequestMapping(value = "/parent/classification/viewSubject.htm")
      public String viewClassification(@RequestParam(value = "semester", required = false)String semester,@RequestParam(value = "idSubject", required = true)Long id,ModelMap m,Authentication auth)
      {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        StudentParent findById  = studentParentMan.findByLogin(login);
        if(findById == null)
        {
            return "admin/errorHups";
        }
         Student student =findById.getStudent();
        
        SubjectOfClass subject = subjectOfClassMan.findByClassID(student.getId_class(), id);
        if(subject==null)
        {
            System.out.println("nenašla jsem předmět");
            return "admin/errorHups";
        }
        Calendar findByStudentMINDate = testResultMan.findByStudentMINDate(student, subject.getId_subject());
        // List <TestResultClassificationForm> list = new ArrayList<TestResultClassificationForm>();
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
         
        return"parent/classification"; 
      }
    private EditParentForm studentToForm (Student s, Long idParent)
    {
        EditParentForm form = new EditParentForm();
        form.setFatherMail(s.getFatherMail());
        form.setFatherPhone(s.getFatherPhone());
        form.setMotherMail(s.getMotherMail());
        form.setMotherPhone(s.getMotherPhone());
        form.setFatherName(s.getFatherName());
        form.setFatherSurname(s.getFatherSurname());
        form.setMotherName(s.getMotherName());
        form.setMotherSurname(s.getMotherSurname());
        form.setIdParent(idParent);
        
        return form;
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
