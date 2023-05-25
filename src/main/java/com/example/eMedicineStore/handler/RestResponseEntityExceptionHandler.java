package com.example.eMedicineStore.handler;

import com.example.eMedicineStore.exception.*;
import com.example.eMedicineStore.handlerEntity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<ErrorMessage> userNotCreatedException(UserNotCreatedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(OrderNotProcessedException.class)
    public ResponseEntity<ErrorMessage> orderNotProcessedException(OrderNotProcessedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(CartNotUpdatedException.class)
    public ResponseEntity<ErrorMessage> cartNotUpdatedException(OrderNotProcessedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorMessage> cartNotFoundException(CartNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(CartNotAddedException.class)
    public ResponseEntity<ErrorMessage> cartNotAddedException(CartNotAddedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(MedicineNotCreatedException.class)
    public ResponseEntity<ErrorMessage> medicineNotCreatedException(MedicineNotCreatedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(MedicineNotFoundException.class)
    public ResponseEntity<ErrorMessage> medicineNotFoundException(MedicineNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> orderNotFoundException(OrderNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

}
