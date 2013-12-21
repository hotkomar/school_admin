/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.admin;

import com.sun.net.httpserver.HttpsServer;
import cz.cvut.hotkomar.controller.AdminControllerImp;
import cz.cvut.hotkomar.form.ChangePassForm;
import cz.cvut.hotkomar.form.Form;
import cz.cvut.hotkomar.form.PaginationForm;
import cz.cvut.hotkomar.form.admin.NewTeacherForm;
import cz.cvut.hotkomar.form.admin.ChangePassStudentForm;
import cz.cvut.hotkomar.form.admin.EditTeacherForm;
import cz.cvut.hotkomar.form.admin.FullTextForm;
import cz.cvut.hotkomar.form.admin.NewStudentForm;
import cz.cvut.hotkomar.model.entity.FileEntity;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.UserType;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.file.Download;
import cz.cvut.hotkomar.service.manager.FileMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.UserTypeMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import cz.cvut.hotkomar.service.pagination.Pagination;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Marie Hotkova
 */
@Controller
@Secured(value = {"ROLE_ADMIN"})
public class TeacherCon implements AdminControllerImp{
    private TeacherMan teacherMan;
    private Pagination pagination;
    private Download download;
    private UserTypeMan userTypeMan;
    private FileMan fileMan;
    private DateFunction dateFunction;
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
    public void setUserTypeMan(UserTypeMan userType) {
        this.userTypeMan = userType;
    }
    
@Autowired
    public void setDownload(Download download) {
        this.download = download;
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
    private ShaPasswordEncoder passwordEncoder;   
    
//    @RequestMapping (value="/admin/teachers.htm")
//    public String teachersGet (ModelMap m)
//    {
//        m.addAttribute("teacher",true);
//        return "/admin/teacher/viewTeacher";
//    }

    @Override
    @RequestMapping (value="/admin/teachers.htm")
    public String view(@RequestParam(defaultValue ="1", value="page", required = true)Integer page, ModelMap m, HttpSession session) {
       String attribute =(String)session.getAttribute("teacherFullText");
        
        List<Teacher>  list= null;
        if(attribute==null){
        list = teacherMan.findAll();
        }else{
            if(!attribute.isEmpty())            
            {
       list = teacherMan.findFullText(attribute);
        System.out.println("finfFullText"+list);
            }
            else{
                list = teacherMan.findAll();
            }
        }
        
        FullTextForm fullTextForm = new FullTextForm();
        fullTextForm.setFullText(attribute);
         pagination.setList(list);
        m.addAttribute("listOfTeachers", pagination.paginationList(list, page));
        m.addAttribute("teacher",true);
        m.addAttribute("pageForm",pagination.getPageForm());
        m.addAttribute("form",fullTextForm);
      return "/admin/teacher/viewTeacher";
    }
@RequestMapping(value = "/admin/teachersFulltext.htm")
public String viewPOST(@ModelAttribute("form") FullTextForm form, BindingResult errors, HttpSession session) {
       session.setAttribute("teacherFullText", form.getFullText());
               
        return "redirect:teachers.htm";
    }
    @Override
    @RequestMapping(value="/admin/addTeacher.htm")
    public String addGET(ModelMap m) {
        m.addAttribute("teacher",true);
        NewTeacherForm form = new NewTeacherForm();
        form.setWagePerHour("0");
        m.addAttribute("form",form);
        return"/admin/teacher/addTeacher";
    }

    @RequestMapping(value="/admin/adminNewTeacher.htm")
    public String addPOST(@Valid@ModelAttribute("form") NewTeacherForm form, BindingResult errors, ModelMap m) {
        
        if(errors.hasErrors())
        {
            m.addAttribute("teacher",true);
        m.addAttribute("form",form);
        return"/admin/teacher/addTeacher";
        }
        
        Teacher teacher = formToTeacher(form, new Teacher());
       
        Set<UserType> user = new HashSet<UserType>();

        UserType userType = userTypeMan.findByType("ROLE_TEACHER");      
                if (userType != null) {
            user.add(userType);
            teacher.setIdType(user);
        } else {
            return "/admin/error";
        }
                teacher.setIdType(user);
                teacher.setPhoto( download.saveFotoStudent(form.getFile()));
        teacherMan.add(teacher, true);
        System.out.println("přidat");
        return "redirect:teachers.htm";
    }

    @Override
    @RequestMapping(value = "/admin/infoTeacher.htm")
    public String info(@RequestParam(value ="id", required = true)Long id, ModelMap m) {
        Teacher teacher = teacherMan.findById(id);
////        Teacher lastTeacher = teacherMan.findById(id-1);
////        Teacher nextTeacher = teacherMan.findById(id+1);
        if(teacher==null)
        {
            //chyba
            System.out.println("učitel nebyl nalezen");
            return"admin/errorHups";
        }
       
        
//        m.addAttribute("last",lastTeacher);
//        m.addAttribute("next", nextTeacher);
        m.addAttribute("teachers", teacher);
        m.addAttribute("teacher",true);
        
       
        return"/admin/teacher/infoTeacher";
    }

    @Override
    @RequestMapping(value = "/admin/editTeacher.htm")
    public String editGET(@RequestParam(value = "id", required = true)Long id, ModelMap m) {
        Teacher teacher = teacherMan.findById(id);
        if(teacher==null)
        {
            return"admin/errorHups";
            
        }
        EditTeacherForm form = editTeacherToForm(teacher);
        form.setAdmin(isAdmin(teacher));
       
        m.addAttribute("form",form);
        m.addAttribute("teacher", true);
      
       return"/admin/teacher/editTeacher";
    }

  @RequestMapping(value = "/admin/adminEditTeacher.htm")
    public String editPOST(@Valid@ModelAttribute("form") EditTeacherForm form, BindingResult errors,ModelMap m) {
      if(errors.hasErrors())
      {
          m.addAttribute("form",form);
        m.addAttribute("teacher", true);
      
       return"/admin/teacher/editTeacher";
      }
        Teacher findById = teacherMan.findById(form.getId());
        System.out.println("findById teacher"+findById.getId());
        if(findById==null){
            //chyba
            }
        Teacher formToTeacher = editFormToTeacher(form,findById);
        formToTeacher.setId(form.getId());
        if(!form.getFile().isEmpty())
        {
          formToTeacher.setPhoto(download.saveFotoStudent(form.getFile()));
        }
        teacherMan.edit(formToTeacher, true);
        return"redirect:infoTeacher.htm?id="+form.getId();
    }

    


    @RequestMapping(value = "/admin/passTeacher.htm")
    public String changePassGET(@RequestParam(value ="id", required = true) Long id, ModelMap m) {
        Teacher findById = teacherMan.findById(id);
        if(findById==null)
        {
           return"admin/errorHups";
        }
        ChangePassStudentForm changePassStudentForm = new ChangePassStudentForm();
        changePassStudentForm.setId(findById.getId());
       m.addAttribute("form",changePassStudentForm);
       m.addAttribute("teacher", true);
        return"/admin/teacher/changePasswordT";
    }

@RequestMapping(value = "/admin/changePasswordTeacher.htm", method = RequestMethod.POST)
    public String changePassPOST(@ModelAttribute(value ="form") ChangePassStudentForm form, BindingResult errors, ModelMap m, Authentication auth) {
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
            
            
        m.addAttribute("teacher",true);
     m.addAttribute("form",form);
     return"/admin/teacher/changePassword"; 
        }
        Teacher findById1 = teacherMan.findById(form.getId());
            if(findById1==null)
            {
                return"admin/errorHups";
            }
         String   hash = passwordEncoder.encodePassword(form.getPassword(),findById1.getLogin());
            findById1.setPassword(hash);
            System.out.println("hash"+hash);
            teacherMan.edit(findById1, true);
        return"redirect:infoTeacher.htm?id="+form.getId();
    }

    @Override
    @RequestMapping(value ="/admin/removeTeacher.htm" )
    public String remove(@RequestParam(value ="id", required =true)Long id,@RequestParam(value ="page", required = false) Integer page) {
        Teacher teacher= teacherMan.findById(id);
        if(teacher==null)
        {
            //chyba
            System.out.println("učitel je null");
            return"/admin/errorHups";
        }
        if(teacher.getId_class()!=null)
        {
            message.setNegativeMes("Uživalet "+teacher.getDegree()+" "+teacher.getName()+" "+teacher.getSurname()+" nemůže být vymazán. Je třídním učitelem.");
            return"redirect:infoTeacher.htm?id="+teacher.getId();
        }
        for (Iterator<UserType> i=teacher.getIdType().iterator();i.hasNext();) {
            UserType next = i.next();
            if(next.getName().equals("ROLE_ADMIN"))
            {
                message.setNegativeMes("Uživalet "+teacher.getDegree()+" "+teacher.getName()+" "+teacher.getSurname()+" nemůže být vymazán. Je administrátorem.");
            return"redirect:infoTeacher.htm?id="+teacher.getId();
            }
        }
        teacherMan.visible(id, true);
        
        return"redirect:teachers.htm?page="+page;
    }
    @RequestMapping(value="/admin/removePhoto.htm")
    public String removePhoto(@RequestParam(value="id", required=true)Long id)
    {
        Teacher findById = teacherMan.findById(id);
        if(findById==null)
        {
            //chyba
        }
        fileMan.visible(findById.getPhoto().getId(), false);
        findById.setPhoto(null);
        teacherMan.edit(findById, true);
        return"redirect:infoTeacher.htm?id="+id;
        
       
    }
    private Teacher formToTeacher(NewTeacherForm form, Teacher t)
    {
        Teacher teacher = t;
        String[] splitDataString = dateFunction.splitDataString(form.getDateOfBorn());
        
       teacher.setDateOfBorn(dateFunction.setDate(Integer.parseInt(splitDataString[0]),Integer.parseInt(splitDataString[1]),Integer.parseInt(splitDataString[2])));
       
        teacher.setFax(form.getFax());
        teacher.setIdentificationNumber(form.getIdentificationNumber());
        teacher.setLogin(form.getLogin().toLowerCase());
        teacher.setMail(form.getMail());
        teacher.setMobilePhone(form.getMobilePhone());
        teacher.setName(form.getName());
        String hash4 = passwordEncoder.encodePassword(form.getPassword(),teacher.getLogin());
        teacher.setPassword(hash4);
       // teacher.setPassword(form.getPassword());
        teacher.setPhoneNumber(form.getPhoneNumber());
        teacher.setPlaceOfBorn(form.getPlaceOfBorn());
        teacher.setSurname(form.getSurname());
        teacher.setVisible(Boolean.TRUE);
       String [] splitDataString2=dateFunction.splitDataString(form.getDateOfEmploy());
        teacher.setDate_of_employ(dateFunction.setDate(Integer.parseInt(splitDataString2[0]),Integer.parseInt(splitDataString2[1]),Integer.parseInt(splitDataString2[2])));
        teacher.setDegree(form.getDegree());
        teacher.setEducation(form.getEducation());
        teacher.setEducation_consultant(form.getEducationConsultant());
        if(!form.getWagePerHour().isEmpty())
        {
       teacher.setWagePerHour(Short.valueOf(form.getWagePerHour()));
        }
        else
        {
            teacher.setWagePerHour(new Short((short)0));
        }
        if(form.getAdmin())
        {
            UserType userType = userTypeMan.findByType("ROLE_ADMIN");
            if(userType!=null){
            Set<UserType> idType = teacher.getIdType();
            idType.add(userType);
            teacher.setIdType(idType);
            }
            else{
                System.out.println("nenalezla jsem ROLE_ADMIN");
            }
        }
        else
        {
            UserType userType = userTypeMan.findByType("ROLE_TEACHER");
            if(userType!=null){
            Set<UserType> idType = new HashSet<UserType> ();
            idType.add(userType);
            teacher.setIdType(idType);
        }
        }
        
        
        
        return teacher;
    }
    private Teacher editFormToTeacher(EditTeacherForm form, Teacher t)
    {
        Teacher teacher = t;
        String[] splitDataString = dateFunction.splitDataString(form.getDateOfBorn());
        
       teacher.setDateOfBorn(dateFunction.setDate(Integer.parseInt(splitDataString[0]),Integer.parseInt(splitDataString[1]),Integer.parseInt(splitDataString[2])));
       
        teacher.setFax(form.getFax());
        teacher.setIdentificationNumber(form.getIdentificationNumber());
        
        teacher.setMail(form.getMail());
        teacher.setMobilePhone(form.getMobilePhone());
        teacher.setName(form.getName());
       // teacher.setPassword(form.getPassword());
        teacher.setPhoneNumber(form.getPhoneNumber());
        teacher.setPlaceOfBorn(form.getPlaceOfBorn());
        teacher.setSurname(form.getSurname());
        teacher.setVisible(Boolean.TRUE);
       String [] splitDataString2=dateFunction.splitDataString(form.getDateOfEmploy());
        teacher.setDate_of_employ(dateFunction.setDate(Integer.parseInt(splitDataString2[0]),Integer.parseInt(splitDataString2[1]),Integer.parseInt(splitDataString2[2])));
        teacher.setDegree(form.getDegree());
        teacher.setEducation(form.getEducation());
        teacher.setEducation_consultant(form.getEducationConsultant());
        if(!form.getWagePerHour().isEmpty())
        {
       teacher.setWagePerHour(Short.valueOf(form.getWagePerHour()));
        }
        else
        {
            teacher.setWagePerHour(new Short((short)0));
        }
        if(form.getAdmin())
        {
            UserType userType = userTypeMan.findByType("ROLE_ADMIN");
            if(userType!=null){
            Set<UserType> idType = teacher.getIdType();
            idType.add(userType);
            teacher.setIdType(idType);
            }
            else{
                System.out.println("nenalezla jsem ROLE_ADMIN");
            }
        }
        else
        {
            UserType userType = userTypeMan.findByType("ROLE_TEACHER");
            if(userType!=null){
            Set<UserType> idType = new HashSet<UserType> ();
            idType.add(userType);
            teacher.setIdType(idType);
        }
        }
        
        
        
        return teacher;
    }
    private NewTeacherForm teacherToForm(Teacher teacher)
    {
        NewTeacherForm form = new  NewTeacherForm();
       form.setDateOfBorn(dateFunction.getDateString(teacher.getDateOfBorn()));
        form.setId(teacher.getId());
        form.setFax(teacher.getFax());
        form.setIdentificationNumber(teacher.getIdentificationNumber());
        form.setLogin(teacher.getLogin());
        form.setMail(teacher.getMail());
        form.setMobilePhone(teacher.getMobilePhone());
        form.setName(teacher.getName());
       // teacher.setPassword(form.getPassword());
        form.setPhoneNumber(teacher.getPhoneNumber());
        form.setPlaceOfBorn(teacher.getPlaceOfBorn());
        form.setSurname(teacher.getSurname());
        
        form.setDateOfEmploy(dateFunction.getDateString(teacher.getDate_of_employ()));
        form.setDegree(form.getDegree());
        form.setEducation(form.getEducation());
        form.setEducationConsultant(teacher.getEducation_consultant());
        form.setWagePerHour(teacher.getWagePerHour().toString());
        
        
        
        
        return form;
    }
    private EditTeacherForm editTeacherToForm(Teacher teacher)
    {
        EditTeacherForm form = new  EditTeacherForm();
       form.setDateOfBorn(dateFunction.getDateString(teacher.getDateOfBorn()));
        form.setId(teacher.getId());
        form.setFax(teacher.getFax());
        form.setIdentificationNumber(teacher.getIdentificationNumber());
        
        form.setMail(teacher.getMail());
        form.setMobilePhone(teacher.getMobilePhone());
        form.setName(teacher.getName());
       // teacher.setPassword(form.getPassword());
        form.setPhoneNumber(teacher.getPhoneNumber());
        form.setPlaceOfBorn(teacher.getPlaceOfBorn());
        form.setSurname(teacher.getSurname());
        
        form.setDateOfEmploy(dateFunction.getDateString(teacher.getDate_of_employ()));
        form.setDegree(form.getDegree());
        form.setEducation(form.getEducation());
        form.setEducationConsultant(teacher.getEducation_consultant());
        form.setWagePerHour(teacher.getWagePerHour().toString());
        
        
        
        
        return form;
    }
    private Boolean isAdmin(Teacher teacher)
    {
        for(UserType i : teacher.getIdType())
        {
            System.out.println("role "+i.getName());
            if(i.getName().equals("ROLE_ADMIN"))
            {
                return Boolean.TRUE;
            }
        }
        return  Boolean.FALSE;
    }
}

