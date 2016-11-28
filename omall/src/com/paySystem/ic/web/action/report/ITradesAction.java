package com.paySystem.ic.web.action.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.report.ITradesService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.ITradesDTO;

/**
 * @ProjectName:omallproject
 * @ClassName:ITradesAction
 * @Description:积分消费记录Action
 * @date: 2014-11-13
 * @author: 毛智东
 * @version: V1.0
 */
@Controller("/report/itrades")
@Scope("prototype")
public class ITradesAction extends BaseAction {

	/** 序列号 **/
	private static final long serialVersionUID = 2321577343747212822L;

	@Resource
	ITradesService iTradesService;

	public ITradesDTO iTradesDTO = new ITradesDTO();

	public ITradesDTO getiTradesDTO() {
		return iTradesDTO;
	}

	public void setiTradesDTO(ITradesDTO iTradesDTO) {
		this.iTradesDTO = iTradesDTO;
	}

	/**
	 * 
	 *@Title:list
	 *@Description:list方法
	 *@Params:@return
	 *@Return:String
	 *@author:毛智东
	 *@Date:2014-11-13下午06:21:00
	 */
	public String list() {

		/** 起始时间默认为前七天时间 **/
		iTradesDTO.setParamBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd",
				DateTimeTool.nDaysAgo(7, new Date())));
		iTradesDTO.setParamEndTime(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		return "list";
	}

	public String jsonPageList() throws Exception {

		/** 设置排序，默认交易时间降序 **/
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("tradesTime", "desc");
		}

		/** 分布查询数据 **/
		QueryResult<ITradesDTO> terResult = iTradesService.queryAll((iTradesDTO
				.getPage() - 1)
				* pageNum, pageNum, iTradesDTO, orderBy);

		/** 封装页面显示数据 **/
		List<ITradesDTO> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		lists = getListString(list);

		/** 设置分页信息 **/
		PageView<ITradesDTO> pageView = new PageView<ITradesDTO>(iTradesDTO
				.getPage(), terResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));

		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 *@Title:getListString
	 *@Description:向页面封装数据
	 *@Params:@param list
	 *@Params:@return
	 *@Return:List<List<String>>
	 *@author:毛智东
	 *@Date:2014-11-13下午06:39:46
	 */
	public List<List<String>> getListString(List<ITradesDTO> list) {
		List<List<String>> lists = new ArrayList<List<String>>();
		if (CollectionUtils.isEmpty(list)) {
			return lists;
		}
		List<String> strings = null;
		for (int i = 0; i < list.size(); i++) {
			ITradesDTO dto = list.get(i);

			/** 开始封装 **/
			strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(dto.getTradesId()));
			strings.add(Utils.getString(dto.getOmemId()));
			strings.add(Utils.getString(dto.getPayOrderId()));
			strings.add(Utils.getString(dto.getPayAccNo()));
			strings.add(Utils.getString(dto.getPayAccName()));
			strings.add(NumberUtil.numberFormat("#0.00##", dto.getPayAmt()));
			strings.add(Utils.getString(dto.getMerAccNo()));
			strings.add(Utils.getString(dto.getMerAccName()));
			strings.add(DateTimeTool.dateFormat("", dto.getTradesTime()));

			/**
			 * operation =
			 * "<a href='report/btrades!findById?btradesDTO.tradesId=" +
			 * dto.getTradesId() + "' title='查看'>" + Globals.IMG_VIEW +
			 * "</a>&nbsp;";
			 */

			lists.add(strings);
		}

		return lists;
	}

	/**
	 * 
	 *@Title:export
	 *@Description:导出Excel
	 *@Params:@return
	 *@Return:String
	 *@author:毛智东
	 *@Date:2014-11-13下午06:50:28
	 */
	public String export() {
		String title = "会员积分消费记录报表";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();

		/** 判断排序参数是否有值 **/
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("tradesTime", "desc");
		}
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("序号");
			headers.add("流水号");
			headers.add("会员号");
			headers.add("订单号");
			headers.add("支付积分账户");
			headers.add("支付账户名称");
			headers.add("消费积分");
			headers.add("商户积分账户");
			headers.add("商户账户名称");
			headers.add("交易时间");

			QueryResult<ITradesDTO> terResult = iTradesService.queryAll(
					(iTradesDTO.getPage() - 1) * pageNum, pageNum, iTradesDTO,
					orderBy);
			List<ITradesDTO> list = terResult.getResultlist();
			List<List<String>> lists = new ArrayList<List<String>>();
			int i = 1;
			for (ITradesDTO dto : list) {
				List<String> slist = new ArrayList<String>();
				slist = retConsumeViews(slist, dto, i++);
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
	 * 
	 *@Title:retConsumeViews
	 *@Description:封装导出数据
	 *@Params:@param list
	 *@Params:@param dto
	 *@Params:@return
	 *@Return:List<String>
	 *@author:毛智东
	 *@Date:2014-11-13下午06:50:45
	 */
	private List<String> retConsumeViews(List<String> list, ITradesDTO dto,
			int index) {
		list.add("" + index);
		list.add(Utils.getString(dto.getTradesId()));
		list.add(Utils.getString(dto.getOmemId()));
		list.add(Utils.getString(dto.getPayOrderId()));
		list.add(Utils.getString(dto.getPayAccNo()));
		list.add(Utils.getString(dto.getPayAccName()));
		list.add(NumberUtil.numberFormat("#0.00##", dto.getPayAmt()));
		list.add(Utils.getString(dto.getMerAccNo()));
		list.add(Utils.getString(dto.getMerAccName()));
		list.add(DateTimeTool.dateFormat("", dto.getTradesTime()));
		return list;
	}
}
