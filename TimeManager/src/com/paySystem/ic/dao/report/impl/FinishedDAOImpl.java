package com.paySystem.ic.dao.report.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.collection.MainTask;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.FinishedDAO;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;
/**
 * @ProjectName:TimeManager 
 * @ClassName:FinishedDAOImpl  
 * @Description:DAO接口的实现
 * @author: 徐凯强
 * @version: V1.0
 * @date:2014-12-21上午10:27:50
 */
@Repository(FinishedDAO.FinishedDAO)
public class FinishedDAOImpl extends DaoSupport<MainTask> implements FinishedDAO {

	/**
	 *@MethodName:queryAll
	 *@Description:查询任务信息
	 *@param page
	 *@param pageNum
	 *@param mainTaskDTO
	 *@param orderBy
	 *@throws Exception  书籍名称
	 *@author:徐凯强
	 *@date:2014-12-21上午10:28:08
	 */
	public QueryResult<MainTaskDTO> queryAll(int page, int pageNum,
			MainTaskDTO mainTaskDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		List<Object> params=new ArrayList<Object>();/**参数设置*/
		StringBuilder sb=new StringBuilder();/**封装查询where条件*/
		QueryResult<MainTask> queryResult = null;
		QueryResult<MainTaskDTO> mainQueryResult=new QueryResult<MainTaskDTO>();
		if (StringUtils.isNotBlank(mainTaskDTO.getMainTaskName())) {
			/**拼接sql语句*/
			sb.append(" and o.mainTaskName like ?").append(params.size()+1);
			/**为where语句赋值*/
			params.add("%"+mainTaskDTO.getMainTaskName().trim()+"%");
		}
		if (mainTaskDTO.getStatus()!=null&&!mainTaskDTO.getStatus().equals(-1)) {
		    sb.append(" and o.status="
				+mainTaskDTO.getStatus());
		}
		if (StringUtils.isNotBlank(mainTaskDTO.getBeginDate())) {
			sb.append(" and o.createTime>='" +  DateTimeTool.queryStartDate(mainTaskDTO.getBeginDate())+"'");
		}
		if (StringUtils.isNotBlank(mainTaskDTO.getEndDate())) {
			sb.append(" and o.createTime<='" + DateTimeTool.queryEndDate(mainTaskDTO.getEndDate())+"'");
		}
		
		sb.append(" and o.userName='"
				+ Utils.getUserSession().getUserName() + "'");
		/** 获取带有实体的QueryResult */
		queryResult=this.getScrollData(page,
				pageNum,sb.toString(),params.toArray(),orderBy);
		/**获取实体集合*/
		List<MainTask> list=queryResult.getResultlist();
		/**进行集合的转换*/
		List<MainTaskDTO> dtos=new ArrayList<MainTaskDTO>();
		for (MainTask mainTask : list) {
			try {
				MainTaskDTO mainTaskDTO2=new MainTaskDTO();
				mainTaskDTO2=(MainTaskDTO) EntityDtoConverter.bean2Dto(mainTask, mainTaskDTO2);
				dtos.add(mainTaskDTO2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mainQueryResult.setResultlist(dtos);
		mainQueryResult.setTotalrecord(queryResult.getTotalrecord());
		return mainQueryResult;
	}


	/**
	 *@MethodName:exportMainTasks
	 *@Description:导出信息
	 *@param mainTaskDTO
	 *@param orderBy
	 *@author:徐凯强
	 *@date:2014-12-20下午11:07:01
	 */
	public QueryResult<MainTaskDTO> exportMainTasks(
			MainTaskDTO mainTaskDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return queryAll(-1, -1, mainTaskDTO, orderBy);
	}
}
