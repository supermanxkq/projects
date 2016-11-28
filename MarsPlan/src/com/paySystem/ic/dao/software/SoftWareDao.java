package com.paySystem.ic.dao.software;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.software.SoftWare;
import com.paySystem.ic.web.dto.software.SoftWareDTO;

/**
 * @ProjectName:MarsPlan 
 * @ClassName:SoftWareDao  
 * @Description:软件Dao
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 9, 20168:38:39 PM
 */
public interface SoftWareDao {
	/** 常量 */
	public static final String SOFTWAREDAO = "SoftWareDao";

	public QueryResult<SoftWare> queryAll(int firstIndex, int pageNum, SoftWareDTO softWareDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;
}
