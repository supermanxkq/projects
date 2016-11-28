//package com.blog.dao.leavemessage.impl;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.blog.bean.leavemessage.LeaveMessageReply;
//import com.blog.dao.leavemessage.LeaveMessageReplyDao;
//
//@Repository
//public class LeaveMessageReplyDaoImpl implements LeaveMessageReplyDao {
//	// 注入sessionFactory
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	@Override
//	public void saveLeaveMessageReply(LeaveMessageReply leaveMessageReply) {
//		sessionFactory.getCurrentSession().saveOrUpdate(leaveMessageReply);
//	}
//}
