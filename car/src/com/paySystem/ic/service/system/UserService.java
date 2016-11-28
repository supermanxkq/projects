package com.paySystem.ic.service.system;

import java.util.HashMap;
import java.util.List;

import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.system.UserDTO;
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

	public UserSession getUserSession(User user,
			HashMap<String, Module> modulesMap);

	public List<OptionsString> getDlsOption();

	public List<OptionsString> getDlsOptionByOrganId(String organId);

	/**
	 *@Title:testUserName
	 *@Description:验证用户名称是否重复
	 *@param userDTO用户的数据传输对象
	 *@Return:boolean返回值，true表示重复，false表示名称不重复
	 *@author:徐凯强
	 *@Date:2014-8-27下午02:54:35
	 */
	public boolean testUserName(UserDTO userDTO);
}
