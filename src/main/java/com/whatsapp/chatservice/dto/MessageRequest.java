package com.whatsapp.chatservice.dto;

import lombok.Data;

@Data
public class MessageRequest {
    private Long senderId;
    private Long receiverId;
    private Long groupId;
    private String content;
    private String messageType;
}
