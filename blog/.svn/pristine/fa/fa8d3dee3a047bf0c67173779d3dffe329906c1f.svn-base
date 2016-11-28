package com.blog.service.comments.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.QueryResult;
import com.blog.bean.article.Comments;
import com.blog.dao.comments.CommentsDao;
import com.blog.dao.common.DaoSupport;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.article.CommentsDTO;
import com.blog.service.comments.CommentsService;
import com.blog.util.EntityDtoConverter;

/**
 * @ProjectName:blog
 * @ClassName:CommentsServiceImpl
 * @Description:评论回复Service实现类
 * @date: 2015-6-15下午10:41:18
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-15下午10:41:18
 */
@Service(CommentsService.COMMENTSSERVICE)
public class CommentsServiceImpl extends DaoSupport<Comments> implements
		CommentsService {
	@Resource
	private CommentsDao commentsDao;

	/**
	 *@MethodName:addCommentsDTO
	 *@Description:添加文章评论
	 *@param commentsDTO
	 *@author:徐半仙儿
	 *@date:2015-6-15下午10:43:05
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addCommentsDTO(CommentsDTO commentsDTO) {
		try {
			Comments comments = new Comments();
			comments = (Comments) EntityDtoConverter.dto2Bean(commentsDTO,
					comments);
			this.save(comments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@MethodName:queryAll
	 *@Description:查询所有的留言
	 *@param articleDTO
	 *@author:徐半仙儿
	 *@return QueryResult<CommentsDTO>
	 *@date:2015-6-15下午11:24:34
	 */
	@Override
	public QueryResult<CommentsDTO> queryAll(ArticleDTO articleDTO) {
		return commentsDao.queryAll(articleDTO);
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
