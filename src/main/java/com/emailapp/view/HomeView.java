package com.emailapp.view;

import com.emailapp.controller.LoginController;
import com.emailapp.view.functionality.MenuProvider;

import java.util.HashMap;
import java.util.Map;

public class HomeView implements BaseView, MenuProvider {

    private static final String WELCOME_MESSAGE = "Welcome to the email application";
    private static final String LOGIN_OPTION = "Login";
    private static final String EXIT_THE_APPLICATION_OPTION = "Exit the application";

    private LoginController loginController = new LoginController();

    public void render() {
        System.out.println(WELCOME_MESSAGE);
        Map<Long, String> availableOptions = new HashMap<>();
        availableOptions.put(1L, LOGIN_OPTION);
        availableOptions.put(2L, EXIT_THE_APPLICATION_OPTION);

        long choice = provideSelectionMenu(availableOptions);

        if (choice == 1) {
            loginController.getLoginView();
        } else if (choice == 2) {
            System.exit(0);
        }
    }
}
