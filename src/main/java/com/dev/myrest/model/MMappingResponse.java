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
@Table(name = "m_mapping")
public class MMappingResponse {

	@Id
	String id;

	String path;

	String description;

	@ManyToOne
	@JoinColumn(name = "status")
	SysStatus sysStatus;

	@Column(name = "update_date")
	String updateDate;

}
