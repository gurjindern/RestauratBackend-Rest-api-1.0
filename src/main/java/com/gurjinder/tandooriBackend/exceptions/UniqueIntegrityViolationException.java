package com.gurjinder.tandooriBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueIntegrityViolationException extends RuntimeException {
    public UniqueIntegrityViolationException(String message) {
        super(message);
    }
}
