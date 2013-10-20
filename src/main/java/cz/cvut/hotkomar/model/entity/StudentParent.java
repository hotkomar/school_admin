/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Marie Hoťková
 */
@Entity
@Table (name="STUDENTPARENT")
public class StudentParent extends AbstractUser{
     @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    /**
     *
     * @return
     */
    public Student getStudent() {
        return student;
    }

    /**
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }
     
}
