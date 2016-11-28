package com.paySystem.ic.dao.buss;


import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.DeliveryMode;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.buss.DeliveryModeDTO;



/**  
 * @ClassName: DeliveryModeDao.java
 * @Description:配送方式dao
 * @Author:yanwuyang 
 * @Date: 2014-7-19 下午11:03:10
 * @Version: V1.0  
 */

public interface DeliveryModeDao extends DAO<DeliveryMode>  {
	
	public final static String DELIVERYMODEDAO="deliveryModeDao";
	
	/**
	 * 
	 *@Title:save
	 *@Description:保存配送方式接口
	 *@Params:@param deliveryModeDTO
	 *@Return:void
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午05:57:15
	 */
	public void save(DeliveryModeDTO deliveryModeDTO);
	
	/**
	 * 
	 *@Title:query
	 *@Description:查询配送方式
	 *@param firstPage 开始页数
	 *@param pageNum 每页显示数量
	 *@param deliveryModeDTO
	 *@param orderBy 排序字段及规则
	 *@return
	 *@throws Exception
	 *@Return:QueryResult<DeliveryModeDTO>
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午05:58:26
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
	 *@Date:2014-7-26下午05:59:40
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 *@Title:findById
	 *@Description:根据主键查找单个配送方式
	 *@param id 主键ID
	 *@return
	 *@Return:DeliveryModeDTO
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午06:01:35
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

