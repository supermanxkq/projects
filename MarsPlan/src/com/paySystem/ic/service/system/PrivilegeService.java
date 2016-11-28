package com.paySystem.ic.service.system;

import java.util.List;

import com.paySystem.ic.bean.system.Privilege;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.system.RolePrivilegeDTO;

public interface PrivilegeService extends DAO<Privilege> {

	public void batchSave(List<Privilege> privileges);

	public void removeAllPrivilege() throws Exception;

	public List<RolePrivilegeDTO> getRolePrivilege();

	public void batchSaveRolePrivilege(List<RolePrivilegeDTO> empRoleList);

}
