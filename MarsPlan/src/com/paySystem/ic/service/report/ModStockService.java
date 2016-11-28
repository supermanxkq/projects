package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.stock.ModStockDTO;

/**
 * @ClassName:StockChangedService
 * @Description:库存变动的Service接口
 * @date: 2014-3-7下午02:08:40
 * @author: 王楠
 * @version: V1.0
 */
public interface ModStockService {
	/**
	 *@Title:queryAll
	 *@Description:库存变动查看的Service接口
	 *@param:@param fristindex
	 *@param:@param pageNum
	 *@param:@param modStockDTO
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<ModStockDTO>
	 *@author:王楠
	 */
	public QueryResult<ModStockDTO> queryAll(int fristindex, int pageNum,
			ModStockDTO modStockDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	 *@Title:exportModStockXls
	 *@Description:库存变动报表导出的Service
	 *@param:@param modStockDTO
	 *@param:@param orderBy
	 *@param:@return
	 *@return:List<ModStockDTO>
	 *@author:王楠
	 */
	public List<ModStockDTO> exportModStockXls(ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	public static final String MODSTOCKSERVICE = "modStockService";
}
