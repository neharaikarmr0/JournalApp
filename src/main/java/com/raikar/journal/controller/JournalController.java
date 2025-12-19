package com.raikar.journal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raikar.journal.entry.JournalEntry;

@RestController
@RequestMapping("journal")
public class JournalController {

	Map<Long, JournalEntry> entries = new HashMap<>();
	
	@PostMapping("addEntry")
	public String addEntry(@RequestBody JournalEntry j) {
		entries.put(j.getId(), j);
		return "added";
	}
	
	@GetMapping("allEntries")
	public List<JournalEntry> getAllEntries(){
		return (List<JournalEntry>) entries;
	}
	
	@GetMapping("getById/{id}")
	public JournalEntry getEntryById(@PathVariable Long id) {
		return entries.get(id);
	}
	
	@PutMapping("updateById/{id}")
	public String updateByIdD(@PathVariable Long id, @RequestBody JournalEntry j) {
		entries.put(id, j);
		return "updated";
	}
	
	@DeleteMapping("deleteById/{id}")
	public String deleteById(@PathVariable Long id) {
		entries.remove(id);
		return "deleted";
	}
}
