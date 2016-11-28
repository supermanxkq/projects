package com.paySystem.ic.dao.stock;

import java.util.List;

import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.stock.ModStockDetail;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.stock.ModStockDetailDTO;

/**
 * @ClassName:StockInfoDao
 * @Description:库存明细Dao接口
 * @date: 2013-12-13下午05:51:32
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface ModStoDetDao extends DAO<ModStockDetail>{

	public static final String MODSTODETDAO ="modStoDetDao";
	
	/**
	 *@Title:saveStockInfo
	 *@Description:保存库存明细信息
	 *@param:@param modStockDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author: 謝
	 *@thorws:
	 */
	ReturnDTO saveStockInfo(ModStockDTO modStockDTO) throws Exception;
	
	/**
	 *@Title:stockDetial
	 *@Description:入库审核/查看详情时，加载要查询的数据
	 *@param:@param modStockDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author: 謝
	 *@thorws:
	 */
	ReturnDTO stockDetial(ModStockDTO modStockDTO);
	
	/**
	 *@Title:queryCardNos
	 *@Description:根据库存变动流水号，查找对应的卡号信息
	 *@Param:@param modStockDTONo
	 *@Param:@return
	 *@Return:ReturnDTO
	 *@Throws:
	 */
	List<CardNo> queryCardNos(String modStockDTONo);
	/**
	* @Title: saveModStockDetailDTO
	* @Description: 保存modStockDetailDTO的接口
	* @param @param setModStockDetailDTO 
	* @return void
	* @throws
	* @author lily
	* @date 2013-12-24 下午08:11:35
	 */
	public void saveModStockDetailDTO(ModStockDetailDTO modStockDetailDTO);
	
}
