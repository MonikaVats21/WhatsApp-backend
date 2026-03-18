package com.whatsapp.chatservice.repository;

import com.whatsapp.chatservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Get all messages between two users (chat history)
    @Query("SELECT m FROM Message m WHERE " +
            "(m.senderId = :userId1 AND m.receiverId = :userId2) OR " +
            "(m.senderId = :userId2 AND m.receiverId = :userId1) " +
            "ORDER BY m.sentAt ASC")
    List<Message> findChatHistory(@Param("userId1") Long userId1,
                                  @Param("userId2") Long userId2);

    // Get all messages in a group
    List<Message> findByGroupIdOrderBySentAtAsc(Long groupId);

    // Get unread messages for a user
    List<Message> findByReceiverIdAndStatus(Long receiverId,
                                            Message.MessageStatus status);
}
