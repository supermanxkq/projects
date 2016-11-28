package com.blog.dao.leavemessage.impl;


//@Repository
//public class LeaveMessageDaoImpl implements LeaveMessageDao {
	// 注入sessionFactory
//	@Autowired
//	private SessionFactory sessionFactory;

	/**
	 * 1. 留言
	 */
//	@Override
//	public void saveLeaveMessage(LeaveMessage leaveMessage) {
//		// 保存留言
//		sessionFactory.getCurrentSession().save(leaveMessage);
//	}

//	@Override
//	public void deleteLeaveMessage(LeaveMessage leaveMessage) {
//		sessionFactory.getCurrentSession().delete(leaveMessage);
//	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<LeaveMessage> findLeaveMessage(int pageSize, int pageNow) {
//		Query query = sessionFactory.getCurrentSession().createQuery(
//				"from LeaveMessage order by leaveMessageDate desc");
//		// limit起始和行数设置
//		query.setFirstResult(pageNow);
//		query.setMaxResults(pageSize);
//		return query.list();
//	}

//	@Override
//	public LeaveMessage findLeaveMessageById(int leaveMessageId) {
//		String sql = "from LeaveMessage where leaveMessageId=:leaveMessageId";
//		Query query = sessionFactory.getCurrentSession().createQuery(sql);
//		query.setInteger("leaveMessageId", leaveMessageId);
//		return (LeaveMessage) query.uniqueResult();
//	}

	/**
	 * 查询所有的留言条数
	 * 
	 * @return
	 */
//	public Long queryAllCount() {
//		String hql = "select count(*) from LeaveMessage";
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		return (Long) query.uniqueResult();
//	}

//	@Override
//	public LeaveMessageReply findLeaveMessageReplyByLeaveMessageId(
//			int leaveMessageId) {
//		String hql = "from LeaveMessageReply leaveMessageReply where leaveMessageReply.leaveMessage.leaveMessageId="
//				+ leaveMessageId;
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		return (LeaveMessageReply) query.uniqueResult();
//	}
//}
