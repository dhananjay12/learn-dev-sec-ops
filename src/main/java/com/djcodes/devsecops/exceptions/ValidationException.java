package com.djcodes.devsecops.exceptions;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String message;

    public ValidationException(String message) {
        this.message = message;

    }

    public String getMsg() {
        return message;
    }

}
