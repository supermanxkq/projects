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
import com.paySystem.ic.service.report.OrganSettTotalService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.OrganSettTotalDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:OrganSettTotal
 * @Description:机构结算报表查询Action
 * @date: 2014-3-12上午10:43:26
 * @author: 王月
 * @version: V1.0
 */
@Controller("/report/organSettTotal")
@Scope("prototype")
public class OrganSettTotalAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	OrganSettTotalService organSettTotalService;

	private OrganSettTotalDTO organSettTotalDTO = new OrganSettTotalDTO();

	public OrganSettTotalDTO getOrganSettTotalDTO() {
		return organSettTotalDTO;
	}

	public void setOrganSettTotalDTO(OrganSettTotalDTO organSettTotalDTO) {
		this.organSettTotalDTO = organSettTotalDTO;
	}

	/**
	 *@Title:list
	 *@Description:显示列表页面
	 *@param:@return
	 *@return:String
	 *@author:王月
	 *@thorws:
	 */
	public String list() {
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		   case 0:
			   break;
		   case 1:
			   break;
		   case 2:
			return "intercepthtml";
		 }
		
		organSettTotalDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		organSettTotalDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		
		this.getRequest()
				.setAttribute("status", OptionsValue.MERSETTTOTAL_FLAG);// 交易类型
		
		return "list";
	}

	/**
	 *@Title:jsonPageList
	 *@Description:定义前台显示查询所有的方法
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author:王月
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/***判断排序参数是否有值*/
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {/**如果页面没有要求排序方式，则设置按照会员编号排序*/
			orderBy.put("organSettId", "desc");
		}
		List<OrganSettTotalDTO> organSettTotal = organSettTotalService
				.queryAll((organSettTotalDTO.getPage() - 1) * pageNum, pageNum,
						organSettTotalDTO, orderBy);
		
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < organSettTotal.size(); i++) {
			/**向页面赋值*/
			OrganSettTotalDTO organSettTotalDTO = organSettTotal.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));/**序号*/
			strings.add(Utils.getString(organSettTotalDTO.getOrganId()));/**机构编号*/
			strings.add(Utils.getString(organSettTotalDTO.getOrgName()));/**机构名称*/
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.MERSETTTOTAL_FLAG, organSettTotalDTO
							.getStatus()));/**结算状态*/
			strings.add(NumberUtil.numberFormat("", organSettTotalDTO
					.getOwnSettle()));/**本机构结算金额*/
			strings.add(NumberUtil.numberFormat("", organSettTotalDTO
					.getOtherSettle()));/**其他机构结算金额*/
			strings.add(NumberUtil.numberFormat("", organSettTotalDTO
					.getOwnOutSettle()));/**本机构卡对外结算金额*/
			strings.add(NumberUtil.numberFormat("", organSettTotalDTO
					.getTotalSettAmt()));/**总结算金额*/
			lists.add(strings);
		}
		PageView pageView = new PageView(organSettTotalDTO.getPage(),
				organSettTotal.size());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 *@Title:export
	 *@Description:导出报表
	 *@param:@return
	 *@return:String
	 *@author:王月
	 *@thorws:
	 */

	public String export() {
		String title = "机构结算报表";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();

		/**判断排序参数是否有值*/
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("organSettId", "desc");
		}
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("机构编号");
			headers.add("机构名称");
			headers.add("结算状态");
			headers.add("本机构结算金额");
			headers.add("其他机构结算金额");
			headers.add("本机构卡对外结算金额");
			headers.add("总结算金额");
			List<OrganSettTotalDTO> organList = organSettTotalService.queryorganSettTotalReport(organSettTotalDTO, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			for (int i = 0; i < organList.size(); i++) {
				OrganSettTotalDTO organ = organList.get(i);
				List<String> slist = new ArrayList<String>();
				slist = retTradesViews(slist, organ);
				lists.add(slist);
			}
			str = util.createXls(headers, lists, title, this.getResponse());
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	/**
	 *@Title:retTradesViews
	 *@Description:封装String方法
	 *@param:@param mdtoList
	 *@param:@param mctdto
	 *@param:@return
	 *@return:List<String>
	 *@author:王月
	 *@thorws:
	 */
	private List<String> retTradesViews(List<String> odtoList,OrganSettTotalDTO orgdto) {
		odtoList.add(Utils.getString(orgdto.getOrganId()));/**机构编号*/
		odtoList.add(Utils.getString(orgdto.getOrgName()));/**机构名称*/
		odtoList.add(Utils.getOptionsIntegerName(OptionsValue.MERSETTTOTAL_FLAG, orgdto.getStatus()));/**结算状态*/
		odtoList.add(NumberUtil.numberFormat("", orgdto.getOwnSettle()));/**本机构结算金额*/
		odtoList.add(NumberUtil.numberFormat("", orgdto.getOtherSettle()));/**其他机构结算金额*/
		odtoList.add(NumberUtil.numberFormat("", orgdto.getOwnOutSettle()));/**本机构卡对外结算金额*/
		odtoList.add(NumberUtil.numberFormat("", orgdto.getTotalSettAmt()));/**总结算金额*/
		odtoList.add(Utils.getString(orgdto.getOperator()));/**操作人*/
		return odtoList;
	}
}
