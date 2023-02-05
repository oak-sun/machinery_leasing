package nam.gor.machineryleasing.models.exceptions;

import java.io.Serial;

public class EntityValidationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EntityValidationException(String field,
                                     String message) {
        super(String.format(
                "Validation error on field '%s': %s",
                field, message));
    }
}
