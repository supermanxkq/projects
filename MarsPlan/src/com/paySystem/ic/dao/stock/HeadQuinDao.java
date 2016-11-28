package com.paySystem.ic.dao.stock;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;

/**
 * @ClassName:HeadQuinDao
 * @Description:总部入库信息Dao接口
 * @date: 2013-12-11下午04:51:44
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface HeadQuinDao extends DAO<ModStock> {

	public static final String HEADQUINDAO ="headQuinDao";
	
	/**
	 *@Title:queryStockByCond
	 *@Description:根据条件查询总部入库信息
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param modStockDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<ModStock>
	 *@thorws:
	 */
	QueryResult<ModStock> queryStockByCond(int firstindex, int maxresult,
			ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderby,int flag) throws Exception;
	
	/**
	 *@Title:addModStockInfo
	 *@Description:添加库存变动信息--总部入库
	 *@param:@param modStockDTO 库存变动DTO对象
	 *@param:@return 
	 *@return:ReturnDTO 返回结果DTO
	 *@author: 謝
	 *@thorws:
	 */
	ReturnDTO addModStockInfo(ModStockDTO modStockDTO,int flag) throws Exception;
	
	/**
	 *@Title:modfiyStatusStockIn
	 *@Description:确认入库:更改库存变动信息表中信息状态为：从 0:未入库 → 1:已入库
	 *@Param:@return
	 *@Return:ReturnDTO
	 *@Throws:
	 */
	ReturnDTO modfiyStatusStockIn(ModStockDTO modStockDTO) throws Exception;
	/**
	* @Title: getModStockSerNo
	* @Description: 获得库存变动表ID
	* @param @return 
	* @return String
	* @throws
	* @author lily
	* @date 2013-12-24 下午07:49:12
	 */
	public String getModStockSerNo();
	/**
	* @Title: saveModStockDTO
	* @Description: 保存ModStockDTO接口
	* @param @param ModStockDTO 
	* @return void
	* @throws
	* @author lily
	* @date 2013-12-24 下午07:48:29
	 */
	public void saveModStockDTO(ModStockDTO ModStockDTO);
}
