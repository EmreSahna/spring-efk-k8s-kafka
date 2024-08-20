package me.emresahna.uniapp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    STUDENT_NOT_FOUND(1001 ,"student.not_found", HttpStatus.NOT_FOUND),
    COURSE_NOT_FOUND(1002, "course.not_found", HttpStatus.NOT_FOUND),
    INSTRUCTOR_NOT_FOUND(1003, "instructor.not_found", HttpStatus.NOT_FOUND),
    STUDENT_ALREADY_EXISTS(1101, "student.already_exists", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus status;
}