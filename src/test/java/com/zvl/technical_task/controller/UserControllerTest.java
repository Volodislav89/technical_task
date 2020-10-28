package com.zvl.technical_task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zvl.technical_task.model.User;
import com.zvl.technical_task.model.UserVoicePrintDTO;
import com.zvl.technical_task.model.VoicePrint;
import com.zvl.technical_task.model.VoicePrintDTO;
import com.zvl.technical_task.service.UserService;
import com.zvl.technical_task.service.VoicePrintService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    User user;
    VoicePrint voicePrint;
    UserVoicePrintDTO userVoicePrintDTO;
    VoicePrintDTO voicePrintDTO;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    VoicePrintService voicePrintService;

    @BeforeEach
    void setUp() {
        user = new User(1L, 25, "Marry", "Kyiv");
        voicePrint = new VoicePrint("1", "Marry voice", user.hashCode());
        userVoicePrintDTO = new UserVoicePrintDTO();
        userVoicePrintDTO.setUser(user);
        userVoicePrintDTO.setVoicePrint(voicePrint);
        voicePrintDTO = new VoicePrintDTO();
        voicePrintDTO.setVoicePrint(voicePrint.getVoicePrint());
    }

    @AfterEach
    void tearDown() {
        user = null;
        voicePrint = null;
        userVoicePrintDTO = null;
        voicePrintDTO = null;
    }

    @Test
    void saveUserAndVoicePrint() throws Exception{
        when(userService.saveNewUser(user)).thenReturn(user);
        when(voicePrintService.saveNewVoicePrint(voicePrint)).thenReturn(voicePrint);
        mockMvc.perform(post("/user")
        .content(objectMapper.writeValueAsString(userVoicePrintDTO))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.userAge", is(25)))
                .andExpect(jsonPath("$.userName", is("Marry")))
                .andExpect(jsonPath("$.address", is("Kyiv")));
        verify(userService, times(1)).saveNewUser(user);
        verify(voicePrintService, times(1)).saveNewVoicePrint(voicePrint);

    }

    @Test
    void findVoicePrintByUserId() throws Exception {
        when(userService.findUserById(1L)).thenReturn(user);
        when(voicePrintService.findVoicePrintByUserHashCode(user.hashCode())).thenReturn(voicePrintDTO);
        mockMvc.perform(get("/user/1")
                .content(objectMapper.writeValueAsString(voicePrintDTO)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.voicePrint", is("Marry voice")));
        verify(userService, times(1)).findUserById(1L);
        verify(voicePrintService, times(1)).findVoicePrintByUserHashCode(user.hashCode());
    }
}