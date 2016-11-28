package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.bean.base.GoodsFamilyAttrRela;
import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.dao.base.GoodsFamilyAttrRelaDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.goods.TypeAttrDTO;


/**  
 * @Title: GoodsAttributeDaoImpl.java
 * @Package: com.paySystem.ic.dao.base.impl
 * @Description: 商品分类属性关联DAO实现类
 * @Author: yanwuyang 
 * @Date: 2014-8-20 下午09:51:17
 * @Version: V1.0  
 */

@Repository(GoodsFamilyAttrRelaDao.GOODSFAMILYATTRRELADAO)
public class GoodsFamilyAttrRelaDaoImpl extends DaoSupport<GoodsFamilyAttrRela> implements
	GoodsFamilyAttrRelaDao {



	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFamilyAttrRelaDao#save(com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO)
	 *@MethodName:save
	 *@Description:保存属性与分组的关联
	 *@param familyAttrRelaDTO
	 *@Author:yanwuyang
	 *@Date:2014-8-24上午10:35:19
	 */
	public void save(GoodsFamilyAttrRelaDTO familyAttrRelaDTO) {
		GoodsFamilyAttrRela familyAttrRela = new GoodsFamilyAttrRela();
		BeanUtils.copyProperties(familyAttrRelaDTO, familyAttrRela);
		this.save(familyAttrRela);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFamilyAttrRelaDao#deleteByFamilyId(java.lang.Integer)
	 *@MethodName:deleteByFamilyId
	 *@Description:删除与分组的关联
	 *@param familyId 类型ID
	 *@Author:yanwuyang
	 *@Date:2014-8-24上午10:38:14
	 */
	public void deleteByFamilyId(Integer familyId) {
		Query query =this.em.createNativeQuery("delete from b_familyattrrela where familyId=?");
		query.setParameter(1, familyId);
		query.executeUpdate();
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFamilyAttrRelaDao#getAssociatedFamilyId()
	 *@MethodName:getAssociatedFamilyId
	 *@Description:获取已经关联的类型
	 *@return
	 *@throws Exception
	 *@Author:yanwuyang
	 *@Date:2014-8-24下午04:36:44
	 */
	public QueryResult<List> getAssociatedFamily(int firstPage, int pageNum,
			GoodsFamilyDTO familyDTO, LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<List> qr = new QueryResult<List>();
		String sql = "select distinct b.familyId,b.familyName,b.status from b_goodsfamily as b left join b_familyattrrela f on b.familyId= f.familyId  where  b.familyid in (select distinct familyid from b_familyattrrela)";
		if(familyDTO.getFamilyName()!=null && !familyDTO.getFamilyName().equals("")){
			sql += " and b.familyName like ?";
		}
		Query query = em.createNativeQuery(sql);
		if(familyDTO.getFamilyName()!=null && !familyDTO.getFamilyName().equals("")){
			query.setParameter(1, "%"+familyDTO.getFamilyName()+"%");
		}
		if (firstPage != -1 && pageNum != -1) {
			query.setFirstResult(firstPage).setMaxResults(pageNum);
		}
		qr.setResultlist(query.getResultList());
		sql = "select count(*) from b_goodsfamily as b  where  b.familyid in (select distinct familyid from b_familyattrrela )";
		if(familyDTO.getFamilyName()!=null && !familyDTO.getFamilyName().equals("")){
			sql += " and b.familyName like ?";
		}
		query = em.createNativeQuery(sql);
		if(familyDTO.getFamilyName()!=null && !familyDTO.getFamilyName().equals("")){
			query.setParameter(1, "%"+familyDTO.getFamilyName()+"%");
		}
		qr.setTotalrecord(Long.parseLong(query.getSingleResult()+""));
		return qr;
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFamilyGroupRelaDao#updateStautsByFamilyId(java.lang.Integer, java.lang.Integer)
	 *@MethodName:updateStautsByFamilyId
	 *@Description:修改状态
	 *@param familyId
	 *@param status
	 *@Author:yanwuyang
	 *@Date:2014-8-24下午11:10:22
	 */
	public void updateStautsByFamilyId(Integer familyId, Integer status) {
		Query query =this.em.createNativeQuery("update b_familyattrrela set status=? where familyId=?");
		query.setParameter(1, status);
		query.setParameter(2, familyId);
		query.executeUpdate();
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFamilyAttrRelaDao#getFirstFamily()
	 *@MethodName:getFirstFamily
	 *@Description:获取第一个分类
	 *@return
	 *@throws Exception
	 *@Author:yanwuyang
	 *@Date:2014-8-25下午5:30:26
	 */
	public GoodsFamilyDTO getFirstFamily() throws Exception {
		List<Object[]> list = em.createNativeQuery("select familyid,familyName,status from b_GoodsFamily where parentId=0 order by familyid asc").getResultList();
		GoodsFamilyDTO familyDTO =new  GoodsFamilyDTO();
		if(list==null || list.size()==0){
			return familyDTO;
		}
		Object[] o = list.get(0);
		familyDTO.setFamilyId((Integer)o[0]);
		familyDTO.setFamilyName((String)o[1]);
		familyDTO.setStatus(Integer.parseInt(o[2]+""));
		return familyDTO;
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.TypeAttrDAO#findTypeAttrRelaList(int)
	 *@MethodName:findTypeAttrRelaList
	 *@Description:查找类型属性
	 *@param typeId
	 *@return
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:21:25
	 */
	@SuppressWarnings("unchecked")
	public List<TypeAttrDTO> findTypeAttrRelaList(int typeId) throws Exception {
		Query query = em.createQuery("from GoodsAttribute o where o.attrId in ( select o1.attrId from GoodsFamilyAttrRela o1 where o1.familyId="+typeId+")");
		
		List<TypeAttrDTO> resultList = new ArrayList<TypeAttrDTO>();
		List list = query.getResultList();
		for(int i=0;i<list.size();i++) {
			GoodsAttribute attr = (GoodsAttribute)list.get(i);
			if(StringUtils.isNotBlank(attr.getAttrName())) {
				TypeAttrDTO ta = new TypeAttrDTO();
				ta.setAttrId(attr.getAttrId());
				ta.setAttrName(attr.getAttrName());
				ta.setIsEn(attr.getIsEn());
				resultList.add(ta);
			}
		}
		return resultList;
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.TypeAttrDAO#queryAttrEntityList(int)
	 *@MethodName:queryAttrEntityList
	 *@Description:查找属性实体对象
	 *@param attrId
	 *@return
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:21:48
	 */
	@SuppressWarnings("unchecked")
	public List<AttrEntity> queryAttrEntityList(int attrId) throws Exception {
		Query query = em.createQuery("from AttrEntity o where o.attrId = " + attrId);
		return query.getResultList();
	}
	
}
