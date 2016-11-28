package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.report.ITradesDTO;

/**
 * @ProjectName:omallproject
 * @ClassName:ITradesService
 * @Description:积分消费记录Service接口
 * @date: 2014-11-13
 * @author: 毛智东
 * @version: V1.0
 */
public interface ITradesService {

	public static final String ITRADESSERVICE = "iTradesService";

	/**
	 * 
	 *@Title:queryAll
	 *@Description:分页查询积分消费记录
	 *@Params:@param firstindex 当前页索引
	 *@Params:@param maxresult 每页记录数
	 *@Params:@param iTradesDTO 参数保存dto
	 *@Params:@param orderby 排序
	 *@Params:@return
	 *@Params:@throws Exception 抛出异常
	 *@Return:QueryResult<ITradesDTO> 返回dto列表
	 *@author:毛智东
	 *@Date:2014-11-12上午10:04:45
	 */
	public QueryResult<ITradesDTO> queryAll(int firstindex, int maxresult,
			ITradesDTO iTradesDTO, LinkedHashMap<String, String> orderby)
			throws Exception;

	/**
	 * 
	 *@Title:findById
	 *@Description:根据主键查询dto
	 *@Params:@param tradesId 主键：流水号
	 *@Params:@return
	 *@Params:@throws Exception 抛出异常
	 *@Return:ITradesDTO 返回dto
	 *@author:毛智东
	 *@Date:2014-11-13上午09:39:57
	 */
	public ITradesDTO findById(Integer tradesId) throws Exception;
}
