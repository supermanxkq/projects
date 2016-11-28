package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;

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
	public void addSave(StoreInfoDTO storeInfoDTO);

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
