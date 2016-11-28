package com.paySystem.ic.service.lucene.impl;

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

import com.paySystem.ic.bean.search.Search;
import com.paySystem.ic.dao.lucene.LuceneDAO;
import com.paySystem.ic.service.lucene.LuceneService;
import com.paySystem.ic.utils.HtmlRegexpUtil;
import com.paySystem.ic.utils.PropertiesUtil;
import com.paySystem.ic.utils.ReflectObject;

/**
 * 
 * @ProjectName:MarsPlan
 * @ClassName:LuceneServiceImpl
 * @Description:Lucene服务接口 1.创建索引 2.更新索引 3.查询索引 ....
 * @date: Apr 16, 20162:45:55 PM
 * @author: bruce
 * @version: V1.0
 */
@Service(LuceneService.LUCENESERVICE)
public class LuceneServiceImpl implements LuceneService {

	public Logger logger = Logger.getLogger("settlog.txt");

	@Autowired
	private LuceneDAO luceneDAO;

	/** 创建非只能分词器，实现最细粒度分词 */
	private Analyzer analyzer = new IKAnalyzer(false);

	/***
	 * 
	 * @MethodName:buildIndex
	 * @Description:定时任务调度将数据库内容创建Lucene索引
	 * @throws Exception
	 * @author:bruce
	 * @return void
	 * @date:Apr 16, 20162:46:51 PM
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
						System.out.println(
								key + "--------------------------" + HtmlRegexpUtil.filterHtml(String.valueOf(value)));
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
	 * 
	 * @MethodName:emptyIndex
	 * @Description:定时任务调度将所有索引库内容清空
	 * @throws Exception
	 * @author:bruce
	 * @return void
	 * @date:Apr 16, 20162:47:21 PM
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
			int pageSize) throws Exception {

		// 默认IKAnalyzer()-false:实现最细粒度切分算法,true:分词器采用智能切分
		QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_40, fields, analyzer);

		parser.setDefaultOperator(QueryParser.AND_OPERATOR);

		Query query = parser.parse(keyWords);

		if (fields.length > 0) {
			if (fields[0] == "id" || "id".equals(fields[0])) {
				Term term = new Term("id", keyWords);
				query = new TermQuery(term);
			}
		}

		TopDocs topDocs = indexSearcher.search(query, 10000);

		return paginationQuery(analyzer, indexSearcher, query, topDocs, page, pageSize);
	}

	/**
	 * 
	 * @MethodName:paginationQuery
	 * @Description:分页|高亮查询
	 * @param analyzer分词器
	 * @param indexSearcher检索器
	 * @param query查询类型对象
	 * @param topDocs评分筛选项
	 * @param currentPage当前页
	 * @param pageSize每页条数
	 * @throws Exception
	 * @author:bruce
	 * @return List<Search>
	 * @date:Apr 16, 20162:54:34 PM
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
