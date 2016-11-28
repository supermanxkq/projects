package com.paySystem.ic.dao.member.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.member.MerDisPnt;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.CardLevelDAO;
import com.paySystem.ic.dao.member.MerDisPntDAO;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
@Repository("merDisPntDAO")
public class MerDisPntDaoImpl extends DaoSupport<MerDisPnt>  implements MerDisPntDAO {
	MerDisPnt merDisPnt = new MerDisPnt();
	CardLevelDTO cardLevelDTO  = new CardLevelDTO();
	@Resource CardLevelDAO cardLevelDaoImpl;
	public MerDisPnt find(String merDisPntids) {
		try{
			merDisPntids =Utils.getString(cardLevelDTO.getMerDisPntId());
			super.find(merDisPntids);
			}catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}


	public String getMerDisPntId() {
		String merDisInt = Utils.createSerialNum(em, "merDisPntId", "MerDisPnt", 10, 0, null, null, null);
	    return merDisInt;
	}


	public void save(CardLevelDTO cardLevelDTO) {
		//调用该类里边的getCardLevelId（）方法自动设置LevelId
		merDisPnt.setAccTypeId(Utils.getString(cardLevelDTO.getAccTypeId()));
		merDisPnt.setCardLevel(cardLevelDaoImpl.find(cardLevelDTO.getLevelId()));
		merDisPnt.setMerDisPntId(getMerDisPntId());
		merDisPnt.setPayDiscount(cardLevelDTO.getPayDiscount());
		merDisPnt.setPpointRate(cardLevelDTO.getPpointRate());
		merDisPnt.setRpointRate(cardLevelDTO.getRpointRate());
		super.save(merDisPnt);
	}


	public ReturnDTO update(CardLevelDTO cardLevelDTO) {
		ReturnDTO dto = new ReturnDTO();
		if(cardLevelDTO!=null){
			merDisPnt.setAccTypeId(Utils.getString(cardLevelDTO.getAccTypeId()));
			merDisPnt.setCardLevel(cardLevelDaoImpl.find(cardLevelDTO.getLevelId()));
			merDisPnt.setMerDisPntId(cardLevelDTO.getMerDisPntId());
			merDisPnt.setPayDiscount(cardLevelDTO.getPayDiscount());
			merDisPnt.setPpointRate(cardLevelDTO.getPpointRate());
			merDisPnt.setRpointRate(cardLevelDTO.getRpointRate());
			super.update(merDisPnt);
			dto.setFlag(true);
		}
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public List<MerDisPnt> query(String levelId) {
		String jql = "select o from MerDisPnt o where o.cardLevel.levelId=?";
		Query query= super.em.createQuery(jql);
		query.setParameter(1, levelId);
		List<MerDisPnt> list = query.getResultList();
		return list;
	}
/**
 * MerDisPnt MerDisPntDTO 实体的get 和 set 方法
 * @return
 */
	public MerDisPnt getMerDisPnt() {
		return merDisPnt;
	}
	public void setMerDisPnt(MerDisPnt merDisPnt) {
		this.merDisPnt = merDisPnt;
	}
	public CardLevelDTO getCardLevelDTO() {
		return cardLevelDTO;
	}
	public void setCardLevelDTO(CardLevelDTO cardLevelDTO) {
		this.cardLevelDTO = cardLevelDTO;
	}

	@SuppressWarnings("unchecked")
	public MerDisPnt search(String levelId) {
		String jpl ="select o from MerDisPnt o where o.cardLevel.levelId=?";
		Query query = super.em.createQuery(jpl);
		query.setParameter(1,levelId);
		List<MerDisPnt> list = query.getResultList();
		MerDisPnt merDisPnt = new MerDisPnt();
		for (int i = 0; i < list.size(); i++) {
			merDisPnt.setAccTypeId(list.get(i).getAccTypeId());
			merDisPnt.setCardLevel(list.get(i).getCardLevel());
			merDisPnt.setMercharts(list.get(i).getMercharts());
			merDisPnt.setMerDisPntId(list.get(i).getMerDisPntId());
			merDisPnt.setPayDiscount(list.get(i).getPayDiscount());
			merDisPnt.setPpointRate(list.get(i).getPpointRate());
			merDisPnt.setRpointRate(list.get(i).getRpointRate());
		}
		return merDisPnt;
	}

}
