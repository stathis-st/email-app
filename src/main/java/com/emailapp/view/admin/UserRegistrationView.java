package com.emailapp.view.admin;

import com.emailapp.domain.Role;
import com.emailapp.domain.User;
import com.emailapp.exception.ExceptionResolver;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;
import com.emailapp.view.functionality.UserEditor;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRegistrationView extends AdminDashboardView implements BaseView, MenuProvider, UserEditor, ExceptionResolver {

    private Set<String> registeredUserNames;
    private List<Role> availableRoles;

    public UserRegistrationView(User sessionUser, List<User> registeredUsers, List<Role> availableRoles) {
        super(sessionUser);
        this.registeredUserNames = registeredUsers.stream().map(User::getUsername).collect(Collectors.toSet());
        this.availableRoles = availableRoles;
    }

    @Override
    public void render() {
        System.out.println("Please fill the user's personal info");

        Scanner scanner = new Scanner(System.in);
        String username = extractUsername(scanner, registeredUserNames);
        String password = extractPassword(scanner);
        String firstName = extractName(scanner, "First Name: ");
        String lastName = extractName(scanner, "Last Name: ");
        long roleId = extractRoleId();
        try {
            adminController.postRegisterUser(username, password, firstName, lastName, roleId);
            System.out.println("Successfully registered user");
        } catch (Exception e) {
            handleException(e);
        }
    }

    private long extractRoleId() {
        Map<Long, String> map = availableRoles.stream()
                .collect(Collectors.toMap(Role::getId, Role::getRoleType));
        return provideSelectionMenu(map);
    }

}
