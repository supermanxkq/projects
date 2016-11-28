package com.paySystem.ic.service.buss;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.buss.DeliveryModeDTO;

/**  
 * @ClassName: DeliveryModeService.java
 * @Description:配送方式方式service接口
 * @Author:yanwuyang 
 * @Date: 2014-7-20 上午08:52:18
 * @Version: V1.0  
 */
public interface DeliveryModeService {
	
	public final static String DELIVERYMODESERV=" deliveryModeService";
	
	/**
	 * 
	 *@Title:save
	 *@Description:保存
	 *@param deliveryModeDTO
	 *@Return:void
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午06:06:01
	 */
	public void save(DeliveryModeDTO deliveryModeDTO);
	
	/**
	 * 
	 *@Title:query
	 *@Description:根据条件查询配送方式
	 *@param firstPage 开始页数
	 *@param pageNum 每页显示条数
	 *@param deliveryModeDTO
	 *@param orderBy 排序字段及规则
	 *@return
	 *@throws Exception
	 *@Return:QueryResult<DeliveryModeDTO>
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午06:06:09
	 */
	public QueryResult<DeliveryModeDTO> query(int firstPage, int pageNum,
			DeliveryModeDTO deliveryModeDTO, LinkedHashMap<String, String> orderBy) throws Exception;
	
	/**
	 * 
	 *@Title:delete
	 *@Description: 删除
	 *@param id
	 *@Return:void
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午06:06:50
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 *@Title:findById
	 *@Description:根据ID查询单个实体
	 *@param id
	 *@return
	 *@Return:DeliveryModeDTO
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午06:06:58
	 */
	public DeliveryModeDTO findById(Integer id);
	
	/**
	 * 
	 *@Title:checkSameName
	 *@Description:检查名称是否存在
	 *@param name
	 *@return
	 *@Return:boolean
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午10:04:48
	 */
	public boolean checkName(String name);

}

