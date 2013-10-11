/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.controller;

import cz.cvut.hotkomar.form.UserTypeForm;
import cz.cvut.hotkomar.model.entity.UserType;
import cz.cvut.hotkomar.service.manager.UserTypeMan;
import java.util.List;
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
    
    @Autowired
    public void setUserTypeMan (UserTypeMan userTypeMan)
    {
        this.userTypeMan=userTypeMan;
    }
    /**
     * view index
     *
     * @param m
     * @return
     */
    @RequestMapping(value = "index.htm")
    public String index2(@RequestParam(value = "name", required = false) String name,ModelMap m ) {

//createStart();
      UserType user = new UserType();
      user.setName(name);
      user.setVisible(Boolean.TRUE);
      userTypeMan.add(user, true);
List<UserType> listUseres = userTypeMan.findAll();
        UserTypeForm prvek = new UserTypeForm();
        prvek.setName(listUseres.get(0).getName()+" "+listUseres.size());
m.addAttribute("prvek",prvek);
m.addAttribute("form",new UserTypeForm());
        return "index";
    }
    //@PostConstruct
    public void createStart()
{   System.out.println("vytvářím uživatele");
    UserType user1 = new UserType();
    user1.setName("TEACHER");
    user1.setVisible(Boolean.TRUE);
    //user1.setVisible(Boolean.TRUE);
    userTypeMan.add(user1, true);
    
    
}
    
}
