/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.teacher;

import cz.cvut.hotkomar.form.student.NewMarkStudentForm;
import cz.cvut.hotkomar.form.teacher.ChangeMarkWebForm;
import cz.cvut.hotkomar.form.teacher.TestResultClassificationForm;
import cz.cvut.hotkomar.form.teacher.ChangeMarkForm;
import cz.cvut.hotkomar.form.teacher.NewClassMarksForm;
import cz.cvut.hotkomar.form.teacher.NewMarkForm;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.Test;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.checkAndMake.TestResultCheck;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.TestMan;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Maru
 */
@Controller
@Secured(value = {"ROLE_TEACHER"})
public class TeacherClassification {
    private TeacherMan teacherMan;
    private StudentClassMan studentClassMan;
    private StudentMan studentMan;
    private SubjectOfClassMan subjectOfClassMan;
    private SubjectMan subjectMan;
    private TestResultMan testResultMan;
    private DateFunction dateFunction;
    private TestMan testMan;
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
@Autowired
    public void setTestMan(TestMan testMan) {
        this.testMan = testMan;
    }
    
    
    
    
    @RequestMapping(value = "/teacher/classification.htm")
    public String view (ModelMap m,Authentication auth)
    {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher teacher  = teacherMan.findByLogin(login);            
        if(teacher==null){
            return"admin/errorHups";
        }
        List<SubjectOfClass> findByTeacher = subjectOfClassMan.findSubjectByTeacher(teacher);
        m.addAttribute("subjectClass", findByTeacher);
        m.addAttribute("classification",true);
        return "teacher/classification/view";
    }
    @RequestMapping(value = "/teacher/classification/classView.htm")
    public String viewClass(@RequestParam(value = "idSubject", required = true)Long id,ModelMap m,Authentication auth)
    {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher teacher  = teacherMan.findByLogin(login);
        if(teacher == null)
        {
            return "admin/errorHups";
        }
       // SubjectOfClass teacher = subjectOfClassMan.teacher(id);
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
    public String viewStudent(@RequestParam(value = "idSubject", required = true)Long id,@RequestParam(value = "idClass", required = true)Long studentClass,ModelMap m,Authentication auth)
    {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher teacher  = teacherMan.findByLogin(login);
        if(teacher == null)
        {
            return "admin/errorHups";
        }
        StudentClass sc = studentClassMan.findById(studentClass);
        //SubjectOfClass teacher = subjectOfClassMan.teacher(studentClass);
        //  List<SubjectOfClass> subject = subjectOfClassMan.findByClassSubjectTeacherVisibleID(sc,teacher.getId_subject(), teacher);
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
    public String studentInfo(@RequestParam(value = "next", required = true)Long next,@RequestParam(value = "previous", required = true)Long previous,@RequestParam(value = "semester", required = false)String semester,@RequestParam(value = "idSubject", required = true)Long idSubject,@RequestParam(value = "idStudent",required = true)Long idStudent,ModelMap m,Authentication auth)
    {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher teacher  = teacherMan.findByLogin(login);
        if(teacher == null)
        {
            return "admin/errorHups";
        }
        Student student = studentMan.findById(idStudent);
        List<Student> listOfStudents = studentMan.findByClass_id(student.getId_class());
        SubjectOfClass subject = subjectOfClassMan.findByClassSubjectTeacherVisibleID(student.getId_class().getId(), idSubject,teacher.getId());
        if(subject ==null || student==null)
        {
            return"admin/errorHups";
        }
        //první známku z předmětu
        Calendar findByStudentMINDate = testResultMan.findByStudentMINDate(student, subject.getId_subject());
        // testResult s datumem
        List <TestResultClassificationForm> list = new ArrayList<TestResultClassificationForm>();
        //seznam školních let
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
        //byl zadán
        else{           
          
             split = semester.split("/");
        }
        System.out.println("plist "+split[0]+" "+split[1]);
            Calendar first = dateFunction.setDBDate(1, 9, Integer.valueOf(split[0]));
            Calendar last = dateFunction.setDBDate(31, 1, Integer.valueOf(split[1]));
            firstSemester = testResultMan.findByStudentBetween(student, subject.getId_subject(),first, last);
            System.out.println("firstSemester "+firstSemester);
            Calendar secondFirst = dateFunction.setDBDate(1,2, Integer.valueOf(split[1]));
            Calendar secondLast = dateFunction.setDBDate(31,8, Integer.valueOf(split[1]));
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
         }
        if(firstAVG>0){
        firstAVG= firstAVG/count;
        }
        count=0;
        if(!secondSemester.isEmpty()){
       for(TestResult r :secondSemester)
        {
          secondForm.add(  resultToForm(new TestResultClassificationForm(), r));
          if(r.getClassified().equals(Short.valueOf("0")))
          {
              if(secondAVG>0){
              secondAVG+=r.getMark();
              }
              count++;
          }
          
        }
        }
       secondAVG=secondAVG/count;
      //  System.out.println("size semestrů "+listOfSemester.size());
        m.addAttribute("students", student);
        m.addAttribute("subjectClassification", subject);
        m.addAttribute("listSemester", listOfSemester);
        
        m.addAttribute("first", firstForm);
        m.addAttribute("second", secondForm);
         m.addAttribute("firstAVG", firstAVG);
        m.addAttribute("secondAVG", secondAVG);
        Long[] nextPrevious = nextPrevious(listOfStudents, student);
        
      m.addAttribute("next", nextPrevious[0]);
      m.addAttribute("previous",nextPrevious[1]);
        m.addAttribute("classification",true);
      //  m.addAttribute("listOfMark", studentMan);
       
       
        return"teacher/classification/studentInfo";
    }
    @RequestMapping(value = "/teacher/classification/studentInfo/changeWebMark.htm", method = RequestMethod.GET)
    public String changeWebMark(@RequestParam(value = "idResult",required = true)Long id,ModelMap m,Authentication auth){
        TestResult findById = testResultMan.findById(id);
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher findById1  = teacherMan.findByLogin(login);
        if(findById1 == null)
        {
            return "admin/errorHups";
        }
        List<SubjectOfClass> findByClassSubjectTeacher = subjectOfClassMan.findByClassSubjectTeacher(findById.getStudent().getId_class(), findById.getTest().getId_subject(), findById1);
        if(findById==null || findByClassSubjectTeacher.isEmpty())
        {
           return"admin/errorHups";
        }
        ChangeMarkWebForm markToForm = actualWebMarkToForm( findById);
        m.addAttribute("classification",true);
        m.addAttribute("testResult",findById);
        m.addAttribute("form",markToForm);
        return"teacher/classification/changeWebMark";
    }
    @RequestMapping(value = "/teacher/classification/studentInfo/changeWebMark.htm", method = RequestMethod.POST)
    public String changeWebMarkPost(@ModelAttribute(value = "form") ChangeMarkWebForm form,ModelMap m, Authentication auth){
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher teacher  = teacherMan.findByLogin(login);
        if(teacher == null)
        {
            return "admin/errorHups";
        }
        TestResult findById = testResultMan.findById(form.getId());
        List<SubjectOfClass> findByClassSubjectTeacher = subjectOfClassMan.findByClassSubjectTeacher(findById.getStudent().getId_class(), findById.getTest().getId_subject(), teacher);
        if(findById==null || findByClassSubjectTeacher.isEmpty())
        {
           return"admin/errorHups";
        }
        findById.setMark(form.getMark());
        findById.setTeacher(teacher);
        testResultMan.edit(findById, true);
        return"redirect:/teacher/classification/studentInfo.htm?idSubject="+findById.getTest().getId_subject().getId()+"&idStudent="+findById.getStudent().getId()+"&next="+(findById.getStudent().getId()+1)+"&previous="+(findById.getStudent().getId()-1);
    }
    @RequestMapping(value ="/teacher/classification/studentInfo/changeMark.htm", method = RequestMethod.GET)
    public String changeMarkGET (@RequestParam(value = "idResult",required = true)Long id,ModelMap m,Authentication auth){
         TestResult findById = testResultMan.findById(id);
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher findById1  = teacherMan.findByLogin(login);
        if(findById1 == null)
        {
            return "admin/errorHups";
        }
        List<SubjectOfClass> findByClassSubjectTeacher = subjectOfClassMan.findByClassSubjectTeacher(findById.getStudent().getId_class(), findById.getTest().getId_subject(), findById1);
        if(findById==null || findByClassSubjectTeacher.isEmpty())
        {
           return"admin/errorHups";
        }
        ChangeMarkForm form = actualMarkToForm(findById);
        m.addAttribute("classification",true);
        m.addAttribute("testResult",findById);
        m.addAttribute("form",form);
        return"teacher/classification/changeMark";
    }
    @RequestMapping(value = "/teacher/classification/studentInfo/changeMark.htm", method = RequestMethod.POST)
    public String changeMarkPost (@Valid@ModelAttribute(value = "form") ChangeMarkForm form, BindingResult error,ModelMap m, Authentication auth)
    {
        if(error.hasErrors())
        {
            TestResult findById = testResultMan.findById(form.getId());
            form.setMap();
        m.addAttribute("classification",true);
        m.addAttribute("testResult",findById);
        m.addAttribute("form",form);
        }
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher teacher  = teacherMan.findByLogin(login);
        if(teacher == null)
        {
            return "admin/errorHups";
        }
        TestResult findById = testResultMan.findById(form.getId());
        List<SubjectOfClass> findByClassSubjectTeacher = subjectOfClassMan.findByClassSubjectTeacher(findById.getStudent().getId_class(), findById.getTest().getId_subject(), teacher);
        if(findById==null || findByClassSubjectTeacher.isEmpty())
        {
           return"admin/errorHups";
        }
        if(error.hasErrors())
        {
          form.setMap();
        m.addAttribute("classification",true);
        m.addAttribute("testResult",findById);
        m.addAttribute("form",form);
        return"teacher/classification/changeMark";
        }
        TestResult formToChangeMark = formToChangeMark(form, findById);
        testResultMan.edit(formToChangeMark, true);
        return"redirect:/teacher/classification/studentInfo.htm?idSubject="+formToChangeMark.getTest().getId_subject().getId()+"&idStudent="+formToChangeMark.getStudent().getId()+"&next=0&previous=0";
    }
    
   @RequestMapping(value = "/teacher/classification/studentInfo/addMark.htm", method = RequestMethod.GET)
   public String addMarkGet(@RequestParam(value = "idStudent", required = true)Long id,@RequestParam(value = "idSubject",required = true)Long idSubject,ModelMap m,Authentication auth)
   {
       if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher teacher  = teacherMan.findByLogin(login);
        if(teacher == null)
        {
            return "admin/errorHups";
        }
        Student findById = studentMan.findById(id);
        SubjectOfClass subject = subjectOfClassMan.findById(idSubject);
       
      
        if(teacher ==null || findById==null || subject==null || !subject.getId_class().getId().equals(findById.getId_class().getId()))
        {
            
           return"admin/errorHups";
        }
        NewMarkForm form = new  NewMarkForm();
        form.setMap();
        form.setClassified(Short.valueOf("0"));
        form.setMarkDate(dateFunction.getDateString(Calendar.getInstance()));
        form.setId(id);
        form.setIdSubject(idSubject);
        form.setIdTeacher(teacher.getId());
       m.addAttribute("classification",true);
       m.addAttribute("form",form);
       return"teacher/classification/addMark";
   }
   @RequestMapping(value = "/teacher/classification/studentInfo/addMark.htm", method = RequestMethod.POST)
   public String addMarkPost (@Valid@ModelAttribute(value = "form") NewMarkForm form, BindingResult error,ModelMap m)
   {
       if(error.hasErrors())
       {
            m.addAttribute("classification",true);
            form.setMap();
       m.addAttribute("form",form);
       return"teacher/classification/addMark";
       }
        SubjectOfClass findById = subjectOfClassMan.findById(form.getIdSubject());
        Teacher findById1 = teacherMan.findById(form.getIdTeacher());
        Student findById2 = studentMan.findById(form.getId());
       
        if(findById==null || findById1==null)
        {
            return"admin/errorHups";
        }
        Test newTest = newTest(form, findById1,findById.getId_subject());
        TestResult formToTestResult = formToTestResult(form, newTest,findById2);
        testResultMan.add(formToTestResult, true);
         
       return"redirect:/teacher/classification/studentInfo.htm?idSubject="+form.getIdSubject()+"&idStudent="+form.getId()+"&next=0&previous=0";
   }
   
   @RequestMapping (value = "/teacher/classification/studentInfo/removeMark.htm")
   public String removeMakr(@RequestParam(value = "idResult", required = true)Long id,Authentication auth)
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
        TestResult findById1 = testResultMan.findById(id);
        List<SubjectOfClass> findByClassSubjectTeacher = subjectOfClassMan.findByClassSubjectTeacher(findById1.getStudent().getId_class(),findById1.getTest().getId_subject(), findById);
        if(findByClassSubjectTeacher.isEmpty())
        {
            return"admin/errorHups";
        }
        if(findById1.getWebTest())
        {
           findById1.setActualMark(Boolean.FALSE);
           
        }
        else{
            
            
                findById1.setVisible(Boolean.FALSE);
              
                
            
        }
        testResultMan.edit(findById1, true);
       return"redirect:/teacher/classification/studentInfo.htm?idSubject="+findById1.getTest().getId_subject().getId()+"&idStudent="+findById1.getStudent().getId()+"&next=0&previous=0";
   }
   @RequestMapping(value = "/teacher/classification/studentView/addMarks.htm", method = RequestMethod.GET)
   public String addMarksGet(@RequestParam(value = "idSubject", required = true)Long idSubject,@RequestParam(value = "idClass", required = true) Long idClass,ModelMap m,Authentication auth)
   {
        if (auth == null) {
            return "admin/errorHups";
        }
        User u = (User) auth.getPrincipal(); //if auth != null
        String login = u.getUsername();
        Teacher teacher  = teacherMan.findByLogin(login);
        if(teacher == null)
        {
            return "admin/errorHups";
        }
        StudentClass findById = studentClassMan.findById(idClass);
        SubjectOfClass findById1 = subjectOfClassMan.findById(idSubject);
        if(teacher==null || findById==null || findById1==null || !findById.getId().equals(findById1.getId_class().getId()) || !findById1.getId_teacher().getId().equals(teacher.getId()))
        {
            return"admin/errorHups";
        }
        List<Student> findByClass_id = studentMan.findByClass_id(findById);
        List<NewMarkStudentForm> list = new ArrayList();
        for(Student s :findByClass_id)
        {
            NewMarkStudentForm newMark = new NewMarkStudentForm();
            newMark.setId(s.getId());
            newMark.setClassified(Short.valueOf("0"));
            newMark.setLogin(s.getLogin());
            newMark.setMark(Short.valueOf("1"));
            newMark.setName(s.getName());
            newMark.setSurname(s.getSurname());
            newMark.setMark(Short.valueOf("1"));
           // newMark.setMap();
            list.add(newMark);
                    
        }
        NewClassMarksForm form= new NewClassMarksForm();
        form.setDate(dateFunction.getDateString(Calendar.getInstance()));
        form.setIdSubject(findById1.getId_subject().getId());
        form.setIdTeacher(teacher.getId());
        form.setListOfStudents(list);
        form.setIdClass(idClass);
        form.setMap();
        
        m.addAttribute("classification",true);
        m.addAttribute("form",form);
       return"teacher/classification/addClassMarks";
   }
   @RequestMapping(value="/teacher/classification/studentView/addMarks.htm", method = RequestMethod.POST)
   public String addMarksPost(@Valid@ModelAttribute(value = "form") NewClassMarksForm form, BindingResult error, ModelMap m)
   {
       if(error.hasErrors())
       {
           form.setMap();
          m.addAttribute("classification",true);
         m.addAttribute("form", form);
        
       return"teacher/classification/addClassMarks"; 
       }
        Test newTest = newTest(form);
        if(newTest==null)
        {
            return"admin/errorHups";
        }
        int count=0;
        for(NewMarkStudentForm s:form.getListOfStudents())
        {
            
            TestResult formToTestResult = formToTestResult(s, newTest, form.getDate());
            if(formToTestResult==null)
            {
                message.setNegativeMes("Studentovi "+s.getName()+" "+s.getSurname()+" ( "+s.getLogin()+") nebyla přiřazena známka!");
            }
            else{
                count++;
                testResultMan.add(formToTestResult, false);
            }
           
        }
        if(count>0)
        {
            message.setPositiveMes("Známky byly přiřazeny.");
        }
       return"redirect:/teacher/classification/studentView.htm?idSubject="+form.getIdSubject()+"&idClass="+form.getIdClass();
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
    private ChangeMarkWebForm actualWebMarkToForm(TestResult testResult)
    {
        if(testResult!=null){
        ChangeMarkWebForm form = new ChangeMarkWebForm();
        form.setId(testResult.getId());
        form.setMark(testResult.getMark());
        form.setMap();
        
        return form;}
        return null;
    }
    private ChangeMarkForm actualMarkToForm(TestResult testResult)
    {
        if(testResult!=null){
        ChangeMarkForm form = new ChangeMarkForm();
        form.setId(testResult.getId());
       
        form.setClassified(testResult.getClassified());
        if(testResult.getClassified().equals(Short.valueOf("0"))){
        form.setMark(testResult.getMark());
        
        }
        else{
            form.setMark(Short.valueOf("1"));
        }
        form.setMap();
        form.setMarkDate(dateFunction.getDateString(testResult.getTestDate()));
        return form;}
        return null;
    }
    

    
    private Long[] nextPrevious(List<Student> list, Student findById1)
    {
        Long [] nextPrevous = new Long [2];
        Long next=Long.valueOf("0");
        Long previous=Long.valueOf("0");
        
        int size = list.size();
        if(list.size()>=3){
            System.out.println("mám víc jak 3 studenty");
        for (int i = 0; i < size; i++) {
            Student get = list.get(i);
            //student byl nalezen v listu
            
            if(get.getId().equals(findById1.getId()))
            {
                System.out.println("našel jsem ho");
                //student je první záznam v listu
                if(i==0)
                {
                    System.out.println("první student v listu");
                    
                    next=list.get(i+1).getId();
                previous=list.get(i).getId();
                }else{
                    //student je poslední záznam v listu
                    if(i==size-1)
                    {
                        System.out.println("POSLEDNÍ STUDENT V LISTU");
                        next=list.get(i).getId();
                previous=list.get(i-1).getId();
                    }
                    //studen je někde uprostřed listu
                    else{
                        System.out.println("někde tam je");
                        
                next=list.get(i+1).getId();
                previous=list.get(i-1).getId();
                System.out.println("next "+next);
                System.out.println("student"+findById1.getId());
                System.out.println("previous"+previous);
                    }
                }
            }
        }        
        }
        else{
            //třída má méně nez 3 studenty
            if(size==2)
            {
                System.out.println("list není prázdný");
                next=list.get(1).getId();
                previous=list.get(0).getId();
            
                
            
            }
            else
            {
               next=findById1.getId();
               previous=findById1.getId();
            }
            
            
        }
        
        nextPrevous[0]=next;
        nextPrevous[1]=previous;
        return nextPrevous;
    }
 private TestResult formToChangeMark(ChangeMarkForm form, TestResult testResult) 
 {
       String[] split = dateFunction.splitDataString(form.getMarkDate());
        Calendar setDBDate = dateFunction.setDBDate(Integer.parseInt(split[0]),Integer.parseInt( split[1]),Integer.parseInt(split[2]));
        testResult.setTestDate(setDBDate);
        if(form.getClassified().equals(Short.valueOf("0")))
        {
            testResult.setMark(form.getMark());
        }
        else{
            testResult.setMark(Short.valueOf("0"));
        }
        testResult.setClassified(form.getClassified());
     return testResult;
 }
    private TestResult formToTestResult(NewMarkForm form,Test t,Student s)
    {
        TestResult testResult = new TestResult();
        testResult.setTest(t);
        testResult.setClassified(form.getClassified());
        if(testResult.getClassified().equals(Short.valueOf("0")))
        {
            testResult.setMark(form.getMark());
        }
        else{
            testResult.setMark(Short.valueOf("0"));
        }
        
        testResult.setVisible(Boolean.TRUE);
        testResult.setActualMark(Boolean.TRUE);
        testResult.setWebTest(Boolean.FALSE);
        String[] split = dateFunction.splitDataString(form.getMarkDate());
        Calendar setDBDate = dateFunction.setDBDate(Integer.parseInt(split[0]),Integer.parseInt( split[1]),Integer.parseInt(split[2]));
        testResult.setTestDate(setDBDate);
        testResult.setStudent(s);
        return testResult;
    }
    
    private Test newTest (NewMarkForm form, Teacher teacher,Subject subject)
    {
        Test test = new Test();
        test.setName(form.getNameOfMark());
        test.setId_subject(subject);
        test.setId_teacher(teacher);
        test.setVisible(Boolean.TRUE);
        test.setTaught(Boolean.TRUE);
        testMan.add(test, false);
        
        return test;
    }
    private Test newTest (NewClassMarksForm form)
    {
        Subject findById = subjectMan.findById(form.getIdSubject());
        Teacher findById1 = teacherMan.findById(form.getIdTeacher());
        if(findById==null || findById1==null)
        {
            return null;
        }
        Test test = new Test();
        test.setName(form.getName());
        test.setId_subject(findById);
        test.setId_teacher(findById1);
        test.setVisible(Boolean.TRUE);
        test.setTaught(Boolean.TRUE);
        testMan.add(test, false);
        
        return test;
    }
    private TestResult formToTestResult(NewMarkStudentForm form,Test t,String date)
    {
        Student findById = studentMan.findById(form.getId());
        if(findById==null)
        {
            return null;
        }
        TestResult testResult = new TestResult();
        testResult.setTest(t);
        testResult.setClassified(form.getClassified());
        if(testResult.getClassified().equals(Short.valueOf("0")))
        {
            testResult.setMark(form.getMark());
        }
        else{
            testResult.setMark(Short.valueOf("0"));
        }
        
        testResult.setVisible(Boolean.TRUE);
        testResult.setActualMark(Boolean.TRUE);
        testResult.setWebTest(Boolean.FALSE);
        String[] split = dateFunction.splitDataString(date);
        Calendar setDBDate = dateFunction.setDBDate(Integer.parseInt(split[0]),Integer.parseInt( split[1]),Integer.parseInt(split[2]));
        testResult.setTestDate(setDBDate);
        testResult.setStudent(findById);
        return testResult;
    }
}

