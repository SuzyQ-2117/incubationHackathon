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

    //Create
    public UserDTO addUser(User user) {
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    //Read by ID
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    //Login verification
    public boolean verifyUserCredentials(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> value.getPassword().equals(password)).orElse(false);
    }

    // Update user details
    public UserDTO updateUserDetails(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getIdentityConfirmed()) {
            // Allow updates to title, first name(s), and surname if identity is not confirmed
            user.setTitle(userDTO.getTitle());
            user.setFirstName(userDTO.getFirstName());
            user.setSurname(userDTO.getSurname());
        }

        // Always allow updates to the following fields
        user.setPreferredName(userDTO.getPreferredName());
        user.setContactNumber(userDTO.getContactNumber());
        user.setAddress(userDTO.getAddress());
        user.setMarketingEmail(userDTO.getMarketingEmail());
        user.setPushNotifications(userDTO.getPushNotifications());
        user.setMarketingText(userDTO.getMarketingText());

        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser);
    }

    // Confirm user identity (one-time update)
    public void confirmUserIdentity(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getIdentityConfirmed()) {
            user.setIdentityConfirmed(true);
            userRepository.save(user);
        }
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
