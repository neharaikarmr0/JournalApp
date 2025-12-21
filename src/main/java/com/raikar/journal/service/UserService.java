package com.raikar.journal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raikar.journal.entity.JournalEntry;
import com.raikar.journal.entity.User;
import com.raikar.journal.repository.JournalRepo;
import com.raikar.journal.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	public UserRepo repo;

	public void addUser(User u) {
		repo.save(u);
	}

	public List<User> getAllUsers() {
		List<User> allUsers = repo.findAll();
		return allUsers;
	}

	public Optional<User> getUserByUsername(String username) {
		Optional<User> user = repo.findByUsername(username);
		return user;
		
	}

	public void deleteByUsername(String username) {
		repo.deleteByUsername(username);
	}

	

	
	
}
