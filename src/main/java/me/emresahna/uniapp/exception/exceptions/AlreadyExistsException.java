package me.emresahna.uniapp.exception.exceptions;

import me.emresahna.uniapp.exception.ExceptionType;
import me.emresahna.uniapp.exception.GenericException;

public class AlreadyExistsException extends GenericException {
    public AlreadyExistsException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
