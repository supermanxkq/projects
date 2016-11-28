package com.paySystem.ic.service.order;

import com.paySystem.ic.bean.order.ReMoney;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoneyService
 * @Description: 退款Service接口
 * @date: 2014-11-14 上午11:06:52
 * @author: 王少辉
 * @version: V1.0
 */
public interface ReMoneyService {

	/**
	 * 退款Service
	 */
	String REMONEYSERVICE = "reMoneyService";

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
