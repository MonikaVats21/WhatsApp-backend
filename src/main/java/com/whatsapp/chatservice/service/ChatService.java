package com.whatsapp.chatservice.service;

import com.whatsapp.chatservice.dto.GroupRequest;
import com.whatsapp.chatservice.dto.MessageRequest;
import com.whatsapp.chatservice.entity.Group;
import com.whatsapp.chatservice.entity.Message;
import com.whatsapp.chatservice.repository.GroupRepository;
import com.whatsapp.chatservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MessageRepository messageRepository;
    private final GroupRepository groupRepository;

    // Send a message
    public Message sendMessage(MessageRequest request) {
        Message message = Message.builder()
                .senderId(request.getSenderId())
                .receiverId(request.getReceiverId())
                .groupId(request.getGroupId())
                .content(request.getContent())
                .messageType(Message.MessageType.valueOf(
                        request.getMessageType() != null ?
                                request.getMessageType() : "TEXT"))
                .status(Message.MessageStatus.SENT)
                .sentAt(LocalDateTime.now())
                .build();

        return messageRepository.save(message);
    }

    // Get chat history between two users
    public List<Message> getChatHistory(Long userId1, Long userId2) {
        return messageRepository.findChatHistory(userId1, userId2);
    }

    // Mark message as delivered
    public Message markDelivered(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found!"));
        message.setStatus(Message.MessageStatus.DELIVERED);
        message.setDeliveredAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // Mark message as read
    public Message markRead(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found!"));
        message.setStatus(Message.MessageStatus.READ);
        message.setReadAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // Get unread messages
    public List<Message> getUnreadMessages(Long userId) {
        return messageRepository.findByReceiverIdAndStatus(
                userId, Message.MessageStatus.SENT);
    }

    // Create a group
    public Group createGroup(GroupRequest request) {
        Group group = Group.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdBy(request.getCreatedBy())
                .memberIds(request.getMemberIds())
                .createdAt(LocalDateTime.now())
                .build();
        return groupRepository.save(group);
    }

    // Get group by ID
    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found!"));
    }

    // Get all groups for a user
    public List<Group> getUserGroups(Long userId) {
        return groupRepository.findGroupsByMemberId(userId);
    }

    // Send message to group
    public Message sendGroupMessage(MessageRequest request) {
        // verify group exists
        groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found!"));

        Message message = Message.builder()
                .senderId(request.getSenderId())
                .groupId(request.getGroupId())
                .content(request.getContent())
                .messageType(Message.MessageType.TEXT)
                .status(Message.MessageStatus.SENT)
                .sentAt(LocalDateTime.now())
                .build();

        return messageRepository.save(message);
    }

    // Get group chat history
    public List<Message> getGroupMessages(Long groupId) {
        return messageRepository.findByGroupIdOrderBySentAtAsc(groupId);
    }
}
