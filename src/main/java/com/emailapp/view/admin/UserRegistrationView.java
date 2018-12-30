package com.emailapp.view.admin;

import com.emailapp.domain.User;
import com.emailapp.exception.ExceptionResolver;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;
import com.emailapp.view.functionality.UserEditor;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRegistrationView extends AdminDashboardView implements BaseView, MenuProvider, UserEditor, ExceptionResolver {

    private Set<String> registeredUserNames;

    public UserRegistrationView(User sessionUser, List<User> registeredUsers) {
        super(sessionUser);
        this.registeredUserNames = registeredUsers.stream().map(User::getUsername).collect(Collectors.toSet());
    }

    @Override
    public void render() {
        System.out.println("Please fill the user's personal info");

        Scanner scanner = new Scanner(System.in);
        String username = extractUsername(scanner);
        String password = extractPassword(scanner);
        String firstName = extractName(scanner, "First Name: ");
        String lastName = extractName(scanner, "Last Name: ");
        try {
            adminController.postRegisterUser(username, password, firstName, lastName);
            System.out.println("Successfully registered user");
        } catch (Exception e) {
            handleException(e);
        }
    }

    //TODO VALIDATIONS
    private String extractUsername(Scanner scanner) {
        //sessionUser.getUsername() exists for sure in registered users set
        String username = sessionUser.getUsername();
        while (registeredUserNames.contains(username)) {
            System.out.print("Username:  ");
            System.out.println(String.format("Should not be one of the following: %s", registeredUserNames));
            username = scanner.nextLine();
        }
        return username;
    }
}
