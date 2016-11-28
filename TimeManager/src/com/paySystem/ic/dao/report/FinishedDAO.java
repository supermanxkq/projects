package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.collection.MainTask;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:TimeManager 
 * @ClassName:FinishedDAO  
 * @Description:任务报表Dao
 * @author: 徐凯强
 * @version: V1.0
 * @date:2014-12-20下午11:05:18
 */
public interface FinishedDAO extends DAO<MainTask> {

	/**常量*/
	public static final String FinishedDAO = "FINISHEDDAO";

	/**
	 *@MethodName:queryAll 
	 *@Description:查询所有
	 *@param page
	 *@param pageNum
	 *@param mainTaskDTO
	 *@param orderBy
	 *@author:徐凯强
	 *@return QueryResult<MainTaskDTO>
	 *@date:2014-12-20下午11:05:40
	 */
	public QueryResult<MainTaskDTO> queryAll(int page, int pageNum,
			MainTaskDTO mainTaskDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;

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
			LinkedHashMap<String, String> orderBy) throws Exception;
}
