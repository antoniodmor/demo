package com.itx.demo.domain.enums;


public enum ErrorCode {

    INVALID_DATE_FORMAT("400", "The date format provided is not valid"),
    UNPROCESABLE_ENTITY("422", "The request is well-formed but it contains semantic errors"),
    UNEXPECTED_ERROR("500", "An unexpected error occurred"),
    UNAUTHORIZED_ERROR("403", "Unauthorized error");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}