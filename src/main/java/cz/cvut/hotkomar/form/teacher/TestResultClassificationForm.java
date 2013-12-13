/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.teacher;

import cz.cvut.hotkomar.model.entity.Teacher;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maru
 */
public class TestResultClassificationForm {
    private Long id;
    private String testName;
    private String mark;
    private Teacher teacher;
    private Teacher changeMark;
    private Boolean webTest;
    private String dateMark;
 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDateMark() {
        return dateMark;
    }

    public void setDateMark(String dateMark) {
        this.dateMark = dateMark;
    }

    public Boolean getWebTest() {
        return webTest;
    }

    public void setWebTest(Boolean webTest) {
        this.webTest = webTest;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getChangeMark() {
        return changeMark;
    }

    public void setChangeMark(Teacher changeMark) {
        this.changeMark = changeMark;
    }

  
    
    
    
}
