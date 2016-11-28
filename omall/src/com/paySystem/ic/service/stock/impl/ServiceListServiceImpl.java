package com.paySystem.ic.service.stock.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ServiceList;
import com.paySystem.ic.dao.stock.ServiceListDao;
import com.paySystem.ic.service.stock.ServiceListService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.stock.ServiceListDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigServiceImpl
 * @Description:TODO
 * @date: 2014-7-22下午02:58:05
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(ServiceListService.SERVICELISTSERVICE)
public class ServiceListServiceImpl implements ServiceListService {

	@Resource
	private ServiceListDao serviceListDao;

	/**
	 *@Title:saveServiceList
	 *@Description:保存
	 *@param serviceListDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:10:41
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveServiceList(ServiceListDTO serviceListDTO) {
		serviceListDTO.setAddTime(new Date());
		serviceListDTO.setUpdateTime(new Date());
		UserSession userSession = Utils.getUserSession();
		serviceListDTO.setOrganID(userSession.getOrganId());
		serviceListDTO.setAddMen(userSession.getUserName());
		serviceListDTO.setStatus(0);
		try {
			serviceListDao.saveServiceList(serviceListDTO);
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
	 *@Date:2014-7-22下午02:18:09
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateServiceList(ServiceListDTO serviceListDTO) {
		serviceListDTO.setUpdateTime(new Date());
		UserSession userSession = Utils.getUserSession();
		serviceListDTO.setOrganID(userSession.getOrganId());
		serviceListDTO.setAddMen(userSession.getUserName());
		if(serviceListDTO.getStatus()==1){
		serviceListDTO.setStatus(1);
		}else if(serviceListDTO.getStatus()==0){
			serviceListDTO.setStatus(0);
		}else{
			serviceListDTO.setStatus(9);
		}
		serviceListDao.updateServiceList(serviceListDTO);
	}

	/**
	 *@Title:querAll
	 *@Description:分页查找
	 *@param firstIndex
	 *@param pageNum
	 *@param serviceListDTO
	 *@param orderBy
	 *@Return:QueryResult<ServiceListDTO>
	 *@author:徐凯强
	 * @throws Exception
	 *@Date:2014-7-23下午05:13:09
	 */
	public QueryResult<ServiceListDTO> queryAll(int firstIndex, int pageNum,
			ServiceListDTO serviceListDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		return serviceListDao.queryAll(firstIndex, pageNum, serviceListDTO,
				orderBy);
	}

	/**
	 *@Title:findServiceListDTO
	 *@Description:根据主键查询
	 *@param serviceListDTO
	 *@Return:serviceListDTO
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:19:56
	 */
	public ServiceListDTO findServiceListDTO(Integer servId) {
		ServiceListDTO serviceListDTO = new ServiceListDTO();
		ServiceListDTO serviceListDTO2 = new ServiceListDTO();
		try {
			serviceListDTO2 = (ServiceListDTO) EntityDtoConverter.bean2Dto(
					serviceListDao.find(servId), serviceListDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceListDTO2;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.stock.ServiceListService#findName(java.lang.String, java.lang.String, java.lang.Integer)
	 *@MethodName:findName
	 *@Description:检验标题名称是否重复
	 *@param name
	 *@param method
	 *@param serviceId
	 *@return
	 *@Author:张军磊
	 *@Date:2014-12-1下午05:27:27
	 */
	@Override
	public Boolean findName(String name, String method, Integer serviceId) {
		boolean flag = false;
		List<ServiceList> list = serviceListDao.findServiceByName(name);
		if (method.equals("addSave")) {

			if (list.size() > 0 == true) {
				flag = true;
			}
		} else {

			if (list.size() > 0
					&& !list.get(0).getServId().equals(serviceId)) {
				flag = true;
			}
		}
		return flag;
	}
}
