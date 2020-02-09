package com.braalex.brewery.exception;

public class SuchUserAlreadyExistException extends Exception {
    public SuchUserAlreadyExistException() {
    }

    public SuchUserAlreadyExistException(final String message) {
        super(message);
    }
}
