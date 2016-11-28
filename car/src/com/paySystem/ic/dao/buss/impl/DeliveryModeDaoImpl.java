package com.paySystem.ic.dao.buss.impl;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.DeliveryMode;
import com.paySystem.ic.dao.buss.DeliveryModeDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.buss.DeliveryModeDTO;

/**  
 * @ClassName: DeliveryModeDaoImpl.java
 * @Description:配送方式dao实现类
 * @Author:yanwuyang 
 * @Date: 2014-7-20 上午08:59:34
 * @Version: V1.0  
 */

@Repository(DeliveryModeDao.DELIVERYMODEDAO)
public class DeliveryModeDaoImpl extends DaoSupport<DeliveryMode> implements
		DeliveryModeDao {

	
	/**
	 * 
	 *@Title:save
	 *@Description:保存配送方式接口
	 *@Params:@param deliveryModeDTO
	 *@Return:void
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午05:57:15
	 */
	public void save(DeliveryModeDTO deliveryModeDTO) {
		DeliveryMode deliveryMode = DTO2Bean(deliveryModeDTO);
		if (deliveryMode.getDmId() == null) {
			this.save(deliveryMode);
		} else {
			this.update(deliveryMode);
		}
	}

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
			DeliveryModeDTO deliveryModeDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<DeliveryModeDTO> dtoResult = new QueryResult<DeliveryModeDTO>();
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		/**如果支付参数名称不为空，则追加查询条件*/
		if (StringUtils.isNotBlank(deliveryModeDTO.getDmName())) {
			sb.append(" and o.dmName like ?").append(params.size() + 1);
			params.add("%" + deliveryModeDTO.getDmName() + "%");
		}
		if (StringUtils.isNotBlank(deliveryModeDTO.getLogisticsComName())) {
			sb.append(" and o.logisticsComName like ?").append(params.size() + 1);
			params.add("%" + deliveryModeDTO.getLogisticsComName() + "%");
		}
		if(deliveryModeDTO.getStatus()!=-1){
			sb.append(" and o.status = ?").append(params.size() + 1);
			params.add(deliveryModeDTO.getStatus());
		}
		/** 执行查询，获取QueryResult信息*/
		QueryResult<DeliveryMode> result = getScrollData(firstPage, pageNum, sb
				.toString(), params.toArray(), orderBy);
		
		dtoResult = this.dtoResult2beanResult(result);
		

		return dtoResult;
	}

	/**
	 * 
	 *@Title:DTO2Bean
	 *@Description:dto转实体bean
	 *@param deliveryModeDTO
	 *@return
	 *@Return:DeliveryMode
	 *@Author:yanwuyang
	 *@Date:2014-8-11下午10:22:17
	 */
	private DeliveryMode DTO2Bean(DeliveryModeDTO deliveryModeDTO) {
		DeliveryMode deliveryMode = new DeliveryMode();
		if (deliveryModeDTO != null) {
			deliveryMode.setDmId(deliveryModeDTO.getDmId());
			deliveryMode.setDmName(deliveryModeDTO.getDmName());
			deliveryMode.setDescr(deliveryModeDTO.getDescr());
			deliveryMode.setEnterCost(deliveryModeDTO.getEnterCost());
			deliveryMode.setCreateTime(deliveryModeDTO.getCreateTime());
			deliveryMode.setStatus(deliveryModeDTO.getStatus());
			deliveryMode.setUpdateTime(deliveryModeDTO.getUpdateTime());
			deliveryMode.setOperatorId(deliveryModeDTO.getOperatorId());
			deliveryMode.setLogisticsComId(deliveryModeDTO.getLogisticsComId());
			deliveryMode.setLogisticsComName(deliveryModeDTO.getLogisticsComName());
			deliveryMode.setCashOnDeliverySign(deliveryModeDTO
					.getCashOnDeliverySign());
			deliveryMode.setUseCount(deliveryModeDTO.getUseCount());
		}
		return deliveryMode;
	}

	private QueryResult<DeliveryModeDTO> dtoResult2beanResult(
			QueryResult<DeliveryMode> result) {

		QueryResult<DeliveryModeDTO> dtoResult = new QueryResult<DeliveryModeDTO>();
		List<DeliveryModeDTO> dtoList = new ArrayList<DeliveryModeDTO>();

		if (result != null) {

			/** 遍历实体Result信息，获取支付参数实体对象*/
			for (DeliveryMode dm : result.getResultlist()) {

				/** 将支付参数实体对象转为支付参数DTO对象*/
				DeliveryModeDTO dto = new DeliveryModeDTO();
				dto = bean2DTO(dm);
				/** 添加到DTO对象集合中*/
				dtoList.add(dto);

			}

		}
		/**组装DtoResult信息 DTO对象集合及总条数*/
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(result.getTotalrecord());

		return dtoResult;
	}

	/**
	 * 
	 *@Title:bean2DTO
	 *@Description:实体转DTO
	 *@param dm
	 *@return
	 *@Return:DeliveryModeDTO
	 *@Author:yanwuyang
	 *@Date:2014-8-11下午10:33:20
	 */
	private DeliveryModeDTO bean2DTO(DeliveryMode dm) {
		DeliveryModeDTO dto = new DeliveryModeDTO();
		if (dto != null) {
			dto.setDmId(dm.getDmId());
			dto.setDmName(dm.getDmName());
			dto.setCreateTime(dm.getCreateTime());
			dto.setCashOnDeliverySign(dm.getCashOnDeliverySign());
			dto.setDescr(dm.getDescr());
			dto.setEnterCost(dm.getEnterCost());
			dto.setLogisticsComId(dm.getLogisticsComId());
			dto.setLogisticsComName(dm.getLogisticsComName());
			dto.setOperatorId(dm.getOperatorId());
			dto.setStatus(dm.getStatus());
			dto.setUpdateTime(dm.getUpdateTime());
			dto.setUseCount(dm.getUseCount());
		}
		return dto;
	}

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
	
	public DeliveryModeDTO findById(Integer id) {
		DeliveryMode deliveryMode = this.find(id);
		DeliveryModeDTO dto = this.bean2DTO(deliveryMode);
		return dto;
	}

	/**
	 * 
	 *@Title:delete
	 *@Description: 删除
	 *@param id
	 *@Return:void
	 *@Author:yanwuyang
	 *@Date:2014-7-26下午05:59:40
	 */
	public void delete(Integer id) {
		/**根据Id获取支付参数实体对象*/
		DeliveryMode deliveryMode = this.find(id);
		/**更改状态为"9"删除状态*/
		deliveryMode.setStatus(9);
		/**更新修改内容，实现逻辑删除操作*/
		this.update(deliveryMode);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.buss.DeliveryModeDao#checkName(java.lang.String)
	 *@MethodName:checkName
	 *@Description:检查名称是否存在
	 *@param name
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午10:06:50
	 */
	@SuppressWarnings("unchecked")
	public boolean checkName(String name) {
		String sql ="select dm.dmId from b_delivery_mode dm where dm.dmName=?";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, name);
		List<Object[]> list = query.getResultList();
		return list.size()>=1;
	}
}
