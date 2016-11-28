package com.paySystem.ic.service.system;

import java.util.List;

import com.paySystem.ic.bean.system.Role;
import com.paySystem.ic.service.common.DAO;

/**
 * @ClassName:RoleService
 * @Description:系统角色管理Service接口
 * @date: 2013-9-13上午11:19:15
 * @author:
 * @version: V1.0
 */
public interface RoleService extends DAO<Role> {

	public boolean validate(String code);

	public String getCode();
	
	public Role findBycode(String code);

	public List<Role> getAll();
}
