package com.paySystem.ic.dao.card.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.card.CardBINDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardBINDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @author:赵巧鹤
 * @projectName: MCIU20131123
 * @className :CardBINDAOImpl
 * @description :TODO 卡BIN的DAO的实现类
 * @date: 2014-3-4上午10:52:54
 * @param :
 * 
 */

@Repository(CardBINDAO.CARDBINDAO)
public class CardBINDAOImpl extends DaoSupport<CardBIN> implements CardBINDAO {

	@Resource
	OrgansDao organsDao;
	@Resource
	CardBINDAO cardBINDAO;


	/**
	 * @Title:getBinId
	 * @Descrition:TODO 按流水号获得binId
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public String getBinId() {

		String binId = Utils.createSerialNum(em, "binId", "CardBIN", 6, 0,
				null, "1", null);

		return binId;
	}

	
	/**
	 * @Title:saveCardBIN
	 * @Descrition:TODO 保存
	 * @param: @param cardBINDTO
	 * @param: @return
	 * @return: CardBIN
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public CardBIN saveCardBIN(CardBINDTO cardBINDTO) {
		CardBIN cardB = new CardBIN();
		cardB.setBinId(cardBINDTO.getBinId());
		cardB.setBinName(cardBINDTO.getBinName());
		cardB.setBinSign(cardBINDTO.getBinSign());
		cardB.setBinType(cardBINDTO.getBinType());
		cardB.setCreateTime(this.getSysTime());
		cardB.setDayConsAmtLimt(cardBINDTO.getDayConsAmtLimt());
		cardB.setDayConsTimes(cardBINDTO.getDayConsTimes());
		cardB.setDescr(cardBINDTO.getDescr());
		cardB.setInitAmt(cardBINDTO.getInitAmt());
		cardB.setLimitAmt(cardBINDTO.getLimitAmt());
		cardB.setSingleConsAmt(cardBINDTO.getSingleConsAmt());
		cardB.setSingleRechAmt(cardBINDTO.getSingleRechAmt());
		cardB.setSingleConsWorn(cardBINDTO.getSingleConsWorn());
		cardB.setSingleRechWorn(cardBINDTO.getSingleRechWorn());
		cardB.setStatus(cardBINDTO.getStatus());
		cardB.setUpdateTime(this.getSysTime());
        cardB.setDispNoLen(cardBINDTO.getDispNoLen());
		cardB.setOrgans(organsDao.find(cardBINDTO.getOrganId()));
		save(cardB);
		return cardB;

	}

	/**
	 * @Title:updateCardBIN
	 * @Descrition:TODO 修改
	 * @param: @param cardBINDTO
	 * @param: @return
	 * @return: ReturnDTO
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public ReturnDTO updateCardBIN(CardBINDTO cardBINDTO) {

		ReturnDTO dto = new ReturnDTO();
		CardBIN cardBIN = this.find(cardBINDTO.getBinId());

		if (cardBIN != null) {
			cardBIN.setBinId(cardBINDTO.getBinId());
			cardBIN.setBinName(cardBINDTO.getBinName());
			cardBIN.setBinSign(cardBINDTO.getBinSign());
			cardBIN.setBinType(cardBINDTO.getBinType());
			cardBIN.setCreateTime(this.getSysTime());
			cardBIN.setDayConsAmtLimt(cardBINDTO.getDayConsAmtLimt());
			cardBIN.setDayConsTimes(cardBINDTO.getDayConsTimes());
			cardBIN.setDescr(cardBINDTO.getDescr());
			cardBIN.setInitAmt(cardBINDTO.getInitAmt());
			cardBIN.setLimitAmt(cardBINDTO.getLimitAmt());
			cardBIN.setSingleConsAmt(cardBINDTO.getSingleConsAmt());
			cardBIN.setSingleRechAmt(cardBINDTO.getSingleRechAmt());
			cardBIN.setSingleConsWorn(cardBINDTO.getSingleConsWorn());
			cardBIN.setSingleRechWorn(cardBINDTO.getSingleRechWorn());
			cardBIN.setStatus(cardBINDTO.getStatus());
			cardBIN.setUpdateTime(this.getSysTime());
			cardBIN.setDispNoLen(cardBINDTO.getDispNoLen());
			this.update(cardBIN);

			UserSession us = Utils.getUserSession();

			dto.setFlag(true);
		}

		return dto;

	}

	/**
	 * @Title:queryByBIN
	 * @Descrition:TODO 根据卡BIN进行查询
	 * @param: @param binId
	 * @param: @return
	 * @return: CardBINDTO
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public CardBINDTO queryByBIN(String binId) {
		CardBINDTO cardBINDTO = getCardBINDTO(this.find(binId));
		return cardBINDTO;
	}
	/**
	 * @Title:queryCardBIN
	 * @Descrition:TODO 查询卡BIN信息
	 * @param: @param fristindex
	 * @param: @param pageNum
	 * @param: @param cardBINDTO
	 * @param: @param orderBy
	 * @param: @return
	 * @param: @throws Exception
	 * @return: QueryResult<CardBIN>
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public QueryResult<CardBIN> queryCardBIN(int fristindex, int pageNum,
			CardBINDTO cardBINDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {

		StringBuilder sql = new StringBuilder(); // 封装查询where条件

		List<Object> params = new ArrayList<Object>();
		// 获取UserSession
		UserSession us = Utils.getUserSession();

		switch (us.getUserLevel()) {
		case 0:

			break;
		case 1:
			sql.append(" and o.organs.organId = '" + us.getOrganId() + "'");
			break;
		}

		/** 判断页面条件 */
		if (StringUtils.isNotBlank(cardBINDTO.getOrgName())) {
			sql.append(" and o.organs.name like ?").append(params.size() + 1);
			// 设置参数:机构名称
			params.add("%" + cardBINDTO.getOrgName() + "%");
		}
		if (StringUtils.isNotBlank(cardBINDTO.getBinId())) {
			sql.append(" and o.binId like ?").append(params.size() + 1);
			// 设置参数：卡BIN
			params.add("%" + cardBINDTO.getBinId() + "%");
		}
		if (cardBINDTO.getStatus() != null && cardBINDTO.getStatus() != -1) {
			sql.append(" and o.status = ?").append(params.size() + 1);

			params.add(cardBINDTO.getStatus());
		}
		QueryResult<CardBIN> queryResult = getScrollData(fristindex, pageNum,
				sql.toString(), params.toArray(), orderBy);

		return queryResult;

	}
	
	/**
	 * @Title:deleteCardBIN
	 * @Descrition:TODO 删除卡BIN
	 * @param: @param binId
	 * @return: void
	 * @author: 赵巧鹤
	 * @throws:
	 */
	

	public void deleteCardBIN(String binId) {

		CardBIN cardBIN = this.find(binId);

		cardBIN.setStatus(9);
		this.update(cardBIN);

	}

	/**
	 * 查询CardBIN信息 --谢洪飞添加
	 */
	public Query queryCardBin(String organId) {
		StringBuilder sql = new StringBuilder(
				"select o from CardBIN o where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		UserSession us = Utils.getUserSession();
		String orgId = organId == null ? "" : organId;
		switch (us.getUserLevel()) {
		case 0:
			if (orgId != "") {
				sql.append(" and o.organs.organId=?").append(params.size() + 1);
				params.add(orgId);
			}
			break;
		case 1:
			sql.append(" and o.organs.organId=?").append(params.size() + 1);
			params.add(us.getOrganId());
			break;
		default:
			break;
		}
		sql.append(" and o.status = ?").append(params.size() + 1);
		params.add(1);
		Object[] queryParams = params.toArray();

		Query query = em.createQuery(sql.toString());
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
		return query;
	}

	/*
	 * @Title: getCardBINDTO
	 * 
	 * @Description: 将卡bin实体封装成DTO对象
	 * 
	 * @param @param cardBIN
	 * 
	 * @param @return
	 * 
	 * @return CardBINDTO
	 * 
	 * @throws
	 * 
	 * @date 2013-12-21 下午05:17:44
	 * 
	 * @author lily
	 */
	private CardBINDTO getCardBINDTO(CardBIN cardBIN) {
		CardBINDTO cardBINDTO = new CardBINDTO();
		if (cardBIN.getBinId() != null&&cardBIN.getBinId().equals(-1)) {
			cardBINDTO.setBinId(cardBIN.getBinId());
		}
		if (cardBIN.getBinName() != null) {
			cardBINDTO.setBinName(cardBIN.getBinName());
		}
		if (cardBIN.getBinSign() != null) {
			cardBINDTO.setBinSign(cardBIN.getBinSign());
		}
		if (cardBIN.getBinType() != null) {
			cardBINDTO.setBinType(cardBIN.getBinType());
		}
		if (cardBIN.getInitAmt() != null) {
			cardBINDTO.setInitAmt(cardBIN.getInitAmt());
		}
		if (cardBIN.getLimitAmt() != null) {
			cardBINDTO.setLimitAmt(cardBIN.getLimitAmt());
		}
		if (cardBIN.getSingleConsAmt() != null) {
			cardBINDTO.setSingleConsAmt(cardBIN.getSingleConsAmt());
		}
		if(cardBIN.getSingleConsWorn() !=null){
			cardBINDTO.setSingleConsWorn(cardBIN.getSingleConsWorn());
		}
		if(cardBIN.getSingleRechWorn() !=null){
			cardBINDTO.setSingleRechWorn(cardBIN.getSingleRechWorn());
		}
		if (cardBIN.getSingleRechAmt() != null) {
			cardBINDTO.setSingleRechAmt(cardBIN.getSingleRechAmt());
		}
		if (cardBIN.getDayConsTimes() != null) {
			cardBINDTO.setDayConsTimes(cardBIN.getDayConsTimes());
		}
		if (cardBIN.getDayConsAmtLimt() != null) {
			cardBINDTO.setDayConsAmtLimt(cardBIN.getDayConsAmtLimt());
		}
		if (cardBIN.getDescr() != null) {
			cardBINDTO.setDescr(cardBIN.getDescr());
		}
		if (cardBIN.getStatus() != null) {
			cardBINDTO.setStatus(cardBIN.getStatus());
		}
		if (cardBIN.getUpdateTime() != null) {
			cardBINDTO.setUpdateTime(cardBIN.getUpdateTime());
		}
		if (cardBIN.getCreateTime() != null) {
			cardBINDTO.setCreateTime(cardBIN.getCreateTime());
		}
		if(cardBIN.getDispNoLen() !=null){
			cardBINDTO.setDispNoLen(cardBIN.getDispNoLen());
		}
		if (cardBIN.getOrgans() != null) {
			cardBINDTO.setOrgName(cardBIN.getOrgans().getName());
			cardBINDTO.setOrgans(cardBIN.getOrgans());
			cardBINDTO.setOrganId(cardBIN.getOrgans().getOrganId());
		}
		return cardBINDTO;
	}

	/**
	 *@Title:getOptionByOrganId
	 *@Description:根据机构号查询下属卡BIN
	 *@param:@param organId 机构编号
	 *@param:@return
	 *@return:List<OptionsString>
	 *@author: 谢
	 *@thorws:
	 */
	public List<OptionsString> getOptionByOrganId(String organId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from CardBIN o where o.status = 1");
		//Organs organs = organsDao.find(organId);
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel()==0) {
				sb.append(" and o.organs.organId like '%" + organId + "%'");
			}
		else {
				sb.append(" and o.organs.organId = '" + organId + "'");
		}
		sb.append(" order by updateTime desc");
		List<CardBIN> cardBinsList = em.createQuery(sb.toString())
				.getResultList();
		List<OptionsString> list = new ArrayList<OptionsString>();
		for (CardBIN cardBin : cardBinsList) {
			list
					.add(new OptionsString(cardBin.getBinId(), cardBin
							.getBinName()));
		}
		return list;
	}
	
	

	/**
	 * @Title:validateBinName
	 * @Descrition:TODO  检验数据库中是否已经存在该卡BIN名称
	 * @param: @param binName
	 * @param: @param binId
	 * @param: @return
	 * @return: boolean
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public boolean validateBinName(String binName, String binId) {

		StringBuilder sb = new StringBuilder(
				"from CardBIN o where o.binName ='" + binName
						+ "' and binId<>'" + binId + "'");
		List<CardBIN> binList = em.createQuery(sb.toString()).getResultList();
		return binList.size() > 0;
	}

	/**
	 * @Title:validate
	 * @Descrition:TODO  检验数据库中是否已经存在该卡BIN号
	 * @param: @param binId
	 * @param: @return
	 * @return: boolean
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public boolean validate(String binId) {

		long count = (Long) em.createQuery(
				"select count(o) from CardBIN o where o.binId=?1")
				.setParameter(1, binId).getSingleResult();
		boolean flag = count > 0;
		return flag;

	}


	/* 
	
	* 项目名称：MCIUJYZ
	* 类名称 ：   CardBINDAOImpl.java
	* 描述 ： 获得卡BIN的ID，
	* 判断ID是否存在如果存在，累积加一，不存在就以XX开始
	* 创建人： 赵巧鹤
	* 创建时间： 2014-5-27上午09:09:56
	* 
	*/
	public String getBINID() {
		// TODO Auto-generated method stub
		Integer cardbinId = 0;
		String sql ="select c.binId from c_bin c order by binId desc ";
		List<Object[]> objList = new ArrayList<Object[]>();
		objList =em.createNativeQuery(sql.toString()).getResultList();
		if(objList.size()!=0){
		  Object obj =	objList.get(0);
		  String binId =obj.toString();
		  cardbinId = Integer.parseInt(binId);
		  cardbinId =cardbinId+1;
		  
		}else{
			cardbinId =100000;
		}
		return cardbinId.toString();
	}

}
