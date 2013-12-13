/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.admin;

import cz.cvut.hotkomar.controller.AdminControllerImp;
import cz.cvut.hotkomar.form.Form;
import cz.cvut.hotkomar.form.admin.AddSubjectToClassForm;
import cz.cvut.hotkomar.form.admin.MoveStudentForm;
import cz.cvut.hotkomar.form.admin.NewClassForm;
import cz.cvut.hotkomar.form.admin.NewStudentForm;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import cz.cvut.hotkomar.service.pagination.Pagination;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Marie Hotkova
 */
@Controller
public class ClassCon implements AdminControllerImp {
//    @RequestMapping (value="/admin/classes.htm")
//    public String teachersGet (ModelMap m)
//    {
//        m.addAttribute("clazz",true);
//        return "/admin/clazz/viewClass";
//    }
    private StudentClassMan studentClassMan;
    private Pagination pagination;
    private StudentMan studentMan;
    private TeacherMan teacherMan;
    private SubjectOfClassMan subjectOfClassMan;
    private SubjectMan subjectMan;
    private FormMessage message;
    private DateFunction dateFunction;

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
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }

    @Autowired
    public void setSubjectMan(SubjectMan subjectMan) {
        this.subjectMan = subjectMan;
    }

    @Autowired
    public void setSubjectOfClassMan(SubjectOfClassMan subjectOfClassMan) {
        this.subjectOfClassMan = subjectOfClassMan;
    }

    @Autowired
    public void setTeacherMan(TeacherMan teacherMan) {
        this.teacherMan = teacherMan;
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
    public void setStudentMan(StudentMan studentMan) {
        this.studentMan = studentMan;
    }

    @Override
    @RequestMapping(value = "/admin/classes.htm")
    public String view(@RequestParam(defaultValue = "1", value = "page", required = false) Integer page, ModelMap m, HttpSession session) {
        List<StudentClass> list = studentClassMan.findAll();
        List<Teacher> teacherWithoutClass = teacherMan.teacherWithoutClass();
        m.addAttribute("teacherWithoutClass", teacherWithoutClass);
        m.addAttribute("listOfClass", pagination.paginationList(list, page));
        m.addAttribute("pageForm", pagination.getPageForm());
        m.addAttribute("clazz", true);
        return "/admin/clazz/viewClass";
    }

    @Override
    @RequestMapping(value = "/admin/addClass.htm")
    public String addGET(ModelMap m) {

        m.addAttribute("teacherWithoutClass", getTeacherWithoutClass());
        m.addAttribute("clazz", true);
        m.addAttribute("form", new NewClassForm());
        return "admin/clazz/addClass";
    }

    @RequestMapping(value = "/admin/adminNewClass.htm")
    public String addPOST(@Valid @ModelAttribute("form") NewClassForm form, BindingResult errors, ModelMap m) {
        if (errors.hasErrors()) {

//            boolean b = false;
//            if (!getTeacherWithoutClass().isEmpty()) {
//                b = true;
//            }
            m.addAttribute("teacherWithoutClass", getTeacherWithoutClass());
            m.addAttribute("clazz", true);
            m.addAttribute("form", form);

            return "admin/clazz/addClass";
        }

        StudentClass clazz = new StudentClass();
        StudentClass formToClass = formToClass(form, clazz);
        if (formToClass == null) {
            //chyba
            return "admin/errorHups";
        }
        studentClassMan.add(formToClass, true);
        //  m.addAttribute("clazz",true);
        return "redirect:classes.htm";
    }

    @Override
    @RequestMapping(value = "admin/infoClass.htm")
    public String info(@RequestParam(value = "id", required = true) Long id, ModelMap m) {
        StudentClass studentClass = studentClassMan.findById(id);
        if (studentClass == null) {
            //chyba
            return "admin/errorHups";
        }
        List<Student> students = studentMan.findByClass_id(studentClass);
        //System.out.println("délka listu je "+students.size());
        MoveStudentForm moveStudentForm = new MoveStudentForm();
        moveStudentForm.setIdClazz(id);
        m.addAttribute("classes", studentClass);
        m.addAttribute("clazz", true);
        m.addAttribute("studentC", true);
        m.addAttribute("form", moveStudentForm);
        m.addAttribute("listclazz", getAllClasses());
        m.addAttribute("students", students);

        return "admin/clazz/infoClassS";
    }

    @RequestMapping(value = "admin/infoClassS.htm")
    public String infoSubject(@RequestParam(value = "id", required = true) Long id, ModelMap m) {
        StudentClass studentClass = studentClassMan.findById(id);
        if (studentClass == null) {
            //chyba
            return "admin/errorHups";
        }
        List<SubjectOfClass> subject = subjectOfClassMan.findByIdClass(studentClass.getId());
        // System.out.println("délka listu je "+subject.size());

        m.addAttribute("classes", studentClass);
        m.addAttribute("clazz", true);
        m.addAttribute("subjectC", true);
        m.addAttribute("subjects", subject);
        return "admin/clazz/infoClassSu";
    }

    @Override
    @RequestMapping(value = "admin/editClass.htm")
    public String editGET(@RequestParam(value = "id", required = true) Long id, ModelMap m) {
        StudentClass findById = studentClassMan.findById(id);
        if (findById == null) {
            //chyba
             return "admin/errorHups";
        }
        Map<Long, String> teacherWithoutClass = getTeacherWithoutClass();
        teacherWithoutClass.put(findById.getId_teacher().getId(), findById.getId_teacher().getName() + " " + findById.getId_teacher().getSurname());
        NewClassForm classToForm = classToForm(new NewClassForm(), findById);
        m.addAttribute("form", classToForm);
        m.addAttribute("clazz", true);
        m.addAttribute("listClassHead", teacherWithoutClass);
        return "admin/clazz/editClass";
    }
    @RequestMapping(value = "/admin/adminEditClass.htm")
    public String editPOST(@Valid @ModelAttribute("form") NewClassForm form, BindingResult errors, ModelMap m)
    {
        StudentClass findById = studentClassMan.findById(form.getId());
        if(findById==null)
        {   
            System.out.println("nenašel jsem třídu");
            return"/admin/errorHups";
        }
        if(errors.hasErrors())
    {
        Map<Long, String> teacherWithoutClass = getTeacherWithoutClass();
        teacherWithoutClass.put(findById.getId_teacher().getId(), findById.getId_teacher().getName() + " " + findById.getId_teacher().getSurname());
        m.addAttribute("listClassHead", teacherWithoutClass);
            m.addAttribute("clazz", true);
            m.addAttribute("form", form);
            return "admin/clazz/editClass";
    }
        
       
        
        StudentClass formToClass = formToClass(form, findById);
        if(formToClass==null)
        {
            System.out.println("nevrátil jsem formulář");
            return"/admin/errorHups";
        }
        studentClassMan.edit(formToClass, true);
        return"redirect:classes.htm";
    }
    @Override
    @RequestMapping(value = "/admin/removeClass.htm")
    public String remove(@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "page", required = true) Integer page) {
        StudentClass class1 = studentClassMan.findById(id);
        if (class1 == null) {
            return "admin/errorHups";
        }

        if (studentMan.findByClass_id(class1).isEmpty() && subjectOfClassMan.findByIdClass(class1.getId()).isEmpty()) {
        
           
            class1.setId_teacher(null);
            studentClassMan.edit(class1, false);
            studentClassMan.visible(id, true);
        } else {
            message.setNegativeMes("Třída nesmí mít přiřazené žáky nebo vyučované předměty.");
            return "redirect:infoClass.htm?id=" + id;
        }
        return "redirect:classes.htm?page=" + page;
    }

    @RequestMapping(value = "/admin/moveStudent.htm")
    public String moveStudentPOST(@ModelAttribute("form") MoveStudentForm form, BindingResult errors, ModelMap m) {
        Student findById = studentMan.findById(form.getIdStudent());
        StudentClass findById1 = studentClassMan.findById(form.getClazz());
        if (findById == null || findById1 == null) {
             return "admin/errorHups";
        }
        findById.setId_class(findById1);
        studentMan.edit(findById, true);
        return "redirect:infoClass.htm?id=" + form.getIdClazz();
    }

    @RequestMapping(value = "admin/removeStudentC.htm")
    public String removeC(@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "class", required = true) Long clazz) {
        Student student = studentMan.findById(id);
        if (student != null) {
            student.setId_class(null);
            studentMan.edit(student, true);
            studentMan.visible(id, true);
            System.out.println("smazat studenta");
        } else {
             return "admin/errorHups";
           
        }
        return "redirect:infoClass.htm?id=" + clazz;
    }

    @RequestMapping(value = "admin/removeSubjectC.htm")
    public String removeCS(@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "class", required = true) Long clazz) {
        SubjectOfClass subjectOfClass = subjectOfClassMan.findById(id);
        if (subjectOfClass != null) {
//            subjectOfClass.setId_class(null);
//            subjectOfClassMan.edit(subjectOfClass, true);
//            subjectOfClassMan.visible(id, true);
           // subjectOfClassMan.delete(subjectOfClass);
            subjectOfClassMan.visible(id, true);
            System.out.println("smazat studenta");
        } else {
             return "admin/errorHups";
        }
        return "redirect:infoClassS.htm?id=" + clazz;
    }

    @RequestMapping(value = "/admin/addSubjectToClass.htm")
    public String addSubjectGET(@RequestParam(value = "id", required = true) Long id, ModelMap m) {
        AddSubjectToClassForm addSubjectToClassForm = new AddSubjectToClassForm();
        addSubjectToClassForm.setIdClass(id);
        m.addAttribute("clazz", true);
        m.addAttribute("form", addSubjectToClassForm);
        m.addAttribute("teachers", getAllTeachers());
        m.addAttribute("subjects", getAllSubjects());
        return "/admin/clazz/addSubject";
    }

    @RequestMapping(value = "/admin/adminAddSubjectToClass.htm")
    public String addSubjectPOST(@ModelAttribute("form") AddSubjectToClassForm form, BindingResult errors, ModelMap m) {
        StudentClass findById = studentClassMan.findById(form.getIdClass());
        Subject findById1 = subjectMan.findById(form.getIdSubject());
        Teacher findById2 = teacherMan.findById(form.getIdTeacher());
        if (findById == null || findById1 == null || findById2 == null) {
            return "admin/errorHups";
        }
        SubjectOfClass subjectOfClass = new SubjectOfClass();
        List<SubjectOfClass> findByClassSubjectTeacher = subjectOfClassMan.findByClassSubjectTeacher(findById, findById1, findById2);
        if(!findByClassSubjectTeacher.isEmpty())
        {
            m.addAttribute("clazz", true);
        m.addAttribute("form", form);
        m.addAttribute("teachers", getAllTeachers());
        m.addAttribute("subjects", getAllSubjects());
        message.setNegativeMes("Tento předmět už daný vyučující v této třídě učí.");
            return"/admin/clazz/addSubject";
        }
        List<SubjectOfClass> findByClassSubjectTeacherVisible = subjectOfClassMan.findByClassSubjectTeacherVisible(findById, findById1, findById2);
        if(findByClassSubjectTeacherVisible.isEmpty())
        { System.out.println("CST je "+findByClassSubjectTeacher.isEmpty());
        subjectOfClass.setId_class(findById);
        subjectOfClass.setId_subject(findById1);
        subjectOfClass.setId_teacher(findById2);
        subjectOfClass.setVisible(Boolean.TRUE);
       
        subjectOfClassMan.add(subjectOfClass, true);
        }
        else{
            findByClassSubjectTeacherVisible.get(0).setVisible(Boolean.TRUE);
            subjectOfClassMan.edit(findByClassSubjectTeacherVisible.get(0), true);
        }
        //subjectOfClass.setId_subject();
        return "redirect:infoClassS.htm?id=" + form.getIdClass();
    }
@RequestMapping(value = "/admin/up.htm")
public String upClass (@RequestParam (defaultValue ="1" ,value = "page", required = false) Long page, ModelMap m)
{
    studentClassMan.upYear();
    return"redirect:classes.htm?page="+page;
}
    private Map<Long, String> getTeacherWithoutClass() {
        Map<Long, String> map = new HashMap<Long, String>();
        List<Teacher> findAll = teacherMan.teacherWithoutClass();
        if(findAll!=null){
        for (Teacher sc : findAll) {
            map.put(sc.getId(), sc.getName() + " " + sc.getSurname());

        }
        }
        System.out.println("MAP TEACHER " + map.size());
        return map;
    }

    private Map<Long, String> getAllClasses() {
        Map<Long, String> map = new HashMap<Long, String>();
        List<StudentClass> findAll = studentClassMan.findAll();
        for (StudentClass sc : findAll) {
            map.put(sc.getId(), "    " + sc.getNameNumber() + "." + sc.getName() + "   ");

        }
        System.out.println("MAP TEACHER " + map.size());
        return map;
    }

    private Map<Long, String> getAllSubjects() {
        Map<Long, String> map = new HashMap<Long, String>();
        List<Subject> findAll = subjectMan.findAll();
        for (Subject sc : findAll) {
            map.put(sc.getId(), sc.getName());

        }
        System.out.println("MAP TEACHER " + map.size());
        return map;
    }

    private Map<Long, String> getAllTeachers() {
        Map<Long, String> map = new HashMap<Long, String>();
        List<Teacher> findAll = teacherMan.findAll();
        for (Teacher sc : findAll) {
            map.put(sc.getId(), sc.getName() + " " + sc.getSurname());

        }
        System.out.println("MAP TEACHER " + map.size());
        return map;
    }

    private StudentClass formToClass(NewClassForm form, StudentClass clazz) {
        clazz.setName(form.getName());
        System.out.println("Byte.valueOf(form.getNumberName())"+form.getNumberName());
       clazz.setNameNumber(Byte.valueOf(form.getNumberName()));
        System.out.println("headClass"+form.getClassHead());
        Teacher findById = teacherMan.findById(form.getClassHead());
        if (findById == null) {
            System.out.println("Při vytváření nové třídy nebyl nalezen vybraný vyučující");
            return null;
        }
        else{
            System.out.println("učitel přiřazen");
            clazz.setId_teacher(findById);
        }
        
       clazz.setNumberOfYears(new Byte(form.getNumberOfYears()));
        clazz.setVisible(Boolean.TRUE);
        int semester = dateFunction.semester(Calendar.getInstance());
        if(semester==1)
        {
            String year = dateFunction.getYear(Calendar.getInstance());
            int actualYear = Integer.valueOf(year);
            int numberOfYears = form.getNumberOfYears().intValue();
            int actualClass = Integer.valueOf(form.getNumberName());
           int yearOfEnd =  (actualYear+numberOfYears)-(actualClass-1);
           clazz.setYearOfFoundation(dateFunction.setDate(1, 9, yearOfEnd));
            
        }
        else{
            String year = dateFunction.getYear(Calendar.getInstance());
            int actualYear = Integer.valueOf(year);
            int numberOfYears = form.getNumberOfYears().intValue();
            int actualClass = Integer.valueOf(form.getNumberName());
           int yearOfEnd =  (actualYear+numberOfYears)-(actualClass);
           clazz.setYearOfFoundation(dateFunction.setDate(1, 9, yearOfEnd));
        }
        //       if(Calendar.getInstance().get(Calendar.YEAR)>7){
        //         clazz.setYearOfFoundation(dateFunction.setDate(1, 9,(int)(Calendar.getInstance().get(Calendar.YEAR)+(form.getNumberOfYears()- Integer.parseInt(form.getNumberName())))));
        //       }
        //       else{
        //           clazz.setYearOfFoundation(dateFunction.setDate(1, 9,(int)(Calendar.getInstance().get(Calendar.YEAR)+(form.getNumberOfYears()- Integer.parseInt(form.getNumberName()))-1)));
        //       }
        


        return clazz;
    }
    private NewClassForm classToForm(NewClassForm form, StudentClass clazz) {
        form.setId(clazz.getId());
        form.setName(clazz.getName());
        System.out.println("classToForm Byte.valueOf(form.getNumberName())"+form.getNumberName());
       form.setNumberName(clazz.getNameNumber().toString());
        Teacher findById = teacherMan.findById(clazz.getId_teacher().getId());
        if (findById == null) {
            System.out.println("Při vytváření nové třídy nebyl nalezen vybraný vyučující");
            return null;
        }
        form.setClassHead(findById.getId());
        form.setNumberOfYears(clazz.getNumberOfYears());
        
       

       



        return form;
    }
    
}
