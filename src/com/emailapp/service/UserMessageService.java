package com.emailapp.service;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.repository.MessageRepository;
import com.emailapp.repository.MessageRepositoryImpl;
import com.emailapp.repository.UserRepository;
import com.emailapp.repository.UserRepositoryImpl;

import java.util.List;

public class UserMessageService {

    private UserRepository userRepository = new UserRepositoryImpl();
    private MessageRepository messageRepository = new MessageRepositoryImpl();

    public User getUserWithReceivedMessages(long userId) {
        User foundUser = userRepository.getOne(userId);
        List<Message> receivedMessages = messageRepository.getReceivedMessagesByUser(userId);
        foundUser.setReceivedMessages(receivedMessages);
        return foundUser;
    }
}
