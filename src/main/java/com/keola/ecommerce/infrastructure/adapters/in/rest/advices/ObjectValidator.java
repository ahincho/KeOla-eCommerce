package com.keola.ecommerce.infrastructure.adapters.in.rest.advices;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ObjectValidator {
    private final Validator validator;
    public  ObjectValidator(Validator validator) {
        this.validator = validator;
    }
    public <T> T validate(T t) throws Exception {
        Set<ConstraintViolation<T>> errors = validator.validate(t);
        if (!errors.isEmpty()) {
            List<String> messages = errors.stream().map(ConstraintViolation::getMessage).toList();
            throw new CustomException(HttpStatus.BAD_REQUEST, String.valueOf(messages.stream().reduce(String::join)));
        }
        return t;
    }
}
