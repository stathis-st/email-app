package com.emailapp.controller.user;

import com.emailapp.controller.BaseController;
import com.emailapp.domain.User;
import com.emailapp.exception.NotFoundException;
import com.emailapp.service.UserMessageService;

import java.sql.SQLException;

public class DeleteModeratorController extends UpdateModeratorController implements BaseController {

    private UserMessageService userMessageService = new UserMessageService();

    public void deleteMessage(User sessionUser, long chosenId) throws NotFoundException, SQLException {
        userMessageService.deleteMessageById(chosenId);
    }
}
