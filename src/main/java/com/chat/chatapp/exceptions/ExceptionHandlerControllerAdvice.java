package com.chat.chatapp.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleResourceNotFound(final MessageNotFoundException exception,
                                                                  final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());

        return error;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleResourceNotFound(final UserNotFoundException exception,
                                                                  final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());

        return error;
    }

    @ExceptionHandler(InvalidBodyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleResourceNotFound(final InvalidBodyException exception,
                                                                  final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());

        return error;
    }

}