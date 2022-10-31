package com.bridgelabz.fundoonote.note.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

//import com.bridgelabz.fundoonote.label.models.Label;
import com.bridgelabz.fundoonote.user.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private boolean isTrash;
	private boolean isArchive;
	private LocalDateTime trashTime;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	User user;
  
//	@ManyToMany
//	@JsonIgnore
//	private List<Label> label;
	

}
