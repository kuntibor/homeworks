package xyz.codingmentor.beanvalidation05.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;

/**
 *
 * @author teiep
 */
public class FirstFillLastFillValidator implements ConstraintValidator<FirstFillLastFill, UserEntity> {

    @Override
    public void initialize(FirstFillLastFill constraintAnnotation) {
        //nothing to initialize 
    }

    @Override
    public boolean isValid(UserEntity value, ConstraintValidatorContext context) {
        boolean bothFill = true;
        boolean bothNull = true;
        if (value.getFirstName() != null && value.getLastName() != null) {
            return bothFill;
        }
        if (value.getFirstName() == null && value.getLastName() == null) {
            return bothNull;
        }
        return false;
    }

}
