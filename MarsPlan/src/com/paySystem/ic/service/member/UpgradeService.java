package com.paySystem.ic.service.member;

import java.util.List;

import com.paySystem.ic.bean.member.Upgrade;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;

public interface UpgradeService {
	public static final String UPGRASESERVICE = "upgradeService";
	/**
	 * 自动生成升级规则设置编号
	 * @return
	 */
	public String getUpgId();
	/**
	 * 更新升降级规则信息
	 * 
	 * @param entity
	 *            实体id
	 */
	public ReturnDTO update(CardLevelDTO cardLevelDTO);
	/**
	 * 获得升级规则实体
	 * @param upgradeId
	 * @return
	 */
	public Upgrade find(String upgradeId);
	/***
	 * 获取升降级规则信息
	 */
	public void save(CardLevelDTO cardLevelDTO);
	/**
	 * 根据条件查询
	 * @param upgId
	 * @return
	 */
	
	public List<Upgrade>  query(String upgId);
	/****
	 * 根据LevelId查询实体
	 */
	
	public Upgrade search(String levelId);
}
