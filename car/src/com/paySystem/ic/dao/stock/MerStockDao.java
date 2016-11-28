package com.paySystem.ic.dao.stock;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;


/**
 * @ClassName:MerStockDao
 * @Description:商户库存变动Dao接口
 * @date: 2014-5-27上午11:02:27
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerStockDao extends DAO<ModStock> {

	
	/**
	 *@Title:saveMerStock
	 *@Description:
	 *            商户领卡，保存库存变动信息
	 *@param:
	 *            @param modStockDTO
	 *@return:
	 *             void
	 *@author:
	 *             谢洪飞
	 */
	public ReturnDTO saveMerStock(ModStockDTO modStockDTO);
	
	/**
	 *@Title:queryStockByCond
	 *@Description:根据条件查询商户领卡信息Dao方法
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
	 *@Title:sureStockIn
	 *@Description:商户领卡：确认入库操作 
	 *@param:@param modStockDTO
	 *@return: void
	 *@author: 谢洪飞
	 */
	public void sureStockIn(ModStockDTO modStockDTO);
	
	public static final String MERSTOCKDAO = "merStockDao";
}
