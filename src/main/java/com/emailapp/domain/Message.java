package com.emailapp.domain;


import java.time.LocalDateTime;

public class Message extends Entity implements FileEntity{

    private String messageData;
    private String subject;
    private LocalDateTime dateOfSubmission;

    private User receiver;
    private User sender;

    public Message() {
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDateOfSubmission(LocalDateTime dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message id=" + id +
                ", messageData='" + messageData + '\'' +
                ", subject='" + subject + '\'' +
                ", dateOfSubmission=" + dateOfSubmission +
                ", receiver=" + receiver +
                ", sender=" + sender;
    }

    @Override
    public String getContent() {
        return this.toString();
    }

    @Override
    public String getBaseDirectory() {
        return "D:\\JavaPrograms\\email-app\\messages\\";
    }
}
