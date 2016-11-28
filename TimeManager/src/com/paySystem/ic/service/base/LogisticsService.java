package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.base.LogisticsDTO;

/**  
* @Title: LogisticsService.java
* @Package: com.paySystem.ic.service.base
* @Description: 物流公司服务接口
* @Author: 赵瑞群
* @Date: 2014-08-01
* @Version: V1.0  
*/
public interface LogisticsService {

	public static final String LOGISTICSSERV = "logisticsService";
	
	/**
	 *@Title:saveLogistics
	 *@Description: 保存物流公司信息
	 *@param:@param LogisticsDTO 物流公司DTO对象
	 *@Return:void  
	 *@author:      赵瑞群
	 *@Thorws:
	 */
	public void saveLogistics(LogisticsDTO logisticsDTO);


	/**
	 *@Title:queryLogisticsByCond
	 *@Description:根据条件查询物流公司信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param logisticsDTO LogisticsDTO对象
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<LogisticsDTO> 返回DTO集合
	 *@author:  赵瑞群
	 * @throws Exception 
	 *@Thorws:
	 */
	public QueryResult<LogisticsDTO> queryLogisticsByCond(int firstPage, int pageNum,
			LogisticsDTO logisticsDTO, LinkedHashMap<String, String> orderBy) throws Exception;
	
	
	/**
	 *  获取物流公司信息
	 *@Title:findById
	 *@Description:根据Id获取物流公司Dto对象
	 *@param:@param logistId
	 *@param:@return
	 *@return:LogisticsDTO
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public LogisticsDTO findById(Integer logistId);
	
	/**
	 *   删除物流公司信息（逻辑删除）
	 *@Title:delLogistics
	 *@Description:
	 *@param:@param logistId
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public LogisticsDTO delLogistics(Integer logistId) throws Exception;

	
	/**
	 *  检查名称是否已存在
	 *@Title:checkSameName
	 *@Description:  检查物流公司名称是否已存在，如存在返回 false
	 *@param:@param logistName 物流公司名称
	 *@param:@return
	 *@Return:boolean
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public boolean checkSameName(String logistName);
	
	/**
	 * 根据状态查询物流公司
	 *@Title: getLogisticsList
	 *@Description:  根据状态查询物流公司
	 *@Return: List<LogisticsDTO> 物流公司结果集
	 *@author: Jacky
	 *@Thorws:
	 */
	public List<LogisticsDTO> getLogisticsList();

}
