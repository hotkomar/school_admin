/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form;

import cz.cvut.hotkomar.service.valid.FieldMatch;
import cz.cvut.hotkomar.service.valid.TestpassMatch;
import javax.print.DocFlavor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Maru
 */
@FieldMatch(first = "newPass", second = "newPass2", message = "Hesla musí být stejná.")
@TestpassMatch(pass = "newPass", message = "Toto heslo už je přiřazené jinému testu")
public class ChangePassForm {
 
    @NotEmpty(message ="Helso musí být vyplněno")
    private String actualPass;
    @NotEmpty(message ="Helso musí být vyplněno")
    private String newPass;
    @NotEmpty(message ="Helso musí být vyplněno")
    private String newPass2;
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
}
