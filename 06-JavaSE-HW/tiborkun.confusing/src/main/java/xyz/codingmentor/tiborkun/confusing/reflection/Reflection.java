package xyz.codingmentor.tiborkun.confusing.reflection;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.codingmentor.tiborkun.confusing.annotation.Confusing;

/**
 *
 * @author teiep
 */
public class Reflection {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private Reflection() {
        //hide the implicit public constructor
    }

    public static void analyze(final Class<?> clazz) {
        Confusing annotationClass = clazz.getAnnotation(Confusing.class);
        if (null != annotationClass) {
            LOGGER.log(Level.INFO,
                    "\n Author: {0}\n Class name: {1}\n Package: {2}\n Super class: {3} \n Methods: \n",
                    new Object[]{annotationClass.author(), clazz.getName(), clazz.getPackage(), clazz.getSuperclass()});
        }

        for (final Method method : clazz.getDeclaredMethods()) {
            Confusing annotationMethod = method.getAnnotation(Confusing.class);
            if (null != annotationMethod) {
                LOGGER.log(Level.INFO, "\n author: {0}\n method name: {1}\n number of parameters: {2}\n method''s return type: {3}",
                        new Object[]{annotationMethod.author(), method.getName(), method.getParameterCount(), method.getReturnType()});
            }
        }
    }

}
