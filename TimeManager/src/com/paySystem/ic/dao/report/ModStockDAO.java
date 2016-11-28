package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;

/**
 * @ClassName:StockChangedDAO
 * @Description:库存变动的DAO接口
 * @date: 2014-3-7上午09:39:56
 * @author: 王楠
 * @version: V1.0
 */
public interface ModStockDAO extends DAO<ModStock> {

	public static final String MODSTOCKDDAO = "modStockDAO";

	/**
	 *@Title:queryAll
	 *@Description:库存变动查看的实现方法
	 *@param:@param firstindex
	 *@param:@param pageNum
	 *@param:@param modStockDTO
	 *@param:@param order
	 *@param:@return
	 *@return:List<ModStockDTO>
	 *@author:王楠
	 */
	public QueryResult<ModStockDTO> queryAll(int firstindex, int pageNum,
			ModStockDTO modStockDTO, LinkedHashMap<String, String> order)
			throws Exception;

	/**
	 *@Title:exportStockChangedXls
	 *@Description: 库存变动报表导出
	 *@param:@param termConsTotalDTO
	 *@param:@param orderBy
	 *@param:@return
	 *@return:List<TermConsTotalDTO>
	 *@author:王楠
	 */
	public List<ModStockDTO> exportModStockXls(ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;
}
