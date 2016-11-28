package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.report.ModStockDAO;
import com.paySystem.ic.service.report.ModStockService;
import com.paySystem.ic.web.dto.stock.ModStockDTO;

/**
 * @ClassName:ModStockServiceImpl
 * @Description:库存变动查看的Service接口实现
 * @date: 2014-3-7下午02:40:15
 * @author: 王楠
 * @version: V1.0
 */
@Service(ModStockService.MODSTOCKSERVICE)
public class ModStockServiceImpl implements ModStockService {
	@Resource
	ModStockDAO modStockDAO;

	/**
	 *@Title:queryAll
	 *@Description:库存变动查看的Service
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:王楠
	 */
	public QueryResult<ModStockDTO> queryAll(int fristindex, int pageNum,
			ModStockDTO modStockDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		return modStockDAO.queryAll(fristindex, pageNum, modStockDTO, orderBy);
	}

	/**
	 *@Title:exportModStockXls
	 *@Description:库存变动报表的导出Service
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:王楠
	 */
	public List<ModStockDTO> exportModStockXls(ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return modStockDAO.exportModStockXls(modStockDTO, orderBy);
	}
}
