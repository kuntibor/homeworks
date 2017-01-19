package xyz.codingmentor.tiborkun.webshop.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Tibor Kun
 */
@Constraint(validatedBy = ManufecturerColorValidator.class)
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface ManufecturerColor {

    String message() default "{ManufecturerColor.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
