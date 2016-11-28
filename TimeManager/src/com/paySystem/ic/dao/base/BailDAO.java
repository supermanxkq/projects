package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Bail;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.BailDTO;

public interface BailDAO extends DAO<Bail> {
	public static final String BIALDAO = "BAILDAO";

	/***
	 * 
	 *@Title:save
	 *@Description:通过DTO实体保存保证金信息
	 *@param:@param bailDTO
	 *@return:void
	 *@author:井建国
	 *@thorws:
	 */
	public void saveBail(Bail bail);

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
	public void update(Bail bail);

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
	public QueryResult<Bail> queryMerByCond(int firstindex, int maxresult,
			BailDTO bailDTO, LinkedHashMap<String, String> orderby)
			throws Exception;

	/***
	 * 
	 *@Title:findByBailId
	 *@Description:根据保证金Id查询实体
	 *@param:@param bailId
	 *@param:@return
	 *@return:Bail
	 *@author:井建国
	 *@thorws:
	 */
	public Bail findByBailId(Integer bailId);

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
	 *@Description:根据油厂编号获取保证金
	 *@param:@param factId
	 *@param:@return
	 *@return:BailDTO
	 *@author:张亚运
	 *@thorws:
	 */
	public BailDTO findBailByFactId(String factId);
}
