package com.emailapp.controller;

import com.emailapp.exception.EmailAppException;

import java.sql.SQLException;

public class BaseController {

    //TODO Add here (upon refactoring) all generic methods (scanner input validations etc...)
    //TODO eg validateNumberInRange(int inputNumber, int lowerLimitIncluded, int upperLimitIncluded), validateInputLengthLowerThan(int maximumLength)

    protected void handleException(Exception exception) {
        if (exception instanceof EmailAppException) {
            System.err.println(exception.getMessage());
        } else if (exception instanceof SQLException) {
            System.err.println("Something went wrong with the database...");
        } else {
            System.err.println("Something went terribly wrong...");
        }
    }
}
