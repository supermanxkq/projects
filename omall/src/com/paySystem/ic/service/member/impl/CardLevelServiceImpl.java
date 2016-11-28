package com.paySystem.ic.service.member.impl;

import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.CardLevel;
import com.paySystem.ic.dao.member.AccDisPntDAO;
import com.paySystem.ic.dao.member.CardLevelDAO;
import com.paySystem.ic.dao.member.UpgradeDAO;
import com.paySystem.ic.service.member.CardLevelService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
import com.paySystem.ic.web.ui.OptionsString;
@Service(CardLevelService.CARDLEVELSERVICE)
public class CardLevelServiceImpl implements CardLevelService {
	@Resource CardLevelDAO cardLevelDaoImpl;
	@Resource AccDisPntDAO accDisPntDAO;
	@Resource UpgradeDAO upgradeDAO;
	/**
	 * 自动获得卡等级编号
	 */
	public String getCardLevelId() {
		return cardLevelDaoImpl.getCardLevelId();
	}
	/**
	 * 保存卡等级信息
	 */

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void save(CardLevelDTO cardLevelDTO) {
		cardLevelDaoImpl.save(cardLevelDTO);
		accDisPntDAO.save(cardLevelDTO);
	//	upgradeDAO.save(cardLevelDTO);
	}
/**
 * 更新卡等级信息
 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO update(CardLevelDTO cardLevelDTO) {
		ReturnDTO dto = cardLevelDaoImpl.update(cardLevelDTO);
		accDisPntDAO.update(cardLevelDTO);
	//	upgradeDAO.update(cardLevelDTO);
		dto.setFlag(true);
		return dto;
	}
	
	/***
	 * 查找卡等级
	 */
	public CardLevel find(String cardLevelids) {
		
		return cardLevelDaoImpl.find(cardLevelids);
	}
	/***
	 * 
	 * @see com.paySystem.ic.service.member.CardLevelService#queryResult(int, int, com.paySystem.ic.web.dto.member.CardLevelDTO, java.util.LinkedHashMap)
	 * @Description:TODO
	 * @date: 2014-3-28下午02:20:41
	 * @author: 井建国
	 * @version: V1.0
	 * @param fristindex
	 * @param pageNum
	 * @param cardlevelDTO
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	public QueryResult<CardLevel> queryResult(int fristindex, int pageNum,
			CardLevelDTO cardlevelDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		return cardLevelDaoImpl.queryResult(fristindex, pageNum, cardlevelDTO, orderBy);
	}
	/***
	 * 
	 * @see com.paySystem.ic.service.member.CardLevelService#saves(java.util.List)
	 * @Description:TODO
	 * @date: 2014-3-28下午02:20:46
	 * @author: 井建国
	 * @version: V1.0
	 * @param entitys
	 */
	public void saves(List<CardLevel> entitys) {
		
	}

	public List<OptionsString> getOption(String OrgansId) {
		List<OptionsString> list = cardLevelDaoImpl.getOption(OrgansId);
		return list;
	}
	/****
	 * 
	 * @see com.paySystem.ic.service.member.CardLevelService#getCardLevelName(java.lang.String, java.lang.String)
	 * @Description:根据卡等级=名称 Id查询卡等级信息
	 * @date: 2014-3-28下午02:19:18
	 * @author: 井建国
	 * @version: V1.0
	 * @param levelName
	 * @param levelId
	 * @return
	 */
	public boolean getCardLevelName(String levelName,String levelId,String method) {
		boolean flag = false;
		if(levelName!=null){
			List<CardLevel> list = cardLevelDaoImpl.getByLevelName(levelName);
			if(method.equals("editSave")&&list.size() != 0){
				for (int i = 0; i < list.size(); i++) {
					CardLevel cardLevel = list.get(i);
					if(cardLevel.getLevelId().equals(levelId)){
						flag = true;
						break;
					}
				}
			}else{
				if(list.size()==0){
					flag = true;
				}
			}
		}
		return flag;
	}
	/***
	 * 
	 * @see com.paySystem.ic.service.member.CardLevelService#getOptionByOrganId(java.lang.String)
	 * @Description:根据机构编号查询卡等级的信息
	 * @date: 2014-3-28下午02:20:10
	 * @author: 井建国
	 * @version: V1.0
	 * @param organId
	 * @return
	 */
	public List<OptionsString> getOptionByOrganId(String organId){
		List<OptionsString> list = cardLevelDaoImpl.getOptionByOrganId(organId);
		return list;
	} 
}
