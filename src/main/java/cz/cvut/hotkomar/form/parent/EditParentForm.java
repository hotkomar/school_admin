/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.parent;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Maru
 */
public class EditParentForm {
    private String motherName;
    private String motherSurname;
    private String fatherName;
    private String fatherSurname;
    private Long idParent;
    @NotEmpty( message = "Telefonní číslo nesmí být prázdné.")
    private String motherPhone;
    // student's father's pohone number
    private String fatherPhone;
    @NotEmpty(message = "E-mail nesmí být prázdný.")
     @Email(message = "E-mail není ve správném formátu.")
    private String motherMail;
      @Email(message = "E-mail není ve správném formátu.")
    private String fatherMail;

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }

    public String getMotherPhone() {
        return motherPhone;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getMotherMail() {
        return motherMail;
    }

    public void setMotherMail(String motherMail) {
        this.motherMail = motherMail;
    }

    public String getFatherMail() {
        return fatherMail;
    }

    public void setFatherMail(String fatherMail) {
        this.fatherMail = fatherMail;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherSurname() {
        return motherSurname;
    }

    public void setMotherSurname(String motherSurname) {
        this.motherSurname = motherSurname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherSurname() {
        return fatherSurname;
    }

    public void setFatherSurname(String fatherSurname) {
        this.fatherSurname = fatherSurname;
    }
      
      
      
}
