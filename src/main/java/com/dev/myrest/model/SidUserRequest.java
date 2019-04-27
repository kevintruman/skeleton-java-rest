package com.dev.myrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sid_user")
public class SidUserRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id;

	String username;

	String password;

	@Column(name = "password_hash")
	String passwordHash;

	String status;

	@Column(name = "update_date")
	String updateDate;

}
