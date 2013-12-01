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
    private Long id;
    private List<ViewQuestionForm> questions=new ArrayList<ViewQuestionForm>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ViewQuestionForm> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ViewQuestionForm> questions) {
        this.questions = questions;
    }
    
}
