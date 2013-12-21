/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.student;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maru
 */
public class ViewTestForm {
    private String name;
   private Long idTest;
   private Long idStudent;
    private List<ViewQuestionForm> questions=new ArrayList<ViewQuestionForm>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public List<ViewQuestionForm> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ViewQuestionForm> questions) {
        this.questions = questions;
    }

    public Long getIdTest() {
        return idTest;
    }

    public void setIdTest(Long idTest) {
        this.idTest = idTest;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }
    
}
