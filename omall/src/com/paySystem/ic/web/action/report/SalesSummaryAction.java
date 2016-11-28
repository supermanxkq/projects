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
import com.paySystem.ic.service.report.SalesSummaryService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.SalesSummaryDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:SalesSummaryAction
 * @Description:销售额汇总action
 * @date: 2014-7-16
 * @author: 王楠
 * @version: V1.0
 */
@Controller("/report/salesSummary")
@Scope("prototype")
public class SalesSummaryAction extends BaseAction{

	private static final long serialVersionUID = -5930630782008482294L;

	@Resource
	SalesSummaryService salesSummaryService;
	@Resource
	FunctionsService functionsService;
	
	private SalesSummaryDTO salesSummaryDTO =new SalesSummaryDTO();
	
	public SalesSummaryDTO getSalesSummaryDTO() {
		return salesSummaryDTO;
	}

	public void setSalesSummaryDTO(SalesSummaryDTO salesSummaryDTO) {
		this.salesSummaryDTO = salesSummaryDTO;
	}

	/**
	*@Title:list
	*@Description:显示列表页面
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-16上午10:48:33
	*/
	public String list(){
		/**0、总部 1、机构 2、商户*/
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel()==0) {
		salesSummaryDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				DateTimeTool.nMonthsAgo(3,new Date())));
		salesSummaryDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		    return "list";
		} else {
			return "intercepthtml";
		}
	}
	
	/**
	*@Title:jsonPageList
	*@Description: 异步获取列表数据 ps:查询所有汇总信息
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:王楠
	*@Date:2014-7-16下午04:48:55
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
			orderBy.put("lastTime", "desc");
		}
		/** 获取queryResult中的值 */
        QueryResult<SalesSummaryDTO> result=salesSummaryService.queryAll(
        		(salesSummaryDTO.getPage()-1)*pageNum,
        		      pageNum, salesSummaryDTO, orderBy);
		/** 获取queryResult中的集合 */
        List<SalesSummaryDTO> salesList=result.getResultlist();
        /** 汇总信息字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取SalesSummaryDTO集合 */
		for (int i=0;i<salesList.size();i++) {
			/**向页面赋值*/
			SalesSummaryDTO salesSummaryDTO=salesList.get(i);
		    List<String> strings=new ArrayList<String>();
			/** 添加到字符串集合中去 */
            strings.add(String.valueOf(i+1));
            strings.add(Utils.getString(salesSummaryDTO.getMerId()));
            strings.add(Utils.getString(salesSummaryDTO.getMerName()));
            strings.add(Utils.getString(salesSummaryDTO.getSalesQty()));
			strings.add(NumberUtil.numberFormat("#,##0.0000", salesSummaryDTO.getSalesAmt()));
			strings.add(Utils.getString(salesSummaryDTO.getLastTime()));
			lists.add(strings);
		}
		/** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(salesSummaryDTO.getPage(), result
				.getTotalrecord());

		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));

		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	*@Title:export
	*@Description:导出
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-17上午09:22:57
	*/
	public String export() {
		String title = "销售额汇总";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("lastTime", "DESC");
		}
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("商户编号");
			headers.add("商户名称");
			headers.add("成交数量");
			headers.add("销售额");
			headers.add("成交时间");
			/** 获取要导出的集合 */
			QueryResult<SalesSummaryDTO> salesList = salesSummaryService.
			exportSalesSummary(salesSummaryDTO, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			List<SalesSummaryDTO> dto = salesList.getResultlist();
			for (int i = 0; i < dto.size(); i++) {
				SalesSummaryDTO salesSummaryDTO = dto.get(i);
				List<String> list = new ArrayList<String>();
				list = findExportList(list, salesSummaryDTO);
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
	*@Title:findExportList
	*@Description:获取要导出的数据的字符串集合
	*@Params:@param list
	*@Params:@param salesSummaryDTO 销售额汇总实体的DTO
	*@Params:@return
	*@Return:List<String>
	*@author:王楠
	*@Date:2014-7-16下午06:05:42
	*/
	private List<String> findExportList(List<String> list,
			SalesSummaryDTO salesSummaryDTO) {
			list.add(Utils.getString(salesSummaryDTO.getMerId()));
			list.add(Utils.getString(salesSummaryDTO.getMerName()));
			list.add(Utils.getString(salesSummaryDTO.getSalesQty()));
			list.add(NumberUtil.numberFormat("#,##0.0000", salesSummaryDTO.getSalesAmt()));
			list.add(Utils.getString(salesSummaryDTO.getLastTime()));
		    return list;
	}

}
