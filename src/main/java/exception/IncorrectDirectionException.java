package exception;

public class IncorrectDirectionException extends Exception {

    public IncorrectDirectionException() {
        super();
    }

    public IncorrectDirectionException(String message) {
        super(message);
    }

    public IncorrectDirectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDirectionException(Throwable cause) {
        super(cause);
    }

    protected IncorrectDirectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
