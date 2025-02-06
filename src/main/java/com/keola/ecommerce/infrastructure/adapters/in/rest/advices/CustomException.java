package com.keola.ecommerce.infrastructure.adapters.in.rest.advices;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends Exception {
    private final HttpStatus httpStatus;
    public CustomException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
