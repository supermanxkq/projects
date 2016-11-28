package com.paySystem.ic.dao.card.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.Accounts;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.bean.member.CardLevel;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.account.AccountsDAO;
import com.paySystem.ic.dao.card.CardsDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.account.AccountsDTO;
import com.paySystem.ic.web.dto.card.CardsDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Repository("CardsDaoImpl")
public class CardsDAOImpl extends DaoSupport<Cards> implements CardsDAO {
	@Resource
	AccountsDAO accountsDAO;

	/**
	 * 卡号之前进行判断 避免卡号为空时，报空指针异常 赵巧鹤
	 * */
	@SuppressWarnings("null")
	public CardsDTO findCards(String cardsNo) {
		UserSession us = Utils.getUserSession();

		CardsDTO cardsDTO = queryByCardNo(cardsNo);
		if (cardsDTO.getOrganId()== null || !cardsDTO.getOrganId().equals(us.getOrganId())) {
			return null;
		}
		return cardsDTO;
	}

	public Cards getCards(CardsDTO cardsDTO) {
		if (cardsDTO == null) {
			return null;
		}
		Cards cards = new Cards();
		if (cardsDTO.getCardNo() != null) {
			cards.setCardNo(cardsDTO.getCardNo());
		}
		if (cardsDTO.getLevelId() != null) {
			cards.setCardLevel(new CardLevel(cardsDTO.getLevelId()));
		}
		if (cardsDTO.getStatus() != null) {
			cards.setStatus(cardsDTO.getStatus());
		}
		// cards.setDeptType(cards.getDeptType());
		// cards.setOrganId(cards.getOrgId());
		if (cardsDTO.getOrganId() != null) {
			cards.setOrgans(new Organs(cardsDTO.getOrganId()));
		}
		if (cardsDTO.getMerId() != null) {
			cards.setMerchants(new Merchants(cardsDTO.getMerId()));
		}
		if (cardsDTO.getHoldmemId() != null) {
			/*cards.setMember(new Member(cardsDTO.getHoldmemId()));*/
		}
		if (cardsDTO.getValidTime() != null) {
			cards.setValidTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
					cardsDTO.getValidTime()));
		}
		if (cardsDTO.getMemsign() != null) {
			cards.setMemSign(cardsDTO.getMemsign());
		}
		if (cardsDTO.getBcSign() != null) {
			cards.setBcSign(cardsDTO.getBcSign());
		}
		if (cardsDTO.getPwdsign() != null) {
			cards.setPwdSign(cardsDTO.getPwdsign());
		}
		if (cardsDTO.getPwdErrorNum() != null) {
			cards.setPwdErrorNum(cardsDTO.getPwdErrorNum());
		}
		if (cardsDTO.getPinBlock() != null) {
			cards.setPinBlock(cardsDTO.getPinBlock());
		}
		if (cardsDTO.getTrack2() != null) {
			cards.setTrack2(cardsDTO.getTrack2());
		}
		if(cardsDTO.getCardViewNo() != null){
			cards.setCardViewNo(cardsDTO.getCardViewNo());
		}
		if (cardsDTO.getCreateTime() != null) {
			cards.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
					cardsDTO.getCreateTime()));
		}

		if(cardsDTO.getCompanyId()!=null){
			cards.setCompanyId(cardsDTO.getCompanyId());
		}
		if(cardsDTO.getCardSingn()!=null){
			cards.setCardSingn(cardsDTO.getCardSingn());
		}

		if(cards.getPwd() !=null){
			cards.setPwd(cardsDTO.getPwd());
		}

		return cards;
	}

	
	public CardsDTO getCardsDTO(Cards cards) {
		if (cards == null) {
			return null;
		}
		CardsDTO cardsDto = new CardsDTO();
		AccountsDTO accountsDto = new AccountsDTO();
		if (cards.getCardNo() != null) {
			cardsDto.setCardNo(cards.getCardNo());
		}
		if (cards.getCardLevel() != null) {
			cardsDto.setLevelId(cards.getCardLevel().getLevelId());
			cardsDto.setLevelName(cards.getCardLevel().getLevelName());
		}
		if (cards.getStatus() != null) {
			cardsDto.setStatus(cards.getStatus());
		}

		// cardsDto.setDeptType(cards.getDeptType());
		// cardsDto.setOrganId(cards.getOrgId());
		if (cards.getOrgans() != null) {
			cardsDto.setOrganId(cards.getOrgans().getOrganId());
			cardsDto.setOrganName(cards.getOrgans().getName());
		}
		if (cards.getMerchants() != null) {
			cardsDto.setMerId(cards.getMerchants().getMerId());
			cardsDto.setMerName(cards.getMerchants().getMerName());
		}
		if (cards.getMember() != null) {
			/*cardsDto.setHoldmemId(cards.getMember().getMemId());
			cardsDto.setHoldName(cards.getMember().getMemRealName());*/
			cardsDto.setTelNo(cards.getMember().getTeleNo());
		}
		if (cards.getValidTime() != null) {
			cardsDto.setValidTime(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm:ss", cards.getValidTime()));
		}
		if (cards.getMemSign() != null) {
			cardsDto.setMemsign(cards.getMemSign());
		}
		if (cards.getBcSign() != null) {
			cardsDto.setBcSign(cards.getBcSign());
		}
		if (cards.getPwdSign() != null) {
			cardsDto.setPwdsign(cards.getPwdSign());
		}
		if (cards.getPwdErrorNum() != null) {
			cardsDto.setPwdErrorNum(cards.getPwdErrorNum());
		}
		if (cards.getPinBlock() != null) {
			cardsDto.setPinBlock(cards.getPinBlock());
		}
		if (cards.getTrack2() != null) {
			cardsDto.setTrack2(cards.getTrack2());
		}
		if (cards.getCreateTime() != null) {
			cardsDto.setCreateTime(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm:ss", cards.getCreateTime()));
		}
		if (cards.getMember() != null) {
			/*cardsDto.setMerName(cards.getMember().getMemRealName());*/
			cardsDto.setMemIdNum(cards.getMember().getPersonId());
		}
		if (cards.getCardNo().substring(0, 6) != null) {
			cardsDto.setBinId(cards.getCardNo().substring(0, 6));
		}
		/* 修改添加* */
		if (cards.getOrgans().getOrganId() != null) {
			cardsDto.setOrganId(cards.getOrgans().getOrganId());
		}
		if(cards.getCardViewNo() !=null){
			cardsDto.setCardViewNo(cards.getCardViewNo());
		}
		if(cards.getCardSingn() !=null){
			cardsDto.setCardSingn(cards.getCardSingn());
			cardsDto.setCardSingnName(Utils.getOptionsIntegerName(OptionsValue.GETCASH_SIGN, cards.getCardSingn()));
			
		}
		if(cards.getPwd() !=null){
			cardsDto.setPwd(cards.getPwd());
		}
		cardsDto.setAccId("");
		List<Accounts> accountsList = accountsDAO
				.findByJpl("from Accounts o where o.cardNo = '"
						+ cards.getCardNo().trim() + "'");
		for (int i = 0; i < accountsList.size(); i++) {
			Accounts accounts = accountsList.get(i);

			if (accounts != null) {
				/*cardsDto.setAccId(cardsDto.getAccId() + accounts.getAccId()
						+ ";");

				BigDecimal balPoint = NumberUtil.add(NumberUtil.sub(accounts
						.getInAmt(), accounts.getOutAmt()), NumberUtil.sub(
						accounts.getUpAmt(), accounts.getDownAmt()));

				accountsDto.setBalPoint(balPoint);// 余额/积分=(入账总数-出账总数)+(上调-下调)
				BigDecimal totalBalPoint = NumberUtil.add(balPoint, accounts
						.getMainBal());
				cardsDto.setBalance(totalBalPoint);*/
			}
		}

		return cardsDto;
	}

	/**
	 *@Title:saveCardsInfo
	 *@Description:新增卡表信息.总部确认入库时调用
	 *@param:@param cardNos 卡号对象集合
	 *@param:@return
	 *@param:@throws Exception
	 *@return:ReturnDTO
	 *@author:谢
	 *@thorws:
	 */
	
	public ReturnDTO saveCardsInfo(List<CardNo> cardNos) throws Exception {
		ReturnDTO dto = new ReturnDTO();
		for (CardNo cardNo : cardNos) {

			Cards cards = new Cards();
            //卡号
			cards.setCardNo(cardNo.getCardNo());
			// 状态为 0：总部入库
			cards.setStatus(0);
			//会员绑定状态 0：未绑定，1：已绑定
			cards.setMemSign(0);
			//密码启用状态 0：未启用，1：已启用
			cards.setPwdSign(1);
			//  密码错误次数: 初始为 0
			cards.setPwdErrorNum(0);
			//显示卡号
			cards.setCardViewNo(cardNo.getCardViewNo());
			cards.setPinBlock(cardNo.getPinblock());			
			cards.setOrgans(cardNo.getOrgans());
			cards.setTrack2(cardNo.getTrack());
			//创建时间
			cards.setCreateTime(getSysTime());

			this.save(cards);
		}
		dto.setFlag(true);
		return dto;
	}

	public QueryResult<Cards> queryResult(int fristindex, int pageNum,
			CardsDTO cardsDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		StringBuilder sql = new StringBuilder();// 封装查询where条件
		List<Object> params = new ArrayList<Object>();// 参数设置
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 1:
			// 机构级别
			sql.append(" and o.organs.organId = ?").append(params.size() + 1);
			params.add(us.getOrganId());
			break;
		case 2:
			// 商户级别
			sql.append(" and o.merchants.merId = ?").append(params.size() + 1);
			params.add(us.getMerId());
			break;
		}
		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (StringUtils.isNotBlank(cardsDTO.getCardNo())) {
			sql.append(" and o.cardNo like ?").append(params.size() + 1);
			// 设置会员卡号
			params.add("%" + cardsDTO.getCardNo().trim() + "%");
		}
		if (StringUtils.isNotBlank(cardsDTO.getHoldName())) {
			sql.append(" and o.member.memRealName like ?").append(
					params.size() + 1);
			// 设置会员名称
			params.add("%" + cardsDTO.getHoldName().trim() + "%");
		}
		if (StringUtils.isNotBlank(cardsDTO.getLevelName())) {
			sql.append(" and o.cardLevel.levelName like ?").append(
					params.size() + 1);
			params.add("%" + cardsDTO.getLevelName().trim() + "%");
		}
		sql.append(" and o.memSign = ?").append(params.size() + 1);
		params.add(1);
		QueryResult<Cards> queryResult = getScrollData(fristindex, pageNum, sql
				.toString(), params.toArray(), orderBy);
		return queryResult;

	}

	@SuppressWarnings("unchecked")
	public CardsDTO checkCardNo(String cardNo) {
		String jpl = "select o from Cards o where o.cardNo=?1";
		Query query = em.createQuery(jpl);
		query.setParameter(1, cardNo);
		CardsDTO cardsDTO = null;
		List<Cards> list = query.getResultList();
		if (list.size() < 1) {
			Cards cards = list.get(0);
			cardsDTO = getCardsDTO(cards);
		}
		return cardsDTO;
	}


	public void saveCards(CardsDTO cardsDTO) {
		this.save(getCards(cardsDTO));
	}

	
	public void updateCards(CardsDTO cardsDTO) {
		this.update(getCards(cardsDTO));
	}

	/**
	 *@Title:appendCardNo
	 *@Description:根据卡BIN号/加载的起始卡号/数量，查询要出库的卡信息
	 *@param:@param modStockDTO 库存变动信息
	 *@param:@return
	 *@return:Query
	 *@author: 謝
	 *@thorws:
	 */
	public Query appendCardNo(ModStockDTO modStockDTO) {
		StringBuilder jpql = new StringBuilder(
				" select o from Cards o where 1=1 ");

		// 查询大于起始号的卡信息
		if (StringUtils.isNotBlank(modStockDTO.getBeginCardNo())) {
			jpql.append(" and o.cardNo >= " + modStockDTO.getBeginCardNo());
		}
		// 查询该卡BIN类型的卡信息
		if (StringUtils.isNotBlank(modStockDTO.getCardBinNo())) {
			jpql.append(" and o.cardNo like '" + modStockDTO.getCardBinNo()
					+ "%'");
		}

		Query query = em.createQuery(jpql.toString());
		// 指定查询卡信息数量
		query.setMaxResults(modStockDTO.getCardCount());
		return query;
	}

	/**
	 *@Title:loadBeginCardNo
	 *@Description:根据卡BIN号加载卡号 
	 *                          1.该卡号属于此卡BIN类型; 
	 *                          2.该卡号为该卡BIN类型的最小卡号;
	 *                          3.该卡号处于状态为：0入库（总部入库完成）;
	 *@param:@param cardBinNo 卡BIN号
	 *@param:@return cardNo 卡号
	 *@author 谢洪飞
	 *@return:String
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public String loadBeginCardNo(String cardBinNo, String organId,Integer status) {
		StringBuilder sql = new StringBuilder(
				"select o from Cards o where 1=1 ");
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
		if (StringUtils.isNotBlank(cardBinNo)) {
			sql.append(" and o.cardNo like ?").append(params.size() + 1);
			params.add(cardBinNo + "%");
		}
		
		sql.append(" and o.status = "+status);
		sql.append(" and o.memSign = "+0 );
		Object[] queryParams = params.toArray();
		Query query = em.createQuery(sql.toString());
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
		List<Cards> cardList = query.getResultList();
		String cardNo = "";
		if (cardList.size() > 0) {
			Cards cards = cardList.get(0);
			cardNo = cards.getCardNo();
		}
		return cardNo;
	}

	/**
	 *@Title:updateCardsStatus
	 *@Description:
	 *           批量更改卡状态:
	 *            从0-总部入库到1-总部出库
	 *            从1：总部出库 到 2：发卡机构入库
	 *            从2：发卡机构入库 到 3：发卡机构出库
	 *@Param:@param modStockDTO
	 *@param:@Param falg 更改状态标志
	 *@Return:void
	 *@author 谢
	 *@Throws:
	 */
	public void updateCardsStatus(ModStock modStock, String flag) {
		//Integer i = Integer.parseInt(flag);
		
		Integer i = 0;
		Integer status = 0;
		switch (Integer.parseInt(flag)) {
		case 1://机构领卡信息添加：更改卡状态为 1，总部出库
			i = 0;
			status = 1;
			break;
		case 2://机构领卡确认入库：查找状态为1（总部出库）的，改为2（机构入库）
			i = 1;
			status = 2;
		case 3://添加商户领卡信息：查找状态为1(总部出库)的，改为3(机构出库)
			i = 1;
			status = 3;
			break;
		case 4: //商户领卡确认：查找状态为3(机构出库)的，改为4（商户入库）
			i = 3;
			status = 4;

		default:
			break;
		}
		
		StringBuilder sb = new StringBuilder();
		// 总部添加机构领卡信息，修改卡表中卡状态为:1 总部出库

		sb = new StringBuilder(" select o from Cards o where 1=1 ");
		sb.append(" and o.cardNo>= " + modStock.getBeginCardNo());

		sb.append(" and o.status=" + i);
		sb.append(" and o.cardNo like '" + modStock.getCardBin().getBinId()
				+ "%'");

		Query query = em.createQuery(sb.toString());
		List<Cards> cardsList = query.getResultList();

		for (Cards cards : cardsList) {
			if(modStock.getMerId()!=null&&"".equals(modStock.getMerId())){
				cards.setMerchants(new Merchants(modStock.getMerId()));	
			}			
			cards.setStatus(status);
			this.update(cards);
		}
	}

	/***
	 * 
	 *@Title:queryByCardNoShow
	 *@Description:根据显示卡号查询卡号返回CardsDTO
	 *@param:@param cardNoShow
	 *@param:@return
	 *@return:CardsDAOImpl
	 *@author:井建国
	 *@thorws:
	 *@father:@see com.paySystem.ic.dao.card.CardsDAO#queryByCardNoShow(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public CardsDTO queryByCardNoShow(String cardNoShow) {
		UserSession us=Utils.getUserSession();
		String  jpl ="select o from Cards o where o.cardViewNo = '" + cardNoShow+"'";
		Query query = em.createQuery(jpl);
		CardsDTO cardsDTO = new CardsDTO();
		if (query.getResultList() == null || query.getResultList().size() == 0) {
			return null;
		} else {
			List<Cards> cardsList = query.getResultList();
			for (int i = 0; i < cardsList.size(); i++) {
				cardsDTO = getCardsDTO(cardsList.get(0));
			}
		}
		return cardsDTO;
	}
	/**
	 *@Title:queryByCardNo
	 *@Description:根据卡号查询该条记录
	 *@param:@param cardNo
	 *@param:@return
	 *@return:CardsDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public CardsDTO queryByCardNo(String cardNo) {
		String jpl = "select o from Cards o where o.cardNo ='" + cardNo
				+ "'";
		CardsDTO cardsDTO = new CardsDTO();
		List<Cards> cardsList = this.findByJpl(jpl);
		for (int i = 0; i < cardsList.size(); i++) {
			cardsDTO = getCardsDTO(cardsList.get(0));
		}
		return cardsDTO;
	}

	/*
	 * (non-Javadoc) <p>Title: findByStatus</p> <p>Description:
	 * 根据卡BIN和状态查询当前所属机构或商户的卡号</p>
	 * 
	 * @param status
	 * 
	 * @return
	 * 
	 * @see com.paySystem.ic.dao.card.CardsDAO#findByStatus(java.lang.Integer)
	 * 
	 * @author lily
	 * 
	 * @date 2014-1-7 下午03:21:26
	 */
	@SuppressWarnings("unchecked")
	public String findByStatus(String bin, Integer status) {
		UserSession us = Utils.getUserSession();
		StringBuilder sql = new StringBuilder(
				"select o from Cards o where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		String orgMerId = us.getOrganId() == null ? us.getMerId() : us
				.getOrganId();
		// 限定可查找权限范围
		if (us.getUserLevel() == 2) {
			sql.append(" and o.merchants.merId=?").append(params.size() + 1);
			params.add(us.getMerId());
		} else {
			sql.append(" and o.organs.organId=?").append(params.size() + 1);
			params.add(us.getOrganId());
		}

		if (status != null) {
			sql.append(" and o.status = ?").append(params.size() + 1);
			params.add(status);
		}
		if (bin != null) {
			sql.append(" and o.cardNo like ?").append(params.size() + 1);
			params.add(bin + "%");
		}
		
		sql.append(" order by o.cardNo asc");
		Query query = em.createQuery(sql.toString());

		setQueryParams(query, params.toArray());

		List<Cards> cardList = query.getResultList();
		String cardNo = "";
		for(int i=0;i<cardList.size();i++){
		  if (cardList.size() > 0) {
			Cards cards = cardList.get(i);
			if(cards.getMemSign()==0){
			cardNo = cards.getCardNo();
		  }
		}
		   }
		return cardNo;
	}

	/***
	 * 
	 * @see com.paySystem.ic.dao.card.CardsDAO#findByBinId(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 * @Description:加载起始卡号
	 * @date: 2014-4-14上午09:47:18
	 * @author: 井建国
	 * @version: V1.0
	 * @param binId
	 * @param organId
	 * @param merId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CardsDTO findByBinId(String binId, String organId, String merId) {
		String jpl = "";
		if (merId != null) {
			// 商户领卡
			jpl = "select o from Cards o where o.cardNo like ?1 and o.merchants.merId = ?2 and o.memSign = 0 and o.status=4 order by cardNo asc";
		} else {
			// 总部入库 或 机构领卡
			jpl = "select o from Cards o where o.cardNo like ?1 and o.organs.organId = ?2 and o.memSign = 0 and (o.status=0 or o.status=2) order by o.cardNo asc";
		}
		Query query = em.createQuery(jpl);
		query.setParameter(1, binId + "%");
		if (merId != null) {
			query.setParameter(2, merId);
		} else {
			query.setParameter(2, organId);
		}
		List<Cards> list = query.getResultList();
		if (list.size() != 0) {
			Cards cards = new Cards();
			for (int i = 0; i < list.size(); i++) {
				cards = list.get(0);
				break;
			}
			CardsDTO cardsDTO = getCardsDTO(cards);
			return cardsDTO;
		} else {
			return null;
		}
	}
	
	/**
	 *@Title:merStockUpdateStatus
	 *@Description:商户领卡确认，修改卡表中信息
	 *@param:@param modStock
	 *@return: void
	 *@author: 谢洪飞
	 */
	public void merStockUpdateStatus(ModStock modStock) {
		StringBuilder sb = new StringBuilder();
		// 总部添加机构领卡信息，修改卡表中卡状态为:1 总部出库

		sb = new StringBuilder(" select o from Cards o where 1=1 ");
		sb.append(" and o.cardNo>= " + modStock.getBeginCardNo());

		sb.append(" and o.status = 1");
		sb.append(" and o.cardNo like '" + modStock.getCardBin().getBinId()
				+ "%'");

		Query query = em.createQuery(sb.toString());
		List<Cards> cardsList = query.getResultList();

		for (Cards cards : cardsList) {
			cards.setStatus(3);
			this.update(cards);
		}
	}

	
}
