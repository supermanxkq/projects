package com.paySystem.ic.dao.report;

import com.paySystem.ic.bean.collection.MainTask;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:StatisticsDAO
 * @Description:统计百分比服务实现类
 * @author: 徐凯强
 * @version: V1.0
 * @date:2015-1-6下午10:37:02
 */
public interface StatisticsDao extends DAO<MainTask> {

	/** 常量 */
	public static final String STATISTICSDAO = "StatisticsDAO";

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
