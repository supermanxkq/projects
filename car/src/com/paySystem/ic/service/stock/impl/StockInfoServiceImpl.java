package com.paySystem.ic.service.stock.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.StockInfo;
import com.paySystem.ic.dao.stock.StockInfoDao;
import com.paySystem.ic.service.stock.StockInfoService;
import com.paySystem.ic.web.dto.stock.StockInfoDTO;

/**
 * @ClassName:StockInfoServiceImpl
 * @Description:库存信息Service实现类
 * @date: 2014-5-28下午05:40:35
 * @author: 谢洪飞
 * @version: V1.0
 */
@Service(StockInfoService.STOCKINFOSERVICE)
public class StockInfoServiceImpl implements StockInfoService{

	
	@Resource StockInfoDao stockInfoDao;
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
	public QueryResult<StockInfo> queryStockByCond(int firstindex,
			int maxresult, StockInfoDTO stockInfoDTO,
			LinkedHashMap<String, String> orderby) throws Exception {
		
		return stockInfoDao.queryStockByCond(firstindex, maxresult, stockInfoDTO, orderby);
	}
	
	/**
	 *@Title:queryStockInfoById
	 *@Description:根据Id获取StockInfoDTO信息
	 *@param:@param stockInfoId
	 *@param:@return
	 *@return:StockInfoDTO
	 *@author:谢洪飞
	 *@Thorws:
	 */
	public StockInfoDTO queryStockInfoById(Integer stockInfoId) {
		StockInfoDTO stockInfoDTO = stockInfoDao.queryStockInfoById(stockInfoId);
		return stockInfoDTO;
	}

}
