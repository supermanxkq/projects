package com.paySystem.ic.service.base;

import java.util.List;

import com.paySystem.ic.bean.base.ReceivingInfo;

public interface ReceivingInfoService {

	public static final String RECEIVINGINFOSERVICE="receivingInfoService";
	
	/**
	*@Title:queryByMemId
	*@Description:根据会员编号查询收货信息
	*@Params:@param memId 会员编号
	*@Params:@return
	*@Return:List<ReceivingInfo>
	*@author:王楠
	*@Date:2014-9-18下午03:09:54
	*/
	public List<ReceivingInfo> queryByMemId(String memId);
}
