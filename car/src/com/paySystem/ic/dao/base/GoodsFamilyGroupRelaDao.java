package com.paySystem.ic.dao.base;


import java.util.List;

import com.paySystem.ic.bean.base.GoodsFamilyGroupRela;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.GoodsFamilyGroupRelaDTO;


/**  
 * @Title: GoodsFamilyGroupRela.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品分类规格分组关联Dao
 * @Author: yanwuyang 
 * @Date: 2014-8-21 下午11:58:09
 * @Version: V1.0  
 */
public interface GoodsFamilyGroupRelaDao extends DAO< GoodsFamilyGroupRela> {

	public final static String GOODSFAMILYGROUPRELADAO="goodsFamilyGroupRelaDao";
	
	
	/**
	 * 
	 *@Title: findGoodsFormatListByFamilyId
	 *@Description: 根据分类id查询规格
	 *@Params:@param familyId
	 *@Return: List<GoodsFamilyGroupRela> 和分类关联的规格
	 *@author: Jacky
	 *@Date:2014-8-22下午6:05:34
	 */
	public List<GoodsFamilyGroupRela> findGoodsFormatListByFamilyId(int familyId) throws Exception;
	
	/**
	 * 
	 *@Title:save
	 *@Description:保存规格与分组的关联
	 *@Params:@param familyGroupRelaDTO
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-8-24上午10:30:05
	 */
	public void save(GoodsFamilyGroupRelaDTO familyGroupRelaDTO);
	
	/**
	 * 
	 *@Title:delete
	 *@Description:删除规格与分组的关联
	 *@Params:@param familyId
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-8-24上午10:44:35
	 */
	public void deleteByFamilyId(Integer familyId);
	
	/**
	 * 
	 *@Title:updateStautsByFamilyId
	 *@Description:修改状态
	 *@Params:@param familyId
	 *@Params:@param status
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-8-24下午11:08:02
	 */
	public void updateStautsByFamilyId(Integer familyId,Integer status);
	
}	
