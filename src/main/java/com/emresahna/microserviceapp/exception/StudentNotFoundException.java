package com.emresahna.microserviceapp.exception;

import static com.emresahna.microserviceapp.exception.ExceptionCodes.STUDENT_NOT_FOUND;

public class StudentNotFoundException extends GenericException {

    public StudentNotFoundException() {
        super(STUDENT_NOT_FOUND.getCode(), STUDENT_NOT_FOUND.getCode(), 404);
    }

}
