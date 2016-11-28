package com.paySystem.ic.service.card.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.dao.card.CardNoDAO;
import com.paySystem.ic.service.card.CardNoService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.card.CardNoDTO;

/**
 * @作者 赵巧鹤
 * @类名称 CardNoServiceImpl 卡号Service的实现类
 * @项目名称 mciu
 * @创建时间 2013-12-10 下午02:47:44
 */
@Service(CardNoService.CARDNOSERVICE)
public class CardNoServiceImpl extends DaoSupport<CardNo> implements
		CardNoService {
	@Resource
	CardNoDAO cardNoDAO;


	public List<CardNo> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public CardNo queryCardById(String cardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CardNo> queryCardNoByGenerald() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 导出
	 * */
	public String exportCardConsumeXls(CardNoDTO cardNoDTO, String title,
			HttpServletResponse response) {

		return cardNoDAO.exportCardConsumeXls(cardNoDTO, title, response);
	}

	/**
	 * 查询所有的卡号
	 * */
	public QueryResult<CardNo> queryCardNo(int fristindex, int pageNum,
			CardNoDTO cardNoDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		QueryResult<CardNo> queryResult = cardNoDAO.queryCardNo(fristindex,
				pageNum, cardNoDTO, orderBy);
		return queryResult;
	}

}
