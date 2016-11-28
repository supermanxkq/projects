package com.paySystem.ic.dao.software;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.software.SoftWareStyle;
import com.paySystem.ic.web.dto.software.SoftWareStyleDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:SoftWareStyleDao
 * @Description:软件类型Dao
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 9, 20168:31:29 PM
 */
public interface SoftWareStyleDao {
	/** 常量 */
	public static final String SOFTWARESTYLEDAO = "SoftWareStyleDao";

	/**
	 * @MethodName:queryAll
	 * @Description:后台查询所有的软件分类
	 * @param firstIndex
	 * @param pageNum
	 * @param softWareStyleDTO
	 * @param orderBy
	 * @throws Exception
	 * @author:徐凯强
	 * @return QueryResult<SoftWareStyle>
	 * @date:Mar 9, 20168:33:51 PM
	 */
	public QueryResult<SoftWareStyle> queryAll(int firstIndex, int pageNum, SoftWareStyleDTO softWareStyleDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;
}
