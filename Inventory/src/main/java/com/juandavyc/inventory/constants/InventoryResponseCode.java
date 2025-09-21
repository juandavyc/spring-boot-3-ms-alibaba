package com.juandavyc.inventory.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter


public enum InventoryResponseCode {

    CREATED(HttpStatus.CREATED, "Inventory created successfully"),
    SUCCESS(HttpStatus.OK, "Request processed successfully"),
    UPDATE_FAILED(HttpStatus.EXPECTATION_FAILED, "Update operation failed"),
    DELETE_FAILED(HttpStatus.EXPECTATION_FAILED, "Delete operation failed"),
    INTERNAL_ERROR(HttpStatus.BAD_REQUEST, "An error occurred"),
    AVAILABLE(HttpStatus.OK, "Resource is available"),
    EXISTS(HttpStatus.CONFLICT, "Resource already exists"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request parameters"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found");

    private final HttpStatus status;
    private final String message;

}
