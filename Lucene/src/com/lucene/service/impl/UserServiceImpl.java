package com.lucene.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lucene.bean.User;
import com.lucene.common.DaoSupport;
import com.lucene.dto.UserDTO;
import com.lucene.service.UserService;
import com.lucene.util.EntityDtoConverter;
import com.lucene.util.MD5;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.save(user);
		return user;
	}
}
