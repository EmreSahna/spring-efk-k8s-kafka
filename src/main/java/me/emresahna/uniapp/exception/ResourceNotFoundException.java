package me.emresahna.uniapp.exception;

import static me.emresahna.uniapp.exception.ExceptionCodes.RESOURCE_NOT_FOUND;

public class ResourceNotFoundException extends GenericException {

    public ResourceNotFoundException(String message) {
        super(RESOURCE_NOT_FOUND.getCode(), message, 404);
    }

}
