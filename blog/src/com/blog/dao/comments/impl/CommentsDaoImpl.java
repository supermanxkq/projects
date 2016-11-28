package com.blog.dao.comments.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.blog.bean.QueryResult;
import com.blog.bean.article.Comments;
import com.blog.dao.comments.CommentsDao;
import com.blog.dao.common.DaoSupport;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.article.CommentsDTO;
import com.blog.util.EntityDtoConverter;

@Repository(CommentsDao.COMMENTSDAO)
public class CommentsDaoImpl extends DaoSupport<Comments> implements
		CommentsDao {
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
		QueryResult<CommentsDTO> cQueryResult = new QueryResult<CommentsDTO>();
		QueryResult<Comments> cQueryResult2 = new QueryResult<Comments>();
		List<CommentsDTO> commentsDTOList = new ArrayList<CommentsDTO>();
		String jpql = " and o.article.articleId="
				+ articleDTO.getArticleId();
		try {
			cQueryResult2 = this.getScrollData(-1, -1, jpql, null, null);
			for (Comments comments : cQueryResult2.getResultlist()) {
				CommentsDTO commentsDTO = new CommentsDTO();
				commentsDTO = (CommentsDTO) EntityDtoConverter.bean2Dto(
						comments, commentsDTO);
				commentsDTOList.add(commentsDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cQueryResult.setResultlist(commentsDTOList);
		cQueryResult.setTotalrecord(cQueryResult2.getTotalrecord());
		return cQueryResult;
	}

}