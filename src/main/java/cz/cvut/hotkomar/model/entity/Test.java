/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.Type;

/**
 *
 * @author Marie Hotkova
 */
@Entity
@Table (name="TEST")

public class Test implements Serializable {
    //class registration identification number

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_test")
    private Long id;
    //id teacher, who made this test
    @ManyToOne()
    private Teacher id_teacher;
    // id subject, which knowladge will be test
    @ManyToOne()
    private Subject id_subject;
    // id class which do test
//   @ManyToOne(fetch= FetchType.EAGER)
//   private StudentClass id_class;
    //name of test
    private String name;
    //when teacher delete test, visibility will be false
    @Type(type = "true_false")
    private Boolean visible;
    @Version
    private Integer version;
    @OrderBy(value = "id")
    @OneToMany()
    private Set<Question> questions = new HashSet<Question>();
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<TestResult> results;
    private String password;
    @Type(type = "true_false")
    private Boolean taught;
    private Short two;
    private Short three;
    private Short four;
    private Short five;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(Teacher id_teacher) {
        this.id_teacher = id_teacher;
    }

    public Subject getId_subject() {
        return id_subject;
    }

    public void setId_subject(Subject id_subject) {
        this.id_subject = id_subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getTaught() {
        return taught;
    }

    public void setTaught(Boolean taught) {
        this.taught = taught;
    }

    public Short getTwo() {
        return two;
    }

    public void setTwo(Short two) {
        this.two = two;
    }

    public Short getThree() {
        return three;
    }

    public void setThree(Short three) {
        this.three = three;
    }

    public Short getFour() {
        return four;
    }

    public void setFour(Short four) {
        this.four = four;
    }

    public Short getFive() {
        return five;
    }

    public void setFive(Short five) {
        this.five = five;
    }
    
    
    
}
