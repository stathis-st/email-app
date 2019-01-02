package com.emailapp.view.functionality;

import java.io.Console;
import java.util.Scanner;

public interface UserEditor {

    default String extractName(Scanner scanner, String renderedOutput) {
        System.out.print(renderedOutput + " (max length 45 characters): ");
        String name = scanner.nextLine().trim();

        while (name.length() <= 0 || name.length() > 45) {
            if (name.length() <= 0) {
                System.out.println(renderedOutput + " must not be empty!");
                System.out.print(renderedOutput + " (max length 45 characters): ");
                name = scanner.nextLine().trim();
            } else {
                System.out.println(renderedOutput + " is longer than 45 characters!");
                System.out.print(renderedOutput + " (max length 45 characters): ");
                name = scanner.nextLine().trim();
            }
        }
        return name;
    }

    default String extractPassword(Scanner scanner) {
        Console console = System.console();
        if (console == null) {
            System.out.print("Password: ");
            return scanner.nextLine();
        } else {
            return new String(console.readPassword("Password: "));
        }
    }

//    default String validatePassword(String password) {
//        while (password.length() <= 0 || password.length() > 45) {
//            if (password.length() <= 0) {
//                System.out.println("Password must not be empty!");
//                System.out.print("Password (max length 25 characters): ");
//                password = scanner.nextLine().trim();
//            } else {
//                System.out.println(renderedOutput + " is longer than 45 characters!");
//                System.out.print(renderedOutput + " (max length 45 characters): ");
//                name = scanner.nextLine().trim();
//            }
//        }
//        return name;
//    }
}
