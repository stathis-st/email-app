package com.emailapp.exception.user;

public class UserPersistenceException extends UserException {

    private static final String MESSAGE_TEMPLATE = "Failed to register user with username: %s, firstname: %s, lastname: %s";

    public UserPersistenceException(String username, String firstname, String lastName) {
        super(String.format(MESSAGE_TEMPLATE, username, firstname, lastName));
    }
}
