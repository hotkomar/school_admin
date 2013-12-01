/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.teacher;

/**
 *
 * @author Maru
 */
public class ResultTestForm {
   private String testResultDate;
   private Short mark;
   private Double percent;
   private Long id;
   private Boolean actualMark;
   private Long idStudent;

    public String getTestResultDate() {
        return testResultDate;
    }

    public void setTestResultDate(String testResultDate) {
        this.testResultDate = testResultDate;
    }

    public Short getMark() {
        return mark;
    }

    public void setMark(Short mark) {
        this.mark = mark;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActualMark() {
        return actualMark;
    }

    public void setActualMark(Boolean actualMark) {
        this.actualMark = actualMark;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }
   
   
}
