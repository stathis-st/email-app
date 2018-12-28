package com.emailapp.repository;

import com.emailapp.domain.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message> {

    List<Message> getReceivedMessagesByUser(long userId);

    List<Message> getSentMessagesByUser(long userId);
}
