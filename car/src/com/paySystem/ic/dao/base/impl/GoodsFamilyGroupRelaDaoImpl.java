package com.paySystem.ic.dao.base.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.GoodsFamilyGroupRela;
import com.paySystem.ic.dao.base.GoodsFamilyGroupRelaDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.GoodsFamilyGroupRelaDTO;


/**  
 * @Title: GoodsFamilyGroupRelaDaoImpl.java
 * @Package: com.paySystem.ic.dao.base.impl
 * @Description: 商品分类规格分组关联Dao实现类
 * @Author: yanwuyang 
 * @Date: 2014-8-22 上午12:08:16
 * @Version: V1.0  
 */
@Repository(GoodsFamilyGroupRelaDao.GOODSFAMILYGROUPRELADAO)
public class GoodsFamilyGroupRelaDaoImpl extends DaoSupport<GoodsFamilyGroupRela> implements
		GoodsFamilyGroupRelaDao {

	/**
	 * 等五洋的dto
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsFamilyGroupRela> findGoodsFormatListByFamilyId(int familyId)
		throws Exception {
		return em.createQuery("from GoodsFamilyGroupRela o where o.familyId="+familyId).getResultList();
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFamilyGroupRelaDao#save(com.paySystem.ic.web.dto.base.GoodsFamilyGroupRelaDTO)
	 *@MethodName:save
	 *@Description:保存规格与分组的关联
	 *@param familyGroupRelaDTO
	 *@Author:yanwuyang
	 *@Date:2014-8-24上午10:33:07
	 */
	public void save(GoodsFamilyGroupRelaDTO familyGroupRelaDTO) {
		GoodsFamilyGroupRela familyGroupRele = new GoodsFamilyGroupRela();
		BeanUtils.copyProperties(familyGroupRelaDTO, familyGroupRele);
		this.save(familyGroupRele);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFamilyGroupRelaDao#delete(java.lang.Integer)
	 *@MethodName:delete
	 *@Description:删除规格与分类的关联
	 *@param familyId 分类ID
	 *@Author:yanwuyang
	 *@Date:2014-8-24上午10:48:17
	 */
	public void deleteByFamilyId(Integer familyId) {
		Query query =this.em.createNativeQuery("delete from b_familygrouprela where familyId=?");
		query.setParameter(1, familyId);
		query.executeUpdate();
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
		Query query =this.em.createNativeQuery("update  b_familygrouprela set status=? where familyId=?");
		query.setParameter(1, status);
		query.setParameter(2, familyId);
		query.executeUpdate();
	}
}
