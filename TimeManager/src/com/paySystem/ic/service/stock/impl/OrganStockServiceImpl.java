package com.paySystem.ic.service.stock.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.card.CardNoDAO;
import com.paySystem.ic.dao.card.CardsDAO;
import com.paySystem.ic.dao.stock.HeadQuinDao;
import com.paySystem.ic.dao.stock.ModStoDetDao;
import com.paySystem.ic.dao.stock.StockInfoDao;
import com.paySystem.ic.service.stock.OrganStockService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;

/**
 * @ClassName:OrganStockServiceImpl
 * @Description:机构领卡实现类
 * @date: 2013-12-19下午12:00:33
 * @author: 谢洪飞
 * @version: V1.0
 */
@Service(OrganStockService.ORGANSTOCKSERV)
public class OrganStockServiceImpl implements OrganStockService {
	
	@Resource HeadQuinDao heDao;
	@Resource ModStoDetDao modStoDetDao;
	@Resource CardNoDAO cardNoDao;
	@Resource CardsDAO cardsDao;
	@Resource StockInfoDao stockInfoDao;
	/**
	 *@Title:addModStockInfo
	 *@Description:添加机构领卡信息
	 *@param:@param modStockDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:謝
	 * @throws Exception 
	 *@thorws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO addModStockInfo(ModStockDTO modStockDTO) throws Exception {
		 ReturnDTO dto = new ReturnDTO();
		 Map<String,Object> retMap = null;
		 try {
			 //1.保存库存变动信息
			   ReturnDTO moddto = new ReturnDTO();
			   moddto = heDao.addModStockInfo(modStockDTO,2);
			   retMap = (Map<String, Object>) moddto.getObj();
			   List<String> ids = (List<String>) retMap.get("ids");
			  //2.保存库存明细信息
			   modStockDTO.setIds(ids);
			   modStoDetDao.saveStockInfo(modStockDTO);
			   //3.更新卡表中对应状态为：1-总部出库
			   ModStock modStock = (ModStock)retMap.get("modStock");
			   cardsDao.updateCardsStatus(modStock,"1");
			   dto.setFlag(true);
		} catch (Exception e) {
			dto.setFlag(false);
			e.printStackTrace();
		}
		  
		   return dto;
	}
	/**
	 *@Title:queryStockByCond
	 *@Description:根据条件查询机构领卡信息Service方法
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
		QueryResult<ModStock> queryResult= heDao.queryStockByCond(firstindex, maxresult, modStockDTO, orderby,1);
		return queryResult;
	}
	
	/**
	 *@Title:loadBeginCardNo
	 *@Description:根据卡BIN加载机构领卡最小卡号
	 *@param:@param binId 卡BIN号
	 *@param:@param organId 机构号
	 *@param:@return
	 *@return:String
	 *@author:謝
	 *@thorws:
	 */
	public String loadBeginCardNo(String binId, String organId,Integer status) {
		
		return cardsDao.loadBeginCardNo(binId, organId,status);
	}
	/**
	 *@Title:appendCardNo
	 *@Description:根据卡BIN号/加载的起始卡号/数量，查询要出库的卡信息
	 *@param:@param modStockDTO  库存变动信息
	 *@param:@return
	 *@return:Query
	 *@author: 謝
	 *@thorws:
	 */
	public Query appendCardNo(ModStockDTO modStockDTO) {
		
		return cardsDao.appendCardNo(modStockDTO);
	}
	/**
	 *@throws Exception 
	 * @Title:sureStockIn
	 *@Description:确认入库-机构领卡
	 *             1.更改库存变动表中的入库状态为：status = 1 - 已入库
	 *             2.更改卡表中状态为：status = 2-发卡机构入库
	 *             3.库存信息表中新增/更新库存信息 (1)出库方减少 (2)入库方增加
	 *@Param:@param modStockDTO
	 *@Param:@return
	 *@Return:ReturnDTO
	 *@Throws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO sureStockIn(ModStockDTO modStockDTO) {
		ReturnDTO dto = new ReturnDTO();
		try{
			//1.更改库存变动表中的入库状态为：status = 1.已入库
			heDao.modfiyStatusStockIn(modStockDTO);
			/*//根据库存变动业务流水号，从变动明细表中取出此批次变动卡号
			List<CardNo> cardNoList = modStoDetDao.queryCardNos(modStockDTO.getId());*/
			
			//3.更新库存信息表中的信息
			ModStock modStock = heDao.find(modStockDTO.getId());
			stockInfoDao.saveStockInfo(modStock);
			//2.更改卡表中状态为：status = 2-发卡机构入库    、、========传递到updateCardsStatus中的modStockDTO为空。
			cardsDao.updateCardsStatus(modStock,"2");
			dto.setObj(modStock);
			dto.setFlag(true);
			
		}catch(Exception e){
			dto.setFlag(false);
			e.printStackTrace();
		}
		
		
		return dto;
	}

}
