package com.paySystem.ic.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.base.ReceivingInfo;
import com.paySystem.ic.dao.base.ReceivingInfoDAO;
import com.paySystem.ic.service.base.ReceivingInfoService;

@Service(ReceivingInfoService.RECEIVINGINFOSERVICE)
public class ReceivingInfoServiceImpl implements ReceivingInfoService{

	@Resource ReceivingInfoDAO receivingInfoDAOImpl;
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.ReceivingInfoService#queryByMemId(java.lang.String)
	 *@MethodName:queryByMemId
	 *@Description:根据会员编号查询收货信息
	 *@param memId 会员编号
	 *@return
	 *@Author:王楠
	 *@Date:2014-9-18下午03:11:06
	 */
	public List<ReceivingInfo> queryByMemId(String memId) {
        List<ReceivingInfo> list=receivingInfoDAOImpl.queryByMemId(memId);
		return list;
	}

}
