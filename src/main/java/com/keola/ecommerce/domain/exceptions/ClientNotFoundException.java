package com.keola.ecommerce.domain.exceptions;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
