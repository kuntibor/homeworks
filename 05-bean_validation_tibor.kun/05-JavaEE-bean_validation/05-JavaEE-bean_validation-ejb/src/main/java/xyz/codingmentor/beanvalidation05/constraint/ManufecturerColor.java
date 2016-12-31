package xyz.codingmentor.beanvalidation05.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author teiep
 */
@Constraint(validatedBy = ManufecturerColorValidator.class)
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface ManufecturerColor {

    String message() default "{ManufecturerColor.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
