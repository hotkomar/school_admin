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
import cz.cvut.hotkomar.model.entity.FileEntity;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.UserType;
import cz.cvut.hotkomar.service.file.Download;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import cz.cvut.hotkomar.service.saving.StudentService;
import cz.cvut.hotkomar.service.manager.StudentMan;
import cz.cvut.hotkomar.service.manager.UserTypeMan;
import cz.cvut.hotkomar.service.pagination.Pagination;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.list.SetUniqueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

/**
 *
 * @author Marie Hotkova
 */
@Controller
public class StudentCon implements AdminControllerImp{
    private Pagination pagination;
    private StudentMan studentMan;
    private Download download;
    private StudentClassMan studentClassMan;
    private UserTypeMan userTypeMan;
    
    
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

    
    

    
    @RequestMapping (value="/admin/students.htm")
    public String view(@RequestParam(defaultValue ="1", value="page", required = true)Integer page, ModelMap m) {
        List<Student> list = studentMan.findAll();
       
         pagination.setList(list);
          m.addAttribute("listOfStudents",pagination.paginationList(list, page));
        m.addAttribute("pageForm",pagination.getPageForm());
        m.addAttribute("student",true);
        
        return "admin/student/viewStudent";
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
    public String addPOST(@ModelAttribute("form") NewStudentForm form, BindingResult errors,ModelMap m) {
        Student student = new Student();
        student =fromToStudent(form, student);
        student.setPhoto( download.saveFotoStudent(form.getFile()));
        studentMan.add(student, true);
        
        
        
        return"redirect:students.htm";
    }

    @Override
    @RequestMapping(value = "/admin/infoStudent.htm")
    public String info(@RequestParam(value = "id", required = true) Long id,ModelMap m) {
        Student student = studentMan.findById(id);
        if(student==null){
            //chybu
        }
        m.addAttribute("students", student);
        m.addAttribute("student",true);
       
        return"/admin/student/infoStudent";
    }

    @Override
    @RequestMapping(value ="/admin/editStudent.htm")
    public String editGET(Long id, ModelMap m) {
        Student student = studentMan.findById(id);
     if(student==null){
         //chyba
     }
     m.addAttribute("student",true);
     m.addAttribute("form",new NewStudentForm());
     
     return"/admin/student/edit";
    }

   
    public String editPOST(Form form, BindingResult errors, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping(value="/admin/removeStudent.htm")
    public String remove(@RequestParam(value="id", required= true)Long id,@RequestParam(value="page", required=true)Integer page) {
        Student student = studentMan.findById(id);
     if(student!=null)
     {
         studentMan.visible(student.getId(), true);
     }
     else{
         //Chyba
     }
    return"redirect:students.htm?page="+page;
    }

    
    @RequestMapping(value = "/admin/studentPass.htm")
    public String changePassGET(@RequestParam(value ="id", required = true)Long id,ModelMap m) {
        m.addAttribute("student",true);
     m.addAttribute("form",new ChangePassStudentForm());
     return"/admin/student/changePassword";
    }

    
    public String changePassPOST(Form form, BindingResult errors, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
       //student.setDateOfBorn();
       student.setFax(form.getFax());
       student.setIdentificationNumber(form.getIdentificationNumber());
       student.setLogin(form.getLogin());
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
       student.setId_class(findById);
       UserType userType = userTypeMan.findByType("ROLE_STUDENT");
            if(userType!=null){
            Set<UserType> idType = student.getIdType();
            idType.add(userType);
            student.setIdType(idType);
            }
            else{
                System.out.println("nenalezla jsem ROLE_STUDENT");
            }
       
       
       return student;
   }
}
