package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.BailDTO;

public interface BailService {

	public static final String BAILSERVICE = "BAILSERVICE";

	/***
	 * 
	 *@Title:save
	 *@Description:通过DTO实体保存保证金信息
	 *@param:@param bailDTO
	 *@return:void
	 *@author:井建国
	 *@thorws:
	 */
	public void save(BailDTO bailDTO);

	/***
	 * 
	 *@Title:update
	 *@Description:通过DTO实体保修改证金信息
	 *@param:@param bailDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:井建国
	 *@thorws:
	 */
	public ReturnDTO update(BailDTO bailDTO);

	/**
	 * 
	 *@Title:delete
	 *@Description:通过DTO实体保删除证金信息
	 *@param:@param bailDTO
	 *@return:void
	 *@author:井建国
	 *@thorws:
	 */
	public boolean delete(BailDTO bailDTO);

	/***
	 * 
	 *@Title:queryMerByCond
	 *@Description:分页查询
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param bailDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult
	 *@author:井建国
	 *@thorws:
	 */
	public List<BailDTO> queryMerByCond(int firstindex, int maxresult,
			BailDTO bailDTO, LinkedHashMap<String, String> orderby)
			throws Exception;

	/****
	 * 
	 *@Title:findByBail
	 *@Description:通过Id查询实体DTO
	 *@param:@param bailId
	 *@param:@return
	 *@return:BailDTO
	 *@author:井建国
	 *@thorws:
	 */
	public BailDTO findByBail(Integer bailId);

	/**
	 *@Title:findByMerId
	 *@Description:根据商户编号获取保证金金额
	 *@param:@param merId
	 *@param:@return
	 *@return:BailDTO
	 *@author:谢洪飞
	 *@thorws:
	 */
	public BailDTO findBailByMerId(String merId);

	/**
	 *@Title:findBailByFactId
	 *@Description:根据油厂编号获取保证金金额
	 *@param:@param factId
	 *@param:@return
	 *@return:BailDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public BailDTO findBailByFactId(String factId);

}
