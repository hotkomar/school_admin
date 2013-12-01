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
public class WorkMatchValidator implements ConstraintValidator<WorkMatch, Object>{

    private String workDate;
    private DateFunction dateFunction;    
    @Autowired
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }
   

    @Override
    public void initialize(final WorkMatch constraintAnnotation) {
     workDate = constraintAnnotation.work();
        
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
       
        try {
            final Object workValid =BeanUtils.getProperty(value, workDate);
            System.out.println("work je"+workValid);
            boolean validDate=false;
            String workDay =(String)workValid;
            if(workDay.length()>0){
            validDate = dateFunction.isValidDate(workDay);
            System.out.println("validace "+validDate);
            return validDate;
            }
            
            
            
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
