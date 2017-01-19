package xyz.codingmentor.tiborkun.webshop.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity;
import static xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity.Color.BLACK;
import static xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity.Color.GREEN;
import static xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity.Color.WHITE;

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
