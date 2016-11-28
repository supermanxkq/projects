package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Logistics;
import com.paySystem.ic.dao.base.LogisticsDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.LogisticsDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 物流信息Dao实现类
 * 
 * @ClassName:PaySerParamDaoImpl
 * @Description:物流信息Dao实现类
 * @date: 2014-7-21上午10:49:02
 * @author: 赵瑞群
 * @version: V1.0
 */
@Repository(LogisticsDao.LOGISTICSDAO)
public class LogisticsDaoImpl extends DaoSupport<Logistics> implements LogisticsDao {

	
	/**
	 *  物流对象保存方法
	 *  
	 *@Title:saveLogistics
	 *@Description: 保存物流信息
	 *@param:@param logisticDTO 物流信息对象
	 *@Return:void  
	 *@author:      赵瑞群
	 *@Thorws:
	 */
	public void saveLogistics(LogisticsDTO logisticsDTO) {
		
		Logistics logistics = this.DTO2Bean(logisticsDTO);
		
		
		if(logisticsDTO.getLogistId()==null){
			
			logistics.setCreateTime(new Date());
			logistics.setUpdateTime(new Date());
				
			this.save(logistics);
		}else{
			logistics.setUpdateTime(new Date());
			this.update(logistics);
		}
		
	    
	}
	


	/**
	 *@Title:queryLogisticsByCond
	 *@Description:根据条件查询物流公司信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param LogisticDTO logisticDTO对象
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<LogisticsDTO> 返回DTO集合
	 *@author:  赵瑞群
	 * @throws Exception 
	 *@Thorws:
	 */
	public QueryResult<LogisticsDTO> queryLogisticsByCond(int firstPage, int pageNum,
			LogisticsDTO logisticsDTO, LinkedHashMap<String, String> orderBy) throws Exception {
		
		QueryResult<LogisticsDTO> dtoResult
		                            = new QueryResult<LogisticsDTO>();
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>(); 
		
		//如果物流公司编号不为空，则追加查询条件
		if(logisticsDTO.getLogistId()!=null&&StringUtils.isNotBlank(logisticsDTO.getLogistId().toString())){
			sb.append(" and o.logistId = ?").append(params.size()+1);
			params.add(logisticsDTO.getLogistId());
		}
		
		//如果物流公司名称不为空，则追加查询条件
		if(StringUtils.isNotBlank(logisticsDTO.getLogistName())){
			sb.append(" and o.logistName like ?").append(params.size()+1);
			params.add("%"+logisticsDTO.getLogistName()+"%");
		}
		
		if(logisticsDTO.getStatus()!=null){
			sb.append(" and o.status = ?").append(params.size()+1);
			params.add(logisticsDTO.getStatus());
		}

		
		//执行查询，获取QueryResult信息
		QueryResult<Logistics> result =
			getScrollData(firstPage, pageNum, sb.toString(), params.toArray(),orderBy);
		
		
		dtoResult = this.dtoResult2beanResult(result);
		
		return dtoResult;
	}


	
	/**
	 *@Title:DTO2Bean
	 *@Description: Bean 转 DTO
	 *@param:@param logisticsDTO
	 *@param:@return
	 *@return:Logistics
	 *@author:赵瑞群
	 *@Thorws:
	 */
	private Logistics DTO2Bean(LogisticsDTO logisticsDTO){
		
		Logistics logistics = new Logistics();
		
		UserSession us = Utils.getUserSession();
		
		if(logisticsDTO!=null){
		   logistics.setLogistId(logisticsDTO.getLogistId());
		   logistics.setLogistName(logisticsDTO.getLogistName());
		   logistics.setStatus(logisticsDTO.getStatus());
		   logistics.setUrl(logisticsDTO.getUrl());
		   logistics.setCreateTime(logisticsDTO.getCreateTime());
		   logistics.setUpdateTime(logisticsDTO.getUpdateTime());
		   logistics.setOperatorId(us.getUserName());
			
		}
		return logistics;
	}
	
	
	/**
	 *   实体Bean 转 DTO
	 *   
	 *@Title:bean2DTO
	 *@Description: 实体Bean 转 DTO
	 *@param:@param logistics 物流公司实体对象
	 *@param:@return
	 *@return:LogisticsDTO 物流公司DTO对象
	 *@author:赵瑞群
	 *@Thorws:
	 */
	private LogisticsDTO bean2DTO(Logistics logistics){
		
		LogisticsDTO logisticsDTO = new LogisticsDTO();
		
		if(logistics!=null){
			
			logisticsDTO.setLogistId(logistics.getLogistId());
			logisticsDTO.setLogistName(logistics.getLogistName());
			logisticsDTO.setUrl(logistics.getUrl());
			logisticsDTO.setCreateTime(logistics.getCreateTime());
			logisticsDTO.setUpdateTime(logistics.getUpdateTime());
			logisticsDTO.setOperatorId(logistics.getOperatorId());
			logisticsDTO.setStatus(logistics.getStatus());
		
		}
		
		return logisticsDTO;
	}
	
	/**
	 * BeanResult转 实体DTOResult
	 * 
	 *@Title:dtoResult2beanResult
	 *@Description:BeanResult转 实体DTOResult
	 *@param:@param result
	 *               实体BeanResult
	 *@param:@return
	 *@return:QueryResult<PaySerParamDTO>
	 *              DTO类型Result信息
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	private QueryResult<LogisticsDTO> dtoResult2beanResult(QueryResult<Logistics> result){
		
		
		QueryResult<LogisticsDTO> dtoResult 
		                            = new QueryResult<LogisticsDTO>();
		List<LogisticsDTO> dtoList
		                            = new ArrayList<LogisticsDTO>();
		
		if(result!=null){
			
			//遍历实体Result信息，获取支付参数实体对象
			for (Logistics logistics : result.getResultlist()){
			
				//将支付参数实体对象转为支付参数DTO对象
				LogisticsDTO logisticsDTO = new LogisticsDTO();
				logisticsDTO = bean2DTO(logistics); 
			    //添加到DTO对象集合中
			    dtoList.add(logisticsDTO);
			    
			}
			
		}
		//组装DtoResult信息  DTO对象集合及总条数
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(result.getTotalrecord());
		
		return dtoResult;
	}


	/**
	 *  获取物流公司信息
	 *  
	 *@Title:findById
	 *@Description:根据Id获取物流公司Dto对象
	 *@param:@param logistId 物流公司ID
	 *@param:@return
	 *@return:LogisticsDTO 物流公司DTO对象
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public LogisticsDTO findById(Integer logistId) {
		
		Logistics logistics = new Logistics();
		
		LogisticsDTO logisticsDTO = new LogisticsDTO();
		//根据ID获取物流公司实体对象
		logistics = this.find(logistId);
		//Bean2DTO
		logisticsDTO = this.bean2DTO(logistics);
		
		return logisticsDTO;
	}


	/**
	 *   删除物流公司信息（逻辑删除）
	 *   
	 *@Title:delLogistics
	 *@Description:  删除物流公司信息
	 *@param:@param logistId
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public LogisticsDTO delLogistics(Integer logistId) throws Exception {
		
		//根据Id获取物流实体对象
		Logistics logistics = this.find(logistId);
		
		//更改状态为"9"删除状态
		logistics.setStatus(9);
				
		//更新修改内容，实现逻辑删除操作
		this.update(logistics);
		
		//Bean2DTO
		LogisticsDTO logisticsDto = this.bean2DTO(logistics);
		
		
		return logisticsDto;
		
	}


	/**
	 *  检查名称是否已存在
	 *  
	 *@Title:checkSameName
	 *@Description:  检查物流公司名称是否已存在，如存在返回 false
	 *@param:@param logistName 物流公司名称
	 *@param:@return
	 *@Return:boolean
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public boolean checkSameName(String logistName) {
		
		String sql = "from Logistics where logistName=?1";
			
		Query query = em.createQuery(sql);
		
		query.setParameter(1,logistName);
			
		List logistList = query.getResultList();
		
		return logistList.size()<1;
	}


	/**
	 * 根据状态查询物流公司
	 *@Title: queryLogisticsList
	 *@Description:  根据状态查询物流公司
	 *@param:@param status 物流公司状态
	 *@Return: QueryResult 物流公司结果集
	 *@author: Jacky
	 *@Thorws:
	 */
	public QueryResult<LogisticsDTO> queryLogisticsList(int status) {
		List<Logistics> logiList = em.createQuery("from Logistics where status = ?").setParameter(1, status).getResultList();
		
		QueryResult<LogisticsDTO> dtoResult 
		        = new QueryResult<LogisticsDTO>();
		List<LogisticsDTO> logiDTOList = new ArrayList<LogisticsDTO>();
		if(CollectionUtils.isNotEmpty(logiList)){
			for (Logistics logistics : logiList){
				LogisticsDTO logisticsDTO = new LogisticsDTO();
				logisticsDTO = bean2DTO(logistics); 
				logiDTOList.add(logisticsDTO);
			
			}
		}
		dtoResult.setResultlist(logiDTOList);
		dtoResult.setTotalrecord(logiList.size());
		return dtoResult;
	}

}
