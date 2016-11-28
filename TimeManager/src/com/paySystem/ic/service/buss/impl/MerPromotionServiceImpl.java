package com.paySystem.ic.service.buss.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.dao.buss.MerPromotionDao;
import com.paySystem.ic.service.buss.MerPromotionService;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;
/**  
 * @Title: PromotionServiceImpl.java
 * @Package: com.paySystem.ic.bean.promotion
 * @Description: 活动管理业务实现类
 * @Author: 赵瑞群
 * @Date: 2014-7-21 下午3:14:09
 * @Version: V1.0  
 */
@Service(MerPromotionService.MERPROMOTIONSERV)
public class MerPromotionServiceImpl implements MerPromotionService{

	
	
	@Resource
	MerPromotionDao merPromotionDao;

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.MerPromotionService#findByProIdAndMerId(java.lang.Integer, java.lang.String)
	 *@MethodName:findByProIdAndMerId
	 *@Description:获取商户活动关联信息
	 *@param proid
	 *@param merId
	 *@return
	 *@Author:luckygroup
	 *@Date:2014-8-24下午12:12:27
	 */
	
	public MerPromotionDTO findByProIdAndMerId(Integer proid, String merId) {
		MerPromotionDTO merPromotionDTO = new MerPromotionDTO();
		merPromotionDTO = merPromotionDao.findByProIdAndMerId(proid, merId);
		
		return merPromotionDTO;
	}

	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.MerPromotionService#findByProIdAndMerId(java.lang.Integer, java.lang.String)
	 *@MethodName:findByProIdAndMerId
	 *@Description:获取商户活动关联信息
	 *@param proid
	 *@return
	 *@Author:luckygroup
	 *@Date:2014-8-24下午12:12:27
	 */
	
	public MerPromotionDTO findByProId(Integer proid) {
		MerPromotionDTO merPromotionDTO = new MerPromotionDTO();
		merPromotionDTO = merPromotionDao.findByProId(proid);
		
		return merPromotionDTO;
	}

	

	
	
	

	
}
