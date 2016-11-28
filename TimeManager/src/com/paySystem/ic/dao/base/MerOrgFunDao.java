package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerOrgFuncSwitch;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerOrgFuncSwitchDTO;

/**
 * @ClassName:MerOrgFunDao
 * @Description:商户机构功能设置Dao接口
 * @date: 2013-12-7下午03:56:54
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerOrgFunDao extends DAO<MerOrgFuncSwitch> {
	
	/**
	 *@Title:updateMof
	 *@Description:更新商户机构设置信息
	 *@param:@param merOrgFuncSwitchDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@thorws:
	 */
	public ReturnDTO updateMof(MerOrgFuncSwitchDTO merOrgFuncSwitchDTO);
	
	/**
	 *@Title:queryMerOrgSw
	 *@Description:根据条件查询商户机构功能设置信息
	 *@param:@param fristindex
	 *@param:@param pageNum
	 *@param:@param terminalsDTO
	 *@param:@param orderBy
	 *@param:@return
	 *@return:QueryResult<MerOrgFuncSwitch>
	 * @throws Exception 
	 *@thorws:
	 */
	QueryResult<MerOrgFuncSwitch> queryMerOrgSw(int fristindex, int pageNum,
			MerOrgFuncSwitchDTO merOrgFuncSwitchDTO, LinkedHashMap<String, String> orderBy) throws Exception;
	
	
	/**
	 *@Title:saveMerOrgFuncSwitch
	 *@Description:保存商户机构功能设置信息--新增
	 *@param:@param merchantsDTO 商户DTO
	 *@param:@param organsDTO    机构DTO
	 *@param:@param flag         机构商户标志：0-机构，1-商户
	 *@param:@return
	 *@return:MerOrgFuncSwitch
	 *@thorws:
	 */
	MerOrgFuncSwitch saveMerOrgFuncSwitch(Merchants merchants,Organs organs,int flag);
	
	public static final String MERORGFUNDAO ="merOrgFunDao";
}
