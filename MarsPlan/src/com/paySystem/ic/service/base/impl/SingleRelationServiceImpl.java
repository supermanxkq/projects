package com.paySystem.ic.service.base.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.SingleRelation;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.SingleRelationDao;
import com.paySystem.ic.dao.card.CardBINDAO;
import com.paySystem.ic.service.base.SingleRelationService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.base.SingleRelationDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * 收单关系管理service实现
 * @author Administrator
 *
 */
@Service(SingleRelationService.SINRELSERVICE)
public class SingleRelationServiceImpl implements SingleRelationService 
                           {

	/**注入本serviceBean中需要用到的外部service
	 */
	@Resource SingleRelationDao singleRelationDao;
	@Resource CardBINDAO cardBinDao;
	/*@Resource MerSinRelationDao merSinRelationDao;*/
	@Resource MerchantsDao merchantsDao;
	
	
	/** (non-Javadoc)
	 * @see com.paySystem.ic.service.base.SingleRelationService#saveSingleRelation(com.paySystem.ic.web.dto.base.SingleRelationDTO)
	 * 保存收单关系记录
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SingleRelation saveSingleRelation(SingleRelationDTO singleRelationDTO) throws Exception {
		SingleRelation singleRelation = new SingleRelation();
		//保存收单关系信息
		singleRelation = singleRelationDao.saveSingleRelation(singleRelationDTO);
		Merchants merchants = merchantsDao.find(singleRelationDTO.getMerId());
		MerchantsDTO merchantsDTO = new MerchantsDTO();
		merchantsDTO.setIsSalePoints(singleRelationDTO.getIsSalePoints());
		merchantsDTO.setCardBins(singleRelationDTO.getCardBins());
		merchantsDTO.setCardBinStatuss(singleRelationDTO.getCardBinStatuss());
		//保存商户卡Bin关联关系
		/*merSinRelationDao.saveMerBinFromMerchantsDTO(merchantsDTO, merchants);*/
		return singleRelation;
	}

	/** (non-Javadoc)
	 * @throws Exception 
	 * @see com.paySystem.ic.service.base.SingleRelationService#updateSingleRelation(com.paySystem.ic.web.dto.base.SingleRelationDTO)
	 * 更新收单关系记录
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO updateSingleRelation(SingleRelationDTO singleRelationDTO) throws Exception {
		ReturnDTO returnDTO = new ReturnDTO();
		//更新收单关系信息
	    returnDTO = singleRelationDao.updateSingleRelation(singleRelationDTO);
		//更新商户卡BIN关联关系
	    Merchants merchants = merchantsDao.find(singleRelationDTO.getMerId());
		MerchantsDTO merchantsDTO = new MerchantsDTO();
		merchantsDTO.setOrganId(merchants.getOrgans().getOrganId());
		merchantsDTO.setMerId(merchants.getMerId());
		merchantsDTO.setIsSalePoints(singleRelationDTO.getIsSalePoints());
		merchantsDTO.setCardBins(singleRelationDTO.getCardBins());
		merchantsDTO.setCardBinStatuss(singleRelationDTO.getCardBinStatuss());
		//保存商户卡Bin关联关系
		/*merSinRelationDao.updateMerSinOthOrg(merchantsDTO,singleRelationDTO.getOrganId());*/
	    return returnDTO;
	}

	@SuppressWarnings("unchecked")
	public QueryResult querySingleRelByCond(int fristindex, int pageNum,
			SingleRelationDTO singleRelationDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult queryResult = singleRelationDao.querySingleRelByCond(fristindex, pageNum, singleRelationDTO, orderBy);
		
		return queryResult;
	}

	public SingleRelation find(String sinRelId) {
		SingleRelation singleRelation = singleRelationDao.find(sinRelId);
		return singleRelation;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteSingleRelation(String sinRelId) {
		SingleRelation singleRelation = singleRelationDao.find(sinRelId);
		singleRelation.setStatus(9);
		singleRelationDao.update(singleRelation);
	}

	/**
	 *@Title:findByMerIdBinId
	 *@Description:根据商户号与卡BIN号查找收单关系
	 *@param:@param merId
	 *@param:@param binId
	 *@param:@return
	 *@return:SingleRelation
	 *@author:谢
	 *@thorws:
	 */
	public SingleRelation findByMerIdBinId(String merId) {
		
		return singleRelationDao.findByMerIdBinId(merId);
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
		
		return singleRelationDao.checkExsisSinRel(singleRelationDTO);
	}

	public boolean checkExsisCardBin(String organId) {
		
		List<OptionsString> cardBinList = cardBinDao.getOptionByOrganId(organId);
		return cardBinList.size()>0;
	}
	
   
      
}
