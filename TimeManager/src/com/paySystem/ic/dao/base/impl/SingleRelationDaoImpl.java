package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.base.SingleRelation;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.base.SingleRelationDao;
import com.paySystem.ic.dao.card.CardBINDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.base.SingleRelationDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:SingleRelationDaoImpl
 * @Description:收单关系管理Dao实现类
 * @date: 2013-12-7下午02:26:53
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(SingleRelationDao.SINGLERELATIONDAO)
public class SingleRelationDaoImpl extends DaoSupport<SingleRelation> implements
		SingleRelationDao {

	@Resource
	OrgansDao organsDao;
	@Resource
	MerchantsDao merchantsDao;
	@Resource
	CardBINDAO cardBinDao;

	/**
	 * 生成收单关系编号
	 * 
	 * @return String singleRelationID
	 */
	public String createSinID() {
		String singleRelationID = Utils.createSerialNum(em, "sinRelId",
				"SingleRelation", 15, 0, null, null, null);
		return singleRelationID;
	}

	public SingleRelation saveSingleRelation(SingleRelationDTO singleRelationDTO) {

		/** 目前新增加一条收单关系，商户与卡BIN的之间的关系处理为：商户可以受理所有卡BIN */
		// List<OptionsString> binOptionsString =
		// cardBinDao.getOptionByOrganId(singleRelationDTO.getOrganId());
		String sinId = this.createSinID();

		SingleRelation singleRelation = new SingleRelation();
		/** 封装实体 singleRelation */
		singleRelation.setSinRelId(this.createSinId(sinId, 0));
		singleRelation.setMainOrganSign(singleRelationDTO.getMainOrganSign());
		singleRelation.setSinOrgans(organsDao.find(singleRelationDTO
				.getSinOrganId()));// 设置收单机构
		singleRelation
				.setOrgans(organsDao.find(singleRelationDTO.getOrganId()));// 设置发卡机构
		singleRelation.setMerchants(merchantsDao.find(singleRelationDTO
				.getMerId()));// 设置对应商户
		singleRelation.setMehodOfSett(singleRelationDTO.getMehodOfSett());
		singleRelation.setRateFee(singleRelationDTO.getRateFee());
		singleRelation.setAsinTranRateFee(singleRelationDTO
				.getAsinTranRateFee());
		singleRelation.setFeeTopLimit(singleRelationDTO.getFeeTopLimit());
		singleRelation.setServPlatformRatio(singleRelationDTO
				.getServPlatformRatio());
		singleRelation.setAcquirerRate(singleRelationDTO.getAcquirerRate());
		singleRelation.setOrganRate(singleRelationDTO.getOrganRate());
		singleRelation.setCountSettType(singleRelationDTO.getCountSettType());
		/** Bins后期作与卡Bin表关联，暂时设置111111 */
		/* singleRelation.setBins("1111111"); */
		// singleRelation.setCardBin(cardBinDao.find(binOptionsString.get(i).getKey()));
		singleRelation.setStatus(1);// 设置启用状态
		singleRelation.setCreateTime(this.getSysTime());
		singleRelation.setUpdateTime(this.getSysTime());
		this.save(singleRelation);

		return null;
	}


	public ReturnDTO updateSingleRelation(SingleRelationDTO singleRelationDTO) {
		ReturnDTO returnDTO = new ReturnDTO();
		// 获得要更新的sinRelation
		SingleRelation sinRelation = this.find(singleRelationDTO.getSinRelId());

		if (sinRelation != null) {
			sinRelation.setMainOrganSign(singleRelationDTO.getMainOrganSign());
			sinRelation.setSinOrgans(organsDao.find(singleRelationDTO
					.getSinOrganId()));// 设置收单机构
			sinRelation.setOrgans(organsDao
					.find(singleRelationDTO.getOrganId()));// 设置发卡机构
			sinRelation.setMerchants(merchantsDao.find(singleRelationDTO
					.getMerId()));// 设置对应商户
			sinRelation.setMehodOfSett(singleRelationDTO.getMehodOfSett());
			sinRelation.setRateFee(singleRelationDTO.getRateFee());
			sinRelation.setAsinTranRateFee(singleRelationDTO
					.getAsinTranRateFee());
			sinRelation.setFeeTopLimit(singleRelationDTO.getFeeTopLimit());
			sinRelation.setServPlatformRatio(singleRelationDTO
					.getServPlatformRatio());
			sinRelation.setAcquirerRate(singleRelationDTO.getAcquirerRate());
			sinRelation.setOrganRate(singleRelationDTO.getOrganRate());
			sinRelation.setCountSettType(singleRelationDTO.getCountSettType());
			/** Bins后期作与卡Bin表关联，暂时设置111111 */
			/* sinRelation.setBins("1111111"); */
			sinRelation.setUpdateTime(new Date());
			this.update(sinRelation);
			UserSession us = Utils.getUserSession();
			returnDTO.setFlag(true);
		}
		return returnDTO;
	}

	@SuppressWarnings("unchecked")
	public QueryResult querySingleRelByCond(int fristindex, int pageNum,
			SingleRelationDTO singleRelationDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(); // 封装查询where条件
		UserSession us = Utils.getUserSession();
		if(singleRelationDTO.getStatus()!=-1){
			sql.append(" and o.status = ?").append(params.size() + 1);
			params.add(singleRelationDTO.getStatus());
		}
		
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			/**
			 * 如果操作员为机构级别，则只能查看涉及到本机构的收单关系信息。 包括以下两种情况： 1.该机构属于收单机构 2.该机构属于发卡机构
			 */
			// 筛选发卡机构为本机构的收单关系信息
			sql.append(" and ( o.organs.organId = ?").append(params.size() + 1);
			params.add(us.getOrganId());
			// 筛选收单机构为本机构的收单关系信息
			sql.append(" or o.sinOrgans.organId = ?").append(params.size() + 1)
					.append(" )");
			params.add(us.getOrganId());
			break;
		case 2:
			sql.append(" and o.merchants.merId like ?").append(
					params.size() + 1);
			params.add("'" + us.getMerId() + "'");
			break;
		}

		if (StringUtils.isNotBlank(singleRelationDTO.getMerId())) {
			sql.append(" and o.merchants.merId like ?").append(
					params.size() + 1);
			// 设置参数：商户ID
			params.add("%" + singleRelationDTO.getMerId() + "%");
		}
		if (StringUtils.isNotBlank(singleRelationDTO.getOrganName())) {
			sql.append(" and o.organs.name like ?").append(params.size() + 1);
			params.add("%" + singleRelationDTO.getOrganName() + "%");
		}
		if (StringUtils.isNotBlank(singleRelationDTO.getMerName())) {
			sql.append(" and o.merchants.merName like ?").append(
					params.size() + 1);
			params.add("%" + singleRelationDTO.getMerName() + "%");
		}
		//sql.append(" and o.status=1 ");
		QueryResult queryResult = getScrollData(fristindex, pageNum, sql
				.toString(), params.toArray(), orderBy);

		return queryResult;
	}

	/**
	 *@Title:initMainOrgSingRel
	 *@Description:初始化商户与主机构的收单关系
	 *@param:@param merchantDto
	 *@return:void
	 *@author: 谢
	 *@thorws:
	 */

	public void initMainOrgSingRel(MerchantsDTO merchantDto, Merchants merchants) {

		UserSession us = Utils.getUserSession();
		String sinId = this.createSinID();
		SingleRelation singleRel = new SingleRelation();
		singleRel.setSinRelId(sinId);
		singleRel.setMerchants(merchants);
		Organs organ = new Organs();
		switch (us.getUserLevel()) {
		case 0:
			organ = organsDao.find(merchantDto.getOrganId());
			break;
		case 1:
			organ = organsDao.find(us.getOrganId());
		default:
			break;
		}
		// 设置主发卡机构

		if (organ.getParentId().equals("0")) {
			singleRel.setOrgans(organ);
			singleRel.setSinOrgans(organ);
		} else {
			singleRel.setOrgans(organsDao.find(organ.getParentId()));
			singleRel.setSinOrgans(organsDao.find(organ.getParentId()));
		}
		singleRel.setCountSettType(merchantDto.getSettelCountWay());// 计算结算方式
		singleRel.setAcquirerRate(merchantDto.getAgentDiscRate());
		singleRel.setAsinTranRateFee(merchantDto.getSingleFee());
		singleRel.setCreateTime(getSysTime());
		singleRel.setMehodOfSett(merchantDto.getSettlementWay());
		singleRel.setFeeTopLimit(merchantDto.getFeeLimit());
		singleRel.setRateFee(merchantDto.getRakeRate());// 手续费率 按金额
		singleRel.setServPlatformRatio(merchantDto.getPlatformRate());// 总部分成比率
		singleRel.setAcquirerRate(merchantDto.getAcquirerRate());// 收单机构
		singleRel.setOrganRate(merchantDto.getOrganRate()); // 发卡机构
		singleRel.setMainOrganSign(0); // 主发卡机构标志
		singleRel.setStatus(1);// 设为启用
		singleRel.setUpdateTime(new Date());
		this.save(singleRel);
	}

	/**
	 *@Title:findByMerIdBinId
	 *@Description:根据商户号和BinId查询收单关系
	 *@param:@param merId 商户号
	 *@param:@param binId 卡BIN号
	 *@param:@return
	 *@return:SingleRelation
	 *@author:谢
	 *@thorws:
	 */

	public SingleRelation findByMerIdBinId(String merId) {
		SingleRelation sinRelation = null;
		StringBuilder sb = new StringBuilder(
				" from SingleRelation o where o.merchants.merId ='" + merId
						+ "'");
		Query query = em.createQuery(sb.toString());
		List<SingleRelation> sinList = query.getResultList();
		if (sinList.size() > 0) {
			sinRelation = sinList.get(0);
		}
		return sinRelation;
	}

	public String createSinId(String id, int i) {
		Integer sinId = Integer.valueOf(id);
		sinId = sinId + i;
		String sinRelId = sinId.toString();
		while (sinRelId.length() < 15) {
			sinRelId = "0" + sinRelId;
		}
		return sinRelId;
	}

	/**
	 *@Title:checkExsisSinRel
	 *@Description:检查是否已经存在该收单关系
	 *@param:@param singleRelationDTO
	 *@param:@return
	 *@return:boolean
	 *@author: 谢
	 *@thorws:
	 */

	public boolean checkExsisSinRel(SingleRelationDTO singleRelationDTO) {

		StringBuilder sql = new StringBuilder(
				" select o from SingleRelation o where o.merchants.merId= '"
						+ singleRelationDTO.getMerId()
						+ "' and o.organs.organId= '"
						+ singleRelationDTO.getOrganId()
						+ "' and o.sinOrgans.organId= '"
						+ merchantsDao.find(singleRelationDTO.getMerId())
								.getOrgans().getOrganId() + "'");
		Query query = em.createQuery(sql.toString());
		List<SingleRelation> sinRels = query.getResultList();

		return sinRels.size() < 1;
	}

	/**
	 *@Title:querySingleRel
	 *@Description:查询收单关系
	 *@param:@return
	 *@return:SingleRelation
	 *@author:谢
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")

	public SingleRelation querySingleRel(String merId, String organId) {

		StringBuilder sql = new StringBuilder(
				" select o from SingleRelation o where o.merchants.merId = '"
						+ merId + "'");
		sql.append(" and o.organs.organId = '" + organId + "'");
		sql.append(" and o.sinOrgans.organId = '" + organId + "'");

		List<SingleRelation> sinRelList = em.createQuery(sql.toString())
				.getResultList();
		SingleRelation sinRelation = null;

		return sinRelation = sinRelList.size() > 0 ? sinRelList.get(0)
				: sinRelation;
	}

	/**
	 *@Title:updateSingleRelation
	 *@Description:更新收单关系信息
	 *@param:@param merchantsDto
	 *@return:void
	 *@author:谢
	 *@thorws:
	 */
	public void updateSingleRelation(MerchantsDTO merchantDto) {

		UserSession us = Utils.getUserSession();
		// 商户级别用户不能进行修改；
		if (us.getUserLevel() != 2) {
			Merchants merchants = merchantsDao.find(merchantDto.getMerId());
			String organId = merchants.getOrgans().getOrganId();
			SingleRelation singleRel = this.querySingleRel(merchantDto
					.getMerId(), organId);
			if (singleRel != null) {
				singleRel.setCountSettType(merchantDto.getSettelCountWay());// 计算结算方式
				singleRel.setAcquirerRate(merchantDto.getAgentDiscRate());
				singleRel.setAsinTranRateFee(merchantDto.getSingleFee());
				singleRel.setMehodOfSett(merchantDto.getSettlementWay());
				singleRel.setFeeTopLimit(merchantDto.getFeeLimit());
				singleRel.setRateFee(merchantDto.getRakeRate());// 手续费率 按金额
				singleRel.setServPlatformRatio(merchantDto.getPlatformRate());// 总部分成比率
				singleRel.setAcquirerRate(merchantDto.getAcquirerRate());// 收单机构
				singleRel.setOrganRate(merchantDto.getOrganRate()); // 发卡机构
				singleRel.setUpdateTime(new Date());
				this.update(singleRel);
			}
		}
	}

}
