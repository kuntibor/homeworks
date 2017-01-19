package xyz.codingmentor.tiborkun.webshop.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import xyz.codingmentor.tiborkun.webshop.entities.UserEntity;

/**
 *
 * @author Tibor Kun
 */
public class DateOfBirthBeforeRegistrationDateValidator implements ConstraintValidator<DateOfBirthBeforeRegistrationDate, UserEntity> {

    @Override
    public void initialize(DateOfBirthBeforeRegistrationDate constraintAnnotation) {
        //nothing to initialize
    }

    @Override
    public boolean isValid(UserEntity value, ConstraintValidatorContext context) {
        if (value.getDateOfBirth() != null) {
            return value.getDateOfBirth().before(value.getRegistrationDate());
        } else {
            return true;
        }
    }

}
