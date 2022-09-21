package com.bridgelabz.fundoonote.note.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.bridgelabz.fundoonote.user.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString

public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int noteid;
	private String title;
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	User user;

}
