package com.paySystem.ic.service.report;

import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:StatisticsService
 * @Description:统计服务
 * @author: 徐凯强
 * @version: V1.0
 * @date:2015-1-6下午10:32:04
 */
public interface StatisticsService {

	/** 常量 */
	public static final String STATISTICSSERVICE = "StatisticsService";

	/**
	 *@MethodName:calcPercents
	 *@Description:计算百分比
	 *@author:徐凯强
	 *@return MainTaskDTO主任务数据传输对象
	 *@date:2015-1-6下午10:32:54
	 *@version: V1.0
	 */
	public MainTaskDTO calcPercents();
}
