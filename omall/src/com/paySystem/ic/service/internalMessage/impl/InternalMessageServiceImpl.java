package com.paySystem.ic.service.internalMessage.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.internalMessage.InternalMessage;
import com.paySystem.ic.dao.internalMessage.InternalMessageDao;
import com.paySystem.ic.service.internalMessage.InternalMessageService;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.internalMessage.InternalMessageDTO;
import com.paySystem.ic.web.dto.internalMessage.ReceiversDTO;
import com.paySystem.ic.web.dto.member.MembersDTO;

/**
 * @ProjectName:omall
 * @ClassName:InternalMessageServiceImpl
 * @Description:站内信服务实现类
 * @date: 2014-11-19上午10:45:51
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(InternalMessageService.INTERNALMESSAGESERVICE)
public class InternalMessageServiceImpl implements InternalMessageService {

	/** 注入internalMessageDao */
	@Resource
	private InternalMessageDao internalMessageDao;

	/**
	 *@MethodName:findMerchants
	 *@Description:查询所有的商户
	 *@Author:徐凯强
	 *@Date:2014-11-19上午10:46:07
	 */
	@Override
	public QueryResult<MerchantsDTO> findMerchants() {
		return internalMessageDao.findMerchants();
	}

	/**
	 *@MethodName:findMembers
	 *@Description:查询所有的会员
	 *@Author:徐凯强
	 *@Date:2014-11-19下午02:56:27
	 */
	@Override
	public QueryResult<MembersDTO> findMembers() {
		return internalMessageDao.findMembers();
	}

	/**
	 *@Title:addSave
	 *@Description:添加站内信记录
	 *@param internalMessage站内信实体类
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-20下午04:13:04
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public InternalMessage addSave(InternalMessageDTO internalMessageDTO) {
		return internalMessageDao.addSave(internalMessageDTO);
	}

	/**
	 *@Title:addReceiver
	 *@Description:添加站内信的收信人
	 *@param receiversDTO收信人数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-20下午04:13:52
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addReceiver(ReceiversDTO receiversDTO) {
		internalMessageDao.addReceiver(receiversDTO);
	}

	/**
	 *@Title:queryAll
	 *@Description:分页查找
	 *@param firstIndex分页的首个参数
	 *@param pageNum当前页有多少条数据
	 *@param internalMessageDTO站内信数据传输对象
	 *@param orderBy排序对象
	 *@Return:QueryResult<InternalMessageDTO>站内信数据传输对象集合和总共条数
	 *@author:徐凯强
	 * @throws Exception抛出异常信息
	 *@Date:2014-7-23下午05:13:09
	 */
	public QueryResult<InternalMessageDTO> queryAll(int firstIndex,
			int pageNum, InternalMessageDTO internalMessageDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return internalMessageDao.queryAll(firstIndex, pageNum,
				internalMessageDTO, orderBy);
	}

	/**
	 *@Title:findDetailsByInternalMessageId
	 *@Description:从数据库中这个站内信下的详细信息
	 *@param internalMessageDTO站内信数据传输对象
	 *@Return:InternalMessageDTO站内信数据传输对象
	 *@author:徐凯强
	 *@Date:2014-11-21上午09:49:39
	 */
	public InternalMessageDTO findDetailsByInternalMessageId(
			InternalMessageDTO internalMessageDTO) {
		return internalMessageDao
				.findDetailsByInternalMessageId(internalMessageDTO);
	}

	/**
	 *@Title:findReceiversByInternalMessageId
	 *@Description:根据站内信编号获取对应的收信者
	 *@param internalMessageDTO站内信数据传输对象
	 *@Return:ReceiversDTO接收者数据传输对象
	 *@author:徐凯强
	 *@Date:2014-11-21上午10:01:35
	 */
	public ReceiversDTO findReceiversByInternalMessageId(
			InternalMessageDTO internalMessageDTO){
		return internalMessageDao
		.findReceiversByInternalMessageId(internalMessageDTO);
	}
}
