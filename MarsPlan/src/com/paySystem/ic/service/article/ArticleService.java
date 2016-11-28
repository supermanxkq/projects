package com.paySystem.ic.service.article;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.article.Article;
import com.paySystem.ic.bean.code.CodeStyle;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.article.ArticleDTO;

/**
 * @ProjectName:blog
 * @ClassName:ArticleService
 * @Description:博客服务类
 * @date: 2015-11-1下午08:41:43
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-11-1下午08:41:43
 */
public interface ArticleService extends DAO<Article> {
	/** 常量 */
	public static final String ARTICLESERVICE = "ArticleService";

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
	 * @MethodName:addSaveDTO
	 * @Description:写博客
	 * @param articleDTO
	 * @author:徐半仙儿
	 * @return void
	 * @date:2015-11-1下午08:41:08
	 */
	public void addSaveDTO(ArticleDTO articleDTO);

	/**
	 * @MethodName:addReadTimes
	 * @Description:添加阅读次数
	 * @param articleDTO
	 * @author:徐半仙儿
	 * @return void
	 * @date:2015-11-1下午08:42:59
	 */
	public void addReadTimes(ArticleDTO articleDTO);

	public void updateData(ArticleDTO articleDTO);

	/**
	 * @MethodName:queryArticleTypesAndCounts
	 * @Description:查询文章分类和分类下文章数量
	 * @author:徐半仙儿
	 * @return List<ArticleDTO>
	 * @date:2015-11-22上午12:16:22
	 */
	public List<ArticleDTO> queryArticleTypesAndCounts();

	/**
	 * @return
	 */
	public List<ArticleDTO> queryArticleDateAndCounts();

	/**
	 * @param articleDTO
	 * @return 使用lucene进行搜素
	 */
//	public List<ArticleDTO> queryByLucene(ArticleDTO articleDTO);
}
