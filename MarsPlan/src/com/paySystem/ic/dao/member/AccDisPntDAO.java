package com.paySystem.ic.dao.member;

import java.util.List;

import com.paySystem.ic.bean.member.AccDisPnt;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;

public interface AccDisPntDAO  extends DAO<AccDisPnt> {
	public static final String ACCDISPNTDAO= "accDisPntDao";
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
	

	/****
	 * 返回List查询结果
	 * @param id
	 * @return
	 */
	public List<AccDisPnt> query(String id);
}
