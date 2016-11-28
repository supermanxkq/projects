package com.paySystem.ic.web.action.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.service.report.StatisticsService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:StatisticsAction
 * @Description:任务完成统计
 * @author: 徐凯强
 * @version: V1.0
 * @date:2015-1-6下午05:38:44
 */
@Controller("/statistics/statistics")
@Scope("prototype")
public class StatisticsAction extends BaseAction {

	/** 序列化 */
	private static final long serialVersionUID = 1L;
	/** 返回数据传输对象 */
	private ReturnDTO returnDTO = new ReturnDTO();
	/** 主任务数据传输对象 */
	private MainTaskDTO mainTaskDTO = new MainTaskDTO();
	/** 注入Service */
	@Autowired
	private StatisticsService statisticsService;

	public MainTaskDTO getMainTaskDTO() {
		return mainTaskDTO;
	}

	public void setMainTaskDTO(MainTaskDTO mainTaskDTO) {
		this.mainTaskDTO = mainTaskDTO;
	}

	public ReturnDTO getReturnDTO() {
		return returnDTO;
	}

	public void setReturnDTO(ReturnDTO returnDTO) {
		this.returnDTO = returnDTO;
	}

	/**
	 *@MethodName:list
	 *@Description:跳转到统计页面
	 *@author:徐凯强
	 *@return String返回字符串结果到struts.xml
	 *@date:2015-1-6下午05:39:09
	 *@version: V1.0
	 */
	public String list() {
		return "success";
	}

	/**
	 *@MethodName:calcPercents
	 *@Description:计算任务完成百分比
	 *@author:徐凯强
	 *@return String返回字符串结果到struts.xml
	 *@date:2015-1-6下午11:07:49
	 *@version: V1.0
	 */
	public String calcPercents() {
		mainTaskDTO = statisticsService.calcPercents();
		return Utils.printInfo(mainTaskDTO);
	}

}
