package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamilyAdvertRelation;
import com.paySystem.ic.bean.base.GoodsFamilyAttrRela;
import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.goods.TypeAttrDTO;


/**  
 * @Title: GoodsAttributeFormatDao.java
 * @Package: com.paySystem.ic.dao.base
 * @Description: 商品分类属性关联DAO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午11:12:41
 * @Version: V1.0  
 */

public interface GoodsFamilyAttrRelaDao extends DAO<GoodsFamilyAttrRela> {
	
	public final static String GOODSFAMILYATTRRELADAO="goodsFamilyAttrRelaDao";
	
	/**
	 * 
	 *@Title:delete
	 *@Description:删除与分组的关联
	 *@Params:@param familyId 类型ID
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-8-20下午10:17:11
	 */
	public void deleteByFamilyId(Integer familyId);

	/**
	 * 
	 *@Title:save
	 *@Description:保存属性符分组的关联
	 *@Params:@param familyAttrRela
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-8-20下午10:17:21
	 */
	public void save(GoodsFamilyAttrRelaDTO familyAttrRelaDTO);
	
	/**
	 * 
	 *@Title:getAssociatedFamilyId
	 *@Description:获取已经关联的类型
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:List
	 *@author:yanwuyang
	 *@Date:2014-8-24下午04:35:21
	 */
	public QueryResult<List> getAssociatedFamily(int firstPage, int pageNum,
			GoodsFamilyDTO familyDTO, LinkedHashMap<String, String> orderBy) throws Exception;
	
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
	
	/**
	 * 
	 *@Title:getFirstFamily
	 *@Description:获取第一个商品分类
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:GoodsFamily
	 *@author:yanwuyang
	 *@Date:2014-8-25下午5:28:30
	 */
	public GoodsFamilyDTO getFirstFamily() throws Exception;
	
	/**
	 * 
	 *@Title:findTypeAttrRelaList
	 *@Description:查找类型属性
	 *@Params:@param typeId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:List<TypeAttrDTO>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:05:59
	 */
	public List<TypeAttrDTO> findTypeAttrRelaList(int familyId) throws Exception;
	
	/**
	 * 
	 *@Title:queryAttrEntityList
	 *@Description:查找属性实体对象
	 *@Params:@param attrId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:List<AttrEntity>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:07:58
	 */
	public List<AttrEntity> queryAttrEntityList(int attrId) throws Exception;

	/**
	*@Title:findByFamilyId
	*@Description:通过商品分类ID查询
	*@Params:@param familyId
	*@Params:@return
	*@Return:GoodsFamilyAttrRelaDTO
	*@author:孟凡岭
	*@Date:2014-12-3上午09:40:10
	*/
	public GoodsFamilyAttrRelaDTO findByFamilyId(Integer familyId) throws Exception;

	/**
	*@Title:saveRel
	*@Description:保存实体
	*@Params:@param g
	*@Return:void
	*@author:孟凡岭
	*@Date:2014-12-3上午10:41:08
	*/
	public void saveRel(GoodsFamilyAttrRela rel);
}
