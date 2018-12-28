package com.emailapp.controller;

import java.util.Scanner;

public class HomeController extends BaseController {

    private LoginController loginController = new LoginController();
    private RegistrationController registrationController = new RegistrationController();

    private static final String WELCOME_MESSAGE = "Welcome to the email application";

    public void welcome() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println("Available options: ");
        System.out.println("\tType '1' to login");
        System.out.println("\tType '2' for Registration");
        System.out.println("\tType '3' to exit the application");

        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            System.out.print("That's not a number! Please enter a number from 1 to 3 > ");
            scanner.next();
        }

        int number = scanner.nextInt();

        switch (number) {
            case 1:
                loginController.login();
                break;
            case 2:
                registrationController.register();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                break;
        }
    }

}
