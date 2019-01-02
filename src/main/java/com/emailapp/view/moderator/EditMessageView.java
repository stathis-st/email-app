package com.emailapp.view.moderator;

import com.emailapp.controller.user.UpdateModeratorController;
import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.exception.ExceptionResolver;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;
import com.emailapp.view.functionality.MessageEditor;
import com.emailapp.view.functionality.MessagesRenderer;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EditMessageView extends BaseView implements MenuProvider, MessagesRenderer, MessageEditor, ExceptionResolver {

    private UpdateModeratorController updateModeratorController = UpdateModeratorController.getInstance();

    private User sessionUser;
    private List<Message> messages;

    public EditMessageView(User sessionUser, List<Message> messages) {
        this.sessionUser = sessionUser;
        this.messages = messages;
    }

    @Override
    public void render() {
        clearConsole();
        Map<Long, String> availableMessagesToEdit = messages.stream()
                .collect(Collectors.toMap(Message::getId, Message::getMessageInfo));

        long chosenId = provideSelectionMenu(availableMessagesToEdit);

        Message chosenMessage = messages.stream()
                .filter(message -> message.getId() == chosenId)
                .findFirst().get();

        showMessage(chosenMessage);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Edit message content: ");
        chosenMessage.setMessageData(extractMessage(scanner));

        showMessage(chosenMessage);

        try {
            updateModeratorController.postEditMessage(sessionUser, chosenMessage);
            System.out.println("Successfully edited message");
        } catch (Exception e) {
            handleException(e);
            System.out.println("Failed to edit message with id " + chosenId);
        }

    }


    @Override
    public void showSpecificDetailsForMessage(Message message) {

    }
}
