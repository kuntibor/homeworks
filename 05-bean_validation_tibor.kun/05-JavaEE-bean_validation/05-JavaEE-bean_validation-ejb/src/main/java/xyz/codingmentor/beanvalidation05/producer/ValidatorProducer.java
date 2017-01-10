package xyz.codingmentor.beanvalidation05.producer;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import xyz.codingmentor.beanvalidation05.annotation.ValidatorQualifier;

/**
 *
 * @author Tibor Kun
 */
public class ValidatorProducer {

    @Produces @ValidatorQualifier
    public Validator producerLogger() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }

}
