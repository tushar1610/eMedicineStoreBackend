package com.example.eMedicineStore.exception;

public class CartNotAddedException extends Exception{

    public CartNotAddedException() {
        super();
    }

    public CartNotAddedException(String message) {
        super(message);
    }

    public CartNotAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotAddedException(Throwable cause) {
        super(cause);
    }

    protected CartNotAddedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
