package com.paySystem.ic.web.action.lucene;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.lucene.search.IndexSearcher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.search.Search;
import com.paySystem.ic.service.lucene.LuceneService;
import com.paySystem.ic.utils.ExceptionUtil;
import com.paySystem.ic.utils.PropertiesUtil;
import com.paySystem.ic.utils.ReturnTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;

/**
 * 
 * @ProjectName:MarsPlan
 * @ClassName:LuceneAction
 * @Description:索引查询检索
 * @date: Apr 16, 20163:19:07 PM
 * @author: bruce
 * @version: V1.0
 */

@Controller("/lucene/lucene")
@Scope("prototype")
public class LuceneAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285246842174744688L;
	@Resource
	private LuceneService luceneService;
	/** 查询关键字 */
	private String keyWord;
	/** 日志 */
	public Logger logger = Logger.getLogger(LuceneAction.class);

	/**
	 * 
	 * @MethodName:luceneSearch
	 * @Description:使用Lucene进行全文检索
	 * @author:bruce
	 * @return String
	 * @throws UnsupportedEncodingException
	 * @date:Apr 16, 20163:19:27 PM
	 */
	public String luceneSearch() throws UnsupportedEncodingException {
		/** 从配置文件中读取要索引的列和要查询的索引目录 */
		// 1.获取Properties配置文件(search.properties)
		String path = PropertiesUtil.path();
		Properties prop = PropertiesUtil.getProperties(path + "search.properties");
		// 2.获取search.properties文件中的statmentName参数数组(与Mapper文件中的id对应)
		String fields = prop.getProperty("statementName");
		// 3.获取search.properties文件中的key参数数组(与Mapper文件中的id对应)
		String key = prop.getProperty("key");

		int page = 1;
		int row = 10;
		ReturnTool returnTool = new ReturnTool();

		int pages = 0;
		int counts = 0;
		List<Search> searchResult = null;
		String[] f = key.split(",");
		try {
			if (null != keyWord && !"".equals(keyWord)) {
				if (null != fields && fields.split(",").length > 1) {
					IndexSearcher indexSearcher = luceneService.getSearchers(fields);
					searchResult = luceneService.queryFromIndex(indexSearcher, keyWord, f, page, row);

					counts = luceneService.count(indexSearcher, keyWord, f);
				} else {
					IndexSearcher indexSearcher = luceneService.getSearcher(fields);
					// String[] f = key.split(",");
					searchResult = luceneService.queryFromIndex(indexSearcher, keyWord, f, page, row);
					counts = luceneService.count(indexSearcher, keyWord, f);
				}
			}
			pages = (int) Math.ceil((double) counts / row);
			returnTool.setPages(pages);
			returnTool.setSuccess(true);
			returnTool.setSearches(searchResult);
			returnTool.setCounts(counts);
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = ExceptionUtil.getExceptionMessage(e);
			logger.info(errorMsg);
			returnTool.setSuccess(false);
			returnTool.setMsg(errorMsg);
		}
		return Utils.printInfo(returnTool);
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}
