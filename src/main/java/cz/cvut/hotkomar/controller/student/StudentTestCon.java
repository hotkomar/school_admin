/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.student;

import cz.cvut.hotkomar.form.CheckPassForm;
import cz.cvut.hotkomar.form.student.ViewAnswerForm;
import cz.cvut.hotkomar.form.student.ViewQuestionForm;
import cz.cvut.hotkomar.form.student.ViewTestForm;
import cz.cvut.hotkomar.model.entity.Answer;
import cz.cvut.hotkomar.model.entity.Question;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Test;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.checkAndMake.TestResultCheck;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TestMan;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Maru
 */
@Controller
@Secured(value = {"ROLE_STUDENT"})
public class StudentTestCon {

    private StudentMan studentMan;
    private TestMan testMan;
    private SubjectOfClassMan subjectOfClassMan;
    private TestResultCheck testResultCheck;
    private TestResultMan testResultMan;
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
    public void setTestMan(TestMan testMan) {
        this.testMan = testMan;
    }

    @Autowired
    public void setSubjectOfClassMan(SubjectOfClassMan subjectOfClassMan) {
        this.subjectOfClassMan = subjectOfClassMan;
    }
@Autowired
    public void setTestResultCheck(TestResultCheck testResult) {
        this.testResultCheck = testResult;
    }
@Autowired
    public void setTestResultMan(TestResultMan testResult) {
        this.testResultMan = testResult;
    }
 
    
    
    @RequestMapping(value = "/student/test.htm")
    public String view(ModelMap m,Authentication auth) {
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
        CheckPassForm form = new CheckPassForm();
        form.setId(findById.getId());
        m.addAttribute("form",form );
        m.addAttribute("test", true);
        return "student/test/view";
    }

    /**
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/student/openTest.htm",method = RequestMethod.POST)
    public String openTest(@ModelAttribute("form") CheckPassForm form, ModelMap m) {
        
        Student findById  = studentMan.findById(form.getId());
        if(findById == null)
        {
            return "admin/errorHups";
        }
        Test findBypass = testMan.findBypass(form.getPassword());
        
        
       
        //najdu předmět podle třídy
        List<SubjectOfClass> findByIdClass = subjectOfClassMan.findByIdClass(findById.getId_class().getId());
        //najít resutl
       
        boolean hasTest = false;
        //našel jsem test
        if (findBypass != null) {
            System.out.println("student "+findById.getName()+" "+findById.getSurname());
            System.out.println("test "+findBypass.getName());
          //  projedu předměty třídy
            for (SubjectOfClass subject : findByIdClass) {
                System.out.println("subject ve for cyklu id of  class"+subject.getId()+"jmého a id předmětu "+subject.getId_subject().getName()+" "+subject.getId_class().getId());
                
                if (subject.getId_subject().getId().equals(findBypass.getId_subject().getId()) && subject.getId_teacher().getId().equals(findBypass.getId_teacher().getId())) {
                    System.out.println("našel jsem předmět");
                    hasTest = true; //předmět je vyučovaný ve třídě
                }
            }
            System.out.println("has test po for cyklu "+hasTest);
            if(hasTest){
            //najdu výsledek testu podle studenta
            System.out.println("hledám podle findById "+findById.getName()+findById.getId());
             System.out.println("hledám podle findById "+findBypass.getName()+" "+findBypass.getPassword()+" "+findBypass.getId());
            TestResult findByStudentTest = testResultMan.findByStudentTestActualMark(findById, findBypass);
            
            System.out.println("findBySrudent je "+findByStudentTest);
            if(findByStudentTest!=null)
            {
                System.out.println("id studenta je "+findById.getId());
                System.out.println("našel jsem výsledek testu "+findByStudentTest.getId());
                hasTest=false;
            }
            else
            {
                TestResult testResult = new TestResult();
                testResult.setStudent(findById);
                testResult.setTest(findBypass);
                testResult.setVisible(Boolean.TRUE);
                testResult.setTestDate(Calendar.getInstance());
                testResult.setActualMark(Boolean.TRUE);
                testResult.setWebTest(Boolean.TRUE);
                testResult.setClassified(Short.valueOf("0"));
                testResultMan.add(testResult, false);
                System.out.println("vytvořil jsem nový výsledek testu "+testResult.getClassified());
            }
            }
        }
        System.out.println("hasTest "+hasTest);
        if (hasTest == false) {
            message.setNegativeMes("Test s tímto heslem neexistuje nebo k němu nemáte povolený přístup.");
            return "redirect:/student/test.htm";
        }
        ViewTestForm testToForm = testToForm(findBypass,findById.getId());
        m.addAttribute("form", testToForm);
        m.addAttribute("test", true);
        return "student/test/test";
    }
@RequestMapping(value = "/student/saveTest.htm")
    public String saveTestPost(@ModelAttribute("form") ViewTestForm form, ModelMap m) {
        Student student = studentMan.findById(form.getIdStudent());
        Test findById = testMan.findById(form.getIdTest());
        TestResult findByStudentTest = testResultMan.findByStudentTestActualMark(student, findById);
        if(findByStudentTest==null)
        {
            System.out.println("ID STUDENTA JE "+findById.getId());
            System.out.println("nenašla jsem výsledek testu");
            return"admin/errorHups";
        }
        TestResult testResult = testResultCheck.checkTest(form,findByStudentTest);
        
        if(testResult!=null){
        testResultMan.edit(testResult, false);
        }
        else{
            System.out.println("neopravil jsem test");
            return"admin/errorHups";
        }
        m.addAttribute("testResult", testResult);
return"student/test/testResult" ;
}

    private ViewTestForm testToForm(Test test,Long studentId) {
        System.out.println("test " + test.getName());
        ViewTestForm form = new ViewTestForm();
        form.setIdTest(test.getId());
        form.setIdStudent(studentId);
        form.setName(test.getName());
        form.setQuestions(questionToForm(test.getQuestions()));
        System.out.println("jsem v testu, který má " + form.getQuestions().size());
        return form;
    }

    private List<ViewQuestionForm> questionToForm(Set<Question> question) {
        System.out.println("vytvářím otázky");

        List<ViewQuestionForm> questionForm = new ArrayList<ViewQuestionForm>();
        for (Iterator<Question> i = question.iterator(); i.hasNext();) {
            System.out.println("projíždím otázky");
            ViewQuestionForm questions = new ViewQuestionForm();
            Question next = i.next();
            questions.setId(next.getId());
            questions.setQuestion(next.getQuestion());
            questions.setPoints(next.getPoints());
            questions.setAnswers(answerToForm(next.getAnswers()));
            System.out.println("otázka má " + questions.getAnswers().size() + " odpovědí");
            questionForm.add(questions);


        }
        System.out.println("test má " + questionForm.size() + " otázek");
        return questionForm;
    }

    private List<ViewAnswerForm> answerToForm(Set<Answer> answers) {
        System.out.println("vytvářím odpovědi");
        List<ViewAnswerForm> answerForm = new ArrayList<ViewAnswerForm>();
        for (Iterator<Answer> i = answers.iterator(); i.hasNext();) {
            System.out.println("projíždím odpovědi");
            ViewAnswerForm form = new ViewAnswerForm();
            Answer next = i.next();
            form.setId(next.getId());
            form.setAnswer(next.getAnswer());
            form.setCorrect(Boolean.FALSE);
            answerForm.add(form);

        }
        System.out.println("délka listu je " + answerForm.size());
        return answerForm;
    }
}
