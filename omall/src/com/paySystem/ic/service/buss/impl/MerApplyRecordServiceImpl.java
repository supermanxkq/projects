package com.paySystem.ic.service.buss.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.dao.buss.MerApplyRecordDao;
import com.paySystem.ic.service.buss.MerApplyRecordService;
import com.paySystem.ic.web.dto.buss.MerApplyRecordDTO;
/**  
 * @Title: PromotionServiceImpl.java
 * @Package: com.paySystem.ic.bean.promotion
 * @Description: 活动管理业务实现类
 * @Author: 赵瑞群
 * @Date: 2014-7-21 下午3:14:09
 * @Version: V1.0  
 */
@Service(MerApplyRecordService.MERAPPLYRECORDSERV)
public class MerApplyRecordServiceImpl implements MerApplyRecordService{

	
	
	@Resource
	MerApplyRecordDao merApplyRecordDao;

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.MerPromotionService#findByProIdAndMerId(java.lang.Integer, java.lang.String)
	 *@MethodName:findByProIdAndMerId
	 *@Description:获取审核记录
	 *@param proid
	 *@param merId
	 *@return
	 *@Author:luckygroup
	 *@Date:2014-8-24下午12:12:27
	 */
	

	public MerApplyRecordDTO findByProIdAndMerIdNew(Integer proid, String merId) {
		
		MerApplyRecordDTO merApplyRecordDTO = new MerApplyRecordDTO();
		merApplyRecordDTO = merApplyRecordDao.findByProIdAndMerIdNew(proid, merId);
		
		return merApplyRecordDTO;
	}

	/**
	 *   审核通过
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public void passAudit(String recordIds) throws Exception {
    	merApplyRecordDao.passAudit(recordIds);
		
	}
    
    

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.MerApplyRecordService#passAllAudit(int)
	 *@MethodName:passAllAudit
	 *@Description:全部审核通过
	 *@param proid
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-26下午10:33:55
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void passAllAudit(int proid) throws Exception {
		merApplyRecordDao.passAllAudit(proid);
		
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.MerApplyRecordService#refuseAudit(java.lang.String, com.paySystem.ic.web.dto.buss.MerApplyRecordDTO)
	 *@MethodName:refuseAudit
	 *@Description:TODO
	 *@param recordIds
	 *@param merApplyRecordDTO
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-27上午12:14:29
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void refuseAudit(String recordIds,
			MerApplyRecordDTO merApplyRecordDTO) throws Exception {
		
		merApplyRecordDao.refuseAudit(recordIds, merApplyRecordDTO);
	}
	
	

	
}
