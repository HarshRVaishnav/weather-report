package com.dice.Weather_Report.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class ExceptionHandler
{
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException e)
    {
        // Handling 4xx errors
        return new ResponseEntity<>(
                "Client error: " + e.getStatusCode() + " - " + e.getStatusText(),
                HttpStatus.BAD_REQUEST
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleHttpServerErrorException(HttpServerErrorException e)
    {
        // Handling 5xx errors
        return new ResponseEntity<>(
                "Server error: " + e.getStatusCode() + " - " + e.getStatusText(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e)
    {
        // Handling  other exceptions
        return new ResponseEntity<>("An error occurred: " + e.getMessage()
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
