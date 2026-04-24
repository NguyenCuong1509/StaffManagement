package com.cuongmn.StaffManegement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    DEPARTMENT_EXISTED(1001, "Department already exists", HttpStatus.BAD_REQUEST),
    DEPARTMENT_NOT_FOUND(1002, "Department not found", HttpStatus.NOT_FOUND),

    STAFF_EXISTED(2001, "Staff already exists", HttpStatus.BAD_REQUEST),
    STAFF_NOT_FOUND(2002, "Staff not found", HttpStatus.NOT_FOUND),

    USERNAME_REQUIRED(3001, "Username is required", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(3002, "Password is required", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS(3003, "Invalid username or password", HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED(3004, "Unauthenticated", HttpStatus.UNAUTHORIZED),

    INVALID_TOKEN(4001, "Invalid token", HttpStatus.UNAUTHORIZED);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}