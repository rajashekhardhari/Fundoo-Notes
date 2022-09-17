package com.bridgelabz.fundoonote.user.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString

//This is called as POJO Class
//POJO - Plain Old Java Object
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

}
