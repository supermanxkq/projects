package com.paySystem.ic.web.dto.system;

import com.paySystem.ic.web.dto.BaseDTO;

public class RolePrivilegeDTO extends BaseDTO {
	private Integer roleid;
	private String privilegeid;

	public RolePrivilegeDTO(Integer roleid, String privilegeid) {
		super();
		this.roleid = roleid;
		this.privilegeid = privilegeid;
	}

	public Integer getRoleid() {
		return roleid;
	}


	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}


	public String getPrivilegeid() {
		return privilegeid;
	}

	public void setPrivilegeid(String privilegeid) {
		this.privilegeid = privilegeid;
	}
}
