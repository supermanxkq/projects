package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.AttrEntityDAO;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.goods.AttrEntityDTO;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityDAOImpl
 * @Description:商品属性值DAO实现类
 * @date: 2014-10-10下午04:34:52
 * @author: 徐凯强
 * @version: V1.0
 */
@Repository(AttrEntityDAO.ATTRENTITYDAO)
public class AttrEntityDAOImpl extends DaoSupport<GoodsAttribute> implements AttrEntityDAO {
    /**
     *@Title:findAll
     *@Description:查询数据库中所有的属性名称，用于显示在下拉框中
     *@param firstindex分页的首个参数
     *@param pageNum每页有多少条数据
     *@param goodsAttributeDTO商品属性数据传输对象
     *@param orderBy排序参数
     *@Return:QueryResult<GoodsAttributeDTO>商品属性记录和总条数集合
     *@author:徐凯强
     *@Date:2014-10-12下午04:15:49
     */
    public QueryResult<GoodsAttribute> findAll(int firstindex, int pageNum, GoodsAttributeDTO goodsAttributeDTO,
            LinkedHashMap<String, String> orderBy) {
        /** jpql语句参数值 */
        List<Object> params = new ArrayList<Object>();
        /** 要返回的集合 */
        QueryResult<GoodsAttribute> gQueryResult = new QueryResult<GoodsAttribute>();
        /** 要执行的SQL语句 */
        StringBuffer stringBuffer = new StringBuffer();
        /** 判断属性名称是否为空，如果不为空，进行查询 */
        if (goodsAttributeDTO.getAttrName() != null && goodsAttributeDTO.getAttrName() != ""
                && !goodsAttributeDTO.getAttrName().equals("全部")) {
            stringBuffer.append(" and o.attrName like ?");
            params.add("%" + goodsAttributeDTO.getAttrName() + "%");
        }

        try {
            /** 获取数据库中所有属性记录 */
            gQueryResult = this.getScrollData(firstindex, pageNum, stringBuffer.toString(), params.toArray(), orderBy);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return gQueryResult;
    }

    /**
     *@Title:showAttrEntities
     *@Description:查询数据库中所有的属性值，用于显示在下拉框中
     *@param firstindex分页的首个参数
     *@param pageNum每页有多少条数据
     *@param attrEntityDTO商品属性值数据传输对象
     *@param goodsAttributeDTO商品属性数据传输对象
     *@param orderBy排序参数
     *@Return:QueryResult<AttrEntityDTO>商品属性值记录和总条数集合
     *@author:徐凯强
     *@Date:2014-10-12下午04:15:49
     */
    public QueryResult<AttrEntityDTO> showAttrEntities(int firstindex, int pageNum, AttrEntityDTO attrEntityDTO,
            GoodsAttributeDTO goodsAttributeDTO, LinkedHashMap<String, String> orderBy) {
        /** jpql语句参数值 */
        List<Object> params = new ArrayList<Object>();
        /** 要返回的集合 */
        QueryResult<AttrEntityDTO> gQueryResultList = new QueryResult<AttrEntityDTO>();
        /** 数据库中的数据集合 */
        QueryResult<AttrEntity> gQueryResult = new QueryResult<AttrEntity>();

        /** 要执行的SQL语句 */
        StringBuffer stringBuffer = new StringBuffer();
        /** 查询属性下面对应的属性值 */
        stringBuffer.append(" and o.attrId=?");
        params.add(goodsAttributeDTO.getAttrId());
        try {
            /** 获取数据库中所有属性记录 */
            gQueryResult = queryAttrEntities(firstindex, pageNum, stringBuffer.toString(), params.toArray(), orderBy);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        List<AttrEntityDTO> attrEntityDTOs = changeEntityToDTO(gQueryResult.getResultlist());
        gQueryResultList.setResultlist(attrEntityDTOs);
        gQueryResultList.setTotalrecord(gQueryResult.getTotalrecord());
        return gQueryResultList;
    }

    /**
     *@Title:queryAttrEntities
     *@Description:查询数据并分页
     *@param firstindex分页的首个参数
     *@param maxresult当前页有多少条数据
     *@param wherejpql查询条件
     *@param queryParams参数值
     *@param orderby排序参数
     *@throws Exception抛出异常
     *@Return:QueryResult<AttrEntity>集合和总条数集合
     *@author:徐凯强
     *@Date:2014-10-12下午08:41:56
     */
    @SuppressWarnings("unchecked")
    public QueryResult<AttrEntity> queryAttrEntities(int firstindex, int maxresult, String wherejpql,
            Object[] queryParams, LinkedHashMap<String, String> orderby) throws Exception {
        QueryResult<AttrEntity> qr = new QueryResult<AttrEntity>();
        Query query = em.createQuery("select o from  AttrEntity" + " o "
                + (wherejpql == null || "".equals(wherejpql.trim()) ? "" : "where 1=1 " + wherejpql)
                + buildOrderby(orderby));
        setQueryParams(query, queryParams);
        /** 获取所有的记录 **/
        if (firstindex != -1 && maxresult != -1) {
            query.setFirstResult(firstindex).setMaxResults(maxresult);
        }
        qr.setResultlist(query.getResultList());
        query = em.createQuery("select count(o.attrEnId) from AttrEntity" + " o "
                + (wherejpql == null || "".equals(wherejpql.trim()) ? "" : "where 1=1 " + wherejpql));
        setQueryParams(query, queryParams);
        qr.setTotalrecord((Long) query.getSingleResult());
        return qr;
    }

    /**
     *@Title:changeEntityToDTO
     *@Description:将属性值实体转换为DTO
     *@param attrEntities实体集合
     *@Return:List<AttrEntityDTO>数据传输对象集合
     *@author:徐凯强
     *@Date:2014-10-12下午08:47:31
     */
    public List<AttrEntityDTO> changeEntityToDTO(List<AttrEntity> attrEntities) {
        /** 要返回的DTO集合 */
        List<AttrEntityDTO> attrEntityDTOs = new ArrayList<AttrEntityDTO>();
        /** 循环将实体转换为DTO */
        for (AttrEntity attrEntity : attrEntities) {
            AttrEntityDTO attrEntityDTO = new AttrEntityDTO();
            attrEntityDTO.setAttrId(attrEntity.getAttrId());
            attrEntityDTO.setAttrEnId(attrEntity.getAttrEnId());
            attrEntityDTO.setAttrEnName(attrEntity.getAttrEnName());
            attrEntityDTO.setStatus(attrEntity.getStatus());
            /** 将转换后的DTO添加到要返回的集合中 */
            attrEntityDTOs.add(attrEntityDTO);
        }
        /** 返回集合 */
        return attrEntityDTOs;
    }

    /**
     *@Title:addSave
     *@Description:保存属性值记录
     *@param attrEntityDTO数据传输对象
     *@Return:void返回值
     *@author:徐凯强
     *@Date:2014-10-12下午04:15:49
     */
    public void addSave(AttrEntityDTO attrEntityDTO) {
        this.save(changeDTOTOEntity(attrEntityDTO));
    }

    /**
     *@Title:changeDTOTOEntity
     *@Description:将数据传输对象DTO转换为实体
     *@param attrEntityDTO数据传输对象
     *@Return:AttrEntity要返回的实体对象
     *@author:徐凯强
     *@Date:2014-10-13上午12:38:25
     */
    public AttrEntity changeDTOTOEntity(AttrEntityDTO attrEntityDTO) {
        /** 实例化实体对象 */
        AttrEntity attrEntity = new AttrEntity();
        /** 转换 */
        attrEntity.setAttrEnId(attrEntityDTO.getAttrEnId());
        attrEntity.setAttrEnName(attrEntityDTO.getAttrEnName());
        attrEntity.setAttrId(attrEntityDTO.getAttrId());
        attrEntity.setStatus(attrEntityDTO.getStatus());
        /** 返回 */
        return attrEntity;
    }

    /**
     *@Description:根据属性id获取属性名称
     *@param:@param attrId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public String getAttrName(Integer attrId) {

        String attrName = (String) em.createNativeQuery("select o.attrName from b_attribute o where o.attrId = ?1")
                .setParameter(1, attrId).getSingleResult();

        return attrName;
    }

    /**
     *@Description:检验属性值名称是否已存在
     *@param:@param attrEnName
     *@param:@param attrId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public boolean checkAttrEnName(String attrEnName, Integer attrId) {
        Integer size = em.createNativeQuery("select * from B_AttrEn o where o.attrEnName = ?1 and o.attrId = ?2")
                .setParameter(1, attrEnName).setParameter(2, attrId).getResultList().size();
        return size > 0;
    }

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.AttrEntityDAO#checkAttrEnName(java.lang.String)
	 *@MethodName: checkAttrEnName
     *@Description: 检验属性值名称是否存在
     *@Params: attrEnName 属性值名称
	 *@Params: @return
	 *@Author: 王少辉
	 *@Date: 2014-12-4 下午06:24:47
	 */
	@Override
	public boolean checkAttrEnName(String attrEnName) {
		Integer size = em.createNativeQuery("select * from B_AttrEn o where o.attrEnName = ?1").setParameter(1, attrEnName).getResultList().size();
		
		return size > 0;
	}
}
