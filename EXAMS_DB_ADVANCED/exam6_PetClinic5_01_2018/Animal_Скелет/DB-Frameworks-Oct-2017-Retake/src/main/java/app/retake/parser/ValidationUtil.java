package app.retake.parser;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;
@Component
public final class ValidationUtil {
    private static Validator validator;

    public ValidationUtil() {
        validator=Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static <T> boolean isValid(T t) {
        return validator.validate(t).size()==0;
    }
}
