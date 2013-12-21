/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.admin;

import cz.cvut.hotkomar.controller.AdminControllerImp;
import cz.cvut.hotkomar.form.Form;
import cz.cvut.hotkomar.form.PaginationForm;
import cz.cvut.hotkomar.form.admin.NewStudentForm;
import cz.cvut.hotkomar.form.admin.ChangePassStudentForm;
import cz.cvut.hotkomar.form.admin.EditStudentForm;
import cz.cvut.hotkomar.form.admin.FullTextForm;
import cz.cvut.hotkomar.model.entity.FileEntity;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.StudentParent;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.UserType;
import cz.cvut.hotkomar.service.checkAndMake.CheckForm;
import cz.cvut.hotkomar.service.checkAndMake.CreateParentUser;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.checkAndMake.MailPost;
import cz.cvut.hotkomar.service.file.Download;
import cz.cvut.hotkomar.service.manager.FileMan;
import cz.cvut.hotkomar.service.manager.StudentClassMan;

import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.StudentParentMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.UserTypeMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import cz.cvut.hotkomar.service.pagination.Pagination;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.collections.list.SetUniqueList;
import org.openid4java.message.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Marie Hotkova
 */
@Controller
@Secured(value = {"ROLE_ADMIN"})
public class StudentCon implements AdminControllerImp{
    private Pagination pagination;
    private StudentMan studentMan;
    private Download download;
    private StudentClassMan studentClassMan;
    private UserTypeMan userTypeMan;
    private  FileMan fileMan;
    private DateFunction dateFunction;
    
   
    private  CreateParentUser createParentUser;
    private StudentParentMan studentParentMan;
    private MailPost mailPost;
    private TeacherMan teacherMan;
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
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }
    

    @Autowired
    public void setFileMan(FileMan fileMan) {
        this.fileMan = fileMan;
    }
    
       
    
@Autowired
    public void setUserTypeMan(UserTypeMan userTypeMan) {
        this.userTypeMan = userTypeMan;
    }
    
@Autowired
    public void setStudentClassMan(StudentClassMan studentClassMan) {
        this.studentClassMan = studentClassMan;
    }
    
@Autowired
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
@Autowired
    public void setStudentMan(StudentMan studentMan) {
        this.studentMan = studentMan;
    }
@Autowired
    public void setDownload(Download download) {
        this.download = download;
    }


@Autowired
    public void setCreateParentUser(CreateParentUser createParentUser) {
        this.createParentUser = createParentUser;
    }
@Autowired
    public void setStudentParentMan(StudentParentMan studentParentMan) {
        this.studentParentMan = studentParentMan;
    }
@Autowired
    public void setMailPost(MailPost mailPost) {
        this.mailPost = mailPost;
    }
@Autowired
    public void setTeacherMan(TeacherMan teacherMan) {
        this.teacherMan = teacherMan;
    }

   @Autowired
    private ShaPasswordEncoder passwordEncoder; 
    

    
    @RequestMapping (value="/admin/students.htm")
    @Override
    public String view(@RequestParam(defaultValue ="1", value="page", required = true)Integer page, ModelMap m, HttpSession session) {
        String attribute =(String)session.getAttribute("studentFullText");
        
        List<Student>  list= null;
        if(attribute==null){
        list = studentMan.findAll();
        }else{
            if(!attribute.isEmpty())            
            {
       list = studentMan.findFullText(attribute);
        System.out.println("finfFullText"+list);
            }
            else{
                list = studentMan.findAll();
            }
        }
        
        FullTextForm fullTextForm = new FullTextForm();
        fullTextForm.setFullText(attribute);
         pagination.setList(list);
          m.addAttribute("listOfStudents",pagination.paginationList(list, page));
        m.addAttribute("pageForm",pagination.getPageForm());
        m.addAttribute("classes", studentClassMan.findAll().size());
        m.addAttribute("form",fullTextForm);
        m.addAttribute("student",true);
        
        return "admin/student/viewStudent";
    }
    @RequestMapping (value="/admin/studentFullText.htm")
    public String viewPOST(@ModelAttribute("form") FullTextForm form, BindingResult errors, HttpSession session) {
       session.setAttribute("studentFullText", form.getFullText());
               
        return "redirect:students.htm";
    }

    @Override
    @RequestMapping (value="/admin/addStudent.htm")
    public String addGET(ModelMap m) {
        m.addAttribute("form", new NewStudentForm());
        m.addAttribute("student",true);
        m.addAttribute("listClass", getAllClasses());
        return "admin/student/addStudent";
    }

     @RequestMapping(value="/admin/adminNewStudent.htm")
    public String addPOST(@Valid@ModelAttribute("form") NewStudentForm form, BindingResult errors,ModelMap m) throws IOException {
                
         if(  errors.hasErrors()  )
         {
             m.addAttribute("form",form);
        m.addAttribute("student",true);
        m.addAttribute("listClass", getAllClasses());
        return "admin/student/addStudent";
         }
        Student student = new Student();
        student =fromToStudent(form, student);
        student.setPhoto( download.saveFotoStudent(form.getFile()));
         
       UserType userType = userTypeMan.findByType("ROLE_STUDENT");
        UserType userType2 = userTypeMan.findByType("ROLE_PARENT");
            if(userType!=null && userType2!=null){
            Set<UserType> idType = student.getIdType();
            idType.add(userType);
            student.setIdType(idType);
           
            }
            else{
                return"admin/errorHups";
               
            }
        studentMan.add(student, true);
         System.out.println("student id "+student.getId());
        StudentParent generateParent = generateParent(student, student.getMotherMail(),userType2);
        String plainpass=generateParent.getPassword();
        String hash4 = passwordEncoder.encodePassword(generateParent.getPassword(),generateParent.getLogin());
        generateParent.setPassword(hash4);
        studentParentMan.add(generateParent, false);
        
        try{
         SimpleMailMessage mail = new SimpleMailMessage();
         System.out.println("matky mail"+student.getMotherMail());
         mail.setTo(student.getMotherMail());
         mail.setFrom("kolejniklub@gmail.com");
         mail.setSubject("SchoolAdmin: přihlašovací údaje");
         mail.setText("Vaše přihlašovací jméno je "+plainpass+" a heslo je "+generateParent.getPassword()+".");
         mailPost.send(mail);
        }
        catch(MailException e)
        {
            message.setNegativeMes("Nepodařilo se odestal e-mail zákonnému zástupci.");
        }
        
        return"redirect:students.htm";
    }

    @Override
    @RequestMapping(value = "/admin/infoStudent.htm")
    public String info(@RequestParam(value = "id", required = true) Long id,ModelMap m) {
        Student student = studentMan.findById(id);
        
        if(student==null){
           return"admin/errorHups";
        }
        StudentParent findByStudent = studentParentMan.findByStudent(student);
        m.addAttribute("students", student);
        m.addAttribute("student",true);
        m.addAttribute("studentParent", findByStudent);
       
        return"/admin/student/infoStudent";
    }

    @Override
    @RequestMapping(value ="/admin/editStudent.htm")
    public String editGET(Long id, ModelMap m) {
        Student student = studentMan.findById(id);
     if(student==null){
          return"admin/errorHups";
     }
        EditStudentForm studentToForm = editStudentToForm(new EditStudentForm(), student);
        Map<Long, String> listClass = getAllClasses(student.getId_class());
     m.addAttribute("student",true);
     m.addAttribute("form",studentToForm);
     m.addAttribute("listClass", listClass);
     return"/admin/student/edit";
    }

   @RequestMapping(value = "/admin/newEditStudent.htm")
    public String editPOST(@Valid @ModelAttribute("form") EditStudentForm form, BindingResult errors,ModelMap m) {
       if(errors.hasErrors())
       {
           StudentClass findById = studentClassMan.findById(form.getId_class());
            Map<Long, String> listClass = getAllClasses(findById);
          m.addAttribute("student",true);
     m.addAttribute("form",form);
     m.addAttribute("listClass", listClass);
      return"/admin/student/edit";
       }
        Student findById = studentMan.findById(form.getId());
        if(findById==null)
        {
            return "/admin/errorHups";
        }
        Student formToStudent = editFromToStudent(form, findById);
        if(!form.getFile().isEmpty())
        {
          formToStudent.setPhoto(download.saveFotoStudent(form.getFile()));
        }
        studentMan.edit(formToStudent, true);
        System.out.println("form id "+form.getId());
      // return "redirect:infoStudent.htm?id="+form.getId();
        return"redirect:infoStudent.htm?id="+form.getId();
    }

    @Override
    @RequestMapping(value="/admin/removeStudent.htm")
    public String remove(@RequestParam(value="id", required= true)Long id,@RequestParam(value="page", required=true)Integer page) {
        Student student = studentMan.findById(id);
     if(student!=null)
     {
         student.setId_class(null);
         studentMan.edit(student, false);
         studentMan.visible(id, true);
            StudentParent findByStudent = studentParentMan.findByStudent(student);
         if(findByStudent!=null)
         {
             
             studentParentMan.visible(findByStudent.getId(), false);
         }
     }
     else{
          return"admin/errorHups";
     }
    return"redirect:students.htm?page="+page;
    }

    
    @RequestMapping(value = "/admin/studentPass.htm")
    public String changePassGET(@RequestParam(value ="id", required = true)Long id,ModelMap m) {
        Student findById = studentMan.findById(id);
        if(findById==null){
            return "admin/errorHups";
        }
        ChangePassStudentForm changePassStudentForm = new ChangePassStudentForm();
        changePassStudentForm.setId(findById.getId());
        m.addAttribute("student",true);
     m.addAttribute("form",changePassStudentForm);
     return"/admin/student/changePassword";
    }

    @RequestMapping(value = "/admin/changePasswordStudent.htm", method = RequestMethod.POST)
    public String changePassPOST(@Valid @ModelAttribute("form") ChangePassStudentForm form, BindingResult errors, ModelMap m, Authentication auth) {
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
        
        String hash4 = passwordEncoder.encodePassword(form.getAdminPass(),findById.getLogin());
        boolean equals = findById.getPassword().equals(hash4);
        if(errors.hasErrors() || !equals)
        {      
            if(!equals)
            {
                message.setNegativeMes("Nezadal jste právě heslo!");
            }
            
            System.out.println("ERROR JE "+errors.hasErrors()+"  equals "+equals);
        m.addAttribute("student",true);
     m.addAttribute("form",form);
     return"/admin/student/changePassword"; 
        }
        Student findById1 = studentMan.findById(form.getId());
            if(findById1==null)
            {
                return"admin/errorHups";
            }
            hash4 = passwordEncoder.encodePassword(form.getPassword(),findById1.getLogin());
            findById1.setPassword(hash4);
            studentMan.edit(findById1, true);
        return"redirect:infoStudent.htm?id="+form.getId();
    }
@RequestMapping(value="/admin/removePhotoS.htm")
    public String removePhoto(@RequestParam(value="id", required=true)Long id)
    {
        Student findById = studentMan.findById(id);
        if(findById==null)
        {
            return "admin/errorHups";
        }
        fileMan.visible(findById.getPhoto().getId(), false);
        findById.setPhoto(null);
        studentMan.edit(findById, true);
        return"redirect:infoStudent.htm?id="+id;
        
       
    }
   private Map<Long, String> getAllClasses() {
        Map<Long, String> map = new HashMap<Long, String>();
        List<StudentClass> findAll = studentClassMan.findAll();
        for (StudentClass sc : findAll) {
            map.put(sc.getId(),sc.getNameNumber()+"."+sc.getName());

        }
        System.out.println("MAP TEACHER "+map.size());
        return map;
    } 

   private Student fromToStudent(NewStudentForm form, Student student)
   {
        
       
            String[] splitDataString = dateFunction.splitDataString(form.getDateOfBorn());
            Calendar setDate = dateFunction.setDate(Integer.parseInt(splitDataString[0]),Integer.parseInt(splitDataString[1]),Integer.parseInt(splitDataString[2]));
       student.setDateOfBorn(setDate);
        
       student.setFax(form.getFax());
       student.setIdentificationNumber(form.getIdentificationNumber());
       System.out.println("login"+form.getLogin());

     if(form.getLogin()!=null){
      student.setLogin(form.getLogin().toLowerCase());}
       student.setMail(form.getMail());
       student.setMobilePhone(form.getMobilePhone());
       student.setName(form.getName());
       String hash4 = passwordEncoder.encodePassword(form.getPassword(),student.getLogin());
        student.setPassword(hash4);
       student.setPhoneNumber(form.getPhoneNumber());
       student.setSurname(form.getSurname());
       student.setVisible(Boolean.TRUE);
       student.setFatherName(form.getFatherName());
       student.setFatherPhone(form.getFatherPhone());
       student.setFatherSurname(form.getFatherSurname());
       student.setMotherMail(form.getMotherMail());
       student.setMotherName(form.getMotherName());
       student.setMobilePhone(form.getMobilePhone());
       student.setMotherSurname(form.getMotherSurname());
        StudentClass findById = studentClassMan.findById(form.getId_class());
       if(findById==null)
       {
           return null;
       }
       else
       {
      student.setId_class(findById);
       }
       
       return student;
   }
    private Student editFromToStudent(EditStudentForm form, Student student)
   {
        
       
            String[] splitDataString = dateFunction.splitDataString(form.getDateOfBorn());
            Calendar setDate = dateFunction.setDate(Integer.parseInt(splitDataString[0]),Integer.parseInt(splitDataString[1]),Integer.parseInt(splitDataString[2]));
       student.setDateOfBorn(setDate);
        
       student.setFax(form.getFax());
       student.setIdentificationNumber(form.getIdentificationNumber());
       
       student.setMail(form.getMail());
       student.setMobilePhone(form.getMobilePhone());
       student.setName(form.getName());
       //student.setPassword(null);
       student.setPhoneNumber(form.getPhoneNumber());
       student.setSurname(form.getSurname());
       student.setVisible(Boolean.TRUE);
       student.setFatherName(form.getFatherName());
       student.setFatherPhone(form.getFatherPhone());
       student.setFatherSurname(form.getFatherSurname());
       student.setMotherMail(form.getMotherMail());
       student.setMotherName(form.getMotherName());
       student.setMobilePhone(form.getMobilePhone());
       student.setMotherSurname(form.getMotherSurname());
        StudentClass findById = studentClassMan.findById(form.getId_class());
       if(findById==null)
       {
           return null;
       }
       else
       {
      student.setId_class(findById);
       }
       
       return student;
   }
   private NewStudentForm studentToForm (NewStudentForm form, Student student)
   {    form.setId(student.getId());
   
       form.setDateOfBorn(dateFunction.getDateString(student.getDateOfBorn()));
      form.setFax(student.getFax());
       form.setIdentificationNumber(student.getIdentificationNumber());
       form.setLogin(student.getLogin());
       form.setMail(student.getMail());
       form.setMobilePhone(student.getMobilePhone());
       form.setName(student.getName());
       //student.setPassword(null);
       form.setPhoneNumber(student.getPhoneNumber());
       form.setSurname(student.getSurname());
       form.setFatherName(student.getFatherName());
       form.setFatherPhone(student.getFatherPhone());
       form.setFatherSurname(student.getFatherSurname());
       form.setMotherMail(student.getMotherMail());
       form.setMotherName(student.getMotherName());
       form.setMobilePhone(student.getMobilePhone());
       form.setMotherSurname(student.getMotherSurname());
       form.setId_class(student.getId_class().getId());
       
       
       
       return form;
   }
   
   private EditStudentForm editStudentToForm (EditStudentForm form, Student student)
   {    form.setId(student.getId());
   
       form.setDateOfBorn(dateFunction.getDateString(student.getDateOfBorn()));
      form.setFax(student.getFax());
       form.setIdentificationNumber(student.getIdentificationNumber());
    
       form.setMail(student.getMail());
       form.setMobilePhone(student.getMobilePhone());
       form.setName(student.getName());
       //student.setPassword(null);
       form.setPhoneNumber(student.getPhoneNumber());
       form.setSurname(student.getSurname());
       form.setFatherName(student.getFatherName());
       form.setFatherPhone(student.getFatherPhone());
       form.setFatherSurname(student.getFatherSurname());
       form.setMotherMail(student.getMotherMail());
       form.setMotherName(student.getMotherName());
       form.setMobilePhone(student.getMobilePhone());
       form.setMotherPhone(student.getMotherPhone());
       form.setMotherSurname(student.getMotherSurname());
       form.setId_class(student.getId_class().getId());
       
       
       
       return form;
   }
  private Map<Long, String> getAllClasses(StudentClass clazz) {
        Map<Long, String> map = new HashMap<Long, String>();
        List<StudentClass> findAll = studentClassMan.findAll();
        for (StudentClass sc : findAll) {
            if(sc!=clazz){
            map.put(sc.getId(),sc.getNameNumber()+"."+sc.getName());
            }

        }
        map.put(clazz.getId(),clazz.getNameNumber()+"."+clazz.getName());
        System.out.println("MAP TEACHER "+map.size());
        return map;
    } 
  
  private StudentParent generateParent(Student s,String mail,UserType userType)
  {
        StudentParent studentParent = new StudentParent();
        studentParent.setStudent(s);
        studentParent.setVisible(Boolean.TRUE);
        studentParent.setMail(mail);
        String createLogin = createParentUser.createLogin(s.getName(),s.getSurname(), s.getId());
        System.out.println("create login "+createLogin);
        String password = createParentUser.getPassword();
        
        studentParent.setPassword(password);
        studentParent.setLogin(createLogin);
        Set<UserType> idType = studentParent.getIdType();
            idType.add(userType);           
        studentParent.setIdType(idType);
        return studentParent;
  }
}
