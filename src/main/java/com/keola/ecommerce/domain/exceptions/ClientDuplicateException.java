package com.keola.ecommerce.domain.exceptions;

public class ClientDuplicateException extends Exception {
    public ClientDuplicateException(String message) {
        super(message);
    }
}
