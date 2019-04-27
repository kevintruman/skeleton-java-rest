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
@Table(name = "m_role")
public class MRoleRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id;

	String name;

	String description;

	String status;

	@Column(name = "update_date")
	String updateDate;

}
