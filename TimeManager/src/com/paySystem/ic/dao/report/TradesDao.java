package com.paySystem.ic.dao.report;

import java.util.Date;
import java.util.List;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.report.MerSettDay;
import com.paySystem.ic.bean.report.Trades;
import com.paySystem.ic.dao.common.DAO;

/**
 * @ClassName:TradesDao
 * @Description:联机交易Dao接口
 * @date: 2013-12-24下午04:19:54
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface TradesDao extends DAO<Trades> {
	

	/**
	 *@Title:RunTradesByMerchants
	 *@Description:商户结算信息日终汇总跑批
	 *             1.统计本商户总消费金额
	 *             2.统计本商户总消费手续费
	 *             3.统计本商户总消费结算金额
	 *             4.统计本商户总退货金额
	 *             5.统计本商户总退货商户品 - 原交易的手续费
	 *             6.统计本商户总退货结算金额
	 *             8.统计本商户总充值金额
	 *             9.统计本商户总充值结算金额
	 *             10.统计本商户总结算金额 = （总消费结算金额 - 总退货结算金额 - 总充值结算金额）
	 *@param:@param merList  所有商户信息集合
	 *@param:@param settDate 结算时间 = ( 跑批时间 - 1 )
	 *@param:@return
	 *@return:List<MerSettDay> 返回商户日结信息集合
	 *@author:谢
	 *@thorws:
	 */
	List<MerSettDay> runTradesByMerchants(List<Merchants> merList,Date settDate);
	
	

	public static final String TRADESDAO = "tradesDao";
}
