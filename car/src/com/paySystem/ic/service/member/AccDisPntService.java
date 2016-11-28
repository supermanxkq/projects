package com.paySystem.ic.service.member;

import java.util.List;

import com.paySystem.ic.bean.member.AccDisPnt;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;

public interface AccDisPntService {
	public static final String ACCDISPNTSERVICE = "accDisPntService";
	/**
	 * 自动生成账户消费折扣积分设置编号
	 * @return
	 */
	public String getAccDisPntId();
	/***
	 * 保存账户折扣积分信息
	 */
	public void save(CardLevelDTO cardLevelDTO);
	/**
	 * 更新账户折扣积分信息
	 * 
	 * @param entity
	 *            实体id
	 */
	public ReturnDTO update(CardLevelDTO cardLevelDTO);
	/***
	 * 获得账户折扣积分实体
	 * @param accDisPntId
	 * @return
	 */
	public AccDisPnt find(String accDisPntId);
	
	
	public List<AccDisPnt>  query(String accDisPntId);
}
