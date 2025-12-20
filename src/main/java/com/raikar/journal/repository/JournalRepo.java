package com.raikar.journal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.raikar.journal.entity.JournalEntry;

@Repository
public interface JournalRepo extends MongoRepository<JournalEntry, Long>{


}
