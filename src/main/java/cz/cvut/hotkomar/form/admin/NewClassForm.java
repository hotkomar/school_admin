/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.admin;

import cz.cvut.hotkomar.model.entity.Teacher;
import javax.print.DocFlavor;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Marie Hotkova
 */
public class NewClassForm {
    private Long id;
    @NotEmpty( message = "Jméno třídy nesmí být prázdné.")
    private String name;
     @Pattern(regexp = "^[0-9]{1,2}$", message = "Ročník smí obsahovat jen čísla od 0 do 99.")
    private String numberName;
    @Min(value = 1, message = "minimální počet ročníků je 1")
    @Max(value = 99, message = "maximální počet ročníků je 99")
    @NotNull(message = "Musíte zadat počet ročníků")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Byte numberOfYears;
    private Long classHead;

    
    @AssertTrue(message="Číslo ročníku musí být menší celkové délce studia. ")
    private boolean getTest()
    {
        if(numberName!=null && numberOfYears!=null )
        {
            if(Byte.valueOf(numberName) <numberOfYears)
            {
               
                return true;
            }
            
        }
       
        return false;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberName() {
        return numberName;
    }

    public void setNumberName(String numberName) {
        this.numberName = numberName;
    }

    public Byte getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Byte numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Long getClassHead() {
        return classHead;
    }

    public void setClassHead(Long classHead) {
        this.classHead = classHead;
    }
   
}
