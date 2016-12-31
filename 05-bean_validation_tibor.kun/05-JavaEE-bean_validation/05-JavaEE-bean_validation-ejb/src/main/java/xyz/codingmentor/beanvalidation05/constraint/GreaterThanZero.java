package xyz.codingmentor.beanvalidation05.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

/**
 *
 * @author teiep
 */
@NotNull
@Constraint(validatedBy = GreaterThanZeroValidator.class)
@Target({ElementType.FIELD})
@Retention(RUNTIME)
public @interface GreaterThanZero {

    String message() default "{GreaterThanZero.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
