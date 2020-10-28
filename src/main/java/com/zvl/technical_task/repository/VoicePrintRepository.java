package com.zvl.technical_task.repository;

import com.zvl.technical_task.model.VoicePrint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoicePrintRepository extends MongoRepository<VoicePrint, String> {
    VoicePrint findByUserHashCode(int userHashCode);
}
