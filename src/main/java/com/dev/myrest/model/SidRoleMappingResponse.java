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
@Table(name = "sid_role_mapping")
public class SidRoleMappingResponse {

	@Id
	String id;

	@ManyToOne
	@JoinColumn(name = "id_role")
	MRoleResponse mRoleResponse;

	@ManyToOne
	@JoinColumn(name = "id_mapping")
	MMappingResponse mMappingRes;

	@ManyToOne
	@JoinColumn(name = "status")
	SysStatus sysStatus;

	@Column(name = "update_date")
	String updateDate;

}
