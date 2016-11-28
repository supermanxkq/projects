package com.paySystem.ic.service.lucene;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.search.IndexSearcher;

import com.paySystem.ic.bean.search.Search;

/**
 * 
 * @ProjectName:MarsPlan
 * @ClassName:LuceneService
 * @Description:Lucene服务接口 1.创建索引 2.更新索引 3.查询索引 ....
 * @date: Apr 16, 20162:45:55 PM
 * @author: bruce
 * @version: V1.0
 */
public interface LuceneService {

	/***
	 * 
	 * @MethodName:buildIndex
	 * @Description:定时任务调度将数据库内容创建Lucene索引
	 * @throws Exception
	 * @author:bruce
	 * @return void
	 * @date:Apr 16, 20162:46:51 PM
	 */
	public void buildIndex() throws Exception;

	/**
	 * 
	 * @MethodName:emptyIndex
	 * @Description:定时任务调度将所有索引库内容清空
	 * @throws Exception
	 * @author:bruce
	 * @return void
	 * @date:Apr 16, 20162:47:21 PM
	 */
	public void emptyIndex() throws Exception;

	/**
	 * 
	 * @MethodName:queryFromIndex
	 * @Description:从索引中检索要查询的内容
	 * @param indexSearcher
	 * @param keyWords查询关键字
	 * @param fields要检索的索引库
	 * @param page页数(检索第几页内容)
	 * @param pageSize每页先是条数
	 * @throws Exception
	 * @author:bruce
	 * @return List<Search>检索结果
	 * @date:Apr 16, 20162:49:03 PM
	 */
	public List<Search> queryFromIndex(IndexSearcher indexSearcher, String keyWords, String[] fields, int page,
			int pageSize) throws Exception;

	/**
	 * 
	 * @MethodName:getSearcher
	 * @Description:获取IndexSearcher对象
	 * @param fields
	 * @throws IOException
	 * @author:bruce
	 * @return IndexSearcher
	 * @date:Apr 16, 20162:50:35 PM
	 */
	public IndexSearcher getSearcher(String fields) throws IOException;

	/**
	 * 
	 * @MethodName:getSearchers
	 * @Description:获取IndexSearcher对象
	 * @param fields
	 * @throws IOException
	 * @author:bruce
	 * @return IndexSearcher
	 * @date:Apr 16, 20162:51:13 PM
	 */
	public IndexSearcher getSearchers(String fields) throws IOException;

	/***
	 * @MethodName:count
	 * @Description:根据检索条件，统计查询结果数
	 * @param indexSearcher
	 * @param keyWords
	 * @param f
	 * @return
	 * @throws Exception
	 * @author:bruce
	 * @return int
	 * @date:Apr 16, 20162:51:31 PM
	 */
	public int count(IndexSearcher indexSearcher, String keyWords, String[] f) throws Exception;

	/** 常量 */
	public static final String LUCENESERVICE = "luceneService";
}
