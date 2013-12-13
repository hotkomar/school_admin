/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.teacher;

import cz.cvut.hotkomar.form.teacher.ChangeMarkWebForm;
import cz.cvut.hotkomar.form.teacher.TestResultClassificationForm;
import cz.cvut.hotkomar.form.teacher.ChangeMarkForm;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.checkAndMake.TestResultCheck;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String viewStudent(@RequestParam(value = "idSubject", required = true)Long id,@RequestParam(value = "idClass", required = true)Long studentClass,ModelMap m)
    {
        Teacher teacher = teacherMan.findById(Long.valueOf("1"));
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
    public String studentInfo(@RequestParam(value = "next", required = true)Long next,@RequestParam(value = "previous", required = true)Long previous,@RequestParam(value = "semester", required = false)String semester,@RequestParam(value = "idSubject", required = true)Long idSubject,@RequestParam(value = "idStudent",required = true)Long idStudent,ModelMap m)
    {
        Teacher teacher = teacherMan.findById(Long.valueOf("1"));
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
        for(TestResult r :firstSemester)
        {
          firstForm.add(  resultToForm(new TestResultClassificationForm(), r));
        }
       for(TestResult r :secondSemester)
        {
          secondForm.add(  resultToForm(new TestResultClassificationForm(), r));
        }
        System.out.println("size semestrů "+listOfSemester.size());
        m.addAttribute("students", student);
        m.addAttribute("subjectClassification", subject);
        m.addAttribute("listSemester", listOfSemester);
        
        m.addAttribute("first", firstForm);
        m.addAttribute("second", secondForm);
        Long[] nextPrevious = nextPrevious(listOfStudents, student);
        
      m.addAttribute("next", nextPrevious[0]);
      m.addAttribute("previous",nextPrevious[1]);
        m.addAttribute("classification",true);
      //  m.addAttribute("listOfMark", studentMan);
       
       
        return"teacher/classification/studentInfo";
    }
    @RequestMapping(value = "/teacher/classification/studentInfo/changeWebMark.htm", method = RequestMethod.GET)
    public String changeWebMark(@RequestParam(value = "idResult",required = true)Long id,ModelMap m){
        TestResult findById = testResultMan.findById(id);
        if(findById==null)
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
    public String changeWebMarkPost(@ModelAttribute(value = "form") ChangeMarkWebForm form,ModelMap m){
        Teacher teacher = teacherMan.findById(Long.valueOf("1"));
        TestResult findById = testResultMan.findById(form.getId());
        if(findById==null)
        {
           return"admin/errorHups";
        }
        findById.setMark(form.getMark());
        findById.setTeacher(teacher);
        testResultMan.edit(findById, true);
        return"redirect:/teacher/classification/studentInfo.htm?idSubject="+findById.getTest().getId_subject().getId()+"&idStudent="+findById.getStudent().getId()+"&next="+(findById.getStudent().getId()+1)+"&previous="+(findById.getStudent().getId()-1);
    }
    @RequestMapping(value ="/teacher/classification/studentInfo/changeMark.htm", method = RequestMethod.GET)
    public String changeMarkPost (@RequestParam(value = "idResult",required = true)Long id,ModelMap m){
        TestResult findById = testResultMan.findById(id);
        if(findById==null)
        {
           return"admin/errorHups";
        }
        ChangeMarkForm form = actualMarkToForm(findById);
        m.addAttribute("classification",true);
        m.addAttribute("testResult",findById);
        m.addAttribute("form",form);
        return"teacher/classification/changeMark";
    }
    private TestResultClassificationForm resultToForm (TestResultClassificationForm form, TestResult testResult)
    {
        form.setId(testResult.getId());
        form.setMark(testResult.getMark().toString());
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
        if(testResult.getClassified().equals(0)){
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
    
//    private List <TestResult> firstSemester (String semester, List<String> listOfSemester,Student student,Subject subject)
//    {
//        List <TestResult>list;
//        String schoolYear=null;
//        if(semester!=null)
//        {
//           //atribut byl vyplněn
//            System.out.println("semester isn't null");
//            
//            for(String s :listOfSemester)
//        {
//            //atribut byl nalezen
//           if( s.equals(semester))
//           {
//               System.out.println("found semester"+s);
//               schoolYear=s;
//           }
//        }
//          if(schoolYear!=null)  
//          {
//              System.out.println("schoolYear not null");
//                String[] split = schoolYear.split("/");
//                Calendar first = dateFunction.setDate(1, 9, Integer.valueOf(split[0]));
//                Calendar last = dateFunction.setDate(31, 1, Integer.valueOf(split[0]));
//              list=  testResultMan.findByStudentBetween(student, subject, first, last);
//              System.out.println("list je dlouhý "+list.size());
//              return list;
//          }
//        }
//        System.out.println("not found semester");
//        int size = listOfSemester.size();
//        schoolYear = listOfSemester.get(size-1);
//        String[] split = schoolYear.split("/");
//                Calendar first = dateFunction.setDate(1, 9, Integer.valueOf(split[0]));
//                Calendar last = dateFunction.setDate(31, 1, Integer.valueOf(split[0]));
//                System.out.println("first "+dateFunction.getDateString(first));
//                System.out.println("last "+dateFunction.getDateString(last));
//              list=  testResultMan.findByStudentBetween(student, subject, first, last);
//              System.out.println("list je dlouhý "+list.size());
//              return list;
//        
//    }
    
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
    
    
}

