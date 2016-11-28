package com.paySystem.ic.service.stock.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.card.CardsDAO;
import com.paySystem.ic.dao.stock.MerStockDao;
import com.paySystem.ic.dao.stock.ModStoDetDao;
import com.paySystem.ic.dao.stock.StockInfoDao;
import com.paySystem.ic.service.stock.MerStockService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;


/**
 * @ClassName:MerStockServiceImpl
 * @Description: 商户领卡ServiceImpl
 * @date: 2014-5-27上午11:42:19
 * @author: 谢洪飞
 * @version: V1.0
 */
@Service(MerStockService.MERSTOCKSERVICE)
public class MerStockServiceImpl implements MerStockService{

	@Resource MerStockDao merStockDao;
	@Resource CardsDAO cardsDao;
	@Resource ModStoDetDao modStoDetDao;
	@Resource StockInfoDao stockInfoDao;
	
	/**
	 *@Title:
	 *      saveMerStockIn
	 *@Description:
	 *      
	 *      保存商户库存变动信息
	 *                
	 *             1.保存库存变动信息；
	 *             2.更新对应卡表中状态为机构出库；
	 *@param:
	 *      modStockDTO 库存变动信息DTO
	 *@return:
	 *        void
	 *@author:
	 *       谢洪飞
	 * @throws Exception 
	 */
	@Transactional(readOnly=false , propagation = Propagation.REQUIRED)
	public void saveMerStockIn(ModStockDTO modStockDTO) throws Exception{
		
		//1.保存库存变动信息
		ReturnDTO retDto = merStockDao.saveMerStock(modStockDTO);
		//2.更新卡表中对应状态为机构出库
		ModStock modStock = new ModStock();
		modStock.setBeginCardNo(modStockDTO.getBeginCardNo());
		CardBIN cardBin = new CardBIN();
		cardBin.setBinId(modStockDTO.getCardBinNo());
		modStock.setCardBin(cardBin);
		cardsDao.updateCardsStatus(modStock, "3");
		//3.保存库存变动明细信息
		Map<String,Object> retMap = (Map<String, Object>) retDto.getObj();
		List<String> ids = (List<String>)retMap.get("ids");
		modStockDTO.setIds(ids);
		modStoDetDao.saveStockInfo(modStockDTO);
		
	}
	
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
	 *@thorws:
	 */
	public QueryResult<ModStock> queryStockByCond(int firstindex,
			int maxresult, ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderby) throws Exception {
		QueryResult<ModStock> queryResult= merStockDao.queryStockByCond(firstindex, maxresult, modStockDTO, orderby);
		return queryResult;
	}
	
	
	public void auditMerStockIn(){
		
	}
	
	
	/**
	 *@Title:
	 *          sureStockIn
	 *@Description:
	 *          
	 *          商户领卡：确认入库操作Service
	 *          
	 *                 1.更改库存变动表 (S_ModStock) 中信息：
	 *                                status    -->  1:已入库;
	 *                                checkMen  -->： 当前操作员;
	 *                                auditTime -->: 当前业务时间
	 *                 //2.新增库存变动明细信息 (S_ModDetail) 内容：（新增记录）（已在新增库存变动信息添加时，完成!）
	 *                 3.改变库存信息表 (S_StockInfo) 中相关信息:
	 *                              1.入库商户的inTotal增加入库数量；
	 *                              2.出库机构的outTotal增加出库数量；
	 *                 4.更改卡表 (C_Cards) 中状态：
	 *                                status  -->  4:商户入库;
	 *                 
	 *                 
	 *@param:
	 *          @param modStockDTO  库存变动DTO对象
	 *@param:
	 *          @return
	 *@return:
	 *          ReturnDTO  返回DTO对象
	 *@author:
	 *          谢洪飞
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public ReturnDTO sureStockIn(ModStockDTO modStockDTO) throws Exception{
		ReturnDTO dto = new ReturnDTO();
		//1.更改库存变动表
		 merStockDao.sureStockIn(modStockDTO);
		//2.改变库存信息
		stockInfoDao.merStockSureIn(modStockDTO);
		//3.更改卡表中状态
		ModStock modStock = merStockDao.find(modStockDTO.getId());
		cardsDao.updateCardsStatus(modStock, "4");
		
		dto.setObj(modStock);
		
		return dto;
	}

	
	
}
