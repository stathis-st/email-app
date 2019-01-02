package com.emailapp.view.admin;

import com.emailapp.controller.user.AdminController;
import com.emailapp.domain.User;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdminDashboardView implements BaseView, MenuProvider {
    
    private static final Map<Long, String> AVAILABLE_OPTIONS = new LinkedHashMap<>();
    
    {
        AVAILABLE_OPTIONS.put(1L, "Register a new User");
        AVAILABLE_OPTIONS.put(2L, "View all users");
        AVAILABLE_OPTIONS.put(3L, "Edit User's info");
        AVAILABLE_OPTIONS.put(4L, "Delete User");
        AVAILABLE_OPTIONS.put(0L, "Logout");
    }

    protected User sessionUser;

    protected AdminController adminController = AdminController.getInstance();

    public AdminDashboardView(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public void render() {
        System.out.println("Dear administrator " + sessionUser.getFirstName() + " welcome to your account");
        while (true) {
            long choice = provideSelectionMenu(AVAILABLE_OPTIONS);
            if (choice == 1) {
                adminController.getUserRegistrationView(sessionUser);
            } else if (choice == 2) {
                adminController.showAllUsers(sessionUser);
            } else if (choice == 3) {
                adminController.getUserEditView(sessionUser);
            } else if (choice == 4) {
                adminController.getUserDeleteView(sessionUser);
            } else if (choice == 5) {
                return;
            }
        }
    }

}
