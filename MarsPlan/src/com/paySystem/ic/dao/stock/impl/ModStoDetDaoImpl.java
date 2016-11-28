package com.paySystem.ic.dao.stock.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.bean.stock.ModStockDetail;
import com.paySystem.ic.dao.card.CardNoDAO;
import com.paySystem.ic.dao.card.CardsDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.stock.HeadQuinDao;
import com.paySystem.ic.dao.stock.ModStoDetDao;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.stock.ModStockDetailDTO;
import com.paySystem.ic.web.dto.system.UserSession;
/**
 * @ClassName:StockInfoDaoImpl
 * @Description:库存明细Dao实现类
 * @date: 2013-12-13下午05:51:48
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(ModStoDetDao.MODSTODETDAO)
public class ModStoDetDaoImpl extends DaoSupport<ModStockDetail> implements ModStoDetDao {

	@Resource
	CardNoDAO cardNoDao;
	@Resource
	HeadQuinDao headQuinDao;
	@Resource 
	CardsDAO cardsDao;
	/**
	 *@Title:saveStockInfo
	 *@Description:保存库存明细信息
	 *@param:@param modStockDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author: 謝
	 *@date: 2013-12-13
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public ReturnDTO saveStockInfo(ModStockDTO modStockDTO) throws Exception{
		ReturnDTO dto = new ReturnDTO();
		String organId = modStockDTO.getOrganId();//获取机构编号
		List<CardNo> cardsList = new ArrayList<CardNo>();
		List<Cards> cardList = new ArrayList<Cards>();
		List<String> beginNos = modStockDTO.getBeginCardNos();
		List<Integer> cardCounts = modStockDTO.getCardCounts();
		List<String> cardBins = modStockDTO.getCardBinNos();
		List<String> ids = modStockDTO.getIds();
		
			//获取出每条卡号表中信息
			for(int i=0;i<ids.size();i++){
				
				if(StringUtils.isNotBlank(ids.get(i))
						&&StringUtils.isNotBlank(beginNos.get(i))
						&&cardCounts.get(i)!=null){
					
					if(StringUtils.isNotBlank(modStockDTO.getOrganId()))
					{
						//机构领卡保存变动明细
						Query query = cardsDao.appendCardNo(modStockDTO);
						cardList = query.getResultList();
						for (Cards cards : cardList) {
							ModStockDetail modStockDetail = new ModStockDetail();
							modStockDetail.setCardNo(cardNoDao.find(cards.getCardNo()));
							modStockDetail.setModStock(headQuinDao.find(ids.get(i)));
							this.save(modStockDetail);
						}
					}
					else{
					    //总部入库保存变动明细	
					Query query = cardNoDao.appendCardNo(beginNos.get(i),
	                            cardCounts.get(i), cardBins.get(i),0);					 
					//获取本条库存变动信息所涉及到的卡号表信息集合
					cardsList = query.getResultList();
					for(CardNo cardNo : cardsList){
						ModStockDetail modStockDetail = new ModStockDetail();
						modStockDetail.setCardNo(cardNo);
						modStockDetail.setModStock(headQuinDao.find(ids.get(i)));
						try {
							this.save(modStockDetail);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					}
					
				}
			}
			dto.setObj(cardsList);
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
	 */
	public ReturnDTO stockDetial(ModStockDTO modStockDTO) {
		ReturnDTO dto = new ReturnDTO();
		UserSession us = Utils.getUserSession();
		List<String> cardNos = new ArrayList<String>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder(" select o from ModStockDetail o where 1=1 ");
		if(StringUtils.isNotBlank(modStockDTO.getId())){
			jpql.append(" and o.modStock.id=?").append(params.size()+1);
			params.add(modStockDTO.getId());
		}
		if(modStockDTO.getQueryType()==0){
			//单卡查询，返回明细
			if(StringUtils.isNotBlank(modStockDTO.getCardNo())){
				jpql.append(" and o.cardNo.cardNo=?").append(params.size()+1);
				params.add(modStockDTO.getCardNo());
			}
		}
		Object [] queryParams = params.toArray();
		Query query = em.createQuery(jpql.toString());
		setQueryParams(query,queryParams);
		List<ModStockDetail> modStoDetList = query.getResultList();
		if(modStockDTO.getQueryType()==1){
			//查询全部，返回卡集合
			for(ModStockDetail modDetail :modStoDetList){
				cardNos.add(modDetail.getCardNo().getCardNo());
			}
			dto.setObj(cardNos);
		}
		else{
			ModStockDetail modDetail = new ModStockDetail();
			ModStockDetailDTO modStockDetDTO = new ModStockDetailDTO();
			if(modStoDetList.size()>0){
				//单卡查询，返回明细
				modDetail = modStoDetList.get(0);	
			}
			ModStock modStock = modDetail.getModStock();
			modStockDetDTO.setModStockId(modStock.getId());
			modStockDetDTO.setCardNoId(modDetail.getCardNo().getCardNo());
			modStockDetDTO.setStockStatus(modStock.getStatus());
			modStockDetDTO.setStockFlag(modStock.getFlag());
			dto.setObj(modStockDetDTO);
		}
		return dto;
	}
	
	/**
	 * 设置参数
	 * 
	 * @param query
	 * @param queryParams
	 *            参数数组
	 */
	protected static void setQueryParams(Query query, Object[] queryParams) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
	}
	/**
	 *@Title:queryCardNos
	 *@Description:根据库存变动流水号，查找对应的卡号信息
	 *@Param:@param modStockDTONo
	 *@Param:@return
	 *@Return:ReturnDTO
	 *@Throws:
	 */
	public List<CardNo> queryCardNos(String modStockDTONo) {
		List<String> cardNos = new ArrayList<String>();
		List<CardNo> cardNoList = new ArrayList<CardNo>();
		StringBuilder jpql = new StringBuilder(" select o from ModStockDetail o where 1=1 ");
	    if(StringUtils.isNotBlank(modStockDTONo)){
	    	jpql.append(" and o.modStock.id=?1");
	    }
	    Query query = em.createQuery(jpql.toString());
	    if(StringUtils.isNotBlank(modStockDTONo)){
	    	 query.setParameter(1, modStockDTONo);
	    }
	   
	    List<ModStockDetail> modStoDetList = query.getResultList();
	    for(ModStockDetail modStockDetail : modStoDetList){
	    	cardNoList.add(modStockDetail.getCardNo());
	    }
	    
		return cardNoList;
	}
	/*
	 * <p>Title: saveModStockDetailDTO</p> <p>Description:
	 * 保存modStockDetailDTO的实现</p>
	 * @param modStockDetailDTO
	 * @see
	 * com.paySystem.ic.dao.stock.ModStoDetDao#saveModStockDetailDTO(com.paySystem
	 * .ic.web.dto.stock.ModStockDetailDTO)
	 * @author lily
	 * @date 2013-12-24 下午08:12:33
	 */
	public void saveModStockDetailDTO(ModStockDetailDTO modStockDetailDTO) {
		this.save(getModStockDetail(modStockDetailDTO));

	}

	/**
	 * @Title: getModStockDetail
	 * @Description: 将ModStockDetailDTO转换为ModStockDetail的私有方法
	 * @param @param modStockDetailDTO
	 * @param @return
	 * @return ModStockDetail
	 * @throws
	 * @author lily
	 * @date 2013-12-24 下午08:13:10
	 */
	private ModStockDetail getModStockDetail(ModStockDetailDTO modStockDetailDTO) {
		if (modStockDetailDTO == null) {
			return null;
		}
		ModStockDetail modStockDetail = new ModStockDetail();
		if (modStockDetailDTO.getModStock() != null) {
			modStockDetail.setModStock(modStockDetailDTO.getModStock());
		}
		if (modStockDetailDTO.getCardNo() != null) {
			modStockDetail.setCardNo(modStockDetailDTO.getCardNo());
		}
		return modStockDetail;
	}
	
	/**
	 *@Title:covertModStockDTO
	 *@Description:通过ModStock获取ModStockDTO
	 *@param:@param modStock
	 *@param:@return
	 *@return:ModStockDTO
	 *@author: 谢
	 *@thorws:
	 */
	private ModStockDTO covertModStockDTO(ModStock modStock){
		
		ModStockDTO mdto = new ModStockDTO();
		mdto.setId(modStock.getId());
		mdto.setAuditTime(modStock.getAuditTime());
		
		return null;
	}

}
