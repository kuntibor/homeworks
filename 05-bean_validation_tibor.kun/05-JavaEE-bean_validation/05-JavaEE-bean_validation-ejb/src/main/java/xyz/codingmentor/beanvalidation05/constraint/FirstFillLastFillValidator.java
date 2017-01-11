package xyz.codingmentor.beanvalidation05.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;

/**
 *
 * @author Tibor Kun
 */
public class FirstFillLastFillValidator implements ConstraintValidator<FirstFillLastFill, UserEntity> {

    @Override
    public void initialize(FirstFillLastFill constraintAnnotation) {
        //nothing to initialize 
    }

    @Override
    public boolean isValid(UserEntity value, ConstraintValidatorContext context) {
        boolean bothFill = value.getFirstName() != null && value.getLastName() != null;
        boolean bothNull = value.getFirstName() == null && value.getLastName() == null;
        return bothNull || bothFill;
    }

}
