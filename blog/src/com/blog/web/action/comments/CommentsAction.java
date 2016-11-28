package com.blog.web.action.comments;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.QueryResult;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.article.CommentsDTO;
import com.blog.service.comments.CommentsService;
import com.blog.util.Utils;
import com.blog.web.action.BaseAction;

/**
 * @ProjectName:blog
 * @ClassName:CommentsAction
 * @Description:评论Action
 * @date: 2015-6-15下午10:49:02
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-15下午10:49:02
 */
@Controller("/comments/comments")
@Scope("prototype")
public class CommentsAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

	/** 注入 */
	@Resource
	private CommentsService commentsService;

	/** 评论DTO */
	private CommentsDTO commentsDTO = new CommentsDTO();
	/** 文章DTO */
	private ArticleDTO articleDTO = new ArticleDTO();

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	public CommentsDTO getCommentsDTO() {
		return commentsDTO;
	}

	public void setCommentsDTO(CommentsDTO commentsDTO) {
		this.commentsDTO = commentsDTO;
	}

	/**
	 *@Title:addSave
	 *@Description:保存服务清单实体类
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23上午09:14:24
	 */
	public String addSave() {
		try {
			/** 评论时间 */
			commentsDTO.setTime(new Date());
			commentsDTO.setUserName(Utils.getUserSession().getUserName());
			commentsService.addCommentsDTO(commentsDTO);
			return "ajaxsuccess";
		} catch (Exception e) {
			return "ajaxfailure";
		}

	}

	/**
	 *@MethodName:jsonList
	 *@Description:获取所有的评论
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-6-15下午11:58:32
	 */
	public String jsonList() {
		QueryResult<CommentsDTO> cQueryResult = commentsService
				.queryAll(articleDTO);
		return Utils.printInfo2(cQueryResult.getResultlist());
	}

}
