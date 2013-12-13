/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import org.hibernate.annotations.Type;

/**
 *
 * @author Maru
 */
@Entity
@Table(name="TESTRESULT")
public class TestResult implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_testRestul")
    private Long id;
    @ManyToOne()
    private Test test;
    private Double percent;
    @ManyToOne()
    private Student student;
    @Type(type = "true_false")
    private Boolean visible;
    @Version
    private Integer version;
    private Short mark;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar testDate;
    @Type(type = "true_false")
    private Boolean actualMark;
    @Type(type = "true_false")
    private Boolean webTest;
    @ManyToOne()
    private Teacher teacher;
    private Short classified;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Short getMark() {
        return mark;
    }

    public void setMark(Short mark) {
        this.mark = mark;
    }

    public Calendar getTestDate() {
        return testDate;
    }

    public void setTestDate(Calendar testDate) {
        this.testDate = testDate;
    }

    public Boolean getActualMark() {
        return actualMark;
    }

    public void setActualMark(Boolean actualMark) {
        this.actualMark = actualMark;
    }

    public void setWebTest(Boolean webTest) {
        this.webTest = webTest;
    }

    public Boolean getWebTest() {
        return webTest;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Short getClassified() {
        return classified;
    }

    public void setClassified(Short classified) {
        this.classified = classified;
    }

    
    
    
    
}
