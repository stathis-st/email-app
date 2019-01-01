package com.emailapp.view.functionality;

import com.emailapp.domain.Message;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface MessagesRenderer {

    default void printMessages(List<Message> messages) {
        messages.forEach(this::showMessage);
    }

    default void showMessage(Message message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Message (").append(message.getId()).append(")\n");
        stringBuilder.append("\tSubject: ").append(message.getSubject()).append("\n");
        stringBuilder.append("\tMessage content: ").append(message.getMessageData()).append("\n");
        stringBuilder.append("\tDate of submission: ").append(DateTimeFormatter.ISO_DATE_TIME.format(message.getDateOfSubmission()));
        System.out.println(stringBuilder);
        showSpecificDetailsForMessage(message);
    }

    void showSpecificDetailsForMessage(Message message);
}
