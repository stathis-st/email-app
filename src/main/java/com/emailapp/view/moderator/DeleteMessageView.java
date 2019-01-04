package com.emailapp.view.moderator;

import com.emailapp.controller.user.DeleteModeratorController;
import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.exception.ExceptionResolver;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;
import com.emailapp.view.functionality.MessageEditor;
import com.emailapp.view.functionality.MessagesRenderer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeleteMessageView extends BaseView implements MenuProvider, MessagesRenderer, MessageEditor, ExceptionResolver {

    private DeleteModeratorController deleteModeratorController = DeleteModeratorController.getInstance();

    private User sessionUser;
    private List<Message> messages;

    public DeleteMessageView(User sessionUser, List<Message> messages) {
        this.sessionUser = sessionUser;
        this.messages = messages;
    }

    @Override
    public void render() {
        clearConsole();
        Map<Long, String> availableMessagesToDelete = new LinkedHashMap<>();
        for (Message message : messages) {
            availableMessagesToDelete.put(message.getId(), message.getMessageInfo());
        }

        long chosenId = provideSelectionMenu(availableMessagesToDelete);
        try {
            deleteModeratorController.postDeleteMessage(sessionUser, chosenId);
            System.out.println("Successfully deleted message with id " + chosenId);
        } catch (Exception e) {
            handleException(e);
            System.out.println("Failed to delete message.");
        }
    }

    @Override
    public void showSpecificDetailsForMessage(Message message) {

    }
}
