package com.emailapp.domain;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message extends Entity implements FileEntity{

    private String messageData;
    private String subject;
    private LocalDateTime dateOfSubmission;

    private User receiver;
    private User sender;

    private String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);


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
        return "Message{" +
                "messageData='" + messageData + '\'' +
                ", subject='" + subject + '\'' +
                ", dateOfSubmission=" + dateOfSubmission +
                ", receiver=" + receiver +
                ", sender=" + sender +
                '}';
    }

    public String getMessageInfo() {
        String messageInfo = "Subject: " + subject + "\t" +
                "Message Content: " + messageData + "\t" +
                "Sender: " + sender.getFirstName() + " " + sender.getLastName() + "\t" +
                "Receiver: " + receiver.getFirstName() + " " + receiver.getLastName() + "\t" +
                "Date Of Submission: " + dateOfSubmission.format(formatter);
        return messageInfo;
    }

    @Override
    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.id).append(",")
                .append(subject).append(",")
                .append(messageData).append(",")
                .append(sender.getFirstName()).append(" ").append(sender.getLastName()).append(",")
                .append(receiver.getFirstName()).append(" ").append(receiver.getLastName()).append(",")
                .append(dateOfSubmission.format(formatter));

        return stringBuilder.toString();
    }

    @Override
    public String getBaseDirectory() {

        StringBuilder path = new StringBuilder();
        path.append(".")
                .append(File.separator)
                .append("messages")
                .append(File.separator);

        return path.toString();
    }
}
