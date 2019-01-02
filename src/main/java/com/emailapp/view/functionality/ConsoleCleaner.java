package com.emailapp.view.functionality;

import java.io.IOException;

public interface ConsoleCleaner {

    default void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final IOException | InterruptedException e) {
            System.out.println("Failed to clear console");
        }
    }
}
