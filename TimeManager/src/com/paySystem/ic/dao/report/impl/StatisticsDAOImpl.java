package com.paySystem.ic.dao.report.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.collection.MainTask;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.StatisticsDao;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:TimeManager
 * @ClassName:StatisticsDAO
 * @Description:统计百分比服务实现类
 * @author: 徐凯强
 * @version: V1.0
 * @date:2015-1-6下午10:37:02
 */
@Repository(StatisticsDao.STATISTICSDAO)
public class StatisticsDAOImpl extends DaoSupport<MainTask> implements
		StatisticsDao {

	/**
	 *@MethodName:calcPercents
	 *@Description:计算百分比
	 *@author:徐凯强
	 *@return MainTaskDTO主任务数据传输对象
	 *@date:2015-1-6下午10:32:54
	 *@version: V1.0
	 */
	public MainTaskDTO calcPercents() {
		/** 实例化要返回的主任务对象 */
		MainTaskDTO mainTaskDTO = new MainTaskDTO();
		/** 获取UserSession对象 */
		UserSession userSession = Utils.getUserSession();
		/** sql语句 */
		/** 已完成条数 */
		StringBuffer wherejpql2 = new StringBuffer();
		wherejpql2.append(" and o.status=0");
		wherejpql2
				.append(" and o.userName='" + userSession.getUserName() + "'");
		long finishedRecord = this.getCount(null, wherejpql2.toString());
		/** 未完成条数 */
		StringBuffer wherejpql3 = new StringBuffer();
		wherejpql3.append(" and o.status=1");
		wherejpql3
				.append(" and o.userName='" + userSession.getUserName() + "'");
		long unFinishedRecord = this.getCount(null, wherejpql3.toString());
		/** 已延迟条数 */
		StringBuffer wherejpql4 = new StringBuffer();
		wherejpql4.append(" and o.status=9");
		wherejpql4
				.append(" and o.userName='" + userSession.getUserName() + "'");
		long delayedRecord = this.getCount(null, wherejpql4.toString());

		long[] percents = new long[3];
		/** 已完成 */
		percents[0] = finishedRecord;
		/** 未完成 */
		percents[1] = unFinishedRecord;
		/** 已延迟 */
		percents[2] = delayedRecord;
		mainTaskDTO.setPercents(percents);
		return mainTaskDTO;
	}
}
