package com.blog.service.system;


import com.blog.bean.system.User;
import com.blog.dao.common.DAO;
import com.blog.dto.system.UserDTO;
import com.blog.dto.system.UserSession;

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
	public UserDTO login(UserDTO userDTO);
	public UserSession getUserSession(UserDTO userDTO);
	/**
	 *@MethodName:addUser 
	 *@Description:注册新用户
	 *@param userDTO 
	 *@author:徐半仙儿
	 *@return void
	 *@date:2015-6-14下午09:17:55
	 */
	public User addUser(UserDTO userDTO);
	/**
	 *@MethodName:validateUserName 
	 *@Description:验证用户名是否存在
	 *@param userDTO
	 *@author:徐半仙儿
	 *@return Boolean
	 *@date:2015-6-15上午12:02:49
	 */
	public Boolean validateUserName(UserDTO userDTO);
}
