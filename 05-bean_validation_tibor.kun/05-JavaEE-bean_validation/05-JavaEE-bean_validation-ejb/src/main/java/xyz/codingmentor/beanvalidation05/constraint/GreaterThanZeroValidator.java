package xyz.codingmentor.beanvalidation05.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author teiep
 */
public class GreaterThanZeroValidator implements ConstraintValidator<GreaterThanZero, Double> {

    @Override
    public void initialize(GreaterThanZero constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (null != value) {
            return value > 0;
        }
        return false;
    }

}
