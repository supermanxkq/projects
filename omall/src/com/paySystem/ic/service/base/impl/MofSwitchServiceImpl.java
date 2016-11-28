package com.paySystem.ic.service.base.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerOrgFuncSwitch;
import com.paySystem.ic.dao.base.MerOrgFunDao;
import com.paySystem.ic.service.base.MofSwitchService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerOrgFuncSwitchDTO;

/**
 *@ClassName:MofSwitchServiceBean
 *@Description:机构/商户功能开关Service实现类
 *@Date:2013-11-30下午10:14:25
 *@Author:谢工
 *@Version: V1.0
 */
@Service(MofSwitchService.MOFSWITCHSERVICE)
public class MofSwitchServiceImpl implements MofSwitchService
                        {

	@Resource
	MerOrgFunDao merOrgFunDao;
	/* (non-Javadoc)
	 * @see com.paySystem.ic.service.base.MofSwitchService#updateMof(com.paySystem.ic.web.dto.base.MerOrgFuncSwitchDTO)
	 * 更新机构商户功能设置信息
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO updateMof(MerOrgFuncSwitchDTO merOrgFuncSwitchDTO) {
		ReturnDTO dto = new ReturnDTO();
		dto = merOrgFunDao.updateMof(merOrgFuncSwitchDTO);
		return dto;
	}

	public QueryResult<MerOrgFuncSwitch> queryMerOrgSw(int fristindex,
			int pageNum, MerOrgFuncSwitchDTO merOrgFuncSwitchDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
	    QueryResult<MerOrgFuncSwitch> queryResult = merOrgFunDao.queryMerOrgSw(fristindex, pageNum, merOrgFuncSwitchDTO, orderBy);
		return queryResult;
	}

	public MerOrgFuncSwitch find(String mofId) {
		MerOrgFuncSwitch mof = merOrgFunDao.find(mofId);
		return mof;
	}
	
	

}
