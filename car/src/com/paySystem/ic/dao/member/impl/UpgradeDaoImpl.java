package com.paySystem.ic.dao.member.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.member.Upgrade;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.CardLevelDAO;
import com.paySystem.ic.dao.member.UpgradeDAO;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
@Repository("upgradeDAO")
public class UpgradeDaoImpl  extends DaoSupport<Upgrade> implements UpgradeDAO {
	CardLevelDTO cardLevelDTO=new CardLevelDTO();
	Upgrade upgrade = new Upgrade();
	@Resource CardLevelDAO cardLevelDaoImpl;

	public Upgrade find(String upgradeId) {
		try{
			upgradeId =Utils.getString(cardLevelDTO.getUpgId());
			super.find(upgradeId);
			}catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

	public String getUpgId() {
		String upgId = Utils.createSerialNum(em, "upgId", "Upgrade", 10, 0, null, null, null);
	    return upgId;
	}

	public void save(CardLevelDTO cardLevelDTO) {
		//调用该类里边的getCardLevelId（）方法自动设置LevelId
		upgrade.setUpgId(getUpgId());
		upgrade.setCardLevel(cardLevelDaoImpl.find(cardLevelDTO.getLevelId()));
		upgrade.setUpper(cardLevelDTO.getUpper());
		upgrade.setUpgType(cardLevelDTO.getUpgType());
		super.save(upgrade);
	}

	public ReturnDTO update(CardLevelDTO cardLevelDTO) {
		ReturnDTO dto = new ReturnDTO();
		if(cardLevelDTO!=null){
			upgrade.setUpgId(Utils.getString(cardLevelDTO.getUpgId()));
			upgrade.setCardLevel(cardLevelDaoImpl.find(cardLevelDTO.getLevelId()));
			upgrade.setUpgType(Utils.getString(cardLevelDTO.getUpgType()));
			upgrade.setUpper(cardLevelDTO.getUpper());
			super.update(upgrade);
			dto.setFlag(true);
		}
		return dto;
	}
	/***
	 * Upgrade 和 UpgradeDTO 的实体类的get set 方法
	 */

	public Upgrade getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(Upgrade upgrade) {
		this.upgrade = upgrade;
	}

	public CardLevelDTO getCardLevelDTO() {
		return cardLevelDTO;
	}

	public void setCardLevelDTO(CardLevelDTO cardLevelDTO) {
		this.cardLevelDTO = cardLevelDTO;
	}

	@SuppressWarnings("unchecked")
	public List<Upgrade> query(String levelId) {
		String jql="select o from Upgrade o where o.cardLevel.levelId=?" ;
		Query query= super.em.createQuery(jql);
		query.setParameter(1, levelId);
		List<Upgrade> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public Upgrade search(String levelId) {
		String jpl ="select o from Upgrade o where o.cardLevel.levelId=?";
		Query query = super.em.createQuery(jpl);
		query.setParameter(1, levelId);
		List<Upgrade> list = query.getResultList();
		Upgrade upgrade = new Upgrade();
		for (int i = 0; i < list.size(); i++) {
			upgrade.setCardLevel(list.get(i).getCardLevel());
			upgrade.setUpgId(list.get(i).getUpgId());
			upgrade.setUpgType(list.get(i).getUpgType());
			upgrade.setUpper(list.get(i).getUpper());
		}
		return upgrade;
	}
	

}
