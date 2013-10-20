/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller;

import cz.cvut.hotkomar.form.UserTypeForm;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.StudentParent;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.UserType;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.StudentParentMan;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.UserTypeMan;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    /**
     * view index
     *
     * @param m
     * @return
     */
    @RequestMapping(value = "index.htm")
    public String index2(@RequestParam(value = "name", required = false) String name,ModelMap m ) {
createStudent();
//createStart();
//      UserType user = new UserType();
//      user.setName(name);
//      user.setVisible(Boolean.TRUE);
//      userTypeMan.add(user, true);
List<UserType> listUseres = userTypeMan.findAll();
        UserTypeForm prvek = new UserTypeForm();
        prvek.setName(listUseres.get(0).getName()+" "+listUseres.size());
m.addAttribute("prvek",prvek);
m.addAttribute("form",new UserTypeForm());
        return "index";
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
//        String hash4 = passwordEncoder.encodePassword(pass4,"admin");
//        teacher.setPassword(hash4);
        teacher.setPassword(pass4);
        
        teacher.setSurname("surname");
        teacher.setVisible(Boolean.TRUE);
       // teacherMan.add(teacher, false);
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

        

        Set<UserType> role = new HashSet<UserType>();
        role.add(ut);
        Set<UserType> role2 = new HashSet<UserType>();
        role2.add(ut2);
        Set<UserType> role3 = new HashSet<UserType>();
        role3.add(ut2);
        role3.add(ut3);
        Set<UserType> role4 = new HashSet<UserType>();
        role4.add(ut4);
        System.out.println("VYTVÁŘÍM UČITELE");
        Teacher teacher = new Teacher();
        teacher.setDateOfBorn(c);
        teacher.setDate_of_employ(c);
        teacher.setDegree("Ing.");
        teacher.setEducation("VS");
        teacher.setEducation_consultant(Boolean.TRUE);
        teacher.setFax("782 982 982");
        teacher.setIdType(role3);
       
        teacher.setIdentificationNumber("905105/1111");
        teacher.setLogin("teacher1");
        teacher.setMail("teacher@schoolAdmin.cz");
        teacher.setMobilePhone("762 872 982");
        teacher.setName("Karel");
        teacher.setPlaceOfBorn("");
        String pass4 = "Pomeranc25";
        teacher.setPassword(pass4);
//        String hash4 = passwordEncoder.encodePassword(pass4,"teacher1");
//        teacher.setPassword(hash4);
        teacher.setPhoneNumber("872 827 972");
        teacher.setSurname("Ryba");
        teacher.setVisible(Boolean.TRUE);
        teacher.setWagePerHour((short) 50);
        teacher.setIdType(role2);
      teacherMan.add(teacher, false);
        Teacher teacher1 = new Teacher();
        teacher1.setDateOfBorn(c);
        teacher1.setDate_of_employ(c);
        teacher1.setDegree("Mgr.");
        teacher1.setEducation("");
        teacher1.setEducation_consultant(Boolean.TRUE);
        teacher1.setFax("782 982 982");
        teacher1.setIdType(role3);
      
        teacher1.setIdentificationNumber("905105/1111");
        teacher1.setLogin("teacher2");
        teacher1.setMail("teacher2@schoolAdmin.cz");
        teacher1.setMobilePhone("762 872 982");
        teacher1.setName("Adam");
        teacher1.setPlaceOfBorn("Praha");
        String pass3 = "Pomeranc25";
        teacher1.setPassword(pass3);
//        String hash3 = passwordEncoder.encodePassword(pass3,"teacher2");
//        teacher1.setPassword(hash3);
        teacher1.setPhoneNumber("872 827 972");
        teacher1.setSurname("Trnka");
        teacher1.setVisible(Boolean.TRUE);
        teacher1.setIdType(role3);
       teacherMan.add(teacher1, false);
        System.out.println("VYTVÁŘÍM TŘÍDU");
        StudentClass clazz = new StudentClass();
        clazz.setName("A");
        clazz.setNameNumber((byte) 1);
        
        clazz.setId_teacher(teacher);
        clazz.setNumberOfYears((byte) 4);
        clazz.setVisible(Boolean.TRUE);
        c.set(2017, 5, 31);
        clazz.setYearOfFoundation(c);
       studentClassMan.add(clazz, false);
        teacher.setId_class(clazz);
       teacherMan.edit(teacher, false);
        System.out.println("přidala jsem třídu");

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
       
        student.setIdType(role);
        String pass = "Pomeranc25";
        student.setPassword(pass);
//        String hash = passwordEncoder.encodePassword(pass,"student");
        student.setLogin("student");
//        student.setPassword(hash);
        student.setVisible(Boolean.TRUE);

        studentMan.add(student, false);
        Student student1 = new Student();
        student1.setName("Zdena");
        student1.setSurname("Bergerová");
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
        student1.setId_class(clazz);
       
        student1.setIdType(role);
        student1.setLogin("student1");
        String pass1 = "Pomeranc25";
        student1.setPassword(pass1);
//        String hash1 = passwordEncoder.encodePassword(pass1,"student1");
//        student1.setPassword(hash1);
        student1.setVisible(Boolean.TRUE);

        studentMan.add(student1, false);
        System.out.println("přidala jsem studenta");

        StudentParent parent = new StudentParent();
        parent.setIdType(role4);
        parent.setLogin("parent1");
        String pass2 = "Pomeranc25";
        parent.setPassword(pass2);
//        String hash2 = passwordEncoder.encodePassword(pass2,"parent1");
//        parent.setPassword(hash2);
        parent.setStudent(student);
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

        subjectOfClass.setId_class(clazz);
        subjectOfClass.setId_teacher(teacher);
        subjectOfClass.setId_subject(subject);
        
        subjectOfClass.setVisible(Boolean.TRUE);
        subjectOfClass1.setId_class(clazz);
        subjectOfClass1.setId_teacher(teacher1);
        subjectOfClass1.setId_subject(subject1);
        subjectOfClass1.setVisible(Boolean.TRUE);
        subjectOfClass2.setId_class(clazz);
        subjectOfClass2.setId_teacher(teacher);
        subjectOfClass2.setId_subject(subject2);
        subjectOfClass2.setVisible(Boolean.TRUE);

//        subjectOfClassMan.add(subjectOfClass, false);
//        subjectOfClassMan.add(subjectOfClass1, false);
//        subjectOfClassMan.add(subjectOfClass2, false);

//        Test test = new Test();
//        test.setName("matematika 1");
//        String testhash = passwordEncoder.encodePassword("Pomeranc25", "test*$");
//        test.setPassword(testhash);
//        test.setVisible(Boolean.TRUE);
//        test.setId_subject(subject);
//        test.setId_teacher(teacher);
//        test.setTaught(Boolean.TRUE);
//        test.setFive((short) 30);
//        test.setFour((short) 50);
//        test.setThree((short) 60);
//        test.setTwo((short) 70);
//
//        
//
//        Question question = new Question();
//        question.setPoints(10);
//        question.setQuestion("jak se máš?");
//        question.setVisible(Boolean.TRUE);
//
//        Question question1 = new Question();
//        question1.setPoints(10);
//        question1.setQuestion("1+1=?");
//        question1.setVisible(Boolean.TRUE);
//
//        Set<Question> questions = new HashSet<Question>();
//        questions.add(question);
//        questions.add(question1);
//
//
//        Answer answer = new Answer();
//        answer.setAnswer("správná");
//        answer.setCorrect(Boolean.TRUE);
//        answer.setVisible(Boolean.TRUE);
//        answerMan.add(answer, false);
//
//        Answer answer1 = new Answer();
//        answer1.setAnswer("101");
//        answer1.setCorrect(Boolean.FALSE);
//        answer1.setVisible(Boolean.TRUE);
//        answerMan.add(answer1, false);
//
//        Answer answer2 = new Answer();
//        answer2.setAnswer("100");
//        answer2.setCorrect(Boolean.FALSE);
//        answer2.setVisible(Boolean.TRUE);
//        answerMan.add(answer2, false);
//
//        Set<Answer> answers = new HashSet<Answer>();
//        answers.add(answer);
//        answers.add(answer1);
//        answers.add(answer2);
//
//        Answer answer3 = new Answer();
//        answer3.setAnswer("spravna");
//        answer3.setCorrect(Boolean.TRUE);
//        answer3.setVisible(Boolean.TRUE);
//        answerMan.add(answer3, false);
//
//        Answer answer11 = new Answer();
//        answer11.setAnswer("b");
//        answer11.setCorrect(Boolean.FALSE);
//        answer11.setVisible(Boolean.TRUE);
//        answerMan.add(answer11, false);
//
//        Answer answer21 = new Answer();
//        answer21.setAnswer("bb");
//        answer21.setCorrect(Boolean.FALSE);
//        answer21.setVisible(Boolean.TRUE);
//        answerMan.add(answer21, false);
//
//        Set<Answer> answers2 = new HashSet<Answer>();
//        answers2.add(answer3);
//        answers2.add(answer11);
//        answers2.add(answer21);
//
//        question.setAnswers(answers);
//        question1.setAnswers(answers2);
//        questionMan.add(question, false);
//        questionMan.add(question1, false);
//        test.setQuestions(questions);
//        // test2.setQuestions(questions);
//
//        testMan.add(test, false);
//        
//        TestResult result = new TestResult();
//        result.setTestDate(c);
//        result.setStudent(student);
//        result.setTest(test);
//        result.setPercent(100.0);
//        result.setMark((short) 2);
//        result.setVisible(Boolean.TRUE);
//        
//
//        
//        testResultMan.add(result, true);


    }
    
}
