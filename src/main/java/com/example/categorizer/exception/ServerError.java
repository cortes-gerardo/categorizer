package com.example.categorizer.exception;

public class ServerError extends RuntimeException {
    public ServerError(final String message, final Throwable cause) {
        super(message, cause);
    }
}
