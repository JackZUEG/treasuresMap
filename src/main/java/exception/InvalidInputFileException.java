package exception;

public class InvalidInputFileException extends Exception{
    public InvalidInputFileException() {
        super();
    }

    public InvalidInputFileException(String message) {
        super(message);
    }

    public InvalidInputFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputFileException(Throwable cause) {
        super(cause);
    }

    protected InvalidInputFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
