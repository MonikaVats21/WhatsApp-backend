package com.whatsapp.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String phone;

    private String displayName;
    private String statusMessage;
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private OnlineStatus onlineStatus;

    private LocalDateTime lastSeen;
    private LocalDateTime createdAt;

    public enum OnlineStatus {
        ONLINE, OFFLINE
    }
}
