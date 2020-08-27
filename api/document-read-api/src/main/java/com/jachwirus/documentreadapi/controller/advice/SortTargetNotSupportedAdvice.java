package com.jachwirus.documentreadapi.controller.advice;

import com.jachwirus.documentreadapi.exception.SortTargetNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SortTargetNotSupportedAdvice {
    @ResponseBody
    @ExceptionHandler(SortTargetNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String sortTargetNotSupportedHandler(SortTargetNotSupportedException ex) {
        return ex.getMessage();
    }
}
