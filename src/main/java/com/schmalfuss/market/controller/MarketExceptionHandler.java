package com.schmalfuss.market.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MarketExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ProblemDetail exceptionHandler(RuntimeException exception) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
        problemDetails.setTitle("Runtime exception - Object not found");
        return problemDetails;
    }
}
