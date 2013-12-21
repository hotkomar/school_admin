/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller;

import cz.cvut.hotkomar.form.SendPassForm;
import cz.cvut.hotkomar.form.UserTypeForm;
import cz.cvut.hotkomar.model.entity.AbstractUser;
import cz.cvut.hotkomar.model.entity.Answer;
import cz.cvut.hotkomar.model.entity.FileEntity;
import cz.cvut.hotkomar.model.entity.Question;
import cz.cvut.hotkomar.model.entity.School;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.StudentParent;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.Test;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.model.entity.UserType;
import cz.cvut.hotkomar.service.checkAndMake.CreateParentUser;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.checkAndMake.MailPost;
import cz.cvut.hotkomar.service.manager.AnswerMan;
import cz.cvut.hotkomar.service.manager.FileMan;
import cz.cvut.hotkomar.service.manager.QuestionMan;
import cz.cvut.hotkomar.service.manager.SchoolMan;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.StudentParentMan;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.TestMan;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import cz.cvut.hotkomar.service.manager.UserTypeMan;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

/**
 *
 * @author Marie Hoťková
 */
@Controller

public class MainController {
    private UserTypeMan userTypeMan;
    private StudentMan studentMan;
    private TeacherMan teacherMan;
    private StudentParentMan parentMan;
    private SubjectMan subjectMan;
    private StudentClassMan studentClassMan;
    private FileMan fileMan;
    private SubjectOfClassMan subjectOfClassMan;
    private TestMan testMan;
    private QuestionMan questionMan;
    private AnswerMan answerMan;
    private TestResultMan testResultMan;
    private DateFunction dateFunction;
    private  SchoolMan schoolMan;
    private MailPost mailPost;
    private  CreateParentUser createParentUser;

    
    @Autowired
    public void setCreateParentUser(CreateParentUser createParentUser) {
        this.createParentUser = createParentUser;
    }

    
     @Autowired
    private ShaPasswordEncoder passwordEncoder;
    
    @Autowired
    public void setParentMan(StudentParentMan parentMan) {
        this.parentMan = parentMan;
    }
    @Autowired
    public void setSubjectOfClassMan(SubjectOfClassMan subjectOfClassMan) {
        this.subjectOfClassMan = subjectOfClassMan;
    }
    
    @Autowired
    public void setUserTypeMan (UserTypeMan userTypeMan)
    {
        this.userTypeMan=userTypeMan;
    }
    @Autowired
    public void setStudentMan (StudentMan student)
    {
        this.studentMan = student;
    }
    @Autowired
    public void setTeacherMan (TeacherMan teacherMan)
    {
        this.teacherMan= teacherMan;
    }
    @Autowired
    public void setStudentParentMan (StudentParentMan parentMan)
    {
        this.parentMan = parentMan;
    }
    @Autowired
    public void setSubjectMan (SubjectMan subjectMan)
    {
        this.subjectMan= subjectMan;
    }
    @Autowired
    public void setStudentClassMan (StudentClassMan studentClassMan )
    {
        this.studentClassMan = studentClassMan;
    }
    
    @Autowired
    public  void setFileMan (FileMan fileMan)
    {
        this.fileMan=fileMan;
    }
@Autowired
    public void setTestResultMan(TestResultMan testResultMan) {
        this.testResultMan = testResultMan;
    }
@Autowired
    public void setTestMan(TestMan testMan) {
        this.testMan = testMan;
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
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }
@Autowired
    public void setSchoolMan(SchoolMan schoolMan) {
        this.schoolMan = schoolMan;
    }
@Autowired
    public void setMailPost(MailPost mailPost) {
        this.mailPost = mailPost;
    }
    
    
    /**
     * view index
     *
     * @param m
     * @return
     */
    @RequestMapping(value = "index.htm")
    public String index2(@RequestParam(value = "name", required = false) String name,ModelMap m, Authentication auth ) {
//createStudent();
//createStart();
//      UserType user = new UserType();
//      user.setName(name);
//      user.setVisible(Boolean.TRUE);
//      userTypeMan.add(user, true);
//List<UserType> listUseres = userTypeMan.findAll();
//        UserTypeForm prvek = new UserTypeForm();
//        prvek.setName(listUseres.get(0).getName()+" "+listUseres.size());
//m.addAttribute("prvek",prvek);
        if(auth==null){
m.addAttribute("form",new UserTypeForm());
        return "index";
        }
        else{
            return"redirect:/redirecRole.htm";
        }
    }
    @RequestMapping(value = "init.htm")
    public String init ()
    {
        createRole();
        return"redirect:index.htm";
    }
    @RequestMapping(value = "help.htm")
    public String helpPage (ModelMap m, Authentication auth,@RequestParam(value = "error",required = false) String error )
    {
       if(auth==null){
           m.addAttribute("form",new SendPassForm());
          
        return "help";
        }
        else{
            return"redirect:/redirecRole.htm";
        } 
    }
    @RequestMapping(value ="newPass.htm" ,method = RequestMethod.POST)
    public String sendMail(@Valid@ModelAttribute("form") SendPassForm form, BindingResult errors ,ModelMap m)
    {
        if(errors.hasErrors())
        {
        return"redirect:help.htm";
        }
        else{
            boolean error=false;
            Student student = studentMan.findByLogin(form.getLogin());
            Teacher teacher;
            StudentParent studentParent;
            if(student==null){
             teacher = teacherMan.findByLogin(form.getLogin());
            if(teacher==null){
            studentParent  = parentMan.findByLogin(form.getLogin());
            error = sendMailFunction(studentParent,form.getLogin());
            
            }
            else{
                error = sendMailFunction(teacher,teacher.getLogin());
            }
            }
            else{
                 error = sendMailFunction(student,form.getMail());
                
            }
            if(error){
            return "redirect:help.htm?error="+error;
            }else{
                return "redirect:index.htm?pass=true";
            }
        }
    }
    private boolean sendMailFunction(Student student,String mail)
    {
        if(student.getMail()==null || student.getMail().isEmpty() || !student.getMail().equals(mail))
        {
            return false;
        }
        String generateMailAndPass = generateMailAndPass(student.getMail(), student.getLogin());
        String encodePassword = passwordEncoder.encodePassword(generateMailAndPass,student.getLogin());
        student.setPassword(encodePassword);
        studentMan.edit(student, true);
        return true;
    }
    private boolean sendMailFunction(Teacher teacher,String mail)
    {
        if(teacher.getMail()==null || teacher.getMail().isEmpty() || !teacher.getMail().equals(mail))
        {
            return false;
        }
        String generateMailAndPass = generateMailAndPass(teacher.getMail(), teacher.getLogin());
        String encodePassword = passwordEncoder.encodePassword(generateMailAndPass,teacher.getLogin());
        teacher.setPassword(encodePassword);
        teacherMan.edit(teacher, true);
        return true;
    }
    private boolean sendMailFunction(StudentParent studentParent, String mail)
    {
        if(studentParent.getMail()==null || studentParent.getMail().isEmpty()|| !studentParent.getMail().equals(mail))
        {
            return false;
        }
        String generateMailAndPass = generateMailAndPass(studentParent.getMail(), studentParent.getLogin());
        String encodePassword = passwordEncoder.encodePassword(generateMailAndPass,studentParent.getLogin());
        studentParent.setPassword(encodePassword);
        parentMan.edit(studentParent, true);
        return true;
    }
    
    private String generateMailAndPass(String userMail,String login)
    {
        String pass="";
        try{
             pass = createParentUser.getPassword();
         SimpleMailMessage mail = new SimpleMailMessage();
         System.out.println("matky mail"+userMail);
         mail.setTo(userMail);
         mail.setFrom("kolejniklub@gmail.com");
         mail.setSubject("SchoolAdmin: přihlašovací údaje");
         mail.setText("Vaše přihlašovací jméno je "+login+" a heslo je "+pass+".");
         mailPost.send(mail);
        }
        catch(MailException e)
        {
            e.printStackTrace();
        }
        
        
        return pass;
    }
    @RequestMapping(value = "maru.htm")
    public String maru ()
    {
        createStudent();
        return"redirect:index.htm";
    }
    @RequestMapping (value = "downloadFile.htm")
    public String downloadGet (@RequestParam(value ="id", required = true)Long id, HttpServletResponse response) throws Exception
    {
        FileEntity file = fileMan.findById(id);
        if(file!=null)
        {
            response.setHeader("Content-Disposition", "inline;filename=\"" + file.getName() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(file.getContentType());
            Blob blob = new SerialBlob(file.getStream());
            IOUtils.copy(blob.getBinaryStream(), out);
            out.flush();
            out.close();
            
        }
        
        return null;
    }
    private void createRole()
    {
        UserType ut = new UserType();
        UserType ut2 = new UserType();
        UserType ut3 = new UserType();
        UserType ut4 = new UserType();

        ut.setName("ROLE_STUDENT");
        ut.setVisible(Boolean.TRUE);
        ut2.setName("ROLE_TEACHER");
        ut2.setVisible(Boolean.TRUE);
        ut3.setName("ROLE_ADMIN");
        ut3.setVisible(Boolean.TRUE);
        ut4.setName("ROLE_PARENT");
        ut4.setVisible(Boolean.TRUE);
         userTypeMan.add(ut, true);
        userTypeMan.add(ut2, false);
        userTypeMan.add(ut3, false);
        userTypeMan.add(ut4, false);
        System.out.println("***********Vytvořil jsem role************************");
        Set<UserType> admin = new HashSet<UserType>();
        admin.add(ut2);
        admin.add(ut3);
       Teacher teacher = new Teacher();
        teacher.setIdType(admin);
        
         teacher.setLogin("admin");
         teacher.setName("name");
         teacher.setEducation_consultant(Boolean.FALSE);
       
        String pass4 = "Pomeranc25";
        String hash4 = passwordEncoder.encodePassword(pass4,teacher.getLogin());
        teacher.setPassword(hash4);
        //teacher.setPassword(pass4);
        teacher.setSurname("surname");
        teacher.setVisible(Boolean.TRUE);
        teacherMan.add(teacher, false);
        
        StudentClass clazz = new StudentClass();
        clazz.setName("A");
        clazz.setNameNumber((byte) 1);
       
        clazz.setId_teacher(teacher);
        clazz.setNumberOfYears((byte) 4);
        clazz.setVisible(Boolean.TRUE);
        
        clazz.setYearOfFoundation(dateFunction.setDate(31,8, 2017));
       studentClassMan.add(clazz, false);
        teacher.setId_class(clazz);
       teacherMan.edit(teacher, false);
        System.out.println("přidala jsem třídu");
        
         Student student = new Student();
         student.setLogin("student1");
        student.setName("Václav");
        student.setSurname("Švec");
        student.setPlaceOfBorn("Karlovy Vary");
       
        student.setIdentificationNumber("983746/3024");
        student.setMail("kupka@schoolAdmin.cz");
        student.setMobilePhone("762 829 972");
        hash4=    passwordEncoder.encodePassword("Pomeranc25", student.getLogin());
        student.setPassword(hash4);
        student.setPhoneNumber("234 234 234");
        student.setFax("234 293 234");
        student.setMotherName("Anna");
        student.setMotherSurname("Kupková");
        student.setMotherPhone("763 834 834");
        student.setFatherName("Jan");
        student.setFatherSurname("Kupka");
        student.setFatherPhone("234 234 234");
        student.setVisible(Boolean.TRUE);
        student.setId_class(clazz);
        studentMan.add(student, false);
         Student student2 = new Student();
         student2.setLogin("student2");
        student2.setName("Lenka");
        student2.setSurname("Halasová");
        student2.setPlaceOfBorn("Karlovy Vary");
        student2.setVisible(Boolean.TRUE);
        student2.setIdentificationNumber("983746/3024");
        student2.setMail("kupka@schoolAdmin.cz");
        student2.setMobilePhone("762 829 972");
        hash4=    passwordEncoder.encodePassword("Pomeranc25", student2.getLogin());
        student2.setPassword(hash4);
        student2.setPhoneNumber("234 234 234");
        student2.setFax("234 293 234");
        student2.setMotherName("Anna");
        student2.setMotherSurname("Kupková");
        student2.setMotherPhone("763 834 834");
        student2.setFatherName("Jan");
        student2.setFatherSurname("Kupka");
        student2.setFatherPhone("234 234 234");
            
        student2.setId_class(clazz);
        studentMan.add(student2, false);
        
        Subject subject = new Subject();
        subject.setName("Matematika");
        subject.setVisible(Boolean.TRUE);
        subject.setValidFrom(Calendar.getInstance());
        subjectMan.add(subject, false);
        
        SubjectOfClass subjectOfClass = new SubjectOfClass();
        subjectOfClass.setVisible(Boolean.TRUE);
        subjectOfClass.setId_class(clazz);
        subjectOfClass.setId_teacher(teacher);
        subjectOfClass.setId_subject(subject);
        subjectOfClassMan.add(subjectOfClass, false);
        
       Test test = new Test();
        test.setName("matematika 1");
        //String testhash = passwordEncoder.encodePassword("Pomeranc25", "testMan*$");
        //test.setPassword(testhash);
        test.setVisible(Boolean.TRUE);
        test.setId_subject(subject);
        test.setId_teacher(teacher);
        test.setTaught(Boolean.TRUE);
        test.setFive((short) 30);
        test.setFour((short) 50);
        test.setThree((short) 60);
        test.setTwo((short) 70);
        test.setPassword("qwe");
        test.setTaught(Boolean.FALSE);
//
        

        Question question = new Question();
        question.setPoints(10);
        question.setQuestion("jak se máš?");
        question.setVisible(Boolean.TRUE);
//
        Question question1 = new Question();
        question1.setPoints(10);
        question1.setQuestion("1+1=?");
        question1.setVisible(Boolean.TRUE);
//
        Set<Question> questions = new HashSet<Question>();
        questions.add(question);
        questions.add(question1);
//
//
        Answer answer = new Answer();
        answer.setAnswer("správná");
        answer.setCorrect(Boolean.TRUE);
        answer.setVisible(Boolean.TRUE);
        answerMan.add(answer, false);
//
        Answer answer1 = new Answer();
        answer1.setAnswer("101");
        answer1.setCorrect(Boolean.FALSE);
        answer1.setVisible(Boolean.TRUE);
        answerMan.add(answer1, false);

        Answer answer2 = new Answer();
        answer2.setAnswer("100");
        answer2.setCorrect(Boolean.FALSE);
        answer2.setVisible(Boolean.TRUE);
        answerMan.add(answer2, false);

        Set<Answer> answers = new HashSet<Answer>();
        answers.add(answer);
        answers.add(answer1);
        answers.add(answer2);
//
        Answer answer3 = new Answer();
        answer3.setAnswer("spravna");
        answer3.setCorrect(Boolean.TRUE);
        answer3.setVisible(Boolean.TRUE);
        answerMan.add(answer3, false);

        Answer answer11 = new Answer();
        answer11.setAnswer("b");
        answer11.setCorrect(Boolean.FALSE);
        answer11.setVisible(Boolean.TRUE);
        answerMan.add(answer11, false);

        Answer answer21 = new Answer();
        answer21.setAnswer("bb");
        answer21.setCorrect(Boolean.FALSE);
        answer21.setVisible(Boolean.TRUE);
        answerMan.add(answer21, false);

        Set<Answer> answers2 = new HashSet<Answer>();
        answers2.add(answer3);
        answers2.add(answer11);
        answers2.add(answer21);
//
        question.setAnswers(answers);
        question1.setAnswers(answers2);
        questionMan.add(question, false);
        questionMan.add(question1, false);
        test.setQuestions(questions);
       //  test2.setQuestions(questions);

        testMan.add(test, false); 
             
          TestResult result = new TestResult();
        result.setTestDate(dateFunction.setDate(1, 1, 2013));
        
        result.setStudent(student);
        result.setTest(test);
        result.setPercent(80.0);
        result.setMark((short) 2);
        result.setVisible(Boolean.TRUE);
        result.setActualMark(Boolean.TRUE);
        result.setWebTest(Boolean.TRUE);
        result.setClassified(Short.valueOf("0"));
        testResultMan.add(result, true);
          TestResult result2 = new TestResult();
        result2.setTestDate(dateFunction.setDate(1, 1, 2013));
        
        result2.setStudent(student2);
        result2.setTest(test);
        result2.setPercent(0.0);
        result2.setMark((short) 5);
        result2.setVisible(Boolean.TRUE);
        result2.setActualMark(Boolean.TRUE);
        result2.setWebTest(Boolean.TRUE);
        result2.setClassified(Short.valueOf("0"));
        testResultMan.add(result2, true);
    }
   public void createStart()
{
    System.out.println("VYTVÁŘÍM ROLE");
        UserType ut = new UserType();
        UserType ut2 = new UserType();
        UserType ut3 = new UserType();
        UserType ut4 = new UserType();

        ut.setName("ROLE_STUDENT");
        ut.setVisible(Boolean.TRUE);
        ut2.setName("ROLE_TEACHER");
        ut2.setVisible(Boolean.TRUE);
        ut3.setName("ROLE_ADMIN");
        ut3.setVisible(Boolean.TRUE);
        ut4.setName("ROLE_PARENT");
        ut4.setVisible(Boolean.TRUE);
         userTypeMan.add(ut, true);
        userTypeMan.add(ut2, false);
        userTypeMan.add(ut3, false);
        userTypeMan.add(ut4, false);
        System.out.println("***********Vytvořil jsem role************************");
        Set<UserType> role3 = new HashSet<UserType>();
        role3.add(ut2);
        role3.add(ut3);
Teacher teacher = new Teacher();
teacher.setIdType(role3);
        
         teacher.setLogin("admin");
         teacher.setName("name");
         teacher.setEducation_consultant(Boolean.FALSE);
       
        String pass4 = "Pomeranc25";
        String hash4 = passwordEncoder.encodePassword(pass4,teacher.getLogin());
        teacher.setPassword(hash4);
       // teacher.setPassword(pass4);
        teacher.setSurname("surname");
        teacher.setVisible(Boolean.TRUE);
        teacherMan.add(teacher, false);
}
    public void createStudent() {
        Calendar c = Calendar.getInstance();
        
        // DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println("VYTVÁŘÍM ROLE");
        UserType ut = new UserType();
        UserType ut2 = new UserType();
        UserType ut3 = new UserType();
        UserType ut4 = new UserType();

        ut.setName("ROLE_STUDENT");
        ut.setVisible(Boolean.TRUE);
        ut2.setName("ROLE_TEACHER");
        ut2.setVisible(Boolean.TRUE);
        ut3.setName("ROLE_ADMIN");
        ut3.setVisible(Boolean.TRUE);
        ut4.setName("ROLE_PARENT");
        ut4.setVisible(Boolean.TRUE);

        userTypeMan.add(ut, true);
        userTypeMan.add(ut2, false);
        userTypeMan.add(ut3, false);
        userTypeMan.add(ut4, false);

        

        Set<UserType> role_student = new HashSet<UserType>();
        role_student.add(ut);
        Set<UserType> role_teacher = new HashSet<UserType>();
        role_teacher.add(ut2);
        Set<UserType> role_admin = new HashSet<UserType>();
        role_admin.add(ut2);
        role_admin.add(ut3);
        Set<UserType> role_paren = new HashSet<UserType>();
        role_paren.add(ut4);
        System.out.println("VYTVÁŘÍM UČITELE");
        
       
            
        
        Teacher teacher = new Teacher();
        teacher.setDateOfBorn(c);
        teacher.setDate_of_employ(c);
        teacher.setDegree("Ing.");
        teacher.setEducation("VS");
        teacher.setEducation_consultant(Boolean.TRUE);
        teacher.setFax("782 982 982");
        teacher.setIdType(role_admin);
        String pass4 = "Pomeranc25";
        teacher.setLogin("teacher1");
        String hash4 = passwordEncoder.encodePassword(pass4,teacher.getLogin());
        teacher.setPassword(hash4);
        teacher.setIdentificationNumber("905105/1111");
        
        teacher.setMail("teacher@schoolAdmin.cz");
        teacher.setMobilePhone("762 872 982");
        teacher.setName("Karel");
        teacher.setPlaceOfBorn("");
//        String pass4 = "Pomeranc25";
//        teacher.setPassword(pass4);
//        String hash4 = passwordEncoder.encodePassword(pass4,"teacher1");
//        teacher.setPassword(hash4);
        teacher.setPhoneNumber("872 827 972");
        teacher.setSurname("Ryba");
        teacher.setVisible(Boolean.TRUE);
        teacher.setWagePerHour((short) 50);
        teacher.setIdType(role_teacher);
      teacherMan.add(teacher, false);
        
        Teacher teacher1 = new Teacher();
        teacher1.setDateOfBorn(c);
        teacher1.setDate_of_employ(c);
        teacher1.setDegree("Mgr.");
        teacher1.setEducation("");
        teacher1.setEducation_consultant(Boolean.TRUE);
        teacher1.setFax("782 982 982");
        teacher1.setIdType(role_admin);
      
        teacher1.setIdentificationNumber("905105/1111");
        teacher1.setLogin("teacher2");
        teacher1.setMail("teacher2@schoolAdmin.cz");
        teacher1.setMobilePhone("762 872 982");
        teacher1.setName("Adam");
        teacher1.setPlaceOfBorn("Praha");
        hash4=    passwordEncoder.encodePassword("Pomeranc25", teacher1.getLogin());
         teacher1.setPassword(hash4);
//        String hash3 = passwordEncoder.encodePassword(pass3,"teacher2");
//        teacher1.setPassword(hash3);
        teacher1.setPhoneNumber("872 827 972");
        teacher1.setSurname("Trnka");
        teacher1.setVisible(Boolean.TRUE);
        teacher1.setIdType(role_admin);
       teacherMan.add(teacher1, false);
        System.out.println("VYTVÁŘÍM TŘÍDU");
        
        StudentClass clazz1 = new StudentClass();
        clazz1.setName("AB");
        clazz1.setNameNumber((byte) 9);
       
        clazz1.setId_teacher(teacher1);
        clazz1.setNumberOfYears((byte) 8);
        clazz1.setVisible(Boolean.TRUE);
        c.set(2009, 5, 31);
        clazz1.setYearOfFoundation(c);
       studentClassMan.add(clazz1, false);
        teacher1.setId_class(clazz1);
       teacherMan.edit(teacher1, false);
        
        StudentClass clazz = new StudentClass();
        clazz.setName("A");
        clazz.setNameNumber((byte) 1);
       
        clazz.setId_teacher(teacher);
        clazz.setNumberOfYears((byte) 4);
        clazz.setVisible(Boolean.TRUE);
        c.set(2017, 5, 31);
        clazz.setYearOfFoundation(dateFunction.setDate(31,8, 2015));
       studentClassMan.add(clazz, false);
        teacher.setId_class(clazz);
       teacherMan.edit(teacher, false);
        System.out.println("přidala jsem třídu");
        
        
        System.out.println("přidala jsem třídu");
        for (int i = 0; i < 50; i++) {
                 
        Student student = new Student();
        student.setName("Petr");
        student.setSurname("Kupka");
        student.setPlaceOfBorn("Karlovy Vary");
        student.setDateOfBorn(c);
        student.setIdentificationNumber("983746/3024");
        student.setMail("kupka@schoolAdmin.cz");
        student.setMobilePhone("762 829 972");

        student.setPhoneNumber("234 234 234");
        student.setFax("234 293 234");
        student.setMotherName("Anna");
        student.setMotherSurname("Kupková");
        student.setMotherPhone("763 834 834");
        student.setFatherName("Jan");
        student.setFatherSurname("Kupka");
        student.setFatherPhone("234 234 234");
            
        student.setId_class(clazz);
       
        student.setIdType(role_student);
      //  String pass = "Pomeranc25";
        student.setLogin("student"+i);
        hash4 = passwordEncoder.encodePassword("Pomeranc25",student.getLogin());
         student.setPassword(hash4);
//        String hash = passwordEncoder.encodePassword(pass,"student");
        
//        student.setPassword(hash);
        student.setVisible(Boolean.TRUE);

        studentMan.add(student, false);
        }
        
        Student student1 = new Student();
        student1.setName("Lenka");
        student1.setSurname("Halasová");
        student1.setPlaceOfBorn("Praha");
        student1.setDateOfBorn(c);
        student1.setIdentificationNumber("952398/2222");
        student1.setMail("bergerova@aschoolAdmin.cz");
        student1.setMobilePhone("762 829 972");
 
        student1.setPhoneNumber("234 234 234");
        student1.setFax("234 293 234");
        student1.setMotherName("Zdena");
        student1.setMotherSurname("Begerová");
        student1.setMotherPhone("763 834 834");
        student1.setFatherName("Pavel");
        student1.setFatherSurname("Berger");
        student1.setFatherPhone("234 234 234");
        student1.setId_class(clazz1);
       
        student1.setIdType(role_student);
        student1.setLogin("student1");
        hash4=    passwordEncoder.encodePassword("Pomeranc25", student1.getLogin());
         student1.setPassword(hash4);
//        String pass1 = "Pomeranc25";
//        student1.setPassword(pass1);
//        String hash1 = passwordEncoder.encodePassword(pass1,"student1");
//        student1.setPassword(hash1);
        student1.setVisible(Boolean.TRUE);

        studentMan.add(student1, false);
        System.out.println("přidala jsem studenta");

        StudentParent parent = new StudentParent();
        parent.setIdType(role_paren);
        parent.setLogin("parent1");
//        String pass2 = "Pomeranc25";
//        parent.setPassword(pass2);
        hash4=    passwordEncoder.encodePassword("Pomeranc25", parent.getLogin());
        parent.setPassword(hash4);
//        String hash2 = passwordEncoder.encodePassword(pass2,"parent1");
//        parent.setPassword(hash2);
        parent.setStudent(studentMan.findById(Long.valueOf(1)));
        parent.setVisible(Boolean.TRUE);

       parentMan.add(parent, false);
        
            
        
        Subject subject = new Subject();
        subject.setName("matematika");
       
        subject.setValidFrom(c);
        subject.setVisible(Boolean.TRUE);
        Subject subject1 = new Subject();
        subject1.setName("fyzika");
       
        subject1.setValidFrom(c);
        subject1.setVisible(Boolean.TRUE);
        Subject subject2 = new Subject();
        subject2.setName("chemie");
        
        subject2.setValidFrom(c);
        subject2.setExpiration(c);
        subject2.setVisible(Boolean.TRUE);
        subjectMan.add(subject, false);
        subjectMan.add(subject1, false);
        subjectMan.add(subject2, false);
        
        SubjectOfClass subjectOfClass = new SubjectOfClass();
        SubjectOfClass subjectOfClass1 = new SubjectOfClass();
        SubjectOfClass subjectOfClass2 = new SubjectOfClass();
        SubjectOfClass subjectOfClass3 = new SubjectOfClass();

        subjectOfClass.setId_class(clazz);
        subjectOfClass.setId_teacher(teacher);
        subjectOfClass.setId_subject(subjectMan.findById(Long.parseLong("1")));        
        subjectOfClass.setVisible(Boolean.TRUE);
        
        subjectOfClass3.setId_class(clazz1);
        subjectOfClass3.setId_teacher(teacher);
        subjectOfClass3.setId_subject(subjectMan.findById(Long.parseLong("1")));        
        subjectOfClass3.setVisible(Boolean.TRUE);
        
        subjectOfClass1.setId_class(clazz);
        subjectOfClass1.setId_teacher(teacher1);
        subjectOfClass1.setId_subject(subjectMan.findById(Long.parseLong("2")));
        subjectOfClass1.setVisible(Boolean.TRUE);
        
        subjectOfClass2.setId_class(clazz);
        subjectOfClass2.setId_teacher(teacher);
        subjectOfClass2.setId_subject(subjectMan.findById(Long.parseLong("3")));
        subjectOfClass2.setVisible(Boolean.TRUE);

        subjectOfClassMan.add(subjectOfClass, false);
        subjectOfClassMan.add(subjectOfClass1, false);
        subjectOfClassMan.add(subjectOfClass2, false);
        subjectOfClassMan.add(subjectOfClass3,false);

        Test test = new Test();
        test.setName("matematika 1");
        //String testhash = passwordEncoder.encodePassword("Pomeranc25", "testMan*$");
        //test.setPassword(testhash);
        test.setVisible(Boolean.TRUE);
        test.setId_subject(subject);
        test.setId_teacher(teacher);
        test.setTaught(Boolean.TRUE);
        test.setFive((short) 30);
        test.setFour((short) 50);
        test.setThree((short) 60);
        test.setTwo((short) 70);
        test.setPassword("qwe");
        test.setTaught(Boolean.FALSE);
//
        

        Question question = new Question();
        question.setPoints(10);
        question.setQuestion("jak se máš?");
        question.setVisible(Boolean.TRUE);
//
        Question question1 = new Question();
        question1.setPoints(10);
        question1.setQuestion("1+1=?");
        question1.setVisible(Boolean.TRUE);
//
        Set<Question> questions = new HashSet<Question>();
        questions.add(question);
        questions.add(question1);
//
//
        Answer answer = new Answer();
        answer.setAnswer("správná");
        answer.setCorrect(Boolean.TRUE);
        answer.setVisible(Boolean.TRUE);
        answerMan.add(answer, false);
//
        Answer answer1 = new Answer();
        answer1.setAnswer("101");
        answer1.setCorrect(Boolean.FALSE);
        answer1.setVisible(Boolean.TRUE);
        answerMan.add(answer1, false);

        Answer answer2 = new Answer();
        answer2.setAnswer("100");
        answer2.setCorrect(Boolean.FALSE);
        answer2.setVisible(Boolean.TRUE);
        answerMan.add(answer2, false);

        Set<Answer> answers = new HashSet<Answer>();
        answers.add(answer);
        answers.add(answer1);
        answers.add(answer2);
//
        Answer answer3 = new Answer();
        answer3.setAnswer("spravna");
        answer3.setCorrect(Boolean.TRUE);
        answer3.setVisible(Boolean.TRUE);
        answerMan.add(answer3, false);

        Answer answer11 = new Answer();
        answer11.setAnswer("b");
        answer11.setCorrect(Boolean.FALSE);
        answer11.setVisible(Boolean.TRUE);
        answerMan.add(answer11, false);

        Answer answer21 = new Answer();
        answer21.setAnswer("bb");
        answer21.setCorrect(Boolean.FALSE);
        answer21.setVisible(Boolean.TRUE);
        answerMan.add(answer21, false);

        Set<Answer> answers2 = new HashSet<Answer>();
        answers2.add(answer3);
        answers2.add(answer11);
        answers2.add(answer21);
//
        question.setAnswers(answers);
        question1.setAnswers(answers2);
        questionMan.add(question, false);
        questionMan.add(question1, false);
        test.setQuestions(questions);
       //  test2.setQuestions(questions);

        testMan.add(test, false);
        for (int i = 0; i < 45; i++) {
            Test t = new Test();
            t.setName("test"+i);
            t.setId_teacher(teacher);
            t.setVisible(Boolean.TRUE);
            t.setTaught(Boolean.FALSE);
            testMan.add(t, true);
        }
//       
        
//        TestResult result = new TestResult();
//        result.setTestDate(dateFunction.setDate(1, 1, 2013));
//        Student findById = studentMan.findById(Long.valueOf("1"));
//        result.setStudent(findById);
//        result.setTest(test);
//        result.setPercent(100.0);
//        result.setMark((short) 2);
//        result.setVisible(Boolean.TRUE);
//        result.setActualMark(Boolean.TRUE);
//        result.setWebTest(Boolean.TRUE);
//        result.setClassified(Short.valueOf("0"));
//        testResultMan.add(result, true);
//        
//        
//        TestResult result2 = new TestResult();
//        result2.setTestDate(dateFunction.setDate(1,11, 2013));
//        
//        result2.setStudent(findById);
//        result2.setTest(test);
//        result2.setPercent(100.0);
//        result2.setMark(null);
//        result2.setVisible(Boolean.TRUE);
//        result2.setActualMark(Boolean.FALSE);
//        result2.setWebTest(Boolean.TRUE);
// result2.setClassified(Short.valueOf("0"));
//        
//        testResultMan.add(result2, true);
//        
//       
//        TestResult result3 = new TestResult();
//        result3.setTestDate(dateFunction.setDate(31, 1, 2013));
//        
//        result3.setStudent(student1);
//        result3.setTest(test);
//        result3.setPercent(100.0);
//        result3.setMark(null);
//        result3.setVisible(Boolean.TRUE);
//        result3.setActualMark(Boolean.TRUE);  
//         result3.setWebTest(Boolean.TRUE);
//          result3.setClassified(Short.valueOf("0"));
//        testResultMan.add(result3, true);
//        
//        
//        TestResult result4 = new TestResult();
//        c.set(2012, 3, 10);
//        result4.setTestDate(dateFunction.setDate(1, 9, 2013));
//        
//        result4.setStudent(findById);
//        result4.setTest(test);
//        result4.setPercent(100.0);
//        result4.setMark((short) 5);
//        result4.setVisible(Boolean.TRUE);
//        result4.setActualMark(Boolean.TRUE); 
//         result4.setWebTest(Boolean.TRUE);
//          result4.setClassified(Short.valueOf("0"));
//        testResultMan.add(result4, true);
//        
//        
//        TestResult result5 = new TestResult();
//        c.set(2012, 0, 5);
//        result5.setTestDate(dateFunction.setDate(1,2, 2013));
//        
//        result5.setStudent(findById);
//        result5.setTest(test);
//        result5.setPercent(100.0);
//        result5.setMark((short) 3);
//        result5.setVisible(Boolean.TRUE);
//        result5.setActualMark(Boolean.TRUE); 
//         result5.setWebTest(Boolean.TRUE);
//          result5.setClassified(Short.valueOf("0"));
//        testResultMan.add(result5, true);
//        
//        
//        TestResult result6 = new TestResult();
//        c.set(2012, 6, 5);
//        result6.setTestDate(dateFunction.setDate(1,9, 2013));
//        
//        result6.setStudent(findById);
//        result6.setTest(test);
//        result6.setPercent(100.0);
//        result6.setMark((short) 2);
//        result6.setVisible(Boolean.TRUE);
//        result6.setActualMark(Boolean.TRUE);
//        result6.setWebTest(Boolean.TRUE);
//         result6.setClassified(Short.valueOf("0"));
//        testResultMan.add(result6, true);
//        
//        Test testWeb = new Test ();
//        testWeb.setName("zadaná známka");
//        testWeb.setId_subject(test.getId_subject());
//        testWeb.setId_teacher(test.getId_teacher());
//        testWeb.setVisible(Boolean.TRUE);
//        testWeb.setTaught(Boolean.TRUE);
//     testMan.add(testWeb, false);
//        TestResult result7 = new TestResult();
//        c.set(2013, 6, 5);
//        result7.setTestDate(dateFunction.setDate(1,12, 2013));
//        
//        result7.setStudent(findById);
//        result7.setTest(testWeb);
//        result7.setPercent(100.0);
//        result7.setMark((short) 2);
//        result7.setVisible(Boolean.TRUE);
//        result7.setActualMark(Boolean.TRUE);
//        result7.setWebTest(Boolean.FALSE);
//         result7.setClassified(Short.valueOf("0"));
       // testResultMan.add(result7, true);
        School school = new School();
        school.setCity("Karlovy Vary");
        school.setPsc("36005");
        school.setStreetName("Konečná 25");
        school.setSchoolName("ZŠ Konečná 25");
        school.setVisible(Boolean.TRUE);
        school.setPhoneNumber("345345345");
        school.setMobilePhone("234234223");
        school.setFax("234567899");
        school.setMail("dkfal@seznma.cz");
        schoolMan.add(school, true);
        

    }
    
}
