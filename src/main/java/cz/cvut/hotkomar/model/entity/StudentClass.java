/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

/**
 *
 * @author Marie Hotkova
 */
@Entity
@Table (name="STUDENTCLASS")
public class StudentClass implements Serializable {
    //class registration identification number

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_class")
    private Long id;
    // id of teacher who is class teacher
    @OneToOne//(fetch = FetchType.EAGER)
    private Teacher id_teacher;
    //duration of study
    private Byte numberOfYears;
    //number in the name of the class 
    private Byte nameNumber;
    // year when class was foundation
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "yearOfEnd")
    private Calendar yearOfFoundation;
    //class name
    private String name;
    @Type(type = "true_false")
    private Boolean visible;
    @Version
    private Integer version;

    @OneToMany(mappedBy = "id_class")
    //@LazyCollection(LazyCollectionOption.FALSE)
    private List<Student> students;
    
    @OneToMany(mappedBy = "id_class",targetEntity = SubjectOfClass.class)
    private List<SubjectOfClass> subjects;

    public List<SubjectOfClass> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectOfClass> subjects) {
        this.subjects = subjects;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Teacher getId_teacher() {
        return id_teacher;
    }

    /**
     *
     * @param id_teacher
     */
    public void setId_teacher(Teacher id_teacher) {
        this.id_teacher = id_teacher;
    }

    /**
     *
     * @return
     */
    public Byte getNumberOfYears() {
        return numberOfYears;
    }

    /**
     *
     * @param numberOfYears
     */
    public void setNumberOfYears(Byte numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /**
     *
     * @return
     */
    public Byte getNameNumber() {
        return nameNumber;
    }

    /**
     *
     * @param nameNumber
     */
    public void setNameNumber(Byte nameNumber) {
        this.nameNumber = nameNumber;
    }

    /**
     *
     * @return
     */
    public Calendar getYearOfFoundation() {
        return yearOfFoundation;
    }

    /**
     *
     * @param yearOfFoundation
     */
    public void setYearOfFoundation(Calendar yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     *
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     *
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    @Transient
    public String getYear ()
    {
        if(yearOfFoundation!=null)
        {
            DateFunction dateFunction = new DateFunction();
           return dateFunction.getYear(yearOfFoundation);
        }
        return "";
    }
    
    
}
