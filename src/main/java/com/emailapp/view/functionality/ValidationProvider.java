package com.emailapp.view.functionality;

public interface ValidationProvider {

    default boolean validateStringForLength(String input, int lowerLimit, int upperLimit) {
        if (input.length() < lowerLimit) {
            System.out.println("Invalid input, lower limit");
            return false;
        }
        return true;
    }
}
