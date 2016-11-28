package com.paySystem.ic.service.report.impl;



import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.dao.report.OrganSettTotalDAO;
import com.paySystem.ic.service.report.OrganSettTotalService;
import com.paySystem.ic.web.dto.report.OrganSettTotalDTO;



@Service(OrganSettTotalService.ORGANSETTTOTALSERVICE)
public class OrganSettTotalServiceImpl implements OrganSettTotalService {
	
	@Resource
	OrganSettTotalDAO organSettTotalDAO;

	/**
	 *@Title:queryResult
	 *@Description:机构结算报表查询service
	 *@param:@param fristIndex
	 *@param:@param pageNum
	 *@param:@param organSettTotalDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@author:王月
	 *@thorws:
	 *2014-3-20
	 *
	 */
	public List<OrganSettTotalDTO> queryAll(int page, int pageNum, 
			OrganSettTotalDTO organSettTotal,
			LinkedHashMap<String, String> orderby) throws Exception {
		return organSettTotalDAO.queryAll(page, pageNum, organSettTotal, orderby);
	}

	/**
	 *@Title:queryorganSettTotalReport
	 *@Description:导出报表的service
	 *@param:@param organSettTotal
	 *@param:@param orderBy
	 *@param:@return
	 *@author:王月
	 *@thorws:
	 *2014-3-24
	 *
	 */
	public List<OrganSettTotalDTO> queryorganSettTotalReport(
			OrganSettTotalDTO organSettTotal,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return organSettTotalDAO.queryorganSettTotalReport(organSettTotal, orderBy);
	}
	
}