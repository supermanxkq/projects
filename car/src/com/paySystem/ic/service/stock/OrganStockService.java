package com.paySystem.ic.service.stock;

import java.util.LinkedHashMap;

import javax.persistence.Query;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;

/**
 * @ClassName:OrganStockService
 * @Description:机构领卡Service接口
 * @date: 2013-12-19上午11:59:44
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface OrganStockService {

	/**
	 *@Title:addModStockInfo
	 *@Description:添加机构领卡信息
	 *@param:@param modStockDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:謝
	 *@thorws:
	 */
	ReturnDTO addModStockInfo(ModStockDTO modStockDTO) throws Exception;
	
	/**
	 *@Title:queryStockByCond
	 *@Description:根据条件查询机构领卡信息Service方法
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param modStockDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<ModStock>
	 *@thorws:
	 */
	QueryResult<ModStock> queryStockByCond(int firstindex,
			int maxresult, ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderby) throws Exception;
	
	/**
	 *@Title:loadBeginCardNo
	 *@Description:根据卡BIN加载机构领卡最小卡号
	 *@param:@param binId 卡BIN号
	 *@param:@param organId 机构号
	 *@param:@return
	 *@return:String
	 *@author:謝
	 *@thorws:
	 */
	String loadBeginCardNo(String binId,String organId,Integer status);
	
	/**
	 *@Title:appendCardNo
	 *@Description:根据卡BIN号/加载的起始卡号/数量，查询要出库的卡信息
	 *@param:@param modStockDTO  库存变动信息
	 *@param:@return
	 *@return:Query
	 *@author: 謝
	 *@thorws:
	 */
	Query appendCardNo(ModStockDTO modStockDTO);
	
	/**
	 *@Title:sureStockIn
	 *@Description:确认入库-总部领卡
	 *@Param:@param modStockDTO
	 *@Param:@return
	 *@Return:ReturnDTO
	 *@Throws:
	 */
	ReturnDTO sureStockIn(ModStockDTO modStockDTO);
	

	
	public static final String ORGANSTOCKSERV = "organStockService";
}
