package com.blog.service.leavemessage.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.QueryResult;
import com.blog.bean.leavemessage.LeaveMessage;
import com.blog.dao.common.DaoSupport;
import com.blog.dto.leavemessage.LeaveMessageDTO;
import com.blog.service.leavemessage.LeaveMessageService;
import com.blog.util.EntityDtoConverter;

@Service(LeaveMessageService.LEAVEMESSAGESERVICE)
public class LeaveMessageServiceImpl extends DaoSupport<LeaveMessage> implements
		LeaveMessageService {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveLeaveMessage(LeaveMessageDTO leaveMessageDTO) {
		try {
			LeaveMessage leaveMessage = new LeaveMessage();
			leaveMessage = (LeaveMessage) EntityDtoConverter.dto2Bean(
					leaveMessageDTO, leaveMessage);
			this.save(leaveMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public QueryResult<LeaveMessageDTO> queryAll(int firstIndex, int pageNum,
			LeaveMessageDTO leaveMessageDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<LeaveMessageDTO> lQueryResultDTO = new QueryResult<LeaveMessageDTO>();
		List<LeaveMessageDTO> leaveMessageDTOs = new ArrayList<LeaveMessageDTO>();
		QueryResult<LeaveMessage> leQueryResult = this.getScrollData(
				firstIndex, pageNum, null, null, orderBy);
		for (LeaveMessage leaveMessage : leQueryResult.getResultlist()) {
			try {
				LeaveMessageDTO leaveMessageDTO2 = new LeaveMessageDTO();
				leaveMessageDTO2 = (LeaveMessageDTO) EntityDtoConverter
						.bean2Dto(leaveMessage, leaveMessageDTO2);
				leaveMessageDTOs.add(leaveMessageDTO2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		lQueryResultDTO.setResultlist(leaveMessageDTOs);
		lQueryResultDTO.setTotalrecord(leQueryResult.getTotalrecord());
		return lQueryResultDTO;
	}

	// @Override
	// public void deleteLeaveMessage(LeaveMessage leaveMessage) {
	// leaveMessageDao.deleteLeaveMessage(leaveMessage);
	// }
	//
	// @Override
	// public List<LeaveMessage> findLeaveMessage(int pageSize, int pageNow) {
	// return leaveMessageDao.findLeaveMessage(pageSize, pageNow);
	// }
	//
	// @Override
	// public LeaveMessage findLeaveMessageById(int leaveMessageId) {
	// return leaveMessageDao.findLeaveMessageById(leaveMessageId);
	// }
	//
	// @Override
	// public Long queryAllCount() {
	// return leaveMessageDao.queryAllCount();
	// }
	//
	// @Override
	// public LeaveMessageReply findLeaveMessageReplyByLeaveMessageId(
	// int leaveMessageId) {
	// return
	// leaveMessageDao.findLeaveMessageReplyByLeaveMessageId(leaveMessageId);
	// }

}
