package com.whatsapp.chatservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = true)
    private Long receiverId;

    private Long groupId;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    private LocalDateTime sentAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime readAt;

    public enum MessageType {
        TEXT, IMAGE, VIDEO, AUDIO, DOCUMENT
    }

    public enum MessageStatus {
        SENT, DELIVERED, READ
    }
}