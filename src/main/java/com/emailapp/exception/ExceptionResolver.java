package com.emailapp.exception;

import java.sql.SQLException;

public interface ExceptionResolver {

    default void handleException(Exception exception) {
        if (exception instanceof EmailAppException) {
            System.out.println(exception.getMessage());
        } else if (exception instanceof SQLException) {
            //log?
            System.err.println("Something went wrong with the database...");
        } else {
            //log?
            System.err.println("Something went terribly wrong...");
        }
    }
}
