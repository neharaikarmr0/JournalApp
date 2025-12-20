package com.raikar.journal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raikar.journal.entity.JournalEntry;
import com.raikar.journal.repository.JournalRepo;

@Service
public class JournalService {

	@Autowired
	public JournalRepo repo;

	public void addEntry(JournalEntry j) {
		repo.save(j);
	}

	public List<JournalEntry> getAllEntries() {
		List<JournalEntry> allEntries = repo.findAll();
		return allEntries;
	}

	public Optional<JournalEntry> getEntryById(Long id) {
		return repo.findById(id);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	

	
	
}
