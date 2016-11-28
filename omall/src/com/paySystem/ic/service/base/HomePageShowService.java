package com.paySystem.ic.service.base;

import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.bean.base.UndealServiceNum;

/**
 * @ProjectName:omall
 * @ClassName:HomePageShowService
 * @Description:首页展示内容的Service接口
 * @date: 2014-11-21
 * @author: 王楠
 * @version: V1.0
 */
public interface HomePageShowService {

	public static final String HOMEPAGESHOWSERVICE="homePageShowService";
	
	/**
	*@Title:findByOrgId
	*@Description:根据机构编号查找机构信息
	*@Params:@param organId 机构编号
	*@Params:@return
	*@Params:@throws Exception
	*@Return:Organs
	*@author:王楠
	*@Date:2014-11-21下午12:07:20
	*/
	public Organs findByOrgId(String organId)throws Exception;
	
	/**
	*@Title:findByStoId
	*@Description:根据商户编号查询店铺信息
	*@Params:@param merId 商户编号
	*@Params:@return
	*@Params:@throws Exception
	*@Return:StoreInfo
	*@author:王楠
	*@Date:2014-11-21下午12:08:23
	*/
	public StoreInfo findByStoId(String merId)throws Exception;
	
}
