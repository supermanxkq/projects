package com.paySystem.ic.dao.base.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.ReceivingInfo;
import com.paySystem.ic.dao.base.ReceivingInfoDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.base.ReceivingInfoDTO;

@Repository(ReceivingInfoDAO.RECEIVINGINFODAO)
public class ReceivingInfoDAOImpl extends DaoSupport<ReceivingInfo> 
                                      implements ReceivingInfoDAO{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.ReceivingInfoDAO#queryByMemId(java.lang.String)
	 *@MethodName:queryByMemId
	 *@Description:根据会员编号查询一个集合信息
	 *@param memId
	 *@return
	 *@Author:王楠
	 *@Date:2014-9-18上午10:49:55
	 */
	@SuppressWarnings("unchecked")
	public List<ReceivingInfo> queryByMemId(String memId) {
        String sql="from ReceivingInfo o where o.member.memId = '"+memId+"'";
        Query query =em.createQuery(sql.toString());
        List<ReceivingInfo> list=query.getResultList();
		return list;
	}

	/**
	*@Title:getReceivingInfoDTO
	*@Description:实体转DTO
	*@Params:@param receivingInfo
	*@Params:@return
	*@Params:@throws Exception
	*@Return:ReceivingInfoDTO
	*@author:王楠
	*@Date:2014-9-18下午04:37:49
	*/
	@SuppressWarnings("unused")
	private ReceivingInfoDTO getReceivingInfoDTO(ReceivingInfo receivingInfo)throws Exception{
		ReceivingInfoDTO receivingInfoDTO=new ReceivingInfoDTO();
		receivingInfoDTO=(ReceivingInfoDTO)EntityDtoConverter.bean2Dto(receivingInfo,receivingInfoDTO);
		return receivingInfoDTO;
	}
	
	/**
	*@Title:getReceivingInfo
	*@Description:DTO转实体
	*@Params:@param receivingInfoDTO
	*@Params:@return
	*@Params:@throws Exception
	*@Return:ReceivingInfo
	*@author:王楠
	*@Date:2014-9-18下午04:39:12
	*/
	@SuppressWarnings("unused")
	private ReceivingInfo getReceivingInfo(ReceivingInfoDTO receivingInfoDTO)throws Exception{
		ReceivingInfo receivingInfo=new ReceivingInfo();
		receivingInfo=(ReceivingInfo)EntityDtoConverter.dto2Bean(receivingInfoDTO, receivingInfo);
		return receivingInfo;
	}
}
