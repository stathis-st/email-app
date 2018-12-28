package com.emailapp.controller;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public interface MenuProvider<T> {

    default void provideSelectionMenu(Map<Integer, Consumer<T>> consumerMap) {
        while (true) {
            provideMenu();
            Scanner scanner = new Scanner(System.in);

            int chosenNumber = Integer.MIN_VALUE;
            while (!consumerMap.keySet().contains(chosenNumber)) {
                System.out.print(String.format("Please enter a number in set: %s", consumerMap.keySet()));
                while (!scanner.hasNextInt()) {
                    System.out.print(String.format("That's not a number! Please enter a number in set: %s", consumerMap.keySet()));
                    scanner.next();
                }
                chosenNumber = scanner.nextInt();
            }

            if (!consumerMap.keySet().contains(chosenNumber)) {
                continue;
            }

            consumerMap.get(chosenNumber).accept(null);
        }
    }

    void provideMenu();
}
