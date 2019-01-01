package com.emailapp.controller.user;

import com.emailapp.controller.BaseController;
import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.exception.NotFoundException;
import com.emailapp.service.UserMessageService;
import com.emailapp.view.moderator.EditMessageView;

import java.sql.SQLException;
import java.util.List;

public class UpdateModeratorController extends ReadModeratorController implements BaseController {

    UserMessageService userMessageService = new UserMessageService();

    public void getMessageEditView(User sessionUser) {
        List<Message> messages = userMessageService.getAllMessages();
        new EditMessageView(sessionUser, messages).render();
    }

    public void postEditMessage(User sessionUser, Message chosenMessage) throws NotFoundException, SQLException {
        userMessageService.editMessage(chosenMessage);
    }
}
