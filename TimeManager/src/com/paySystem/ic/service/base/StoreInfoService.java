package com.paySystem.ic.service.base;

import com.paySystem.ic.web.dto.base.StoreInfoDTO;

/**
 * @ProjectName:omall
 * @ClassName:StoreInfoService
 * @Description:店铺基本设置服务类
 * @date: 2014-9-23下午05:29:34
 * @author: 徐凯强
 * @version: V1.0
 */
public interface StoreInfoService {
	/** 常量 */
	public static final String STOREINFOSERVICE = "StoreInfoService";

	/**
	 *@Title:addSave
	 *@Description:保存店铺基本设置信息
	 *@param storeInfoDTO店铺基本设置数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-9-23下午05:30:17
	 */
	public void addSave(StoreInfoDTO storeInfoDTO);

	/**
	 *@Title:findById
	 *@Description:根据店铺编号查找数据库中的记录
	 *@param storeId店铺编号
	 *@Return:StoreInfoDTO店铺数据传输对象
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:13:37
	 */
	public StoreInfoDTO findById(Integer storeId);

	/**
	 *@Title:updateStoreInfo
	 *@Description:更新
	 *@param storeInfoDTO店铺设置数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:53:02
	 */
	public void updateStoreInfo(StoreInfoDTO storeInfoDTO);
}