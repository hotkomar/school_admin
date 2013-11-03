/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.admin;

import cz.cvut.hotkomar.controller.AdminControllerImp;
import cz.cvut.hotkomar.form.Form;
import cz.cvut.hotkomar.form.PaginationForm;
import cz.cvut.hotkomar.form.admin.NewTeacherForm;
import cz.cvut.hotkomar.form.admin.ChangePassStudentForm;
import cz.cvut.hotkomar.form.admin.NewStudentForm;
import cz.cvut.hotkomar.model.entity.FileEntity;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.UserType;
import cz.cvut.hotkomar.service.file.Download;
import cz.cvut.hotkomar.service.manager.FileMan;
import cz.cvut.hotkomar.service.manager.TeacherMan;
import cz.cvut.hotkomar.service.manager.UserTypeMan;
import cz.cvut.hotkomar.service.pagination.Pagination;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Marie Hotkova
 */
@Controller
public class TeacherCon implements AdminControllerImp{
    private TeacherMan teacherMan;
    private Pagination pagination;
    private Download download;
    private UserTypeMan userTypeMan;
    private FileMan fileMan;
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
    
    
//    @RequestMapping (value="/admin/teachers.htm")
//    public String teachersGet (ModelMap m)
//    {
//        m.addAttribute("teacher",true);
//        return "/admin/teacher/viewTeacher";
//    }

    @Override
    @RequestMapping (value="/admin/teachers.htm")
    public String view(Integer page, ModelMap m) {
        List <Teacher> list = teacherMan.findAll();
       
       //  pagination.setList(list);
        m.addAttribute("listOfTeachers", pagination.paginationList(list, page));
        m.addAttribute("teacher",true);
        m.addAttribute("pageForm",pagination.getPageForm());
      return "/admin/teacher/viewTeacher";
    }

    @Override
    @RequestMapping(value="/admin/addTeacher.htm")
    public String addGET(ModelMap m) {
        m.addAttribute("teacher",true);
        m.addAttribute("form",new NewTeacherForm());
        return"/admin/teacher/addTeacher";
    }

    @RequestMapping(value="/admin/adminNewTeacher.htm")
    public String addPOST(@ModelAttribute("form") NewTeacherForm form, BindingResult errors, ModelMap m) {
        Teacher teacher = formToTeacher(form, new Teacher());
       
        Set<UserType> user = new HashSet<UserType>();
//        
        //user is admin
//        if(form.getAdmin()==true)
//        {
//            UserType userType = userTypeMan.findByType("ROLE_ADMIN");
//            if(userType!=null)
//            {
//                user.add(userType);
//                
//            }else
//            {
//              return "/admin/error";  
//            }
//                
//        }
       // user is teacher
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
        }
        int isAdmin = teacher.getIdType().size();
        System.out.println("role učitele"+teacher.getIdType().size());
//        m.addAttribute("last",lastTeacher);
//        m.addAttribute("next", nextTeacher);
        m.addAttribute("teachers", teacher);
        m.addAttribute("teacher",true);
        m.addAttribute("admin",isAdmin);
       
        return"/admin/teacher/infoTeacher";
    }

    @Override
    @RequestMapping(value = "/admin/editTeacher.htm")
    public String editGET(@RequestParam(value = "id", required = true)Long id, ModelMap m) {
        Teacher teacher = teacherMan.findById(id);
        if(teacher==null)
        {
            //chyba
            System.out.println("nenašel jsem id učitele");
        }
        NewTeacherForm form = teacherToForm(teacher);
        form.setAdmin(isAdmin(teacher));
       
        m.addAttribute("form",form);
        m.addAttribute("teacher", true);
      
       return"/admin/teacher/editTeacher";
    }

  @RequestMapping(value = "/admin/adminEditTeacher.htm")
    public String editPOST(@ModelAttribute("form") NewTeacherForm form, BindingResult errors,ModelMap m) {
        Teacher findById = teacherMan.findById(form.getId());
        System.out.println("findById teacher"+findById.getId());
        if(findById==null){
            //chyba
            }
        Teacher formToTeacher = formToTeacher(form,findById);
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
       m.addAttribute("form",new ChangePassStudentForm());
       m.addAttribute("teacher", true);
        return"/admin/teacher/changePasswordT";
    }


    public String changePassPOST(Form form, BindingResult errors, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping(value ="/admin/removeTeacher.htm" )
    public String remove(@RequestParam(value ="id", required =true)Long id,@RequestParam(value ="page", required = false) Integer page) {
        Teacher teacher= teacherMan.findById(id);
        if(teacher==null)
        {
            //chyba
            System.out.println("učitel je null");
        }
        teacherMan.visible(id, true);
        
        return"redirect:teachers.htm";
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
       // teacher.setDateOfBorn(form.getDateOfBorn());
        teacher.setFax(form.getFax());
        teacher.setIdentificationNumber(form.getIdentificationNumber());
        teacher.setLogin(form.getLogin());
        teacher.setMail(form.getMail());
        teacher.setMobilePhone(form.getMobilePhone());
        teacher.setName(form.getName());
       // teacher.setPassword(form.getPassword());
        teacher.setPhoneNumber(form.getPhoneNumber());
        teacher.setPlaceOfBorn(form.getPlaceOfBorn());
        teacher.setSurname(form.getSurname());
        teacher.setVisible(Boolean.TRUE);
        //teacher.setDate_of_employ(form.getDateOfEmploy());
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
       // teacher.setDateOfBorn(form.getDateOfBorn());
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
        
        //teacher.setDate_of_employ(form.getDateOfEmploy());
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
                return new Boolean(true);
            }
        }
        return new Boolean(false);
    }
}

