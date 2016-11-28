package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.UnruleType;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.UnruleTypeDao;
import com.paySystem.ic.web.dto.goods.UnruleTypeDTO;


/**  
 * @Title: UnruleTypeDaoImpl.java
 * @Package: com.paySystem.ic.dao.goods.impl
 * @Description: TODO
 * @Author: yanwuyang 
 * @Date: 2014-8-27 下午4:49:49
 * @Version: V1.0  
 */

@Repository(UnruleTypeDao.UNRULETYPE)
public class UnruleTypeDaoImpl extends DaoSupport<UnruleType> implements UnruleTypeDao {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.UnruleTypeDao#getAllUnruleTypes()
	 *@MethodName:getAllUnruleTypes
	 *@Description:获取所有的违规类型
	 *@return
	 *@throws Exception
	 *@Author:yanwuyang
	 *@Date:2014-8-27下午4:51:54
	 */
	public QueryResult<UnruleTypeDTO> getAllUnruleTypes() throws Exception {
		QueryResult<UnruleType> qr = new QueryResult<UnruleType>();
		Query query =this.em.createQuery("from UnruleType");
		qr.setResultlist(query.getResultList());
		return dtoResult2beanResult(qr);
	}
	
	private QueryResult<UnruleTypeDTO> dtoResult2beanResult(
			QueryResult<UnruleType> list) {
		QueryResult<UnruleTypeDTO> dtoResult = new QueryResult<UnruleTypeDTO>();
		List<UnruleTypeDTO> dtoList = new ArrayList<UnruleTypeDTO>();
		if (list != null) {
			/** 遍历实体Result信息，获取支付参数实体对象*/
			for (UnruleType unruleType : list.getResultlist()) {
				/** 将支付参数实体对象转为支付参数DTO对象*/
				UnruleTypeDTO unruleTypeDTO = new UnruleTypeDTO();
				BeanUtils.copyProperties(unruleType, unruleTypeDTO);
				/** 添加到DTO对象集合中*/
				dtoList.add(unruleTypeDTO);
			}
		}
		/**组装DtoResult信息 DTO对象集合及总条数*/
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(list.getTotalrecord());

		return dtoResult;
	}

}
