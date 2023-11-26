package com.jdhb.rest.exception;

public class EmployeeClientException extends RuntimeException {

    public EmployeeClientException(String message) {
        super(message);
    }

    public EmployeeClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
