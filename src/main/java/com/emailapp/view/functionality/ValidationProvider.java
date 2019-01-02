package com.emailapp.view.functionality;

public interface ValidationProvider {

    default boolean validateStringForLength(String input, int lowerLimit, int upperLimit) {
        if (input.length() <= lowerLimit || input.length() > upperLimit) {
            System.out.println(String.format("Invalid input! Length should be between %s and %s", lowerLimit, upperLimit));
            return false;
        }
        return true;
    }
}
