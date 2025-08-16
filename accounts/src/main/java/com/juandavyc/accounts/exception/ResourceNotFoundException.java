package com.juandavyc.accounts.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found for field %s with value %s", resourceName, fieldName, fieldValue));
    }
}
