package com.paySystem.ic.dao.goods;

import java.util.List;

import com.paySystem.ic.web.dto.goods.StockPriMaDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:StockPriMaDAO
 * @Description:商品库存价格管理
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
public interface StockPriMaDAO {
	
	public static final String STOCKPRIDAO = "stockPriMaDAO";
	
	/**
	 * 
	 *@Title:saveBeans
	 *@Description:批量保存
	 *@Params:@param stockList
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:04:24
	 */
	public void saveBeans(List<StockPriMaDTO> stockList) throws Exception;
	
	/**
	 * 
	 *@Title:deleteBeans
	 *@Description:批量删除
	 *@Params:@param goodsId
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:04:34
	 */
	public void deleteBeans(Long goodsId) throws Exception;
	
	/**
	 * 
	 *@Title:saveBean
	 *@Description:保存
	 *@Params:@param stockPrmi
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:04:55
	 */
	public void saveBean(StockPriMaDTO stockPrmi) throws Exception;
	
	/**
	 * 
	 *@Title:findStockPriMaByGoodsId
	 *@Description:根据商品编号查找处理记录
	 *@Params:@param goodsId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:List<StockPriMaDTO>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:05:03
	 */
	public List<StockPriMaDTO> findStockPriMaByGoodsId(Long goodsId) throws Exception;
	
}
