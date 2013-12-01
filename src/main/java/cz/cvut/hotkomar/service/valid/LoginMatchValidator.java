/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.valid;

import cz.cvut.hotkomar.service.checkAndMake.CheckForm;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LoginMatchValidator implements ConstraintValidator<LoginMatch, Object>{

    private String loginName;
    private CheckForm checkForm;
@Autowired
    public void setCheckForm(CheckForm checkForm) {
        this.checkForm = checkForm;
    }

    
    
    
    @Override
    public void initialize(final LoginMatch constraintAnnotation) {
        
        loginName=constraintAnnotation.login();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final String loginObj = BeanUtils.getProperty(value, loginName);
            if(loginObj.length()>0){
            return checkForm.uniqueLogin(loginObj);
            }
        } catch (final Exception ignore) {
            ignore.printStackTrace();
        }
       return true;
    }
    
}
