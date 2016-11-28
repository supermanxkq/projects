package com.paySystem.ic.dao.buss.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.buss.MerPromotion;
import com.paySystem.ic.bean.buss.Promotion;
import com.paySystem.ic.dao.buss.MerPromotionDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;

/**
 * 商户活动管理Dao实现类
 *  
 * @ClassName:PromotionDaoImpl
 * @Description:活动管理Dao实现类
 * @date: 2014-8-21上午10:49:02
 * @author: 赵瑞群
 * @version: V1.0
 */
@Repository(MerPromotionDao.MERPROMOTIONDAO)
public class MerPromotionDaoImpl extends DaoSupport<MerPromotion> implements MerPromotionDao {

	/**
	 * 
	*@Title:findCountMerByProId
	*@Description:通过活动id查询参与该活动的商户数
	*@Params:@param proid
	*@Params:@return
	*@Return:int
	*@author:luckygroup
	*@Date:2014-8-23下午5:31:23
	 */
	public long findCountMerByProId(int proid){
		String sql = "select count(*) from B_MerPromotion mer,b_merapplyrecord record where mer.merid = record.merId and record.status!=3 and record.status!=9 and mer.proid='"+proid+"' and record.proid="+proid;
		Query query = em.createNativeQuery(sql);
		BigInteger countBig = (BigInteger)query.getSingleResult();
		long count = countBig.longValue();
		return count;
	}
	
	
	

	/**
	 *  查询平台可用的平台活动
	 *@Title: findOnlinePromotionPlatform
	 *@Description:  查询平台可用的平台活动
	 *@param:@return
	 *@Return: List<PromotionDTO> 可报名的平台活动
	 *@author: Jacky
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<MerPromotionDTO> findOnlinePromotionPlatformById(Integer proId) throws Exception {
		List<MerPromotionDTO> proList = new ArrayList<MerPromotionDTO>();
		List<MerPromotion> onlineProList = em.createQuery("from MerPromotion o where o.proid=? ").setParameter(1, proId).getResultList();
		if(CollectionUtils.isNotEmpty(onlineProList)) {
			for(MerPromotion promotion : onlineProList) {
				MerPromotionDTO proDTO = new MerPromotionDTO();
				BeanUtils.copyProperties(promotion, proDTO,new String[]{"descr"});
				proList.add(proDTO);
			}
		}
		return proList;
	}

	


	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.buss.MerPromotionDao#saveMerPromotion(com.paySystem.ic.web.dto.buss.MerPromotionDTO)
	 *@MethodName:saveMerPromotion
	 *@Description:保存商户活动关联信息
	 *@param merpromotionDTO
	 *@Author:luckygroup
	 *@Date:2014-8-24上午9:23:43
	 */
	
	public void saveMerPromotion(MerPromotionDTO merPromotionDTO) {
		
		MerPromotion merPromotion = new MerPromotion();
		BeanUtils.copyProperties(merPromotionDTO, merPromotion,new String[]{"descr"});
		
		
		
		if(merPromotionDTO.getMpid()==null){
			
			merPromotion.setCreateTime(new Date());
			merPromotion.setUpdateTime(new Date());
			this.save(merPromotion);
			
		}else{
				
			merPromotion.setUpdateTime(new Date());
			this.update(merPromotion);
		}
		
	}




	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.buss.MerPromotionDao#findByProIdAndMerId(java.lang.Integer, java.lang.String)
	 *@MethodName:findByProIdAndMerId
	 *@Description:查找商户活动管理信息
	 *@param proid
	 *@param merId
	 *@return
	 *@Author:luckygroup
	 *@Date:2014-8-24上午9:23:43
	 */
	
	public MerPromotionDTO findByProIdAndMerId(Integer proid, String merId) {
		MerPromotion merPromotion = new MerPromotion();
		
		MerPromotionDTO merPromotionDTO = new MerPromotionDTO();
		/**根据ID获取活动管理实体对象*/
		Query  query = em.createQuery("from MerPromotion mer where mer.proid=? and mer.merid=? ");
		query.setParameter(1, proid);
		query.setParameter(2, merId);
		List merList = query.getResultList();
		if(merList.size()>0){
			merPromotion = (MerPromotion)merList.get(0);
			/**Bean2DTO*/
			BeanUtils.copyProperties(merPromotion,merPromotionDTO,new String[]{"descr"});
			
			
			return merPromotionDTO;
		}else{
			return null;
		}
		
	}


	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.buss.MerPromotionDao#findByProIdAndMerId(java.lang.Integer, java.lang.String)
	 *@MethodName:findByProIdAndMerId
	 *@Description:查找商户活动管理信息
	 *@param proid
	 *@return
	 *@Author:luckygroup
	 *@Date:2014-8-24上午9:23:43
	 */
	


	public MerPromotionDTO findByProId(Integer proid) {
		
		MerPromotion merPromotion = new MerPromotion();
		
		MerPromotionDTO merPromotionDTO = new MerPromotionDTO();
		/**根据ID获取活动管理实体对象*/
		Query  query = em.createQuery("from MerPromotion mer where mer.proid=? ");
		query.setParameter(1, proid);
		List merList = query.getResultList();
		if(merList.size()>0){
			merPromotion = (MerPromotion)merList.get(0);
			/**Bean2DTO*/
			BeanUtils.copyProperties(merPromotion,merPromotionDTO,new String[]{"descr"});
			
			
			return merPromotionDTO;
		}else{
			return null;
		}
	}


}
