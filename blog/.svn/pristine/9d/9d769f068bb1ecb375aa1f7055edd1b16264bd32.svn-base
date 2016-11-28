package com.blog.dao.blogtype;

import java.util.LinkedHashMap;
import java.util.Map;

import com.blog.bean.QueryResult;
import com.blog.dto.blogtype.BlogTypeDTO;

public interface BlogTypeDAO {
	/**常量*/
	public static final String BLOGTYPEDAO = "BlogTypeDAO";


	public QueryResult<BlogTypeDTO> queryAll(int firstIndex, int pageNum,
			BlogTypeDTO blogTypeDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	public Map<Integer,String> getAllTypes();
}
