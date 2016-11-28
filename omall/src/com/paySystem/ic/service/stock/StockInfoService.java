package com.paySystem.ic.service.stock;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.StockInfo;
import com.paySystem.ic.web.dto.stock.StockInfoDTO;

/**
 * @ClassName:StockInfoService
 * @Description:库存信息Service接口
 * @date: 2014-5-28下午05:33:38
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface StockInfoService {

	
	
	/**
	 *@Title:
	 *       queryStockByCond
	 *@Description:
	 *       根据条件获取库存信息
	 *@param:
	 *       @param firstindex 起始记录数
	 *@param:
	 *       @param maxresult 每页最大条数
	 *@param:
	 *       @param stockInfoDTO 库存信息DTO
	 *@param:
	 *       @param orderby 排序
	 *@param:
	 *       @return 
	 *@param:
	 *       @throws Exception
	 *@return:
	 *       QueryResult<StockInfo> 查询结果
	 *@author:
	 *       谢洪飞
	 */
	QueryResult<StockInfo> queryStockByCond(int firstindex,
			int maxresult, StockInfoDTO stockInfoDTO,
			LinkedHashMap<String, String> orderby) throws Exception;
	
	
	/**
	 *@Title:queryStockInfoById
	 *@Description:根据Id获取StockInfoDTO信息
	 *@param:@param stockInfoId
	 *@param:@return
	 *@return:StockInfoDTO
	 *@author:谢洪飞
	 *@Thorws:
	 */
	StockInfoDTO queryStockInfoById(Integer stockInfoId);
	
	public static final String STOCKINFOSERVICE = "stockInfoService"; 
	
}
