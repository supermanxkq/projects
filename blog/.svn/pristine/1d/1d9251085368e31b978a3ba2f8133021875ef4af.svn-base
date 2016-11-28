package com.blog.service.comments;

import com.blog.bean.QueryResult;
import com.blog.bean.article.Comments;
import com.blog.dao.common.DAO;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.article.CommentsDTO;

/**
 * @ProjectName:blog 
 * @ClassName:CommentsService  
 * @Description:评论service
 * @date: 2015-6-15下午10:37:48
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-15下午10:37:48
 */
public interface CommentsService extends DAO<Comments>{
	/**常量*/
	public static final String COMMENTSSERVICE = "CommentsService";

	/**
	 *@MethodName:addCommentsDTO 
	 *@Description:添加评论
	 *@param commentsDTO 
	 *@author:徐半仙儿
	 *@return void
	 *@date:2015-6-15下午10:39:44
	 */
	public void addCommentsDTO(CommentsDTO commentsDTO);
	/**
	 *@MethodName:queryAll 
	 *@Description:查询所有的留言
	 *@param articleDTO
	 *@author:徐半仙儿
	 *@return QueryResult<CommentsDTO>
	 *@date:2015-6-15下午11:24:34
	 */
	public QueryResult<CommentsDTO> queryAll(ArticleDTO articleDTO);
}
