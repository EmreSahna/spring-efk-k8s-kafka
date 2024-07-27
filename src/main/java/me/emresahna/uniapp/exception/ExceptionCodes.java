package me.emresahna.uniapp.exception;

import lombok.Getter;

@Getter
public enum ExceptionCodes {

    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND");

    private final String code;

    ExceptionCodes(String code) {
        this.code = code;
    }

}