package com.paySystem.ic.dao.article.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.article.Article;
import com.paySystem.ic.dao.article.ArticleDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.article.ArticleDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Repository(ArticleDAO.ARTICLEDAO)
public class ArticleDaoImpl extends DaoSupport<Article> implements ArticleDAO {

	public QueryResult<ArticleDTO> queryAll(int firstIndex, int pageNum, ArticleDTO articleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<ArticleDTO> queryResultDTOList = new QueryResult<ArticleDTO>();
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		UserSession us = Utils.getUserSession();
		/** 模糊查询 */
		/** 根据博客的标题进行模糊查询 */
		if (articleDTO.getTitle() != null && !articleDTO.getTitle().equals("")) {
			wherejpql.append(" and o.title like ?");
			queryParams.add("%" + articleDTO.getTitle() + "%");
		}
		if (articleDTO.getTypeId() != null && !articleDTO.getTypeId().equals("") && articleDTO.getTypeId() != -1) {
			wherejpql.append(" and o.typeId=" + articleDTO.getTypeId());
		}
		wherejpql.append(" and o.status!=9");

		QueryResult<Article> queryResult = this.getScrollData(firstIndex, pageNum, wherejpql.toString(),
				queryParams.toArray(), orderBy);
		/** 将实体queryResult转换为DTOqueryResult */
		List<Article> listEntity = queryResult.getResultlist();
		List<ArticleDTO> listDtos = changeListToDtoList(listEntity);
		/** 赋值QueryResult<MessServParamConfigDTO> */
		queryResultDTOList.setResultlist(listDtos);
		queryResultDTOList.setTotalrecord(queryResult.getTotalrecord());
		return queryResultDTOList;
	}

	public List<ArticleDTO> changeListToDtoList(List<Article> listEntity) {
		List<ArticleDTO> listDtos = new ArrayList<ArticleDTO>();

		for (int i = 0; i < listEntity.size(); i++) {
			ArticleDTO articleDTO = new ArticleDTO();
			try {
				articleDTO = (ArticleDTO) EntityDtoConverter.bean2Dto(listEntity.get(i), articleDTO);
				listDtos.add(articleDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listDtos;
	}

	/**
	 * @MethodName:queryArticleTypesAndCounts
	 * @Description:查询文章分类和分类下文章数量
	 * @author:徐半仙儿
	 * @return Map<String,Integer>String为文章分类名称，Integer为分类下文章数量
	 * @date:2015-11-21下午10:28:23
	 */
	/**
	 * @MethodName:queryArticleTypesAndCounts
	 * @Description:查询文章分类和分类下文章数量
	 * @author:徐半仙儿
	 * @date:2015-11-22上午12:15:43
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleDTO> queryArticleTypesAndCounts() {
		List<ArticleDTO> articleDTOs = new ArrayList<ArticleDTO>();
		String jpql = "select a.blogTypeId,a.name,count(t.articleid) from t_article t,t_blogtype a where t.blogtypeid=a.blogtypeid and t.status!=9 group by t.blogtypeid";
		Query query = this.em.createNativeQuery(jpql);
		List<Object[]> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {
			Object[] result = list.get(i);
			ArticleDTO articleDTO = new ArticleDTO();
			// BlogType blogType=new BlogType();
			// blogType.setBlogTypeId( Integer.parseInt(result[0].toString()));
			// blogType.setName((String)result[1]);
			// articleDTO.setBlogType(blogType);
			articleDTO.setBlogTypeCount(Integer.parseInt(result[2].toString()));
			articleDTOs.add(articleDTO);
		}
		return articleDTOs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleDTO> queryArticleDateAndCounts() {
		List<ArticleDTO> articleDTOs = new ArrayList<ArticleDTO>();
		String jpql = "select DATE_FORMAT(`contentDate`, '%Y-%m'),COUNT(*) from `t_article` where status!=9 group by DATE_FORMAT(`contentDate`, '%Y-%m') order by contentDate desc;";
		Query query = this.em.createNativeQuery(jpql);
		List<Object[]> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {
			Object[] result = list.get(i);
			ArticleDTO articleDTO = new ArticleDTO();
			articleDTO.setContentDateString(result[0].toString());
			articleDTO.setCount(result[1].toString());
			articleDTOs.add(articleDTO);
		}
		return articleDTOs;
	}

//	@Override
//	public List<ArticleDTO> queryByLucene(ArticleDTO articleDTO) {
//		List<ArticleDTO> articles = new ArrayList<>();
//		try {
//			Directory dir = FSDirectory.open(Paths.get(Globals.INDEX_DIR));
//			IndexReader reader = DirectoryReader.open(dir);
//			IndexSearcher is = new IndexSearcher(reader);
//			SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
//			QueryParser parser = new MultiFieldQueryParser(new String[] { "title", "content" }, analyzer);
//			org.apache.lucene.search.Query query = parser.parse(articleDTO.getTitle());
//			long start = System.currentTimeMillis();
//			TopDocs hits = is.search(query, 30);
//			long end = System.currentTimeMillis();
//			System.out.println(
//					"匹配 " + articleDTO.getTitle() + " ，总共花费" + (end - start) + "毫秒" + "查询到" + hits.totalHits + "个记录");
//			for (ScoreDoc scoreDoc : hits.scoreDocs) {
//				Document doc = is.doc(scoreDoc.doc);
//				String desc = doc.get("content");
//				String title = doc.get("title");
//				ArticleDTO articleDTO2 = new ArticleDTO();
//				articleDTO2.setTitle(LuceneUtils.lighterStr(query, analyzer, "title", title));
//				articleDTO2.setId(Integer.parseInt(doc.get("id")));
//				articleDTO2.setContentDateString(doc.get("contentDate"));
//				articleDTO2.setThumbnailPath(doc.get("thumbnailPath"));
//				articleDTO2.setContent(LuceneUtils.lighterStr(query, analyzer, "content", desc));
//				articles.add(articleDTO2);
//			}
//			reader.close();
//
//		} catch (Exception e)
//
//		{
//			e.printStackTrace();
//		}
//		return articles;
//	}
}
