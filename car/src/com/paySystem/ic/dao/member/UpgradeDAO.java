package com.paySystem.ic.dao.member;

import java.util.List;

import com.paySystem.ic.bean.member.Upgrade;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;

public interface UpgradeDAO extends DAO<Upgrade>  {
	public static final String UPGRADEDAO= "upgradeDAO";
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
		

		/****
		 * 返回List查询结果
		 * @param id
		 * @return
		 */
		public List<Upgrade> query(String id);
		/****
		 * 根据LevelId查询实体
		 */
		
		public Upgrade search(String levelId);
}
