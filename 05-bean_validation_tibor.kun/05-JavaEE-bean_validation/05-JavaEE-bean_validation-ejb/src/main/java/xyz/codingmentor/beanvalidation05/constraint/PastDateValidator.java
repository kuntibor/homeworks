package xyz.codingmentor.beanvalidation05.constraint;

import java.util.Calendar;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author teiep
 */
public class PastDateValidator implements ConstraintValidator<PastDate, Date> {

    @Override
    public void initialize(PastDate constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (null != value) {
            return value.compareTo(Calendar.getInstance().getTime()) < 0;
        }
        return false;
    }

}
