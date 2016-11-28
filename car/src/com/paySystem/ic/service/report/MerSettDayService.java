package com.paySystem.ic.service.report;

import com.paySystem.ic.web.dto.ReturnDTO;

/**
 * @ClassName:MerSettDayService
 * @Description:商户日终结算信息汇总跑批Service接口
 * @date: 2013-12-25上午10:10:39
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerSettDayService {

	/**
	 *@Title:runMerSettDay
	 *@Description:日跑批汇总调度
	 *             
	 *             1.汇总交易表中所有前日信息
	 *             2.保存商户日结算信息
	 *             
	 *             调度周期：每天
	 *             
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:
	 *@thorws:
	 */
	ReturnDTO runMerSettDay();
	
	public static final String MERSETTDAYSERV = "merSettDayService";
}
