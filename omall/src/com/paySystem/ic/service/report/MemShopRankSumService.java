package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.report.MemShoppingSumDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:MemShopRankSumService
 * @Description:会员购物量汇总service接口
 * @date: 2014-7-24
 * @author: 王楠
 * @version: V1.0
 */
public interface MemShopRankSumService {
	public static String MEMSHOPRANKSUMSERVICE = "memShopRankSumService";

	/**
	 *@Title:queryAll
	 *@Description:查询会员购物量汇总的Service接口
	 *@Params:@param page 页
	 *@Params:@param pageNum 页容量
	 *@Params:@param memShoppingSumDTO 查询会员购物量汇总的DTO
	 *@Params:@param orderBy 排序
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:QueryResult<MemShoppingSumDTO>
	 *@author:王楠
	 *@Date:2014-7-24下午02:27:27
	 */
	public QueryResult<MemShoppingSumDTO> queryAll(int page, int pageNum,
			MemShoppingSumDTO memShoppingSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 *@Title:exportMemShopXls
	 *@Description:导出会员购物量汇总信息
	 *@Params:@param memShoppingSumDTO 会员购物量汇总实体DTO
	 *@Params:@param orderBy 排序
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:QueryResult<MemShoppingSumDTO>
	 *@author:王楠
	 *@Date:2014-7-24下午02:30:06
	 */
	public QueryResult<MemShoppingSumDTO> exportMemShopXls(
			MemShoppingSumDTO memShoppingSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;
}
