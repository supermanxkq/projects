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
import com.paySystem.ic.service.report.MerConsTotalService;
import com.paySystem.ic.service.report.MessTotalService;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.MessConsTotalDTO;
import com.paySystem.ic.web.dto.report.TermConsTotalDTO;

/**
 * @ClassName:MessConsTotalAction
 * @Description:短信费用汇总Action
 * @date: 2014-11-13上午14:08:30
 * @author: 张国法
 * @version: V1.0
 */
@Controller("/report/messconstotal")
@Scope("prototype")
public class MessConsTotalAction extends BaseAction {

	private static final long serialVersionUID = 3273874228516874532L;

	@Resource
	MessTotalService messTotalService;

	private MessConsTotalDTO messConsTotalDTO = new MessConsTotalDTO();

	public MessTotalService getMessTotalService() {
		return messTotalService;
	}

	public void setMessTotalService(MessTotalService messTotalService) {
		this.messTotalService = messTotalService;
	}

	public MessConsTotalDTO getMessConsTotalDTO() {
		return messConsTotalDTO;
	}

	public void setMessConsTotalDTO(MessConsTotalDTO messConsTotalDTO) {
		this.messConsTotalDTO = messConsTotalDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 *@Title:list
	 *@Description:显示列表页面
	 *@param:@return
	 *@return:String
	 *@author:张国法
	 *@thorws:
	 */
	public String list() {

		// this.getRequest().setAttribute("flag",OptionsValue.NEWTRADESTYPE
		// );//结算状态
		messConsTotalDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				DateTimeTool.nDaysAgo(7, new Date())));
		messConsTotalDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		return "list";
	}

	/**
	 *@Title:jsonPageList
	 *@Description:异步获取列表数据
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author:张国法
	 *@thorws:
	 */

	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照会员编号排序
			orderBy.put("memId", "desc");
		}
		List<MessConsTotalDTO> messConsList = messTotalService.queryAll(
				(messConsTotalDTO.getPage() - 1) * pageNum, pageNum,
				messConsTotalDTO, orderBy);

		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < messConsList.size(); i++) {
			// 向页面赋值
			MessConsTotalDTO messDTO = messConsList.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(messDTO.getMerId()));
			strings.add(Utils.getString(messDTO.getMessFee()));
			// strings.add(Utils.getOptionsIntegerName(OptionsValue.NEWTRADESTYPE,messDTO.getMessType()));
			strings.add(Utils.getString(messDTO.getMessPeriod()));
			strings.add(Utils.getString(messDTO.getCreateTime()));
			
			// strings.add(String.valueOf(messDTO.getTradesNum()));
			lists.add(strings);
		}

		PageView pageView = new PageView(messConsTotalDTO.getPage(),
				messConsList.size());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 *@Title:export
	 *@Description:导出Excel表Action
	 *@param:@return
	 *@return:String
	 *@author:张国法
	 *@thorws:
	 */
	public String export() {
		String title = "短信费用汇总表";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();

		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("merId", "desc");
		}
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("商户编号");
			headers.add("总费用");
			headers.add("总条数");
			headers.add("创建时间");


			List<MessConsTotalDTO> mdtoList = messTotalService
					.queryMessConsTotalReport(messConsTotalDTO, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			for (int i = 0; i < mdtoList.size(); i++) {

				MessConsTotalDTO mdto = mdtoList.get(i);

				List<String> slist = new ArrayList<String>();
				slist = retTradesViews(slist, mdto);
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
	 *@author:张国法
	 *@thorws:
	 */
	private List<String> retTradesViews(List<String> mdtoList,
			MessConsTotalDTO mctdto) {
		mdtoList.add(Utils.getString(mctdto.getMerId()));
		mdtoList.add(Utils.getString(mctdto.getMessFee()));
		mdtoList.add(Utils.getString(mctdto.getMessPeriod()));
		mdtoList.add(Utils.getString(mctdto.getCreateTime()));
		return mdtoList;
	}
}
