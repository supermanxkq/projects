package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.MerSettTotal;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.report.MerSettTotalDTO;

/**
 * @ClassName:MerSettTotalService
 * @Description:商户周期结算跑批Service接口
 * @date: 2013-12-25下午01:52:18
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerSettTotalService {

	/**
	 *@Title:runMerSettTotal
	 *@Description:商户周期结算信息跑批汇总
	 *
	 *             1.跑批汇总商户日结算信息表中本周期记录
	 *             2.保存周期结算信息
	 *             
	 *             调度频率：一个结算周期
	 *             
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:谢
	 *@thorws:
	 */
	ReturnDTO runMerSettTotal();
	
	/**
	 *@Title:queryMerSettTotal
	 *@Description:查询商户周期结算信息
	 *@param:@param merSettTotalDTO
	 *@param:@return
	 *@return:Query 商户周期结算信息集合查询Query
	 *@author:谢
	 *@thorws:
	 */
	public QueryResult<MerSettTotal> queryMerSettTotal(int fristindex, int pageNum,
            MerSettTotalDTO merSettTotalDTO, LinkedHashMap<String, String> orderBy) throws Exception ;
	
	/**
	 *@Title:sureMerSett
	 *@Description:确认结算
	 *@param:@param merSettTotalDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:谢
	 *@thorws:
	 */
	ReturnDTO sureMerSett(MerSettTotalDTO merSettTotalDTO);
	
	public static final String MERSETTTOTALSERVICE = "merSettTotalService";
}
