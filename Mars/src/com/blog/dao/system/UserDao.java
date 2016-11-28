package com.blog.dao.system;

import com.blog.dto.system.UserDTO;

/**
 * @ProjectName:htcollege
 * @ClassName:ArticleDAO
 * @Description:TODO(用户dao)
 * @author: 枫雪工作室
 * @version: V1.0
 * @date:2015-4-26下午10:29:54
 */
public interface UserDao {
	public static final String UserDao = "userDao";

	/**
	 *@MethodName:login
	 *@Description:TODO(登录)
	 *@param userDTO
	 *@return
	 *@author:枫雪工作室
	 *@return boolean
	 *@date:2015-4-26下午10:30:59
	 *@version: V1.0
	 */
	public UserDTO login(UserDTO userDTO);
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
