package me.emresahna.uniapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import me.emresahna.uniapp.service.TranslationService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.RequiredArgsConstructor;
import me.emresahna.uniapp.utils.LocaleUtils;
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
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GeneralExceptionHandler {
    private final TranslationService translationService;

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(GenericException exception, WebRequest request) {
        return createExceptionResponse(exception.getStatus(), exception.getCode(), List.of(exception.getMessage()), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(MethodArgumentNotValidException exception, WebRequest request) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return createExceptionResponse(HttpStatus.BAD_REQUEST, exception.getStatusCode().value(), errors, request);
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleCircuit(CallNotPermittedException exception, WebRequest request) {
        return createExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.value(), List.of(exception.getMessage()), request);
    }

    private ResponseEntity<ExceptionResponse> createExceptionResponse(HttpStatus status, int code, List<String> message, WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        Locale locale = LocaleUtils.getLocaleFromRequest(request);

        ExceptionResponse response = ExceptionResponse.builder()
                .status(status)
                .code(code)
                .path(path)
                .timestamp(OffsetDateTime.now())
                .detail(translationService.findByKey(message, locale))
                .build();

        log.error("Error: {}", response);

        return ResponseEntity.status(status).body(response);
    }
}
