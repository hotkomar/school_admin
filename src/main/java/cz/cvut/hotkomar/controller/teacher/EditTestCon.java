/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.teacher;

import cz.cvut.hotkomar.form.teacher.AnswerForm;
import cz.cvut.hotkomar.form.teacher.EditAnswerForm;
import cz.cvut.hotkomar.form.teacher.EditQuestionForm;
import cz.cvut.hotkomar.form.teacher.EditTestForm;
import cz.cvut.hotkomar.form.teacher.NewTestForm;
import cz.cvut.hotkomar.form.teacher.QuestionForm;
import cz.cvut.hotkomar.model.entity.Answer;
import cz.cvut.hotkomar.model.entity.Question;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.Test;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.manager.AnswerMan;
import cz.cvut.hotkomar.service.manager.QuestionMan;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.TestMan;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.solr.common.util.Hash;
import org.hibernate.dialect.function.AnsiTrimFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Maru
 */
@Controller
public class EditTestCon {

    private SubjectOfClassMan subjectOfClassMan;
    private TeacherMan teacherMan;
    private SubjectMan subjectMan;
    private TestMan testMan;
    private QuestionMan questionMan;
    private AnswerMan answerMan;
    private TestResultMan testResultMan;
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
    public void setAnswerMan(AnswerMan answerMan) {
        this.answerMan = answerMan;
    }

    @Autowired
    public void setTestMan(TestMan testMan) {
        this.testMan = testMan;
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
    public void setTestResultMan(TestResultMan testResultMan) {
        this.testResultMan = testResultMan;
    }

    @RequestMapping(value = "/teacher/editTest.htm", method = RequestMethod.GET)
    public String editTestGET(ModelMap m, HttpSession session, @ModelAttribute("form") EditTestForm form, @RequestParam(value = "id", required = true) Long id) {

        Test findById = testMan.findById(id);
        if (findById == null) {
            return "admin/errorHups";
        }
        List<TestResult> findByTest = testResultMan.findByTest(findById);
        if(!findByTest.isEmpty())
        {
            message.setNegativeMes("Test nelze editovat, jestliže byl již vypracován nekterým ze studentů.");
            return "redirect:infoTest/results.htm?id="+findById.getId();
        }
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println("Test " + findById.getName());
        for (Iterator<Question> i = findById.getQuestions().iterator(); i.hasNext();) {
            Question q = i.next();
            for (Iterator<Answer> x = q.getAnswers().iterator(); x.hasNext();) {
                System.out.println("zadání otázky " + q.getQuestion() + " id je " + q.getId());
                Answer a = x.next();
                System.out.println("odpověď je " + a.getAnswer() + " id je " + a.getId() + " a je " + a.getCorrect());
            }
        }
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");

        EditTestForm testToForm = testToForm(new EditTestForm(), findById);
        System.out.println("id je " + testToForm.getId());
//        if(session.getAttribute("editTestContainer") == null){      
        session.setAttribute("editTestContainer", testToForm);
//            session.setAttribute("idTest", id);

//        }

        m.addAttribute("test", true);
        return "teacher/test/editTest";
    }

    @RequestMapping(value = "/teacher/editTestLoad.htm")
    public String editTestForm(ModelMap m, HttpSession s, @RequestParam(value = "ok", required = false) Boolean ok) {
        System.out.println("LOAD FORM");


//        m.addAttribute("form", testToForm);
        m.addAttribute("org.springframework.validation.BindingResult.form", s.getAttribute("editNewTestErrors"));




        // m.addAttribute("listSubject",getSubjects(findById));
        EditTestForm form = (EditTestForm) s.getAttribute("editTestContainer");
//        System.out.println("*********************************************************");
//       System.out.println("***************Formulář*****************");
//        System.out.println("*********************************************************");
//        System.out.println("Test "+form.getName());
//        for (Iterator<EditQuestionForm> i = form.getQuestions().iterator();i.hasNext();) {
//            EditQuestionForm q = i.next();
//            System.out.println("zadání otázky "+q.getQuestion() +" id je "+q.getId()+" a je za "+q.getPoints().toString()+" bodů");
//            for (Iterator<EditAnswerForm> x =q.getAnswers().iterator();x.hasNext();) {
//                
//                EditAnswerForm a=x.next();
//                System.out.println("odpověď je "+a.getAnswer()+" id je "+a.getId()+" a je "+a.getCorrect());
//            }
//        }
//        System.out.println("*********************************************************");
//        System.out.println("***************Formulář*****************");
//        System.out.println("*********************************************************");
        Teacher t = teacherMan.findById(form.getId_teacher());
        Subject sub = subjectMan.findById(form.getId_subject());
        Map<Long, String> subjectswithoutActual = getSubjectswithoutActual(t, sub);
        m.addAttribute("listSubject", subjectswithoutActual);

        m.addAttribute("form", form);
        if (ok != null) {
            if (ok == true) {
                m.addAttribute("saved", true);
            }
            if (ok == false) {
                m.addAttribute("saved", false);
            }
        }
        System.out.println("RETURNING FORM");
        return "teacher/test/editFormItems";
    }

    @RequestMapping(value = "/teacher/editTestClear.htm")
    public String editTestClear(ModelMap m, HttpSession s) {
        if (s.getAttribute("editTestContainer") != null) {
            s.setAttribute("editTestContainer", new EditTestForm());
        }
        return "redirect:edittest.htm";
    }

    @RequestMapping(value = "/teacher/editQuestion.htm")
    public String editQuestion(ModelMap m, HttpSession s) {
        System.out.println("ADD NEW QUESTION");
        EditTestForm testContainer = (EditTestForm) s.getAttribute("editTestContainer");
        testContainer.getQuestions().add(new EditQuestionForm());
        s.setAttribute("editTestContainer", testContainer);
        return "redirect:editTestLoad.htm";
    }

    @RequestMapping(value = "/teacher/editQuestionDelete.htm", method = RequestMethod.GET)
    public String editDelQuestion(ModelMap m, HttpSession s, @RequestParam("idx") Integer idx) {
        System.out.println("DELETING QUESTION " + idx);
        EditTestForm container = (EditTestForm) s.getAttribute("editTestContainer");
        container.getQuestions().remove(idx.intValue());
        s.setAttribute("editTestContainer", container);
        return "redirect:editTestLoad.htm";
    }

    @RequestMapping(value = "/teacher/editAnswer.htm")
    public String editAddAnswer(ModelMap m, HttpSession s, @RequestParam("idxQ") Integer idxQ) {
        System.out.println("ADDING ANSWER");
        EditTestForm container = (EditTestForm) s.getAttribute("editTestContainer");
        container.getQuestions().get(idxQ).getAnswers().add(new EditAnswerForm());
        s.setAttribute("editTestContainer", container);
        return "redirect:editTestLoad.htm";
    }

    @RequestMapping(value = "/teacher/editAnswerDelete.htm", method = RequestMethod.GET)
    public String editAnswerDelete(ModelMap m, HttpSession s, @RequestParam("idxQ") Integer idxQ, @RequestParam("idxA") Integer idxA) {
        System.out.println("DELETING ANSWER");
        EditTestForm container = (EditTestForm) s.getAttribute("editTestContainer");
        container.getQuestions().get(idxQ).getAnswers().remove(idxA.intValue());
        s.setAttribute("editTestContainer", container);
        return "redirect:editTestLoad.htm";
    }

    @RequestMapping(value = "/teacher/editTest.htm", method = RequestMethod.POST)
    public String editTestPOST(ModelMap m, HttpSession s, @Valid @ModelAttribute("form") EditTestForm testForm, BindingResult errors) {
        //adding errors
        s.setAttribute("editNewTestErrors", errors);

        System.out.println("POSTING");
        System.out.println(testForm.getName());
//        for(EditQuestionForm q: testForm.getQuestions()){
//            System.out.println("Q "+q.getAnswers());
//        }

        s.setAttribute("editTestContainer", testForm);
        return null;
    }

    @RequestMapping(value = "/teacher/saveEditTest.htm", method = RequestMethod.GET)
    public String saveEditTest(ModelMap m, HttpSession s) {
        BindingResult errors = (BindingResult) s.getAttribute("editNewTestErrors");
        if (errors.hasErrors()) {
            System.out.println("VALID FALSE");
            return "redirect:editTestLoad.htm?ok=false";
        }
        System.out.println("SAVE");
        EditTestForm attribute = (EditTestForm) s.getAttribute("editTestContainer");
        Test findById = testMan.findById(attribute.getId());
        if (findById == null) {
            return "admin/errorHups";
        }
        Test formToTest = formToTest(attribute, findById);
        
        testMan.edit(formToTest, true);

        // s.setAttribute("editTestContainer", new EditTestForm());
        return "redirect:editTestLoad.htm?ok=true"; //jenom kdyz je validni
    }

    private Map<Long, String> getSubjectswithoutActual(Teacher t, Subject subjectID) {
        Map<Long, String> map = new HashMap<Long, String>();
        List<SubjectOfClass> findAll = subjectOfClassMan.findByTeacher(t);
        for (SubjectOfClass sc : findAll) {
            if (sc.getId_subject() != subjectID) {
                map.put(sc.getId_subject().getId(), sc.getId_subject().getName());
            }
        }
        map.put(subjectID.getId(), subjectID.getName());
        return map;
    }

    private EditTestForm testToForm(EditTestForm form, Test test) {
        form.setName(test.getName());
        form.setFive(test.getFive());
        form.setFour(test.getFour());
        form.setThree(test.getThree());
        form.setTwo(test.getTwo());
        form.setId(test.getId());
        form.setTaught(Boolean.TRUE);
        form.setVisible(Boolean.TRUE);
        System.out.println("předmět " + form.getId_subject());
        form.setId_teacher(test.getId_teacher().getId());
        form.setId_subject(test.getId_subject().getId());
        form.setVisible(Boolean.TRUE);
        Set<Question> questions = test.getQuestions();
        List<EditQuestionForm> editQuestionForms = new ArrayList<EditQuestionForm>();
        for (Iterator<Question> i = questions.iterator(); i.hasNext();) {
            Question question = i.next();
            EditQuestionForm questionToForm = questionToForm(new EditQuestionForm(), question);
            editQuestionForms.add(questionToForm);
        }
        form.setQuestions(editQuestionForms);

        System.out.println("délka otázek" + form.getQuestions().size());


        return form;
    }

    private EditQuestionForm questionToForm(EditQuestionForm form, Question question) {
        form.setQuestion(question.getQuestion());
        form.setPoints(question.getPoints());
        form.setId(question.getId());
        form.setVisible(Boolean.TRUE);
        Set<Answer> answers = question.getAnswers();
        List<EditAnswerForm> editAnswerForms = new ArrayList<EditAnswerForm>();
        for (Iterator<Answer> i = answers.iterator(); i.hasNext();) {
            Answer answer = i.next();
            EditAnswerForm answerToForm = answerToForm(new EditAnswerForm(), answer);
            editAnswerForms.add(answerToForm);
        }
        form.setAnswers(editAnswerForms);
        System.out.println("Otázka čislo: " + form.getId());
        System.out.println("Znění otázky " + form.getQuestion() + " je  za " + form.getPoints() + " bodů");
        return form;
    }

    private EditAnswerForm answerToForm(EditAnswerForm form, Answer answer) {
        form.setId(answer.getId());
        form.setAnswer(answer.getAnswer());
        form.setCorrect(answer.getCorrect());
        form.setVisible(Boolean.TRUE);
        System.out.println("odpověď číslo: " + form.getId());
        System.out.println("znění: " + form.getAnswer() + " " + form.getCorrect());
        return form;
    }

    private Test formToTest(EditTestForm form, Test test) {
        // test.setId(form.getId());
        test.setName(form.getName());
        Subject findById = subjectMan.findById(form.getId_subject());
        if (findById == null) {
            return null;
        }
        test.setId_subject(findById);
        test.setFive(form.getFive());
        test.setFour(form.getFour());
        test.setThree(form.getThree());
        test.setTwo(form.getTwo());
        Set<Question> editQuestionList = editQuestionList(form, test);
        if(editQuestionList==null)
        {
            return null;
        }
        test.setQuestions(editQuestionList);
        


        return test;
    }

    private Set<Question> editQuestionList(EditTestForm form, Test test) {
        Set<Question> questions = new HashSet<Question>();//přidá se do testu
        List<Long> questionsForm = new ArrayList<Long>(); //id otázek z formuláře
        List<Long> questionsDB = new ArrayList<Long>();//id otázek z databáze
        for (EditQuestionForm question : form.getQuestions()) {        //uložit id z formuláře do listu    
            questionsForm.add(question.getId());
        }
        for (Iterator<Question> i = test.getQuestions().iterator(); i.hasNext();) { //uložit id z otázek z databáze
            Question next = i.next();
            questionsDB.add(next.getId());
        }
        questionsDB.removeAll(questionsForm);//vrátí pouze id prvků, které jsou smazané 
        for (int i = 0; i < questionsDB.size(); i++) { //mazání
            System.out.println("otázka je " + questionsDB.get(i));
            Question findById = questionMan.findById(questionsDB.get(i)); //najdu otázku podle  id
            Set<Answer> answers = new HashSet<Answer>();
            if (findById != null) { //existuje                
                answers = findById.getAnswers();//uložím odpovědi
                List<Answer> listAnswer = new ArrayList<Answer>();
                for (Iterator<Answer> j = answers.iterator(); j.hasNext();) {
                    Answer next = j.next();
                    listAnswer.add(next);
                }
                findById.setAnswers(null); //zruším cizí klíč
                System.out.println("list of answer je dlouhý " + listAnswer.size());
                questionMan.edit(findById, true);//uložím do db bez odpovědí                
                for (Answer a : listAnswer) {
                    answerMan.delete(a);
                }
                Set<Question> questions1 = test.getQuestions(); //uložím si otázky testu                
                questions1.remove(findById);//uberu otázku                
                test.setQuestions(questions1);//uložm otázky do testu               
                testMan.edit(test, true); //uložím test do db                
                questionMan.delete(findById);//smažu otázku                
            }
            else{
                return null;
            }
        }
        for (int i = 0; i < form.getQuestions().size(); i++) {
            if(form.getQuestions().get(i).getId()==null){
            Question formToQuestion = formToQuestion(form.getQuestions().get(i), new Question());
                Set<Answer> editAnswer = editAnswer(form.getQuestions().get(i), formToQuestion);
                formToQuestion.setAnswers(editAnswer);
            questionMan.add(formToQuestion, true);
            questions.add(formToQuestion);
            }
            else{
                Question findById = questionMan.findById(form.getQuestions().get(i).getId());
                if(findById!=null)
                {
                    Question formToQuestion = formToQuestion(form.getQuestions().get(i), findById);
                    Set<Answer> editAnswer = editAnswer(form.getQuestions().get(i), formToQuestion);
                formToQuestion.setAnswers(editAnswer);
                    questionMan.edit(formToQuestion, true);
                    questions.add(formToQuestion);
                }
                else{
                return null;
            }
            }
            
        }
        return questions;
    }
private Set<Answer> editAnswer (EditQuestionForm form, Question q){
    Set<Answer> answers= new HashSet<Answer>();// set odpovědí
     List<Long> answerForm = new ArrayList<Long>(); //id odpovědí z formuláře
        List<Long> answerDB = new ArrayList<Long>();//id odpovědí z databáze
        for (EditAnswerForm answer : form.getAnswers()) {        //uložit id z formuláře do listu    
            answerForm.add(answer.getId());
        }
        for (Iterator<Answer> i = q.getAnswers().iterator(); i.hasNext();) { //uložit id z odpovědí z databáze
            Answer next = i.next();
            answerDB.add(next.getId());
        }
        q.setAnswers(null);// odstraním odpovědi otázce, abych je mohla editovat
        questionMan.edit(q, false);
    answerDB.removeAll(answerForm);
    for (int i = 0; i < answerDB.size(); i++) { //mazání
        Answer findById = answerMan.findById(answerDB.get(i));
        answerMan.delete(findById);
    }
    for (int i = 0; i < form.getAnswers().size(); i++) { //editace přidání
       
        if(form.getAnswers().get(i).getId()!=null) //editace odpovědi
        {
            Answer findById = answerMan.findById(form.getAnswers().get(i).getId()); //najdu odpověď
            if(findById!=null) //pokud opravdu je v dv
            {
                Answer formToAnswer = formToAnswer(form.getAnswers().get(i), findById); //změním
                answerMan.edit(formToAnswer,false); //edituji
                answers.add(formToAnswer); //přidám do SET
            }
            else{
                return null;
            }
        }else{
            Answer formToAnswer = formToAnswer(form.getAnswers().get(i),new Answer());
            System.out.println("!!!!!!!!!!!!!!!!Nově přidaná otázka je!!!!!!!!!!!!!!!!!!!!");
            System.out.println(" "+formToAnswer);
            System.out.println("odpověď "+formToAnswer.getAnswer()+" a id je "+formToAnswer.getId());
            answerMan.add(formToAnswer, false);
            answers.add(formToAnswer);
        }
    }
    return answers;
}
    private Question formToQuestion(EditQuestionForm form, Question q) {
        if (form.getId() != null) {
            q.setId(form.getId());
        }

        q.setPoints(form.getPoints());
        q.setQuestion(form.getQuestion());
        q.setVisible(Boolean.TRUE);
        // q.setAnswers(null);
        return q;
    }
//    private Set<Question> editQuestionList (EditTestForm form, Test test)
//    {
//        Set<Question> questions= new HashSet<Question>();
//        List<Long> questionsForm = new ArrayList<Long>();
//        List<Long> questionsDB = new ArrayList<Long>();
//        
//        for(EditQuestionForm question:form.getQuestions())
//        {
//            
//          questionsForm.add(  question.getId());
//        }
//        for(Iterator<Question> i=test.getQuestions().iterator();i.hasNext();)
//      {
//            Question next = i.next();
//           questionsDB.add(next.getId());
//          
//      
//      }
//        
//        questionsDB.removeAll(questionsForm);
//      for(int i =0; i<questionsDB.size();i++)
//      {
//           
//          System.out.println("otázka je "+questionsDB.get(i));
//            Question findById = questionMan.findById(questionsDB.get(i));
//            List<Answer> findByQuestion = answerMan.findByQuestion(findById);
//            for (int j = 0; j <findByQuestion.size(); j++) {
//              answerMan.visible(findByQuestion.get(j).getId(), true);
//          }
//          questionMan.visible(findById.getId(), true);
//      
//      }
//        System.out.println("Jdu mazat odpovědi");
//        
//        for(int i =0; i<form.getQuestions().size();i++)
//      {
//           
//          System.out.println("odpovědi k otázce "+form.getQuestions().get(i));
//            Question findById = questionMan.findById(form.getQuestions().get(i).getId());
//          if(findById!=null){
//              Set<Answer> editAnswerList = editAnswerList(form.getQuestions().get(i),findById);
//              findById.setAnswers(editAnswerList);
//              questionMan.edit(findById, true);
//                  
//          }
//      
//      }
//        
//        
//        return questions;
//    }

    private Answer formToAnswer(EditAnswerForm form, Answer answer) {
        if (form.getId() != null) {
            answer.setId(form.getId());}
            answer.setCorrect(form.getCorrect());
            answer.setAnswer(form.getAnswer());
            answer.setVisible(Boolean.TRUE);

        
        return answer;
    }
//    private Set<Answer> editAnswerList (EditQuestionForm form, Question question)
//    {
//         Set<Answer> answers= new HashSet<Answer>();
//         List<Long> answerForm = new ArrayList<Long>();
//        List<Long> answerDB = new ArrayList<Long>();
//       
//        for(EditAnswerForm a :form.getAnswers())
//        {
//            answerForm.add(a.getId());
//        }
//         for(Iterator<Answer> i=question.getAnswers().iterator();i.hasNext();)
//      {
//            Answer next = i.next();
//           answerDB.add(next.getId());
//      
//      }
//         answerDB.removeAll(answerForm);
//         for(int i =0; i<answerDB.size();i++)
//      {
//           
//          System.out.println("otázka je "+answerDB.get(i));
//          answerMan.visible(answerDB.get(i), true);
//      
//      }
//        
//        for (int i = 0; i < form.getAnswers().size(); i++) {
//             Answer formToAnswer = formToAnswer(form.getAnswers().get(i),new Answer());
//             if(formToAnswer.getId()!=null)
//             {
//                 Answer findById = answerMan.findById(formToAnswer.getId());
//                 Answer formToAnswer1 = formToAnswer(form.getAnswers().get(i),findById);
//                 answerMan.edit(formToAnswer1, false);
//             }
//             else{
//                 System.out.println("!!!!!!!!!!!!!!!!!!!!PŘIDÁVÁM ODPOVĚĎ!!!!!!!!!!!!!!!!");
//                 answerMan.add(formToAnswer, true);
//             }
//             answers.add(formToAnswer);
//            
//        }
//         return answers;
//         
//    }
//    
//    private void saveQuestion(Test t, List<QuestionForm> q)
//    {
//        Set<Question> questions = new HashSet<Question>();
//        for (Iterator<QuestionForm> i=q.iterator(); i.hasNext();) {
//            QuestionForm questionForm = i.next();
//            Question question = new Question();
//            question.setPoints(questionForm.getPoints());
//            question.setVisible(Boolean.TRUE);
//            question.setQuestion(questionForm.getQuestion());
//            questionMan.add(question,false);
//            questions.add(question);
//            saveAnswer(question, questionForm.getAnswers());
//            
//        }
//        t.setQuestions(questions);
//        testMan.edit(t, false);
//        
//    }
//    private void saveAnswer (Question q, List<AnswerForm> a)
//    {
//        Set<Answer> answers = new HashSet<Answer>();
//        for(Iterator<AnswerForm> i =a.iterator(); i.hasNext();)
//        {
//            AnswerForm answerForm = i.next();
//            Answer answer = new Answer();
//            answer.setAnswer(answerForm.getAnswer());
//            answer.setCorrect(answerForm.getCorrect());
//            answer.setVisible(Boolean.TRUE);
//            answerMan.add(answer, true);
//            answers.add(answer);
//        }
//        q.setAnswers(answers);
//        questionMan.edit(q, true);
//    }  
//    
}
