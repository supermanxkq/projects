package com.paySystem.ic.service.code;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.code.Code;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.code.CodeDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:CodeService
 * @Description:代码服务类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 6, 20165:08:49 AM
 */
public interface CodeService extends DAO<Code> {
	/** 常量 */
	public static final String CODESERVICE = "CodeService";

	/**
	 * @MethodName:queryAll
	 * @Description:前台查询所有的代码集合
	 * @param firstIndex
	 * @param pageNum
	 * @param codeDTO
	 * @param orderBy
	 * @throws Exception
	 * @author:徐凯强
	 * @return QueryResult<Code>
	 * @date:Mar 6, 20165:09:14 AM
	 */
	public QueryResult<Code> queryAll(int firstIndex, int pageNum, CodeDTO codeDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 * @MethodName:addSave
	 * @Description:后台添加代码信息
	 * @param codeDTO
	 * @author:徐凯强
	 * @return void
	 * @date:Mar 6, 20165:10:20 AM
	 */
	public void addSave(CodeDTO codeDTO);

	/**
	 * @MethodName:updateData
	 * @Description:后台更新代码库数据
	 * @param codeDTO
	 * @author:徐凯强
	 * @return void
	 * @date:Mar 7, 20165:56:40 PM
	 */
	public void updateData(CodeDTO codeDTO);
}
