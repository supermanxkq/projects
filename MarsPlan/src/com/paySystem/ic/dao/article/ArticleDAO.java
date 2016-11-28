package com.paySystem.ic.dao.article;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.article.Article;
import com.paySystem.ic.web.dto.article.ArticleDTO;

public interface ArticleDAO {
	/** 常量 */
	public static final String ARTICLEDAO = "ArticleDAO";

	/**
	 * @Title:queryAll
	 * @Description:分页查找
	 * @param firstIndex分页的首个参数
	 * @param pageNum当前页有多少条数据
	 * @param ArticleDTO服务清单数据传输对象
	 * @param orderBy排序对象
	 * @Return:QueryResult<ArticleDTO>服务清单数据传输对象
	 * @author:徐凯强
	 * @throws Exception抛出异常信息
	 * @Date:2014-7-23下午05:13:09
	 */
	public QueryResult<ArticleDTO> queryAll(int firstIndex, int pageNum, ArticleDTO articleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 * @MethodName:queryArticleTypesAndCounts
	 * @Description:查询文章分类和分类下文章数量
	 * @author:徐半仙儿
	 * @return List<ArticleDTO>
	 * @date:2015-11-22上午12:17:22
	 */
	public List<ArticleDTO> queryArticleTypesAndCounts();

	/**
	 * @return
	 */
	public List<ArticleDTO> queryArticleDateAndCounts();

	// public List<ArticleDTO> queryByLucene(ArticleDTO articleDTO);
}
