package com.zvl.technical_task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoicePrint {
    @Id
    private String id;
    private String voicePrint;
    private int userHashCode;
}
