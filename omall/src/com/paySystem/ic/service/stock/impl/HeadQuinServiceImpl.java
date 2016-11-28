package com.paySystem.ic.service.stock.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.account.AccountsDAO;
import com.paySystem.ic.dao.card.CardBINDAO;
import com.paySystem.ic.dao.card.CardNoDAO;
import com.paySystem.ic.dao.card.CardsDAO;
import com.paySystem.ic.dao.card.CardsDetailDAO;
import com.paySystem.ic.dao.stock.HeadQuinDao;
import com.paySystem.ic.dao.stock.ModStoDetDao;
import com.paySystem.ic.dao.stock.StockInfoDao;
import com.paySystem.ic.service.stock.HeadQuinService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardBINDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:HeadQuinServiceImpl
 * @Description:总部入库Service实现类
 * @date: 2013-12-11下午04:50:14
 * @author: 谢洪飞
 * @version: V1.0
 */
@Service(HeadQuinService.HEADQUINSERV)
public class HeadQuinServiceImpl implements HeadQuinService{
	public static Logger log = Logger.getLogger(HeadQuinService.class);
	
	@Resource HeadQuinDao heDao;
	@Resource CardBINDAO cardBinDao;
	@Resource CardNoDAO cardNoDao;
	@Resource ModStoDetDao modStoDetDao;
	@Resource StockInfoDao stockInfoDao;
	@Resource CardsDAO cardsDao;
	@Resource AccountsDAO accountsDao;
	@Resource CardsDetailDAO acardsDao;

	/**
	 * 根据条件查询库存变动信息
	 */
	public QueryResult<ModStock> queryStockByCond(int firstindex,
			int maxresult, ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderby) throws Exception {
		
		return heDao.queryStockByCond(firstindex, maxresult, modStockDTO, orderby,0);
	}

	public QueryResult<CardBIN> queryCardBinList(int firstindex, int maxresult,
			CardBINDTO cardBINDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		
		QueryResult<CardBIN> query =
			 cardBinDao.queryCardBIN(firstindex, maxresult, cardBINDTO, orderby);
		
		return query;
	}

	public Query queryCardBinList(String organId) {
		Query query = cardBinDao.queryCardBin(organId);
		return query;
	}

	/**
	 *@Title:loadBeginCardNo
	 *@Description:根据卡BIN号加载属于该卡BIN
	 *             并且处于导出状态的最小卡号
	 *@param:@param cardBinNo 卡BIN号
	 *@param:@return
	 *@return:String
	 *@author: 謝
	 *@thorws:
	 */
	public String loadBeginCardNo(String cardBinNo,String organId) {
		
		String loadCardNo = cardNoDao.loadBeginCardNo(cardBinNo,organId);
		
		return loadCardNo;
	}

	/**
	 *@Title:appendCardNo
	 *@Description:根据起始卡号/数量/卡BIN号
	 *             查找要添加入库的卡号信息
	 *@param:@param beginCardNo :起始卡号
	 *@param:@param cardCount   ：数量
	 *@param:@param cardBinNo   ：所属卡BIN号
	 *@param:@return
	 *@return:Query
	 *@author: 謝
	 *@thorws:
	 */
	public Query appendCardNo(String beginCardNo, int cardCount,
			String cardBinNo,int status) {
		Query query = cardNoDao.appendCardNo(beginCardNo, cardCount, cardBinNo,status);
		return query;
	}

	/**
	 *@Title:addModStockInfo
	 *@Description:添加总部入库信息保存方法
	 *             1.保存库存变动信息
	 *             2.更新卡号表中的状态为“待确认”
	 *             3.保存库存明细信息
	 *             
	 *             新增需求
	 *             按照入库方向：
	 *             ①.如果入库方向为 卡厂 到 总部：按照正常方式操作
	 *             ②.如果入库方向为 卡厂 到 发卡机构，则进行如下处理:
	 *                1.保存两条库存变动信息 卡厂到总部，状态为已入库；总部到发卡机构，状态为待确认；
	 *                2.修改总部现有库存信息：入库=数量，出库=数量；
	 *                3.保存两条库存变动明细信息；
	 *                4.新增卡表信息；
	 *                5.新增账户表信息；
	 *                
	 *@param:@param modStockDTO 库存变动DTO对象
	 *@param:@return 
	 *@return:ReturnDTO 返回结果DTO
	 *@author: 谢
	 * @throws Exception 
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO addModStockInfo(ModStockDTO modStockDTO,int flag) throws Exception {
		
	   ReturnDTO dto = new ReturnDTO();
	   ReturnDTO detailDto = new ReturnDTO();
	   //入库方向为: 卡厂 到 总部
	   //1.保存库存变动信息
	   ReturnDTO moddto = new ReturnDTO();
	   moddto = heDao.addModStockInfo(modStockDTO,flag);
	   Map<String,Object> retMap = (Map<String, Object>) moddto.getObj();
	   List<String> ids = (List<String>)retMap.get("ids");
	  //2.保存库存明细信息
	   modStockDTO.setIds(ids);
	   detailDto = modStoDetDao.saveStockInfo(modStockDTO);
	   //3.更新卡号表中的状态为"待确认"状态
	   cardNoDao.updateCardStatus(modStockDTO);
	  
	   if(modStockDTO.getFx()=="1"||"1".equals(modStockDTO.getFx())){
		   /*入库方向为 卡厂到发卡机构*/
		   ModStock modStock = (ModStock)retMap.get("modStock");//总部入库保存的库存变动信息
		   ReturnDTO moddto1 = new ReturnDTO();
		   //1.将 卡厂到总部的库存变动信息入库状态改为"已入库"状态
		   String modId = (String)retMap.get("id");
		   modStockDTO.setId(modId);
		   heDao.modfiyStatusStockIn(modStockDTO);//总部入库环节 确认入库
		   //2.库存信息表中的 总部库存增加数量
		   stockInfoDao.saveStockInfo(modStock);
		   //3.新增库存变动  从总部到发卡机构的变动信息
		   moddto1 = heDao.addModStockInfo(modStockDTO, 2);
		   //4.增加(总部中该卡BIN)出库，领卡机构入库信息
		   Map<String,Object> retMap1 = (Map<String,Object>) moddto1.getObj();
		   ModStock modStock1 = (ModStock) retMap1.get("modStock");
		   stockInfoDao.saveStockInfo(modStock1);
/*		   //5.增加(发卡机构该卡BIN)入库信息
*/		   
		   //4.库存信息（机构领卡信息）
		   
		   /*6.新增库存变动明细信                                         
		    *  1.获取机构ID：根据卡号截取获取BINID，根据BinID获取机构编号
		    */
		   String binID = modStockDTO.getBeginCardNo().substring(0, 6);
		   CardBIN cardBin = cardBinDao.find(binID);
		   String orgId = cardBin.getOrgans().getOrganId();//获取机构编号
		   modStockDTO.setOrganId(orgId);
		   modStoDetDao.saveStockInfo(modStockDTO);
		   
		   //7.新增卡表信息
		   
		   List<CardNo> cardsNoList = (List<CardNo>) detailDto.getObj();
		   
			cardsDao.saveCardsInfo(cardsNoList);
			acardsDao.saveCardsDetail(cardsNoList);
			/* //8.新增账户信息
			accountsDao.saveAccounts(cardsNoList);*/
		   
	   }
	   dto.setFlag(true);
	   return dto;
	}

	/**
	 *@Title:stockDetial
	 *@Description:入库审核/查看详情时，加载要查询的数据
	 *@param:@param modStockDTO
	 *@param:@return
	 *@return:ReturnDTO 返回ReturnDTO对象
	 *@author: 謝
	 *@thorws:
	 */
	public ReturnDTO stockDetial(ModStockDTO modStockDTO) {
		ReturnDTO dto = modStoDetDao.stockDetial(modStockDTO);
		return dto;
	}

	/**
	 *@Title:sureStockIn
	 *@Description:[确认入库]操作触发，将该批卡入库
	 *              1.更改入库变动信息表中的入库状态为：从0:未入库 → 1:已入库
	 *              2.更改卡号表中的入库状态为：从2:待确认 → 3:已入库
	 *              3.插入库存信息表
	 *              4.新增卡表信息
	 *              5.新增账户表信息
	 *@Param:@return
	 *@Return:String
	 *@author: 謝
	 * @throws Exception 
	 * @throws Exception 
	 *@Throws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO sureStockIn(ModStockDTO modStockDTO) throws Exception {
		
		ModStock modStock = heDao.find(modStockDTO.getId());
		ReturnDTO dto = new ReturnDTO();
		UserSession us = Utils.getUserSession();
		try {
			//1.更改库存变动表中的入库状态
		    heDao.modfiyStatusStockIn(modStockDTO);
		    /**根据变动流水号查询对应的卡号-从变动明细表中查询*/
			List<CardNo> cardNoList = modStoDetDao.queryCardNos(modStockDTO.getId());
			//2.批量更改卡号表中的状态
			cardNoDao.modfiyCardStatus(cardNoList);
			//3.插入库存信息--如果已有，则更新。如果没有，则新增
			//不存在，则新增
			stockInfoDao.saveStockInfo(modStock);
			//4.新增卡表信息
			cardsDao.saveCardsInfo(cardNoList);
			acardsDao.saveCardsDetail(cardNoList);

			/*//5.新增账户信息 默认两个账户： 积分账户、现金账户
			accountsDao.saveAccounts(cardNoList);*/

			log.info("[入库成功!]确认入库操作人："+us.getUserName());
			dto.setFlag(true);
			dto.setObj(modStock);
		} catch (Exception e) {
			log.info("[入库失败!]确认入库操作失败！");
			dto.setFlag(false);
			e.printStackTrace();
		}
		
		return dto;
	}


	
}
