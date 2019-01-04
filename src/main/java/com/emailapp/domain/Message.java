package com.emailapp.domain;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message extends Entity implements FileEntity {

    private static final String CONTENT = "%s,%s,%s,%s %s,%s %s,%s";
    private static final String BASE_DIRECTORY = "." + File.separator + "messages" + File.separator;

    private String messageData;
    private String subject;
    private LocalDateTime dateOfSubmission;

    private User receiver;
    private User sender;

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

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
        return "Subject: " + subject + "\n\t" +
                "Message Content: " + messageData + "\n\t" +
                "Sender: " + sender.getFirstName() + " " + sender.getLastName() + "\n\t" +
                "Receiver: " + receiver.getFirstName() + " " + receiver.getLastName() + "\n\t" +
                "Date Of Submission: " + dateOfSubmission.format(FORMATTER) + "\n";
    }

    @Override
    public String getContent() {
        return String.format(CONTENT,
                this.id, subject, messageData, sender.getFirstName(), sender.getLastName(), receiver.getFirstName(), receiver.getLastName(), dateOfSubmission.format(FORMATTER));
    }

    @Override
    public String getBaseDirectory() {
        return BASE_DIRECTORY;
    }
}
