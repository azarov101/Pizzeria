package com.example.delivery.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    public InternalServerException(String errorMessage) {
        super(errorMessage);
    }
    public InternalServerException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
