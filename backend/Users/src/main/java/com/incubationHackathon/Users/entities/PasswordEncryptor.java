//package com.incubationHackathon.Users.entities;
//
//import com.incubationHackathon.Users.repos.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//import jakarta.annotation.PostConstruct;
//import java.util.List;
//
//@Component
//public class PasswordEncryptor {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    // This method will automatically run after the application context is initialized
//    @PostConstruct
//    public void encryptPasswords() {
//        List<User> users = userRepository.findAll();
//
//        for (User user : users) {
//            String plainPassword = user.getPassword();
//            if (!isAlreadyEncoded(plainPassword)) {
//                String hashedPassword = passwordEncoder.encode(plainPassword);
//                user.setPassword(hashedPassword);
//                userRepository.save(user);
//                System.out.println("Updated password for user: " + user.getUsername());
//            }
//        }
//    }
//
//    private boolean isAlreadyEncoded(String password) {
//        return password.startsWith("$2a$");
//    }
//}
