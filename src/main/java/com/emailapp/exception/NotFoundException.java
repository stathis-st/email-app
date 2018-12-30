package com.emailapp.exception;

public class NotFoundException extends EmailAppException {

    private static final String MESSAGE = "Resource with id: %s was not found!";

    public NotFoundException(long id) {
        super(String.format(MESSAGE, id));
    }
}
