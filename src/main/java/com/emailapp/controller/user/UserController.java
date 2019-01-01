package com.emailapp.controller.user;

import com.emailapp.controller.BaseController;
import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.exception.NotFoundException;
import com.emailapp.service.UserMessageService;
import com.emailapp.service.UserService;
import com.emailapp.view.user.ComposeMessageView;
import com.emailapp.view.user.ReceivedMessagesView;
import com.emailapp.view.user.SentMessagesView;
import com.emailapp.view.user.UserDashboardView;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class UserController implements BaseController {

    private static UserController userControllerInstance;

    private UserMessageService userMessageService = UserMessageService.getInstance();
    private UserService userService = UserService.getInstance();

    private UserController() {
    }

    public static UserController getInstance() {
        if (userControllerInstance == null) {
            userControllerInstance = new UserController();
        }
        return userControllerInstance;
    }

    public void getUserDashboardView(User user) {
        new UserDashboardView(user).render();
    }

    public void showReceivedMessagesOfUser(User user) {
        User userWithMessages = userMessageService.getUserWithReceivedMessages(user);
        new ReceivedMessagesView(userWithMessages).render();
    }

    public void showSentMessagesOfUser(User user) {
        User userWithMessages = userMessageService.getUserWithSentMessages(user);
        new SentMessagesView(userWithMessages).render();
    }

    public void getComposeMessageView(User fromUser) {
        List<User> availableUsers = userService.getAllSimpleUsers();
        new ComposeMessageView(fromUser, availableUsers).render();
    }

    public void composeMessage(User fromUser, Message message) throws NotFoundException, SQLException {
        message.setDateOfSubmission(LocalDateTime.now());
        message.setSender(fromUser);
        userMessageService.sendMessage(message);
    }


}
