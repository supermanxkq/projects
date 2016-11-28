package com.paySystem.ic.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.member.Upgrade;
import com.paySystem.ic.dao.member.UpgradeDAO;
import com.paySystem.ic.service.member.UpgradeService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
@Service(UpgradeService.UPGRASESERVICE)
public class UpgradeServiceImpl implements UpgradeService {
	@Resource UpgradeDAO upgradeDaoImlp;
	public Upgrade find(String upgId) {
		return upgradeDaoImlp.find(upgId);
	}

	public String getUpgId() {
		return upgradeDaoImlp.getUpgId();
	}

	public void save(CardLevelDTO cardLevelDTO) {
		upgradeDaoImlp.save(cardLevelDTO);
	}

	public ReturnDTO update(CardLevelDTO cardLevelDTO) {
		return upgradeDaoImlp.update(cardLevelDTO);
	}

	public List<Upgrade> query(String upgId) {
		return upgradeDaoImlp.query(upgId);
	}

	public Upgrade search(String levelId) {
		return upgradeDaoImlp.search(levelId);
	}

}
