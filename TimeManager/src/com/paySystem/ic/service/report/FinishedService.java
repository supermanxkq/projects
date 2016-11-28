package com.paySystem.ic.service.report;
import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:SalesSummaryService
 * @Description:销售额汇总的Service接口
 * @date: 2014-7-16
 * @author: 王楠
 * @version: V1.0
 */
public interface FinishedService {

	public static String FINISHEDSERVICE="FinishedService";
	
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
	public QueryResult<MainTaskDTO> queryAll(int page,int pageNum,
			MainTaskDTO mainTaskDTO,LinkedHashMap<String,String> orderBy)throws Exception;
	
	/**
	 *@MethodName:exportMainTasks 
	 *@Description:导出信息
	 *@param mainTaskDTO主任务数据传输对象
	 *@param orderBy排序
	 *@author:徐凯强
	 *@return QueryResult<MainTaskDTO>集合、条数
	 *@date:2014-12-20下午11:01:20
	 */
	public QueryResult<MainTaskDTO> exportMainTasks(
			MainTaskDTO mainTaskDTO,LinkedHashMap<String,String> orderBy)throws Exception; 
	
}
