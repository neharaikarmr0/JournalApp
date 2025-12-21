package com.raikar.journal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raikar.journal.entity.JournalEntry;
import com.raikar.journal.repository.JournalRepo;
import com.raikar.journal.repository.UserRepo;
import com.raikar.journal.entity.User;

@Service
public class JournalService {

	@Autowired
	public JournalRepo journalRepo;
	
	@Autowired
	public UserService userService;

	@Transactional
	public void addEntry(JournalEntry j, String username) {
		Optional<User> u = userService.getUserByUsername(username);
		if(u.isPresent()) {
			User user = u.get();
			j.setDateTime(LocalDateTime.now());
			JournalEntry saved = journalRepo.save(j);
			user.getJournal_entries().add(saved);
			userService.addUser(user);
		}
		
	}

	public List<JournalEntry> getAllEntries() {
		List<JournalEntry> allEntries = journalRepo.findAll();
		return allEntries;
	}

	public Optional<JournalEntry> getEntryById(ObjectId id) {
		return journalRepo.findById(id);
	}

	public void deleteById(String username, ObjectId id) {
		Optional<User> user = userService.getUserByUsername(username);
		if(user.isPresent()) {
			User u = user.get();
			u.getJournal_entries().removeIf(x -> x.getId().equals(id));
	        userService.addUser(u); // Update user list
	        journalRepo.deleteById(id);
		}
        
	}

	public void updateEntry(JournalEntry j1) {
		journalRepo.save(j1);
	}

	

	
	
}
