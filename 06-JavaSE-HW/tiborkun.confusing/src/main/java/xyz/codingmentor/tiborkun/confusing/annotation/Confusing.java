package xyz.codingmentor.tiborkun.confusing.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author teiep
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.METHOD,
    ElementType.TYPE}
)
@Documented
public @interface Confusing {

    String author() default "Troll";

    String message() default "need to recode";
}
