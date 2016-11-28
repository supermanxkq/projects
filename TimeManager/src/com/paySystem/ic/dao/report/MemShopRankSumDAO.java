package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.MemShoppingSum;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.MemShoppingSumDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:MemShopRankSumDAO
 * @Description:会员购物量汇总排名的DAO接口
 * @date: 2014-7-23
 * @author: 王楠
 * @version: V1.0
 */
public interface MemShopRankSumDAO extends DAO<MemShoppingSum>{

	public static final String MEMSHOPRANKSUMDAO="MemShopRankSumDAO";
	
	/**
	*@Title:queryAll
	*@Description:会员购物量汇总排名查询
	*@Params:@param page 页
	*@Params:@param pageNum 页容量
	*@Params:@param memShoppingSum 会员购物量汇总排名的实体
	*@Params:@param orderBy 排序
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<MemShoppingSumDTO>
	*@author:王楠
	*@Date:2014-7-23下午05:45:27
	*/
	public QueryResult<MemShoppingSumDTO> queryAll(int page,int pageNum,
			MemShoppingSumDTO memShoppingSumDTO,LinkedHashMap<String, String> orderBy)throws Exception;
	
	/**
	*@Title:exportMemShopXls
	*@Description:导出汇总排名信息
	*@Params:@param memShoppingSumDTO 会员购物量汇总排名的实体DTO
	*@Params:@param orderBy 排序
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<MemShoppingSumDTO>
	*@author:王楠
	*@Date:2014-7-23下午05:49:22
	*/
	public QueryResult<MemShoppingSumDTO> exportMemShopXls(MemShoppingSumDTO memShoppingSumDTO,
			LinkedHashMap<String, String> orderBy)throws Exception;
		
}
