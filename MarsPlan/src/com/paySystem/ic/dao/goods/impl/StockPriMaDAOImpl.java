package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.goods.StockPriMa;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.StockPriMaDAO;
import com.paySystem.ic.web.dto.goods.StockPriMaDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:StockPriMaDAOImpl
 * @Description:商品库存价格管理dao实现类
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
@Repository(StockPriMaDAO.STOCKPRIDAO)
public class StockPriMaDAOImpl extends DaoSupport<StockPriMa> implements StockPriMaDAO {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.StockPriMaDAO#saveBeans(java.util.List)
	 *@MethodName:saveBeans
	 *@Description:批量保存
	 *@param stockList
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:19:14
	 */
	public void saveBeans(List<StockPriMaDTO> stockList) throws Exception {
		this.saves(batchCopy(stockList));
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.StockPriMaDAO#saveBean(com.paySystem.ic.web.dto.goods.StockPriMaDTO)
	 *@MethodName:saveBean
	 *@Description:保存
	 *@param stockPrmi
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:19:35
	 */
	public void saveBean(StockPriMaDTO stockPrmi) throws Exception {
		this.save(stockPrmi);
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.StockPriMaDAO#deleteBeans(java.lang.Long)
	 *@MethodName:deleteBeans
	 *@Description:批量删除
	 *@param goodsId
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:19:46
	 */
	public void deleteBeans(Long goodsId) throws Exception {
		em.createQuery("delete from StockPriMa o where o.goodsId = ?").setParameter(1, String.valueOf(goodsId)).executeUpdate();
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.StockPriMaDAO#findStockPriMaByGoodsId(java.lang.Long)
	 *@MethodName:findStockPriMaByGoodsId
	 *@Description:根据商品编号查找库存
	 *@param goodsId
	 *@return
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:20:00
	 */
	@SuppressWarnings("unchecked")
	public List<StockPriMaDTO> findStockPriMaByGoodsId(Long goodsId)
			throws Exception {
		List<StockPriMa> stockPriMaList = em.createQuery("from StockPriMa o where o.goodsId=?").setParameter(1, String.valueOf(goodsId)).getResultList();
		return bean2DTO(stockPriMaList);
	}

	/**
	 * 
	 *@Title:bean2DTO
	 *@Description:dto转bean
	 *@Params:@param stockPriMaList
	 *@Params:@return
	 *@Return:List<StockPriMaDTO>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:20:07
	 */
	private List<StockPriMaDTO> bean2DTO(List<StockPriMa> stockPriMaList) {
		List<StockPriMaDTO> stockPriList = new ArrayList<StockPriMaDTO>();
		if(CollectionUtils.isNotEmpty(stockPriMaList)) {
			for(StockPriMa spmd : stockPriMaList) {
				StockPriMaDTO spm = new StockPriMaDTO();
				BeanUtils.copyProperties(spmd, spm);
				stockPriList.add(spm);
			}
		}
		return stockPriList;
	}
	
	/**
	 * 
	 *@Title:batchCopy
	 *@Description:批量复制
	 *@Params:@param stockList
	 *@Params:@return
	 *@Return:List<StockPriMa>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:20:16
	 */
	private List<StockPriMa> batchCopy(List<StockPriMaDTO> stockList) {
		List<StockPriMa> list = new ArrayList<StockPriMa>(stockList.size());
		for(StockPriMaDTO goodsAttr : stockList) {
			StockPriMa ga = new StockPriMa();
			BeanUtils.copyProperties(goodsAttr, ga);
			list.add(ga);
		}
		return list;
	}
}
