/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.admin;

import cz.cvut.hotkomar.service.valid.SubjectMatch;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Marie Hoťková
 */
@SubjectMatch(vFrom = "validFrom",expiration = "expiration", message = "Některé datum není ve správném formátu.")
public class NewSubjectForm {
    
    private Long id;
     @NotEmpty( message = "Jméno nesmí být prázdné.")
    private String name;
     @Pattern(regexp = "^[0-3][0-9].[0-1][0-9].[1-2][0-9][0-9][0-9]$|^{0}$", message = "Datum platnosti musí být ve tvaru den.měsíc.rok .")
    
    private String expiration;
     // @NotEmpty(message = "Datum nesmí být prázdné.")
     @Pattern(regexp = "^[0-3][0-9].[0-1][0-9].[1-2][0-9][0-9][0-9]$|^{0}$", message = "Datum platnosti musí být ve tvaru den.měsíc.rok .")
    private String validFrom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }
    
    
}
