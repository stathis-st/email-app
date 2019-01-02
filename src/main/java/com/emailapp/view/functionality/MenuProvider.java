package com.emailapp.view.functionality;

import java.util.Map;
import java.util.Scanner;

public interface MenuProvider {

    String ENTER_FROM_OPTIONS = "Please choose one of the available options: %s ";
    String NOT_A_NUMBER = "That's not a number!";

    default long provideSelectionMenu(Map<Long, String> availableOptions) {
        System.out.println("Available options:");
        availableOptions.forEach((key, value) -> System.out.println(String.format("[%s] - %s", key, value)));

        Scanner scanner = new Scanner(System.in);
        long chosenNumber = Long.MIN_VALUE;
        while (!availableOptions.keySet().contains(chosenNumber)) {
            System.out.print(String.format(ENTER_FROM_OPTIONS, availableOptions.keySet()));
            while (!scanner.hasNextLong()) {
                System.out.println(NOT_A_NUMBER);
                System.out.print(String.format(ENTER_FROM_OPTIONS, availableOptions.keySet()));
                scanner.next();
            }
            chosenNumber = scanner.nextInt();
        }

        return chosenNumber;
    }

    default void goBack() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type '0' to go back");

            while (!scanner.hasNextInt()) {
                System.out.print("That's not a number! ");
                scanner.next();
            }
            int number = scanner.nextInt();

            if (number == 0) {
                return;
            }
        }
    }
}
