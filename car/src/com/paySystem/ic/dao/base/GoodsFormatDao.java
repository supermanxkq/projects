package com.paySystem.ic.dao.base;

import java.util.List;

import com.paySystem.ic.bean.base.GoodsFormat;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.GoodsFormatDTO;


/**  
 * @Title: GoodsAttributeFormatDao.java
 * @Package: com.paySystem.ic.dao.base
 * @Description: 商品规格DAO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午11:12:41
 * @Version: V1.0  
 */

public interface GoodsFormatDao extends DAO<GoodsFormat> {
	
	public final static String GOODSFORMATDAO="goodsFormatDao";
	
	/**
	 * 
	 *@Title:save
	 *@Description:保存规格
	 *@Params:@param formatDTO
	 *@Params:@return
	 *@Return:Integer
	 *@author:yanwuyang
	 *@Date:2014-8-22下午6:05:34
	 */
	public Integer save(GoodsFormatDTO formatDTO);
	
	/**
	 * 
	 *@Title:getFormatByGroup
	 *@Description:根据分组ID获取规格
	 *@Params:@param groupId
	 *@Params:@return
	 *@Return:QueryResult<GoodsFormatDTO>
	 *@author:yanwuyang
	 *@Date:2014-8-22下午10:29:11
	 */
	public List getFormatsByGroup(Integer groupId) throws Exception;
	
	/**
	 * 
	 *@Title:getFormatByFamilyId
	 *@Description:根据分类ID获取规格
	 *@Params:@param familyId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:QueryResult<GoodsFormat>
	 *@author:yanwuyang
	 *@Date:2014-8-24下午12:28:07
	 */
	public List<GoodsFormatDTO> getFormatByFamilyId(Integer familyId) throws Exception;

	
	/**
	 * 
	 *@Title:checkName
	 *@Description:校验名称是否存在
	 *@Params:@param name
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:boolean
	 *@author:yanwuyang
	 *@Date:2014-8-24下午09:50:46
	 */
	public boolean checkName(String name,Integer fgroupId) throws Exception;
	
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
	public List<GoodsFormatDTO> getParentFormatByFamilyId(Integer familyId)throws Exception ;
}
