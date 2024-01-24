package com.dice.Weather_Report.exception;

import com.dice.Weather_Report.reaponse.ApiErrorResponse;
import com.dice.Weather_Report.reaponse.ApiValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler
{
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpClientErrorException(HttpClientErrorException e)
    {
        // Handling 4xx errors
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .status(400)
                .message(e.getMessage())
                 .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(apiErrorResponse);


    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpServerErrorException(HttpServerErrorException e)
    {
        // Handling 5xx errors
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .status(500)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.internalServerError().body(apiErrorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception e)
    {
        // Handling  other exceptions
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .status(500)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.internalServerError().body(apiErrorResponse);
    }


    // handling validation failures
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        Map<String, List<String>> errors = new HashMap<>();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String errorMessage = getBriefErrorMessage(fieldError);
            errors.computeIfAbsent(fieldName, key -> new ArrayList<>()).add(errorMessage);
        }

        ApiValidationErrorResponse apiValidationErrorResponse = ApiValidationErrorResponse.builder()
                .status(1002)
                .message(errors)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(apiValidationErrorResponse);
    }

    private String getBriefErrorMessage(FieldError fieldError) {
        String defaultMessage = fieldError.getDefaultMessage();
        String[] parts = defaultMessage.split("\\{");
        return parts[0].trim();
    }
}
