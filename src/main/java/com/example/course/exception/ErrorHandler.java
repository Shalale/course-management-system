package com.example.course.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.course.exception.ExceptionConstants.NOT_FOUND_EXCEPTION_MESSAGE;
import static com.example.course.exception.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex){
        log.error("Exception: ", ex);
        return new ExceptionResponse("UNEXPECTED_EXCEPTION", UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(NotFoundException ex){
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ExceptionResponse handle(DeactiveElementException ex){
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }
}
