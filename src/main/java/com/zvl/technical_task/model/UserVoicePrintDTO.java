package com.zvl.technical_task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoicePrintDTO {
    private User user;
    private VoicePrint voicePrint;
}
