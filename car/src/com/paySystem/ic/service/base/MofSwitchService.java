package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerOrgFuncSwitch;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerOrgFuncSwitchDTO;

/**
 *@ClassName:MofSwitchService
 *@Description:机构/商户功能设置Service接口
 *@Date:2013-11-30下午10:11:51
 *@Author:谢工
 *@Version: V1.0
 */
public interface MofSwitchService {
    
	public static final String MOFSWITCHSERVICE = "mofSwitchService";
	
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
	 *@Title:find
	 *@Description:根据编号查询 机构功能设置信息
	 *@param:@param mofId
	 *@param:@return
	 *@return:MerOrgFuncSwitch
	 *@thorws:
	 */
	MerOrgFuncSwitch find(String mofId);
}
