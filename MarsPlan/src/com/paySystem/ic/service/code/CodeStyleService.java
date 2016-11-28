package com.paySystem.ic.service.code;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.code.CodeStyle;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.code.CodeStyleDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:MarsPlan
 * @ClassName:CodeStyleService
 * @Description:代码分类服务类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 6, 20163:47:15 AM
 */
public interface CodeStyleService extends DAO<CodeStyle> {
	/** 常量 */
	public static final String CODESTYLESERVICE = "CodeStyleService";

	/**
	 * @MethodName:queryAll
	 * @Description:前台获取所有代码分类
	 * @throws Exception
	 * @author:徐凯强
	 * @return QueryResult<CodeStyle>
	 * @date:Mar 6, 20163:45:16 AM
	 */
	public QueryResult<CodeStyle> queryAll() throws Exception;

	/**
	 * @MethodName:queryAll
	 * @Description:后台获取所有代码分类
	 * @param firstIndex
	 * @param pageNum
	 * @param codeStyleDTO
	 * @param orderBy
	 * @throws Exception
	 * @author:徐凯强
	 * @return QueryResult<CodeStyle>
	 * @date:Mar 6, 20163:46:25 AM
	 */
	public QueryResult<CodeStyle> queryAll(int firstIndex, int pageNum, CodeStyleDTO codeStyleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 * @MethodName:addSave
	 * @Description:后台添加分类信息
	 * @param codeStyle
	 * @author:徐凯强
	 * @return void
	 * @date:Mar 6, 20164:49:24 AM
	 */
	public void addSave(CodeStyle codeStyle);
	
	/**
	 *@MethodName:queryStyles 
	 *@Description:后台添加代码时需要的代码类型集合
	 *@author:徐凯强
	 *@return List<OptionsInteger>
	 *@date:Mar 6, 20165:48:49 AM
	 */
	public List<OptionsInteger> queryStyles();

}
