package com.paySystem.ic.service.base;

import com.paySystem.ic.web.dto.base.MemIntegral;

/**
 * @ProjectName:omall
 * @ClassName:MemIntegralService
 * @Description:商城会员积分的Service接口
 * @date: 2014-9-18
 * @author: 王楠
 * @version: V1.0
 */
/**
 * @ProjectName:omall
 * @ClassName:MemIntegralService
 * @Description:商城会员积分的Service接口
 * @date: 2014-9-18
 * @author: 王楠
 * @version: V1.0
 */
public interface MemIntegralService {

	public static final String MEMINTEGRALSERVICE="memIntegralService";
	
	/**
	*@Title:queryByMemId
	*@Description:根据会员编号查询商城积分信息
	*@Params:@param memId
	*@Params:@return
	*@Return:MemIntegral
	*@author:王楠
	*@Date:2014-9-18下午02:30:51
	*/
	public MemIntegral queryByMemId(String memId);
}
