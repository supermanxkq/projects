package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.bean.goods.TypeAttrRela;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.TypeAttrDAO;
import com.paySystem.ic.web.dto.goods.TypeAttrDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:TypeAttrDAOImpl
 * @Description:商品类型属性关联dao实现类
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
@Repository(TypeAttrDAO.TYPEATTR)
public class TypeAttrDAOImpl extends DaoSupport<TypeAttrRela> implements TypeAttrDAO {

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
		Query query = em.createQuery("from GoodsAttribute o where o.attrId in ( select o1.attrId from TypeAttrRela o1 where o1.typeId="+typeId+")");
		
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
