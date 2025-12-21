package com.raikar.journal.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raikar.journal.entity.JournalEntry;
import com.raikar.journal.entity.User;
import com.raikar.journal.service.JournalService;
import com.raikar.journal.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("journal")
@Slf4j
public class JournalController {

	@Autowired
	public JournalService journalService;

	@Autowired
	public UserService userService;

	@PostMapping("addEntry/{username}")
	public ResponseEntity<JournalEntry> addEntry(@RequestBody JournalEntry j, @PathVariable String username) {
		try {
			journalService.addEntry(j,username);
			return new ResponseEntity<>(j,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getUserEntries/{username}")
	public ResponseEntity<?> getAllEntries(@PathVariable String username){
		try {
			List<JournalEntry> allEntries = null;
			Optional<User> user = userService.getUserByUsername(username);
			if(user.isPresent()) {
				User u = user.get();
				allEntries = u.getJournal_entries();
			}
			if(allEntries!=null) {
				return new ResponseEntity<>(allEntries, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getById/{id}")
	public ResponseEntity<?> getEntryById(@PathVariable ObjectId id) {
		try {
			Optional<JournalEntry> j = journalService.getEntryById(id);
			if(j.isPresent() ){
				return new ResponseEntity<>(j.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateById/{id}")
	public ResponseEntity<?>  updateByIdD(@RequestBody JournalEntry j,@PathVariable ObjectId id, String username) {
		try {
			JournalEntry j1 = journalService.getEntryById(id).orElse(null);
			if(j1!=null ){
				j1.setTitle(j.getTitle()!=null && !j.getTitle().equals("")?j.getTitle():j1.getTitle());
				j1.setDesc(j.getDesc()!=null && !j.getDesc().equals("")?j.getDesc():j1.getDesc());
				journalService.updateEntry(j1);
				return new ResponseEntity<>(j1, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("deleteById/{username}/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String username, @PathVariable ObjectId id) {
		try {
				journalService.deleteById(username,id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
