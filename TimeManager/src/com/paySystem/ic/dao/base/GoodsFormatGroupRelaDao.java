package com.paySystem.ic.dao.base;

import java.util.List;
import java.util.Set;

import com.paySystem.ic.bean.base.GoodsFormatGroupRela;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupInfoDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupRelaDTO;


/**  
 * @Title: GoodsAttributeFormatDao.java
 * @Package: com.paySystem.ic.dao.base
 * @Description: 规格分组关联表DAO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午11:12:41
 * @Version: V1.0  
 */

public interface GoodsFormatGroupRelaDao extends DAO<GoodsFormatGroupRela> {
	
	public final static String GOODSFORMATGROUPRELADAO="goodsFormatGroupRelaDao";
	
	/**
	 * 
	 *@Title:save
	 *@Description:保存规格与分组的关联
	 *@Params:@param formatGroupRelaDTO
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-8-22下午6:13:11
	 */
	public void save(GoodsFormatGroupRelaDTO formatGroupRelaDTO);
	
	/**
	 * 
	 *@Title: findGoodsFormatGroupRelaByFormatId
	 *@Description: 根据formatIds批量查找groupId 和名称信息
	 *@Params:@param formatSet 规格set
	 *@Return: List<GoodsFormatGroupInfoDTO> 分组对应名称
	 *@author: Jacky
	 *@Date:2014-8-22下午6:13:11
	 */
	public List<GoodsFormatGroupInfoDTO> findGoodsFormatGroupRelaByFormatId(Set<Integer> formatSet);
	
	/**
	 * 
	 *@Title:getAllGoodsFormatGroupRela
	 *@Description:获取所有的规格分组关联
	 *@Params:@return
	 *@Return:List<GoodsFormatGroupRelaDTO>
	 *@author:yanwuyang
	 *@Date:2014-8-24下午01:40:11
	 */
	public List<GoodsFormatGroupRelaDTO> getAllGoodsFormatGroupRela();
	

}
