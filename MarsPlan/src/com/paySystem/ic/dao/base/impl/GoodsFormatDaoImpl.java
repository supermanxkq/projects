package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFormat;
import com.paySystem.ic.dao.base.GoodsFormatDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.GoodsFormatDTO;


/**  
 * @Title: GoodsAttributeDaoImpl.java
 * @Package: com.paySystem.ic.dao.base.impl
 * @Description: 商品规格DAO实现类
 * @Author: yanwuyang 
 * @Date: 2014-8-20 下午09:51:17
 * @Version: V1.0  
 */

@Repository(GoodsFormatDao.GOODSFORMATDAO)
public class GoodsFormatDaoImpl extends DaoSupport<GoodsFormat> implements
		GoodsFormatDao {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFormatDao#save(com.paySystem.ic.web.dto.base.GoodsFormatDTO)
	 *@MethodName:save
	 *@Description:保存
	 *@param formatDTO
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-9-23下午2:53:23
	 */
	public Integer save(GoodsFormatDTO formatDTO) {
		/**校验名称是否存在*/
		Integer id = findByName(formatDTO.getFormatName());
		if(id!=null){
			return id;
		}
		GoodsFormat format = new GoodsFormat();
		BeanUtils.copyProperties(formatDTO, format);
		this.save(format);
		return format.getFormatId();
	}

	/**
	 * 
	 *@Title:getFormatByGroup
	 *@Description:根据分组ID获取规格
	 *@Params:@param groupId
	 *@Params:@return
	 *@Return:QueryResult<GoodsFormatDTO>
	 *@author:yanwuyang
	 *@Date:2014-8-22下午10:27:51
	 */
	public List getFormatsByGroup(Integer groupId){
		QueryResult<GoodsFormat> qr = new QueryResult<GoodsFormat>();
		Query query = this.em.createNativeQuery("select b2.formatId,b2.formatName from b_formatgrouprela b1 left join b_format b2 on b1.formatId = b2.formatId where b1.fgroupId=?");
		query.setParameter(1, groupId);
		return query.getResultList();
	}
	
	/**
	 * 
	 *@Title:dtoResult2beanResult
	 *@Description:dto转bean
	 *@Params:@param list
	 *@Params:@return
	 *@Return:QueryResult<GoodsFormatDTO>
	 *@author:yanwuyang
	 *@Date:2014-9-23下午2:53:33
	 */
	private QueryResult<GoodsFormatDTO> dtoResult2beanResult(
			QueryResult<GoodsFormat> list) {
		QueryResult<GoodsFormatDTO> dtoResult = new QueryResult<GoodsFormatDTO>();
		List<GoodsFormatDTO> dtoList = new ArrayList<GoodsFormatDTO>();
		if (list != null) {
			/** 遍历实体Result信息，获取支付参数实体对象*/
			for (GoodsFormat format : list.getResultlist()) {
				/** 将支付参数实体对象转为支付参数DTO对象*/
				GoodsFormatDTO formatDTO = new GoodsFormatDTO();
				BeanUtils.copyProperties(format, formatDTO);
				/** 添加到DTO对象集合中*/
				dtoList.add(formatDTO);
			}
		}
		/**组装DtoResult信息 DTO对象集合及总条数*/
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(list.getTotalrecord());
		return dtoResult;
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFormatDao#getFormatByFamilyId(java.lang.Integer)
	 *@MethodName:getFormatByFamilyId
	 *@Description:根据分类ID获取规格
	 *@param familyId
	 *@return
	 *@throws Exception
	 *@Author:yanwuyang
	 *@Date:2014-8-24下午12:42:35
	 */
	public List<GoodsFormatDTO> getFormatByFamilyId(Integer familyId)
			throws Exception {
		Query query = em.createNativeQuery("select f.formatId,f.formatName from b_format as f where f.formatId in (select fr.formatId from b_familygrouprela as fr where fr.familyId=?)");
		query.setParameter(1, familyId);
		List<Object[]>  formtsObjs= query.getResultList();
		if(CollectionUtils.isNotEmpty(formtsObjs)) {
			List<GoodsFormatDTO> formatReturnList = new ArrayList<GoodsFormatDTO>(formtsObjs.size());
			for(Object[] obj : formtsObjs) {
				GoodsFormatDTO fd = new GoodsFormatDTO();
				fd.setFormatId((Integer)obj[0]);
				fd.setFormatName(obj[1]!=null?obj[1].toString():"");
				formatReturnList.add(fd);
			}
			return formatReturnList;
		}
		return null;
	}
	
	/**
	 * 
	 *@Title:getParentFormatByFamilyId
	 *@Description:查询父类的规格
	 *@Params:@param familyId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:List<GoodsFormatDTO>
	 *@author:yanwuyang
	 *@Date:2014-8-25下午3:42:13
	 */
	public List<GoodsFormatDTO> getParentFormatByFamilyId(Integer familyId)throws Exception {
		List<Integer> familyIds = new ArrayList<Integer>();
		getParentFamilyId(familyIds,familyId);
		Query query = em.createNativeQuery("select f.formatId,f.formatName from b_format as f where f.formatId in " +
				"(select fr.formatId from b_familygrouprela as fr where fr.familyId in (" +getBatchSQL(familyIds)+"))");
		List<Object[]>  formtsObjs= query.getResultList();
		if(CollectionUtils.isNotEmpty(formtsObjs)) {
			List<GoodsFormatDTO> formatReturnList = new ArrayList<GoodsFormatDTO>(formtsObjs.size());
			for(Object[] obj : formtsObjs) {
				GoodsFormatDTO fd = new GoodsFormatDTO();
				fd.setFormatId((Integer)obj[0]);
				fd.setFormatName(obj[1]!=null?obj[1].toString():"");
				fd.setIsParent("true");
				formatReturnList.add(fd);
			}
			return formatReturnList;
		}
		return null;
	}
	/**
	 * 
	 *@Title:getBatchSQL
	 *@Description:拼接所有父类的ID
	 *@Params:@param familyIds
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-9-23下午2:52:20
	 */
	private String getBatchSQL(List<Integer> familyIds){
		if(familyIds.size()==0){
			return "0";
		}
		String sql ="";
		for (int i = 0; i < familyIds.size(); i++) {
			if(!sql.equals("")){
				sql+=",";
			}
			sql+=familyIds.get(i);
		}
		return sql;
	}
	
	/**
	 * 
	 *@Title:getParentFamilyId
	 *@Description:根据分类ID获取所有的父类ID
	 *@Params:@param list
	 *@Params:@param familyId
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-9-23下午2:51:54
	 */
	private void getParentFamilyId(List<Integer> list,Integer familyId){
		Query query = em.createNativeQuery("select parentId from b_goodsfamily where familyid=?");
		query.setParameter(1, familyId);
		Object o = query.getSingleResult();
		Integer parentId= (Integer)o;
		if(parentId!=0){
			list.add(parentId);
			getParentFamilyId(list,parentId);
		}
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
	public boolean checkName(String name,Integer fgroupId) throws Exception {
		Query query = em.createNativeQuery("select b.formatId from b_format as b left join b_formatgrouprela b2 on b.formatid=b2.formatId where b2.fgroupId=? and b.formatName=?");
		query.setParameter(1, fgroupId);
		query.setParameter(2, name);
		return query.getResultList().size()>=1;
	}
	
	/**
	 * 
	 *@Title:findByName
	 *@Description:根据名称查找对象
	 *@Params:@param name
	 *@Params:@return
	 *@Return:Integer
	 *@author:yanwuyang
	 *@Date:2014-8-25下午11:59:38
	 */
	private Integer findByName(String name){
		Query query = em.createNativeQuery("select b.formatId from b_format as b where b.formatName=?");
		query.setParameter(1, name);
		try{
			Object o =query.getSingleResult();
			return (Integer)o;
		}catch(Exception e){
			return null;
		}
	}
}
