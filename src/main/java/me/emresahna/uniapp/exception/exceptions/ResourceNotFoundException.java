package me.emresahna.uniapp.exception.exceptions;

import me.emresahna.uniapp.exception.ExceptionType;
import me.emresahna.uniapp.exception.GenericException;

public class ResourceNotFoundException extends GenericException {
    public ResourceNotFoundException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
