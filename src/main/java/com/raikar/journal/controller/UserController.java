package com.raikar.journal.controller;

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

import com.raikar.journal.entity.User;
import com.raikar.journal.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

	@Autowired
	public UserService service;
	
	@PostMapping("addUser")
	public ResponseEntity<User> addUser(@RequestBody User u) {
		try {
			log.info("inside addUser method!");
			service.addUser(u);
			return new ResponseEntity<>(u,HttpStatus.CREATED);
		}catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("getAllUsers")
	public ResponseEntity<?> getAllUsers(){
		try {
			log.info("inside getAllUsers method!");
			List<User> allUsers= service.getAllUsers();
			if(allUsers!=null) {
				return new ResponseEntity<>(allUsers, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("getUserByUsername/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		try {
			log.info("inside getUserByUsername method!");
			Optional<User> u = service.getUserByUsername(username);
			if(u.isPresent() ){
				return new ResponseEntity<>(u.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("updateByUsername/{username}")
	public ResponseEntity<?>  updateByIdD(@PathVariable String username, @RequestBody User u) {
		try {
			log.info("inside updateByIdD method!");
			User old = service.getUserByUsername(username).orElse(null);
			if(old!=null ){
				old.setUsername(u.getUsername()!=null && !u.getUsername().equals("")?u.getUsername():old.getUsername());
				old.setPassword(u.getPassword()!=null && !u.getPassword().equals("")?u.getPassword():old.getPassword());
				service.addUser(old);
				return new ResponseEntity<>(old, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("deleteByUsername/{username}")
	public ResponseEntity<?> deleteById(@PathVariable String username) {
		try {
			log.info("inside deleteById method!");
			User u = service.getUserByUsername(username).orElse(null);
			if(u!=null ){
				service.deleteByUsername(username);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
