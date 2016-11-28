package com.paySystem.ic.service.card;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.card.CardNoDTO;

/**
 * @author ：赵巧鹤
 * @ClassName ：CardNoService 卡号Service
 * @project_name：mciu
 * @createTime：2013-12-9 下午05:38:58
 * 
 * 
 */
public interface CardNoService extends DAO<CardNo> {

	public static final String CARDNOSERVICE = "cardNoService";

	/**
	 * 查询所有的卡号
	 * */
	public List<CardNo> queryAll();

	/**
	 * 根据卡号ID进行查询
	 * */
	public CardNo queryCardById(String cardNo);

	/**
	 * 根据批次号进行查询
	 * */
	public List<CardNo> queryCardNoByGenerald();

	/**
	 * 导出
	 * */

	public String exportCardConsumeXls(CardNoDTO cardNoDTO, String title,
			HttpServletResponse response);

	/**
	 * 查询所有的卡号信息
	 * */
	public QueryResult<CardNo> queryCardNo(int fristindex, int pageNum,
			CardNoDTO cardNoDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;
}
