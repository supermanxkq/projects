package com.paySystem.ic.dao.order;

import com.paySystem.ic.bean.order.ReMoney;
import com.paySystem.ic.dao.common.DAO;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoneyDao
 * @Description: 退款Dao接口
 * @date: 2014-11-14 上午11:09:54
 * @author: 王少辉
 * @version: V1.0
 */
public interface ReMoneyDao extends DAO<ReMoney> {
	
	/**
	 * 退款Dao
	 */
	String REMONEYDAO = "reMoneyDao";
	
	/**
	 *@Title: addReMoney
	 *@Description: 添加退款记录信息
	 *@Params: rm 退款记录
	 *@Return: void
	 *@author: 王少辉
	 *@Date: 2014-11-14 上午11:11:41
	 */
	void addReMoney(ReMoney rm);
}
