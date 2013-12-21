/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maru
 */
@Component
public class ClassificationMatchValidator implements ConstraintValidator<ClassificationMatch, Object> {

    private String twoClassification;
    private String threeClassification;
    private String fourClassification;
    private String fiveClassification;
    @Override
    public void initialize(final ClassificationMatch constraintAnnotation) {
       twoClassification = constraintAnnotation.two();
       threeClassification= constraintAnnotation.three();
       fourClassification=constraintAnnotation.four();
       fiveClassification=constraintAnnotation.five();
    }

    
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final String twoObj = BeanUtils.getProperty(value,twoClassification);
            final String threeObj = BeanUtils.getProperty(value, threeClassification);
            final String fourObj = BeanUtils.getProperty(value, fourClassification);
            final String fiveObj = BeanUtils.getProperty(value, fiveClassification);
            System.out.println("validace");
          //  return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
            return( twoObj==null && threeObj==null && fourObj==null && fiveObj==null )|| (Short.parseShort(twoObj)> Short.parseShort(threeObj) && Short.parseShort(threeObj)> Short.parseShort(fourObj) && Short.parseShort(fourObj)> Short.parseShort(fiveObj));
        } catch (final Exception ignore) {
            // ignore
        }
        return true;
    }
    
}
