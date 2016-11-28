package com.paySystem.ic.dao.member.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.member.AccDisPnt;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.AccDisPntDAO;
import com.paySystem.ic.dao.member.CardLevelDAO;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
@Repository(AccDisPntDAO.ACCDISPNTDAO)
public class AccDisPntDaoImpl  extends DaoSupport<AccDisPnt>  implements AccDisPntDAO {
	AccDisPnt accDisPnt = new AccDisPnt();
	CardLevelDTO cardLevelDTO = new CardLevelDTO();
	@Resource CardLevelDAO cardLevelDaoImpl;
	@SuppressWarnings("unchecked")
	public String getAccDisPntId() {
		String cardLevelId = Utils.createSerialNum(em, "accDisPntId", "AccDisPnt",10, 0, null, "1", null);
		return cardLevelId;
	}

	public void save(CardLevelDTO cardLevelDTO) {
		//调用该类里边的getCardLevelId（）方法自动设置LevelId
		if(cardLevelDTO.getCaccTypeId()==1){
			AccDisPnt accDisPnt =new AccDisPnt();
			accDisPnt.setAccDisPntId(getAccDisPntId());
			accDisPnt.setCaccTypeId(cardLevelDTO.getCaccTypeId());
			accDisPnt.setCardLevel(cardLevelDaoImpl.find(cardLevelDTO.getLevelId()));
			accDisPnt.setCpayDiscount(cardLevelDTO.getCpayDiscount());
			accDisPnt.setCppointRate(cardLevelDTO.getCppointRate());
			accDisPnt.setCrpointRate(cardLevelDTO.getCrpointRate());
			super.save(accDisPnt);
		}
		if(cardLevelDTO.getSaccTypeId()==2){
			AccDisPnt accDisPnt =new AccDisPnt();
			accDisPnt.setAccDisPntId(getAccDisPntId());
			accDisPnt.setCaccTypeId(cardLevelDTO.getSaccTypeId());
			accDisPnt.setCardLevel(cardLevelDaoImpl.find(cardLevelDTO.getLevelId()));
			accDisPnt.setCpayDiscount(cardLevelDTO.getSpayDiscount());
			accDisPnt.setCppointRate(cardLevelDTO.getSppointRate());
			accDisPnt.setCrpointRate(cardLevelDTO.getSrpointRate());
			super.save(accDisPnt);
		}
	}
	
	public ReturnDTO update(CardLevelDTO cardLevelDTO) {
		ReturnDTO dto = new ReturnDTO();
		if(cardLevelDTO!=null){
				List<AccDisPnt> accDisPntList =  query(cardLevelDTO.getLevelId());
				for (int i = 0; i < accDisPntList.size(); i++) {
					AccDisPnt accDisPnt = accDisPntList.get(i);
					if(accDisPnt.getCaccTypeId()==1){
						accDisPnt.setCardLevel(cardLevelDaoImpl.find(cardLevelDTO.getLevelId()));
						accDisPnt.setCaccTypeId(cardLevelDTO.getCaccTypeId());
						accDisPnt.setCpayDiscount(cardLevelDTO.getCpayDiscount());
						accDisPnt.setCppointRate(cardLevelDTO.getCppointRate());
						accDisPnt.setCrpointRate(cardLevelDTO.getCrpointRate());
						super.update(accDisPnt);
					}
					if(accDisPnt.getCaccTypeId()==2){
						accDisPnt.setCardLevel(cardLevelDaoImpl.find(cardLevelDTO.getLevelId()));
						accDisPnt.setCaccTypeId(cardLevelDTO.getSaccTypeId());
						accDisPnt.setCpayDiscount(cardLevelDTO.getSpayDiscount());
						accDisPnt.setCppointRate(cardLevelDTO.getSppointRate());
						accDisPnt.setCrpointRate(cardLevelDTO.getSrpointRate());
						super.update(accDisPnt);
					}
				}
				dto.setFlag(true);
		}
		return dto;
	}
/**
 * AccDisPnt AccDisPntDTO 实体累的get set 方法
 * @return
 */
	public AccDisPnt getAccDisPnt() {
		return accDisPnt;
	}

	public void setAccDisPnt(AccDisPnt accDisPnt) {
		this.accDisPnt = accDisPnt;
	}

	public CardLevelDTO getCardLevelDTO() {
		return cardLevelDTO;
	}

	public void setCardLevelDTO(CardLevelDTO cardLevelDTO) {
		this.cardLevelDTO = cardLevelDTO;
	}

	@SuppressWarnings("unchecked")
	public List<AccDisPnt> query(String levelId) {
		String jpl ="select o from AccDisPnt o where o.cardLevel.levelId=?";
		Query query= super.em.createQuery(jpl);
		query.setParameter(1, levelId);
		List<AccDisPnt> list = query.getResultList();
		return list;
	}
}
