package com.emailapp.repositories;

import com.emailapp.domain.Message;

import java.util.ArrayList;

public interface MessageRepository extends Crud<Message> {

    int updateSenderFlag(long id);

    int updateReceiverFlag(long id);

    int editMessageContent(long id, String newMessage);

    ArrayList<StringBuilder> getUserInbox(long userId);

    ArrayList<StringBuilder> getUserSent(long userId);
}
