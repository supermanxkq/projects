package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.report.GoodsSaleSumDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:GoodsSaleSumService
 * @Description:商品销量汇总排名的Service接口
 * @date: 2014-9-10
 * @author: 王楠
 * @version: V1.0
 */
public interface GoodsSaleSumService {
	
	public static final String GOODSSALESUMSERVICE="GoodsSaleSumService";

	/**
	*@Title:queryAll
	*@Description:商品销量汇总排名的信息查询
	*@Params:@param page 起始页
	*@Params:@param pageNum 页容量
	*@Params:@param goodsSaleSumDTO 商品销量汇总实体的DTO
	*@Params:@param orderBy 排序
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<GoodsSaleSumDTO>
	*@author:王楠
	*@Date:2014-9-10下午12:10:58
	*/
	public QueryResult<GoodsSaleSumDTO> queryAll(int page,int pageNum,
			GoodsSaleSumDTO goodsSaleSumDTO,
			LinkedHashMap<String,String> orderBy)throws Exception;
	
	/**
	*@Title:exportGoodsSale
	*@Description:导出汇总排名信息
	*@Params:@param goodsSaleSumDTO 商品销量汇总排名实体的DTO
	*@Params:@param orderBy 排序
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<GoodsSaleSumDTO>
	*@author:王楠
	*@Date:2014-9-10下午02:14:52
	*/
	public QueryResult<GoodsSaleSumDTO> exportGoodsSale(
			GoodsSaleSumDTO goodsSaleSumDTO,LinkedHashMap<String,String> orderBy)throws Exception;
}
