package com.lucene.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.lucene.bean.Search;
import com.lucene.dao.LuceneDAO;
import com.lucene.service.LuceneService;
import com.lucene.util.ContentExtractor;
import com.lucene.util.HtmlRegexpUtil;
import com.lucene.util.PropertiesUtil;
import com.lucene.util.ReflectObject;

/**
 * Lucene 服务实现
 * 
 * 1.创建索引； 2.更新索引； 3.查询索引； 4...
 * 
 * @ClassName:LuceneService
 * @Description:TODO
 * @date: 2014-10-23下午04:40:42
 * @author: 谢洪飞
 * @version: V1.0
 */
// @Service(LuceneService.LUCENESERVICE)
@Service("LuceneService")
public class LuceneServiceImpl implements LuceneService {

	public Logger logger = Logger.getLogger("settlog.txt");

	@Autowired
	private LuceneDAO luceneDAO;

	/** 创建非只能分词器，实现最细粒度分词 */
	private Analyzer analyzer = new IKAnalyzer(false);

	/**
	 * 创建索引方法
	 * 
	 * @Title:buildIndex
	 * @Description:定时任务调度将数据库内容创建Lucene索引
	 * @param:@throws Exception
	 * @Return:void
	 * @author: 谢洪飞
	 * @Thorws:
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void buildIndex() throws Exception {

		// 0.准备.
		IndexWriterConfig iwConfig = null;
		IndexWriter indexWriter = null;
		Directory directory = null;

		// 1.获取Properties配置文件(search.properties)
		String path = PropertiesUtil.path();
		Properties prop = PropertiesUtil.getProperties(path + "search.properties");

		// 2.获取search.properties文件中的statmentName参数数组(与Mapper文件中的id对应)
		String[] statementNames = prop.getProperty("statementName").trim().split(",");
		String lucenePathPrefix = prop.getProperty("indexPath").trim();

		// 3.查询数据库中内容，并生成相关索引
		if (null != statementNames && statementNames.length > 0) {
			// IndexWriter配置参数
			iwConfig = new IndexWriterConfig(Version.LUCENE_40, analyzer);

			// 遍历查询所有要生成索引的数据
			for (String statementName : statementNames) {

				// 获取要创建索引的数据
				List<Search> list = luceneDAO.queryForList(statementName);
				// 指定要创建索引的位置
				String filePath = lucenePathPrefix + statementName + "_lucene";
				directory = FSDirectory.open(new File(filePath));
				indexWriter = new IndexWriter(directory, iwConfig);

				// 创建索引
				for (Search search : list) {
					logger.debug("!------ 创建索引开始!" + new Date());
					logger.info("|--- 开始创建 " + statementName + "_lucene 索引 ---|");
					Document doc = new Document();
					Map<String, Object> searchMap = ReflectObject.reflectFromObject(search);
					Iterator iterator = searchMap.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry entry = (Entry) iterator.next();
						Object key = entry.getKey();
						Object value = entry.getValue();
						System.out.println(key+"--------------------------"+HtmlRegexpUtil.filterHtml(String.valueOf(value)));
						// 增加document内容
						doc.add(new TextField(String.valueOf(key), HtmlRegexpUtil.filterHtml(String.valueOf(value)),
								Store.YES));
					}
					indexWriter.addDocument(doc);
				}
				// 释放资源
				releaseIndexWriter(indexWriter, directory);
			}
		}
	}

	/**
	 * 清空所有索引库内容
	 * 
	 * @Title:emptyIndex
	 * @Description: 定时任务调度将所有索引库内容清空
	 * @param:@throws Exception
	 * @Return:void
	 * @author: 谢洪飞
	 * @Thorws:
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void emptyIndex() throws Exception {

		// 0.准备
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;

		// 1.读取search.properties配置信息
		String path = PropertiesUtil.path();
		Properties prop = PropertiesUtil.getProperties(path + "search.properties");
		String[] statementNames = prop.getProperty("statementName").trim().split(",");
		String indexPathPrefix = prop.getProperty("indexPath").trim();

		// 2.遍历所有索引库，并删除
		if (null != statementNames && statementNames.length > 0) {
			for (String statementName : statementNames) {
				File indexFile = new File(indexPathPrefix + statementName + "_lucene");
				// 如果索引文件存在
				if (indexFile.exists()) {
					Directory directory = FSDirectory.open(indexFile);
					IndexReader indexReader = IndexReader.open(directory);
					indexWriterConfig = new IndexWriterConfig(Version.LUCENE_40, analyzer);

					indexWriter = new IndexWriter(directory, indexWriterConfig);
					// 删除索引名为：indexPathPrefix + statementName + _lucene 的文件
					indexWriter.deleteAll();
					// 释放资源
					releaseIndexWriter(indexWriter, directory);
					indexReader.close();
				}
			}
		}
	}

	/**
	 * 索引查询
	 * 
	 * @Title:queryFromIndex
	 * @Description:从索引中检索要查询的内容
	 * @param:@param indexSearcher
	 *                   索引查询器
	 * @param:@param keyWords
	 *                   查询关键字
	 * @param:@param fields
	 *                   要检索的索引库
	 * @param:@param page
	 *                   页数(检索第几页内容)
	 * @param:@param pageSize
	 *                   每页先是条数
	 * @param:@return 检索结果集合内容
	 * @param:@throws Exception
	 * @return:List<Search> 检索结果
	 * @author: 谢洪飞
	 * @Thorws:
	 */
	public List<Search> queryFromIndex(IndexSearcher indexSearcher, String keyWords, String[] fields, int page,
			int pageSize) throws Exception {

		// 默认IKAnalyzer()-false:实现最细粒度切分算法,true:分词器采用智能切分
		QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_40, fields, analyzer);

		parser.setDefaultOperator(QueryParser.AND_OPERATOR);

		Query query = parser.parse(keyWords);

		if (fields.length > 0) {
			if (fields[0] == "typeId" || "typeId".equals(fields[0])) {
				Term term = new Term("typeId", keyWords);
				query = new TermQuery(term);
			}
		}

		TopDocs topDocs = indexSearcher.search(query, 10000);

		return paginationQuery(analyzer, indexSearcher, query, topDocs, page, pageSize);
	}

	/**
	 * 分页 |高亮 查询
	 * 
	 * @Title:paginationQuery
	 * @Description:分页|高亮查询
	 * @param:@param analyzer
	 *                   分词器
	 * @param:@param indexSearcher
	 *                   检索器
	 * @param:@param query
	 *                   查询类型对象
	 * @param:@param topDocs
	 *                   评分筛选项
	 * @param:@param currentPage
	 *                   当前页
	 * @param:@param pageSize
	 *                   每页条数
	 * @param:@return
	 * @return:List<Search>
	 * @author: 谢洪飞
	 * @throws Exception
	 * @Thorws:
	 */
	public List<Search> paginationQuery(Analyzer analyzer, IndexSearcher indexSearcher, Query query, TopDocs topDocs,
			int currentPage, int pageSize) throws Exception {

		List<Search> searchResults = new ArrayList<Search>();

		// 匹配条数
		if (topDocs.totalHits <= 0) {
			return searchResults;
		}

		TopScoreDocCollector results = TopScoreDocCollector.create(topDocs.totalHits, false);

		// 执行查询
		indexSearcher.search(query, results);
		// 分页取出指定的doc信息
		ScoreDoc[] doc = results.topDocs((currentPage * pageSize) - pageSize).scoreDocs;
		Properties prop = PropertiesUtil.getProperties(PropertiesUtil.path() + "search.properties");
		// 根据配置内容构造格式化编辑器
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(prop.getProperty("startTag"),
				prop.getProperty("endTag"));
		// 创建高亮构造器
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));

		for (int i = 0; i < doc.length; i++) {
			Document document = indexSearcher.doc(doc[i].doc);
			// 内容添加高亮显示效果 new SimpleFragmenter(200)：配置高亮数据长度
			highlighter.setTextFragmenter(new SimpleFragmenter(200));
			TokenStream tokenStream = analyzer.tokenStream("content",
					new StringReader(document.get("content") == null ? "" : document.get("content")));

			// content高亮显示
			String content = highlighter.getBestFragment(tokenStream,
					document.get("content") == null ? "" : document.get("content"));

			TokenStream titleToken = analyzer.tokenStream("title",
					new StringReader(document.get("title") == null ? "" : document.get("title")));

			// title高亮显示
			String title = highlighter.getBestFragment(titleToken,
					document.get("title") == null ? "" : document.get("title"));

			TokenStream storeNameToken = analyzer.tokenStream("storeName",
					new StringReader(document.get("storeName") == null ? "" : document.get("storeName")));

			String storeName = highlighter.getBestFragment(storeNameToken,
					document.get("storeName") == null ? "" : document.get("storeName"));

			Map<String, String> map = new HashMap<String, String>();

			for (IndexableField field : document.getFields()) {

				/** 更改高亮显示样式 */
				if ("content".equals(field.name())) {
					if (null != content) {
						map.put(field.name(), content);
					} else {
						map.put(field.name(), document.get("content") == null ? "" : document.get("content"));
					}

				} else if ("title".equals(field.name())) {
					if (null != title) {
						map.put(field.name(), title);
					} else {
						map.put(field.name(), document.get("title") == null ? "" : document.get("title"));
					}

				} else if ("storename".equalsIgnoreCase(field.name())) {
					if (null != storeName) {
						map.put(field.name(), storeName);
					} else {
						map.put(field.name(), document.get("storeName") == null ? "" : document.get("storeName"));
					}
				} else {
					map.put(field.name(), field.stringValue());
				}
			}
			Search search = (Search) ReflectObject.TconvertMap(Search.class, map);
			searchResults.add(search);
		}

		return searchResults;
	}

	/**
	 * 获取IndexSearcher对象
	 * 
	 * @Title:getSearcher
	 * @Description:
	 * @param:@param fields
	 * @param:@return
	 * @return:IndexSearcher
	 * @author: 谢洪飞
	 * @throws IOException
	 * @Thorws:
	 */
	@SuppressWarnings("deprecation")
	@Override
	public IndexSearcher getSearcher(String fields) throws IOException {

		// 获取索引库位置
		String indexPath = PropertiesUtil.getProperties(PropertiesUtil.path() + "search.properties")
				.getProperty("indexPath").trim() + fields + "_lucene";

		Directory directory = FSDirectory.open(new File(indexPath));
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		return indexSearcher;
	}

	@Override
	public IndexSearcher getSearchers(String fields) throws IOException {

		String[] fieldsArr = fields.split(",");
		IndexReader[] indexReaders = new IndexReader[fieldsArr.length];

		int index = 0;
		for (String path : fieldsArr) {
			String indexPath = PropertiesUtil.getProperties(PropertiesUtil.path() + "search.properties")
					.getProperty("indexPath").trim() + path + "_lucene";

			Directory directory = FSDirectory.open(new File(indexPath));
			indexReaders[index++] = DirectoryReader.open(directory);
		}

		return new IndexSearcher(new MultiReader(indexReaders, true));
	}

	/**
	 * 根据检索条件，统计查询结果数
	 * 
	 * @param indexSearcher
	 * @param keyWords
	 *            输入框中内容
	 * @param f
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@Override
	public int count(IndexSearcher indexSearcher, String keyWords, String[] f) throws Exception {
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_40, f, analyzer);
		queryParser.setDefaultOperator(queryParser.AND_OPERATOR);
		Query query = queryParser.parse(keyWords);
		TopDocs topDocs = indexSearcher.search(query, 10000);
		return topDocs.totalHits;
	}

	/**
	 * 释放资源
	 * 
	 * @Title:releaseIndexWriter
	 * @Description:释放资源
	 * @param:@param indexWriter
	 * @param:@param directory
	 * @Return:void
	 * @author: 谢洪飞
	 * @Thorws:
	 */
	private void releaseIndexWriter(IndexWriter indexWriter, Directory directory) {

		try {
			if (null != indexWriter) {
				indexWriter.close();
			}
		} catch (Exception e) {
			logger.info("释放 IndexWriter对象发生异常!");
			e.printStackTrace();
		} finally {
			try {
				if (null != directory && IndexWriter.isLocked(directory)) {
					IndexWriter.unlock(directory);
				}
			} catch (Exception e2) {
				logger.info("释放 IndexWriter对象对" + directory + "的锁定发生异常!");
				e2.printStackTrace();
			}
		}
	}
}
