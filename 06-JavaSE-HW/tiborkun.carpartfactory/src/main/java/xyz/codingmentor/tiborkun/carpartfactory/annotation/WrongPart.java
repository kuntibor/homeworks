package xyz.codingmentor.tiborkun.carpartfactory.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import xyz.codingmentor.tiborkun.carpartfactory.factory.CarType;

/**
 *
 * @author teiep
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface WrongPart {

    String defect() default "Wrong dimensions";

    CarType madeFor();
}
