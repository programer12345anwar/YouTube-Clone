package com.youtube.central.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public void exceptionHandling(Exception ex) throws  Exception {

    }
}
