package com.emailapp.view;

import com.emailapp.controller.LoginController;
import com.emailapp.exception.InvalidCredentialsException;

import java.io.Console;
import java.util.Scanner;

public class LoginView implements BaseView {

    private static final int MAXIMUM_LOGIN_TRIES = 3;
    private static final String FAILED_LOGIN_MESSAGE = String.format("You reached the maximum login tries (%s). Exiting application...", MAXIMUM_LOGIN_TRIES);

    @Override
    public void render() {
        int i = 0;
        while (i++ < MAXIMUM_LOGIN_TRIES) {
            try {
                Scanner scanner = new Scanner(System.in);
                Console console = System.console();

                System.out.println("Enter your credentials to login");

                System.out.print("Username: ");
                String username = scanner.nextLine();
                String password;

                if (console == null) {
                    System.out.print("Password: ");
                    password = scanner.nextLine();
                } else {
                    password = new String(console.readPassword("Password: "));
                }

                new LoginController().postFromLoginView(username, password);
            } catch (InvalidCredentialsException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(FAILED_LOGIN_MESSAGE);
    }

}
