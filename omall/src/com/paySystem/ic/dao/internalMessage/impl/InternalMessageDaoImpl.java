package com.paySystem.ic.dao.internalMessage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.internalMessage.InternalMessage;
import com.paySystem.ic.bean.internalMessage.Receivers;
import com.paySystem.ic.bean.member.Members;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.internalMessage.InternalMessageDao;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.internalMessage.InternalMessageDTO;
import com.paySystem.ic.web.dto.internalMessage.ReceiversDTO;
import com.paySystem.ic.web.dto.member.MembersDTO;

/**
 * @ProjectName:omall
 * @ClassName:InternalMessageDaoImpl
 * @Description:站内信dao实现类
 * @date: 2014-11-19下午02:57:22
 * @author: 徐凯强
 * @version: V1.0
 */
@Repository(InternalMessageDao.INTERNALMESSAGEDAO)
public class InternalMessageDaoImpl extends DaoSupport<InternalMessage>
		implements InternalMessageDao {
	/**
	 *@Title:findMerchants
	 *@Description:获取所有的商户
	 *@Return:QueryResult<MerchantsDTO>所有商户的集合
	 *@author:徐凯强
	 *@Date:2014-11-19上午10:44:53
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<MerchantsDTO> findMerchants() {
		/** 创建查询所有的sql语句 */
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Merchants o");
		/** 实例化查询到的商户queryResultDTO集合 */
		QueryResult<MerchantsDTO> mQueryResult = new QueryResult<MerchantsDTO>();
		/** 实例化查询到的商户实体集合 */
		List<Merchants> mList = new ArrayList<Merchants>();
		/** 执行sql语句获取所有的商户实体集合 */
		mList = this.em.createQuery(stringBuffer.toString()).getResultList();
		/** 将实体集合转换为DTO集合 */
		List<MerchantsDTO> merchantsDTOs = new ArrayList<MerchantsDTO>();
		for (Merchants merchants : mList) {
			MerchantsDTO merchantsDTO = new MerchantsDTO();
			try {
				merchantsDTO = (MerchantsDTO) EntityDtoConverter.bean2Dto(
						merchants, merchantsDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			merchantsDTOs.add(merchantsDTO);
		}
		/** 设置到queryResult集合中 */
		mQueryResult.setResultlist(merchantsDTOs);
		return mQueryResult;
	}

	/**
	 *@MethodName:findMembers
	 *@Description:查询所有会员
	 *@Author:徐凯强
	 *@Date:2014-11-19下午02:57:55
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<MembersDTO> findMembers() {
		/** 创建查询所有的sql语句 */
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Members o");
		/** 实例化查询到的会员queryResultDTO集合 */
		QueryResult<MembersDTO> mQueryResult = new QueryResult<MembersDTO>();
		/** 实例化查询到的会员实体集合 */
		List<Members> mList = new ArrayList<Members>();
		/** 执行sql语句获取所有的会员实体集合 */
		mList = this.em.createQuery(stringBuffer.toString()).getResultList();
		/** 将实体集合转换为DTO集合 */
		List<MembersDTO> membersDTOs = new ArrayList<MembersDTO>();
		for (Members members : mList) {
			MembersDTO membersDTO = new MembersDTO();
			try {
				membersDTO = (MembersDTO) EntityDtoConverter.bean2Dto(members,
						membersDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			membersDTOs.add(membersDTO);
		}
		/** 设置到queryResult集合中 */
		mQueryResult.setResultlist(membersDTOs);
		return mQueryResult;
	}

	/**
	 *@Title:addSave
	 *@Description:添加站内信记录
	 *@param internalMessage站内信实体类
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-20下午04:13:04
	 */
	public InternalMessage addSave(InternalMessageDTO internalMessageDTO) {
		/** 发送时间 */
		internalMessageDTO.setSendTime(new Date());
		/** 发送人 */
		internalMessageDTO.setSender(Utils.getUserSession().getUserName());
		InternalMessage internalMessage = new InternalMessage();
		try {
			this.save(EntityDtoConverter.dto2Bean(internalMessageDTO,
					internalMessage));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return internalMessage;
	}

	/**
	 *@Title:addReceiver
	 *@Description:添加站内信的收信人
	 *@param receiversDTO收信人数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-20下午04:13:52
	 */
	@Override
	public void addReceiver(ReceiversDTO receiversDTO) {
		Receivers receivers = new Receivers();
		try {
			this.save(EntityDtoConverter.dto2Bean(receiversDTO, receivers));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		/** 集合实例化 */
		QueryResult<InternalMessageDTO> queryResultDTOList = new QueryResult<InternalMessageDTO>();
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		/** 模糊查询 */
		 if (internalMessageDTO.getTitle() != null
		 && !internalMessageDTO.getTitle().equals("")) {
		 wherejpql.append(" and o.title  like ?");
		 queryParams.add("%"+internalMessageDTO.getTitle()+"%");
		 }
		// if (serviceListDTO.getServType() != null
		// && serviceListDTO.getServType() != -1) {
		// wherejpql.append(" and o.servType=?");
		// queryParams.add(serviceListDTO.getServType());
		// }
		/** 分页查询出数据库中的所有的数据 */
		QueryResult<InternalMessage> queryResult = this.getScrollData(
				firstIndex, pageNum, wherejpql.toString(), queryParams
						.toArray(), orderBy);
		/** 将实体queryResult转换为DTOqueryResult */
		List<InternalMessage> listEntity = queryResult.getResultlist();
		List<InternalMessageDTO> internalMessageDTOs = new ArrayList<InternalMessageDTO>();
		for (InternalMessage internalMessage : listEntity) {
			InternalMessageDTO internalMessageDTO2 = new InternalMessageDTO();
			try {
				internalMessageDTO2 = (InternalMessageDTO) EntityDtoConverter
						.bean2Dto(internalMessage, internalMessageDTO2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			internalMessageDTOs.add(internalMessageDTO2);
		}
		/** 赋值QueryResult<InternalMessageDTO> */
		queryResultDTOList.setResultlist(internalMessageDTOs);
		queryResultDTOList.setTotalrecord(queryResult.getTotalrecord());
		return queryResultDTOList;
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
		InternalMessage internalMessage = this.find(internalMessageDTO
				.getInternalMessageId());
		try {
			internalMessageDTO = (InternalMessageDTO) EntityDtoConverter
					.bean2Dto(internalMessage, internalMessageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return internalMessageDTO;
	}

	/**
	 *@Title:findReceiversByInternalMessageId
	 *@Description:根据站内信编号获取对应的收信者
	 *@param internalMessageDTO站内信数据传输对象
	 *@Return:ReceiversDTO接收者数据传输对象
	 *@author:徐凯强
	 *@Date:2014-11-21上午10:01:35
	 */
	@SuppressWarnings("unchecked")
	public ReceiversDTO findReceiversByInternalMessageId(
			InternalMessageDTO internalMessageDTO) {
		/** 获取数据库中所有的收信者 */
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Receivers o where o.internalMessageId="
				+ internalMessageDTO.getInternalMessageId());
		List<Receivers> rList = (List<Receivers>) this.em.createQuery(
				stringBuffer.toString()).getResultList();

		/** 将所有的收信者拼接成字符串 */
		StringBuffer stringBufferReceivers = new StringBuffer();
		for (Receivers receivers : rList) {
			stringBufferReceivers.append(receivers.getReceiverName() + ";");
		}

		/** 添加到ReceiversDTO中 */
		ReceiversDTO receiversDTO = new ReceiversDTO();
		receiversDTO.setReceiverName(stringBufferReceivers.toString());
		return receiversDTO;
	}

	/**
	 *@MethodName:saveMsg
	 *@Description:保存信息
	 *@param msg
	 *@Author:孟凡岭
	 *@Date:2014-12-16上午10:31:41
	 */
	public InternalMessage saveMsg(InternalMessage msg) {
		// TODO Auto-generated method stub
		this.save(msg);
		return msg;
	}
}
