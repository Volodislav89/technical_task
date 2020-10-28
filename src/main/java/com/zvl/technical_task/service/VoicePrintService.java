package com.zvl.technical_task.service;

import com.zvl.technical_task.model.VoicePrint;
import com.zvl.technical_task.model.VoicePrintDTO;
import com.zvl.technical_task.repository.VoicePrintRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoicePrintService {
    private VoicePrintRepository voicePrintRepository;
    private ModelMapper modelMapper;

    public VoicePrint saveNewVoicePrint(VoicePrint voicePrint) {
        return voicePrintRepository.save(voicePrint);
    }

    public VoicePrintDTO findVoicePrintByUserHashCode(int hashCode) {
        VoicePrint voicePrint = voicePrintRepository.findByUserHashCode(hashCode);
        VoicePrintDTO voicePrintDTO = convertToVoicePrintDTO(voicePrint);
        return voicePrintDTO;
    }

    private VoicePrintDTO convertToVoicePrintDTO(VoicePrint voicePrint) {
        VoicePrintDTO voicePrintDTO = modelMapper
                .map(voicePrint, VoicePrintDTO.class);
        return voicePrintDTO;
    }
}
