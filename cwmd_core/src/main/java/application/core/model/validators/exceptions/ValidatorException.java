package application.core.model.validators.exceptions;

/**
 * Custom exception
 */
public class ValidatorException extends RuntimeException {
    public ValidatorException(String message) {
        super(message);
    }
}