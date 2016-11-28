package com.paySystem.ic.service.order;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.order.ReMoReasonDTO;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoneyReasonService
 * @Description: 退款Service接口
 * @date: 2014-11-12 下午05:40:37
 * @author: 王少辉
 * @version: V1.0
 */
public interface ReMoneyReasonService {

	/**
	 * 退款Service
	 */
	String REMONEYREASONSERVICE = "reMoneyReasonService";

	/**
	 *@Title: queryAll
	 *@Description: 分页查询退款信息
	 *@Params: firstindex 分页信息第一条记录索引
	 *@Params: maxresult 分页信息每页记录数
	 *@Params: tankerTradesDTO 保存分页信息
	 *@Params: orderBy 排序条件
	 *@Return: QueryResult<ReMoReasonDTO> 返回分页退款信息
	 *@throws: Exception 抛出异常
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午06:00:49
	 */
	QueryResult<ReMoReasonDTO> queryAll(int firstindex, int maxresult,
			ReMoReasonDTO reMoReasonDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	 *@Title: findById
	 *@Description: 根据主键ID查询退款详细
	 *@Params: id 主键ID
	 *@Return: ReMoReasonDTO 返回退款详细
	 *@throws: Exception 抛出异常
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午06:04:01
	 */
	ReMoReasonDTO findById(int id) throws Exception;

	/**
	 *@Title: auditSuccess
	 *@Description: 审核通过，更新申请表审核状态并在申请记录表添加审核记录
	 *@Params: reMoReasonDTO 申请信息
	 *@Return: void
	 *@author: 王少辉
	 *@Date: 2014-12-2 下午04:40:44
	 */
	void auditSuccess(ReMoReasonDTO reMoReasonDTO) throws Exception;
	
	/**
	 *@Title: auditFailure
	 *@Description: 审核拒绝，更新申请表审核状态并在申请记录表添加审核记录
	 *@Params: reMoReasonDTO 申请信息
	 *@Return: void
	 *@author: 王少辉
	 *@Date: 2014-12-2 下午04:50:39
	 */
	void auditFailure(ReMoReasonDTO reMoReasonDTO) throws Exception;

}
