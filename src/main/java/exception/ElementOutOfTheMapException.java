package exception;

public class ElementOutOfTheMapException extends Exception{

    public ElementOutOfTheMapException() {
        super();
    }

    public ElementOutOfTheMapException(String message) {
        super(message);
    }

    public ElementOutOfTheMapException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementOutOfTheMapException(Throwable cause) {
        super(cause);
    }

    protected ElementOutOfTheMapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
