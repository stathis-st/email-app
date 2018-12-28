package com.emailapp.controller;

import com.emailapp.domain.User;
import com.emailapp.repository.UserRepositoryImpl;

import java.util.Scanner;

public class RegistrationController {

    public void register() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please fill in your personal info");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        UserRepositoryImpl userDAO = new UserRepositoryImpl();

        User user = new User(username, password, lastName, firstName);

        long id = userDAO.save(user);

        if (id > 0) {
            System.out.println("Dear " + user.getFirstName() + " thank you for registering to the email application!");
        } else {
            System.out.println("something went wrong");
        }

    }
}
