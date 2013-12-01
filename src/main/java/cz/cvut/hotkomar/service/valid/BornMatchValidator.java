/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.valid;

import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maru
 */ 
@Component
public class BornMatchValidator implements ConstraintValidator<BornMatch, Object>{
    private String born;
    private DateFunction dateFunction;    
    @Autowired
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }
   

    @Override
    public void initialize(final BornMatch constraintAnnotation) {
     born = constraintAnnotation.born();
        
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
       
        try {
            final Object bornValid =BeanUtils.getProperty(value, born);
            System.out.println("born je"+bornValid);
            boolean validDate=false;
            String bornDay =(String)bornValid;
            System.out.println("born day je *"+bornDay+"* a délka je "+bornDay.length());
            if(bornDay.length()>0){
            validDate = dateFunction.validDate(bornDay);
            System.out.println("validace "+validDate);}
            return !validDate;
        } catch (final Exception ignore) {
            System.out.println("*******************************************************");
            ignore.printStackTrace();
            System.out.println("*******************************************************");
        }
        System.out.println("*******************************************************");
        System.out.println("**************validace neprošla*************************");
        System.out.println("*******************************************************");
        return true;
    }
}
