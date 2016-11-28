package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.SalesSummary;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.SalesSummaryDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:SalesSummaryDAO
 * @Description:销售额汇总的DAO接口
 * @date: 2014-7-16
 * @author: 王楠
 * @version: V1.0
 */
public interface SalesSummaryDAO extends DAO<SalesSummary> {

	public static final String SALESSUMMARYDAO = "salesSummaryDAO";

	/**
	 *@Title:queryAll
	 *@Description:查询销售额汇总信息
	 *@Params:@param page 页码
	 *@Params:@param pageNum 页容量
	 *@Params:@param salesSummaryDTO 销售额汇总的实体DTO
	 *@Params:@param orderBy 排序
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:QueryResult<SalesSummaryDTO>
	 *@author:王楠
	 *@Date:2014-7-16下午12:07:45
	 */
	public QueryResult<SalesSummaryDTO> queryAll(int page, int pageNum,
			SalesSummaryDTO salesSummaryDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 *@Title:exportSalesSummary
	 *@Description:导出销售额汇总数据
	 *@Params:@param salesSummaryDTO 销售额汇总实体的DTO
	 *@Params:@param orderBy 排序
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:QueryResult<SalesSummaryDTO>
	 *@author:王楠
	 *@Date:2014-7-16下午02:15:49
	 */
	public QueryResult<SalesSummaryDTO> exportSalesSummary(
			SalesSummaryDTO salesSummaryDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 *@Title:getSalesSummaryDTO
	 *@Description:将实体转换成DTO
	 *@Params:@param salesSummary 销售额汇总实体
	 *@Params:@return
	 *@Return:SalesSummaryDTO 销售额汇总实体的DTO
	 *@author:王楠
	 *@Date:2014-7-16下午04:11:35
	 */
	public SalesSummaryDTO getSalesSummaryDTO(SalesSummary salesSummary);

	/**
	 *@Title:getSalesSummary
	 *@Description:将DTO转换成实体
	 *@Params:@param salesSummaryDTO
	 *@Params:@return
	 *@Return:SalesSummary
	 *@author:王楠
	 *@Date:2014-7-17上午09:56:00
	 */
	public SalesSummary getSalesSummary(SalesSummaryDTO salesSummaryDTO);

}
