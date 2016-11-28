package com.paySystem.ic.service.member;

import java.util.List;

import com.paySystem.ic.bean.member.MerDisPnt;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;

public interface MerDisPntService {

	public static final String MERDISPNTSERVICE = "merDisPntService";
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
	
	public List<MerDisPnt>  query(String merDisPntId);
	/****
	 * 根据LevelId查询实体
	 */
	
	public MerDisPnt search(String levelId);
}
