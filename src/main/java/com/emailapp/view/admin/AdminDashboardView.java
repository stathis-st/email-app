package com.emailapp.view.admin;

import com.emailapp.controller.user.AdminController;
import com.emailapp.controller.user.ReadModeratorController;
import com.emailapp.domain.User;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;

import java.util.HashMap;
import java.util.Map;

public class AdminDashboardView implements BaseView, MenuProvider {

    protected User sessionUser;

    protected AdminController adminController = new AdminController();
    private ReadModeratorController readModeratorController = new ReadModeratorController();

    public AdminDashboardView(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public void render() {
        System.out.println("Dear administrator " + sessionUser.getFirstName() + " welcome to your account");
        Map<Long, String> availableOptions = new HashMap<>();
        availableOptions.put(1L, "Register a new User");
        availableOptions.put(2L, "View all users");
        availableOptions.put(3L, "Edit User's info");
        availableOptions.put(4L, "Delete User");
        availableOptions.put(5L, "Manage Messages");
        availableOptions.put(6L, "Logout");
        while (true) {
            long choice = provideSelectionMenu(availableOptions);
            if (choice == 1) {
                adminController.getUserRegistrationView(sessionUser);
            } else if (choice == 2) {
                adminController.showAllUsers(sessionUser);
            } else if (choice == 3) {
                adminController.getUserEditView(sessionUser);
            } else if (choice == 4) {
                adminController.getUserDeleteView(sessionUser);
            } else if (choice == 5) {
                readModeratorController.getBaseModeratorView(sessionUser);
            } else if (choice == 6) {
                return;
            }
        }
    }

}
