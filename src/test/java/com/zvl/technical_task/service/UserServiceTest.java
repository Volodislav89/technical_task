package com.zvl.technical_task.service;

import com.zvl.technical_task.exception.UserException;
import com.zvl.technical_task.model.User;
import com.zvl.technical_task.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, 25, "Marry", "Kyiv");
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void saveNewUser() {
        when(userRepository.save(user)).thenReturn(user);
        User newUser = userService.saveNewUser(user);
        assertEquals(newUser, user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void findUserById() throws UserException {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User userWithId = userService.findUserById(1L);
        assertEquals(userWithId, user);
        verify(userRepository, times(1)).findById(1L);
    }
}