package com.lucene.service;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.search.IndexSearcher;

import com.lucene.bean.Search;

/**
 *  Lucene 服务接口
 *  
 *         1.创建索引；
 *         2.更新索引；
 *         3.查询索引；
 *         4...
 * 
 * @ClassName:LuceneService
 * @Description:TODO
 * @date: 2014-10-23下午04:40:42
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface LuceneService {

	
	/**
	 *   创建索引方法
	 * 
	 *@Title:buildIndex
	 *@Description:定时任务调度将数据库内容创建Lucene索引
	 *@param:@throws Exception
	 *@Return:void
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public void buildIndex() throws Exception;
	
	
	/**
	 *  清空所有索引库内容
	 * 
	 *@Title:emptyIndex
	 *@Description:定时任务调度将所有索引库内容清空
	 *@param:@throws Exception
	 *@Return:void
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public void emptyIndex() throws Exception;
	
	/**
	 *  索引查询
	 * 
	 *@Title:queryFromIndex
	 *@Description:从索引中检索要查询的内容
	 *@param:@param keyWords 查询关键字
	 *@param:@param fields   要检索的索引库
	 *@param:@param page     页数(检索第几页内容)
	 *@param:@param pageSize 每页先是条数
	 *@param:@return  检索结果集合内容
	 *@param:@throws Exception
	 *@return:List<Search> 检索结果
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public List<Search> queryFromIndex(IndexSearcher indexSearcher , String keyWords , String [] fields,int page ,int pageSize) throws Exception;
	
	/**
	 *  获取IndexSearcher对象
	 *  
	 *@Title:getSearcher
	 *@Description:TODO
	 *@param:@param fields
	 *@param:@return
	 *@return:IndexSearcher
	 *@author: 谢洪飞
	 * @throws IOException 
	 * @Thorws:
	 */
	public IndexSearcher getSearcher(String  fields) throws IOException;
	
	public IndexSearcher getSearchers(String fields) throws IOException;
	
	
	/**
	 *   根据检索条件，统计查询结果数
	 * 
	 * @param indexSearcher
	 * @param keyWords 输入框中内容
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public int count(IndexSearcher indexSearcher , String keyWords , String [] f) throws Exception;
	
	
	public static final String LUCENESERVICE = "luceneService";
}
