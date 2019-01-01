package com.emailapp.controller.user;

import com.emailapp.controller.BaseController;
import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.service.UserMessageService;
import com.emailapp.view.moderator.MessagesView;
import com.emailapp.view.moderator.ModeratorDashboardView;

import java.util.List;

public class ReadModeratorController implements BaseController {

    private static ReadModeratorController readModeratorControllerInstance;

    private UserMessageService userMessageService = UserMessageService.getInstance();

    protected ReadModeratorController() {
    }

    public static synchronized ReadModeratorController getInstance() {
        if (readModeratorControllerInstance == null) {
            readModeratorControllerInstance = new ReadModeratorController();
        }
        return readModeratorControllerInstance;
    }


    public void getBaseModeratorView(User sessionUser) {
        new ModeratorDashboardView(sessionUser).render();
    }

    public void showAllMessages(User sessionUser) {
        List<Message> messages = userMessageService.getAllMessages();
        new MessagesView(sessionUser, messages).render();
    }
}
