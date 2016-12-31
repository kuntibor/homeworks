package xyz.codingmentor.beanvalidation05.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;

/**
 *
 * @author teiep
 */
public class DateOfBirthEarlierThanRegistrationDateValidator implements ConstraintValidator<DateOfBirthEarlierThanRegistrationDate, UserEntity> {

    @Override
    public void initialize(DateOfBirthEarlierThanRegistrationDate constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(UserEntity value, ConstraintValidatorContext context) {
        if (value.getDateOfBirth() != null) {
            return value.getDateOfBirth().compareTo(value.getRegistrationDate()) < 0;
        } else {
            return true;
        }
    }

}
