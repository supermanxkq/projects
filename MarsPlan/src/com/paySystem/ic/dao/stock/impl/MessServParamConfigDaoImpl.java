package com.paySystem.ic.dao.stock.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.MessServParamConfig;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.stock.MessServParamConfigDao;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.MessServParamConfigDTO;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigDaoImpl
 * @Description:短信服务器配置，添加、查看、更新记录接口实现
 * @date: 2014-7-22下午02:19:37
 * @author: 徐凯强
 * @version: V1.0
 */
@Repository(MessServParamConfigDao.MESSSERVPARAMCONFIGDAO)
public class MessServParamConfigDaoImpl extends DaoSupport<MessServParamConfig>
		implements MessServParamConfigDao {

	/**
	 *@Title:saveMessServParamConfig
	 *@Description:保存
	 *@param messServParamConfig
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:22:21
	 */
	public void saveMessServParamConfig(
			MessServParamConfigDTO messServParamConfigDTO) {
		MessServParamConfig messServParamConfig1 = new MessServParamConfig();
		MessServParamConfig messServParamConfig2 = new MessServParamConfig();
		messServParamConfig1 = changeDTOToEntity(messServParamConfigDTO);
		messServParamConfig2 = changeDTOTOEntity2(messServParamConfigDTO);
		/** 因为验证码中服务器和状态为disable，所以这里设置为普通短信的值 */
		messServParamConfig2
				.setServiceId(messServParamConfigDTO.getServiceId());
		messServParamConfig2.setStatus(messServParamConfigDTO.getStatus());
		try {
			this.save(messServParamConfig1);
			this.save(messServParamConfig2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@Title:updateMessServParamConfig
	 *@Description:更新短信服务器配置信息
	 *@param messServParamConfig
	 *@Return:void
	 *@author:徐凯强
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 *@Date:2014-7-22下午02:23:02
	 */
	public void updateMessServParamConfig(
			MessServParamConfigDTO messServParamConfigDTO) {
		try {
			if (messServParamConfigDTO.getParamType() != null) {
				this.update(changeDTOToEntity(messServParamConfigDTO));
			} else {
				this.update(changeDTOTOEntity2(messServParamConfigDTO));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@MethodName:queryAll
	 *@Description:根据条件查询
	 *@param firstIndex
	 *@param pageNum
	 *@param messServParamConfigDTO
	 *@param orderBy
	 * @throws Exception
	 *@Author:徐凯强
	 *@Date:2014-7-23下午07:40:56
	 */
	public QueryResult<MessServParamConfigDTO> queryAll(int firstIndex,
			int pageNum, MessServParamConfigDTO messServParamConfigDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<MessServParamConfigDTO> queryResultDTOList = new QueryResult<MessServParamConfigDTO>();
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		/** 模糊查询 */
		if (messServParamConfigDTO.getStatus() != null) {
			wherejpql.append(" and o.status=?");
			queryParams.add(messServParamConfigDTO.getStatus());
		}
		if (messServParamConfigDTO.getServiceId() != null
				&& messServParamConfigDTO.getServiceId() != -1) {
			wherejpql.append(" and o.serviceId=?");
			queryParams.add(messServParamConfigDTO.getServiceId());
		}

		QueryResult<MessServParamConfig> queryResult = this.getScrollData(
				firstIndex, pageNum, wherejpql.toString(), queryParams
						.toArray(), orderBy);
		/** 将实体queryResult转换为DTOqueryResult */
		List<MessServParamConfig> listEntity = queryResult.getResultlist();
		List<MessServParamConfigDTO> listDtos = changeListToDtoList(listEntity);
		/** 赋值QueryResult<MessServParamConfigDTO> */
		queryResultDTOList.setResultlist(listDtos);
		queryResultDTOList.setTotalrecord(queryResult.getTotalrecord());
		return queryResultDTOList;
	}

	/**
	 *@Title:changeListToDtoList
	 *@Description:将实例queryResult转换为DTOQueryResult
	 *@param listEntity
	 *@Return:List<MessServParamConfigDTO>
	 *@author:徐凯强
	 *@Date:2014-7-23下午08:34:02
	 */
	public List<MessServParamConfigDTO> changeListToDtoList(
			List<MessServParamConfig> listEntity) {
		List<MessServParamConfigDTO> listDtos = new ArrayList<MessServParamConfigDTO>();

		for (int i = 0; i < listEntity.size(); i++) {
			MessServParamConfigDTO messServParamConfigDTO = new MessServParamConfigDTO();
			try {
				messServParamConfigDTO = (MessServParamConfigDTO) EntityDtoConverter
						.bean2Dto(listEntity.get(i), messServParamConfigDTO);
				listDtos.add(messServParamConfigDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listDtos;
	}

	/**
	 *@Title:changeDTOToEntity
	 *@Description:将DTO对象转换为实体对象
	 *@param messServParamConfigDTO
	 *@Return:MessServParamConfig
	 *@author:徐凯强
	 *@Date:2014-7-25上午11:38:44
	 */
	public MessServParamConfig changeDTOToEntity(
			MessServParamConfigDTO messServParamConfigDTO) {
		MessServParamConfig messServParamConfig = new MessServParamConfig();
		/** 普通短信服务器 */
		messServParamConfig.setMspId(messServParamConfigDTO.getMspId());
		messServParamConfig.setServiceId(messServParamConfigDTO.getServiceId());
		messServParamConfig.setParamType(1);
		messServParamConfig.setAccount(messServParamConfigDTO.getAccount());
		messServParamConfig.setPassword(messServParamConfigDTO.getPassword());
		messServParamConfig.setMobile(messServParamConfigDTO.getMobile());
		messServParamConfig.setStatus(messServParamConfigDTO.getStatus());

		messServParamConfig.setAccountValue(messServParamConfigDTO
				.getAccountValue());
		messServParamConfig.setPasswordValue(messServParamConfigDTO
				.getPasswordValue());
		messServParamConfig.setServUrlValue(messServParamConfigDTO
				.getServUrlValue());
		messServParamConfig.setContent(messServParamConfigDTO.getContent());
		return messServParamConfig;
	}

	/**
	 *@Title:changeDTOTOEntity2
	 *@Description:将DTO转换为实体
	 *@param messServParamConfigDTO
	 *@Return:MessServParamConfig
	 *@author:徐凯强
	 *@Date:2014-7-29下午04:09:05
	 */
	public MessServParamConfig changeDTOTOEntity2(
			MessServParamConfigDTO messServParamConfigDTO) {
		MessServParamConfig messServParamConfig = new MessServParamConfig();
		/** 验证码短信服务器 */
		messServParamConfig.setMspId(messServParamConfigDTO.getMspId1());
		messServParamConfig
				.setServiceId(messServParamConfigDTO.getServiceId1());
		messServParamConfig.setParamType(2);
		messServParamConfig.setAccount(messServParamConfigDTO.getAccount1());
		messServParamConfig.setPassword(messServParamConfigDTO.getPassword1());
		messServParamConfig.setMobile(messServParamConfigDTO.getMobile1());
		messServParamConfig.setStatus(messServParamConfigDTO.getStatus1());
		messServParamConfig.setAccountValue(messServParamConfigDTO
				.getAccountValue1());
		messServParamConfig.setPasswordValue(messServParamConfigDTO
				.getPasswordValue1());
		messServParamConfig.setServUrlValue(messServParamConfigDTO
				.getServUrlValue1());
		return messServParamConfig;
	}

	/**
	 *@Title:testShortMessServConfig
	 *@Description:发送测试短信
	 *@param url服务器url
	 *@param phone手机号码
	 *@param content发送内容
	 *@param account用户名
	 *@param passWord密码
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-25下午02:39:39
	 */
	public ReturnDTO testShortMessServConfig(
			MessServParamConfigDTO messServParamConfigDTO) {
		String result = null;

		StringBuffer requestParameters = new StringBuffer();

		/** 账户号 */
		requestParameters.append(messServParamConfigDTO.getAccount() + "="
				+ messServParamConfigDTO.getAccountValue());
		/** 口令 */
		requestParameters.append("&" + messServParamConfigDTO.getPassword()
				+ "=" + messServParamConfigDTO.getPasswordValue());
		/** 手机号码 */
		requestParameters.append("&" + messServParamConfigDTO.getMobile() + "="
				+ messServParamConfigDTO.getMobileValue());

		String a="西小口加油站加油送洗车服务。先到先得。";
		String content="";
		try {
			 content=java.net.URLEncoder.encode(a,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		/** 内容 */
		requestParameters.append("&"+messServParamConfigDTO.getContent()+"="+content);
		System.out.println(requestParameters.toString());
		if (messServParamConfigDTO.getServUrlValue().startsWith("http://")) {
			// Send a GET request to the servlet
			try {
				// Send data
				String urlStr = messServParamConfigDTO.getServUrlValue();
				if (requestParameters != null && requestParameters.length() > 0) {
					urlStr += "?" + requestParameters;
				}
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				result = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ReturnDTO returnDTO = new ReturnDTO();
		return returnDTO;
	}

	/**
	 *@Title:changeStatus
	 *@Description:改变其他普通服务器的状态为停用
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-29上午10:37:39
	 */
	public ReturnDTO changeStatus() {
		ReturnDTO returnDTO = new ReturnDTO();
		String sql = "update MessServParamConfig m set m.status=0";
		Query query = em.createQuery(sql);
		if (query.executeUpdate() > -1) {
			returnDTO.setFlag(true);
		} else {
			returnDTO.setFlag(false);
		}
		return returnDTO;
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
		String result = null;

		StringBuffer requestParameters = new StringBuffer();
		/** 账户 */
		requestParameters.append(messServParamConfigDTO.getAccount1() + "="
				+ messServParamConfigDTO.getAccountValue1());
		/** 口令 */
		requestParameters.append("&" + messServParamConfigDTO.getPassword1()
				+ "=" + messServParamConfigDTO.getPasswordValue1());
		/** 手机 */
		requestParameters.append("&" + messServParamConfigDTO.getMobile1()
				+ "=" + messServParamConfigDTO.getMobileValue1());
		String code=String.valueOf(Math.random()).substring(2, 8);
		String str="尊敬的客户您好，您的验证码为"+code+"请勿回复！";
		String strString="";
		try {
			 strString=java.net.URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		requestParameters.append("&Content="+strString);
		if (messServParamConfigDTO.getServUrlValue1().startsWith("http://")) {
			// Send a GET request to the servlet
			try {
				// Send data
				String urlStr = messServParamConfigDTO.getServUrlValue1();
				if (requestParameters != null && requestParameters.length() > 0) {
					urlStr += "?" + requestParameters;
				}
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				result = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ReturnDTO returnDTO = new ReturnDTO();
		return returnDTO;
	}

	/**
	 *@Title:changeShortMessStatus
	 *@Description:改变其他普通服务器的状态
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-29上午10:37:39
	 */
	public ReturnDTO changeShortMessStatus() {
		ReturnDTO returnDTO = new ReturnDTO();
		String sql = "update MessServParamConfig m set m.status=0 where m.paramType=1";
		Query query = em.createQuery(sql);
		if (query.executeUpdate() > -1) {
			returnDTO.setFlag(true);
		} else {
			returnDTO.setFlag(false);
		}
		return returnDTO;
	}

	/**
	 *@Title:changeValidateMessStatus
	 *@Description:改变其他验证码服务器的状态
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-7-29上午10:37:39
	 */
	public ReturnDTO changeValidateMessStatus() {
		ReturnDTO returnDTO = new ReturnDTO();
		String sql = "update MessServParamConfig m set m.status=0 where m.paramType=2";
		Query query = em.createQuery(sql);
		if (query.executeUpdate() > -1) {
			returnDTO.setFlag(true);
		} else {
			returnDTO.setFlag(false);
		}
		return returnDTO;
	}

	/**
	 *@Title:sendMessageByParamType
	 *@Description:外部调用发送短信
	 *@param messServParamConfigDTO
	 *@Return:ReturnDTO
	 *@author:徐凯强
	 *@Date:2014-8-4下午03:28:02
	 */
	public ReturnDTO sendMessageByParamType(
			MessServParamConfigDTO messServParamConfigDTO) {

		/** 通过通道类型获取数据库中状态为启用的服务商 */
		MessServParamConfig messServParamConfig = findMessServParamConfigByParamType(messServParamConfigDTO);
		/** 获取服务商url、账户号、账户号值、口令、口令值、手机号码、服务器url值 */

		/** 拼接字符串到发送短信的方法中 */
		String result = null;

		StringBuffer requestParameters = new StringBuffer();
		/** 账户 */
		requestParameters.append(messServParamConfig.getAccount() + "="
				+ messServParamConfig.getAccountValue());
		/** 口令 */
		requestParameters.append("&" + messServParamConfig.getPassword()
				+ "=" + messServParamConfig.getPasswordValue());
		/** 手机 */
		requestParameters.append("&" + messServParamConfig.getMobile()
				+ "=" + messServParamConfigDTO.getMobileValue());
		requestParameters.append("&"+messServParamConfig.getContent()+"="+messServParamConfigDTO.getContent());
		if (messServParamConfig.getServUrlValue().startsWith("http://")) {
			// Send a GET request to the servlet
			try {
				// Send data
				String urlStr = messServParamConfig.getServUrlValue();
				if (requestParameters != null && requestParameters.length() > 0) {
					urlStr += "?" + requestParameters;
				}
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				result = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ReturnDTO returnDTO = new ReturnDTO();
		return returnDTO;
	}

	/**
	 *@Title:findMessServParamConfigByParamType
	 *@Description:通过通道类型获取获取服务商
	 *@param messServParamConfigDTO
	 *@Return:MessServParamConfig
	 *@author:徐凯强
	 *@Date:2014-8-4下午03:33:51
	 */
	public MessServParamConfig findMessServParamConfigByParamType(
			MessServParamConfigDTO messServParamConfigDTO) {
		String sql = "select o from MessServParamConfig o where o.paramType=1 and o.status=1";
		Query query = em.createQuery(sql);
		MessServParamConfig messServParamConfig = null;
		if (query.getResultList() != null) {
			messServParamConfig = (MessServParamConfig) query.getResultList()
					.get(0);
		}
		return messServParamConfig;
	}
	
}