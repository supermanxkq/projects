package com.paySystem.ic.service.stock.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.stock.BussParamsConfig;
import com.paySystem.ic.dao.stock.BussParamsConfigDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.stock.BussParamsConfigService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.BussParamsConfigDTO;

/**
 * @ProjectName:omall
 * @ClassName:BussParamsConfigServiceImpl
 * @Description:业务参数设置
 * @date: 2014-10-14
 * @author: 王楠
 * @version: V1.0
 */
@Service(BussParamsConfigService.BUSSPARAMSCONFIGSERVICE)
public class BussParamsConfigServiceImpl extends DaoSupport<BussParamsConfig>
                                                implements BussParamsConfigService{
	@Resource
	BussParamsConfigDAO bussParamsConfigDAO;

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.stock.BussParamsConfigService#addSave(com.paySystem.ic.web.dto.stock.BussParamsConfigDTO)
	 *@MethodName:addSave
	 *@Description:保存业务配置信息
	 *@param bussParamsConfigDTO 商城业务参数配置实体的DTO
	 *@Author:王楠
	 *@Date:2014-10-14下午04:35:57
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(BussParamsConfigDTO bussParamsConfigDTO) {
 
		try {
			bussParamsConfigDAO.addBussParamsConfig(bussParamsConfigDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.stock.BussParamsConfigService#findList()
	 *@MethodName:findList
	 *@Description:查找数据的方法
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-10-15下午03:38:10
	 */
	public BussParamsConfigDTO findList() throws Exception {
				
		return bussParamsConfigDAO.findList();
	}
	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.stock.BussParamsConfigService#updateBussParamsConfig(com.paySystem.ic.web.dto.stock.BussParamsConfigDTO)
	 *@MethodName:updateBussParamsConfig
	 *@Description:修改业务参数配置信息
	 *@param bussParamsConfigDTO 业务参数配置信息实体的DTO
	 *@return
	 *@Author:王楠
	 *@Date:2014-10-15下午03:50:56
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO updateBussParamsConfig(BussParamsConfigDTO bussParamsConfigDTO) {
        ReturnDTO returnDTO =new ReturnDTO();
        try {
			returnDTO=bussParamsConfigDAO.updateBussParamsConfig(bussParamsConfigDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnDTO;
	}

}
