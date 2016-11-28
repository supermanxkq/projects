package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.bean.goods.GoodsAttrValue;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.GoodsAttrValueDAO;
import com.paySystem.ic.web.dto.goods.GoodsAttrValueDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:GoodsAttrValueDAOImpl
 * @Description:商品属性值dao实现类
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
@Repository(GoodsAttrValueDAO.GOODATTRVALUE)
public class GoodsAttrValueDAOImpl extends DaoSupport<GoodsAttrValue> implements GoodsAttrValueDAO {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.GoodsAttrValueDAO#saveBean(com.paySystem.ic.web.dto.goods.GoodsAttrValueDTO)
	 *@MethodName:saveBean
	 *@Description:保存商品属性值
	 *@param goodsAttrValueDTO
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:12:58
	 */
	public void saveBean(GoodsAttrValueDTO goodsAttrValueDTO) throws Exception {
		GoodsAttrValue gav = new GoodsAttrValue();
		BeanUtils.copyProperties(goodsAttrValueDTO, gav);
		this.save(gav);
		goodsAttrValueDTO.setAttrvalId(gav.getAttrvalId());
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.GoodsAttrValueDAO#saveBeans(java.util.List)
	 *@MethodName:saveBeans
	 *@Description:批量保存商品属性值
	 *@param goodsAttrValueDTOList
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:13:09
	 */
	public void saveBeans(List<GoodsAttrValueDTO> goodsAttrValueDTOList)
			throws Exception {
		this.saves(batchCopy(goodsAttrValueDTOList));
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.GoodsAttrValueDAO#deleteBean(java.lang.Long)
	 *@MethodName:deleteBean
	 *@Description:删除商品属性
	 *@param goodsId
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:13:15
	 */
	public void deleteBean(Long goodsId) throws Exception {
		em.createQuery("delete from GoodsAttrValue o where o.goodsId=?").setParameter(1, goodsId).executeUpdate();
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.GoodsAttrValueDAO#findAttrValueByGoodsId(java.lang.Long)
	 *@MethodName:findAttrValueByGoodsId
	 *@Description:根据商品编号查找属性值
	 *@param goodsId
	 *@return
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:13:24
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsAttrValueDTO> findAttrValueByGoodsId(Long goodsId)
			throws Exception {
		 List<GoodsAttrValueDTO> goodsAttrValueList = new ArrayList<GoodsAttrValueDTO>();
		 List<GoodsAttrValue> goodsAttrList = em.createQuery("from GoodsAttrValue o where goodsId=?  ").setParameter(1, goodsId).getResultList();
		 if(CollectionUtils.isNotEmpty(goodsAttrList)) {
			 List<Integer> attrList = new ArrayList<Integer>();
			 /** 按照常理说，只要取第一个就行了，因为后面都是只能直接copy **/
			 GoodsAttrValue firstValue = goodsAttrList.get(0);
			 if(firstValue.getAttrId1() != null) {
				 attrList.add(firstValue.getAttrId1());
			 }
			 if(firstValue.getAttrId2() != null) {
				 attrList.add(firstValue.getAttrId2());
			 }
			 if(firstValue.getAttrId3() != null) {
				 attrList.add(firstValue.getAttrId3());
			 }
			 if(firstValue.getAttrId4() != null) {
				 attrList.add(firstValue.getAttrId4());
			 }
			 if(firstValue.getAttrId5() != null) {
				 attrList.add(firstValue.getAttrId5());
			 }
			 Map<Integer,List<String>> entityMap = null;
			 Map<Integer,GoodsAttribute> attrMap = null;
			 if(attrList.size() > 0) {
				 String SQL = getBatchSQL(attrList);
				 List<GoodsAttribute> attList =  em.createQuery(SQL).getResultList();
				 if(CollectionUtils.isNotEmpty(attList)) {
					 List<Integer> enIdList = new ArrayList<Integer>();
					 attrMap = new HashMap<Integer,GoodsAttribute>();
					 for(GoodsAttribute aoodsAttr : attList) {
						 attrMap.put(aoodsAttr.getAttrId(), aoodsAttr);
						 /** 是、一半是 **/
						 if(aoodsAttr.getIsEn()!=null &&(aoodsAttr.getIsEn() == 0 || aoodsAttr.getIsEn() == 2)) {
							 enIdList.add(aoodsAttr.getAttrId());
						 }
					 }
					 if(CollectionUtils.isNotEmpty(enIdList)) {
						 String ENSQL = getEnBatchSQL(enIdList);
						 /** 根据enId来查询关联的下拉条列表**/
						 List<AttrEntity> entityList = em.createQuery(ENSQL).getResultList();
						 if(CollectionUtils.isNotEmpty(entityList)) {
							 entityMap = new HashMap<Integer,List<String>>();
							 for(AttrEntity attrEntity : entityList) {
								 if(null == entityMap.get(attrEntity.getAttrId())) {
									 entityMap.put(attrEntity.getAttrId(), new ArrayList<String>());
								 }
								 entityMap.get(attrEntity.getAttrId()).add(attrEntity.getAttrEnName());
							 }
						 }
					 }
					 
				 }
			 }
			 if(null != attrMap && attrMap.size() > 0) {
				 for(GoodsAttrValue nameValue : goodsAttrList) {
					 GoodsAttrValueDTO gavd = new GoodsAttrValueDTO();
					 goodsAttrValueList.add(gavd);
					 BeanUtils.copyProperties(nameValue, gavd);
					 if(nameValue.getAttrId1() != null) {
						 GoodsAttribute attr = attrMap.get(nameValue.getAttrId1());
						 if(null != attr) {
							 gavd.setAttrName1(attr.getAttrName());
							 gavd.setIsEn1(attr.getIsEn());
							 if(null != entityMap && null != attr.getIsEn()&& (attr.getIsEn() == 0 || attr.getIsEn() == 2)) {
								 gavd.setAttrEnList1(entityMap.get(attr.getAttrId()));
							 } else {
								 gavd.setAttrValue1(nameValue.getAttrValue1());
							 }
						 }
					 }
					 if(nameValue.getAttrId2() != null) {
						 GoodsAttribute attr = attrMap.get(nameValue.getAttrId2());
						 if(null != attr) {
							 gavd.setAttrName2(attr.getAttrName());
							 gavd.setIsEn2(attr.getIsEn());
							 if(null != entityMap && null != attr.getIsEn()&& (attr.getIsEn() == 0 || attr.getIsEn() == 2)) {
								 gavd.setAttrEnList2(entityMap.get(attr.getAttrId()));
							 } else {
								 gavd.setAttrValue2(nameValue.getAttrValue2());
							 }
						 }
					 }
					 if(nameValue.getAttrId3() != null) {
						 GoodsAttribute attr = attrMap.get(nameValue.getAttrId3());
						 if(null != attr) {
							 gavd.setAttrName3(attr.getAttrName());
							 gavd.setIsEn3(attr.getIsEn());
							 if(null != entityMap && null != attr.getIsEn()&& (attr.getIsEn() == 0 || attr.getIsEn() == 2)) {
								 gavd.setAttrEnList3(entityMap.get(attr.getAttrId()));
							 } else {
								 gavd.setAttrValue3(nameValue.getAttrValue3());
							 }
						 }
					 }
					 if(nameValue.getAttrId4() != null) {
						 GoodsAttribute attr = attrMap.get(nameValue.getAttrId4());
						 if(null != attr) {
							 gavd.setAttrName4(attr.getAttrName());
							 gavd.setIsEn4(attr.getIsEn());
							 if(null != entityMap && null != attr.getIsEn()&& (attr.getIsEn() == 0 || attr.getIsEn() == 2)) {
								 gavd.setAttrEnList4(entityMap.get(attr.getAttrId()));
							 } else {
								 gavd.setAttrValue4(nameValue.getAttrValue4());
							 }
						 }
					 }
					 if(nameValue.getAttrId5() != null) {
						 GoodsAttribute attr = attrMap.get(nameValue.getAttrId5());
						 if(null != attr) {
							 gavd.setAttrName5(attr.getAttrName());
							 gavd.setIsEn5(attr.getIsEn());
							 if(null != entityMap && null != attr.getIsEn()&& (attr.getIsEn() == 0 || attr.getIsEn() == 2)) {
								 gavd.setAttrEnList5(entityMap.get(attr.getAttrId()));
							 } else {
								 gavd.setAttrValue5(nameValue.getAttrValue5());
							 }
						 }
					 }
					 
				 }
			 }
			 
		 }
		 return goodsAttrValueList;
	}
	
	/**
	 * 枚举批量查询
	 *@Title:getBatchSQL
	 *@Description:sql拼接
	 *@Params:@param enIdList enId的列表
	 *@Params:@return String 拼接后的sql
	 *@Return:String
	 *@author:Jacky
	 *@Date:2014-8-26下午09:13:36
	 */
	private String getEnBatchSQL(List<Integer> enIdList) {
		StringBuilder sb = new StringBuilder("from AttrEntity o where o.attrId in ( ");
		if(CollectionUtils.isNotEmpty(enIdList)) {
			for(int i = 0; i < enIdList.size(); i++ ) {
				sb.append(enIdList.get(i));
				if(i < enIdList.size()-1) {
					sb.append(",");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
	
	/**
	 * 
	 *@Title:getBatchSQL
	 *@Description:sql拼接
	 *@Params:@param attrList
	 *@Params:@return
	 *@Return:String
	 *@author:Jacky
	 *@Date:2014-8-26下午09:13:36
	 */
	private String getBatchSQL(List<Integer> attrList) {
		StringBuilder sb = new StringBuilder("from GoodsAttribute o where o.attrId in (");
		if(CollectionUtils.isNotEmpty(attrList)) {
			for(int i = 0; i < attrList.size(); i++ ) {
				sb.append(attrList.get(i));
				if(i < attrList.size()-1) {
					sb.append(",");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
	/**
	 * 
	 *@Title:batchCopy
	 *@Description:批量复制
	 *@Params:@param goodsAttrValueDTOList
	 *@Params:@return
	 *@Return:List<GoodsAttrValue>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:13:48
	 */
	private List<GoodsAttrValue> batchCopy(List<GoodsAttrValueDTO> goodsAttrValueDTOList) {
		List<GoodsAttrValue> list = new ArrayList<GoodsAttrValue>(goodsAttrValueDTOList.size());
		for(GoodsAttrValueDTO goodsAttr : goodsAttrValueDTOList) {
			GoodsAttrValue ga = new GoodsAttrValue();
			BeanUtils.copyProperties(goodsAttr, ga);
			list.add(ga);
		}
		return list;
	}
	
}
