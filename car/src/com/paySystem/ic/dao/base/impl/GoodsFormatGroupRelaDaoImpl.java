package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.GoodsFormatGroupRela;
import com.paySystem.ic.dao.base.GoodsFormatGroupRelaDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupInfoDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupRelaDTO;


/**  
 * @Title: GoodsAttributeDaoImpl.java
 * @Package: com.paySystem.ic.dao.base.impl
 * @Description: 商品规格分组DAO实现类
 * @Author: yanwuyang 
 * @Date: 2014-8-20 下午09:51:17
 * @Version: V1.0  
 */

@Repository(GoodsFormatGroupRelaDao.GOODSFORMATGROUPRELADAO)
public class GoodsFormatGroupRelaDaoImpl extends DaoSupport<GoodsFormatGroupRela> implements
	GoodsFormatGroupRelaDao {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFormatGroupRelaDao#save(com.paySystem.ic.web.dto.base.GoodsFormatGroupRelaDTO)
	 *@MethodName:save
	 *@Description:保存
	 *@param formatGroupRelaDTO
	 *@Author:yanwuyang
	 *@Date:2014-9-23下午2:55:00
	 */
	public void save(GoodsFormatGroupRelaDTO formatGroupRelaDTO) {
		GoodsFormatGroupRela formatGroupRela = new GoodsFormatGroupRela();
		BeanUtils.copyProperties(formatGroupRelaDTO, formatGroupRela);
		this.save(formatGroupRela);
		
	}
	
	
	/**
	 * 
	 *@Title: findGoodsFormatGroupRelaByFormatId
	 *@Description: 根据formatIds批量查找groupId 和名称信息
	 *@Params:@param formatSet 规格set
	 *@Return: List<GoodsFormatGroupInfoDTO> 分组对应名称
	 *@author: Jacky
	 *@Date:2014-8-22下午6:13:11
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsFormatGroupInfoDTO> findGoodsFormatGroupRelaByFormatId(
			Set<Integer> formatSet) {
		List<GoodsFormatGroupInfoDTO> groupInfoList = new ArrayList<GoodsFormatGroupInfoDTO>();
		List resultList = em.createNativeQuery(getBatchQuerySQL(formatSet)).getResultList();
		for(Object obj : resultList) {
			Object[] o = (Object[])obj;
			GoodsFormatGroupInfoDTO gfg = new GoodsFormatGroupInfoDTO();
			gfg.setFormatId((Integer)o[0]);
			gfg.setfGroupName(o[1]==null?"":o[1].toString());
			gfg.setFormatName(o[2]==null?"":o[2].toString());
			groupInfoList.add(gfg);
		}
		return groupInfoList;
	}
	
	/**
	 * 
	 *@Title: getBatchQuerySQL
	 *@Description: 获取批量查询SQL
	 *@Params:@param formatSet 规格set
	 *@Return: String 查询sql
	 *@author: Jacky
	 *@Date:2014-8-22下午6:13:11
	 */
	private String getBatchQuerySQL(Set<Integer> formatSet){
		StringBuilder sb = new StringBuilder("select o1.formatId,o2.fgroupName,o3.formatName from b_formatgrouprela as o1 left outer join  b_formatgroup as o2 on  o1.fgroupId=o2.fgroupId left outer join b_format as o3 on o1.formatId=o3.formatId where  o1.formatId in (");
		if(CollectionUtils.isNotEmpty(formatSet)) {
			int i=0;
			for(Integer fomatId : formatSet) {
				i++;
				sb.append(fomatId);
				if(i != formatSet.size()) {
					sb.append(",");
				}
			}
			/** 查询时候不会出现  formatSet.size==0,如果出现sql不完整 就报错,因为不能让其查全部属性**/
			sb.append(")");
		}
		return sb.toString();
	}


	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.GoodsFormatGroupRelaDao#getAllGoodsFormatGroupRela()
	 *@MethodName:getAllGoodsFormatGroupRela
	 *@Description:获取所有的规格与分组的关联
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-8-24下午01:40:56
	 */
	public List<GoodsFormatGroupRelaDTO> getAllGoodsFormatGroupRela() {
		List<GoodsFormatGroupRelaDTO> groupList = new ArrayList<GoodsFormatGroupRelaDTO>();
		List<Object[]> oList=em.createNativeQuery("select b.fgrId,b.fgroupId,b.formatId,g.fgroupName from b_formatgrouprela as b left join b_formatgroup as g on b.fgroupId=g.fgroupId").getResultList();
		for(Object obj : oList) {
			Object[] o = (Object[])obj;
			GoodsFormatGroupRelaDTO gg = new GoodsFormatGroupRelaDTO();
			gg.setFgrId((Integer)o[0]);
			gg.setFgroupId((Integer)o[1]);
			gg.setFormatId((Integer)o[2]);
			gg.setFgroupName((String)o[3]);
			groupList.add(gg);
		}
		return groupList;
	}
	
}
