package com.blog.service.blogtype;

import java.util.LinkedHashMap;
import java.util.Map;

import com.blog.bean.QueryResult;
import com.blog.bean.blogtype.BlogType;
import com.blog.dao.common.DAO;
import com.blog.dto.blogtype.BlogTypeDTO;

public interface BlogTypeService extends DAO<BlogType> {
	/** 常量 */
	public static final String BLOGTYPESERVICE = "BlogTypeService";

	public QueryResult<BlogTypeDTO> queryAll(int firstIndex, int pageNum,
			BlogTypeDTO blogTypeDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	public void addSaveDTO(BlogTypeDTO blogTypeDTO);

	public Map<Integer,String> getAllTypes();
}
