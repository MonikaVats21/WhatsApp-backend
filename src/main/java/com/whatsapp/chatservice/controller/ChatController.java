package com.whatsapp.chatservice.controller;

import com.whatsapp.chatservice.dto.GroupRequest;
import com.whatsapp.chatservice.dto.MessageRequest;
import com.whatsapp.chatservice.entity.Group;
import com.whatsapp.chatservice.entity.Message;
import com.whatsapp.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // Send 1-to-1 message
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) {
        return ResponseEntity.ok(chatService.sendMessage(request));
    }

    // Get chat history between two users
    @GetMapping("/history/{userId1}/{userId2}")
    public ResponseEntity<List<Message>> getChatHistory(
            @PathVariable Long userId1,
            @PathVariable Long userId2) {
        return ResponseEntity.ok(chatService.getChatHistory(userId1, userId2));
    }

    // Mark message as delivered
    @PutMapping("/{messageId}/delivered")
    public ResponseEntity<Message> markDelivered(@PathVariable Long messageId) {
        return ResponseEntity.ok(chatService.markDelivered(messageId));
    }

    // Mark message as read
    @PutMapping("/{messageId}/read")
    public ResponseEntity<Message> markRead(@PathVariable Long messageId) {
        return ResponseEntity.ok(chatService.markRead(messageId));
    }

    // Get unread messages for a user
    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<Message>> getUnreadMessages(@PathVariable Long userId) {
        return ResponseEntity.ok(chatService.getUnreadMessages(userId));
    }

    // Create a group
    @PostMapping("/group")
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest request) {
        return ResponseEntity.ok(chatService.createGroup(request));
    }

    // Get group details
    @GetMapping("/group/{groupId}")
    public ResponseEntity<Group> getGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(chatService.getGroupById(groupId));
    }

    // Get all groups for a user
    @GetMapping("/group/user/{userId}")
    public ResponseEntity<List<Group>> getUserGroups(@PathVariable Long userId) {
        return ResponseEntity.ok(chatService.getUserGroups(userId));
    }

    // Send message to group
    @PostMapping("/group/send")
    public ResponseEntity<Message> sendGroupMessage(@RequestBody MessageRequest request) {
        return ResponseEntity.ok(chatService.sendGroupMessage(request));
    }

    // Get group chat history
    @GetMapping("/group/{groupId}/messages")
    public ResponseEntity<List<Message>> getGroupMessages(@PathVariable Long groupId) {
        return ResponseEntity.ok(chatService.getGroupMessages(groupId));
    }
}