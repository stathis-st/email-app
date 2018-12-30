package com.emailapp.view.admin;

import com.emailapp.controller.user.AdminController;
import com.emailapp.domain.User;
import com.emailapp.exception.ExceptionResolver;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeleteUserView implements BaseView, MenuProvider, ExceptionResolver {

    private AdminController adminController = new AdminController();

    private User sessionUser;
    private List<User> registeredUsers;

    public DeleteUserView(User sessionUser, List<User> registeredUsers) {
        this.sessionUser = sessionUser;
        this.registeredUsers = registeredUsers;
    }

    @Override
    public void render() {
        Map<Long, String> availableUsersToDelete = registeredUsers.stream()
                .collect(Collectors.toMap(User::getId, User::getUsername));
        long chosenId = provideSelectionMenu(availableUsersToDelete);
        try {
            new AdminController().deleteUser(chosenId);
            System.out.println("Successfully deleted user with id " + chosenId);
        } catch (Exception e) {
            handleException(e);
            System.out.println("Failed to delete user.");
        }
    }
}
