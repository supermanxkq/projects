package com.paySystem.ic.dao.base;

import java.util.List;

import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.AreaDicDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;
import com.paySystem.ic.web.dto.goods.StoreGtypeRelDTO;

public interface StoreInfoDAO extends DAO<StoreInfo> {

	/** 常量 */
	public static final String STOREINFODAO = "StoreInfoDAO";

	/**
	 *@Title:addSave
	 *@Description:保存店铺设置信息
	 *@param storeInfoDTO店铺设置信息数据传输
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-9-23下午05:36:37
	 */
	public StoreInfo addSave(StoreInfoDTO storeInfoDTO);

	/**
	 *@Title:updateStoreInfo
	 *@Description:更新
	 *@param storeInfoDTO店铺设置数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:53:02
	 */
	public StoreInfo updateStoreInfo(StoreInfoDTO storeInfoDTO);

	/**
	 *@Title:findProvinces
	 *@Description:查询所有
	 *@Return:List<AreaDicDTO>数据传输对象
	 *@author:徐凯强
	 *@Date:2014-11-3下午06:33:07
	 */
	public List<AreaDicDTO> findProvinces();

	/**
	 *@Title:findCityByProvinceId
	 *@Description:根据省查找省下面的城市
	 *@param areaDicDTO数据传输对象
	 *@Return:List<AreaDicDTO>数据传输对象集合
	 *@author:徐凯强
	 *@Date:2014-11-4上午11:18:38
	 */
	public List<AreaDicDTO> findCityByProvinceId(AreaDicDTO areaDicDTO);

	/**
	 *@Title:chooseTypes
	 *@Description:选择商品分类
	 *@Return:List<GoodsFamilyDTO>商品分类集合
	 *@author:徐凯强
	 *@Date:2014-11-4下午04:23:44
	 */
	public List<GoodsFamilyDTO> chooseTypes();

	/**
	 *@Title:insertRel
	 *@Description:插入商品类型和店铺关系id
	 *@param storeGtypeRelDTO
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-6上午11:11:22
	 */
	public void insertRel(StoreGtypeRelDTO storeGtypeRelDTO);

	/**
	 *@Title:deleteAll
	 *@Description:删除所有已选分类
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-6下午12:52:47
	 */
	public void deleteAll();

	/**
	 *@Title:findCheckedTypes
	 *@Description:查找所有已选商品分类
	 *@Return:List<GoodsFamilyDTO>数据传输对象集合
	 *@author:徐凯强
	 *@Date:2014-11-6下午01:20:41
	 */
	public List<GoodsFamilyDTO> findCheckedTypes();

	/**
	 *@Title:deleteById
	 *@Description:删除根据编号
	 *@param storeGtypeRelDTO数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-10下午04:18:26
	 */
	public void deleteById(StoreGtypeRelDTO storeGtypeRelDTO);
	
	/**
	 * 
	 *@Title: saveStore
	 *@Description: 批量增加店铺
	 *@Params: @param storeInfoDTO
	 *@Params: @throws Exception
	 *@Return: void
	 *@author: 郭营
	 *@Date: 2014-11-19下午02:27:46
	 */
	public void  saveStore(StoreInfoDTO storeInfoDTO )throws Exception;

	/**
	*@Title:findByMerId
	*@Description:根据merId查询店铺信息
	*@Params:@param merId
	*@Params:@return
	*@Return:StoreInfo
	*@author:孟凡岭
	*@Date:2014-11-24下午05:31:35
	*/
	public StoreInfo findByMerId(String merId);

	/**
	*@Title:ajaxObject
	*@Description:验证广告对象
	*@Params:@param id
	*@Params:@return
	*@Return:boolean
	*@author:孟凡岭
	*@Date:2014-12-8上午11:19:36
	*/
	public boolean ajaxObject(String id);
	
	
}
