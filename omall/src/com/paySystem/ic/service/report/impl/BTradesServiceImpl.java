package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.report.BTradesDAO;
import com.paySystem.ic.service.report.BTradesService;
import com.paySystem.ic.web.dto.report.BTradesDTO;

/**
 * @ProjectName: omproject
 * @ClassName: BTradesServiceImpl
 * @Description: 业务交易报表Service实现类
 * @date: 2014-11-7 下午05:25:36
 * @author: 王少辉
 * @version: V1.0
 */
@Service(BTradesService.BTRADESSERVICE)
public class BTradesServiceImpl implements BTradesService {

	/**
	 * 业务交易DAO
	 */
	@Resource
	private BTradesDAO bTradesDAO;

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.report.BTradesService#findById
	 *                        (int)
	 *@MethodName: findById
	 *@Description: 根据id查询业务交易信息
	 *@Params: id 主键ID
	 *@Return: BTradesDTO 返回业务交易信息
	 *@throws: Exception 抛出异常
	 *@Author: 王少辉
	 *@Date: 2014-11-7 下午06:21:30
	 */
	@Override
	public BTradesDTO findById(int id) throws Exception {
		return bTradesDAO.findById(id);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.report.BTradesService#queryAll
	 *                        (int, int,
	 *                        com.paySystem.ic.web.dto.report.BTradesDTO,
	 *                        java.util.LinkedHashMap)
	 *@MethodName: queryAll
	 *@Description: 分页查询业务交易报表
	 *@Params: firstindex 分页信息第一条记录索引
	 *@Params: maxresult 分页信息每页记录数
	 *@Params: tankerTradesDTO 保存分页信息
	 *@Params: orderby 排序条件
	 *@Return: QueryResult<BTradesDTO> 返回分页查询出的业务交易报表
	 *@throws: Exception 抛出异常
	 *@Author: 王少辉
	 *@Date: 2014-11-7 下午06:21:30
	 */
	@Override
	public QueryResult<BTradesDTO> queryAll(int firstindex, int maxresult,
			BTradesDTO bTradesDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		return bTradesDAO.queryAll(firstindex, maxresult, bTradesDTO, orderby);
	}

}
