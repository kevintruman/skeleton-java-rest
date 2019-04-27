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
@Table(name = "m_mapping")
public class MMappingRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id;

	String path;

	String description;

	String status;

	@Column(name = "update_date")
	String updateDate;

}
