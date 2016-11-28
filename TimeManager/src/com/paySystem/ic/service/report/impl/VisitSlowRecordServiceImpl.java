package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.report.VisitSlowRecordDAO;
import com.paySystem.ic.service.report.VisitSlowRecordService;
import com.paySystem.ic.web.dto.report.VisitSlowRecordDTO;
/**
 * @ProjectName:omall20140708DS
 * @ClassName:VisitSlowRecordServiceImpl
 * @Description:访客流量统计Service实现类
 * @date: 2014-7-17
 * @author: 赵巧鹤
 * @version: V1.0
 */
@Repository(VisitSlowRecordService.VISITSLOWRECORDSERVICE)
public class VisitSlowRecordServiceImpl implements VisitSlowRecordService{
	@Resource
	VisitSlowRecordDAO visiSlowRecordDAO;

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.report.VisitSlowRecordService#queryAll(int, int, com.paySystem.ic.web.dto.report.VisitSlowRecordDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:TODO
	 *@param page
	 *@param pageNum
	 *@param visitSlowRecordDTO
	 *@param orderBy
	 *@return
	 *@Author:赵巧鹤
	 *@Date:2014-7-17下午05:01:43
	 */
	public QueryResult<VisitSlowRecordDTO> queryAll(int page, int pageNum,
			VisitSlowRecordDTO visitSlowRecordDTO,
			LinkedHashMap<String, String> orderBy) {
		QueryResult<VisitSlowRecordDTO> list=visiSlowRecordDAO.queryAll(page, pageNum, visitSlowRecordDTO, orderBy);
		return list;
		
	}

}
