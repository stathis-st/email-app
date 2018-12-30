package com.emailapp.service;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.domain.UserMessage;
import com.emailapp.exception.NotFoundException;
import com.emailapp.repository.MessageRepository;
import com.emailapp.repository.MessageRepositoryImpl;
import com.emailapp.repository.UserMessageRepository;
import com.emailapp.repository.UserRepository;
import com.emailapp.repository.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

import static com.emailapp.domain.UserMessage.RECEIVED;
import static com.emailapp.domain.UserMessage.SENT;

public class UserMessageService {

    private MessageRepository messageRepository = new MessageRepositoryImpl();
    private UserMessageRepository userMessageRepository = new UserMessageRepository();
    private UserRepository userRepository = new UserRepositoryImpl();

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

    public long sendMessage(Message message) throws NotFoundException, SQLException {
        userRepository.getOne(message.getSender().getId());

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

    public List<UserMessage> getUserMessagesByMessage(Message message) {
        return userMessageRepository.getUserMessagesByMessageId(message.getId());
    }

    public boolean deleteUserMessage(UserMessage userMessage) {
        return userMessageRepository.delete(userMessage);
    }

    public boolean deleteMessage(Message message) {
        return messageRepository.delete(message);
    }

}