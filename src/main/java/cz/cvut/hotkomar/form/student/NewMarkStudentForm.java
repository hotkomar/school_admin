/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.student;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maru
 */
public class NewMarkStudentForm {
    
    private Long id;
    private String login;
    private String name;
    private String surname;
    private Short classified;
    private Short mark;
     

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Short getClassified() {
        return classified;
    }

    public void setClassified(Short classified) {
        this.classified = classified;
    }

    public Short getMark() {
        return mark;
    }

    public void setMark(Short mark) {
        this.mark = mark;
    }

    
     
     
}
