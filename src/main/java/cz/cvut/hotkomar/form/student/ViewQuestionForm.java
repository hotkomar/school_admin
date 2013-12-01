/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.student;

import cz.cvut.hotkomar.form.teacher.AnswerForm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maru
 */
public class ViewQuestionForm {
    private Long id;
    private String question;
    private Integer points;
   private List<ViewAnswerForm> answers= new ArrayList<ViewAnswerForm>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<ViewAnswerForm> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ViewAnswerForm> answer) {
        this.answers = answer;
    }
    
    
    
}
