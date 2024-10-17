package com.incubationHackathon.Users.services;

import com.incubationHackathon.Users.entities.User;
import com.incubationHackathon.Users.repos.UserRepository;
import com.incubationHackathon.users.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CRUD operations and business logic
    public UserDTO addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    public Long getUserIdByUsername(String username) {
        // Assuming User entity has a getId() method and username is unique
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserId();
    }

    public UserDTO updateUserDetails(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getIdentityConfirmed()) {
            user.setTitle(userDTO.getTitle());
            user.setFirstName(userDTO.getFirstName());
            user.setSurname(userDTO.getSurname());
        }

        user.setPreferredName(userDTO.getPreferredName());
        user.setContactNumber(userDTO.getContactNumber());
        user.setAddress(userDTO.getAddress());
        user.setMarketingEmail(userDTO.getMarketingEmail());
        user.setPushNotifications(userDTO.getPushNotifications());
        user.setMarketingText(userDTO.getMarketingText());

        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser);
    }

    public void confirmUserIdentity(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getIdentityConfirmed()) {
            user.setIdentityConfirmed(true);
            userRepository.save(user);
        }
    }

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    public boolean verifyUserCredentials(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return passwordEncoder.matches(password, user.getPassword());
    }
}
