package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Logistics;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.LogisticsDTO;


/**
 * @ClassName:PaySerParamDao
 * @Description:物流公司信息Dao
 * @date: 2014-7-21上午10:46:46
 * @author: 赵瑞群
 * @version: V1.0
 */
public interface LogisticsDao extends DAO<Logistics> {
	
	

	/**
	 *@Title:saveLogistics
	 *@Description: 保存物流公司信息
	 *@param:@param paySerParam 物流公司DTO对象
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
	 *@return:List<PaySerParamDTO> 返回DTO集合
	 *@author:  赵瑞群
	 *@Thorws:
	 */
	public QueryResult<LogisticsDTO> queryLogisticsByCond(int firstPage, int pageNum,
			LogisticsDTO logisticsDTO, LinkedHashMap<String, String> orderBy)throws Exception;
	
	/**
	 *  获取物流公司信息
	 *@Title:findById
	 *@Description:根据Id获取物流公司信息Dto对象
	 *@param:@param logistId
	 *@param:@return
	 *@return:PaySerParamDTO
	 *@author:谢洪飞
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
	public LogisticsDTO delLogistics(Integer logisticsId) throws Exception;
	
	/**
	 *  检查物流公司名称是否已存在
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
	 *@Title: queryLogisticsList
	 *@Description:  根据状态查询物流公司
	 *@param:@param status 物流公司状态
	 *@Return: QueryResult 物流公司结果集
	 *@author: Jacky
	 *@Thorws:
	 */
	public QueryResult<LogisticsDTO> queryLogisticsList(int status);
	
	public static final String LOGISTICSDAO ="logisticsDao";
	
}
