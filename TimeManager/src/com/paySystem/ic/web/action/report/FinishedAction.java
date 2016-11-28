package com.paySystem.ic.web.action.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.collection.CollectionService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.report.FinishedService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:FinishedAction
 * @Description:已完成控制类
 * @author: 徐凯强
 * @version: V1.0
 * @date:2014-12-20下午09:51:21
 */
@Controller("/finished/finished")
@Scope("prototype")
public class FinishedAction extends BaseAction {

	private static final long serialVersionUID = -5930630782008482294L;

	/** 注入 */
	@Resource
	FinishedService finishedService;
	@Resource
	FunctionsService functionsService;
	@Resource
	CollectionService collectionService;
	/** 实例化 */
	private MainTaskDTO mainTaskDTO = new MainTaskDTO();

	public MainTaskDTO getMainTaskDTO() {
		return mainTaskDTO;
	}

	public void setMainTaskDTO(MainTaskDTO mainTaskDTO) {
		this.mainTaskDTO = mainTaskDTO;
	}

	/**
	 *@MethodName:list
	 *@Description:显示列表页面
	 *@author:徐凯强
	 *@return String返回字符串到struts.xml
	 *@date:2014-12-20下午10:18:36
	 */
	public String list() {
			mainTaskDTO.setStatus(-1);
			mainTaskDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
					new Date()));
			mainTaskDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",
					new Date()));
			/** 任务的状态 */
			this.getSession().setAttribute("statusValue", OptionsValue.STATUSVALUE);
			return "list";
	}

	/**
	 *@MethodName:jsonPageList
	 *@Description:异步获取列表数据
	 *@throws Exception抛出异常
	 *@author:徐凯强
	 *@return String返回struts.xml字符串
	 *@date:2014-12-20下午10:13:03
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("createTime", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<MainTaskDTO> result = finishedService.queryAll((mainTaskDTO
				.getPage() - 1)
				* pageNum, pageNum, mainTaskDTO, orderBy);
		/** 获取queryResult中的集合 */
		List<MainTaskDTO> mainTaskDTOs = result.getResultlist();
		/** 字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取SalesSummaryDTO集合 */
		for (int i = 0; i < mainTaskDTOs.size(); i++) {
			/** 向页面赋值 */
			MainTaskDTO mainTaskDTO = mainTaskDTOs.get(i);
			List<String> strings = new ArrayList<String>();
			/** 添加到字符串集合中去 */
			strings.add(mainTaskDTO.getMainTaskId().toString());
			/** 任务优先级 */
			if (mainTaskDTO.getLevel() != null) {
				if (mainTaskDTO.getLevel().equals(0)) {
					strings.add("无");
				} else if (mainTaskDTO.getLevel().equals(1)) {
					strings.add(Globals.IMG_LOW);
				} else if (mainTaskDTO.getLevel().equals(2)) {
					strings.add(Globals.IMG_MIDDLE);
				} else {
					strings.add(Globals.IMG_HIGH);
				}
			} else {
				strings.add("无");
			}
			/** 判断任务名称长度，或是内容太长则只显示部分信息，否则全部显示 **/
			if (mainTaskDTO.getMainTaskName().length() > 7) {
				mainTaskDTO.setMainTaskName(mainTaskDTO.getMainTaskName().substring(0, 6) + "......");
			} 
			/** 任务名称 */
			strings.add(mainTaskDTO.getMainTaskName());
			/** 任务状态 */
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATUSVALUE,
					mainTaskDTO.getStatus()));
			/** 创建时间 */
			strings.add(DateTimeTool
					.dateFormat("", mainTaskDTO.getCreateTime()));
			
			/**截止日期*/
			strings.add(DateTimeTool
					.dateFormat("", mainTaskDTO.getByDate()));
			String operation = "";
			operation += "<a href=finished/finished!checkUI?mainTaskDTO.mainTaskId="
					+ mainTaskDTO.getMainTaskId()
					+ " title='查看'>"
					+ Globals.IMG_VIEW + "</a>";
			strings.add(operation);
			lists.add(strings);
		}
		// /** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(mainTaskDTO.getPage(), result
				.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@MethodName:export
	 *@Description:导出
	 *@author:徐凯强
	 *@return String
	 *@date:2014-12-20下午11:11:50
	 */
	public String export() {
		String title = "任务汇总";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("createTime", "DESC");
		}
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("任务编号");
			headers.add("任务名称");
			headers.add("优先级");
			headers.add("状态");
			headers.add("创建时间");
			headers.add("截止时间");
			headers.add("任务描述");
			headers.add("任务评论");
			/** 获取要导出的集合 */
			QueryResult<MainTaskDTO> mainQueryResult = finishedService
					.exportMainTasks(mainTaskDTO, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			List<MainTaskDTO> dto = mainQueryResult.getResultlist();
			for (int i = 0; i < dto.size(); i++) {
				MainTaskDTO mainTaskDTO = dto.get(i);
				List<String> list = new ArrayList<String>();
				list = findExportList(list, mainTaskDTO);
				lists.add(list);
			}
			str = util.createXls(headers, lists, title, this.getResponse());
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *@MethodName:findExportList
	 *@Description:获取要导出的数据的字符串集合
	 *@param list
	 *@param mainTaskDTO
	 *@author:徐凯强
	 *@return List<String>
	 *@date:2014-12-20下午11:11:24
	 */
	private List<String> findExportList(List<String> list,
			MainTaskDTO mainTaskDTO) {
		list.add(Utils.getString(mainTaskDTO.getMainTaskId()));
		list.add(mainTaskDTO.getMainTaskName());
		list.add(Utils.getOptionsIntegerName(OptionsValue.LEVELVALUE,
				mainTaskDTO.getLevel()));
		list.add(Utils.getOptionsIntegerName(OptionsValue.STATUSVALUE,
				mainTaskDTO.getStatus()));
		list.add(DateTimeTool.dateFormat("", mainTaskDTO.getCreateTime()));
		list.add(DateTimeTool.dateFormat("", mainTaskDTO.getByDate()));
		list.add(mainTaskDTO.getDescr());
		list.add(mainTaskDTO.getComments());
		return list;
	}

	/**
	 *@Title:checkUI
	 *@Description:查看删除记录
	 *@Return:String
	 *@author:徐凯强
	 *@date:2014-12-21上午10:52:57
	 */
	public String checkUI() {
		this.setMethod("checkDetail");
		/** 任务的优先级 */
		this.getSession().setAttribute("levelValue", OptionsValue.LEVELVALUE);
		/** 任务的状态 */
		this.getSession().setAttribute("statusValue", OptionsValue.STATUSVALUE);
		mainTaskDTO = collectionService.findMainTaskDTO(mainTaskDTO
				.getMainTaskId());
		mainTaskDTO.setCreateTimeString(DateTimeTool.dateFormat("yyyy-MM-dd",
				mainTaskDTO.getCreateTime()));
		mainTaskDTO.setByDateString(DateTimeTool.dateFormat(
				"yyyy-MM-dd HH:mm:ss", mainTaskDTO.getByDate()));
		return "input";
	}

}
