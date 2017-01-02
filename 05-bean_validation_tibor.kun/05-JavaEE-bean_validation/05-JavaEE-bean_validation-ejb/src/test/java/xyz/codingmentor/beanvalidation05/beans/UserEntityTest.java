package xyz.codingmentor.beanvalidation05.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 *
 * @author teiep
 */
public class UserEntityTest {

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
    public void userNameNotNull() {
        String username = "Tibor1";
        UserEntity user = new UserEntity(username, "aaAA11", "tibor@gmail.com");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void userNameNull() {
        UserEntity user = new UserEntity(null, "aaAA11", "tibor@gmail.com");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void rightUsername() {
        String rightUsername = "Tibor1";
        UserEntity user = new UserEntity(rightUsername, "aaAA11", "tibor@gmail.com");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongUserNameFewCharacter() {
        String wrongSizeUsername = "1";
        UserEntity user = new UserEntity(wrongSizeUsername, "aaAA11", "tibor@gmail.com");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void rightPassword() {
        String rightPassword = "aA12.,";
        UserEntity user = new UserEntity("Tibor1", rightPassword, "tibor@gmail.com");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongPasswordFewCharacter() {
        String wrongPassword = "aA12.";
        UserEntity user = new UserEntity("Tibor1", wrongPassword, "tibor@gmail.com");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongPassword, violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Password.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void wrongPasswordNoCapital() {
        String wrongPassword = "aa12.,";
        UserEntity user = new UserEntity("Tibor1", wrongPassword, "tibor@gmail.com");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongPassword, violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Password.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void wrongPasswordNoNumberOrSpecialChar() {
        String wrongPassword = "aAaAaA";
        UserEntity user = new UserEntity("Tibor1", wrongPassword, "tibor@gmail.com");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongPassword, violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Password.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void rightAddress() {
        String rightAddress = "1234, Budapest, Petőfi út 1";
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setAddress(rightAddress);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongAddress() {
        String wrongAddress = "aaaa, Budapest, Petőfi út 1";
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setAddress(wrongAddress);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongAddress, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void rightPhone() {
        String rightPhone = "06201234567";
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setPhone(rightPhone);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongPhone() {
        String wrongPhone = "07201234567";
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setPhone(wrongPhone);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongPhone, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void rightEmail() {
        String rightEmail = "tibor@gmail.com";
        UserEntity user = new UserEntity("Tibor1", "aaAA11", rightEmail);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongEmail() {
        String wrongEmail = "ti@bor@gmail.com";
        UserEntity user = new UserEntity("Tibor1", "aaAA11", wrongEmail);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongEmail, violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Email.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void rightRegistrationDatePast() {
        Calendar rightRegDate = Calendar.getInstance();
        rightRegDate.add(Calendar.SECOND, -2);
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setRegistrationDate(rightRegDate.getTime());
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongRegistrationDateFuture() {
        Calendar wrongRegDate = Calendar.getInstance();
        wrongRegDate.add(Calendar.SECOND, 2);
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setRegistrationDate(wrongRegDate.getTime());
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongRegDate.getTime(), violations.iterator().next().getInvalidValue());
    }

    @Test
    public void rightLastModifiedDatePast() {
        Calendar rightRegDate = Calendar.getInstance();
        rightRegDate.add(Calendar.SECOND, -2);
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setLastModifiedDate(rightRegDate.getTime());
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongLastModifiedDateFuture() {
        Calendar wrongRegDate = Calendar.getInstance();
        wrongRegDate.add(Calendar.SECOND, 2);
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setLastModifiedDate(wrongRegDate.getTime());
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongRegDate.getTime(), violations.iterator().next().getInvalidValue());
    }

    @Test
    public void rightFirstNameLastNameBothNull() {
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setFirstName(null);
        user.setLastName(null);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void rightFirstNameLastNameBothFill() {
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setFirstName("Tibor");
        user.setLastName("Kun");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongFirstNameLastNametFill() {
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        user.setFirstName("Tibor");
        String wrongLastName = null;
        user.setLastName(wrongLastName);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{FirstFillLastFill.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void rightRegistrationDateAfterDateOfBirth() {
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -20);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        calendar.add(Calendar.YEAR, 19);
        Date registrationDate = calendar.getTime();
        user.setRegistrationDate(registrationDate);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void wrongRegistrationDateBeforeDateOfBirth() {
        UserEntity user = new UserEntity("Tibor1", "aaAA11", "tibor@gmail.com");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -20);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        calendar.add(Calendar.YEAR, -1);
        Date registrationDate = calendar.getTime();
        user.setRegistrationDate(registrationDate);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{DateOfBirthBeforeRegistrationDate.message}", violations.iterator().next().getMessageTemplate());
    }
}
