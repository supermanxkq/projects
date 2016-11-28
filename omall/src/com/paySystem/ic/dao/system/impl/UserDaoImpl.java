package com.paySystem.ic.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.system.UserDao;
@Repository(UserDao.USERDAO)
public class UserDaoImpl  extends DaoSupport<User> implements UserDao {

	@Override
	public void saveUser(User user) throws Exception {
			this.save(user);
	}

}
