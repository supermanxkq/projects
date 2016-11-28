package com.paySystem.ic.dao.buss.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.Promotion;
import com.paySystem.ic.dao.buss.PromotionDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.buss.MerApplyRecordDTO;
import com.paySystem.ic.web.dto.buss.PromotionDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 活动管理Dao实现类
 * 
 * @ClassName:PromotionDaoImpl
 * @Description:活动管理Dao实现类
 * @date: 2014-8-21上午10:49:02
 * @author: 赵瑞群
 * @version: V1.0
 */
@Repository(PromotionDao.PROMOTIONDAO)
public class PromotionDaoImpl extends DaoSupport<Promotion> implements
		PromotionDao {

	/**
	 * 活动管理保存方法
	 * 
	 *@Title:savePromotion
	 *@Description: 保存活动信息
	 *@param:@param promotionDTO 活动信息对象
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public Integer savePromotion(PromotionDTO promotionDTO) {

		Promotion promotion = new Promotion();
		BeanUtils.copyProperties(promotionDTO, promotion, new String[] {
				"beginTime", "endTime", "proImg", "descr" });
		try {
			promotion.setDescr(promotionDTO.getDescr().getBytes("UTF8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		promotion.setBeginTime(promotionDTO.getBeginTime());
		promotion.setEndTime(promotionDTO.getEndTime());
		promotion.setProImg(promotionDTO.getProImgFileName());

		if (promotionDTO.getProid() == null) {

			promotion.setCreateTime(new Date());
			promotion.setUpdateTime(new Date());

			this.save(promotion);
			return promotion.getProid();
		} else {

			Promotion oldPromotion = this.find(promotionDTO.getProid());

			if (oldPromotion != null) {
				/** 如果不删除 应该把原来的图片url赋值进去 **/
				if (promotionDTO.getDeleteProImg() == 0
						&& StringUtils
								.isBlank(promotionDTO.getProImgFileName())) {
					promotion.setProImg(oldPromotion.getProImg());
				}
			}

			promotion.setUpdateTime(new Date());
			this.update(promotion);
			return promotion.getProid();
		}

	}

	/**
	 *@Title:queryPromotionByCond
	 *@Description:根据条件查询活动管理信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum 每页显示调试
	 *@param:@param PromotionDTO PromotionDTO对象
	 *@param:@param orderBy 排序方式
	 *@param:@return
	 *@return:List<PromotionDTO> 返回DTO集合
	 *@author: 赵瑞群
	 * @throws Exception
	 *@Thorws:
	 */
	public QueryResult<PromotionDTO> queryPromotionByCond(int firstPage,
			int pageNum, PromotionDTO promotionDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {

		QueryResult<PromotionDTO> dtoResult = new QueryResult<PromotionDTO>();
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		/** 获取当前登陆人信息 */
		UserSession us = Utils.getUserSession();

		String depeName = us.getMerName();

		int userLevel = us.getUserLevel();

		/** 如果为商户 则只能查询本商户的活动或者平台活动 */
		if (userLevel == 2) {
			sb.append(" and (o.proHost like ?").append(params.size() + 1);
			params.add("" + depeName + "");

			sb.append(" or o.hostSign = 0)");
		}

		if (StringUtils.isNotBlank(promotionDTO.getProname())) {
			sb.append(" and o.proname like ?").append(params.size() + 1);
			params.add("%" + promotionDTO.getProname() + "%");
		}

		if (StringUtils.isNotBlank(promotionDTO.getProHost())) {
			sb.append(" and o.proHost like ?").append(params.size() + 1);
			params.add("%" + promotionDTO.getProHost() + "%");
		}

		if (Integer.parseInt(promotionDTO.getHostSign()) != -1) {
			sb.append(" and o.hostSign = ?").append(params.size() + 1);
			params.add(promotionDTO.getHostSign());
		}

		if (Integer.parseInt(promotionDTO.getProType()) != -1) {
			sb.append(" and o.proType = ?").append(params.size() + 1);
			params.add(promotionDTO.getProType());
		}

		if (Integer.parseInt(promotionDTO.getStatus()) != -1) {
			sb.append(" and o.status = ?").append(params.size() + 1);
			params.add(promotionDTO.getStatus());
		}

		if (Integer.parseInt(promotionDTO.getProItem()) != -1) {
			sb.append(" and o.proItem = ?").append(params.size() + 1);
			params.add(promotionDTO.getProItem());
		}

		QueryResult<Promotion> result = getScrollData(firstPage, pageNum, sb
				.toString(), params.toArray(), orderBy);

		dtoResult = this.beanResult2dtoResult(result);

		return dtoResult;
	}

	/**
	 * 查询平台可用的平台活动
	 * 
	 * @Title: findOnlinePromotionPlatform
	 *@Description: 查询平台可用的平台活动
	 *@param:@return
	 *@Return: List<PromotionDTO> 可报名的平台活动
	 *@author: Jacky
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<PromotionDTO> findOnlinePromotionPlatform(String merId,
			String userName) throws Exception {
		List<PromotionDTO> proList = new ArrayList<PromotionDTO>();
		List<Object[]> onlineProList = em
				.createNativeQuery(
						"select o2.proId,o2.proname from B_Promotion o2 left outer join B_MerApplyRecord o1   on o1.proId=o2.proId where (o2.operatorId=? and  o2.status not in (5,6,9))"
								+ "or (o1.merid=? and  o1.status=2  and  o2.status not in (5,6,9))")
				.setParameter(1, userName).setParameter(2, merId)
				.getResultList();
		if (CollectionUtils.isNotEmpty(onlineProList)) {
			for (Object[] promotion : onlineProList) {
				PromotionDTO proDTO = new PromotionDTO();
				proDTO.setProid((Integer) promotion[0]);
				proDTO.setProname((String) promotion[1]);
				proList.add(proDTO);
			}
		}
		return proList;
	}

	/**
	 * BeanResult转 实体DTOResult
	 * 
	 *@Title:dtoResult2beanResult
	 *@Description:BeanResult转 实体DTOResult
	 *@param:@param result 实体BeanResult
	 *@param:@return
	 *@return:QueryResult<PaySerParamDTO> DTO类型Result信息
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	private QueryResult<PromotionDTO> beanResult2dtoResult(
			QueryResult<Promotion> result) {

		QueryResult<PromotionDTO> dtoResult = new QueryResult<PromotionDTO>();
		List<PromotionDTO> dtoList = new ArrayList<PromotionDTO>();

		if (result != null) {

			for (Promotion promotion : result.getResultlist()) {

				PromotionDTO promotionDTO = new PromotionDTO();
				BeanUtils.copyProperties(promotion, promotionDTO, new String[] {
						"proImg", "descr" });
				promotionDTO.setProImgFileName(promotion.getProImg());
				try {
					promotionDTO.setDescr(new String(promotion.getDescr(),
							"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				dtoList.add(promotionDTO);

			}

		}
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(result.getTotalrecord());

		return dtoResult;
	}

	/**
	 * 获取活动管理信息
	 * 
	 *@Title:findById
	 *@Description:根据Id获取活动管理Dto对象
	 *@param:@param logistId 活动管理ID
	 *@param:@return
	 *@return:PromotionDTO 活动管理DTO对象
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO findById(Integer proid) {

		Promotion promotion = new Promotion();

		PromotionDTO promotionDTO = new PromotionDTO();
		/** 根据ID获取活动管理实体对象 */
		promotion = this.find(proid);
		/** Bean2DTO */
		BeanUtils.copyProperties(promotion, promotionDTO, new String[] {
				"proImg", "descr" });
		promotionDTO.setProImgFileName(promotion.getProImg());
		if (promotion.getDescr() != null) {
			try {
				promotionDTO
						.setDescr(new String(promotion.getDescr(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return promotionDTO;
	}

	/**
	 * 删除活动管理信息（逻辑删除）
	 * 
	 *@Title:delPromotion
	 *@Description: 删除活动管理信息
	 *@param:@param logistId
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO delPromotion(Integer proid) throws Exception {

		/** 根据Id获取活动实体对象 */
		Promotion promotion = this.find(proid);

		/** 更改状态为"9"删除状态 */
		promotion.setStatus("9");

		/** 更新修改内容，实现逻辑删除操作 */
		this.update(promotion);
		PromotionDTO promotionDTO = new PromotionDTO();
		/** Bean2DTO */
		BeanUtils.copyProperties(promotion, promotionDTO, new String[] {
				"proImg", "descr" });
		promotionDTO.setProImgFileName(promotion.getProImg());
		try {
			promotionDTO.setDescr(new String(promotion.getDescr(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return promotionDTO;

	}

	/**
	 * 检查名称是否已存在
	 * 
	 *@Title:checkSameName
	 *@Description: 检查活动管理名称是否已存在，如存在返回 false
	 *@param:@param logistName 活动管理名称
	 *@param:@return
	 *@Return:boolean
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public boolean checkSameName(String proname) {

		String sql = "from Promotion where proname=?1";

		Query query = em.createQuery(sql);

		query.setParameter(1, proname);

		List proList = query.getResultList();

		return proList.size() < 1;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.buss.PromotionDao#findGoodsCountByProid
	 *                        (int)
	 *@MethodName:findGoodsCountByProid
	 *@Description:TODO
	 *@param proid
	 *@return
	 *@Author:luckygroup
	 *@Date:2014-8-23下午6:30:22
	 */

	public long findGoodsCountByProid(int proid) {
		String sql = "select count(*) from B_GoodsInfo goods where goods.goodsSta=0 and  goods.proid="
				+ proid;
		Query query = em.createNativeQuery(sql);
		BigInteger countBig = (BigInteger) query.getSingleResult();
		long count = countBig.longValue();
		return count;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.buss.PromotionDao#openRecruit(
	 *                        java.lang.Integer)
	 *@MethodName:openRecruit
	 *@Description:开始招募
	 *@param proid
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午5:36:11
	 */

	public PromotionDTO openRecruit(Integer proid) throws Exception {
		/** 根据Id获取活动实体对象 */
		Promotion promotion = this.find(proid);

		promotion.setStatus("2");

		/** 更新修改内容 */
		this.update(promotion);
		PromotionDTO promotionDTO = new PromotionDTO();
		BeanUtils.copyProperties(promotion, promotionDTO, new String[] {
				"proImg", "descr" });
		promotionDTO.setProid(promotion.getProid());
		return promotionDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.buss.PromotionDao#stopRecruit(
	 *                        java.lang.Integer)
	 *@MethodName:stopRecruit
	 *@Description:停止招募
	 *@param proid
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午5:36:11
	 */

	public PromotionDTO stopRecruit(Integer proid) throws Exception {
		/** 根据Id获取活动实体对象 */
		Promotion promotion = this.find(proid);

		promotion.setStatus("3");

		/** 更新修改内容 */
		this.update(promotion);
		PromotionDTO promotionDTO = new PromotionDTO();
		promotionDTO.setProid(promotion.getProid());
		return promotionDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.buss.PromotionDao#stopPromotion
	 *                        (java.lang.Integer)
	 *@MethodName:stopPromotion
	 *@Description:终止活动
	 *@param proid
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午5:36:11
	 */

	public PromotionDTO stopPromotion(Integer proid) throws Exception {
		/** 根据Id获取活动实体对象 */
		Promotion promotion = this.find(proid);

		promotion.setStatus("6");

		/** 更新修改内容 */
		this.update(promotion);
		PromotionDTO promotionDTO = new PromotionDTO();
		promotionDTO.setProid(promotion.getProid());
		return promotionDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.buss.PromotionDao#beginPromotion
	 *                        (java.lang.Integer)
	 *@MethodName:beginPromotion
	 *@Description:开始活动
	 *@param proid
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午5:36:11
	 */

	public PromotionDTO beginPromotion(Integer proid) throws Exception {
		/** 根据Id获取活动实体对象 */
		Promotion promotion = this.find(proid);

		promotion.setStatus("4");

		/** 更新修改内容 */
		this.update(promotion);
		PromotionDTO promotionDTO = new PromotionDTO();
		promotionDTO.setProid(promotion.getProid());

		return promotionDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.buss.PromotionDao#queryApplyRecordByCond
	 *                        (int, int,
	 *                        com.paySystem.ic.web.dto.buss.MerApplyRecordDTO,
	 *                        java.util.LinkedHashMap)
	 *@MethodName:queryApplyRecordByCond
	 *@Description:获取商户申请列表
	 *@param firstPage
	 *@param pageNum
	 *@param merApplyRecordDTO
	 *@param orderBy
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-25下午9:42:30
	 */

	public QueryResult<List> queryApplyRecordByCond(int firstPage, int pageNum,
			MerApplyRecordDTO merApplyRecordDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		int proid = merApplyRecordDTO.getProid();
		String recordStatus = merApplyRecordDTO.getStatus();
		QueryResult<List> qr = new QueryResult<List>();
		String sql = "SELECT  mer.merName name, record.recordId recordId,record.status status, mp.applyGoodsQuantity quantity,mer.merId merid  from";
		sql += " b_merapplyrecord record , b_merchants mer , b_merpromotion mp where record.merid = mer.merId and record.merid = mp.merid and record.proid = mp.proid and record.proid=? and record.status=?";

		if (merApplyRecordDTO.getMerid() != null
				&& !merApplyRecordDTO.getMerid().equals("")) {
			sql += " and mer.merName like ?";
		}

		sql += " order by record.updateTime desc ";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, proid);
		query.setParameter(2, recordStatus);
		if (merApplyRecordDTO.getMerid() != null
				&& !merApplyRecordDTO.getMerid().equals("")) {
			query.setParameter(3, "%" + merApplyRecordDTO.getMerid() + "%");
		}

		int countNum = query.getResultList().size();
		if (firstPage != -1 && pageNum != -1) {
			query.setFirstResult(firstPage).setMaxResults(pageNum);
		}
		qr.setResultlist(query.getResultList());

		qr.setTotalrecord(countNum);

		return qr;
	}

	/**
	 * 修改结束时间
	 */
	public void updateEndTime(PromotionDTO promotionDTO) {
		/** 根据Id获取活动实体对象 */
		Promotion promotion = this.find(promotionDTO.getProid());

		promotion.setEndTime(promotionDTO.getEndTime());

		/** 更新修改内容 */
		this.update(promotion);

	}

	/**
	 *@MethodName:ajaxObject
	 *@Description:验证广告对象
	 *@param id
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-8上午11:30:57
	 */
	public boolean ajaxObject(String id) {
		// TODO Auto-generated method stub
		Promotion p = this.find(Integer.valueOf(id));
		if (p != null) {
			return true;
		} else {
			return false;
		}
	}

}
