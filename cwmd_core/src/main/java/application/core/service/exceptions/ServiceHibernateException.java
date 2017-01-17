package application.core.service.exceptions;

/**
 * Exception class for Hibernate.
 */
public class ServiceHibernateException extends RuntimeException {

    /**
     * Constructor for the error message.
     * @param message The message of the error.
     */
    public ServiceHibernateException(String message) {
        super(message);
    }

    /**
     * Constructor for {@link Exception}.
     * @param e The Exception
     */
    public ServiceHibernateException(Exception e) {
        super(e);
    }

    /**
     * Constructor with error message and cause.
     * @param message The error message.
     * @param cause The error cause.
     */
    public ServiceHibernateException(String message, Throwable cause) {
        super(message, cause);
    }
}