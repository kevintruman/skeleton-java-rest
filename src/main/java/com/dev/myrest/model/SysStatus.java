package com.dev.myrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sys_status")
public class SysStatus {

	@Id
	String id;

	String name;

	@Column(name = "full_name")
	String fullName;

	String description;

	@Column(name = "update_date")
	String updateDate;

}
