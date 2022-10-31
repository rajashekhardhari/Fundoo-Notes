package com.bridgelabz.fundoonote.label.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.bridgelabz.fundoonote.note.models.Note;
import com.bridgelabz.fundoonote.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long labelId;
	private String labelTitle;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@ManyToMany(mappedBy = "label")
	@JsonIgnore
	private List<Note> note;
}
