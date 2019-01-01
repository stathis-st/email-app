package com.emailapp.view.functionality;

import java.util.Scanner;

public interface MessageEditor {

    default String extractMessage(Scanner scanner) {
        System.out.print("Message (max length 250 characters): ");
        String message = scanner.nextLine();

        while (message.length() > 250) {
            System.out.println("Your message is longer than 250 characters!");
            System.out.println("Please type a message up to 250 characters");
            System.out.print("Message (max length 250 characters): ");
            message = scanner.nextLine();
        }

        return message;
    }
}
