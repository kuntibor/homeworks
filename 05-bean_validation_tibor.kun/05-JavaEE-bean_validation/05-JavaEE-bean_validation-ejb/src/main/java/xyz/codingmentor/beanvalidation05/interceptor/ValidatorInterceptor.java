package xyz.codingmentor.beanvalidation05.interceptor;

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

    private Set<ConstraintViolation<Object>> violations;

    @AroundInvoke
    public Object beanValidator(InvocationContext ic) throws Exception {
        validateParameters(ic.getParameters());
        return ic.proceed();
    }

    public void validateParameters(Object[] parameters) {
        Validate validate;
        for (Object parameter : parameters) {
            validate = parameter.getClass().getAnnotation(Validate.class);
            if (null != validate) {
                validateBean(parameter);
            }
        }
    }

    public void validateBean(Object object) {
        String errorMessages = "\nValidation error:";
        violations = validator.validate(object);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Object> cv : violations) {
                errorMessages += "\n\t" + cv.getMessage();
            }
            throw new ValidationException(errorMessages);
        }
    }

}
