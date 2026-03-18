package com.whatsapp.userservice.controller;

import com.whatsapp.userservice.entity.User;
import com.whatsapp.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<User> updateStatus(@PathVariable Long id,
                                             @RequestParam User.OnlineStatus status) {
        return ResponseEntity.ok(userService.updateOnlineStatus(id, status));
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<User> updateProfile(@PathVariable Long id,
                                              @RequestBody User user) {
        return ResponseEntity.ok(userService.updateProfile(id, user));
    }
}