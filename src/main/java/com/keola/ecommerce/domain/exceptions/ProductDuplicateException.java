package com.keola.ecommerce.domain.exceptions;

public class ProductDuplicateException extends Exception {
    public ProductDuplicateException(String message) {
        super(message);
    }
}
