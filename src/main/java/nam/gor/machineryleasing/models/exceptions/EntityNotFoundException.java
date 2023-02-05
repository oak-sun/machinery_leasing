package nam.gor.machineryleasing.models.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message,
                                   Object... args) {
        super(String.format(message, args));
    }
}
