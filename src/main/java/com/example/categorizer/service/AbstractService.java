package com.example.categorizer.service;

import com.example.categorizer.exception.RetryableException;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.function.Supplier;

import static java.lang.String.format;

public abstract class AbstractService {

    @Retryable(
            value = { RetryableException.class },
            maxAttempts = 2,
            backoff = @Backoff(delay = 5000))
    protected <T> T tryOrThrow(final Supplier<T> supplier, final String operation) throws RetryableException {
        try {
            return supplier.get();
        } catch (DataIntegrityViolationException e){
            String message = format("%s while %s", e.getClass().getSimpleName(), operation);
            log().warn(message);
            throw new RetryableException(message, e);
        } catch (Exception e) {
            log().error("UnknownError: " + e.getClass().getName());
            throw new RuntimeException(e);
        }
    }

    protected abstract Logger log();
}
