package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.report.SalesSummaryDAO;
import com.paySystem.ic.service.report.SalesSummaryService;
import com.paySystem.ic.web.dto.report.SalesSummaryDTO;
/**
 * @ProjectName:MCIU_DS
 * @ClassName:SalesSummaryServiceImpl
 * @Description:销售额汇总的Service接口实现
 * @date: 2014-7-16
 * @author: 王楠
 * @version: V1.0
 */
@Service(SalesSummaryService.SALESSUMMARYSERVICE)

public class SalesSummaryServiceImpl implements SalesSummaryService{

	@Resource
	SalesSummaryDAO salesSummaryDAO;

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.report.SalesSummaryService#queryAll(int, int, com.paySystem.ic.web.dto.report.SalesSummaryDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:查询汇总信息的Service实现
	 *@param page 页
	 *@param pageNum 页容量
	 *@param salesSummaryDTO 销售额汇总信息实体的DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-7-16下午03:43:24
	 */
	public QueryResult<SalesSummaryDTO> queryAll(int page, int pageNum,
			SalesSummaryDTO salesSummaryDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		/** 调用DAO获取QueryResult结果 */
		QueryResult<SalesSummaryDTO> queryResult=salesSummaryDAO.queryAll(
				page, pageNum, salesSummaryDTO, orderBy);
		return queryResult;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.report.SalesSummaryService#exportSalesSummary(com.paySystem.ic.web.dto.report.SalesSummaryDTO, java.util.LinkedHashMap)
	 *@MethodName:exportSalesSummary
	 *@Description:导出汇总信息的Service接口实现
	 *@param salesSummaryDTO 销售额汇总信息实体的DTO
	 *@param orderBy 排序
	 *
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-7-16下午03:44:26
	 */
	public QueryResult<SalesSummaryDTO> exportSalesSummary(
			SalesSummaryDTO salesSummaryDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return salesSummaryDAO.exportSalesSummary(salesSummaryDTO, orderBy);
	}
}
