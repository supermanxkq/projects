package com.paySystem.ic.dao.base.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Bail;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.dao.base.BailDAO;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.BailDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/****
 * 
 * @ClassName:BailDAOImpl
 * @Description:保证金底层实现
 * @date: 2014-5-14下午05:30:37
 * @author: 井建国
 * @version: V1.0
 */
@Repository(BailDAO.BIALDAO)
public class BailDAOImpl extends DaoSupport<Bail> implements BailDAO,
		Serializable {

	private static final long serialVersionUID = 1L;
	@Resource
	OrgansDao organsDAO;
	@Resource
	MerchantsDao merchantsDaoImpl;

	/***
	 * 
	 * @see com.paySystem.ic.dao.base.BailDAO#queryMerByCond(int, int,
	 *      com.paySystem.ic.web.dto.base.BailDTO, java.util.LinkedHashMap)
	 * @Description:分页查询结果
	 * @date: 2014-5-15上午10:51:50
	 * @author: 井建国
	 * @version: V1.0
	 * @param firstindex
	 * @param maxresult
	 * @param bailDTO
	 * @param orderby
	 * @return
	 * @throws Exception
	 */
	public QueryResult<Bail> queryMerByCond(int firstindex, int maxresult,
			BailDTO bailDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		StringBuilder sql = new StringBuilder();// 封装查询where条件
		List<Object> params = new ArrayList<Object>();// 参数设置
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 1:
			// 机构级别
			sql.append(" and o.organs.organId = '" + us.getOrganId() + "'");
			break;
		/*
		 * case 2: // 商户级别 sql.append(" and o.merchants.merId = '" +
		 * us.getMerId() + "'"); break;
		 */
		}
		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (bailDTO.getMerOrgName() != null) {
			List<Organs> listOrg = organsDAO.queryByName(bailDTO
					.getMerOrgName());
			List<Merchants> listMer = merchantsDaoImpl.queryByName(bailDTO
					.getMerOrgName());
			if (bailDTO.getTypeSign() == 2) {
				if (listOrg.size() != 0) {
					for (int i = 0; i < listOrg.size(); i++) {
						Organs organs = listOrg.get(i);
						sql.append(" or o.merOrgId = ?").append(
								params.size() + 1);
						// 设置机构编号
						sql.append(" or o.merOrgId = ?").append(
								params.size() + 1);
						// 设置会员编号
						params.add(organs.getOrganId());
					}
				}
			} else {
				if (listMer.size() > 0) {
					for (int j = 0; j < listMer.size(); j++) {
						Merchants merchants = listMer.get(j);
						sql.append(" or o.merOrgId = ?").append(
								params.size() + 1);
						// 设置商户编号
						params.add(merchants.getMerId());
					}
				} else {
					sql.append(" and o.merOrgId = ?").append(params.size() + 1);
					params.add("");
				}
			}
		}
		if (bailDTO.getTypeSign() != null) {
			sql.append(" and o.typeSign = ?").append(params.size() + 1);
			// 设置类型标识
			params.add(bailDTO.getTypeSign());
		}
		if (bailDTO.getCoopStatus() != null && bailDTO.getCoopStatus() != -1) {
			sql.append(" and o.coopStatus = ?").append(params.size() + 1);
			// 设置合作状态
			params.add(bailDTO.getCoopStatus());
		}
		QueryResult<Bail> queryResult = getScrollData(firstindex, maxresult,
				sql.toString(), params.toArray(), orderby);

		return queryResult;
	}

	/**
	 * 
	 * @see com.paySystem.ic.dao.base.BailDAO#update(com.paySystem.ic.web.dto.base.BailDTO)
	 * @Description:根据更新DTO实体进行Bail更新
	 * @date: 2014-5-15上午10:53:02
	 * @author: 井建国
	 * @version: V1.0
	 * @param bailDTO
	 * @return
	 */
	public void update(Bail bail) {
		bail.setUpdateTime(getSysTime());
		super.update(bail);
	}

	/***
	 * 
	 *@Title:findByBailId
	 *@Description:根据bailId 查询实体
	 *@param:@param bailId
	 *@param:@return
	 *@author:井建国
	 *@thorws:
	 *@see com.paySystem.ic.dao.base.BailDAO#findByBailId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Bail findByBailId(Integer bailId) {
		String jpl = "select o from Bail o where o.bailId = " + bailId;
		Query query = em.createQuery(jpl);
		List<Bail> list = query.getResultList();
		Bail bail = new Bail();
		for (int i = 0; i < list.size(); i++) {
			bail = list.get(0);
		}
		return bail;
	}

	public void saveBail(Bail bail) {
		bail.setPayTime(getSysTime());
		save(bail);

	}

	/**
	 *@Title:findByMerId
	 *@Description:根据商户编号获取保证金信息
	 *@param:@param merId
	 *@param:@return
	 *@return:BailDTO
	 *@author:谢洪飞
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public BailDTO findBailByMerId(String merId) {
		Bail bail = new Bail();
		String jpql = "select o from Bail o where o.merOrgId = ?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, merId);
		List<Bail> bailList = query.getResultList();
		if (bailList.size() > 0) {
			bail = bailList.get(0);
		}
		BailDTO bailDTO = new BailDTO();
		bailDTO = this.convertToDto(bail);
		return bailDTO;
	}

	/**
	 *@Title:findByFactId
	 *@Description:根据油厂编号获取保证金信息
	 *@param:@param factId
	 *@param:@return
	 *@return:BailDTO
	 *@author:张亚运
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public BailDTO findBailByFactId(String factId) {
		Bail bail = new Bail();
		String jpql = "select o from Bail o where o.typeSign = 2 and o.orgOilId = '"
				+ factId + "'";
		Query query = em.createQuery(jpql.toString());
		List<Bail> bailList = query.getResultList();
		if (bailList.size() > 0) {
			bail = bailList.get(0);
		}
		BailDTO bailDTO = new BailDTO();
		bailDTO = this.convertToDto(bail);
		return bailDTO;
	}

	/**
	 *@Title:convertToDto
	 *@Description:Bail实体转换BailDTO
	 *@param:@param bail
	 *@param:@return
	 *@return:BailDTO
	 *@author:谢洪飞
	 *@thorws:
	 */
	public BailDTO convertToDto(Bail bail) {
		BailDTO bailDTO = new BailDTO();
		bailDTO.setBailId(bail.getBailId());
		bailDTO.setBailAmt(bail.getBailAmt());
		bailDTO.setBuyOilRate(bail.getBuyOilRate());
		bailDTO.setContractNo(bail.getContractNo());
		bailDTO.setCoopStatus(bail.getCoopStatus());
		bailDTO.setCoopTime(bail.getCoopTime());
		bailDTO.setDescr(bail.getDescr());
		bailDTO.setMerOrgId(bail.getMerOrgId());
		bailDTO.setOrgOilId(bail.getOrgOilId());
		bailDTO.setPayTime(bail.getPayTime());
		bailDTO.setTypeSign(bail.getTypeSign());
		bailDTO.setUpdateTime(bail.getUpdateTime());
		return bailDTO;
	}

}
