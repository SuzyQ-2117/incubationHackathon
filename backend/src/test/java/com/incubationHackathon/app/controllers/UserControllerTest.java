package com.incubationHackathon.app.controllers;

import com.incubationHackathon.app.dtos.UserDTO;
import com.incubationHackathon.app.entities.User;
import com.incubationHackathon.app.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        // Given
        User user = new User(); // Set necessary fields in User if required
        UserDTO userDTO = new UserDTO(); // Set necessary fields in UserDTO if required
        when(userService.addUser(any(User.class))).thenReturn(userDTO);

        // When
        ResponseEntity<UserDTO> response = userController.addUser(user);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).addUser(user);
    }

    @Test
    public void testGetUserById() {
        // Given
        Long userId = 1L;
        UserDTO userDTO = new UserDTO(); // Set necessary fields in UserDTO if required
        when(userService.getUserById(userId)).thenReturn(userDTO);

        // When
        ResponseEntity<UserDTO> response = userController.getUserById(userId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testVerifyUserCredentials() {
        // Given
        String username = "testUser";
        String password = "testPass";
        boolean validCredentials = true;
        when(userService.verifyUserCredentials(username, password)).thenReturn(validCredentials);

        // When
        ResponseEntity<Boolean> response = userController.verifyUserCredentials(username, password);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(validCredentials, response.getBody());
        verify(userService, times(1)).verifyUserCredentials(username, password);
    }

    @Test
    public void testUpdateUserDetails() {
        // Given
        Long userId = 1L;
        UserDTO userDTO = new UserDTO(); // Set necessary fields in UserDTO if required
        when(userService.updateUserDetails(anyLong(), any(UserDTO.class))).thenReturn(userDTO);

        // When
        ResponseEntity<UserDTO> response = userController.updateUserDetails(userId, userDTO);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).updateUserDetails(userId, userDTO);
    }

    @Test
    public void testConfirmUserIdentity() {
        // Given
        Long userId = 1L;

        // When
        ResponseEntity<Void> response = userController.confirmUserIdentity(userId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).confirmUserIdentity(userId);
    }

    @Test
    public void testDeleteUser() {
        // Given
        Long userId = 1L;

        // When
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }
}

