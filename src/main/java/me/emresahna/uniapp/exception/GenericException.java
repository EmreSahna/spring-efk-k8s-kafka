package me.emresahna.uniapp.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class GenericException extends RuntimeException {

    private final int code;
    private final String message;
    private final HttpStatus status;

    public GenericException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
        this.status = exceptionType.getStatus();
    }
}