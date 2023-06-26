package com.emresahna.microserviceapp.exception;

public enum ExceptionCodes {

    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND");

    private final String code;

    ExceptionCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}