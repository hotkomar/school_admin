/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.valid;

import cz.cvut.hotkomar.service.checkAndMake.CheckForm;
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
public class TestpassMatchValidator implements ConstraintValidator<TestpassMatch, Object> {
 private String passV;
 private CheckForm checkForm;
@Autowired
    public void setCheckForm(CheckForm checkForm) {
        this.checkForm = checkForm;
    }
 @Override
    public void initialize(final TestpassMatch constraintAnnotation) {
        
        passV=constraintAnnotation.pass();
    }
 @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final String passObj = BeanUtils.getProperty(value, passV);
            boolean uniqueTestPass = checkForm.uniqueTestPass(passObj);
            return uniqueTestPass;
            
        } catch (final Exception ignore) {
            ignore.printStackTrace();
        }
       return true;
    }
}
