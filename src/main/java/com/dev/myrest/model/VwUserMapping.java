package com.dev.myrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "vw_user_mapping")
public class VwUserMapping {

	@Id
	String id;

	String username;

	String password;

	@Column(name = "password_hash")
	String passwordHash;

	String status;

	@Column(name = "id_role")
	String idRole;

	@Column(name = "status_user_role")
	String statusUserRole;

	@Column(name = "name_role")
	String nameRole;

	@Column(name = "status_role")
	String statusRole;

	@Column(name = "id_mapping")
	String idMapping;

	@Column(name = "status_role_mapping")
	String statusRoleMapping;

	String path;

	@Column(name = "status_mapping")
	String statusMapping;

}
