package com.blog.service.code;

import java.util.LinkedHashMap;

import com.blog.bean.QueryResult;
import com.blog.bean.code.Code;
import com.blog.dao.common.DAO;
import com.blog.dto.code.CodeDTO;

public interface CodeService extends DAO<Code> {
	/** 常量 */
	public static final String CODESERVICE = "CodeService";

	public QueryResult<Code> queryAll(int firstIndex, int pageNum, CodeDTO codeDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;
}
