package com.emailapp.view.admin;

import com.emailapp.controller.user.AdminController;
import com.emailapp.domain.User;
import com.emailapp.exception.ExceptionResolver;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;
import com.emailapp.view.functionality.UserEditor;
import com.emailapp.view.functionality.UsersRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EditUserView extends BaseView implements MenuProvider, UserEditor, UsersRenderer, ExceptionResolver {

    private AdminController adminController = AdminController.getInstance();

    private User sessionUser;
    private List<User> registeredUsers;

    public EditUserView(User sessionUser, List<User> registeredUsers) {
        this.sessionUser = sessionUser;
        this.registeredUsers = registeredUsers;
    }


    @Override
    public void render() {
        clearConsole();
        Map<Long, String> availableUsersToEdit = registeredUsers.stream()
                .collect(Collectors.toMap(User::getId, User::getUsername));

        long chosenId = provideSelectionMenu(availableUsersToEdit);

        User chosenUser = registeredUsers.stream()
                .filter(user -> user.getId() == chosenId)
                .findFirst().get();


        showUser(chosenUser);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose fields to edit");
            Map<Long, String> fieldsToEdit = new HashMap<>();
            fieldsToEdit.put(1L, "First Name");
            fieldsToEdit.put(2L, "Last Name");
            fieldsToEdit.put(3L, "Password");
            fieldsToEdit.put(0L, "Done");
            long choice = provideSelectionMenu(fieldsToEdit);

            if (choice == 1) {
                chosenUser.setFirstName(extractName(scanner, "First Name: "));
            } else if (choice == 2) {
                chosenUser.setLastName(extractName(scanner, "Last Name: "));
            } else if (choice == 3) {
                chosenUser.setPassword(extractPassword(scanner));
            } else if (choice == 0) {
                break;
            }
            showUser(chosenUser);
        }
        try {
            adminController.postEditUser(sessionUser, chosenUser);
            System.out.println("Successfully edited user");
        } catch (Exception e) {
            handleException(e);
            System.out.println("Failed to edit user with id " + chosenId);
        }
    }

    @Override
    public void showUser(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user.getId()).append("\t")
                .append(user.getPassword()).append("\t")
                .append(user.getFirstName()).append("\t")
                .append(user.getLastName());
        System.out.println(stringBuilder);
    }
}
