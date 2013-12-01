/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller.admin;

import cz.cvut.hotkomar.controller.AdminControllerImp;
import cz.cvut.hotkomar.form.Form;
import cz.cvut.hotkomar.form.admin.FullTextForm;
import cz.cvut.hotkomar.form.admin.NewStudentForm;
import cz.cvut.hotkomar.form.admin.NewSubjectForm;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import cz.cvut.hotkomar.service.manager.SubjectOfClassMan;
import cz.cvut.hotkomar.service.message.FormMessage;
import cz.cvut.hotkomar.service.pagination.Pagination;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Marie Hotkova
 */

@Controller
public class SubjectCon implements AdminControllerImp{
//    @RequestMapping (value="/admin/subjects.htm")
//    public String teachersGet (ModelMap m)
//    {
//        m.addAttribute("subject",true);
//        return "/admin/subject/viewSubject";
//    } 
    
private SubjectMan subjectMan;
private SubjectOfClassMan subjectOfClassMan;
private Pagination pagination;
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
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }


@Autowired
    public SubjectOfClassMan setSubjectOfClassMan() {
        return subjectOfClassMan;
    }
@Autowired
    public void setSubjectOfClassMan(SubjectOfClassMan subjectOfClassMan) {
        this.subjectOfClassMan = subjectOfClassMan;
    }


   
@Autowired
    public void setSubjectMan(SubjectMan subjectMan) {
        this.subjectMan = subjectMan;
    }

    @Override
    @RequestMapping (value="/admin/subjects.htm")
    public String view(@RequestParam(defaultValue ="1", value ="page", required = false)Integer page, ModelMap m, HttpSession session) {
        String attribute =(String)session.getAttribute("subjectFullText");
        
        List<Subject>  list= null;
        if(attribute==null){
        list = subjectMan.findAll();
        }else{
            if(!attribute.isEmpty())            
            {
       list = subjectMan.findFullText(attribute);
        System.out.println("finfFullText"+list);
            }
            else{
                list = subjectMan.findAll();
            }
        }
        
        FullTextForm fullTextForm = new FullTextForm();
        fullTextForm.setFullText(attribute);
         pagination.setList(list);
        m.addAttribute("subjects",list);
        m.addAttribute("subject",true);
        m.addAttribute("listOfClass",pagination.paginationList(list, page));
        m.addAttribute("pageForm",pagination.getPageForm());
        m.addAttribute("form",fullTextForm);
        return "/admin/subject/viewSubject";
    }
    @RequestMapping (value="/admin/subjectFullText.htm")
    public String viewPOST(@ModelAttribute("form") FullTextForm form, BindingResult errors, HttpSession session) {
       session.setAttribute("subjectFullText", form.getFullText());
               
        return "redirect:subjects.htm";
    }
    
    @Override
    @RequestMapping (value = "/admin/addSubject.htm")
    public String addGET(ModelMap m) {
    String dateString = dateFunction.getDateString(Calendar.getInstance());
    NewSubjectForm form = new NewSubjectForm();
    form.setValidFrom(dateString);
        m.addAttribute("form",form);
        m.addAttribute("subject",true);
        return "/admin/subject/addSubject";
    }

   @RequestMapping(value = "/admin/adminNewSubject.htm")
    public String addPOST(@Valid@ModelAttribute("form") NewSubjectForm form, BindingResult errors, ModelMap m) {
       if(errors.hasErrors())
       {
           m.addAttribute("form",form);
        m.addAttribute("subject",true);
        return "/admin/subject/addSubject";
       }
    Subject subject = new Subject();
    Subject formToSubject = formToSubject(form, subject);
     subjectMan.add(formToSubject, true);
     return"redirect:subjects.htm";
    }

    @Override
    @RequestMapping(value = "/admin/infoSubject.htm")
    public String info(@RequestParam(value = "id", required = true)Long id, ModelMap m) {
        Subject findById = subjectMan.findById(id);
        if(findById==null)
        {
            //chyba
        }
    List<SubjectOfClass> findByIdClass = subjectOfClassMan.findByIdClass(id);
        System.out.println("počet tříd je "+findByIdClass );
        m.addAttribute("classes",findByIdClass);
        m.addAttribute("subjects",findById);
        m.addAttribute("subject",true);
       
        return"/admin/subject/infoSubject";
    }

    @Override
    @RequestMapping(value = "/admin/editSubject.htm")
    public String editGET(@RequestParam(value = "id", required = true)Long id, ModelMap m) {
        Subject findById = subjectMan.findById(id);
        if(findById==null)
        {
            //chyba
        }
    NewSubjectForm subjectToForm = subjectToForm( new NewSubjectForm(), findById);
        m.addAttribute("form",subjectToForm);
        m.addAttribute("subject",true);
        return"/admin/subject/editSubject";
    }

    @RequestMapping(value="/admin/adminEditSubject.htm")
    public String editPOST(@Valid@ModelAttribute("form") NewSubjectForm form, BindingResult errors, ModelMap m) {
        if(errors.hasErrors())
        {
            m.addAttribute("form",form);
        m.addAttribute("subject",true);
        return"/admin/subject/editSubject";
        }
    Subject findById = subjectMan.findById(form.getId());
    if(findById==null)
    {
        //chyba
    }
    Subject formToSubject = formToSubject(form, findById);
    subjectMan.edit(findById, true);
        return"redirect:infoSubject.htm?id="+form.getId();
    }

    @Override
    @RequestMapping(value="/admin/removeSubject.htm")
    public String remove(@RequestParam(value = "id", required = true)Long id,@RequestParam(value = "page", required=false) Integer page) {
        Subject findById = subjectMan.findById(id);
        if(findById==null)
        {
          return"/admin/errorHups";
        }
        if(!subjectOfClassMan.findByIdSubject(id).isEmpty())
        {
            message.setNegativeMes("Předmět nemůže být smazát, když je vyučován");
            return"redirect:infoSubject.htm?id="+id;
        }
       subjectMan.visible(id, true);
       return"redirect:subjects.htm?page="+page;
    }
    @RequestMapping(value = "/admin/removeS.htm")
public String removeClass (@RequestParam(value="id", required = true)Long id, @RequestParam(value="subject", required = true)Long subject)
    {
    SubjectOfClass findById = subjectOfClassMan.findById(id);
    if(findById==null)
    {
     return"admin/errorHups";   
    }
    subjectOfClassMan.delete(findById);
        return "redirect:infoSubject.htm?id="+subject;
    }
    private Subject formToSubject(NewSubjectForm form, Subject subject)
{
   // subject.setExpiration();
    subject.setName(form.getName());
    if(form.getValidFrom().isEmpty())
    {
        subject.setValidFrom(Calendar.getInstance());
    }
    else
    {
        String[] splitDataString = dateFunction.splitDataString(form.getValidFrom());
        Calendar setDate = dateFunction.setDate(Integer.parseInt(splitDataString[0]),Integer.parseInt(splitDataString[1]),Integer.parseInt(splitDataString[2]));
        subject.setValidFrom(setDate);
        
    }
    if(!form.getExpiration().isEmpty())
    {
        String[] splitDataString = dateFunction.splitDataString(form.getExpiration());
        Calendar setDate = dateFunction.setDate(Integer.parseInt(splitDataString[0]),Integer.parseInt(splitDataString[1]),Integer.parseInt(splitDataString[2]));
        subject.setExpiration(setDate);
    }
    //subject.setValidFrom(null);
    subject.setVisible(Boolean.TRUE);
    return subject;
}
    private NewSubjectForm subjectToForm (NewSubjectForm form,Subject subject)
    {
        form.setName(subject.getName());
//        form.setExpiration(Long.MIN_VALUE);
//        form.setValidFrom(Long.MIN_VALUE);
       form.setId(subject.getId());
        return form;
    }
}
