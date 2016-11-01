package application.core.service.exceptions;

public class ServiceHibernateException extends RuntimeException {
    public ServiceHibernateException(String message) {
        super(message);
    }

    public ServiceHibernateException(Exception e) {
        super(e);
    }

    public ServiceHibernateException(String message, Throwable cause) {
        super(message, cause);
    }
}