package com.paySystem.ic.service.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.order.ReMoney;
import com.paySystem.ic.dao.order.ReMoneyDao;
import com.paySystem.ic.service.order.ReMoneyService;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoneyServiceImpl
 * @Description: 退款Service实现
 * @date: 2014-11-14 上午11:08:04
 * @author: 王少辉
 * @version: V1.0
 */
@Service(ReMoneyService.REMONEYSERVICE)
public class ReMoneyServiceImpl implements ReMoneyService {

	/**
	 * 退款Dao
	 */
	@Resource
	private ReMoneyDao reMoneyDao;

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.order.ReMoneyService#addReMoney(com.paySystem.ic.bean.order.ReMoney)
	 *@MethodName: addReMoney
	 *@Description: 添加退款记录信息
	 *@Params: rm 退款记录
	 *@Author: 王少辉
	 *@Date: 2014-11-14 上午11:16:38
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void addReMoney(ReMoney rm) {
		reMoneyDao.addReMoney(rm);
	}
}
