package exception;

public class IncorrectMovementException extends Exception {

    public IncorrectMovementException() {
        super();
    }

    public IncorrectMovementException(String message) {
        super(message);
    }

    public IncorrectMovementException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectMovementException(Throwable cause) {
        super(cause);
    }

    protected IncorrectMovementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
