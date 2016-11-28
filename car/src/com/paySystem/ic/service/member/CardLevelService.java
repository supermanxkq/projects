package com.paySystem.ic.service.member;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.CardLevel;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
import com.paySystem.ic.web.ui.OptionsString;
/**
* 卡等级服务层接口
* 
* 
* @author 井建国
*
*/
public interface CardLevelService {

	//设置static final 类型的依赖注入常量
	public static final String CARDLEVELSERVICE = "cardLevelService";
	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void save(CardLevelDTO memberDTO);
	/**
	 * 保存多实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void saves(List<CardLevel> entitys);
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
	public CardLevel find(String cardLevelids);

	

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
		/**
		* @Title: getOption
		* @Description: 获得根据发卡机构代码获得其所拥有的卡级别列表
		* @param @return   
		* @return List<OptionsString>  
		* @throws
		 */
		public List<OptionsString> getOption(String OrgansId);
		/***
		 * 
		 *@Title:getCardLevelName
		 *@Description:根据卡等级名称，Id查询卡等级信息
		 *@param:@param levelName
		 *@param:@param levelId
		 *@param:@return
		 *@return:boolean
		 *@author:井建国
		 *@thorws:
		 */
		public boolean getCardLevelName(String levelName,String levelId,String method);
		
		public List<OptionsString> getOptionByOrganId(String organId);
		
}
