package com.emailapp.controller;

import com.emailapp.controller.user.AdminController;
import com.emailapp.controller.user.UserController;
import com.emailapp.domain.User;
import com.emailapp.exception.InvalidCredentialsException;
import com.emailapp.service.UserService;

import java.io.Console;
import java.util.Scanner;

import static com.emailapp.domain.Role.ADMIN_TYPE;
import static com.emailapp.domain.Role.USER_TYPE;

public class LoginController extends BaseController {

    private static final int MAXIMUM_LOGIN_TRIES = 3;
    private static final String FAILED_LOGIN_MESSAGE = String.format("You reached the maximum login tries (%s). Exiting application...", MAXIMUM_LOGIN_TRIES);

    private UserService userService = new UserService();
    private UserController userController = UserController.getInstance();
    private AdminController adminController = null;

    public void login() {
        int i = 0;
        while (i++ < MAXIMUM_LOGIN_TRIES) {
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

            try {
                User user = userService.login(username, password);
                System.out.println("Dear " + user.getFirstName() + " welcome to your account");
                switch (user.getRole().getRoleType()) {
                    case USER_TYPE:
                        userController.showMenuForUser(user);
                        break;
                    case ADMIN_TYPE:
                        userController.showMenuForUser(user);
                        break;
                    default:
                        break;
                }
            } catch (InvalidCredentialsException exception) {
                exception.printStackTrace();
            }

        }
        System.out.println(FAILED_LOGIN_MESSAGE);
    }
}
