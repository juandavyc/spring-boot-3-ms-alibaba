package com.juandavyc.orders.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String field, String value) {
        super(String.format("%s not found for field %s, value: '%s'", resource, field, value));
    }
}
