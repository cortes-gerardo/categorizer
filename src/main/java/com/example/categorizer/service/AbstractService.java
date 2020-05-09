package com.example.categorizer.service;

import com.example.categorizer.exception.RetryableException;
import com.example.categorizer.exception.ServerError;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
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
        } catch (TransientDataAccessException e){
            String message = format("%s while doing operation %s, retrying", e.getClass().getSimpleName(), operation);
            log().warn(message);
            throw new RetryableException(message, e);
        } catch (Exception e) {
            String message = format("%s while doing operation %s and can't be handled, failing", e.getClass().getSimpleName(), operation);
            log().error(message, e);
            throw new ServerError(message, e);
        }
    }

    @Recover
    protected void recover(final RetryableException e) {
        String message = format("%s can not be recovered, failing", e.getClass().getSimpleName());
        log().error(message, e);
        throw new ServerError(message, e);
    }

    protected abstract Logger log();
}
