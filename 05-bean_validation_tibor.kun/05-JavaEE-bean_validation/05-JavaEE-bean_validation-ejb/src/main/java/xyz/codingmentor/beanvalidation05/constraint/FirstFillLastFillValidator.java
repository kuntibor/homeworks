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
        //
    }

    @Override
    public boolean isValid(UserEntity value, ConstraintValidatorContext context) {
        if (value.getFirstName() != null) {
            return value.getLastName() != null;
        } else {
            return value.getLastName() == null;
        }
    }

}
