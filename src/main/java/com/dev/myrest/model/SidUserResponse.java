package com.dev.myrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sid_user")
public class SidUserResponse {

	@Id
	String id;

	String username;

	String password;

	@Column(name = "password_hash")
	String passwordHash;

	@ManyToOne
	@JoinColumn(name = "status")
	SysStatus sysStatus;

	@Column(name = "update_date")
	String updateDate;

}
