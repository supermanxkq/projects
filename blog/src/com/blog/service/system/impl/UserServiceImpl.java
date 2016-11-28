package com.blog.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.system.User;
import com.blog.dao.common.DaoSupport;
import com.blog.dao.system.UserDao;
import com.blog.dto.system.UserDTO;
import com.blog.dto.system.UserSession;
import com.blog.service.system.UserService;
import com.blog.util.EntityDtoConverter;
import com.blog.util.MD5;

/**
 * @ProjectName:blog
 * @ClassName:UserServiceImpl
 * @Description:用户管理servcie实现类
 * @date: 2015-6-14下午09:18:36
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-14下午09:18:36
 */
@Service(UserService.USERSERVICE)
public class UserServiceImpl extends DaoSupport<User> implements UserService {
	/** 注入userDao */
	@Autowired
	private UserDao userDao;

	public UserDTO login(UserDTO userDTO) {
		return userDao.login(userDTO);
	}

	public UserSession getUserSession(UserDTO userDTO) {
		UserSession userSession = new UserSession();
		userSession.setUserId(userDTO.getUserId());
		userSession.setUserName(userDTO.getUserName());
		userSession.setPassWord(userDTO.getPassWord());
		userSession.setUserLeavel(userDTO.getUserLeavel());
		return userSession;
	}

	/**
	 *@MethodName:addUser
	 *@Description:注册新用户
	 *@param userDTO
	 *@author:徐半仙儿
	 *@date:2015-6-14下午09:20:07
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User addUser(UserDTO userDTO) {
		User user = new User();
		try {
			user = (User) EntityDtoConverter.dto2Bean(userDTO, user);
			user.setPassWord(MD5.MD5Encode("123456"));
			user.setUserLeavel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.save(user);
		return user;
	}
	/**
	 *@MethodName:validateUserName 
	 *@Description:验证用户名是否存在
	 *@param userDTO
	 *@author:徐半仙儿
	 *@return Boolean
	 *@date:2015-6-15上午12:02:49
	 */
	public Boolean validateUserName(UserDTO userDTO){
		return userDao.validateUserName(userDTO);
	}
}
