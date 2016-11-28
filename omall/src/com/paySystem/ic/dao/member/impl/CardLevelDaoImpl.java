package com.paySystem.ic.dao.member.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.CardLevel;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.CardLevelDAO;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

@Repository("cardLevelDAO")
public class CardLevelDaoImpl extends DaoSupport<CardLevel> implements
		CardLevelDAO {

	CardLevel cardLevel = new CardLevel();
	CardLevelDTO cardLevelDTO = new CardLevelDTO();
	@Resource
	MerchantsService merchantsServiceBean;
	@Resource
	OrgansService organsServiceBean;


	public CardLevel find(String[] cardLevelids) {
		try {
			for (int i = 0; i < cardLevelids.length; i++) {
				cardLevelids[i] = Utils.getString(cardLevelDTO.getLevelId());
			}
			super.find(cardLevelids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public synchronized String getCardLevelId() {
		String cardLevelId = Utils.createSerialNum(em, "levelId", "CardLevel",
				10, 0, null, "1", null);
		return cardLevelId;
	}

	public void save(CardLevelDTO cardLevelDTO) {
		// 调用该类里边的getCardLevelId（）方法自动设置LevelId
		cardLevel.setLevelId(Utils.getString(getCardLevelId()));
		cardLevel.setLevelName(Utils.getString(cardLevelDTO.getLevelName()));
		cardLevel.setLevelSign(cardLevelDTO.getLevelSign());
		if(!cardLevelDTO.getMerId().equals("")){
		cardLevel.setMerchants(merchantsServiceBean.find(cardLevelDTO.getMerId()));
		}
		if(!cardLevelDTO.getOrganId().equals("")){
		cardLevel.setOrgans(organsServiceBean.find(cardLevelDTO.getOrganId()));
		}
		cardLevel.setNewPoint(cardLevelDTO.getNewPoint());
		cardLevel.setPerLevelId(Utils.getString(cardLevelDTO.getPerLevelId()));
		cardLevel.setStatus(cardLevelDTO.getStatus());
		cardLevel.setUpdateTime(new Date());
		cardLevel.setDescr(Utils.getString(cardLevelDTO.getDescr()));
		super.save(cardLevel);
	}


	public ReturnDTO update(CardLevelDTO cardLevelDTO) {
		ReturnDTO dto = new ReturnDTO();
		if (cardLevelDTO != null) {
			cardLevel.setLevelId(Utils.getString(cardLevelDTO.getLevelId()));
			cardLevel.setLevelName(Utils.getString(cardLevelDTO.getLevelName()));
			cardLevel.setLevelSign(cardLevelDTO.getLevelSign());
			if (cardLevelDTO.getMerId() != null) {
				cardLevel.setMerchants(merchantsServiceBean.find(cardLevelDTO.getMerId()));
			} else {
				cardLevel.setOrgans(organsServiceBean.find(cardLevelDTO.getOrganId()));
			}
			cardLevel.setNewPoint(cardLevelDTO.getNewPoint());
			cardLevel.setPerLevelId(Utils.getString(cardLevelDTO.getPerLevelId()));
			cardLevel.setStatus(cardLevelDTO.getStatus());
			cardLevel.setUpdateTime(new Date());
			cardLevel.setDescr(Utils.getString(cardLevelDTO.getDescr()));
			super.update(cardLevel);
			dto.setFlag(true);
		}
		return dto;
	}


	public QueryResult<CardLevel> queryResult(int fristindex, int pageNum,
			CardLevelDTO cardLevelDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		StringBuilder sql = new StringBuilder();// 封装查询where条件
		List<Object> params = new ArrayList<Object>();// 参数设置
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 1:
			// 机构级别
			sql.append(" and o.organs.organId = '" + us.getOrganId()+"'");
			break;
		case 2:
			// 商户级别
			sql.append(" and o.merchants.merId = '" + us.getMerId() + "'");
			break;
		}
		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (StringUtils.isNotBlank(cardLevelDTO.getLevelName())) {
			sql.append(" and o.levelName like ?").append(params.size() + 1);
			// 设置卡等级名称
			params.add("%" + cardLevelDTO.getLevelName().trim() + "%");
		}
		if (StringUtils.isNotBlank(cardLevelDTO.getOrganId())) {
			sql.append(" and o.organs.organId like ?").append(params.size() + 1);
			// 设置机构编号
			params.add("%" + cardLevelDTO.getOrganId().trim() + "%");
		}
		if (StringUtils.isNotBlank(cardLevelDTO.getMerId())) {
			sql.append(" and o.merchants.merId like ?").append(params.size() + 1);
			// 设置商户编号
			params.add("%" + cardLevelDTO.getMerId().trim() + "%");
		}
		if(cardLevelDTO.getStatus()!= null&&cardLevelDTO.getStatus()!=-1){
				sql.append(" and o.status = ?").append(params.size() + 1);
				// 设置卡等级状态
				params.add(cardLevelDTO.getStatus());
		}
		
		System.out.println(sql);
		QueryResult<CardLevel> queryResult = getScrollData(fristindex, pageNum,
				sql.toString(), params.toArray(), orderBy);
		return queryResult;
	}

	public CardLevel getCardLevel() {
		return cardLevel;
	}

	public void setCardLevel(CardLevel cardLevel) {
		this.cardLevel = cardLevel;
	}

	public CardLevelDTO getCardLevelDTO() {
		return cardLevelDTO;
	}

	public void setCardLevelDTO(CardLevelDTO cardLevelDTO) {
		this.cardLevelDTO = cardLevelDTO;
	}

	public List<OptionsString> getOption(String organsId) {
		String sql = "select o from CardLevel o where o.status = 1 "
				+ " and o.organs.organId = '" + organsId
				+ "' order by o.levelId asc";
		List<CardLevel> cardLevelList = em.createQuery(sql).getResultList();
		List<OptionsString> list = new ArrayList<OptionsString>();
		for (CardLevel cardLeve : cardLevelList) {
			list.add(new OptionsString(cardLeve.getLevelId(), cardLeve
					.getLevelName()));
		}
		return list;
	}

	/*
	 * <p>Title: findCardLevelDTO</p> <p>Description: 根据卡等级ID获得卡等级DTO</p>
	 * @param levelId
	 * @return CardLevelDTO
	 * @see
	 * com.paySystem.ic.dao.member.CardLevelDAO#findCardLevelDTO(java.lang.String)
	 * @date 2013-12-21 下午06:01:33
	 * @author lily
	 */

	public CardLevelDTO findCardLevelDTO(String levelId) {
		if(levelId==null){
			return null;
		}
		return getCardLevelDTO(this.find(levelId));
	}

	/**
	 * 
	 * @Title: getCardLevelDTO
	 * @Description: 将卡等级实体cardLevel封装成卡等级DTO,
	 * @param cardLevel
	 * @return CardLevelDTO
	 * @throws
	 * @date 2013-12-21 下午06:03:09
	 * @author lily
	 */
	private CardLevelDTO getCardLevelDTO(CardLevel cardLevel) {
		CardLevelDTO cardLevelDTO = new CardLevelDTO();
		if (cardLevel.getLevelId() != null) {
			cardLevelDTO.setLevelId(cardLevel.getLevelId());
		}
		if (cardLevel.getLevelName() != null) {
			cardLevelDTO.setLevelName(cardLevel.getLevelName());
		}
		if (cardLevel.getLevelSign() != null) {
			cardLevelDTO.setLevelSign(cardLevel.getLevelSign());
		}
		if (cardLevel.getPerLevelId() != null) {
			cardLevelDTO.setPerLevelId(cardLevel.getPerLevelId());
		}
		if (cardLevel.getNewPoint() != null) {
			cardLevelDTO.setNewPoint(cardLevel.getNewPoint());
		}
		if (cardLevel.getStatus() != null) {
			cardLevelDTO.setStatus(cardLevel.getStatus());
		}
		if (cardLevel.getOrgans() != null) {
			cardLevelDTO.setOrganId(cardLevel.getOrgans().getOrganId());
			cardLevelDTO.setName(cardLevel.getOrgans().getName());
		}
		if (cardLevel.getMerchants() != null) {
			cardLevelDTO.setMerName(cardLevel.getMerchants().getMerName());
			cardLevelDTO.setMerId(cardLevel.getMerchants().getMerId());
		}
		if (cardLevel.getUpdateTime() != null) {
			cardLevelDTO.setUpdateTime(cardLevel.getUpdateTime());
		}
		if (cardLevel.getDescr() != null) {
			cardLevelDTO.setDescr(cardLevel.getDescr());
		}
		return cardLevelDTO;
	}

	@SuppressWarnings("unchecked")
	public List<CardLevel> getByLevelName(String levelName) {
		String jpl = "select o from CardLevel o where levelName = ?1";
		Query query = em.createQuery(jpl);
		query.setParameter(1, levelName);
		List<CardLevel> list = query.getResultList();
		return list;
	}
	/*****
	 * 
	 *@Title:findCardLevel
	 *@Description:查出卡等级集合
	 *@param:@return
	 *@return:List<CardLevel>
	 *@author:井建国
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<OptionsString> getOptionByOrganId(String organId) {
		String jpl = "select o from CardLevel o where o.organs.organId = ?1";
		Query query = em.createQuery(jpl);
		query.setParameter(1, organId);
		List<CardLevel> list = query.getResultList();
		List<OptionsString> optionValue = new ArrayList<OptionsString>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				CardLevel cardLevel = list.get(i);
				String cardLevelName = cardLevel.getLevelName();
				String levelId = cardLevel.getLevelId();
				OptionsString optionsString = new OptionsString();
				optionsString.setKey(levelId);
				optionsString.setValue(cardLevelName);
				optionValue.add(optionsString);
			}
		}
		return optionValue;
	}
}
