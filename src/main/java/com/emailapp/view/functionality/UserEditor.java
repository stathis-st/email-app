package com.emailapp.view.functionality;

import java.io.Console;
import java.util.Scanner;

public interface UserEditor {

    default String extractName(Scanner scanner, String renderedOutput) {
        System.out.print(renderedOutput);
        return scanner.nextLine();
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
}
