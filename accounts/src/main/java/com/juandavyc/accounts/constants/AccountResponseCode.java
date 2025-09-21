package com.juandavyc.accounts.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter


public enum AccountResponseCode {

    CREATED(HttpStatus.CREATED, "Account created successfully"),
    SUCCESS(HttpStatus.OK, "Request processed successfully"),
    UPDATE_FAILED(HttpStatus.EXPECTATION_FAILED, "Update operation failed"),
    DELETE_FAILED(HttpStatus.EXPECTATION_FAILED, "Delete operation failed"),
    INTERNAL_ERROR(HttpStatus.BAD_REQUEST, "An error occurred"),
    AVAILABLE(HttpStatus.OK, "Resource is available"),
    EXISTS(HttpStatus.CONFLICT, "Resource already exists"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request parameters");

    private final HttpStatus status;
    private final String message;

}
