package com.incubationHackathon.Users.controllers;

import com.incubationHackathon.Users.dtos.AuthRequest;
import com.incubationHackathon.Users.dtos.AuthResponse;
import com.incubationHackathon.Users.entities.User;
import com.incubationHackathon.Users.services.JwtUtil;
import com.incubationHackathon.Users.services.UserService;
import com.incubationHackathon.users.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status() {
        return ResponseEntity.ok("Service is up");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            // Assuming you have a method to get userId based on the username
            Long userId = userService.getUserIdByUsername(loginRequest.getUsername());
            String jwt = jwtUtil.generateToken(userId);
            return ResponseEntity.ok(new AuthResponse(jwt));  // Assuming you have an AuthResponse class with a "token" field
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String expiredToken = jwtUtil.generateExpiredToken(token);

        return ResponseEntity.ok("Token has been expired. Please remove the token from storage.");
    }




    // Register and login user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Delegate all the logic to the service
            UserDTO createdUser = userService.registerUser(user);
    return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException e) {
            // Handle the case where the username is already taken
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Registration successful but login failed.");
        }
    }

    // Retrieve a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserFromJwt(@RequestHeader("Authorization") String token) {
        try {
            // Extract the JWT token by removing the "Bearer " prefix
            String jwt = token.replace("Bearer ", "");

            // Decode the JWT to get the username
            String userId = jwtUtil.extractUsername(jwt);

            // Find the user by username (or userId)
            UserDTO user = userService.getUserById(Long.parseLong(userId));

            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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
