package com.paySystem.ic.service.internalMessage;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.internalMessage.InternalMessage;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.internalMessage.InternalMessageDTO;
import com.paySystem.ic.web.dto.internalMessage.ReceiversDTO;
import com.paySystem.ic.web.dto.member.MembersDTO;

/**
 * @ProjectName:omall
 * @ClassName:InternalMessageService
 * @Description:站内信服务
 * @date: 2014-11-19上午10:44:04
 * @author: 徐凯强
 * @version: V1.0
 */
public interface InternalMessageService {

	/** 常量 */
	public static final String INTERNALMESSAGESERVICE = "InternalMessageService";

	/**
	 *@Title:findMerchants
	 *@Description:获取所有的商户
	 *@Return:QueryResult<MerchantsDTO>所有商户的集合
	 *@author:徐凯强
	 *@Date:2014-11-19上午10:44:53
	 */
	public QueryResult<MerchantsDTO> findMerchants();

	/**
	 *@Title:findMembers
	 *@Description:获取所有的会员
	 *@Return:QueryResult<MembersDTO>所有会员的集合
	 *@author:徐凯强
	 *@Date:2014-11-19上午10:44:53
	 */
	public QueryResult<MembersDTO> findMembers();

	/**
	 *@Title:addSave
	 *@Description:添加站内信记录
	 *@param internalMessageDTO站内信数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-20下午04:13:04
	 */
	public InternalMessage addSave(InternalMessageDTO internalMessageDTO);

	/**
	 *@Title:addReceiver
	 *@Description:添加站内信的收信人
	 *@param receiversDTO收信人数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-20下午04:13:52
	 */
	public void addReceiver(ReceiversDTO receiversDTO);

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
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 *@Title:findDetailsByInternalMessageId
	 *@Description:从数据库中这个站内信下的详细信息
	 *@param internalMessageDTO站内信数据传输对象
	 *@Return:InternalMessageDTO站内信数据传输对象
	 *@author:徐凯强
	 *@Date:2014-11-21上午09:49:39
	 */
	public InternalMessageDTO findDetailsByInternalMessageId(
			InternalMessageDTO internalMessageDTO);

	/**
	 *@Title:findReceiversByInternalMessageId
	 *@Description:根据站内信编号获取对应的收信者
	 *@param internalMessageDTO站内信数据传输对象
	 *@Return:ReceiversDTO接收者数据传输对象
	 *@author:徐凯强
	 *@Date:2014-11-21上午10:01:35
	 */
	public ReceiversDTO findReceiversByInternalMessageId(
			InternalMessageDTO internalMessageDTO);
}
