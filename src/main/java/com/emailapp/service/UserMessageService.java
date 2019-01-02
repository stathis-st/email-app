package com.emailapp.service;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.domain.UserMessage;
import com.emailapp.exception.NotFoundException;
import com.emailapp.repository.FileRepository;
import com.emailapp.repository.FileRepositoryImpl;
import com.emailapp.repository.MessageRepository;
import com.emailapp.repository.MessageRepositoryImpl;
import com.emailapp.repository.UserMessageRepository;
import com.emailapp.repository.UserRepository;
import com.emailapp.repository.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.emailapp.domain.UserMessage.RECEIVED;
import static com.emailapp.domain.UserMessage.SENT;

public class UserMessageService {


    private static UserMessageService userMessageServiceInstance;

    private MessageRepository messageRepository = MessageRepositoryImpl.getInstance();
    private UserMessageRepository userMessageRepository = UserMessageRepository.getInstance();
    private UserRepository userRepository = UserRepositoryImpl.getInstance();
    private FileRepository fileRepository = FileRepositoryImpl.getInstance();

    private UserMessageService() {
    }

    public static UserMessageService getInstance() {
        if (userMessageServiceInstance == null) {
            userMessageServiceInstance = new UserMessageService();
        }
        return userMessageServiceInstance;
    }

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

    public List<Message> getAllMessages() {
        return userRepository.getAll().stream()
                .map(user -> {
                    List<Message> messages = messageRepository.getReceivedMessagesByUser(user.getId());
                    messages.forEach(message -> message.setReceiver(user));
                    return messages;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public long sendMessage(Message message) throws NotFoundException, SQLException {
        userRepository.getOne(message.getSender().getId());

        long id = messageRepository.save(message);
        message.setId(id);

        UserMessage sentMessage = new UserMessage();
        sentMessage.setMessageId(id);
        sentMessage.setUserId(message.getSender().getId());
        sentMessage.setMessageType(SENT);

        UserMessage receivedMessage = new UserMessage();
        receivedMessage.setMessageId(id);
        receivedMessage.setUserId(message.getReceiver().getId());
        receivedMessage.setMessageType(RECEIVED);

        userMessageRepository.save(sentMessage);
        userMessageRepository.save(receivedMessage);

        fileRepository.writeToFile(message);
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

    public void deleteMessageById(long chosenId) throws NotFoundException, SQLException {
        Message message = messageRepository.getOne(chosenId);
        List<UserMessage> userMessages = getUserMessagesByMessage(message);
        userMessages.forEach(this::deleteUserMessage);
        deleteMessage(message);
    }

    public Message getMessageById(long id) throws NotFoundException, SQLException {
        return messageRepository.getOne(id);
    }

    public void editMessage(Message chosenMessage) throws NotFoundException, SQLException {
        getMessageById(chosenMessage.getId());
        messageRepository.update(chosenMessage);
    }
}
