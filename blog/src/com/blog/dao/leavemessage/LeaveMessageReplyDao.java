package com.blog.dao.leavemessage;

import com.blog.bean.leavemessage.LeaveMessageReply;

public interface LeaveMessageReplyDao {

	/**
	 * 1.回复
	 * 
	 * @param user
	 */
	public void saveLeaveMessageReply(LeaveMessageReply leaveMessageReply);

}
