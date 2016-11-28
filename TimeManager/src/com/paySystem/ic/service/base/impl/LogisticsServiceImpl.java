package com.paySystem.ic.service.base.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.base.LogisticsDao;
import com.paySystem.ic.service.base.LogisticsService;
import com.paySystem.ic.web.dto.base.LogisticsDTO;
/**  
 * @Title: LogisticsServiceImpl.java
 * @Package: com.paySystem.ic.bean.logistics
 * @Description: 物流公司业务实现类
 * @Author: 赵瑞群
 * @Date: 2014-7-21 下午3:14:09
 * @Version: V1.0  
 */
@Service(LogisticsService.LOGISTICSSERV)
public class LogisticsServiceImpl implements LogisticsService{

	@Resource
	LogisticsDao logisticsDao;

	/**
	 *@Title:saveLogistics
	 *@Description: 保存物流公司信息
	 *@param:@param logisticsDTO 物流公司DTO对象
	 *@Return:void  
	 *@author:      赵瑞群
	 *@Thorws:
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void saveLogistics(LogisticsDTO logisticsDTO) {
		
		logisticsDao.saveLogistics(logisticsDTO);
		
	}
	
	/**
	 *@Title:queryLogisticsByCond
	 *@Description:根据条件物流公司信息列表
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
			LogisticsDTO logisticsDTO, LinkedHashMap<String, String> orderBy) throws Exception{
		
		QueryResult<LogisticsDTO> dtoResult =
				logisticsDao.queryLogisticsByCond(firstPage, pageNum, logisticsDTO, orderBy);
		
		return dtoResult;
	}

	
	/**
	 *  获取物流公司信息
	 *@Title:findById
	 *@Description:根据Id获取物流公司信息Dto对象
	 *@param:@param logistId 接口Id
	 *@param:@return
	 *@return:LogisticsDTO
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public LogisticsDTO findById(Integer logistId) {
		
		LogisticsDTO logisticsDto = logisticsDao.findById(logistId);
		
		return logisticsDto;
	}

	/**
	 *   删除物流公司信息（逻辑删除）
	 *@Title:delLogistics
	 *@Description:
	 *@param:@param logistId
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public LogisticsDTO delLogistics(Integer logistId) throws Exception {
		LogisticsDTO logisticsDto = logisticsDao.delLogistics(logistId);
		return logisticsDto;
	}

	
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
	public boolean checkSameName(String logistName) {
		
		return logisticsDao.checkSameName(logistName);
	}

	/**
	 * 根据状态查询物流公司
	 *@Title: getLogisticsList
	 *@Description:  根据状态查询物流公司
	 *@Return: List<LogisticsDTO> 物流公司结果集
	 *@author: Jacky
	 *@Thorws:
	 */
	public List<LogisticsDTO> getLogisticsList() {
		/** 只查询已经被启用的物流公司  **/
		QueryResult<LogisticsDTO> queryResult = logisticsDao.queryLogisticsList(1);
		if(queryResult.getTotalrecord() > 0) {
			return queryResult.getResultlist();
		}
		return null;
	}

}
