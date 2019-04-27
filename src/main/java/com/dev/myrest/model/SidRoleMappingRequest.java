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
@Table(name = "sid_role_mapping")
public class SidRoleMappingRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id;

	@Column(name = "id_role")
	String idRole;

	@Column(name = "id_mapping")
	String idMapping;

	String status;

	@Column(name = "update_date")
	String updateDate;

}
