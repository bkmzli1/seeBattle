package ru.bkmz.utils;

public class ExceptionOutOfBounds extends Exception{

    public ExceptionOutOfBounds() {
        super();
    }

    public ExceptionOutOfBounds(String message) {
        super(message);
    }

    public ExceptionOutOfBounds(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionOutOfBounds(Throwable cause) {
        super(cause);
    }

    protected ExceptionOutOfBounds(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
