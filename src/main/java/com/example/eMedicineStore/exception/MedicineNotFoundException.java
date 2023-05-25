package com.example.eMedicineStore.exception;

public class MedicineNotFoundException extends Exception{

    public MedicineNotFoundException() {
        super();
    }

    public MedicineNotFoundException(String message) {
        super(message);
    }

    public MedicineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MedicineNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MedicineNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
