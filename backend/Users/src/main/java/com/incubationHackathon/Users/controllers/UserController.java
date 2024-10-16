package com.incubationHackathon.Users.controllers;

import com.incubationHackathon.Users.dtos.AuthRequest;
import com.incubationHackathon.Users.dtos.AuthResponse;
import com.incubationHackathon.Users.entities.User;
import com.incubationHackathon.Users.services.JwtUtil;
import com.incubationHackathon.Users.services.UserService;
import com.incubationHackathon.users.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // Add a new user
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        UserDTO createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Retrieve a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Update user details
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserDetails(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUserDetails(id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Confirm identity
    @PatchMapping("/{id}/confirm-identity")
    public ResponseEntity<Void> confirmUserIdentity(@PathVariable Long id) {
        userService.confirmUserIdentity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
