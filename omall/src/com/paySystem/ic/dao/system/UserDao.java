package com.paySystem.ic.dao.system;

import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.dao.common.DAO;

public interface UserDao extends DAO<User> {
	
	public static final String USERDAO = "userDao";
	
	public void saveUser(User user) throws Exception ;
}
