package wedding.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil  {
    <T>  boolean isValid(T object);

    <T> Set<ConstraintViolation<T>> getViolations(T object);
}
