package com.paySystem.ic.service.report;


import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.web.dto.report.OrganSettTotalDTO;


/**
 * @ClassName:OrganSettTotalService
 * @Description:机构结算报表查询
 * @date: 2014-3-20上午09:01:37
 * @author: 王月
 * @version: V1.0
 */
public interface OrganSettTotalService{
	
	public static final String ORGANSETTTOTALSERVICE = "organSettTotalService";
	
	
	/**
	 *@Title:queryAll
	 *@Description:机构结算报表查询service
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param organSettTotal
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<OrganSettTotalDTO>
	 *@author:王月
	 *@thorws:
	 */
	public List<OrganSettTotalDTO> queryAll(int page, int pageNum,
			OrganSettTotalDTO organSettTotal,LinkedHashMap<String, String> orderBy) throws Exception ;	
			
	/**
	 *@Title:queryorganSettTotalReport
	 *@Description:导出service
	 *@param:@param organSettTotalDTO
	 *@param:@param orderBy
	 *@param:@return
	 *@return:List<OrganSettTotalDTO>
	 *@author:王月
	 *@thorws:
	 */
	public List<OrganSettTotalDTO> queryorganSettTotalReport(
			OrganSettTotalDTO organSettTotal,LinkedHashMap<String, String> orderBy);

}