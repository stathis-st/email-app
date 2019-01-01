package com.emailapp.controller.user;

import com.emailapp.controller.BaseController;
import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.exception.NotFoundException;
import com.emailapp.service.UserMessageService;
import com.emailapp.view.moderator.DeleteMessageView;

import java.sql.SQLException;
import java.util.List;

public class DeleteModeratorController extends UpdateModeratorController implements BaseController {

    private UserMessageService userMessageService = new UserMessageService();

    public void getMessageDeleteView(User sessionUser) {
        List<Message> messages = userMessageService.getAllMessages();
        new DeleteMessageView(sessionUser, messages).render();
    }

    public void postDeleteMessage(User sessionUser, long chosenId) throws NotFoundException, SQLException {
        userMessageService.deleteMessageById(chosenId);
    }
}
