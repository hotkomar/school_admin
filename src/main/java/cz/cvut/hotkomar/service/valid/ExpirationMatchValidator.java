/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.valid;

import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import java.util.Calendar;
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
public class ExpirationMatchValidator implements ConstraintValidator<ExpirationMatch, Object> {
    private String expiration;
    private DateFunction dateFunction;    
    @Autowired
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }
   

    @Override
    public void initialize(final ExpirationMatch constraintAnnotation) {
     expiration = constraintAnnotation.expirationDate();
        
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
       
        try {
            final String expirationValid =BeanUtils.getProperty(value,expiration);
            if(expirationValid!=null && !expirationValid.isEmpty()){
            Calendar calendar = Calendar.getInstance();
            boolean validDate = dateFunction.validDate(expirationValid);
            if(!validDate)
            {
                return false;
            }
                else{
            String[] split = dateFunction.splitDataString(expirationValid);
                System.out.println("expiraton "+expirationValid);
            Calendar setDBDate = dateFunction.setDate(Integer.parseInt(split[0]), Integer.parseInt(split[1]),Integer.parseInt(split[2]));
                
            if(calendar.after(setDBDate)  )
            {
            return false;
            
            }
                }
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
