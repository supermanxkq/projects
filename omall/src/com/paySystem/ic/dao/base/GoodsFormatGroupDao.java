package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFormatGroup;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupDTO;


/**  
 * @Title: GoodsAttributeFormatDao.java
 * @Package: com.paySystem.ic.dao.base
 * @Description: 商品规格分组DAO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午11:12:41
 * @Version: V1.0  
 */

public interface GoodsFormatGroupDao extends DAO<GoodsFormatGroup> {
	
	public final static String GOODSFORMATGROUPDAO="goodsFormatGroupDao";
	
	/**
	 * 
	 *@Title:save
	 *@Description:保存规格分组
	 *@Params:@param formatGroupDTO
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-8-22下午3:29:55
	 */
	public void save(GoodsFormatGroupDTO formatGroupDTO);
	
	/**
	 *@Title: saveObject
	 *@Description: 保存规格分组
	 *@Params: formatGroupDTO 规格分组
	 *@Return: Integer 返回规格分组编号
	 *@author: 王少辉
	 *@Date: 2014-11-28 下午04:24:17
	 */
	Integer saveObject(GoodsFormatGroupDTO formatGroupDTO);
	
	/**
	 * 
	 *@Title:getAllFormatGruops
	 *@Description:查询所有的规格分组
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:QueryResult<GoodsFormatGroupDTO>
	 *@author:yanwuyang
	 *@Date:2014-8-22下午3:36:42
	 */
	public QueryResult<GoodsFormatGroupDTO> getAllFormatGruops() throws Exception;
	
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
	public boolean checkName(String name) throws Exception;

}
