package com.paySystem.ic.web.action.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.report.ModStockService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:ModStockAction
 * @Description:库存变动Action
 * @date: 2014-3-14下午05:25:54
 * @author: 王楠
 * @version: V1.0
 */
@Controller("/report/modStockList")
@Scope("prototype")
public class ModStockAction extends BaseAction {

	private static final long serialVersionUID = -6333175596566821109L;
	@Resource
	ModStockService modStockService;
	@Resource
	FunctionsService functionsService;

	private ModStockDTO modStockDTO = new ModStockDTO();

	public ModStockDTO getModStockDTO() {
		return modStockDTO;
	}

	public void setModStockDTO(ModStockDTO modStockDTO) {
		this.modStockDTO = modStockDTO;
	}

	public String list() {
		UserSession us = Utils.getUserSession();
		this.getRequest().setAttribute("status",
				OptionsValue.INVENTORYCHANGE_STATUS);
		modStockDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		modStockDTO.setEndDate(DateTimeTool
				.dateFormat("yyyy-MM-dd", new Date()));
		return "list1";
	}

	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();

		UserSession us = Utils.getUserSession();

		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())/**
		 * 
		 * 如果有值,按照界面的值排序，如果没值，按照我下面的更新时间降序排
		 */
		&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			/** 如果页面排序方式没有要求，则设置更新时间排序 */
			orderBy.put("id", "desc");
		}
		/** 查询结果 */
		QueryResult<ModStockDTO> queryAll = new QueryResult<ModStockDTO>();
		try {
			queryAll = modStockService.queryAll((modStockDTO.getPage() - 1)* pageNum,
					pageNum, modStockDTO, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ModStockDTO> list=queryAll.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			/** 向页面赋值 */
			ModStockDTO modStockDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(modStockDTO.getId()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.USER_LEVEL,
					modStockDTO.getInStype()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.USER_LEVEL,
					modStockDTO.getOutStype()));
			strings.add(Utils.getString(modStockDTO.getCardCount()));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.INVENTORYCHANGE_STATUS, modStockDTO
							.getStatus()));
			strings.add(Utils.getString(modStockDTO.getBeginCardNo()));
			strings.add(Utils.getString(modStockDTO.getProposer()));
			strings.add(Utils.getString(modStockDTO.getCheckMen()));
			strings.add(Utils.getString(modStockDTO.getCreateTimeStr()));
			strings.add(Utils.getString(modStockDTO.getAuditTimeStr()));
			lists.add(strings);
		}
		PageView pageView = new PageView(modStockDTO.getPage(), queryAll.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:export
	 *@Description:导出功能
	 *@param:@return
	 *@return:String
	 *@author:王楠
	 */
	public String export() {
		String title = "库存变动查看表";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		@SuppressWarnings("unused")
		UserSession us = Utils.getUserSession();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("id", "desc");
		}
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("流水号");
			headers.add("入库方类型");
			headers.add("出库方类型");
			headers.add("数量");
			headers.add("状态");
			headers.add("起始卡号");
			headers.add("入库操作人");
			headers.add("审核操作人");
			headers.add("创建时间");
			headers.add("入库时间");
			List<ModStockDTO> modStockList = modStockService.exportModStockXls(
					modStockDTO, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			for (int i = 0; i < modStockList.size(); i++) {
				ModStockDTO msto = modStockList.get(i);
				List<String> list = new ArrayList<String>();
				list = retModStockViews(list, msto);
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
	 *@Title:retModStockViews
	 *@Description:封装String方法
	 *@param:@param list
	 *@param:@param msto
	 *@param:@return
	 *@return:List<String>
	 *@author:王楠
	 */
	private List<String> retModStockViews(List<String> modStockList,
			ModStockDTO msto) {
		modStockList.add(Utils.getString(msto.getId()));
		modStockList.add(Utils.getOptionsIntegerName(OptionsValue.USER_LEVEL,
				msto.getInStype()));
		modStockList.add(Utils.getOptionsIntegerName(OptionsValue.USER_LEVEL,
				msto.getOutStype()));
		modStockList.add(Utils.getString(msto.getCardCount()));
		modStockList.add(Utils.getOptionsIntegerName(
				OptionsValue.INVENTORYCHANGE_STATUS, msto.getStatus()));
		modStockList.add(Utils.getString(msto.getBeginCardNo()));
		modStockList.add(Utils.getString(msto.getProposer()));
		modStockList.add(Utils.getString(msto.getCheckMen()));
		modStockList.add(Utils.getString(msto.getCreateTimeStr()));
		modStockList.add(Utils.getString(msto.getAuditTimeStr()));
		return modStockList;
	}
}