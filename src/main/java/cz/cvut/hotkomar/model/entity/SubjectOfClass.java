/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.Type;

/**
 *
 * @author Marie Hotkova
 */
@Entity
@Table (name="SUBJCECTOFCLASS")
public class SubjectOfClass implements Serializable {

//id subject, which is teached
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Column(name="id_subjectOfClass")
private Long id;
@ManyToOne(fetch= FetchType.EAGER)
private Subject id_subject;
//id class, where is subject teached
@ManyToOne(fetch= FetchType.EAGER)
private StudentClass id_class;
//id teacher who teach subject
@ManyToOne(fetch= FetchType.EAGER)
private Teacher id_teacher;
//when admin delete subject of class, visible will be false
@Type(type="true_false")
private Boolean visible;
@Version
private Integer version;

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
    public Subject getId_subject() {
        return id_subject;
    }

    /**
     *
     * @param id_subject
     */
    public void setId_subject(Subject id_subject) {
        this.id_subject = id_subject;
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

}
