package com.raikar.journal.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.raikar.journal.service.JournalService;

@RestController
@RequestMapping("journal")
public class JournalController {

	@Autowired
	public JournalService service;
	
	@PostMapping("addEntry")
	public ResponseEntity<JournalEntry> addEntry(@RequestBody JournalEntry j) {
		try {
			j.setDateTime(LocalDateTime.now());
			service.addEntry(j);
			return new ResponseEntity<>(j,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("getAllEntries")
	public ResponseEntity<?> getAllEntries(){
		try {
			List<JournalEntry> allEntries = service.getAllEntries();
			if(allEntries!=null) {
				return new ResponseEntity<>(allEntries, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity<?> getEntryById(@PathVariable Long id) {
		try {
			Optional<JournalEntry> j = service.getEntryById(id);
			if(j.isPresent() ){
				return new ResponseEntity<>(j.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("updateById/{id}")
	public ResponseEntity<?>  updateByIdD(@PathVariable Long id, @RequestBody JournalEntry j) {
		try {
			JournalEntry j1 = service.getEntryById(id).orElse(null);
			if(j1!=null ){
				j1.setTitle(j.getTitle()!=null && !j.getTitle().equals("")?j.getTitle():j1.getTitle());
				j1.setDesc(j.getDesc()!=null && !j.getDesc().equals("")?j.getDesc():j1.getDesc());
				service.addEntry(j1);
				return new ResponseEntity<>(j1, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			JournalEntry j1 = service.getEntryById(id).orElse(null);
			if(j1!=null ){
				service.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
