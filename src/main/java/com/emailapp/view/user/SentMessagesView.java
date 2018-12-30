package com.emailapp.view.user;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.view.functionality.MessagesRenderer;

public class SentMessagesView extends UserDashboardView implements MessagesRenderer {

    public SentMessagesView(User sessionUser) {
        super(sessionUser);
    }

    @Override
    public void render() {
        printMessages(sessionUser.getSentMessages());
        goBack();
    }

    @Override
    public void showSpecificDetailsForMessage(Message message) {
        System.out.println(String.format("\tTo: %s %s\n", message.getReceiver().getFirstName(), message.getReceiver().getLastName()));
    }
}