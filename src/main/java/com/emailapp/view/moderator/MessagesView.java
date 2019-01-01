package com.emailapp.view.moderator;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MessagesRenderer;

import java.util.List;

public class MessagesView implements BaseView, MessagesRenderer {

    private User sessionUser;
    private List<Message> messages;

    public MessagesView(User sessionUser, List<Message> messages) {
        this.sessionUser = sessionUser;
        this.messages = messages;
    }

    @Override
    public void render() {
        printMessages(messages);
    }

    @Override
    public void showSpecificDetailsForMessage(Message message) {
        String receiver = "From " + message.getReceiver().getFirstName() + " " + message.getReceiver().getLastName();
        String sender = "To " + message.getSender().getFirstName() + " " + message.getSender().getLastName();
        System.out.println(receiver);
        System.out.println(sender);
    }
}
