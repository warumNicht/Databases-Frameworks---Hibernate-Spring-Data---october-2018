package gamestore.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ConstraintViolationsExtractor {
    public static  <T> String getConstraintViolationsMessages(Set<ConstraintViolation<T>> constraintViolations) {
        StringBuilder sb = new StringBuilder();
        constraintViolations.forEach(v -> sb.append(v.getMessage()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
