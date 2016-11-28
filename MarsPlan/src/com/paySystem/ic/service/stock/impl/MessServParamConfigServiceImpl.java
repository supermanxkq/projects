package com.paySystem.ic.service.stock.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.MessServParamConfig;
import com.paySystem.ic.dao.stock.MessServParamConfigDao;
import com.paySystem.ic.service.stock.MessServParamConfigService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.MessServParamConfigDTO;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigServiceImpl
 * @Description:TODO
 * @date: 2014-7-22下午02:58:05
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(MessServParamConfigService.MESSSERVPARAMCONFIGSERVICE)
public class MessServParamConfigServiceImpl implements
		MessServParamConfigService {

	@Resource
	private MessServParamConfigDao messServParamConfigDao;


	/**
	 *@Title:saveMessServParamConfig
	 *@Description:保存
	 *@param messServParamConfigDTO
	 *@Return:void
	 *@author:徐凯强
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 *@Date:2014-7-22下午02:10:41
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveMessServParamConfig(
			MessServParamConfigDTO messServParamConfigDTO)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		messServParamConfigDao.saveMessServParamConfig(messServParamConfigDTO);
	}

	/**
	 *@Title:updateMessServParamConfig
	 *@Description:更新短信服务器配置信息
	 *@param messServParamConfigDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:18:09
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateMessServParamConfig(
			MessServParamConfigDTO messServParamConfigDTO)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		messServParamConfigDao
				.updateMessServParamConfig(messServParamConfigDTO);
	}

	/**
	 *@Title:querAll
	 *@Description:分页查找所有的短信服务器配置信息列表
	 *@param firstIndex
	 *@param pageNum
	 *@param messServParamConfigDTO
	 *@param orderBy
	 *@Return:QueryResult<MessServParamConfigDTO>
	 *@author:徐凯强
	 * @throws Exception
	 *@Date:2014-7-23下午05:13:09
	 */
	public QueryResult<MessServParamConfigDTO> queryAll(int firstIndex,
			int pageNum, MessServParamConfigDTO messServParamConfigDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return messServParamConfigDao.queryAll(firstIndex, pageNum,
				messServParamConfigDTO, orderBy);
	}

	/**
	 *@Title:findConfigDTO
	 *@Description:根据主键查询
	 *@param messServParamConfigDTO
	 *@Return:MessServParamConfigDTO
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:19:56
	 */
	public MessServParamConfigDTO findConfigDTO(
			MessServParamConfigDTO messServParamConfigDTO) {
		MessServParamConfigDTO messServConfigDTO = new MessServParamConfigDTO();
		MessServParamConfig messServParamConfig = new MessServParamConfig();
		try {
			/** 根据编号获取数据库中的数据 */
			messServParamConfig = messServParamConfigDao
					.find(messServParamConfigDTO.getMspId());
			/** 根据通道类型判断要返回的参数类型 */
			messServConfigDTO = changeEntityToDTO(messServParamConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messServConfigDTO;
	}

	/**
	 *@MethodName:testShortMessServConfig
	 *@Description:测试普通短信服务器配置
	 *@param messServParamConfigDTO
	 *@Author:徐凯强
	 *@Date:2014-7-25下午05:09:14
	 */
	public ReturnDTO testShortMessServConfig(
			MessServParamConfigDTO messServParamConfigDTO) {
		return messServParamConfigDao
				.testShortMessServConfig(messServParamConfigDTO);
	}

	/**
	 *@Title:changeEntityToDTO
	 *@Description:将实体转换为数据传输实体
	 *@param messServParamConfig
	 *@Return:MessServParamConfigDTO
	 *@author:徐凯强
	 *@Date:2014-7-28下午05:49:49
	 */
	public MessServParamConfigDTO changeEntityToDTO(
			MessServParamConfig messServParamConfig) {
		MessServParamConfigDTO messServParamConfigDTO = new MessServParamConfigDTO();
		if (messServParamConfig.getParamType() == 1) {
			messServParamConfigDTO.setMspId(messServParamConfig.getMspId());
			messServParamConfigDTO.setServiceId(messServParamConfig
					.getServiceId());
			messServParamConfigDTO.setParamType(messServParamConfig
					.getParamType());
			messServParamConfigDTO.setAccount(messServParamConfig.getAccount());
			messServParamConfigDTO.setPassword(messServParamConfig
					.getPassword());
			messServParamConfigDTO.setMobile(messServParamConfig.getMobile());
			messServParamConfigDTO.setStatus(messServParamConfig.getStatus());
			
			messServParamConfigDTO.setAccountValue(messServParamConfig.getAccountValue());
			messServParamConfigDTO.setPasswordValue(messServParamConfig.getPasswordValue());
			messServParamConfigDTO.setServUrlValue(messServParamConfig.getServUrlValue());
			messServParamConfigDTO.setContent(messServParamConfig.getContent());
		} else {
			messServParamConfigDTO.setMspId1(messServParamConfig.getMspId());
			messServParamConfigDTO.setServiceId1(messServParamConfig
					.getServiceId());
			messServParamConfigDTO.setParamType1(messServParamConfig
					.getParamType());
			messServParamConfigDTO
					.setAccount1(messServParamConfig.getAccount());
			messServParamConfigDTO.setPassword1(messServParamConfig
					.getPassword());
			messServParamConfigDTO.setMobile1(messServParamConfig.getMobile());
			messServParamConfigDTO.setStatus1(messServParamConfig.getStatus());
			messServParamConfigDTO.setAccountValue1(messServParamConfig.getAccountValue());
			messServParamConfigDTO.setPasswordValue1(messServParamConfig.getPasswordValue());
			messServParamConfigDTO.setServUrlValue1(messServParamConfig.getServUrlValue());
		}
		return messServParamConfigDTO;
	}

	/**
	 *@Title:changeStatus
	 *@Description:改变其他服务器的状态
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-29上午10:37:39
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO changeStatus() {
		return messServParamConfigDao.changeStatus();
	}

	
	/**
	 *@Title:testValidateMessServCon
	 *@Description:发送验证码短信服务器
	 *@param messServParamConfigDTO 
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-29下午05:03:57
	 */
	public ReturnDTO testValidateMessServCon(
			MessServParamConfigDTO messServParamConfigDTO) {
		return messServParamConfigDao.testValidateMessServCon(messServParamConfigDTO);
	}
	/**
	*@Title:changeShortMessStatus
	*@Description:改变其他普通服务器的状态
	*@Return:ReturnDTO
	*@author:徐凯强
	*@Date:2014-7-29上午10:37:39
	*/
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO changeShortMessStatus() {
		return messServParamConfigDao.changeShortMessStatus();
	}
	/**
	*@Title:changeValidateMessStatus
	*@Description:改变其他验证码服务器的状态
	*@Return:ReturnDTO
	*@author:徐凯强
	*@Date:2014-7-29上午10:37:39
	*/
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO changeValidateMessStatus() {
		return messServParamConfigDao.changeValidateMessStatus();
	}
	/**
	 *@Title:sendMessageByParamType
	 *@Description:外部调用发送短信
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-8-5下午02:39:46
	 */
	public ReturnDTO sendMessageByParamType(
			MessServParamConfigDTO messServParamConfigDTO) {
		return messServParamConfigDao.sendMessageByParamType(messServParamConfigDTO);
	}
	
	

}
