package com.emailapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class HomeController extends BaseController implements MenuProvider<HomeController> {

    private LoginController loginController = new LoginController();

    private static final String WELCOME_MESSAGE = "Welcome to the email application";

    public void welcome() {
        Map<Integer, Consumer<HomeController>> consumerMap = new HashMap<>();
        consumerMap.put(1, o -> loginController.login());
        consumerMap.put(2, o -> System.exit(0));

        provideSelectionMenu(consumerMap);
    }

    @Override
    public void provideMenu() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println("Available options: ");
        System.out.println("\tType '1' to login");
        System.out.println("\tType '2' for Registration");
        System.out.println("\tType '3' to exit the application");
    }
}
