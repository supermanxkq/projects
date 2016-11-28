package com.paySystem.ic.dao.article.impl;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;

/**
 * @author bolt lucene工具类
 */
public class LuceneUtils {
	/**
	 * @param query
	 * @param a
	 * @param fieldName
	 * @param txt
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 *             高亮关键字
	 */
	public static String lighterStr(Query query, Analyzer a, String fieldName, String txt)
			throws IOException, InvalidTokenOffsetsException {
		String str = null;// 设定放回结果
		QueryScorer queryScorer = new QueryScorer(query);// 如果有需要，可以传入评分
		// 设置高亮标签
		Formatter formatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
		// 高亮分析器
		Highlighter hl = new Highlighter(formatter, queryScorer);
		Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
		hl.setTextFragmenter(fragmenter);
		// 获取返回结果
		str = hl.getBestFragment(a, fieldName, txt);
		if (str == null) {
			return txt;
		}
		return str;
	}
}
