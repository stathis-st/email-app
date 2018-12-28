package com.emailapp.exception;

public class InvalidCredentialsException extends EmailAppException {
    private static final String MESSAGE = "Wrong credentials";

    public InvalidCredentialsException() {
        super(MESSAGE);
    }
}
