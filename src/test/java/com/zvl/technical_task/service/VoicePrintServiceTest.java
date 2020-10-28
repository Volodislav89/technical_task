package com.zvl.technical_task.service;

import com.zvl.technical_task.model.User;
import com.zvl.technical_task.model.VoicePrint;
import com.zvl.technical_task.model.VoicePrintDTO;
import com.zvl.technical_task.repository.VoicePrintRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class VoicePrintServiceTest {

    @Mock
    VoicePrintRepository voicePrintRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    VoicePrintService voicePrintService;

    VoicePrint voicePrint;
    User user;
    VoicePrintDTO voicePrintDTO;

    @BeforeEach
    void setUp() {
        user = new User(2L, 30, "Ben", "Kyiv");
        voicePrint = new VoicePrint("1", "Ben voice", user.hashCode());
        voicePrintDTO = new VoicePrintDTO();
        voicePrintDTO.setVoicePrint(voicePrint.getVoicePrint());
    }

    @AfterEach
    void tearDown() {
        user = null;
        voicePrint = null;
    }

    @Test
    void saveNewVoicePrint() {
        when(voicePrintRepository.save(voicePrint)).thenReturn(voicePrint);
        VoicePrint newVoicePrint = voicePrintService.saveNewVoicePrint(voicePrint);
        assertEquals(newVoicePrint, voicePrint);
        verify(voicePrintRepository, times(1)).save(voicePrint);
    }

    @Test
    void findVoicePrintByUserHashCode() {
        when(voicePrintRepository.findByUserHashCode(user.hashCode())).thenReturn(voicePrint);
        when(modelMapper.map(any(), any())).thenReturn(voicePrintDTO);
        VoicePrintDTO voicePrintDTO = voicePrintService.findVoicePrintByUserHashCode(user.hashCode());
        assertEquals(voicePrint.getVoicePrint(), voicePrintDTO.getVoicePrint());
        verify(voicePrintRepository, times(1)).findByUserHashCode(user.hashCode());
    }
}