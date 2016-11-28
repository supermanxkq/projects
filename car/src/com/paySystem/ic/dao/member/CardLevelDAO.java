package com.paySystem.ic.dao.member;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.CardLevel;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
import com.paySystem.ic.web.ui.OptionsString;

public interface CardLevelDAO  extends DAO<CardLevel>{
	public static final String CARDLEVELDAO= "cardLevelDAO";
	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void save(CardLevelDTO memberDTO);
	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public ReturnDTO update(CardLevelDTO cardLevelDTO);
	/**
	 * 获取实体
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 * @return
	 */
	public CardLevel find(String [] cardLevelids);
	/**
	 * 自动生成会员编号
	 * @return
	 */
	public String getCardLevelId();	
	/***
	 * 
	 * 条件查询
	 * 
	 * */
	public QueryResult<CardLevel> queryResult(int fristindex,int pageNum, CardLevelDTO cardlevelDTO,LinkedHashMap<String, String> orderBy) throws Exception;
	public List<OptionsString> getOption(String organsId);
	/**
	 * 
	* @Title: findCardLevelDTO
	* @Description: 根据卡等级ID获取卡等级DTO
	* @param @param saleLevelId
	* @param @return   
	* @return CardLevelDTO 
	* @throws
	* @date 2013-12-21 下午06:00:28
	* @author lily
	 */
	public CardLevelDTO findCardLevelDTO(String levelId); 
	
	/****
	 * 
	 *@Title:getByLevelName
	 *@Description:验证卡等级名称不能为空
	 *@param:@param levelName
	 *@param:@return
	 *@return:List<CardLevel>
	 *@author:井建国
	 *@thorws:
	 */
	public List<CardLevel> getByLevelName(String levelName);
	/*****
	 * 
	 *@Title:findCardLevel
	 *@Description:查出卡等级集合
	 *@param:@return
	 *@return:List<CardLevel>
	 *@author:井建国
	 *@thorws:
	 */
	public List<OptionsString> getOptionByOrganId(String organId);
}
