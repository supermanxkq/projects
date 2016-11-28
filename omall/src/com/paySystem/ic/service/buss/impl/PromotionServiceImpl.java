package com.paySystem.ic.service.buss.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.buss.MerApplyRecordDao;
import com.paySystem.ic.dao.buss.MerPromotionDao;
import com.paySystem.ic.dao.buss.PromotionDao;
import com.paySystem.ic.service.buss.PromotionService;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.buss.MerApplyRecordDTO;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;
import com.paySystem.ic.web.dto.buss.PromotionDTO;
import com.paySystem.ic.web.dto.system.UserSession;
/**  
 * @Title: PromotionServiceImpl.java
 * @Package: com.paySystem.ic.bean.promotion
 * @Description: 活动管理业务实现类
 * @Author: 赵瑞群
 * @Date: 2014-7-21 下午3:14:09
 * @Version: V1.0  
 */
@Service(PromotionService.PROMOTIONSERV)
public class PromotionServiceImpl implements PromotionService{

	@Resource
	PromotionDao promotionDao;
	
	@Resource
	MerPromotionDao merPromotionDao;
	
	@Resource
	MerApplyRecordDao merApplyRecordDao;

	/**
	 *@Title:savePromotion
	 *@Description: 保存活动管理信息
	 *@param:@param promotionDTO 活动管理DTO对象
	 *@Return:void  
	 *@author:      赵瑞群
	 *@Thorws:
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void savePromotion(PromotionDTO promotionDTO,MerPromotionDTO merPromotionDTO) throws Exception { 
		
		if(promotionDTO.getProImg()!=null && StringUtils.isNotBlank(promotionDTO.getProImgFileName())) {
			/** 上传文件 **/ 
			String fileName = UploadUtil.uploadImg(promotionDTO.getProImg(),promotionDTO.getProImgFileName());
			/** 设置文件名 **/
			promotionDTO.setProImgFileName(fileName);
		}
		
		int proId = promotionDao.savePromotion(promotionDTO);
		UserSession us = Utils.getUserSession();
		
		if(us.getUserLevel()==2){
			merPromotionDTO.setMerid(us.getMerId());
			merPromotionDTO.setProid(proId);
			merPromotionDao.saveMerPromotion(merPromotionDTO);
		}
		
	}
	
	/**
	 *@Title:queryPromotionByCond
	 *@Description:根据条件活动管理信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param promotionDTO PromotionDTO对象
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<PromotionDTO> 返回DTO集合
	 *@author:  赵瑞群
	 * @throws Exception 
	 *@Thorws:
	 */
	public QueryResult<PromotionDTO> queryPromotionByCond(int firstPage, int pageNum,
			PromotionDTO promotionDTO, LinkedHashMap<String, String> orderBy) throws Exception{
		
		QueryResult<PromotionDTO> dtoResult =
				promotionDao.queryPromotionByCond(firstPage, pageNum, promotionDTO, orderBy);
		
		return dtoResult;
	}

	
	/**
	 *  获取活动管理信息
	 *@Title:findById
	 *@Description:根据Id获取活动管理信息Dto对象
	 *@param:@param logistId 接口Id
	 *@param:@return
	 *@return:PromotionDTO
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO findById(Integer proid) {
		
		PromotionDTO promotionDTO = promotionDao.findById(proid);
		
		return promotionDTO;
	}

	/**
	 *   删除活动管理信息（逻辑删除）
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param logistId
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public PromotionDTO delPromotion(Integer proid) throws Exception {
		PromotionDTO promotionDTO = promotionDao.delPromotion(proid);
		return promotionDTO;
	}

	
	/**
	 *  检查名称是否已存在
	 *@Title:checkSameName
	 *@Description:  检查活动管理名称是否已存在，如存在返回 false
	 *@param:@param logistName 活动管理名称
	 *@param:@return
	 *@Return:boolean
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public boolean checkSameName(String proname) {
		
		return promotionDao.checkSameName(proname);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.PromotionService#findCountMerByProId(int)
	 *@MethodName:findCountMerByProId
	 *@Description:根据活动编码查询参加的商户的数量
	 *@param proid
	 *@return
	 *@Author:luckygroup
	 *@Date:2014-8-23下午6:26:04
	 */
	
	public long findCountMerByProId(int proid) {
		long count = (Long)merPromotionDao.findCountMerByProId(proid);
		return count;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.PromotionService#findCountGoodsByProId(int)
	 *@MethodName:findCountGoodsByProId
	 *@Description:根据活动编码查询参与商品的数量
	 *@param proid
	 *@return
	 *@Author:luckygroup
	 *@Date:2014-8-23下午6:51:49
	 */
	
	public long findCountGoodsByProId(int proid) {
		long count = (Long)promotionDao.findGoodsCountByProid(proid);
		return count;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.PromotionService#openRecruit(java.lang.Integer)
	 *@MethodName:openRecruit
	 *@Description:开始招募
	 *@param proid
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午5:26:52
	 */
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public PromotionDTO openRecruit(Integer proid) throws Exception {
		
		PromotionDTO promotionDTO = promotionDao.openRecruit(proid);
		return promotionDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.PromotionService#stopRecruit(java.lang.Integer)
	 *@MethodName:stopRecruit
	 *@Description:停止招募
	 *@param proid
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午5:26:52
	 */
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public PromotionDTO stopRecruit(Integer proid) throws Exception {
		
		PromotionDTO promotionDTO = promotionDao.stopRecruit(proid);
		return promotionDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.PromotionService#stopPromotion(java.lang.Integer)
	 *@MethodName:stopPromotion
	 *@Description:终止活动
	 *@param proid
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午5:26:52
	 */
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public PromotionDTO stopPromotion(Integer proid) throws Exception {
		
		PromotionDTO promotionDTO = promotionDao.stopPromotion(proid);
		return promotionDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.PromotionService#beginPromotion(java.lang.Integer)
	 *@MethodName:beginPromotion
	 *@Description:开始活动
	 *@param proid
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午5:26:52
	 */
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public PromotionDTO beginPromotion(Integer proid) throws Exception {
		
		PromotionDTO promotionDTO = promotionDao.beginPromotion(proid);
		return promotionDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.PromotionService#saveApplyMer(com.paySystem.ic.web.dto.buss.MerPromotionDTO, com.paySystem.ic.web.dto.buss.MerApplyRecordDTO)
	 *@MethodName:saveApplyMer
	 *@Description： 保存参与活动的商户信息
	 *@param merPromotionDTO
	 *@param merApplyRecordDTO
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午11:29:02
	 */
	
	public void saveApplyMer(MerPromotionDTO merPromotionDTO,
			MerApplyRecordDTO merApplyRecordDTO) throws Exception {
		merPromotionDao.saveMerPromotion(merPromotionDTO);
		merApplyRecordDao.saveMerApplyRecord(merApplyRecordDTO);	
		
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.PromotionService#queryApplyRecordByCond(int, int, com.paySystem.ic.web.dto.buss.MerApplyRecordDTO, java.util.LinkedHashMap)
	 *@MethodName:queryApplyRecordByCond
	 *@Description:获取申请商户数
	 *@param firstPage
	 *@param pageNum
	 *@param merApplyRecordDTO
	 *@param orderBy
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-25下午10:16:40
	 */
	
	public QueryResult<List> queryApplyRecordByCond(int firstPage, int pageNum,
			MerApplyRecordDTO merApplyRecordDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<List> dtoResult = promotionDao.queryApplyRecordByCond(firstPage, pageNum, merApplyRecordDTO, orderBy);
		
		return dtoResult;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.PromotionService#saveApplyMer(com.paySystem.ic.web.dto.buss.MerPromotionDTO, com.paySystem.ic.web.dto.buss.MerApplyRecordDTO)
	 *@MethodName:saveApplyMer
	 *@Description： 修改结束时间
	 *@param merPromotionDTO
	 *@param merApplyRecordDTO
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-24下午11:29:02
	 */
	
	public void updateEndTime(PromotionDTO promotionDTO) {
		promotionDao.updateEndTime(promotionDTO);
		
	}

	/**
	 *@MethodName:ajaxObject
	 *@Description:验证广告对象
	 *@param id
	 *@Author:孟凡岭
	 *@Date:2014-12-8上午11:30:07
	 */
	public boolean ajaxObject(String id) {
		// TODO Auto-generated method stub
		return promotionDao.ajaxObject(id);
	}
	
	

	
}
