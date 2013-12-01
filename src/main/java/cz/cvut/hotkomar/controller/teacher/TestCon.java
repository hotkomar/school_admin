/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.teacher;

//import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import cz.cvut.hotkomar.form.ChangePassForm;
import cz.cvut.hotkomar.form.CheckPassForm;
import cz.cvut.hotkomar.form.RemovePasswordForm;
import cz.cvut.hotkomar.form.admin.FullTextForm;
import cz.cvut.hotkomar.form.admin.NewTeacherForm;
import cz.cvut.hotkomar.form.teacher.AnswerForm;
import cz.cvut.hotkomar.form.teacher.ChangeMarkForm;
import cz.cvut.hotkomar.form.teacher.NewTestForm;
import cz.cvut.hotkomar.form.teacher.QuestionForm;
import cz.cvut.hotkomar.form.teacher.ResultTestForm;
import cz.cvut.hotkomar.model.entity.Answer;
import cz.cvut.hotkomar.model.entity.Question;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.Test;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.checkAndMake.CheckForm;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.manager.AnswerMan;
import cz.cvut.hotkomar.service.manager.QuestionMan;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.TestMan;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import cz.cvut.hotkomar.service.pagination.Pagination;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.bcel.verifier.statics.DOUBLE_Upper;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Marie Hoťková
 */
@Controller
public class TestCon {
   private SubjectOfClassMan subjectOfClassMan;
   private TeacherMan teacherMan;
   private SubjectMan subjectMan;
   private  TestMan testMan;
   private QuestionMan questionMan;
   private AnswerMan answerMan;
   private Pagination pagination;
   private CheckForm checkForm;
  private  TestResultMan testResultMan;
  private StudentClassMan  studentClassMan;
  private StudentMan studentMan;
  private  DateFunction dateFunction;
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
    public void setQuestionMan(QuestionMan questionMan) {
        this.questionMan = questionMan;
    }
@Autowired
    public void setCheckForm(CheckForm checkForm) {
        this.checkForm = checkForm;
    }
@Autowired
    public void setAnswerMan(AnswerMan answerMan) {
        this.answerMan = answerMan;
    }
@Autowired
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
@Autowired
    public void setStudentClassMan(StudentClassMan studentClassMan) {
        this.studentClassMan = studentClassMan;
    }
@Autowired
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }

   
   
   @Autowired
    public void setTestMan(TestMan testMan) {
        this.testMan = testMan;
    }
@Autowired
    public void setTestResultMan(TestResultMan testResultMan) {
        this.testResultMan = testResultMan;
    }
   
   
    
   
   @Autowired
    public void setSubjectMan(SubjectMan subjectMan) {
        this.subjectMan = subjectMan;
    }
   
   
   
@Autowired
    public void setTeacherMan(TeacherMan teacherMan) {
        this.teacherMan = teacherMan;
    }
   
   
@Autowired
    public void setSubjectOfClassMan(SubjectOfClassMan subjectOfClassMan) {
        this.subjectOfClassMan = subjectOfClassMan;
    }
@Autowired
    public void setStudentMan(StudentMan sutdentMan) {
        this.studentMan = sutdentMan;
    }
   
   
    
    @RequestMapping(value="/teacher/addTest.htm", method = RequestMethod.GET)
    public String addTestGET (ModelMap m,HttpSession session, @ModelAttribute("form") NewTestForm form)
    {if(session.getAttribute("addTestContainer") == null){
            session.setAttribute("addTestContainer", form);
        }
        m.addAttribute("test",true);
        return"teacher/test/addTest";
    }
    @RequestMapping(value = "/teacher/addTestLoad.htm")
    public String addTestForm(ModelMap m, HttpSession s, @RequestParam(value = "ok",required = false) Boolean ok){   
        System.out.println("LOAD FORM");
        /*
         * *********************************************************
         * *********************************************************
         * *********************************************************
         */
        
        m.addAttribute("org.springframework.validation.BindingResult.form", s.getAttribute("addNewTestErrors"));
        
        
       Teacher findById = teacherMan.findById(Long.valueOf("1"));
        m.addAttribute("listSubject",getSubjects(findById));
        m.addAttribute("form", (NewTestForm) s.getAttribute("addTestContainer"));
        if(ok != null){
            if(ok == true){
               m.addAttribute("saved", true);
            }
            if(ok==false)
            {
                m.addAttribute("saved",false);
            }
        }
        return "teacher/test/formItems";
    }
    @RequestMapping(value = "/teacher/addTestClear.htm")
    public String addTestClear(ModelMap m, HttpSession s){
        if(s.getAttribute("addTestContainer") != null){
            s.setAttribute("addTestContainer", new NewTestForm());
        }
        return "redirect:addtest.htm";
    }
    
    @RequestMapping(value = "/teacher/addQuestion.htm")
    public String addQuestion(ModelMap m, HttpSession s){
        System.out.println("ADD NEW QUESTION");
        NewTestForm testContainer = (NewTestForm) s.getAttribute("addTestContainer");
        testContainer.getQuestions().add(new QuestionForm());
        s.setAttribute("addTestContainer", testContainer);
        return "redirect:addTestLoad.htm";
    }
    @RequestMapping(value = "/teacher/addQuestionDelete.htm", method = RequestMethod.GET)
    public String add2delQuestion(ModelMap m, HttpSession s, @RequestParam("idx") Integer idx){
        System.out.println("DELETING QUESTION "+idx);
        NewTestForm container = (NewTestForm) s.getAttribute("addTestContainer");
        container.getQuestions().remove(idx.intValue());
        s.setAttribute("addTestContainer", container);
        return "redirect:addTestLoad.htm";
    }
    @RequestMapping(value = "/teacher/addAnswer.htm")
    public String add2addAnswer(ModelMap m, HttpSession s, @RequestParam("idxQ") Integer idxQ){
        System.out.println("ADDING ANSWER");
        NewTestForm container = (NewTestForm) s.getAttribute("addTestContainer");
        container.getQuestions().get(idxQ).getAnswers().add(new AnswerForm());
        s.setAttribute("addTestContainer", container);
        return "redirect:addTestLoad.htm";
    }
    @RequestMapping(value = "/teacher/addAnswerDelete.htm", method = RequestMethod.GET)
    public String addAnswerDelete(ModelMap m, HttpSession s, @RequestParam("idxQ") Integer idxQ, @RequestParam("idxA") Integer idxA){
        System.out.println("DELETING ANSWER");
        NewTestForm container = (NewTestForm) s.getAttribute("addTestContainer");
        container.getQuestions().get(idxQ).getAnswers().remove(idxA.intValue());
        s.setAttribute("addTestContainer", container);
        return "redirect:addTestLoad.htm";
    }
    
    @RequestMapping(value = "/teacher/addTest.htm", method = RequestMethod.POST)
    public String addTestPOST(ModelMap m, HttpSession s, @Valid @ModelAttribute("form") NewTestForm testForm, BindingResult errors){
        //adding errors
        s.setAttribute("addNewTestErrors", errors);
        
        System.out.println("POSTING");
        System.out.println(testForm.getName());
        for(QuestionForm q: testForm.getQuestions()){
            System.out.println("Q "+q.getAnswers());
        }
        
        s.setAttribute("addTestContainer", testForm);
        return null;
}
   @RequestMapping(value="/teacher/saveTest.htm", method= RequestMethod.GET)
   public String saveTestPost(ModelMap m, HttpSession s)
   {
       BindingResult errors = (BindingResult) s.getAttribute("addNewTestErrors");
       if(errors.hasErrors()){
           return "redirect:addTestLoad.htm?ok=false";
       }
       System.out.println("SAVE");
       NewTestForm attribute =(NewTestForm) s.getAttribute("addTestContainer");
       Test formToTest = formToTest(attribute,new Test(), Long.parseLong("1"));
       if(formToTest==null)
       {
           return"admin/errorHups";
       }
       testMan.add(formToTest, true);
       saveQuestion(formToTest,attribute.getQuestions());
       s.setAttribute("addTestContainer", new NewTestForm());
       return"redirect:addTestLoad.htm?ok=true"; //jenom kdyz je validni
   }
    @RequestMapping  (value = "/teacher/tests.htm")
    public String viewTest (ModelMap m,@RequestParam(defaultValue = "1", value = "page", required = false) Integer page,HttpSession session)
    {String attribute =(String)session.getAttribute("testFullText");
       Teacher findById = teacherMan.findById(Long.valueOf("1"));
       List<SubjectOfClass> findByTeacher = subjectOfClassMan.findByTeacher(findById);
       if(findByTeacher.size()>0)
       {
           m.addAttribute("subjects",true);
       }
       else{
         m.addAttribute("subjects", false);  
       }
      // List<Test> findAll = testMan.findByTeacher(findById);
      //  System.out.println("počet testů "+findAll.size());
        List<Test>  list=  new ArrayList<Test>();;
        if(attribute==null){
        list = testMan.findByTeacher(findById);
        }else{
            if(!attribute.isEmpty())            
            {
                List<Test> findFullText = testMan.findFullText(attribute);
                for (Test t :findFullText) {
                    if(t.getId_teacher().equals(findById)){
                         
                        list.add(t);
                    }
//                    if(list.isEmpty())
//                    {
//                         list = new ArrayList<Test>();
//                    }   
                }
        System.out.println("finfFullText"+list);
            }
            else{
                list = testMan.findByTeacher(findById);
            }
        }
        
        
        
        
        
        FullTextForm fullTextForm = new FullTextForm();
        fullTextForm.setFullText(attribute);
       pagination.setList(list);
       m.addAttribute("listTest",pagination.paginationList(list, page));
        m.addAttribute("pageForm",pagination.getPageForm());
     //  m.addAttribute("listTest", findAll);
        m.addAttribute("form",fullTextForm);
        m.addAttribute("test",true);
        return"teacher/test/viewTest";
    }
    
    @RequestMapping (value="/teacher/testFullText.htm")
    public String viewPOST(@ModelAttribute("form") FullTextForm form, BindingResult errors, HttpSession session) {
       session.setAttribute("testFullText", form.getFullText());
               
        return "redirect:tests.htm";
    }
    
    
    @RequestMapping(value = "/teacher/infoTest.htm")
    public String infoTest (@RequestParam (value="test", required = true)Long id, ModelMap m)
    {
       Teacher teacher = teacherMan.findById(Long.parseLong("1"));
       Test findById = testMan.findByIDTeacher(id, teacher);
       if(findById==null)
       {
           return "/admin/errorHups";
       }
       m.addAttribute("mark",pointArray(findById));
      m.addAttribute("point",countPointOfTest(findById));
       m.addAttribute("tests", findById);
       m.addAttribute("test",true);
       m.addAttribute("testI",true);
        return"/teacher/test/infoTest";
    }
    @RequestMapping(value = "/teacher/infoTest/results.htm")
    public String testResults (@RequestParam(value = "test", required = true)Long id,ModelMap m)
    {
        
       Teacher teacher = teacherMan.findById(Long.parseLong("1"));
       Test findById = testMan.findByIDTeacher(id, teacher);
       // List<TestResult> findByTest = testResultMan.findByTest(findById);
       List<SubjectOfClass> findBySubjectTeacher = subjectOfClassMan.findBySubjectTeacher(findById.getId_subject(),findById.getId_teacher());
       if(findById==null)
       {
           return "/admin/errorHups";
       }
       List<StudentClass> classVisible = studentClassMan.getClassVisbleFalseSubjectOfClass(findById.getId_subject().getId(), findById.getId());
        System.out.println("BLABLA "+classVisible);
         m.addAttribute("classVisible", classVisible);
       m.addAttribute("mark",pointArray(findById));
      m.addAttribute("point",countPointOfTest(findById));
       m.addAttribute("tests", findById);
       m.addAttribute("test",true);
       m.addAttribute("resultTest",true);
      m.addAttribute("result",findBySubjectTeacher);
       
        return"/teacher/test/testResults";
    }
    @RequestMapping(value = "/teacher/infoTest/classResult.htm")
    public String classResult(@RequestParam(value = "classID", required = true) Long id,@RequestParam(value ="test", required = true)Long idTest,ModelMap m)
    {
        System.out.println("lkdsjkdslfjlkdsjfdslkfjlksdjfdlkjdsaflksajkdsalůjldsjlfdsůafdsj");
        Teacher teacher = teacherMan.findById(Long.parseLong("1"));
       Test findById = testMan.findByIDTeacher(idTest, teacher);
        StudentClass clazz = studentClassMan.findById(id);
        if(findById==null || clazz==null)
        {
            return"admin/errorHups";
        }
       //  List<TestResult> findByClass = testResultMan.findByClass(student);
       List<Student> findByClass_id = studentMan.findByClass_id(clazz);
       
         m.addAttribute("mark",pointArray(findById));
      m.addAttribute("point",countPointOfTest(findById));
       m.addAttribute("tests", findById);
     m.addAttribute("results",findByClass_id);
     m.addAttribute("test",true);
     m.addAttribute("resultTest",true);
     m.addAttribute("clazzID",clazz.getId());
    
     
        return"teacher/test/classResult";
    }
    @RequestMapping(value = "/teacher/infoTest/removeMark.htm")
    public String removeResult(@RequestParam(value = "result", required = true) Long id,@RequestParam(value ="test", required = true)Long idTest,@RequestParam(value = "idClass", required = true)Long clazzID)
    {
        Teacher teacher = teacherMan.findById(Long.parseLong("1"));
       Test findById = testMan.findByIDTeacher(idTest, teacher);
//        StudentClass clazz = studentClassMan.findById(id);
       TestResult findById1 = testResultMan.findById(id);
        if(findById==null || findById1==null)
        {
            return"admin/errorHups";
        }
       testResultMan.visible(id, true);
        
        return"redirect:studentResult.htm?student="+findById1.getStudent().getId()+"&test="+idTest+"&idClass="+clazzID;
    }
    @RequestMapping(value = "/teacher/infoTest/newResult.htm")
    public String newResult(@RequestParam(value = "student", required = true) Long id,@RequestParam(value ="test", required = true)Long idTest,@RequestParam(value = "idClass", required = true)Long clazzID)
    {
        Teacher teacher = teacherMan.findById(Long.parseLong("1"));
       Test findById = testMan.findByIDTeacher(idTest, teacher);
       //        StudentClass clazz = studentClassMan.findById(id);
       Student student = studentMan.findById(id);
       TestResult findById1 = testResultMan.findByStudentActualMark(student);
        if(findById==null || findById1==null)
        {
            return"admin/errorHups";
        }
       findById1.setActualMark(Boolean.FALSE);
       testResultMan.edit(findById1, true);
        
        return"redirect:studentResult.htm?student="+findById1.getStudent().getId()+"&test="+idTest+"&idClass="+clazzID;
    }
    @RequestMapping(value = "/teacher/infoTest/studentResult.htm", method = RequestMethod.GET)
    public String studentResult(@RequestParam(value = "student", required = true) Long id,@RequestParam(value ="test", required = true)Long idTest,@RequestParam(value="idClass",required = true)Long clazz, ModelMap m)
    {
        Teacher teacher = teacherMan.findById(Long.parseLong("1"));
       Test findById = testMan.findByIDTeacher(idTest, teacher);
        Student student = studentMan.findById(id);
        if(findById==null || student==null)
        {
            return"admin/errorHups";
        }
       List<TestResult> findByStudent = testResultMan.findByStudent(student);
       List<ResultTestForm> forms = new ArrayList<ResultTestForm>();
       for(TestResult r :findByStudent)
       {
            ResultTestForm testResultToForm = testResultToForm(r);
            forms.add(testResultToForm);
       }
       TestResult findByStudentActualMark=null;
       if(!subjectOfClassMan.findByIdClass(student.getId_class().getId()).isEmpty()){
           
      findByStudentActualMark = testResultMan.findByStudentActualMark(student);
    }
       
       
         m.addAttribute("mark",pointArray(findById));
      m.addAttribute("point",countPointOfTest(findById));
       m.addAttribute("tests", findById);
     m.addAttribute("results",forms);
     m.addAttribute("test",true);
     m.addAttribute("resultTest",true);
     m.addAttribute("form", actualMarkToForm(findByStudentActualMark));
     m.addAttribute("clazzID", clazz);
        return"teacher/test/studentResults";
    }
    @RequestMapping(value = "/teacher/infoTest/studentResult.htm", method = RequestMethod.POST)
    public String studentResultPost(@ModelAttribute(value = "form") ChangeMarkForm form)
    {
       TestResult findById = testResultMan.findById(form.getId());
       if(findById==null)
       {
          return"admin/errorHups";
       }
        findById.setMark(form.getMark());
        testResultMan.edit(findById, true);
        return"redirect:studentResult.htm?student="+findById.getStudent().getId()+"&test="+findById.getTest().getId()+"&idClass="+findById.getStudent().getId_class().getId();
    }
    @RequestMapping(value = "/teacher/removeTest.htm")
    public String removeTest(@RequestParam(value = "id", required = true)Long id)
    {
       Teacher findById = teacherMan.findById(Long.valueOf("1"));
       Test findByIDTeacher = testMan.findByIDTeacher(id,findById);
       if(findByIDTeacher==null)
       {
           return"admin/errorHups";
       }
       List<TestResult> findByTest = testResultMan.findByTest(findByIDTeacher);
       if(findByTest.isEmpty()){
       testMan.visible(id, true);
       return"redirect:tests.htm";
       }
       
           message.setNegativeMes("Test nelze smazat, existují vypracovaná řešení");
       return"redirect:/teacher/infoTest/results.htm?test="+findByIDTeacher.getId();
        
    }
    @RequestMapping(value = "/teacher/infoTest/statistic.htm")
    public String viewStatistic (@RequestParam(value = "test", required = true)Long id,ModelMap m)
    {
       Teacher findById = teacherMan.findById(Long.valueOf("1"));
       Test findByIDTeacher = testMan.findByIDTeacher(id, findById);
       if(findByIDTeacher==null)
       {
           return"admin/errorHups";
       }
       //       Integer findByAvarageActualMarkClass = testResultMan.findByAvarageActualMarkClass(findByIDTeacher, Short.valueOf("2"));
       //        System.out.println("count je "+findByAvarageActualMarkClass);
       List<Double> statisticMark = statisticMark(findByIDTeacher);
       Double findByAvarageActualMark = testResultMan.findByAvarageActualMarkClass(findByIDTeacher, studentClassMan.findById(Long.valueOf("1")));
      // Double findByAvarageActualMarkClass = testResultMan.findByAvarageActualMarkClass(findByIDTeacher, studentClassMan.findById(Long.valueOf("1")));
       Map<StudentClass, Double> findByAvarageActualMarkClass1 = testResultMan.findByAvarageActualMarkClass(findByIDTeacher);
       
       
       m.addAttribute("prumer", findByAvarageActualMark);
       m.addAttribute("ListMarks", statisticMark);
        m.addAttribute("markCount",testResultMan.findByAvarageActualMark(findByIDTeacher));
       m.addAttribute("mark",pointArray(findByIDTeacher));
      m.addAttribute("point",countPointOfTest(findByIDTeacher));
        m.addAttribute("tests", findByIDTeacher);
        m.addAttribute("test",true);
        m.addAttribute("resultStatistic",true);
        m.addAttribute("clazzAVG",findByAvarageActualMarkClass1);
    // m.addAttribute("classStatistic",null);
        return"teacher/test/testStatistic";
    }
    @RequestMapping(value = "/teacher/infoTest/removeClassRresult.htm")
    public String removeClassResults (@RequestParam(value = "idClass", required = true)Long id,@RequestParam(value = "test",required = true) Long testId,ModelMap m)
    {
       Teacher teacher = teacherMan.findById(Long.valueOf("1"));
       Test findByIDTeacher = testMan.findByIDTeacher(testId, teacher);
       StudentClass findById = studentClassMan.findById(id);
       
       if(findById==null || findByIDTeacher==null)
       {
           return"admin/errorHups";
       }
       List<TestResult> findByClass = testResultMan.findByClass(findById);
       removeTestResultsFromClass(findByClass);
       return"redirect:results.htm?test="+testId;
    }
    @RequestMapping(value = "/teacher/changeTestPass.htm", method = RequestMethod.GET)
    public String changeTest(@RequestParam(value = "id", required = true)Long id, ModelMap m )
    {
       Teacher findById = teacherMan.findById(Long.valueOf("1"));
       Test findByIDTeacher = testMan.findByIDTeacher(id,findById);
       if(findByIDTeacher==null)
       {
           return"admin/errorHups";
       }
       
       boolean pass=false;
       if(findByIDTeacher.getPassword()!=null)
       {
           pass=true;
       }
       ChangePassForm form = new ChangePassForm();
       form.setId(id);
       m.addAttribute("test", true);
       m.addAttribute("form",form);
       m.addAttribute("change",true);
       m.addAttribute("idTest", id);
       m.addAttribute("hasPass",pass);
        return"teacher/test/pass";
    }
    @RequestMapping(value = "/teacher/changeTestPass.htm", method = RequestMethod.POST)
    public String changeTestPost(@Valid@ModelAttribute("form") ChangePassForm form, BindingResult error, ModelMap m)
    {Teacher findById = teacherMan.findById(Long.valueOf("1"));
       Test findByIDTeacher = testMan.findByIDTeacher(form.getId(), findById);
    if(findByIDTeacher==null)
        {
            return"admin/errorHups";
        }
            
        if(!checkForm.corectPassword(form.getActualPass(),findById.getPassword()) || error.hasErrors())
        {
           boolean pass=false;
       if(findByIDTeacher.getPassword()!=null)
       {
           pass=true;
       }
       m.addAttribute("test", true);
       m.addAttribute("form",form);
       m.addAttribute("change",true);
       m.addAttribute("idTest", form.getId());
       m.addAttribute("hasPass",pass);
        return"teacher/test/pass"; 
       
        
        }
       findByIDTeacher.setPassword(form.getNewPass());
       testMan.edit(findByIDTeacher,true);
        
        
        return"redirect:/teacher/infoTest.htm?id="+form.getId();
    }
    @RequestMapping(value = "/teacher/viewPass.htm", method = RequestMethod.GET)
    public String viewPass(@RequestParam(value = "id", required = true)Long id, ModelMap m)
    {
       Teacher findById = teacherMan.findById(Long.valueOf("1"));
       Test findByIDTeacher = testMan.findByIDTeacher(id,findById);
       if(findByIDTeacher==null || findByIDTeacher.getPassword()==null)
       {
           return"admin/errorHups";
       }
       CheckPassForm form = new CheckPassForm();
       form.setId(id);
       m.addAttribute("view", true);
       m.addAttribute("form",form);
       m.addAttribute("test",true);
       m.addAttribute("idTest", id);
       
        return"teacher/test/viewPass";
    }
    @RequestMapping(value = "/teacher/viewPass.htm", method = RequestMethod.POST)
    public String viewPassPost(@Valid@ModelAttribute("form") CheckPassForm form,BindingResult error, ModelMap m)
    {
       Teacher findById = teacherMan.findById(Long.valueOf("1"));
       Test findByIDTeacher = testMan.findByIDTeacher(form.getId(), findById);
       if(findByIDTeacher==null)
       {
           return"admin/errorHups";
       }
       if(error.hasErrors()|| !checkForm.corectPassword(findById.getPassword(),form.getPassword()))
       {
          
           m.addAttribute("view", true);
       m.addAttribute("form",form);
       m.addAttribute("test",true);
      
       return"teacher/test/viewPass";
       }
        RemovePasswordForm form1 = new RemovePasswordForm();
        form1.setId(form.getId());
        form1.setPassword(findByIDTeacher.getPassword());
       m.addAttribute("view", true);
       m.addAttribute("idTest",form.getId());
     //  m.addAttribute("viewPassword", findByIDTeacher.getPassword());
       m.addAttribute("test",true);
       m.addAttribute("form",form1);
      
       m.addAttribute("viewPassword", findByIDTeacher.getPassword());
        return"teacher/test/viewPassword";
    }
    @RequestMapping(value = "/teacher/removePass.htm", method = RequestMethod.POST)
    public String removePassPost(@Valid @ModelAttribute("form") RemovePasswordForm form, ModelMap m, BindingResult error)
    {
       Teacher findById = teacherMan.findById(Long.valueOf("1"));
       Test findByIDTeacher = testMan.findByIDTeacher(form.getId(), findById);
       if(findByIDTeacher==null )
       {
           return"admin/errorHups";
       }
       if(error.hasErrors())
       {
           System.out.println("errror "+error.toString());
           m.addAttribute("view", true);
       m.addAttribute("form",form);
       m.addAttribute("test",true);
       m.addAttribute("viewPassword", null);
       return"teacher/test/viewPass";
       }
       findByIDTeacher.setPassword(null);
       testMan.edit(findByIDTeacher, true);
        return"redirect:infoTest.htm?test="+form.getId();
    }
    private Map<Long, String> getSubjects(Teacher t) {
        Map<Long, String> map = new HashMap<Long, String>();
        List<SubjectOfClass> findAll = subjectOfClassMan.findByTeacher(t);
        for (SubjectOfClass sc : findAll) {
            map.put(sc.getId_subject().getId(),sc.getId_subject().getName());

        }
        return map;
    }
    
    private Test formToTest (NewTestForm form, Test test, Long idTeacher)
    {
        test.setName(form.getName());
        test.setFive(form.getFive());
        test.setFour(form.getFour());
        test.setThree(form.getFour());
        test.setTwo(form.getTwo());
     //   test.setPassword(form.getPassword());
        test.setTaught(Boolean.TRUE);
        test.setVisible(Boolean.TRUE);
        System.out.println("předmět "+form.getId_subject());
       Teacher teacher= teacherMan.findById(idTeacher);
       Subject subject = subjectMan.findById(form.getId_subject());
       
       if(teacher== null || subject==null  )
       {
           return null;
       }
        test.setId_teacher(teacher);
        test.setId_subject(subject);
      
        System.out.println("délka otázek" +form.getQuestions().size());
       
        
        return test;
    }
    
    private void saveQuestion(Test t, List<QuestionForm> q)
    {
        Set<Question> questions = new HashSet<Question>();
        for (Iterator<QuestionForm> i=q.iterator(); i.hasNext();) {
            QuestionForm questionForm = i.next();
            Question question = new Question();
            question.setPoints(questionForm.getPoints());
            question.setVisible(Boolean.TRUE);
            question.setQuestion(questionForm.getQuestion());
            questionMan.add(question,false);
            questions.add(question);
            saveAnswer(question, questionForm.getAnswers());
            
        }
        t.setQuestions(questions);
        testMan.edit(t, false);
        
    }
    private void saveAnswer (Question q, List<AnswerForm> a)
    {
        Set<Answer> answers = new HashSet<Answer>();
        for(Iterator<AnswerForm> i =a.iterator(); i.hasNext();)
        {
            AnswerForm answerForm = i.next();
            Answer answer = new Answer();
            answer.setAnswer(answerForm.getAnswer());
            answer.setCorrect(answerForm.getCorrect());
            answer.setVisible(Boolean.TRUE);
            answerMan.add(answer, true);
            answers.add(answer);
        }
        q.setAnswers(answers);
        questionMan.edit(q, true);
    }
    
    
    private int countPointOfTest(Test test)
    {
        int count =0;
     for(Iterator<Question> i = test.getQuestions().iterator(); i.hasNext();)
     {
            Question next = i.next();
         count+=next.getPoints();
     }
     return count;
    }
    private double [] pointArray (Test test)
    {
        double [] pointArray = new double [5];
       double  countPointOfTest = countPointOfTest(test);
        System.out.println("countPoinOfTest "+countPointOfTest);
      double onePercent = countPointOfTest/100.0;
        System.out.println("one percent "+onePercent);
       
        pointArray[0]=(onePercent*test.getFive());
        System.out.println("five "+pointArray[0]);
        pointArray[1]=(onePercent*test.getFour());
         System.out.println("four "+pointArray[1]);
        pointArray[2]=(onePercent*test.getThree());
         System.out.println("two "+pointArray[2]);
        pointArray[3]=(onePercent*test.getTwo());
         System.out.println("one "+pointArray[3]);
       // pointArray[4]=(int)(onePercent*test.get());
        return pointArray;
      
    }
    
    
    private ResultTestForm testResultToForm (TestResult result)
    {
        ResultTestForm form = new ResultTestForm();
        form.setId(result.getId());
        form.setActualMark(result.getActualMark());
        form.setMark(result.getMark());
        form.setPercent(result.getPercent());
       String dateString = dateFunction.getDateString(result.getTestDate());
        form.setTestResultDate(dateString);
        form.setIdStudent(result.getStudent().getId());
        
        
        return form;
    }
    private ChangeMarkForm actualMarkToForm(TestResult testResult)
    {
        if(testResult!=null){
        ChangeMarkForm form = new ChangeMarkForm();
        form.setId(testResult.getId());
        form.setMark(testResult.getMark());
        form.setMap();
        
        return form;}
        return null;
    }
    
    private void removeTestResultsFromClass (List<TestResult> classResult)
    {
        if(classResult.isEmpty()){
            message.setNegativeMes("Žádný student neměl vypracovaný tento test");
        }
        else{
            for(TestResult r: classResult)
            {
                testResultMan.visible(r.getId(), false);
            }
            message.setPositiveMes("Výsledky testu byly smazány.");
        }
    }
    private List<Double> statisticMark ( Test test)
    {
        List <Double> list = new ArrayList<Double>();
       Integer findByAvarageActualMark = testResultMan.findByAvarageActualMark(test);
       double percent = 100.0/(double)findByAvarageActualMark.intValue();
        System.out.println("*******************************************************");
         System.out.println("*******************************************************");
          System.out.println("*******************************************************");
        System.out.println("1 procento je "+percent);
         System.out.println("*******************************************************");
          System.out.println("*******************************************************");
           System.out.println("*******************************************************");
        for (int i = 1; i < 6; i++) {
            Integer findByAvarageActualMark1 = testResultMan.findByAvarageActualMark(test, Short.valueOf(i+""));
            double percentMark = (double)findByAvarageActualMark1.intValue()*percent;
            System.out.println("percent Mark"+" i="+i+" "+percentMark);
            list.add(Double.valueOf(percentMark));
            
            
            
        }
        return list;
    }
}
