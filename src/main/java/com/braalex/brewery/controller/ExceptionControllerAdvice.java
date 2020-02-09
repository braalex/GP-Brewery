package com.braalex.brewery.controller;

import com.braalex.brewery.exception.SuchUserAlreadyExistException;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;

@Log
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({SuchUserAlreadyExistException.class})
    private ResponseEntity<ErrorMessage> handleBadRequest(final Exception e) {
        log.log(Level.SEVERE, e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Data
    private static class ErrorMessage {
        private final String errorMessage;
    }
}
