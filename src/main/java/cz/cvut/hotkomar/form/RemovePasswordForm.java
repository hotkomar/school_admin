/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Maru
 */
public class RemovePasswordForm {
    private  Long id;
 
  private  String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  
}
