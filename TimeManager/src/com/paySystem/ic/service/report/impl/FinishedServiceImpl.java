package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.report.FinishedDAO;
import com.paySystem.ic.service.report.FinishedService;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;
/**
 * @ProjectName:TimeManager 
 * @ClassName:FinishedServiceImpl  
 * @Description:Service实现类
 * @author: 徐凯强
 * @version: V1.0
 * @date:2014-12-20下午11:12:48
 */
@Service(FinishedService.FINISHEDSERVICE)
public class FinishedServiceImpl implements FinishedService{

	@Resource
	FinishedDAO finishedDAO;

	/**
	 *@MethodName:queryAll
	 *@Description:查询所有
	 *@param page
	 *@param pageNum
	 *@param mainTaskDTO
	 *@param orderBy
	 *@author:徐凯强
	 *@date:2014-12-20下午11:13:11
	 */
	public QueryResult<MainTaskDTO> queryAll(int page, int pageNum,
			MainTaskDTO mainTaskDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		/** 调用DAO获取QueryResult结果 */
		QueryResult<MainTaskDTO> queryResult=finishedDAO.queryAll(
				page, pageNum, mainTaskDTO, orderBy);
		return queryResult;
	}


	/**
	 *@MethodName:exportMainTasks 
	 *@Description:导出信息
	 *@param mainTaskDTO主任务数据传输对象
	 *@param orderBy排序
	 *@author:徐凯强
	 *@return QueryResult<MainTaskDTO>集合、条数
	 *@date:2014-12-20下午11:01:20
	 */
	public QueryResult<MainTaskDTO> exportMainTasks(
			MainTaskDTO mainTaskDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return finishedDAO.exportMainTasks(mainTaskDTO, orderBy);
	}
}
