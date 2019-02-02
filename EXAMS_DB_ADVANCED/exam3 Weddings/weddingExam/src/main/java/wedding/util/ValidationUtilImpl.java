package wedding.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil {
    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean isValid(T object) {
        Set<ConstraintViolation<T>> validate = this.validator.validate(object);
        return this.validator.validate(object).size() == 0;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> getViolations(T object) {
        return this.validator.validate(object);
    }

}
