package com.emailapp.view.functionality;

import java.util.Scanner;

public interface MessageEditor {

    default String extractMessage(Scanner scanner) {
        System.out.print("Message (max length 250 characters): ");
        String message = scanner.nextLine().trim();

        while (message.length() > 250) {
            System.out.println("Your message is longer than 250 characters!");
            System.out.println("Please type a message up to 250 characters");
            System.out.print("Message (max length 250 characters): ");
            message = scanner.nextLine().trim();
        }
        if (message.length() == 0) {
            message = "(empty message content)";
        }
        return message;
    }

    default String extractSubject(Scanner scanner) {
        System.out.print("Subject (max length 100 characters): ");
        String subject = scanner.nextLine().trim();

        while (subject.length() > 100) {
            System.out.println("The subject is longer than 100 characters!");
            System.out.println("Please type a subject up to 100 characters");
            System.out.print("Subject (max length 100 characters): ");
            subject = scanner.nextLine().trim();
        }
        if (subject.length() == 0) {
            subject = "(no subject)";
        }
        return subject;
    }
}
