package com.whatsapp.userservice.service;

import com.whatsapp.userservice.entity.User;
import com.whatsapp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setOnlineStatus(User.OnlineStatus.OFFLINE);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateOnlineStatus(Long id, User.OnlineStatus status) {
        User user = getUserById(id);
        user.setOnlineStatus(status);
        user.setLastSeen(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User updateProfile(Long id, User updatedUser) {
        User user = getUserById(id);
        user.setDisplayName(updatedUser.getDisplayName());
        user.setStatusMessage(updatedUser.getStatusMessage());
        user.setProfilePicture(updatedUser.getProfilePicture());
        return userRepository.save(user);
    }
}