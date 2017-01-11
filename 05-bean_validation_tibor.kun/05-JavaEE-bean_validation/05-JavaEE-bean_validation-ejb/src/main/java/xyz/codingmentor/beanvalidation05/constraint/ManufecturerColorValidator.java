package xyz.codingmentor.beanvalidation05.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Color.BLACK;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Color.GREEN;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Color.WHITE;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Manufacturer.APPLE;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Manufacturer.SAMSUNG;

/**
 *
 * @author Tibor Kun
 */
public class ManufecturerColorValidator implements ConstraintValidator<ManufecturerColor, DeviceEntity> {

    @Override
    public void initialize(ManufecturerColor constraintAnnotation) {
        //nothing to initialize
    }

    @Override
    public boolean isValid(DeviceEntity value, ConstraintValidatorContext context) {
        switch (value.getManufacturer()) {
            case APPLE: return value.getColor() == BLACK || value.getColor() == WHITE;
            case SAMSUNG: return value.getColor() != GREEN;
            default: return true;
        }
    }
}
