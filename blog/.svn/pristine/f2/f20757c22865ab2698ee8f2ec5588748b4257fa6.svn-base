package com.blog.service.article.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.QueryResult;
import com.blog.bean.article.Article;
import com.blog.dao.article.ArticleDAO;
import com.blog.dao.common.DaoSupport;
import com.blog.dto.article.ArticleDTO;
import com.blog.service.article.ArticleService;
import com.blog.util.EntityDtoConverter;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigServiceImpl
 * @Description:TODO
 * @date: 2014-7-22下午02:58:05
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(ArticleService.ARTICLESERVICE)
public class ArticleServiceImpl extends DaoSupport<Article> implements
		ArticleService {

	@Resource
	private ArticleDAO articleDAO;

	/**
	 *@Title:updateServiceList
	 *@Description:更新
	 *@param serviceListDTO
	 *@Return:void
	 *@author:徐凯强
	 *@Date:2014-7-22下午02:18:09
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateServiceList(ArticleDTO articleDTO) {
		try {
			Article article = new Article();
			article = (Article) EntityDtoConverter
					.dto2Bean(articleDTO, article);
			this.update(article);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 *@Title:querAll
	 *@Description:分页查找
	 *@param firstIndex
	 *@param pageNum
	 *@param serviceListDTO
	 *@param orderBy
	 *@Return:QueryResult<ServiceListDTO>
	 *@author:徐凯强
	 * @throws Exception
	 *@Date:2014-7-23下午05:13:09
	 */
	public QueryResult<ArticleDTO> queryAll(int firstIndex, int pageNum,
			ArticleDTO articleDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		return articleDAO.queryAll(firstIndex, pageNum, articleDTO, orderBy);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSaveDTO(ArticleDTO articleDTO) {
		try {
			Article article = new Article();
			article = (Article) EntityDtoConverter
					.dto2Bean(articleDTO, article);
			// 发表时间
			article.setContentDate(new Date());
			article.setReadTimes(0);
			this.save(article);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 *@MethodName:addReadTimes
	 *@Description:添加阅读次数
	 *@param articleDTO
	 *@author:徐半仙儿
	 *@date:2015-11-1下午08:43:21
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addReadTimes(ArticleDTO articleDTO) {
		/** 设置文章的阅读次数+1 */
		articleDTO.setReadTimes(articleDTO.getReadTimes() + 1);
		try {
			Article article = new Article();
			article = (Article) EntityDtoConverter
					.dto2Bean(articleDTO, article);
			this.update(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *@MethodName:queryArticleTypesAndCounts 
	 *@Description:查询文章分类和分类下文章数量
	 *@author:徐半仙儿
	 *@return List<ArticleDTO>
	 *@date:2015-11-22上午12:16:22
	 */
	public List<ArticleDTO>  queryArticleTypesAndCounts(){
		return articleDAO.queryArticleTypesAndCounts();
	}

	@Override
	public List<ArticleDTO> queryArticleDateAndCounts() {
		return articleDAO.queryArticleDateAndCounts();
	}
}
