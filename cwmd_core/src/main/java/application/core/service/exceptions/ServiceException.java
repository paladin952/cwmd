package application.core.service.exceptions;

/**
 * Exception class for the Services.
 */
public class ServiceException extends RuntimeException {

    /**
     * Constructor for the error message.
     * @param message The message of the error.
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructor for {@link Exception}.
     * @param e The Exception
     */
    public ServiceException(Exception e) {
        super(e);
    }

    /**
     * Constructor with error message and cause.
     * @param message The error message.
     * @param cause The error cause.
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
