package com.bridgelabz.fundoonote.user.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//import com.bridgelabz.fundoonote.label.models.Label;
import com.bridgelabz.fundoonote.note.models.Note;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "User_Id")
	private Integer id;
	@Column(name = "User_Name")
	private String name;
	@Column(name = "User_Email")
	private String email;
	@Column(name = "User_Number")
	private String phoneNumber;
	@Column(name = "User_Password")
	private String password;
	@Column(name = "User_Verified")
	private Boolean isVerified = false;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	List<Note> note;
	
//	@OneToMany(mappedBy = "user")
//	@JsonIgnore
//	List<Label> label;

}
