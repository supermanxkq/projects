package com.paySystem.ic.dao.base;

import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.MemIntegral;

/**
 * @ProjectName:omall
 * @ClassName:MemIntegralDAO
 * @Description:商城会员积分DAO接口
 * @date: 2014-9-18
 * @author: 王楠
 * @version: V1.0
 */
public interface MemIntegralDAO extends DAO<MemIntegral>{

	public static final String MEMINTEGRALDAO="MemIntegralDAO";
	
	/**
	*@Title:queryByMemId
	*@Description:根据会员编号查询积分信息
	*@Params:@param memId 会员编号
	*@Params:@return
	*@Return:MemIntegral 商城会员积分实体
	*@author:王楠
	*@Date:2014-9-18上午11:51:55
	*/
	public MemIntegral queryByMemId(String memId);
}
