package com.whatsapp.chatservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chat_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String groupIcon;

    @Column(nullable = false)
    private Long createdBy;

    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(name = "group_members",
            joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "member_id")
    private List<Long> memberIds;
}
