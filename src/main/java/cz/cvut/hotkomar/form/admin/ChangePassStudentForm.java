/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.admin;

import cz.cvut.hotkomar.service.valid.FieldMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Marie Hoťková
 */
@FieldMatch(first = "password", second = "password2", message = "Hesla musí být stejná.")
public class ChangePassStudentForm {
    @NotEmpty(message = "Heslo nesmí být prázdné")
    private String adminPass;
    
    @NotEmpty(message = "Heslo nesmí být prázdné")
    @Pattern(regexp = "^^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(.){5,255}$", message = "Heslo musí mít minimálně 5 znaků a musí obsahovat minimálně jedno číslo, jedno velké a jedno malé písmeno.")
    private String password;
    @NotEmpty(message = "Heslo nesmí být prázdné")
    @Pattern(regexp = "^^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(.){5,255}$", message = "Heslo musí mít minimálně 5 znaků a musí obsahovat minimálně jedno číslo, jedno velké a jedno malé písmeno.")
    private String password2;
    private Long id;

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
