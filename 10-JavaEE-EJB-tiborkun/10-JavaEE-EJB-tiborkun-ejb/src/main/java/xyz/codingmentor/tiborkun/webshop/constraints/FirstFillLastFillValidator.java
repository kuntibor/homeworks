package xyz.codingmentor.tiborkun.webshop.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import xyz.codingmentor.tiborkun.webshop.entities.UserEntity;

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
