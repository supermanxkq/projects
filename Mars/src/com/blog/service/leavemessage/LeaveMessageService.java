package com.blog.service.leavemessage;

import java.util.LinkedHashMap;

import com.blog.bean.QueryResult;
import com.blog.bean.leavemessage.LeaveMessage;
import com.blog.dao.common.DAO;
import com.blog.dto.leavemessage.LeaveMessageDTO;

public interface LeaveMessageService extends DAO<LeaveMessage> {
	public static final String LEAVEMESSAGESERVICE = "LeaveMessageService";

	/**
	 * 1.留言
	 * 
	 * @param user
	 */
	public void saveLeaveMessage(LeaveMessageDTO leaveMessageDTO);

	/**
	 * 2.删除留言
	 */
//	public void deleteLeaveMessage(LeaveMessage leaveMessage);

	/**
	 * 3.查找所有留言
	 * 
	 * @return
	 */
	public QueryResult<LeaveMessageDTO> queryAll(int firstIndex, int pageNum,
			LeaveMessageDTO leaveMessageDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;
//
//	public LeaveMessageReply findLeaveMessageReplyByLeaveMessageId(
//			int leaveMessageId);
//
//	/**
//	 * 4.通过ID查找留言
//	 * 
//	 * @param leaveMessageId
//	 * @return
//	 */
//	public LeaveMessage findLeaveMessageById(int leaveMessageId);

	/**
	 * 5.查询所有留言条数
	 * 
	 * @return
	 */
//	public Long queryAllCount();

}
