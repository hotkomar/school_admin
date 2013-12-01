/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author Marie Hotkova
 */
@Entity
@Table(name="STUDENT")
@Indexed(index = "Student_Entity_Index")
public class Student extends AbstractUser{
    // id class which student is member
    @ManyToOne//(fetch = FetchType.EAGER)
    private StudentClass id_class;
    //student's mother name
    private String motherName;
    //student's mother surname
    private String motherSurname;
    // student's father name
    private String fatherName;
    //student's father surname
    private String fatherSurname;
    //student's mother's phone number
    private String motherPhone;
    // student's father's pohone number
    private String fatherPhone;
    private String motherMail;
    private String fatherMail;
    

    /**
     *
     * @return
     */
    public StudentClass getId_class() {
        return id_class;
    }

    /**
     *
     * @param id_class
     */
    public void setId_class(StudentClass id_class) {
        this.id_class = id_class;
    }

    /**
     *
     * @return
     */
    public String getMotherName() {
        return motherName;
    }

    /**
     *
     * @param motherName
     */
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    /**
     *
     * @return
     */
    public String getMotherSurname() {
        return motherSurname;
    }

    /**
     *
     * @param motherSurname
     */
    public void setMotherSurname(String motherSurname) {
        this.motherSurname = motherSurname;
    }

    /**
     *
     * @return
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     *
     * @param fatherName
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    /**
     *
     * @return
     */
    public String getFatherSurname() {
        return fatherSurname;
    }

    /**
     *
     * @param fatherSurname
     */
    public void setFatherSurname(String fatherSurname) {
        this.fatherSurname = fatherSurname;
    }

    /**
     *
     * @return
     */
    public String getMotherPhone() {
        return motherPhone;
    }

    /**
     *
     * @param motherPhone
     */
    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }

    /**
     *
     * @return
     */
    public String getFatherPhone() {
        return fatherPhone;
    }

    /**
     *
     * @param fatherPhone
     */
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
    
    
}
