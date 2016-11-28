package com.paySystem.ic.service.base.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.dao.base.FreightSetDAO;
import com.paySystem.ic.service.base.FreightSetService;
import com.paySystem.ic.web.dto.base.FreightSetDTO;

@Service(FreightSetService.FREIGHTSETSERVICE)
public class FreightSetServiceImpl implements FreightSetService{

	@Resource
	FreightSetDAO freightSetDAO;
	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.FreightSetService#findFreightSet()
	 *@MethodName: findFreightSet
	 *@Description: 查询运费设置
	 *@throws Exception
	 *@Author: 廖晓远 
	 *@Date: 2014-11-14下午03:46:47
	 */
	@Override
	public FreightSetDTO findFreightSet() throws Exception {
		return freightSetDAO.findFreightSet();
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.FreightSetService#updateOrSave(com.paySystem.ic.web.dto.base.FreightSetDTO)
	 *@MethodName: updateOrSave
	 *@Description: 保存运费设置
	 *@param freightSetDTO
	 *@throws Exception
	 *@Author: 廖晓远 
	 *@Date: 2014-11-14下午03:46:47
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void updateOrSave(FreightSetDTO freightSetDTO) throws Exception {
		freightSetDAO.updateOrSave(freightSetDTO);
	}

}
