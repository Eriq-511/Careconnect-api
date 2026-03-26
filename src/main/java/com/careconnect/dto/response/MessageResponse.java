package com.careconnect.dto.response;

import java.util.Date;

public class MessageResponse {
    private Long id;
    private Long senderId;
    private String content;
    private Date sentAt;
    private String status;

    public MessageResponse(Long id, Long senderId, String content, Date sentAt, String status) {
        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
        this.status = status;
    }

    public Long getId() { return id; }
    public Long getSenderId() { return senderId; }
    public String getContent() { return content; }
    public Date getSentAt() { return sentAt; }
    public String getStatus() { return status; }
}
