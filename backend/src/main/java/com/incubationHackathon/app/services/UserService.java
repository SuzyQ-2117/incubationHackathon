package com.incubationHackathon.app.services;

import com.incubationHackathon.app.dtos.UserDTO;
import com.incubationHackathon.app.entities.User;
import com.incubationHackathon.app.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    public UserDTO addUser(User user) {
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public boolean verifyUserCredentials(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> value.getPassword().equals(password)).orElse(false);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getTitle(),
                user.getFirstName(),
                user.getSurname(),
                user.getPreferredName(),
                user.getDob(),
                user.getAddress(),
                user.getContactNumber(),
                user.getIdentityConfirmed(),
                user.getUsername(),
                user.getMarketingEmail(),
                user.getPushNotifications(),
                user.getMarketingText()
        );
    }
}
