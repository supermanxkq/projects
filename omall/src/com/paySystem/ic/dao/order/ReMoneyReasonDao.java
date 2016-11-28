package com.paySystem.ic.dao.order;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.ReMoReason;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.order.ReMoReasonDTO;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoneyReasonDao
 * @Description: 退款Dao接口
 * @date: 2014-11-12 下午05:53:02
 * @author: 王少辉
 * @version: V1.0
 */
public interface ReMoneyReasonDao extends DAO<ReMoReason> {

	/**
	 * 退款Dao
	 */
	String REMONEYREASONDAO = "reMoneyReasonDao";

	/**
	 *@Title: findById
	 *@Description: 根据主键ID查询退款详细
	 *@Params: id 主键ID
	 *@Return: ReMoReasonDTO 返回退款详细
	 *@throws: Exception 抛出异常
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午06:11:07
	 */
	ReMoReasonDTO findById(int id) throws Exception;

	/**
	 *@Title: queryAll
	 *@Description: 分页查询退款信息
	 *@Params: firstindex 分页信息第一条记录索引
	 *@Params: maxresult 分页信息每页记录数
	 *@Params: reMoReasonDTO 保存分页信息
	 *@Params: orderBy 排序条件
	 *@Return: QueryResult<ReMoReasonDTO> 返回分页退款信息
	 *@throws: Exception 抛出异常
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午07:00:05
	 */
	QueryResult<ReMoReasonDTO> queryAll(int firstindex, int maxresult,
			ReMoReasonDTO reMoReasonDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

}
