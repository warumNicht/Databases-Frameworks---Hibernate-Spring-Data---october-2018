package org.softuni.mostwanted.parser;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public final class ValidationUtil {
    //TODO: Implement me ...
    private Validator validator;

    public ValidationUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    public  <T> boolean isValid(T object){
        Set<ConstraintViolation<Object>> exceptions = this.validator.validate(object);
        return exceptions.size()==0;
    }
}
