package application.core.utils.exceptions;

public class FlowMailerException extends RuntimeException {
    public FlowMailerException(String message) {
        super(message);
    }

    public FlowMailerException(Exception e) {
        super(e);
    }

    public FlowMailerException(String message, Throwable cause) {
        super(message, cause);
    }
}
