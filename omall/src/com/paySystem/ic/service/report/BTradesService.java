package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.report.BTradesDTO;

/**
 * @ProjectName: omproject
 * @ClassName: BTradesService
 * @Description: 业务交易报表Service接口
 * @date: 2014-11-7 下午05:24:34
 * @author: 王少辉
 * @version: V1.0
 */
public interface BTradesService {

	/**
	 * 业务交易报表service
	 */
	String BTRADESSERVICE = "bTradesService";

	/**
	 *@Title: queryAll
	 *@Description: 分页查询业务交易报表
	 *@Params: firstindex 分页信息第一条记录索引
	 *@Params: maxresult 分页信息每页记录数
	 *@Params: tankerTradesDTO 保存分页信息
	 *@Params: orderby 排序条件
	 *@Return: QueryResult<BTradesDTO> 返回分页查询出的业务交易报表
	 *@throws: Exception 抛出异常
	 *@author: 王少辉
	 *@Date: 2014-9-10 下午04:41:59
	 */
	QueryResult<BTradesDTO> queryAll(int firstindex, int maxresult,
			BTradesDTO bTradesDTO, LinkedHashMap<String, String> orderby)
			throws Exception;

	/**
	 *@Title: findById
	 *@Description: 根据id查询业务交易信息
	 *@Params: id 主键ID
	 *@Return: BTradesDTO 返回业务交易信息
	 *@throws: Exception 抛出异常
	 *@author: 王少辉
	 *@Date: 2014-9-10 下午04:40:24
	 */
	BTradesDTO findById(int id) throws Exception;

}
