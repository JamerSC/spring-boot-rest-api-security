package com.jamersc.springboot.restcrud.exception;

public class EmployeeIDNotAllowedInRequestBodyException extends RuntimeException {
    public EmployeeIDNotAllowedInRequestBodyException(String message) {
        super(message);
    }

    public EmployeeIDNotAllowedInRequestBodyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeIDNotAllowedInRequestBodyException(Throwable cause) {
        super(cause);
    }
}
