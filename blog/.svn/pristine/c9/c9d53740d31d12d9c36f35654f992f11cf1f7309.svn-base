package com.blog.dao.system.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.blog.bean.system.User;
import com.blog.dao.common.DaoSupport;
import com.blog.dao.system.UserDao;
import com.blog.dto.system.UserDTO;
import com.blog.util.EntityDtoConverter;
import com.blog.util.MD5;

/**
 * @ProjectName:htcollege
 * @ClassName:UserDaoImpl
 * @Description:TODO(用户UserDao实现类)
 * @author: 枫雪工作室
 * @version: V1.0
 * @date:2015-4-26下午10:34:37
 */
@Repository(UserDao.UserDao)
public class UserDaoImpl extends DaoSupport<User> implements UserDao {

	/**
	 *@MethodName:login
	 *@Description:TODO(登录)
	 *@param userDTO
	 *@return
	 *@author:枫雪工作室
	 *@return boolean
	 *@date:2015-4-26下午10:36:41
	 *@version: V1.0
	 */
	@SuppressWarnings("unchecked")
	public UserDTO login(UserDTO userDTO) {
		/** 创建HQL语句 */
		String jpql = "select u from User u where u.userName=:userName and u.passWord=:passWord";
		/** 实例化Query对象 */
		Query query = em.createQuery(jpql);
		query.setParameter("userName", userDTO.getUserName());
		query.setParameter("passWord", MD5.MD5Encode(userDTO.getPassWord()));
		
		/** 执行HQL语句 */
		List<User> users = query.getResultList();
		/** 判断是否有值，返回true或false */
		UserDTO userDTO2 = new UserDTO();
		try {
			if (users != null && users.size() != 0) {

				return userDTO2 = (UserDTO) EntityDtoConverter.bean2Dto(users.get(0),
						userDTO2);

			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *@MethodName:validateUserName 
	 *@Description:验证用户名是否存在
	 *@param userDTO
	 *@author:徐半仙儿
	 *@return Boolean
	 *@date:2015-6-15上午12:02:49
	 */
	@SuppressWarnings("unchecked")
	public Boolean validateUserName(UserDTO userDTO){
		String jpql="from User o where o.userName='"+userDTO.getUserName()+"'";
		List<User> users=this.em.createQuery(jpql).getResultList();
		if(users.size()==0){
			return false;
		}else{
			return true;
		}
	}
}
