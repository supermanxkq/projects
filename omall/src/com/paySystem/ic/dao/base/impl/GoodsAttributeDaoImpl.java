package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.dao.base.GoodsAttributeDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;

/**  
 * @Title: GoodsAttributeDaoImpl.java
 * @Package: com.paySystem.ic.dao.base.impl
 * @Description: 商品属性dao实现类
 * @Author: yanwuyang 
 * @Date: 2014-8-20 下午09:51:17
 * @Version: V1.0  
 */

@Repository(GoodsAttributeDao.GOODSATTRIBUTEDAO)
public class GoodsAttributeDaoImpl extends DaoSupport<GoodsAttribute> implements GoodsAttributeDao {

    public void delete(Integer id) {

    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsAttributeDao#save(com.paySystem.ic.web.dto.base.GoodsAttributeDTO)
     *@MethodName:save
     *@Description:保存商品属性
     *@param attributeDTO
     *@Author:yanwuyang
     *@Date:2014-8-21下午10:23:07
     */
    public void save(GoodsAttributeDTO attributeDTO) {
        GoodsAttribute attribute = new GoodsAttribute();
        BeanUtils.copyProperties(attributeDTO, attribute);
		this.save(attribute);
    }
    
    /**
     *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsAttributeDao#edit(com.paySystem.ic.web.dto.base.GoodsAttributeDTO)
     *@MethodName:edit
     *@Description:修改属性
     *@param attributeDTO
     *@Author:王楠
     *@Date:2014-12-1下午02:55:40
     */
    public void edit(GoodsAttributeDTO attributeDTO){
    	GoodsAttribute attribute = new GoodsAttribute();
    	BeanUtils.copyProperties(attributeDTO, attribute);
    	this.update(attribute);
    }
    
    /**
     *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsAttributeDao#saveAttr(com.paySystem.ic.web.dto.base.GoodsAttributeDTO)
     *@MethodName: saveAttr
     *@Description: 保存商品属性（带返回值）
     *@Params: attributeDTO 商品属性
     *@return: Integer 返回商品属性编号
     *@Author: 王少辉
     *@Date: 2014-11-21 上午10:54:40
     */
    public Integer saveAttr(GoodsAttributeDTO attributeDTO) {
    	// 校验名称是否存在
		Integer id = findByName(attributeDTO.getAttrName());
		if(id != null){
			return id;
		}
    	
    	GoodsAttribute attribute = new GoodsAttribute();
    	BeanUtils.copyProperties(attributeDTO, attribute);
    	this.save(attribute);
    	return attribute.getAttrId();
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsAttributeDao#getAllAttributes()
     *@MethodName:getAllAttributes
     *@Description:获取所有商品属性
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-21下午10:23:22
     */
    public QueryResult<GoodsAttributeDTO> getAllAttributes() throws Exception {
        QueryResult<GoodsAttribute> qr = new QueryResult<GoodsAttribute>();
        Query query = this.em.createQuery("from GoodsAttribute");
        qr.setResultlist(query.getResultList());
        return dtoResult2beanResult(qr);
    }

    /**
     * 
     *@Title:dtoResult2beanResult
     *@Description:dto转bean
     *@Params:@param list
     *@Params:@return
     *@Return:QueryResult<GoodsAttributeDTO>
     *@author:yanwuyang
     *@Date:2014-9-23下午2:52:48
     */
    private QueryResult<GoodsAttributeDTO> dtoResult2beanResult(QueryResult<GoodsAttribute> list) {
        QueryResult<GoodsAttributeDTO> dtoResult = new QueryResult<GoodsAttributeDTO>();
        List<GoodsAttributeDTO> dtoList = new ArrayList<GoodsAttributeDTO>();
        if (list != null) {
            /** 遍历实体Result信息，获取支付参数实体对象*/
            for (GoodsAttribute attribute : list.getResultlist()) {
                /** 将支付参数实体对象转为支付参数DTO对象*/
                GoodsAttributeDTO attributeDTO = new GoodsAttributeDTO();
                BeanUtils.copyProperties(attribute, attributeDTO);
                /** 添加到DTO对象集合中*/
                dtoList.add(attributeDTO);
            }
        }
        /**组装DtoResult信息 DTO对象集合及总条数*/
        dtoResult.setResultlist(dtoList);
        dtoResult.setTotalrecord(list.getTotalrecord());

        return dtoResult;
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsAttributeDao#getAttributeByFamilyId(java.lang.Integer)
     *@MethodName:getAttributeByFamilyId
     *@Description:根据分类ID获取属性
     *@param familyId
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-24下午12:41:49
     */
    public List<GoodsAttributeDTO> getAttributeByFamilyId(Integer familyId) throws Exception {
        Query query = em
                .createNativeQuery("select a.attrId,a.attrName from b_attribute as a where a.attrId in (select fa.attrId from b_familyattrrela as fa where fa.familyId=?)");
        query.setParameter(1, familyId);
        List<Object[]> attrObjs = query.getResultList();
        if (CollectionUtils.isNotEmpty(attrObjs)) {
            List<GoodsAttributeDTO> attrReturnList = new ArrayList<GoodsAttributeDTO>(attrObjs.size());
            for (Object[] obj : attrObjs) {
                GoodsAttributeDTO fa = new GoodsAttributeDTO();
                fa.setAttrId((Integer) obj[0]);
                fa.setAttrName(obj[1] != null ? obj[1].toString() : "");
                attrReturnList.add(fa);
            }
            return attrReturnList;
        }
        //查询父类的属性

        return null;
    }

    /**
     * 
     *@Title:getParnetAttributeByFamilyId
     *@Description:查询父类的属性
     *@Params:@param familyId
     *@Params:@return
     *@Params:@throws Exception
     *@Return:List<GoodsAttributeDTO>
     *@author:yanwuyang
     *@Date:2014-8-25下午3:38:20
     */
    public List<GoodsAttributeDTO> getParnetAttributeByFamilyId(Integer familyId) throws Exception {
        List<Integer> familyIds = new ArrayList<Integer>();
        getParentFamilyId(familyIds, familyId);
        Query query = em.createNativeQuery("select a.attrId,a.attrName from b_attribute as a where a.attrId in"
                + " (select fa.attrId from b_familyattrrela as fa where fa.familyId in (" + getBatchSQL(familyIds)
                + "))");
        List<Object[]> attrObjs = query.getResultList();
        if (CollectionUtils.isNotEmpty(attrObjs)) {
            List<GoodsAttributeDTO> attrReturnList = new ArrayList<GoodsAttributeDTO>(attrObjs.size());
            for (Object[] obj : attrObjs) {
                GoodsAttributeDTO fa = new GoodsAttributeDTO();
                fa.setAttrId((Integer) obj[0]);
                fa.setAttrName(obj[1] != null ? obj[1].toString() : "");
                fa.setIsParent("true");
                attrReturnList.add(fa);
            }
            return attrReturnList;
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
    private String getBatchSQL(List<Integer> familyIds) {
        if (familyIds.size() == 0) {
            return "0";
        }
        String sql = "";
        for (int i = 0; i < familyIds.size(); i++) {
            if (!sql.equals("")) {
                sql += ",";
            }
            sql += familyIds.get(i);
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
    private void getParentFamilyId(List<Integer> list, Integer familyId) {
        Query query = em.createNativeQuery("select parentId from b_goodsfamily where familyid=?");
        query.setParameter(1, familyId);
        Object o = query.getSingleResult();
        Integer parentId = (Integer) o;
        if (parentId != 0) {
            list.add(parentId);
            getParentFamilyId(list, parentId);
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
    public boolean checkName(String name) throws Exception {
    	Query query = em.createNativeQuery("select b.attrId from b_attribute as b where b.attrName=?");
    	query.setParameter(1, name);
    	return query.getResultList().size() >= 1;
    }
    
    /**
     *@Title: findByName
     *@Description: 校验名称，存在时返回属性编号，不存在时返回null
     *@Params: name 属性名称
     *@Return: Integer 属性编号
     *@author: 王少辉
     *@Date: 2014-12-4 下午06:15:43
     */
    public Integer findByName(String name) {
        Query query = em.createNativeQuery("select b.attrId from b_attribute as b where b.attrName=?");
        query.setParameter(1, name);
        try {
			return (Integer) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
    }

    /**
     *@Description:校验显示名称是否存在
     *@param:@param displayName
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public boolean checkDisPlayName(String showLable) {
        Query query = em.createNativeQuery("select * from b_attribute b where b.showlable=?1");
        query.setParameter(1, showLable);
        return query.getResultList().size() >= 1;
    }

}
