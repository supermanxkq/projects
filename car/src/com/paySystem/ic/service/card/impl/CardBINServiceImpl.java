package com.paySystem.ic.service.card.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.card.CardBINDAO;
import com.paySystem.ic.service.card.CardBINService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardBINDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @作者 赵巧鹤
 * @类名称 CardBINServiceImpl 卡BINServive的实现类
 * @项目名称 mciu
 * @创建时间 2013-12-10 下午02:47:00
 */
@Service(CardBINService.CARDBINSERVICE)
public class CardBINServiceImpl extends DaoSupport<CardBIN> implements
		CardBINService {
	@Resource
	OrgansDao organsDao;
	@Resource
	CardBINDAO cardBINDAO;


	/**
	 * 根据卡BIN号进行查询
	 * 
	 * */
	public CardBIN queryById(String binId) {
		return cardBINDAO.find(binId);

	}

	/**
	 * 删除卡BIN
	 * 
	 * */
	public void deleteCardBIN(String binId) {
		cardBINDAO.deleteCardBIN(binId);

	}

	/**
	 * 保存卡BIN信息
	 * */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public CardBIN saveCardBIN(CardBINDTO cardBINDTO) {

		Organs organs = organsDao.find(cardBINDTO.getOrganId());
		cardBINDTO.setOrgans(organs);
		CardBIN cardB = new CardBIN();
		cardB = cardBINDAO.saveCardBIN(cardBINDTO);

		return cardB;
	}

	/**
	 * 修改卡BIn的信息
	 * */
	public ReturnDTO updateCardBIN(CardBINDTO cardBINDTO) {
		ReturnDTO dto = new ReturnDTO();
		dto = cardBINDAO.updateCardBIN(cardBINDTO);

		return dto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.paySystem.ic.service.base.TerminalsService#getTerminalsId()
	 * 卡号（按流水）
	 */
	public String getCardBINId() {
		String binId = cardBINDAO.getBinId();
		return binId;
	}

	/**
	 * 查询所有的卡BIN信息
	 * */
	public List<OptionsString> queryCardBIN() {

		return null;
	}

	/**
	 * 根据页面条件查询卡号信息
	 * 
	 * @throws Exception
	 */
	public QueryResult<CardBIN> queryTermByCond(int fristindex, int pageNum,
			CardBINDTO cardBINDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {

		QueryResult<CardBIN> queryResult = cardBINDAO.queryCardBIN(fristindex,
				pageNum, cardBINDTO, orderBy);
		return queryResult;
	}

	/**
	 *@Title:getOptionByOrganId
	 *@Description:根据机构号查询下属卡BIN
	 *@param:@param organId 机构编号
	 *@param:@return
	 *@return:List<OptionsString>
	 *@author: 谢
	 *@thorws:
	 */
	public List<OptionsString> getOptionByOrganId(String organId) {
		List<OptionsString> list = new ArrayList<OptionsString>();
		list = cardBINDAO.getOptionByOrganId(organId);
		return list;
	}

	/**
	 * @param binName
	 * @param binId
	 *            检验数据库中是否已经存在该卡BIN名称
	 * @return
	 */
	public boolean validateBinName(String binName, String binId) {

		return cardBINDAO.validateBinName(binName, binId);
	}

	/**
	 * @param binName
	 * @param binId
	 *            检验数据库中是否已经存在该卡BIN号
	 * @return
	 */
	public boolean validate(String binId) {

		boolean flag = cardBINDAO.validate(binId);
		return flag;
	}

	/* 
	
	* 项目名称：MCIUJYZ
	* 类名称 ：   CardBINServiceImpl.java
	* 描述 ：      获得binId,移植数据库专门写的方法
	* 创建人： 赵巧鹤
	* 创建时间： 2014-5-27上午09:38:27
	* 
	*/
	public String getBinID() {
		// TODO Auto-generated method stub
		return cardBINDAO.getBINID();
	}

}
