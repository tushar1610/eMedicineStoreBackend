package com.example.eMedicineStore.exception;

public class MedicineNotCreatedException extends Exception{

    public MedicineNotCreatedException() {
        super();
    }

    public MedicineNotCreatedException(String message) {
        super(message);
    }

    public MedicineNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MedicineNotCreatedException(Throwable cause) {
        super(cause);
    }

    protected MedicineNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
