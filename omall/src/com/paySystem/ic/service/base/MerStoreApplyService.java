package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.base.MerStoreApplyDTO;

/**
 * 
 * @ProjectName: backomall
 * @ClassName: MerStoreApplyService
 * @Description: 商户入驻申请的Service层接口
 * @date: 2014-11-21 上午09:46:48
 * @author: 郭营
 * @version: V1.0
 */
public interface MerStoreApplyService {
	public static final String AUDITMERCHANTSSERVICE = "auditMerchantsService";

	/**
	 * 
	 *@Title: queryAll
	 *@Description: 查询商户入驻申请的数据
	 *@Params: @param page 起始页
	 *@Params: @param pageNum 终止页
	 *@Params: @param merStoreApplyDTO
	 *@Params: @param orderBy 排序方式
	 *@Params: @return
	 *@Params: @throws Exception
	 *@Return: QueryResult<MerStoreApplyDTO>
	 *@author: 郭营
	 *@Date: 2014-11-21上午09:23:12
	 */
	QueryResult<MerStoreApplyDTO> queryAll(int page, int pageNum,
			MerStoreApplyDTO merStoreApplyDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 * 
	 *@Title: MerApplyAction
	 *@Description: 商户入驻申请审核
	 *@Params: @param modfiyValue 要审核的申请记录
	 *@Params: @param status 状态
	 *@Params: @return
	 *@Params: @throws Exception
	 *@Return: String
	 *@author: 郭营
	 *@Date: 2014-11-21上午09:43:51
	 */
	public String MerApplyAction(String modfiyValue, String status)
			throws Exception;
}
