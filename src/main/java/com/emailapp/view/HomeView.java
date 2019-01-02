package com.emailapp.view;

import com.emailapp.controller.LoginController;
import com.emailapp.view.functionality.MenuProvider;

import java.util.LinkedHashMap;
import java.util.Map;

public class HomeView extends BaseView implements MenuProvider {

    private static final String WELCOME_MESSAGE = "Welcome to the email application";
    private static final String LOGIN_OPTION = "Login";
    private static final String EXIT_THE_APPLICATION_OPTION = "Exit the application";
    private static final Map<Long, String> AVAILABLE_OPTIONS = new LinkedHashMap<>();

    {
        AVAILABLE_OPTIONS.put(1L, LOGIN_OPTION);
        AVAILABLE_OPTIONS.put(2L, EXIT_THE_APPLICATION_OPTION);
    }

    private LoginController loginController = LoginController.getInstance();

    @Override
    public void render() {
        clearConsole();
        while (true) {
            System.out.println(WELCOME_MESSAGE);
            long choice = provideSelectionMenu(AVAILABLE_OPTIONS);

            if (choice == 1) {
                loginController.getLoginView();
            } else if (choice == 2) {
                return;
            }
        }
    }
}
