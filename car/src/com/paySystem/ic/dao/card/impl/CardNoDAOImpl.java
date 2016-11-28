package com.paySystem.ic.dao.card.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.dao.card.CardNoDAO;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardNoDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @author:赵巧鹤
 * @projectName: MCIU20131123 卡号生成列表的DAO 实现类
 * @className :CardNoDAOImpl
 * @description :TODO
 * @date: 2014-3-4上午10:50:16
 * @param :
 * 
 */
@Repository(CardNoDAO.CARDNODAO)
public class CardNoDAOImpl extends DaoSupport<CardNo> implements CardNoDAO {

	@Resource
	OrgansService organsService;
	@Resource
	CardNoDAO cardNoDAO;

	/**
	 * @Title:getCardNo
	 * @Descrition:TODO 按流水号获得卡号
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String getCardNo() {
		return null;
	}

	/**
	 * @Title:queryByNo
	 * @Descrition:TODO 根据卡号进行查询
	 * @param: @param cardNo
	 * @param: @return
	 * @return: CardNo
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public CardNo queryByNo(String cardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Title:queryCardNo
	 * @Descrition:TODO 查询卡号信息
	 * @param: @param fristindex
	 * @param: @param pageNum
	 * @param: @param cardNoDTO
	 * @param: @param orderBy
	 * @param: @return
	 * @param: @throws Exception
	 * @return: QueryResult<CardNo>
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public QueryResult<CardNo> queryCardNo(int fristindex, int pageNum,
			CardNoDTO cardNoDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		// 获取UserSession
		UserSession us = Utils.getUserSession();

		switch (us.getUserLevel()) {
			case 1:
				sql.append(" and o.organs.organId = '" + us.getOrganId() + "'");
				break;
		}
		/** 判断页面条件 */
		if (StringUtils.isNotBlank(cardNoDTO.getCardNo())) {
			sql.append("and o.cardNo like ?").append(params.size() + 1);
			params.add("%" + cardNoDTO.getCardNo());
		}
		if (StringUtils.isNotBlank(cardNoDTO.getGeneraId())) {
			sql.append(" and o.generaId like ?").append(params.size() + 1);
			params.add("%" + cardNoDTO.getGeneraId());

		}
		if (cardNoDTO.getStatus() != null && cardNoDTO.getStatus() != -1) {
			sql.append(" and o.status = ?").append(params.size() + 1);

			params.add(cardNoDTO.getStatus());
		}
		QueryResult<CardNo> queryResult = getScrollData(fristindex, pageNum,
				sql.toString(), params.toArray(), orderBy);

		return queryResult;

	}

	/**
	 * @Title:exportCardConsumeXls
	 * @Descrition:TODO 导出
	 * @param: @param cardNoDTO
	 * @param: @param title
	 * @param: @param response
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String exportCardConsumeXls(CardNoDTO cardNoDTO, String title,
			HttpServletResponse response) {

		String str = "";
		try {
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("卡号");
			headers.add("批次号");
			headers.add("状态");
			headers.add("生成时间");

			List<List<String>> datas = new ArrayList<List<String>>();
			StringBuffer sql = new StringBuffer();
			UserSession us = Utils.getUserSession();
			sql.append("from CardNo o where 1=1 ");
			switch (us.getUserLevel()) {
			case 1:
				sql.append(" and o.organs.organId = '" + us.getOrganId() + "'");
				break;
			}
			/** 判断过滤条件，如果无过滤条查询全部数据 */
			if (StringUtils.isNotBlank(cardNoDTO.getCardNo())) {
				sql.append(" and o.cardNo like '%"
						+ cardNoDTO.getCardNo().trim() + "%'");
			}
			if (StringUtils.isNotBlank(cardNoDTO.getGeneraId())) {
				sql.append(" and o.generaId like '%"
						+ cardNoDTO.getGeneraId().trim() + "%'");
			}
			if (cardNoDTO.getStatus() != -1) {
				sql.append(" and o.status=" + cardNoDTO.getStatus());
			}
			sql.append(" order by o.createTime desc");
			System.out.println(sql);
			List<CardNo> list = this.findByJpl(sql.toString());

			for (int i = 0; i < list.size(); i++) {
				CardNo cardNo = list.get(i);
				List<String> strings = new ArrayList<String>();
				strings.add("'" + Utils.getString(cardNo.getCardNo()));
				strings.add("'" + Utils.getString(cardNo.getGeneraId()));
				strings
						.add("'"
								+ Utils.getOptionsIntegerName(
										OptionsValue.CARDNO_STATUS, cardNo
												.getStatus()));
				// strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS,
				// cardNo.getStatus()));

				strings.add("'" + Utils.getString(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", cardNo.getCreateTime())));
				datas.add(strings);
			}

			str = util.createXls(headers, datas, title, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	// String sql = new String();
	// 这是另外一种方法
	// QueryResult<CardNo> Qr= getScrollData();
	// List<CardNo> list = Qr.getResultlist();

	// 切记 这里的cardNo “C”要大写！！！
	/*
	 * sql ="select o from CardNo o"; List<CardNo> list =findByJpl(sql);
	 * 
	 * for(int i =0;i<list.size();i++){ CardNo cardno =list.get(i); List<String>
	 * strings = new ArrayList<String>();
	 * strings.add(Utils.getString(cardno.getCardNo()));
	 * strings.add(Utils.getString(cardno.getCardName()));
	 * strings.add(Utils.getString(cardno.getOrgans().getName()));
	 * strings.add(Utils.getString(cardno.getGeneraId()));
	 * strings.add(Utils.getString(cardno.getStatus()));
	 * strings.add(Utils.getString(cardno.getCreateTime()));
	 * 
	 * lists.add(strings); } str =util.createXls(headers,lists, title,
	 * response); }catch(Exception e){ e.printStackTrace(); } return str; }
	 */
	/**
	 *@Title:loadBeginCardNo
	 *@Description:根据卡BIN号加载卡号 1.该卡号属于此卡BIN类型 2.该卡号为该卡BIN类型的最小卡号 3.该卡号处于导出状态
	 *@param:@param cardBinNo 卡BIN号
	 *@param:@return cardNo 卡号
	 *@author 谢洪飞
	 *@return:String
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public String loadBeginCardNo(String cardBinNo, String organId) {
		StringBuilder sql = new StringBuilder(
				"select o from CardNo o where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		String orgId = organId == null ? "" : organId;
		UserSession us = Utils.getUserSession();
		// 限定可查找权限范围
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:// 限定卡号所属机构
			sql.append(" and o.organs.organId=?").append(params.size() + 1);
			params.add(us.getUserLevel());
			break;
		case 2:// 限定卡号所属机构
			sql.append(" and o.organs.organId=?").append(params.size() + 1);
			params.add(us.getUserLevel());
			break;
		default:
			break;
		}
		if (cardBinNo != "") {// 限定卡号所属卡BIN
			sql.append(" and o.cardBIN.binId=?").append(params.size() + 1);
			params.add(cardBinNo);
		}
		if (orgId != "") {
			// 如果为机构领卡，则加载 - 已经处于总部入库状态的卡号
			/*
			 * sql.append(" and o.organs.organId=?").append(params.size()+1);
			 * params.add(orgId);
			 */
			sql.append(" and o.status=?").append(params.size() + 1);
			params.add(3);
		} else {
			// 限制卡号状态为 1：导出 状态 - 总部入库需要加载 状态为导出状态的卡号
			sql.append(" and o.status=?").append(params.size() + 1);
			params.add(1);
		}
		sql.append(" order by o.cardNo asc");

		Object[] queryParams = params.toArray();

		Query query = em.createQuery(sql.toString());
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
		List<CardNo> cardList = query.getResultList();
		String cardNo = "";
		if (cardList.size() > 0) {
			CardNo cardNoObj = cardList.get(0);
			cardNo = cardNoObj.getCardNo();
		}
		return cardNo;
	}

	/**
	 *@Title:appendCardNo
	 *@Description:根据起始卡号/数量/卡BIN号 查询卡号信息
	 *@param:@param beginCardNo ：起始卡号
	 *@param:@param cardCount ：添加数量
	 *@param:@param cardBinNo ：卡BIN号
	 *@param:@return
	 *@return:QueryResult<CardNo>
	 *@author:谢洪飞
	 *@thorws:
	 */
	public Query appendCardNo(String beginCardNo, int cardCount,
			String cardBinNo, int status) {

		StringBuilder sql = new StringBuilder(
				"select o from CardNo o where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		/** 设定查询条件 */
		if (cardBinNo != "") {// 限定卡号所属卡BIN
			sql.append(" and o.cardBIN.binId=?").append(params.size() + 1);
			params.add(cardBinNo);
		}
		if (status == 3) {// 设定查询状态:
			sql.append(" and o.status=?").append(params.size() + 1);
			params.add(3);
		} else {
			// 限制卡号状态为 1：导出 状态
			sql.append(" and o.status=?").append(params.size() + 1);
			params.add(1);
		}

		// 限制查询数据卡号大于起始卡号的信息
		sql.append(" and o.cardNo >=?").append(params.size() + 1);
		params.add(beginCardNo);
		sql.append(" order by o.cardNo asc ");

		Object[] queryParams = params.toArray();

		Query query = em.createQuery(sql.toString());
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
		// 数量--查询多少条
		query.setFirstResult(0).setMaxResults(cardCount);

		return query;
	}

	/**
	 *@Title:updateCardStatus
	 *@Description:更改卡号表中信息的状态： 从“导出-:1”状态改为“待确认-:2”状态 新增需求: 如果入库方向为：卡厂到总部
	 *                           状态改为2 待确认状态 如果入库方向为：卡厂到发卡机构 状态改为3 已入库状态
	 *@param:@param modStockDTO 库存变动信息DTO对象
	 *@param:@return
	 *@return:ReturnDTO 返回DTO对象
	 *@author: 謝
	 *@thorws:
	 */
	public ReturnDTO updateCardStatus(ModStockDTO modStockDTO) throws Exception {
		ReturnDTO dto = new ReturnDTO();
		List<String> cardNoList = modStockDTO.getBeginCardNos();
		List<String> endNoList = modStockDTO.getEndNos();
		int flag = -1;
		if (modStockDTO.getFx() == "0" || "0".equals(modStockDTO.getFx())) {
			flag = 2;
		} else if (modStockDTO.getFx() == "1"
				|| "1".equals(modStockDTO.getFx())) {
			flag = 3;
		}
		for (int i = 0; i < cardNoList.size(); i++) {
			modfiyStatus(cardNoList.get(i), endNoList.get(i), 2);
		}
		dto.setFlag(true);
		return dto;
	}

	/**
	 *@Title:modfiyStatus
	 *@Description:更改卡号表中的状态
	 *@param:@param beginNo 起始卡号
	 *@param:@param endNo 结束卡号
	 *@param:@param status 状态
	 *@return:void
	 *@author:謝
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	private void modfiyStatus(String beginNo, String endNo, int status)
			throws Exception {

		List<Object> params = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder(
				" select o from CardNo o where 1=1");
		if (StringUtils.isNotBlank(beginNo)) {
			jpql.append(" and o.cardNo>=?").append(params.size() + 1);
			params.add(beginNo);
		}
		if (StringUtils.isNotBlank(endNo)) {
			jpql.append(" and o.cardNo<=?").append(params.size() + 1);
			params.add(endNo);
		}
		jpql.append(" and o.status=1 ");

		Object[] queryParams = params.toArray();
		Query query = em.createQuery(jpql.toString());
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
		List<CardNo> cardNoList = query.getResultList();

		for (CardNo cardNo : cardNoList) {
			cardNo.setStatus(status);// 设置卡号状态为 ：= 待确认-2
			try {
				this.update(cardNo);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 *@Title:updateCardStatus
	 *@Description: 更改卡号表中信息的状态： 从"待确认"状态改为“已入库”状态
	 *@param:@param modStockDTO 库存变动信息DTO对象
	 *@param:@return
	 *@return:ReturnDTO 返回DTO对象
	 *@author:谢洪飞
	 *@thorws:
	 */
	public ReturnDTO modfiyCardStatus(List<CardNo> cardNos) throws Exception {
		ReturnDTO dto = new ReturnDTO();

		for (CardNo cardNo : cardNos) {
			cardNo.setStatus(3);
			this.update(cardNo);
		}
		dto.setFlag(true);

		return dto;
	}

}
