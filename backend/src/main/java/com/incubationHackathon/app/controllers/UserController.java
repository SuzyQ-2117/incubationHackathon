package com.incubationHackathon.app.controllers;

import com.incubationHackathon.app.dtos.UserDTO;
import com.incubationHackathon.app.entities.User;
import com.incubationHackathon.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

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

    // Verify login credentials
    @PostMapping("/login")
    public ResponseEntity<Boolean> verifyUserCredentials(@RequestParam String username, @RequestParam String password) {
        boolean isValid = userService.verifyUserCredentials(username, password);
        return ResponseEntity.ok(isValid);
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
