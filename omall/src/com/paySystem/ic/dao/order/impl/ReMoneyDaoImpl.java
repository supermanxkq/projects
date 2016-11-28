package com.paySystem.ic.dao.order.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.order.ReMoney;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.order.ReMoneyDao;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoneyDaoImpl
 * @Description: 退款Dao实现
 * @date: 2014-11-14 上午11:12:33
 * @author: 王少辉
 * @version: V1.0
 */
@Repository(ReMoneyDao.REMONEYDAO)
public class ReMoneyDaoImpl extends DaoSupport<ReMoney> implements ReMoneyDao {

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.order.ReMoneyDao#addReMoney(com
	 *                        .paySystem.ic.bean.order.ReMoney)
	 *@MethodName: addReMoney
	 *@Description: 添加退款记录信息
	 *@Params: rm 退款记录
	 *@Author: 王少辉
	 *@Date: 2014-11-14 上午11:12:55
	 */
	@Override
	public void addReMoney(ReMoney rm) {
		rm.setUpdateTime(getSysTime());
		save(rm);
	}

}
