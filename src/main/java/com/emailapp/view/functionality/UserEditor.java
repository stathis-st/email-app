package com.emailapp.view.functionality;

import java.io.Console;
import java.util.Scanner;
import java.util.Set;

public interface UserEditor extends ValidationProvider {

    default String extractName(Scanner scanner, String renderedOutput) {
        System.out.print(renderedOutput + " (max length 45 characters): ");
        String name = scanner.nextLine().trim();
        while (!validateStringForLength(name, 0, 45)) {
            name = scanner.nextLine().trim();
        }
        return name;
    }

    default String extractPassword(Scanner scanner) {
        Console console = System.console();
        String password = askForPassword(scanner, console);
        while (!validateStringForLength(password, 0, 25)) {
            password = askForPassword(scanner, console);
        }
        return password;
    }

    default String askForPassword(Scanner scanner, Console console) {
        String password;
        if (console == null) {
            System.out.print("Password (max length 25 characters): ");
            password = scanner.nextLine().trim();
        } else {
            password = new String(console.readPassword("Password (max length 25 characters): "));
        }
        return password;
    }

    default String extractUsername(Scanner scanner, Set<String> registeredUserNames) {
        System.out.print("Username (max length 25 characters): ");
        String username = scanner.nextLine().trim();
        while (isAlreadyTaken(username, registeredUserNames) || !validateStringForLength(username, 0, 25)) {
            System.out.print("Username (max length 25 characters): ");
            username = scanner.nextLine().trim();
        }
        return username;
    }

    default boolean isAlreadyTaken(String username, Set<String> registeredUserNames) {
        if (registeredUserNames.contains(username)) {
            System.out.println("Username is already taken!");
            return true;
        }
        return false;
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
