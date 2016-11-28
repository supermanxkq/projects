package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.report.MemShopRankSumDAO;
import com.paySystem.ic.service.report.MemShopRankSumService;
import com.paySystem.ic.web.dto.report.MemShoppingSumDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:MemShopRankSumServiceImpl
 * @Description:会员购物量汇总排名的Service接口的实现类
 * @date: 2014-7-24
 * @author: 王楠
 * @version: V1.0
 */
@Service(MemShopRankSumService.MEMSHOPRANKSUMSERVICE)
public class MemShopRankSumServiceImpl implements MemShopRankSumService{

	@Resource
	MemShopRankSumDAO memShopRankSumDAO;
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.report.MemShopRankSumService#queryAll(int, int, com.paySystem.ic.web.dto.report.MemShoppingSumDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:会员购物量汇总查询的Service接口的实现
	 *@param page 页码
	 *@param pageNum 页容量
	 *@param memShoppingSumDTO 会员购物量汇总排名的DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-7-24下午02:38:44
	 */
	public QueryResult<MemShoppingSumDTO> queryAll(int page, int pageNum,
			MemShoppingSumDTO memShoppingSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		/**调用DAO获取QueryResult结果*/
		QueryResult<MemShoppingSumDTO> queryResult=
			memShopRankSumDAO.queryAll(page, pageNum, memShoppingSumDTO, orderBy);
		return queryResult;
	}
	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.report.MemShopRankSumService#exportMemShopXls(com.paySystem.ic.web.dto.report.MemShoppingSumDTO, java.util.LinkedHashMap)
	 *@MethodName:exportMemShopXls
	 *@Description:导出会员购物量汇总查询信息的Service接口实现
	 *@param memShoppingSumDTO 会员购物量汇总排名的DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-7-24下午02:39:46
	 */
	public QueryResult<MemShoppingSumDTO> exportMemShopXls(
			MemShoppingSumDTO memShoppingSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return memShopRankSumDAO.exportMemShopXls(memShoppingSumDTO, orderBy);
	}


}
