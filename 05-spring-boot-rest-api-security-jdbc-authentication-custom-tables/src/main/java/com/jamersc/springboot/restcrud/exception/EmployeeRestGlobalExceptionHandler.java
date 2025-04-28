package com.jamersc.springboot.restcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeRestGlobalExceptionHandler {

    // add an exception handler
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc) {

        // create employee id not found response
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value()); // 404
        error.setMessage(exc.getMessage()); // e.g., "Employee id not found - 6"
        error.setTimestamp(System.currentTimeMillis()); // current time in ms

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeIDNotAllowedInRequestBodyException exc) {

        // create employee id not found response
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value()); // 400
        error.setMessage(exc.getMessage()); // e.g., "Cannot change primary key to a value that already exists."
        error.setTimestamp(System.currentTimeMillis()); // current time in ms

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
