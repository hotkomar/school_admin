/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Type;

/**
 *
 * @author Marie Hotkova
 */
@Entity
@Table (name="TEACHER")
public class Teacher extends AbstractUser{
   //the highest degree, which teacher has
    private String degree;
    //if teacher is education consultant, education_consultant is true
    @Type(type = "true_false")
    private Boolean education_consultant;
    //hourly wage of a teacher
    private Short wagePerHour;
    //highest education, which teacher has
    private String education;
    // date when the teacher was employed
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date_of_employ;
    @OneToMany(mappedBy = "id_teacher", targetEntity = SubjectOfClass.class, fetch = FetchType.EAGER)
    private List<SubjectOfClass> subjClass;
    @OneToOne(mappedBy="id_teacher",fetch = FetchType.EAGER)
    private StudentClass id_class;

    /**
     *
     * @return
     */
    public String getDegree() {
        return degree;
    }

    /**
     *
     * @param degree
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     *
     * @return
     */
    public Boolean getEducation_consultant() {
        return education_consultant;
    }

    /**
     *
     * @param education_consultant
     */
    public void setEducation_consultant(Boolean education_consultant) {
        this.education_consultant = education_consultant;
    }

    /**
     *
     * @return
     */
    public Short getWagePerHour() {
        return wagePerHour;
    }

    /**
     *
     * @param wagePerHour
     */
    public void setWagePerHour(Short wagePerHour) {
        this.wagePerHour = wagePerHour;
    }

    /**
     *
     * @return
     */
    public String getEducation() {
        return education;
    }

    /**
     *
     * @param education
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     *
     * @return
     */
    public Calendar getDate_of_employ() {
        return date_of_employ;
    }

    /**
     *
     * @param date_of_employ
     */
    public void setDate_of_employ(Calendar date_of_employ) {
        this.date_of_employ = date_of_employ;
    }

    /**
     *
     * @return
     */
    public List<SubjectOfClass> getSubjClass() {
        return subjClass;
    }

    /**
     *
     * @param subjClass
     */
    public void setSubjClass(List<SubjectOfClass> subjClass) {
        this.subjClass = subjClass;
    }

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
 
}
