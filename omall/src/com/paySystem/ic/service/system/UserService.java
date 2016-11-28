package com.paySystem.ic.service.system;

import java.util.HashMap;
import java.util.List;

import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:UserService
 * @Description:用户登录用户管理Service接口
 * @date: 2013-9-20上午11:07:54
 * @version: V1.0
 */
public interface UserService extends DAO<User> {

	public boolean exist(String username);

	public User validate(String username, String password);
	
	public UserSession getUserSession(User user,HashMap<String, Module> modulesMap);
	
	public List<OptionsString> getDlsOption();
	
	public List<OptionsString> getDlsOptionByOrganId(String organId);
	
}
