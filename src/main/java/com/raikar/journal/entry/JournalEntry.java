package com.raikar.journal.entry;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JournalEntry {

	private Long id;
	private String title;
	private String desc;
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
}
