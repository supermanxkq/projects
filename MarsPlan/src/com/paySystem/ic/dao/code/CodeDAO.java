package com.paySystem.ic.dao.code;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.code.Code;
import com.paySystem.ic.web.dto.code.CodeDTO;

public interface CodeDAO {
	/**常量*/
	public static final String CODEDAO = "CodeDAO";


	public QueryResult<Code> queryAll(int firstIndex, int pageNum,
			CodeDTO codeDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;
}
