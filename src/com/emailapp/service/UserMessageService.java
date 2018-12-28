package com.emailapp.service;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.domain.UserMessage;
import com.emailapp.repository.MessageRepository;
import com.emailapp.repository.MessageRepositoryImpl;
import com.emailapp.repository.UserMessageRepository;

import java.util.List;

import static com.emailapp.domain.UserMessage.RECEIVED;
import static com.emailapp.domain.UserMessage.SENT;

public class UserMessageService {

    private MessageRepository messageRepository = new MessageRepositoryImpl();
    private UserMessageRepository userMessageRepository = new UserMessageRepository();

    public User getUserWithReceivedMessages(User user) {
        List<Message> receivedMessages = messageRepository.getReceivedMessagesByUser(user.getId());
        user.setReceivedMessages(receivedMessages);
        return user;
    }

    public User getUserWithSentMessages(User user) {
        List<Message> sentMessages = messageRepository.getSentMessagesByUser(user.getId());
        user.setSentMessages(sentMessages);
        return user;
    }

    public long sendMessage(Message message) {
        long id = messageRepository.save(message);

        UserMessage sentMessage = new UserMessage();
        sentMessage.setMessageId(id);
        sentMessage.setUserId(message.getSender().getId());
        //TODO CHANGE TO ENUM
        sentMessage.setMessageType(SENT);

        UserMessage receivedMessage = new UserMessage();
        receivedMessage.setMessageId(id);
        receivedMessage.setUserId(message.getReceiver().getId());
        //TODO CHANGE TO ENUM
        receivedMessage.setMessageType(RECEIVED);

        userMessageRepository.save(sentMessage);
        userMessageRepository.save(receivedMessage);
        return id;
    }
}
