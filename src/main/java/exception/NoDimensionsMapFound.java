package exception;

public class NoDimensionsMapFound extends Exception{
    public NoDimensionsMapFound() {
        super();
    }

    public NoDimensionsMapFound(String message) {
        super(message);
    }

    public NoDimensionsMapFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDimensionsMapFound(Throwable cause) {
        super(cause);
    }

    protected NoDimensionsMapFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
