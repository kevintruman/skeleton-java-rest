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
@Table(name = "m_role")
public class MRoleResponse {

	@Id
	String id;

	String name;

	String description;

	@ManyToOne
	@JoinColumn(name = "status")
	SysStatus sysStatus;

	@Column(name = "update_date")
	String updateDate;

}
