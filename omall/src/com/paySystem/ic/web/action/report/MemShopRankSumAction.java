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
import com.paySystem.ic.service.report.MemShopRankSumService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.MemShoppingSumDTO;
import com.paySystem.ic.web.dto.report.SalesSummaryDTO;
/**
 * @ProjectName:MCIU_DS
 * @ClassName:MemShopRankSumAction
 * @Description:会员购物量汇总的Action
 * @date: 2014-7-24
 * @author: 王楠
 * @version: V1.0
 */
@Controller("/report/memShopSum")
@Scope("prototype")
public class MemShopRankSumAction extends BaseAction{

	private static final long serialVersionUID = -8304863226747644240L;
	
	@Resource
	MemShopRankSumService memShopRankSumService;
	@Resource
	FunctionsService functionsService;
	
	private MemShoppingSumDTO memShoppingSumDTO=new MemShoppingSumDTO();
	
	public MemShoppingSumDTO getMemShoppingSumDTO() {
		return memShoppingSumDTO;
	}

	public void setMemShoppingSumDTO(MemShoppingSumDTO memShoppingSumDTO) {
		this.memShoppingSumDTO = memShoppingSumDTO;
	}

	/**
	*@Title:list
	*@Description:页面显示的list方法
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-22上午11:29:31
	*/
	public String list(){
		return "list";
	}

	/**
	*@Title:jsonPageList
	*@Description:异步获取列表数据 ps:查询所有汇总信息
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:王楠
	*@Date:2014-7-24下午02:50:31
	*/
	@SuppressWarnings("unchecked")
	public String jsonPageList(){
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("shoppingRank", "asc");
		}
		/** 获取queryResult中的值 */
        QueryResult<MemShoppingSumDTO> result =new QueryResult<MemShoppingSumDTO>();
		try {
			result = memShopRankSumService.queryAll(
					(memShoppingSumDTO.getPage()-1)*pageNum,
					      pageNum, memShoppingSumDTO, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/** 获取queryResult中的集合 */
        List<MemShoppingSumDTO> memShopList=result.getResultlist();
        /** 汇总信息字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取MemShoppingSumDTO集合 */
		for (int i=0;i<memShopList.size();i++) {
			/**向页面赋值*/
			MemShoppingSumDTO memShoppingSumDTO=memShopList.get(i);
			List<String> strings=new ArrayList<String>();
			/**添加到字符串集合中去*/
			strings.add(String.valueOf(i+1));
			strings = findExportList(strings,memShoppingSumDTO);
			lists.add(strings);
		}
		/**实例化PageView对象，获取分页的参数、总页数、总记录数*/
		PageView pageView=new PageView(memShoppingSumDTO.getPage(),result.getTotalrecord());
		/**实例化ListInfoDTO*/
		ListInfoDTO listInfoDTO=new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	*@Title:export
	*@Description:导出
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-24下午03:09:29
	*/
	public String export(){
		String title="会员购物量汇总排名";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("shoppingRank", "asc");
		}
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("会员编号");
			headers.add("会员名称");
			headers.add("购买量");
			headers.add("金额额");
			headers.add("购物量排名");
			/** 获取要导出的集合 */
			QueryResult<MemShoppingSumDTO> memShopList = 
				memShopRankSumService.exportMemShopXls(memShoppingSumDTO, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			List<MemShoppingSumDTO> dto = memShopList.getResultlist();
			for (int i = 0; i < dto.size(); i++) {
				MemShoppingSumDTO memShoppingSumDTO = dto.get(i);
				List<String> list = new ArrayList<String>();
				list = findExportList(list, memShoppingSumDTO);
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
	*@Params:@param memShoppingSumDTO 实体的DTO
	*@Params:@return
	*@Return:List<String>
	*@author:王楠
	*@Date:2014-7-24下午03:29:48
	*/
	private List<String> findExportList(List<String> list,
			MemShoppingSumDTO memShoppingSumDTO) {
        list.add(Utils.getString(memShoppingSumDTO.getMemId()));
        list.add(Utils.getString(memShoppingSumDTO.getMemName()));
        list.add(Utils.getString(memShoppingSumDTO.getShoppingQty()));
        list.add(NumberUtil.numberFormat("#,##0.0000", memShoppingSumDTO.getShoppingAmt()));
        list.add(Utils.getString(memShoppingSumDTO.getShoppingRank()));
		return list;
	}
}
