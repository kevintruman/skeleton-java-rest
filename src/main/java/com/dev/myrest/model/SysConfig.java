package com.dev.myrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sys_config")
public class SysConfig {

	@Id
	String id;

	String key;

	String value;

	String description;

	@Column(name = "update_date")
	String updateDate;

}
