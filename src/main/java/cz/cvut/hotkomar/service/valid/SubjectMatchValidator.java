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
public class SubjectMatchValidator implements ConstraintValidator<SubjectMatch, Object> {

   private String vDate;
   private String expirationDate;
    private DateFunction dateFunction;    
    @Autowired
    public void setDateFunction(DateFunction dateFunction) {
        this.dateFunction = dateFunction;
    }
   

    @Override
    public void initialize(final SubjectMatch constraintAnnotation) {
  vDate=  constraintAnnotation.vFrom();
  expirationDate= constraintAnnotation.expiration();
        
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        System.out.println("validuju");
        try {
            System.out.println("try");
            final String vFromValid =BeanUtils.getProperty(value,vDate);
            System.out.println("vfrom "+vFromValid);
            final String expirationValid=BeanUtils.getProperty(value,expirationDate);
          //  System.out.println("valid je"+validFromValid+" a expiration "+expirationValid);
//            
            boolean vFrom =true;
            boolean expiration=true;
            if(vFromValid.length()>0)
            {
                System.out.println("validace from ");
              vFrom=  dateFunction.isValidDate(vFromValid);
                System.out.println("datum je "+vFrom);
            }
            if(expirationValid.length()>0)
            {
                System.out.println("validace ex");
              expiration=  dateFunction.isValidDate(expirationValid);
            }
            System.out.println("from "+vFrom+" ex "+expiration+" obě "+(vFrom && expiration));
           return vFrom && expiration;
         
            
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
