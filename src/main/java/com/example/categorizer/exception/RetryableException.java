package com.example.categorizer.exception;

public class RetryableException extends RuntimeException {
    public RetryableException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
