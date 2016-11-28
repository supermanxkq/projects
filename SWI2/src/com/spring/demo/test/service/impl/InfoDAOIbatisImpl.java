package com.spring.demo.test.service.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.spring.demo.test.service.iface.UserService;

/**
 * @ProjectName:SWI2 
 * @ClassName:InfoDAOIbatisImpl  
 * @Description:人员信息数据操作类
 * @date: 2015-7-12下午08:47:21
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-7-12下午08:47:21
 */
public class InfoDAOIbatisImpl extends SqlMapClientDaoSupport implements UserService {

	/**
	 *@MethodName:Delete
	 *@Description:根据编号进行删除
	 *@param id
	 *@author:徐半仙儿
	 *@date:2015-7-12下午08:48:30
	 */
	@Override
	public boolean Delete(Object id) {
		return false;
	}

	@Override
	public List Find(Object uname) {
		return null;
	}

	@Override
	public List FindAll(Object bean) {
		return null;
	}

	/**
	 *@MethodName:FindByArr
	 *@Description:通过属性进行查询
	 *@param arr
	 *@author:徐半仙儿
	 *@date:2015-7-12下午08:49:46
	 */
	@Override
	public List FindByArr(Object arr) {
		try {
			return this.getSqlMapClient().queryForList("selectTInfoByArr", arr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object FindById(Class cl, Object id) {
		return null;
	}

	@Override
	public boolean Save(Object bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(Object bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Object bean) {
		// TODO Auto-generated method stub
		return false;
	}

}
