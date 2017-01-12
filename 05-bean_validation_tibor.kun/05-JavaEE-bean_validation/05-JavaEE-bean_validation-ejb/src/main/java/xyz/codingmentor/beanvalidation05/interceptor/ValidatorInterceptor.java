package xyz.codingmentor.beanvalidation05.interceptor;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import xyz.codingmentor.beanvalidation05.annotation.Validate;
import xyz.codingmentor.beanvalidation05.annotation.ValidatorQualifier;
import xyz.codingmentor.beanvalidation05.exception.ValidationException;

/**
 *
 * @author Tibor Kun
 */
@Interceptor
@BeanValidation
public class ValidatorInterceptor {

    @Inject
    @ValidatorQualifier
    private Validator validator;

    @AroundInvoke
    public Object beanValidator(InvocationContext ic) throws Exception {
        validateParameters(ic.getParameters());
        return ic.proceed();
    }

    public void validateParameters(Object[] parameters) {
        Arrays.asList(parameters)
                .stream()
                .filter(p -> p.getClass().isAnnotationPresent(Validate.class))
                .forEach(p -> validateBean(p));
    }

    public void validateBean(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations
                .stream()
                .map(e -> "\n\tValidation error: " + e.getMessage())
                .reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }

}
