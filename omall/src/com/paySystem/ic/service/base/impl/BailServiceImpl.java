package com.paySystem.ic.service.base.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Bail;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.dao.base.BailDAO;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.service.base.BailService;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.BailDTO;

/***
 * 
 * @ClassName:BailServiceImpl
 * @Description:保证金业务处理
 * @date: 2014-5-14下午05:37:19
 * @author: 井建国
 * @version: V1.0
 */
@Repository(BailService.BAILSERVICE)
public class BailServiceImpl implements BailService {
	
	@Resource BailDAO bailDAOImpl;
	@Resource OrgansDao organsDaoImpl;
	@Resource MerchantsDao merchantsDaoImpl;
	/****
	 * 
	 *@Title:getBail
	 *@Description:将DTO转换为实体
	 *@param:@param bailDTO
	 *@param:@return
	 *@return:Bail
	 *@author:井建国
	 *@thorws:
	 */
	public Bail getBail(BailDTO bailDTO){
		Bail bail = new Bail();
		bail.setBailAmt(bailDTO.getBailAmt());
		bail.setBailId(bailDTO.getBailId());
		bail.setBuyOilRate(bailDTO.getBuyOilRate());
		bail.setContractNo(bailDTO.getContractNo());
		bail.setCoopStatus(bailDTO.getCoopStatus());
		bail.setCoopTime(bailDTO.getCoopTime());
		bail.setMerOrgId(bailDTO.getMerOrgId());
		bail.setOrgOilId(bailDTO.getOrgOilId());
		bail.setPayTime(bailDTO.getPayTime());
		bail.setTypeSign(bailDTO.getTypeSign());
		bail.setDescr(bailDTO.getDescr());
		return bail;
	}
	
	/****
	 * 
	 *@Title:getBailDTO
	 *@Description:将实体转换为DTO
	 *@param:@param bail
	 *@param:@return
	 *@return:BailDTO
	 *@author:井建国
	 *@thorws:
	 */
	public BailDTO getBailDTO(Bail bail){
		BailDTO bailDTO = new BailDTO();
		bailDTO.setBailId(bail.getBailId());
		bailDTO.setBailAmt(bail.getBailAmt());
		bailDTO.setBuyOilRate(bail.getBuyOilRate());
		bailDTO.setCoopStatus(bail.getCoopStatus());
		bailDTO.setCoopStatusName(Utils.getOptionsIntegerName(OptionsValue.COOP_STATUE,bail.getCoopStatus()));
		bailDTO.setMerOrgId(bail.getMerOrgId());

	
		bailDTO.setPayTime(bail.getPayTime());
		bailDTO.setUpdateTime(bail.getUpdateTime());
		bailDTO.setDescr(bail.getDescr());
		return bailDTO;
	}

	/**
	 * 
	 * @see com.paySystem.ic.service.base.BailService#delete(com.paySystem.ic.web.dto.base.BailDTO)
	 * @Description:通过DTO实体删除相应的保证金信息
	 * @date: 2014-5-14下午09:37:52
	 * @author: 井建国
	 * @version: V1.0
	 * @param bailDTO
	 */
	public boolean delete(BailDTO bailDTO) {
		boolean flag = false;
		bailDAOImpl.update(bailDTO);
		flag=true;
		return flag;
	}

	/**
	 * 
	 * @see com.paySystem.ic.service.base.BailService#queryMerByCond(int, int, com.paySystem.ic.web.dto.base.BailDTO, java.util.LinkedHashMap)
	 * @Description:分页查询
	 * @date: 2014-5-14下午09:37:55
	 * @author: 井建国
	 * @version: V1.0
	 * @param firstindex
	 * @param maxresult
	 * @param bailDTO
	 * @param orderby
	 * @return
	 * @throws Exception
	 */
	public List<BailDTO> queryMerByCond(int firstindex, int maxresult,
			BailDTO bailDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		List<BailDTO> list = new ArrayList<BailDTO>();
		if(bailDTO.getMerOrgName().length()==0){
			bailDTO.setMerOrgName(null);
		}
		QueryResult<Bail> queryResult = bailDAOImpl.queryMerByCond(firstindex, maxresult, bailDTO, orderby);
		List<Bail> bailList = queryResult.getResultlist();
		for (int i = 0; i < bailList.size(); i++) {
			Bail bail = bailList.get(i);
			BailDTO bailDto = getBailDTO(bail);
			list.add(bailDto);
		}
		return list;
	}

	/**
	 * 
	 * @see com.paySystem.ic.service.base.BailService#save(com.paySystem.ic.web.dto.base.BailDTO)
	 * @Description:通过DTO实体保存相应的保证金信息
	 * @date: 2014-5-14下午09:38:02
	 * @author: 井建国
	 * @version: V1.0
	 * @param bailDTO
	 */
	public void save(BailDTO bailDTO) {
		bailDAOImpl.save(getBail(bailDTO));
	}

	/**
	 * 
	 * @see com.paySystem.ic.service.base.BailService#update(com.paySystem.ic.web.dto.base.BailDTO)
	 * @Description:通过DTO实体更新相应的保证金信息
	 * @date: 2014-5-14下午09:38:06
	 * @author: 井建国
	 * @version: V1.0
	 * @param bailDTO
	 * @return
	 */
	public ReturnDTO update(BailDTO bailDTO) {
		boolean flag = false;
		ReturnDTO dto = new ReturnDTO();
		bailDAOImpl.update(getBail(bailDTO));
		flag = true;
		dto.setFlag(flag);
		return dto;
	}
	
	/***
	 * 
	 *@Title:findByBail
	 *@Description:根据BailId查询DTO实体
	 *@param:@param bailId
	 *@param:@return
	 *@author:井建国
	 *@thorws:
	 *@see com.paySystem.ic.service.base.BailService#findByBail(java.lang.Integer)
	 */
	public BailDTO findByBail(Integer bailId) {
		BailDTO bailDTO = getBailDTO(bailDAOImpl.findByBailId(bailId));
		return bailDTO;
	}

	/**
	 *@Title:findByMerId
	 *@Description:根据商户编号获取保证金金额
	 *@param:@param merId
	 *@param:@return
	 *@return:BailDTO
	 *@author:谢洪飞
	 *@thorws:
	 */
	public BailDTO findBailByMerId(String merId) {
		BailDTO bailDTO = bailDAOImpl.findBailByMerId(merId);
		return bailDTO;
	}
 /**@Title:findBailByFactId
	 *@Description:根据油厂编号获取保证金金额
	 *@param:@param factId
	 *@param:@return
	 *@return:BailDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public BailDTO findBailByFactId(String factId) {
		BailDTO bailDTO = bailDAOImpl.findBailByFactId(factId);
		return bailDTO;
	}

}
