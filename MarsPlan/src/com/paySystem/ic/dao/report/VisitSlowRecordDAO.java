package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.VisitSlowRecord;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.report.VisitSlowRecordDTO;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:VisitSlowRecordDAO
 * @Description:访客流量统计DAO接口
 * @date: 2014-7-17
 * @author: 赵巧鹤
 * @version: V1.0
 */
public interface VisitSlowRecordDAO extends DAO<VisitSlowRecord>{
	public static final String VISITSLOWRECORDDAO ="visitSlowRecordDAO";
	
	/**
	*@Title:queryAll
	*@Description:查询所有
	*@Params:@param page
	*@Params:@param pageNum
	*@Params:@param visitSlowRecordDTO
	*@Params:@param orderBy
	*@Params:@return
	*@Return:QueryResult<VisitSlowRecordDTO>
	*@author:赵巧鹤
	*@Date:2014-7-17下午03:31:32
	*/
	public QueryResult<VisitSlowRecordDTO> queryAll(int page,int pageNum,VisitSlowRecordDTO visitSlowRecordDTO,LinkedHashMap<String, String> orderBy);

}
