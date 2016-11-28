package com.blog.service.timeline;

import java.io.Serializable;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.blog.bean.QueryResult;
import com.blog.bean.article.Article;
import com.blog.dao.common.DAO;
import com.blog.dto.article.ArticleDTO;

/**
 * @ProjectName:blog
 * @ClassName:TimeLineService
 * @Description:TODO(用一句话描述这个文件做什么)
 * @date: 2015-11-25下午11:14:38
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-11-25下午11:14:38
 */
public interface TimeLineService {
	/** 常量 */
	public static final String TIMELINESERVICE = "TimeLineService";

	public Object loadById(Class clazz, Serializable id);

	public Object loadObject(String hql);

	public void delById(Class clazz, Serializable id);

	public void saveOrUpdate(Object obj);

	public List listAll(String clazz);

	public List listAll(String clazz, int pageNo, int pageSize);

	public int countAll(String clazz);

	public List query();
	public List queryArticle(String jpql);
	public List query(String hql, int pageNo, int pageSize);

	public int countQuery(String hql);

	public int countQuery2(String hql);

	public int update(String hql);

	public Connection getConnection();

	public String getIpToAddress(double ip);

	public boolean updateClicks(int clicks, int id);

	public int SqlUpdate(String Sql);

	public int countSqlQuery(String sql);

	public List SqlQueryList(String sql);

	public List SqlQueryList(String sql, int pageNo, int pageSize);
}
