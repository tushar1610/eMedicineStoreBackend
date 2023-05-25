package com.example.eMedicineStore.exception;

public class OrderNotProcessedException extends Exception{

    public OrderNotProcessedException() {
        super();
    }

    public OrderNotProcessedException(String message) {
        super(message);
    }

    public OrderNotProcessedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotProcessedException(Throwable cause) {
        super(cause);
    }

    protected OrderNotProcessedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
