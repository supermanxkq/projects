package com.paySystem.ic.dao.report;

import java.util.List;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.report.MerSettDay;
import com.paySystem.ic.bean.report.MerSettTotal;
import com.paySystem.ic.dao.common.DAO;

/**
 * @ClassName:MerSettDayDao
 * @Description:商户日终跑批信息记录表
 * @date: 2013-12-24下午02:47:57
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerSettDayDao extends DAO<MerSettDay> {

	public static final String MerSettDayDao = "MERSETTDAYDAO";
	
	/**
	 *@Title:saveMerSettDay
	 *@Description:保存商户日终跑批信息
	 *@param:@param merSettDayList
	 *@return:void
	 *@author:谢
	 *@thorws:
	 */
	void saveMerSettDay(List<MerSettDay> merSettDayList) throws Exception;
	
	/**
	 *@Title:runMerSettToTotal
	 *@Description:周期跑批
	 *@param:@param merchantsList 商户集合
	 *@param:@param beginSettDate      结算周期开始日期
	 *@param:@param endSettDate        结算周期结算日期
	 *@param:@return 
	 *@return:List<MerSettTotal>  周期结算信息记录集合
	 *@author:  谢
	 *@thorws:
	 */
	List<MerSettTotal> runMerSettToTotal(List<Merchants> merchantsList);
	

	/**
	 *@Title:queryMerSettDayBeforeToday
	 *@Description:查询商户日结算表中跑批日期中跑批日期最大的记录
	 *@param:@param merId  商户编号
	 *@param:@return
	 *@return:MerSettDay    本商户日跑批结算记录统计日期最大的记录
	 *@author:谢
	 *@thorws:
	 */
	MerSettDay queryMerSettDayBeforeToday(String merId);
}
