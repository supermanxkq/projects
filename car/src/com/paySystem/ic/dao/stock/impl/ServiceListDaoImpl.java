package com.paySystem.ic.dao.stock.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ServiceList;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.stock.ServiceListDao;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.stock.ServiceListDTO;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigDaoImpl
 * @Description:短信服务器配置，添加、查看、更新记录接口实现
 * @date: 2014-7-22下午02:19:37
 * @author: 徐凯强
 * @version: V1.0
 */
@Repository(ServiceListDao.SERVICELISTDAO)
public class ServiceListDaoImpl extends DaoSupport<ServiceList> implements
		ServiceListDao {

	/**
	 *@Title:saveServiceList
	 *@Description:保存
	 *@param serviceListDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:22:21
	 */
	public void saveServiceList(ServiceListDTO serviceListDTO) {
		ServiceList serviceList = new ServiceList();
		try {
			serviceList = (ServiceList) EntityDtoConverter.dto2Bean(serviceListDTO, serviceList);
			this.save(serviceList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@Title:updateServiceList
	 *@Description:更新
	 *@param serviceListDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:23:02
	 */
	public void updateServiceList(ServiceListDTO serviceListDTO) {
		ServiceList serviceList = new ServiceList();
		try {
			this.update(EntityDtoConverter
					.dto2Bean(serviceListDTO, serviceList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@MethodName:queryAll
	 *@Description:分页查找
	 *@param firstIndex
	 *@param pageNum
	 *@param serviceListDTO
	 *@param orderBy
	 *@Return:QueryResult<ServiceListDTO>
	 *@Author:徐凯强
	 *@Date:2014-7-23下午07:40:56
	 */
	public QueryResult<ServiceListDTO> queryAll(int firstIndex, int pageNum,
			ServiceListDTO serviceListDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		QueryResult<ServiceListDTO> queryResultDTOList = new QueryResult<ServiceListDTO>();
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		/** 模糊查询 */
		if (serviceListDTO.getName() != null
				&& !serviceListDTO.getName().equals("")) {
			wherejpql.append(" and o.name=?");
			queryParams.add(serviceListDTO.getName());
		}
		if (serviceListDTO.getStatus() != null
				&& serviceListDTO.getStatus() != -1) {
			wherejpql.append(" and o.status=?");
			queryParams.add(serviceListDTO.getStatus());
		}
		if (serviceListDTO.getServType() != null
				&& serviceListDTO.getServType() != -1) {
			wherejpql.append(" and o.servType=?");
			queryParams.add(serviceListDTO.getServType());
		}

		QueryResult<ServiceList> queryResult = this.getScrollData(firstIndex,
				pageNum, wherejpql.toString(), queryParams.toArray(), orderBy);
		/** 将实体queryResult转换为DTOqueryResult */
		List<ServiceList> listEntity = queryResult.getResultlist();
		List<ServiceListDTO> listDtos = changeListToDtoList(listEntity);
		/** 赋值QueryResult<MessServParamConfigDTO> */
		queryResultDTOList.setResultlist(listDtos);
		queryResultDTOList.setTotalrecord(queryResult.getTotalrecord());
		return queryResultDTOList;
	}

	/**
	 *@Title:changeListToDtoList
	 *@Description:将实例queryResult转换为DTOQueryResult
	 *@param listEntity
	 *@Return:List<ServiceListDTO>
	 *@author:徐凯强
	 *@Date:2014-7-23下午08:34:02
	 */
	public List<ServiceListDTO> changeListToDtoList(List<ServiceList> listEntity) {
		List<ServiceListDTO> listDtos = new ArrayList<ServiceListDTO>();

		for (int i = 0; i < listEntity.size(); i++) {
			ServiceListDTO serviceListDTO = new ServiceListDTO();
			try {
				serviceListDTO = (ServiceListDTO) EntityDtoConverter.bean2Dto(
						listEntity.get(i), serviceListDTO);
				listDtos.add(serviceListDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listDtos;
	}
}