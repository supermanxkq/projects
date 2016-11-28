package com.paySystem.ic.dao.article;

import java.util.LinkedHashMap;
import java.util.Map;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.article.ArticleTypeDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:ArticleTypeDAO
 * @Description:文章Dao
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 10, 20163:47:09 PM
 */
public interface ArticleTypeDAO {
	/** 常量 */
	public static final String ARTICLETYPEDAO = "ArticleTypeDAO";

	public QueryResult<ArticleTypeDTO> queryAll(int firstIndex, int pageNum, ArticleTypeDTO articleTypeDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	public Map<Integer, String> getAllTypes();
}
