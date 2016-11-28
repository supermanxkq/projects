package com.paySystem.ic.dao.account.Impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.AccKinds;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.dao.account.AccKindsDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.account.AccKindsDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

@Repository(AccKindsDAO.ACCKINDSDAO)
public class AccKindsDaoImpl extends DaoSupport<AccKinds> implements
		AccKindsDAO {


	public AccKindsDTO findAccKinds(Integer kindId) throws Exception {
		return getAccKindsDTO(this.find(kindId));
	}


	public void saveAccKinds(AccKindsDTO accKindsDTO) throws Exception {
		save(getAccKinds(accKindsDTO));
	}


	public void updateAccKinds(AccKindsDTO accKindsDTO) throws Exception {
		accKindsDTO.setUpdateTime(DateTimeTool.dateFormat(
				"yyyy-MM-dd HH:mm:ss", this.getSysTime()));
		this.update(getAccKinds(accKindsDTO));
	}

	@SuppressWarnings("unchecked")
	public QueryResult<AccKindsDTO> queryAll(int firstindex, int maxresult,
			AccKindsDTO accKindsDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		UserSession us = Utils.getUserSession();
		String sql = "select o from AccKinds o ";
		QueryResult qr = new QueryResult<AccKinds>();
		StringBuilder wherejpql = new StringBuilder(""); // 封装查询where条件
		List<Object> params = new ArrayList<Object>();
		/** 判断页面条件 */
		if (us.getUserLevel()==1){
			wherejpql.append(" and o.organs.organId = ?").append(params.size() + 1);
			params.add(us.getOrganId());			
		}
		if (accKindsDTO.getStatus() != null && accKindsDTO.getStatus() != -1) {

			wherejpql.append(" and o.status = ?").append(params.size() + 1);
			params.add(accKindsDTO.getStatus());
		}
		if (StringUtils.isNotBlank(accKindsDTO.getKindName())) {
			wherejpql.append(" and o.kindName like ?")
					.append(params.size() + 1);
			params.add("%" + accKindsDTO.getKindName() + "%");
		}
		
		sql = sql
				+ (wherejpql == null || "".equals(wherejpql.toString().trim()) ? ""
						: " where 1=1 " + wherejpql.toString())
				+ buildOrderby(orderBy);
		System.out.println(sql);
		Query query = em.createQuery(sql);
		setQueryParams(query, params.toArray());
		/** 获取所有的记录 **/
		if (firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		}
		List<AccKinds> list = query.getResultList();
		List<AccKindsDTO> accKindsList = getAccKindsDTOList(list);
		qr.setResultlist(accKindsList);
		String sql2 = "select count(o) from AccKinds o"
				+ (wherejpql == null || "".equals(wherejpql.toString().trim()) ? ""
						: " where 1=1 " + wherejpql.toString());
		query = em.createQuery(sql2);
		setQueryParams(query, params.toArray());
		qr.setTotalrecord((Long) query.getSingleResult());
		return qr;
	}

	private List<AccKindsDTO> getAccKindsDTOList(List<AccKinds> list) {
		List<AccKindsDTO> accKindsList = new ArrayList<AccKindsDTO>();
		for (int i = 0; i < list.size(); i++) {
			AccKinds accKinds = list.get(i);
			AccKindsDTO accKindsDto = getAccKindsDTO(accKinds);
			accKindsList.add(accKindsDto);
		}
		return accKindsList;
	}

	private Integer createAccKindsId() {
		// String sql = "select C_ACC_KINDS_SEQ.nextval from dual";
		// Query query = em.createNativeQuery(sql);
		// List<Object> list = (List<Object>) query.getResultList();
		// Integer accKindsId = Integer.valueOf(list.get(0).toString());

		Integer accKindsId = 0;
		String sql = "select max(t.kindid) from c_acc_kinds t";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		if (list.get(0) != null) {
			accKindsId = Integer.valueOf(list.get(0).toString()) + 1;
		}

		return accKindsId;
	}

	private AccKinds getAccKinds(AccKindsDTO accKindsDTO) {
		AccKinds accKinds = new AccKinds();
		Integer accKindsId = accKindsDTO.getKindId();
		if (accKindsId == null) {
			accKindsId = createAccKindsId();
		}

		accKinds.setKindId(accKindsId); // 类型编号
		if (accKindsDTO.getKindName() != null) {
			accKinds.setKindName(accKindsDTO.getKindName()); // 类型名称
		}
		if (StringUtils.isNotBlank(accKindsDTO.getOrgId())) {
			// Organs organs = new Organs();
			// organs.setOrganId(accKindsDTO.getOrgId());
			Organs organs = new Organs(accKindsDTO.getOrgId());
			accKinds.setOrgans(organs);
		}
		if (StringUtils.isNotBlank(accKindsDTO.getMerId())) {
			Merchants merchants = new Merchants();
			merchants.setMerId(accKindsDTO.getMerId());
			accKinds.setMerchants(merchants);
		}
		if (accKindsDTO.getAccSign() != null) {
			accKinds.setAccSign(accKindsDTO.getAccSign()); // 消费类型 0：金额；1：按次数
		}
		if (accKindsDTO.getSaleTimesLimit() != null) {
			accKinds.setSaleTimesLimit(accKindsDTO.getSaleTimesLimit()); // 当日消费次数显示(0)表示无限制

		}
		if (accKindsDTO.getBeginDate() != null) {
			accKinds.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
					accKindsDTO.getBeginDate())); // 有效起始日期
		}
		if (!("".equals(accKindsDTO.getEndDate()))) {
			accKinds.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",
					accKindsDTO.getEndDate())); // 有效终止日期
		}
		if (accKindsDTO.getStatus() != null) {
			accKinds.setStatus(accKindsDTO.getStatus()); // 类型启用状态 0：删除；1：启用；2：
			// 禁用

		}
		if (accKindsDTO.getWithDraType() != null) {
			accKinds.setWithDraType(accKindsDTO.getWithDraType()); // 提现类型 0：否；1：是
		}
		if (accKindsDTO.getTransAccType() != null) {
			accKinds.setTransAccType(accKindsDTO.getTransAccType()); // 转账类型 0：否；1：是
		}
		if (accKindsDTO.getConsType() != null) {
			accKinds.setConsType(accKindsDTO.getConsType()); // 消费类型 0：否；1：是
		}
		if (accKindsDTO.getRechargeType() != null) {
			accKinds.setRechargeType(accKindsDTO.getRechargeType()); // 充值类型 0：否；1：是
		}		
		if (accKindsDTO.getUpdateTime() != null) {
			accKinds.setUpdateTime(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm:ss", accKindsDTO.getUpdateTime())); // 更新时间
		}
		if (accKindsDTO.getDescr() != null) {
			accKinds.setDescr(accKindsDTO.getDescr()); // 类型描述

		}
		return accKinds;
	}

	private AccKindsDTO getAccKindsDTO(AccKinds accKinds) {
		if (accKinds == null) {
			return null;
		}
		AccKindsDTO accKindsDto = new AccKindsDTO();
		if (accKinds.getKindId() != null) {
			accKindsDto.setKindId(accKinds.getKindId()); // 类型编号
		}
		if (accKinds.getKindName() != null) {
			accKindsDto.setKindName(accKinds.getKindName()); // 类型名称
		}

		if (accKinds.getOrgans() != null) {
			accKindsDto.setOrgId(accKinds.getOrgans().getOrganId());// 机构编号
			accKindsDto.setOrgName(accKinds.getOrgans().getName());
		}
		if (accKinds.getMerchants() != null) {
			accKindsDto.setMerId(accKinds.getMerchants().getMerId());// 商户号
			accKindsDto.setMerName(accKinds.getMerchants().getMerName());
		}
		if (accKinds.getAccSign() != null) {
			accKindsDto.setAccSign(accKinds.getAccSign()); // 消费类型 0：金额；1：按次数
		}
		if (accKinds.getSaleTimesLimit() != null) {
			accKindsDto.setSaleTimesLimit(accKinds.getSaleTimesLimit()); // 当日消费次数显示(0)表示无限制
		}
		if (accKinds.getBeginDate() != null) {
			accKindsDto.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
					accKinds.getBeginDate())); // 有效起始日期
		}
		if (accKinds.getEndDate() != null) {
			accKindsDto.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",
					accKinds.getEndDate())); // 有效终止日期
		}
		if (accKinds.getStatus() != null) {
			accKindsDto.setStatus(accKinds.getStatus()); // 类型启用状态 0：删除；1：启用；2：
			// 禁用
		}
		
		if (accKinds.getWithDraType() != null) {
			accKindsDto.setWithDraType(accKinds.getWithDraType()); //提现类型 0：否  1： 是
		}
		if (accKinds.getTransAccType() != null) {
			accKindsDto.setTransAccType(accKinds.getTransAccType()); //转账类型 0：否  1： 是
		}
		if (accKinds.getConsType() != null) {
			accKindsDto.setConsType(accKinds.getConsType()); //消费类型 0：否  1： 是
		}
		if (accKinds.getRechargeType() != null) {
			accKindsDto.setRechargeType(accKinds.getRechargeType()); //消费类型 0：否  1： 是
		}

		if (accKinds.getUpdateTime() != null) {
			accKindsDto.setUpdateTime(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm:ss", accKinds.getUpdateTime())); // 更新时间
		}
		if (accKinds.getDescr() != null) {
			accKindsDto.setDescr(accKinds.getDescr()); // 类型描述
		}
		return accKindsDto;
	}

	/*
	 * (non-Javadoc) 赵巧鹤 查询所有的账户
	 */

	public List<AccKinds> queryAll() {

		List<AccKinds> list = this.findByJpl("from AccKinds o");
		return list;
	}

	/**
	 * 
	 *@Title:findType
	 *@TODO:查出帐户类型的名字和ID
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<OptionsInteger> findType() {
		// TODO Auto-generated method stub
		UserSession us=Utils.getUserSession();
		StringBuffer sql = new StringBuffer("from AccKinds a where a.status=1 ");
		/**判断用户等级**/
		if(us.getUserLevel()==1){
			sql.append(" and a.organs.organId='"
				+ us.getOrganId() + "'");
		}
		
		List<AccKinds> list = this.findByJpl(sql.toString());
		List<OptionsInteger> option = new ArrayList<OptionsInteger>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				option.add(new OptionsInteger(list.get(i).getKindId(), list
						.get(i).getKindName()));
			}
		}
		return option;
	}
}