package com.emresahna.microserviceapp.exception;

import com.emresahna.microserviceapp.service.TranslationService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GeneralExceptionHandler {
    private final TranslationService translationService;

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(GenericException exception,
                                                                    WebRequest request) {
        var path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        return createExceptionResponse(exception, path);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(MethodArgumentNotValidException exception,
                                                                WebRequest request) {
        var path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();

        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        var build = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(Objects.requireNonNull(exception.getFieldError()).getCode())
                .path(path)
                .timestamp(OffsetDateTime.now())
                .detail(translationService.findByKey(errors))
                .build();

        return ResponseEntity.badRequest().body(build);
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleCircuit(CallNotPermittedException exception,
                                                       WebRequest request) {
        var path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        var build = ExceptionResponse.builder()
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .code("SERVICE_UNAVAILABLE")
                .path(path)
                .timestamp(OffsetDateTime.now())
                .detail(translationService.findByKey(List.of(exception.getMessage())))
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(build);
    }

    private ResponseEntity<ExceptionResponse> createExceptionResponse(GenericException exception,
                                                              String path) {
        var build = ExceptionResponse.builder()
                .status(exception.getStatus())
                .code(exception.getCode())
                .path(path)
                .timestamp(OffsetDateTime.now())
                .detail(translationService.findByKey(List.of(exception.getMessage())))
                .build();

        return ResponseEntity.status(exception.getStatus()).body(build);
    }
}
