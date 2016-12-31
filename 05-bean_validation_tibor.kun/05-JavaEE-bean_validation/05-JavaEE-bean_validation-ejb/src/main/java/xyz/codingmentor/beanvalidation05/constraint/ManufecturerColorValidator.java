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
 * @author teiep
 */
public class ManufecturerColorValidator implements ConstraintValidator<ManufecturerColor, DeviceEntity> {

    @Override
    public void initialize(ManufecturerColor constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(DeviceEntity value, ConstraintValidatorContext context) {

        if (null != value.getManufacturer()) {
            if (value.getManufacturer() == APPLE && (value.getColor() == BLACK || value.getColor() == WHITE)) {
                return true;
            } else if (value.getManufacturer() == SAMSUNG && value.getColor() != GREEN) {
                return true;
            }
            return false;
        }
        return false;
    }
}
