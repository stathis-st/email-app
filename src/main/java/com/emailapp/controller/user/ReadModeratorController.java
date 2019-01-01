package com.emailapp.controller.user;

import com.emailapp.controller.BaseController;
import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.service.UserMessageService;
import com.emailapp.view.moderator.MessagesView;
import com.emailapp.view.moderator.ModeratorDashboardView;

import java.util.List;

public class ReadModeratorController implements BaseController {

    private UserMessageService userMessageService = new UserMessageService();

    public void getBaseModeratorView(User sessionUser) {
        new ModeratorDashboardView(sessionUser).render();
    }

    public void showAllMessages(User sessionUser) {
        List<Message> messages = userMessageService.getAllMessages();
        new MessagesView(sessionUser, messages).render();
    }
}
