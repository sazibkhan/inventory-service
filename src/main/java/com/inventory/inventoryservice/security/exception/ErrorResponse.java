package com.inventory.inventoryservice.security.exception;

public class ErrorResponse {

    private String error;
    private String message;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    // getters
    public String getError() { return error; }
    public String getMessage() { return message; }
}



