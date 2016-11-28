package com.paySystem.ic.dao.member;

import java.util.List;

import com.paySystem.ic.bean.member.MerDisPnt;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;

public interface MerDisPntDAO  extends DAO<MerDisPnt> {
	public static final String MERDISPNTDAO= "merDisPntDAO";
	/**
	 * 自动生成商户消费折扣积分设置编号
	 * @return
	 */
	public String getMerDisPntId();
	/***
	 * 获取商户折扣积分信息
	 */
	public void save(CardLevelDTO cardLevelDTO);
	/**
	 * 更新商户折扣积分信息
	 * 
	 * @param entity
	 *            实体id
	 */
	public ReturnDTO update(CardLevelDTO cardLevelDTO);
	/***
	 * 获取商户实体
	 * @param merDisPntids
	 * @return
	 */
	public MerDisPnt find(String merDisPntids);
	
	
	
	/****
	 * 返回List查询结果
	 * @param id
	 * @return
	 */
	public List<MerDisPnt> query(String id);
	/****
	 * 根据LevelId查询实体
	 */
	
	public MerDisPnt search(String levelId);
}
