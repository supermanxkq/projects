package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.report.GoodsSaleSumDAO;
import com.paySystem.ic.service.report.GoodsSaleSumService;
import com.paySystem.ic.web.dto.report.GoodsSaleSumDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:GoodsSaleSumServiceImpl
 * @Description:商品销量汇总排名的Service接口的实现类
 * @date: 2014-9-10
 * @author: 王楠
 * @version: V1.0
 */
@Service(GoodsSaleSumService.GOODSSALESUMSERVICE)
public class GoodsSaleSumServiceImpl implements GoodsSaleSumService{

	@Resource
	GoodsSaleSumDAO goodsSaleSumDAO;
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.report.GoodsSaleSumService#queryAll(int, int, com.paySystem.ic.web.dto.report.GoodsSaleSumDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:查询商品销量汇总的信息
	 *@param page 起始页
	 *@param pageNum 页容量
	 *@param goodsSaleSumDTO 商品销量汇总实体的DTO
	 *@param orderBy 排序
	 *@return 
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-10下午03:00:02
	 */
	public QueryResult<GoodsSaleSumDTO> queryAll(int page, int pageNum,
			GoodsSaleSumDTO goodsSaleSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		
		return goodsSaleSumDAO.queryAll(page, pageNum, goodsSaleSumDTO, orderBy);
	}
	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.report.GoodsSaleSumService#exportGoodsSale(com.paySystem.ic.web.dto.report.GoodsSaleSumDTO, java.util.LinkedHashMap)
	 *@MethodName:exportGoodsSale
	 *@Description:导出商品汇总信息
	 *@param goodsSaleSumDTO 商品销量汇总实体的DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-10下午03:02:25
	 */
	public QueryResult<GoodsSaleSumDTO> exportGoodsSale(
			GoodsSaleSumDTO goodsSaleSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {

		return goodsSaleSumDAO.exportGoodsSale(goodsSaleSumDTO, orderBy);
	}


}
