package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.BTrades;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.BTradesDTO;

/**
 * @ProjectName: omproject
 * @ClassName: BTradesDAO
 * @Description: 业务交易报表DAO接口
 * @date: 2014-11-7 下午06:25:05
 * @author: 王少辉
 * @version: V1.0
 */
public interface BTradesDAO extends DAO<BTrades> {

	/**
	 * 业务交易报表DAO
	 */
	String BTRADESDAO = "bTradesDAO";

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
