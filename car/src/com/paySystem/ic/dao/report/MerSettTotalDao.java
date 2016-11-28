package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.report.MerSettTotal;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.MerSettTotalDTO;

/**
 * @ClassName:MerSettTotalDao
 * @Description:商户周期跑批Dao接口
 * @date: 2013-12-25上午10:29:17
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerSettTotalDao extends DAO<MerSettTotal> {
	
	
	/**
	 *@Title:saveMerSettTotal
	 *@Description:批量保存商户周期结算信息
	 *@param:@param merSettTotalList
	 *@param:@throws Exception
	 *@return:void
	 *@author: 谢
	 *@thorws:
	 */
	void saveMerSettTotal(List<MerSettTotal> merSettTotalList) throws Exception;
	
	/**
	 *@Title:queryMerSettTotal
	 *@Description:查询商户周期结算信息
	 *@param:@param merSettTotalDTO
	 *@param:@return
	 *@return:Query
	 *@author:谢
	 *@thorws:
	 */
	public QueryResult<MerSettTotal> queryMerSettTotal(int fristindex, int pageNum,
			                                            MerSettTotalDTO merSettTotalDTO, LinkedHashMap<String, String> orderBy) throws Exception;
	/**
	 *@Title:sureMerSett
	 *@Description:确认结算
	 *@param:@param merSettTotalDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:谢
	 *@thorws:
	 */
	MerSettTotalDTO sureMerSett(MerSettTotalDTO merSettTotalDTO);
	
	/**
	 *@Title:getAllNeedSettMerId
	 *@Description:获取所有要结算的商户集合信息
	 *@param:@param mersList  所有商户集合
	 *@param:@return
	 *@return:List<String> 所有需要结算的商户编号
	 *@author: 谢
	 *@thorws:
	 */
	List<String> getAllNeedSettMerId(List<Merchants> mersList);
	
	/**
	 *@Title:getLastSettInfo
	 *@Description:获取上次结算信息
	 *@param:@param merId  商户ID
	 *@param:@return
	 *@return:MerSettTotal
	 *@author:谢
	 *@thorws:
	 */
	MerSettTotal getLastSettInfo(String merId);
	
	public static final String MERSETTTAOTALDAO = "merSettTotalDao";

}
