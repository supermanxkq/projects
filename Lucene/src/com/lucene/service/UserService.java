package com.lucene.service;


import com.lucene.bean.User;
import com.lucene.common.DAO;
import com.lucene.dto.UserDTO;

/**
 * @ProjectName:blog 
 * @ClassName:UserService  
 * @Description:用户管理service
 * @date: 2015-6-14下午09:17:06
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-14下午09:17:06
 */
public interface UserService extends DAO<User>{
	/**常量*/
	public static final String USERSERVICE="userService";
	/**
	 *@MethodName:addUser 
	 *@Description:注册新用户
	 *@param userDTO 
	 *@author:徐半仙儿
	 *@return void
	 *@date:2015-6-14下午09:17:55
	 */
	public User addUser(UserDTO userDTO);
}
