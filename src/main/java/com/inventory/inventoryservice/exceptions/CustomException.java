package com.inventory.inventoryservice.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    public CustomException(String message, HttpStatus status) {
        super(message);
    }
}
