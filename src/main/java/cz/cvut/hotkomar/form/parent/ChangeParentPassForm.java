/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.parent;

import cz.cvut.hotkomar.service.valid.FieldMatch;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Maru
 */
@FieldMatch(first = "newPass", second = "newPass2", message = "Hesla musí být stejná.")
public class ChangeParentPassForm {
   
    
    private Long id;
    @NotEmpty(message = "Aktuální heslo musí být vyplněno.")
    private String actualPass;
    @NotEmpty(message = "Nové heslo musí být vyplněno.")
    @Pattern(regexp = "^^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(.){5,255}$", message = "Heslo musí mít minimálně 5 znaků a musí obsahovat minimálně jedno číslo, jedno velké a jedno malé písmeno.")
    private String newPass;
    @NotEmpty(message = " heslo musí být vyplněno.")
    @Pattern(regexp = "^^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(.){5,255}$", message = "Heslo musí mít minimálně 5 znaků a musí obsahovat minimálně jedno číslo, jedno velké a jedno malé písmeno.")
    private String newPass2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActualPass() {
        return actualPass;
    }

    public void setActualPass(String actualPass) {
        this.actualPass = actualPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getNewPass2() {
        return newPass2;
    }

    public void setNewPass2(String newPass2) {
        this.newPass2 = newPass2;
    }
    
    
}
