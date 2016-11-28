package com.blog.dao.code;

import java.util.LinkedHashMap;

import com.blog.bean.QueryResult;
import com.blog.bean.code.Code;
import com.blog.dto.code.CodeDTO;

public interface CodeDAO {
	/**常量*/
	public static final String CODEDAO = "CodeDAO";


	public QueryResult<Code> queryAll(int firstIndex, int pageNum,
			CodeDTO codeDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;
}
