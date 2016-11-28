package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.web.dto.report.TermConsTotalDTO;

/**
 * @ClassName:MerConsTotalService
 * @Description:商户周期结算Service接口
 * @date: 2014-2-27上午11:36:50
 * @author: 张亚运
 * @version: V1.0
 */


public interface MerConsTotalService {
	
	public static final String MERCONSTOTALSERVICE = "merConsTotalService";
	
	
	/**
	 *@Title:queryAll
	 *@Description:查询商户交易汇总信息
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param merConsTotal
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<TermConsTotalDTO>
	 *@author:张亚运
	 *@thorws:
	 */
	public List<TermConsTotalDTO> queryAll(int page, int pageNum,
			TermConsTotalDTO merConsTotal,LinkedHashMap<String, String> orderBy)
			throws Exception;
	
	
	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出Excel调用
	 *@param:@param merConsTotal
	 *@param:@param orderBy
	 *@param:@return
	 *@return:List<TermConsTotalDTO>
	 *@author:
	 *@thorws:
	 */
	public List<TermConsTotalDTO> queryMerConsTotalReport(TermConsTotalDTO merConsTotal,
			LinkedHashMap<String, String>orderBy);
	
}








