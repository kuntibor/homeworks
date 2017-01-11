package xyz.codingmentor.beanvalidation05.beans;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Color.BLACK;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Color.BLUE;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Color.GREEN;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Color.WHITE;

/**
 *
 * @author Tibor Kun
 */
public class DeviceEntityTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Test
    public void rightIdSize() {
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, "5ss", 100000, DeviceEntity.Color.BLACK, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongIdSize() {
        String wrongId = "1";
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, "5ss", 100000, DeviceEntity.Color.BLACK, 10);
        device.setId(wrongId);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongId, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void rightPrice() {
        Integer rightPrice = 100000;
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, "5ss", rightPrice, DeviceEntity.Color.BLACK, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongPrice() {
        Integer wrongPrice = 0;
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, "5ss", wrongPrice, DeviceEntity.Color.BLACK, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongPrice, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void righType() {
        String rightType = "phone";
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, rightType, 100000, DeviceEntity.Color.BLACK, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongType() {
        String wrongType = "0";
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, wrongType, 100000, DeviceEntity.Color.BLACK, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongType, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void rightColorApple() {
        DeviceEntity.Color rightColor = BLACK;
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, "5ss", 100000, rightColor, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void rightColorSamsung() {
        DeviceEntity.Color rightColor = WHITE;
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, "5ss", 100000, rightColor, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongColorApple() {
        DeviceEntity.Color wrongColor = BLUE;
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, "5ss", 100000, wrongColor, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{ManufecturerColor.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void wrongColorSamsung() {
        DeviceEntity.Color wrongColor = GREEN;
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.APPLE, "5ss", 100000, wrongColor, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{ManufecturerColor.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void rightManufacturer() {
        DeviceEntity device = new DeviceEntity(DeviceEntity.Manufacturer.HTC, "One", 100000, WHITE, 10);
        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(device);
        Assert.assertEquals(0, violations.size());
    }

}
