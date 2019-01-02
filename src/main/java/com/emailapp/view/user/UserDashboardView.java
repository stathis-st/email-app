package com.emailapp.view.user;

import com.emailapp.controller.user.UserController;
import com.emailapp.domain.User;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserDashboardView extends BaseView implements MenuProvider {

    private static final Map<Long, String> AVAILABLE_OPTIONS = new LinkedHashMap<>();

    {
        AVAILABLE_OPTIONS.put(1L, "Received");
        AVAILABLE_OPTIONS.put(2L, "Sent");
        AVAILABLE_OPTIONS.put(3L, "Compose");
        AVAILABLE_OPTIONS.put(4L, "Logout");
    }

    protected User sessionUser;

    protected UserController userController = UserController.getInstance();

    public UserDashboardView(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public void render() {
        clearConsole();
        System.out.println("Dear " + sessionUser.getFirstName() + " welcome to your account");
        while (true) {
            long choice = provideSelectionMenu(AVAILABLE_OPTIONS);
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
