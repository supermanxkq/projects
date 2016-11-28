//package com.blog.service.reply.impl;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.annotation.Resource;
//import javax.persistence.Query;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.blog.bean.QueryResult;
//import com.blog.bean.article.Comments;
//import com.blog.bean.article.Reply;
//import com.blog.dao.comments.CommentsDao;
//import com.blog.dao.common.DaoSupport;
//import com.blog.dto.article.ArticleDTO;
//import com.blog.dto.article.CommentsDTO;
//import com.blog.service.comments.CommentsService;
//import com.blog.service.reply.ReplyService;
//import com.blog.util.EntityDtoConverter;
//
///**
// * @ProjectName:blog 
// * @ClassName:ReplyServiceImpl  
// * @Description:回复service实现类
// * @date: 2015-6-16上午04:32:31
// * @author: 徐半仙儿
// * @version: V1.0
// * @date:2015-6-16上午04:32:31
// */
//@Service(ReplyService.REPLYSERVICE)
//public class ReplyServiceImpl extends DaoSupport<Reply> implements
//		ReplyService {
//
//	/**
//	 *@MethodName:addCommentsDTO
//	 *@Description:添加文章评论
//	 *@param commentsDTO
//	 *@author:徐半仙儿
//	 *@date:2015-6-15下午10:43:05
//	 */
////	@Override
////	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
////	public void addCommentsDTO(CommentsDTO commentsDTO) {
////		try {
////			Comments comments = new Comments();
////			comments = (Comments) EntityDtoConverter.dto2Bean(commentsDTO,
////					comments);
////			this.save(comments);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//
//	/**
//	 *@MethodName:queryAll
//	 *@Description:查询所有的留言
//	 *@param articleDTO
//	 *@author:徐半仙儿
//	 *@return QueryResult<CommentsDTO>
//	 *@date:2015-6-15下午11:24:34
//	 */
////	@Override
////	public QueryResult<CommentsDTO> queryAll(ArticleDTO articleDTO) {
////		return commentsDao.queryAll(articleDTO);
////	}
//
//	// @Override
//	// public void deleteLeaveMessage(LeaveMessage leaveMessage) {
//	// leaveMessageDao.deleteLeaveMessage(leaveMessage);
//	// }
//	//
//	// @Override
//	// public List<LeaveMessage> findLeaveMessage(int pageSize, int pageNow) {
//	// return leaveMessageDao.findLeaveMessage(pageSize, pageNow);
//	// }
//	//
//	// @Override
//	// public LeaveMessage findLeaveMessageById(int leaveMessageId) {
//	// return leaveMessageDao.findLeaveMessageById(leaveMessageId);
//	// }
//	//
//	// @Override
//	// public Long queryAllCount() {
//	// return leaveMessageDao.queryAllCount();
//	// }
//	//
//	// @Override
//	// public LeaveMessageReply findLeaveMessageReplyByLeaveMessageId(
//	// int leaveMessageId) {
//	// return
//	// leaveMessageDao.findLeaveMessageReplyByLeaveMessageId(leaveMessageId);
//	// }
//	
////	@SuppressWarnings("unchecked")
////	public List<Reply> findReplyByCommontsId(Integer commtentsId){
////		String jpl="select * from t_reply o where o.commontsId="+commtentsId;
////		Query query=em.createNativeQuery(jpl);
////		return query.getResultList();
////	}
//
//}
