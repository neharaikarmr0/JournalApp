package com.raikar.journal.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;

@Data
@Document(collection="users")
public class User {

	@Id
	public ObjectId id;
	
	@Indexed(unique=true)
	@NonNull
	public String username;
	
	@NonNull
	public String password;
	
	@DBRef
	public List<JournalEntry> journal_entries = new ArrayList<>();

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<JournalEntry> getJournal_entries() {
		return journal_entries;
	}

	public void setJournal_entries(List<JournalEntry> journal_entries) {
		this.journal_entries = journal_entries;
	}
	
	
}
