package com.paySystem.ic.service.stock;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;


/**
 * @ClassName:MerStockService
 * @Description:商户领卡Service
 * @date: 2014-5-26下午02:19:16
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerStockService {
	
	public static final String MERSTOCKSERVICE = "merStockService";
	
	/**
	 *@Title:
	 *      saveMerStockIn
	 *@Description:
	 *      保存商户库存变动信息
	 *      
	 *            1.保存库存变动信息；
	 *            
	 *@param:
	 *      modStockDTO 库存变动信息DTO
	 *@return:
	 *        void
	 *@author:
	 *       谢洪飞
	 */
	public void saveMerStockIn(ModStockDTO modStockDTO) throws Exception;
	
	/**
	 *@Title:queryStockByCond
	 *@Description:根据条件查询商户领卡信息Service方法
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param modStockDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<ModStock>
	 */
	public QueryResult<ModStock> queryStockByCond(int firstindex,
			int maxresult, ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderby) throws Exception;
	
	/**
	 *@Title:
	 *          sureStockIn
	 *@Description:
	 *          商户领卡：确认入库操作Service
	 *@param:
	 *          @param modStockDTO  库存变动DTO对象
	 *@param:
	 *          @return
	 *@return:
	 *          ReturnDTO  返回DTO对象
	 *@author:
	 *          谢洪飞
	 */
	public ReturnDTO sureStockIn(ModStockDTO modStockDTO)  throws Exception;
	
}
