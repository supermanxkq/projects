package com.paySystem.ic.service.buss.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.buss.DeliveryModeDao;
import com.paySystem.ic.service.buss.DeliveryModeService;
import com.paySystem.ic.web.dto.buss.DeliveryModeDTO;

/**  
 * @ClassName: DeliveryModeServiceImpl.java
 * @Description:配送方式service实现类
 * @Author:yanwuyang 
 * @Date: 2014-7-20 上午08:53:57
 * @Version: V1.0  
 */

@Service(DeliveryModeService.DELIVERYMODESERV)
public class DeliveryModeServiceImpl implements DeliveryModeService {
	
	@Resource
	private DeliveryModeDao deliveryModeDao;
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.DeliveryModeService#save(com.paySystem.ic.web.dto.buss.DeliveryModeDTO)
	 *@MethodName:save
	 *@Description:保存
	 *@param deliveryModeDTO
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午06:07:54
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void save(DeliveryModeDTO deliveryModeDTO) {
		deliveryModeDao.save(deliveryModeDTO);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.DeliveryModeService#query(int, int, com.paySystem.ic.web.dto.buss.DeliveryModeDTO, java.util.LinkedHashMap)
	 *@MethodName:query
	 *@Description:查询
	 *@param firstPage
	 *@param pageNum
	 *@param deliveryModeDTO
	 *@param orderBy
	 *@return
	 *@throws Exception
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午06:08:20
	 */
	public QueryResult<DeliveryModeDTO> query(int firstPage, int pageNum,
			DeliveryModeDTO deliveryModeDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return deliveryModeDao.query(firstPage, pageNum, deliveryModeDTO, orderBy);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.DeliveryModeService#delete(java.lang.Integer)
	 *@MethodName:delete
	 *@Description: 删除
	 *@param id
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午06:08:33
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void delete(Integer id) {
		deliveryModeDao.delete(id);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.DeliveryModeService#findById(java.lang.Integer)
	 *@MethodName:findById
	 *@Description:根据ID查询配送方式
	 *@param id
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午06:08:44
	 */
	public DeliveryModeDTO findById(Integer id) {
		return deliveryModeDao.findById(id);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.buss.DeliveryModeService#checkSameName(java.lang.String)
	 *@MethodName:checkSameName
	 *@Description:检查名称是否存在
	 *@param name
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午10:05:20
	 */
	public boolean checkName(String name) {
		return deliveryModeDao.checkName(name);
	}
	

}

