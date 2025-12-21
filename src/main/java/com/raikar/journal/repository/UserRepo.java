package com.raikar.journal.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.raikar.journal.entity.JournalEntry;
import com.raikar.journal.entity.User;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId>{

	public Optional<User> findByUsername(String username);

	public void deleteByUsername(String username);


}
