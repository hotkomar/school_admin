/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.teacher;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Maru
 */
public class QuestionForm {
    private Long id;
    @NotEmpty(message = "Musíte zadat otázku.")
    @Size(max=1024,message="Otázka může obsahovat maximálně 1024 znaků")
    private String question;
   @NotNull(message = "Musíte vyplnit počet bodů.")
    @Min(value = 1, message = "Minimální počet bodů za otázku je 1")
    @Max(value = 100, message = "Maximální počet bodů za otázku je 99")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Integer points;
 @Valid
    private List<AnswerForm> answers= new ArrayList<AnswerForm>();
 
   @AssertTrue(message = "Každá otázka musí mít odpověď")
 private Boolean isAnswerEmpty()
 {
     if(answers.isEmpty())
     {
         return false;
     }
     return true;
 }
    private Boolean visible;

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

    public List<AnswerForm> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerForm> answers) {
        this.answers = answers;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    
    
}
