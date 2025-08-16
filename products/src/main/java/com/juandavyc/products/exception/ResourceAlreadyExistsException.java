package com.juandavyc.products.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String resource,String field, String value) {
        super(String.format("%s already exists for field %s with value: %s", resource, field, value));
    }
}
