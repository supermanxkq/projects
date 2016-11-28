package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFormatGroup;
import com.paySystem.ic.dao.base.GoodsFormatGroupDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupDTO;


/**  
 * @Title: GoodsAttributeDaoImpl.java
 * @Package: com.paySystem.ic.dao.base.impl
 * @Description: 商品规格分组DAO实现类
 * @Author: yanwuyang 
 * @Date: 2014-8-20 下午09:51:17
 * @Version: V1.0  
 */

@Repository(GoodsFormatGroupDao.GOODSFORMATGROUPDAO)
public class GoodsFormatGroupDaoImpl extends DaoSupport<GoodsFormatGroup> implements
	GoodsFormatGroupDao {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFormatGroupDao#save(com.paySystem.ic.web.dto.base.GoodsFormatGroupDTO)
	 *@MethodName:save
	 *@Description:保存规格分组
	 *@param formatGroupDTO
	 *@Author:yanwuyang
	 *@Date:2014-8-22下午3:30:27
	 */
	public void save(GoodsFormatGroupDTO formatGroupDTO) {
		GoodsFormatGroup formatGroup = new GoodsFormatGroup();
		BeanUtils.copyProperties(formatGroupDTO, formatGroup);
		this.save(formatGroup);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFormatGroupDao#getAllFormatGruops()
	 *@MethodName:getAllFormatGruops
	 *@Description:查询所有的规格分组
	 *@return
	 *@throws Exception
	 *@Author:yanwuyang
	 *@Date:2014-8-22下午3:38:33
	 */
	public QueryResult<GoodsFormatGroupDTO> getAllFormatGruops()
			throws Exception {
		QueryResult<GoodsFormatGroup> qr = new QueryResult<GoodsFormatGroup>();
		Query query =this.em.createQuery("from GoodsFormatGroup");
		qr.setResultlist(query.getResultList());
		return dtoResult2beanResult(qr);
	}


	/**
	 * 
	 *@Title:dtoResult2beanResult
	 *@Description:dto转bean
	 *@Params:@param list
	 *@Params:@return
	 *@Return:QueryResult<GoodsFormatGroupDTO>
	 *@author:yanwuyang
	 *@Date:2014-9-23下午2:54:23
	 */
	private QueryResult<GoodsFormatGroupDTO> dtoResult2beanResult(
			QueryResult<GoodsFormatGroup> list) {
		QueryResult<GoodsFormatGroupDTO> dtoResult = new QueryResult<GoodsFormatGroupDTO>();
		List<GoodsFormatGroupDTO> dtoList = new ArrayList<GoodsFormatGroupDTO>();
		if (list != null) {
			/** 遍历实体Result信息，获取支付参数实体对象*/
			for (GoodsFormatGroup formatGroup : list.getResultlist()) {
				/** 将支付参数实体对象转为支付参数DTO对象*/
				GoodsFormatGroupDTO formatGroupDTO = new GoodsFormatGroupDTO();
				BeanUtils.copyProperties(formatGroup, formatGroupDTO);
				/** 添加到DTO对象集合中*/
				dtoList.add(formatGroupDTO);
			}
		}
		/**组装DtoResult信息 DTO对象集合及总条数*/
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(list.getTotalrecord());
		return dtoResult;
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFormatGroupDao#checkName(java.lang.String)
	 *@MethodName:checkName
	 *@Description:校验名称是否存在
	 *@param name
	 *@return
	 *@throws Exception
	 *@Author:yanwuyang
	 *@Date:2014-8-24下午09:55:34
	 */
	public boolean checkName(String name) throws Exception {
		Query query = em.createNativeQuery("select b.fgroupId from b_formatgroup as b where b.fgroupName=?");
		query.setParameter(1, name);
		return query.getResultList().size()>=1;
	}

}
