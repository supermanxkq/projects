package com.paySystem.ic.service.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.report.VisitSlowRecordDTO;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:VisitSlowRecordService
 * @Description:流量统计Service接口
 * @date: 2014-7-17
 * @author: 赵巧鹤
 * @version: V1.0
 */
public interface VisitSlowRecordService {
	
public static final String VISITSLOWRECORDSERVICE ="visitSlowRecordService";

/**
*@Title:queryAll
*@Description:TODO
*@Params:@param page
*@Params:@param pageNum
*@Params:@param visitSlowRecordDTO
*@Params:@param orderBy
*@Params:@return
*@Return:QueryResult<VisitSlowRecordDTO>
*@author:赵巧鹤
*@Date:2014-7-17下午05:00:17
*/
public QueryResult<VisitSlowRecordDTO> queryAll(int page, int pageNum,
		VisitSlowRecordDTO visitSlowRecordDTO,
		LinkedHashMap<String, String> orderBy);

}
