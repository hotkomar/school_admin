/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.teacher;


import cz.cvut.hotkomar.service.valid.ClassificationMatch;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Maru
 */
@ClassificationMatch(two="two", three="three", four="four", five="five", message = "Tabulka klasifikace byla špatně vyplněna.")
public class EditTestForm implements Serializable {
    private Long id;
   
    private Long id_teacher;
  
    private Long id_subject;
 
    //name of test
    @NotEmpty(message ="Jméno musí být vyplněno.")
    private String name;
    //when teacher delete test, visibility will be false
  
    private Boolean visible;
    @Valid
    private List<EditQuestionForm> questions = new ArrayList<EditQuestionForm>();

    private Boolean taught;
    
    
    @Min(value = 4, message = "minimální počet procent je 4")
    @Max(value = 99, message = "maximální počet procent je 99")
    @NotNull(message = "Nesmí zůstat prázdné.")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Short two;
    @Min(value = 3, message = "minimální počet procent je 3")
    @Max(value = 98, message = "maximální počet procent je 98")
    @NotNull(message = "Nesmí zůstat prázdné.")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Short three;
    @Min(value = 2, message = "minimální počet procent je 2")
    @Max(value = 97, message = "maximální počet procent je 97")
    @NotNull(message = "Nesmí zůstat prázdné.")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Short four;
    @Min(value = 1, message = "minimální počet procent je 1")
    @Max(value = 96, message = "maximální počet procent je 96")
    @NotNull(message = "Nesmí zůstat prázdné.")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Short five;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(Long id_teacher) {
        this.id_teacher = id_teacher;
    }

    public Long getId_subject() {
        return id_subject;
    }

    public void setId_subject(Long id_subject) {
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

    public List<EditQuestionForm> getQuestions() {
        return questions;
    }

    public void setQuestions(List<EditQuestionForm> questions) {
        this.questions = questions;
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
