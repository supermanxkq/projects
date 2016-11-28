package com.paySystem.ic.service.report.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paySystem.ic.dao.report.StatisticsDao;
import com.paySystem.ic.service.report.StatisticsService;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:StatisticsServiceImpl
 * @Description:统计百分比服务实现类
 * @author: 徐凯强
 * @version: V1.0
 * @date:2015-1-6下午10:34:13
 */
@Service(StatisticsService.STATISTICSSERVICE)
public class StatisticsServiceImpl implements StatisticsService {

	/** 注入statisticsDao */
	@Autowired
	private StatisticsDao statisticsDao;

	/**
	 *@MethodName:calcPercents
	 *@Description:计算百分比
	 *@author:徐凯强
	 *@return MainTaskDTO主任务数据传输对象
	 *@date:2015-1-6下午10:32:54
	 *@version: V1.0
	 */
	public MainTaskDTO calcPercents() {
		return statisticsDao.calcPercents();
	}
}
