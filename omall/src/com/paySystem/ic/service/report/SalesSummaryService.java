package com.paySystem.ic.service.report;
import java.util.LinkedHashMap;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.report.SalesSummaryDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:SalesSummaryService
 * @Description:销售额汇总的Service接口
 * @date: 2014-7-16
 * @author: 王楠
 * @version: V1.0
 */
public interface SalesSummaryService {

	public static String SALESSUMMARYSERVICE="salesSummaryService";
	
	/**
	*@Title:queryAll
	*@Description:查询汇总信息的Service接口
	*@Params:@param page 页
	*@Params:@param pageNum 页容量
	*@Params:@param salesSummaryDTO 销售额汇总实体的DTO
	*@Params:@param orderBy 排序
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<SalesSummaryDTO>
	*@author:王楠
	*@Date:2014-7-16下午03:27:25
	*/
	public QueryResult<SalesSummaryDTO> queryAll(int page,int pageNum,
			SalesSummaryDTO salesSummaryDTO,LinkedHashMap<String,String> orderBy)throws Exception;
	
	/**
	*@Title:exportSalesSummary
	*@Description:导出汇总信息
	*@Params:@param salesSummaryDTO 销售额汇总实体的DTO 
	*@Params:@param orderBy 排序
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<SalesSummaryDTO>
	*@author:王楠
	*@Date:2014-7-16下午03:28:54
	*/
	public QueryResult<SalesSummaryDTO> exportSalesSummary(
			SalesSummaryDTO salesSummaryDTO,LinkedHashMap<String,String> orderBy)throws Exception; 
	
}
