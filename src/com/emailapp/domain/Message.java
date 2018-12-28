package com.emailapp.domain;


import java.time.LocalDateTime;

public class Message extends Entity {

  private long id;
  private long senderId;
  private long receiverId;
  private boolean deletedFromSender;
  private boolean deletedFromReceiver;
  private String messageData;
  private String subject;
  private LocalDateTime dateOfSubmission;

  public Message() {
  }

  public Message(long senderId, long receiverId, boolean deletedFromSender, boolean deletedFromReceiver, String messageData,
                 String subject, LocalDateTime dateOfSubmission) {
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.deletedFromSender = deletedFromSender;
    this.deletedFromReceiver = deletedFromReceiver;
    this.messageData = messageData;
    this.subject = subject;
    this.dateOfSubmission = dateOfSubmission;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getSenderId() {
    return senderId;
  }

  public void setSenderId(long senderId) {
    this.senderId = senderId;
  }

  public long getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(long receiverId) {
    this.receiverId = receiverId;
  }

  public boolean isDeletedFromSender() {
    return deletedFromSender;
  }

  public void setDeletedFromSender(boolean deletedFromSender) {
    this.deletedFromSender = deletedFromSender;
  }

  public boolean isDeletedFromReceiver() {
    return deletedFromReceiver;
  }

  public void setDeletedFromReceiver(boolean deletedFromReceiver) {
    this.deletedFromReceiver = deletedFromReceiver;
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
}
