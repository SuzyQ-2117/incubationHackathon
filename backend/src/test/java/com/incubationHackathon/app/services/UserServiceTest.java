package com.incubationHackathon.app.services;

import com.incubationHackathon.app.dtos.UserDTO;
import com.incubationHackathon.app.entities.User;
import com.incubationHackathon.app.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("John");

        when(userRepository.save(user)).thenReturn(user);

        UserDTO result = userService.addUser(user);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("Jane");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1L);
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
    }

    @Test
    void testVerifyUserCredentials() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPass");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        boolean result = userService.verifyUserCredentials("testUser", "testPass");
        assertTrue(result);

        result = userService.verifyUserCredentials("testUser", "wrongPass");
        assertFalse(result);
    }

    @Test
    void testUpdateUserDetails() {
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("John");
        user.setIdentityConfirmed(false);

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Jane");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO updatedUser = userService.updateUserDetails(1L, userDTO);
        assertEquals("Jane", updatedUser.getFirstName());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
