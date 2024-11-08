package com.example.springdatajpa.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.springdatajpa.demo.model.ExceptionMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ExceptionMessage> handleRuntimeException(final RuntimeException re) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).<ExceptionMessage>body(
                ExceptionMessage.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR).errorMessage(re.getMessage())
                        .build());

    }
}
