package com.emailapp.exception;

public abstract class EmailAppException extends Exception {

    public EmailAppException(String message) {
        super(message);
    }
}
