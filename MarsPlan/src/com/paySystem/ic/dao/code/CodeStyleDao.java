package com.paySystem.ic.dao.code;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.code.CodeStyle;
import com.paySystem.ic.web.dto.code.CodeStyleDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:CodeStyleDao
 * @Description:代码分类数据库操作类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 6, 20163:51:06 AM
 */
public interface CodeStyleDao {
	/** 常量 */
	public static final String CODESTYLEDAO = "CodeStyleDao";

	/**
	 * @MethodName:queryAll
	 * @Description:后台查询所有代码分类
	 * @param firstIndex
	 * @param pageNum
	 * @param codeStyleDTO
	 * @param orderBy
	 * @throws Exception
	 * @author:徐凯强
	 * @return QueryResult<CodeStyle>
	 * @date:Mar 6, 20163:53:59 AM
	 */
	public QueryResult<CodeStyle> queryAll(int firstIndex, int pageNum, CodeStyleDTO codeStyleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;
}
