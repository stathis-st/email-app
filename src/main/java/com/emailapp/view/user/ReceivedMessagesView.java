package com.emailapp.view.user;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.view.functionality.MessagesRenderer;

public class ReceivedMessagesView extends UserDashboardView implements MessagesRenderer {

    public ReceivedMessagesView(User sessionUser) {
        super(sessionUser);
    }

    @Override
    public void render() {
        printMessages(sessionUser.getReceivedMessages());
        goBack();
    }

    @Override
    public void showSpecificDetailsForMessage(Message message) {
        System.out.println(String.format("\tFrom: %s %s\n", message.getSender().getFirstName(), message.getSender().getLastName()));
    }
}