package com.spring.demo.test.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.spring.demo.test.service.iface.UserService;

/**
 * @ProjectName:SWI
 * @ClassName:UserDAOIbatisImpl
 * @Description:用户服务接口实现类
 * @date: 2015-7-10下午03:57:17
 * @author: 半仙儿
 * @version: V1.0
 * @date:2015-7-10下午03:57:17
 */
public class UserDAOIbatisImpl extends SqlMapClientDaoSupport implements
		UserService {
	/** 创建slf4jLogger对象 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 *@MethodName:Delete
	 *@Description:通过编号进行删除
	 *@param id
	 *@author:半仙儿
	 *@date:2015-7-10下午04:01:37
	 */
	public boolean Delete(Object id) {
		logger.debug("delete bean Id="+id);
		return false;
	}

	/**
	 *@MethodName:Find
	 *@Description:通过用户名查询数据库所用的与该uname相同的用户
	 *@param uname
	 *@return 
	 *@author:徐半仙儿
	 *@date:2015-7-12下午08:18:23
	 */
	public List Find(Object uname) {
		try {
			return this.getSqlMapClient().queryForList("selectAllTUserByName",uname);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List FindAll(Object bean) {
		return null;
	}

	public List FindByArr(Object arr) {
		try {
			return this.getSqlMapClient().queryForList("selectTUserByUname",arr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object FindById(Class cl, Object id) {
		return null;
	}

	public boolean Save(Object bean) {
		return false;
	}

	public boolean Update(Object bean) {
		return false;
	}

	public boolean insert(Object bean) {
		return false;
	}

}
