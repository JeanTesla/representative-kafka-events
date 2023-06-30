package com.jeantesla.representativekafkaeventsproducer.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<GenericErrorResponse> domainExceptionHandle(DomainException ex){

        HttpStatus status = ex.getClass().getAnnotation(ResponseStatus.class).value();

        GenericErrorResponse genericErrorResponse = GenericErrorResponse.builder()
                .status(status)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(genericErrorResponse, status);
    }
}
