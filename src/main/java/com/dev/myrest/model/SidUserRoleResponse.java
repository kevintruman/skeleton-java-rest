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
@Table(name = "sid_user_role")
public class SidUserRoleResponse {

	@Id
	String id;

	@ManyToOne
	@JoinColumn(name = "id_user")
	SidUserResponse sidUserRes;

	@ManyToOne
	@JoinColumn(name = "id_role")
	MRoleResponse mRoleRes;

	@ManyToOne
	@JoinColumn(name = "status")
	SysStatus sysStatus;

	@Column(name = "update_date")
	String updateDate;

}
