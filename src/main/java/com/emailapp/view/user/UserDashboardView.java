package com.emailapp.view.user;

import com.emailapp.controller.user.UserController;
import com.emailapp.domain.User;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;

import java.util.HashMap;
import java.util.Map;

public class UserDashboardView implements BaseView, MenuProvider {

    protected User sessionUser;

    protected UserController userController = UserController.getInstance();

    public UserDashboardView(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public void render() {
        System.out.println("Dear " + sessionUser.getFirstName() + " welcome to your account");
        Map<Long, String> availableOptions = new HashMap<>();
        availableOptions.put(1L, "Received");
        availableOptions.put(2L, "Sent");
        availableOptions.put(3L, "Compose");
        availableOptions.put(4L, "Logout");
        while (true) {
            long choice = provideSelectionMenu(availableOptions);
            if (choice == 1) {
                userController.showReceivedMessagesOfUser(sessionUser);
            } else if (choice == 2) {
                userController.showSentMessagesOfUser(sessionUser);
            } else if (choice == 3) {
                userController.getComposeMessageView(sessionUser);
            } else if (choice == 4) {
                return;
            }
        }
    }
}
