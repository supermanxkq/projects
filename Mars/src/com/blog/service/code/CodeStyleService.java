package com.blog.service.code;

import com.blog.bean.QueryResult;
import com.blog.bean.code.CodeStyle;
import com.blog.dao.common.DAO;

public interface CodeStyleService extends DAO<CodeStyle> {
	/** 常量 */
	public static final String CODESTYLESERVICE = "CodeStyleService";

	public QueryResult<CodeStyle> queryAll() throws Exception;
}
