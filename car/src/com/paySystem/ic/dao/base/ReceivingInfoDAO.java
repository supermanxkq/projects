package com.paySystem.ic.dao.base;

import java.util.List;

import com.paySystem.ic.bean.base.ReceivingInfo;
import com.paySystem.ic.dao.common.DAO;

/**
 * @ProjectName:omall
 * @ClassName:ReceivingInfoDAO
 * @Description:收货信息的DAO接口
 * @date: 2014-9-18
 * @author: 王楠
 * @version: V1.0
 */
public interface ReceivingInfoDAO extends DAO<ReceivingInfo>{

	public static final String RECEIVINGINFODAO = "ReceivingInfoDAO";
	
	/**
	*@Title:queryByMemId
	*@Description:根据会员编号查询一个集合
	*@Params:@param memId 会员编号
	*@Params:@return
	*@Return:List<ReceivingInfo>
	*@author:王楠
	*@Date:2014-9-18上午10:47:22
	*/
	public List<ReceivingInfo> queryByMemId(String memId);
}
