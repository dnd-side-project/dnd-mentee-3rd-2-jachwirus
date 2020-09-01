package com.jachwirus.documentreadapi.controller.advice;

import com.jachwirus.documentreadapi.exception.DocumentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DocumentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String documentNotFoundHandler(DocumentNotFoundException ex){
        return ex.getMessage();
    }
}