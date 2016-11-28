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
import com.paySystem.ic.service.report.MerConsTotalService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.TermConsTotalDTO;

/**
 * @ClassName:MerConsTotalAction
 * @Description:商户消费汇总Action
 * @date: 2014-3-4上午09:08:30
 * @author: 张亚运
 * @version: V1.0
 */
@Controller("/report/merconstotal")
@Scope("prototype")
public class MerConsTotalAction extends BaseAction {

	private static final long serialVersionUID = 485211547955087560L;

	@Resource
	MerConsTotalService merConsTotalService;

	private TermConsTotalDTO termConsDto = new TermConsTotalDTO();

	public TermConsTotalDTO getTermConsDto() {
		return termConsDto;
	}

	public void setTermConsDto(TermConsTotalDTO termConsDto) {
		this.termConsDto = termConsDto;
	}

	/**
	 *@Title:list
	 *@Description:显示列表页面
	 *@param:@return
	 *@return:String
	 *@author:张亚运
	 *@thorws:
	 */
	public String list() {

		this.getRequest().setAttribute("flag",OptionsValue.NEWTRADESTYPE );//结算状态
		termConsDto.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",new Date()));
		termConsDto.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",new Date()));
		return "list";
	}

	/**
	 *@Title:jsonPageList
	 *@Description:异步获取列表数据
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author:张亚运
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
		List<TermConsTotalDTO> merConsList = merConsTotalService.queryAll(
				(termConsDto.getPage() - 1) * pageNum, pageNum, termConsDto,
				orderBy);

		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < merConsList.size(); i++) {
			// 向页面赋值
			TermConsTotalDTO merConsTotalDTO = merConsList.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(merConsTotalDTO.getMerId()));
			strings.add(Utils.getString(merConsTotalDTO.getMerName()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.NEWTRADESTYPE,
			merConsTotalDTO.getTradeType()));
			strings.add(NumberUtil.numberFormat("", merConsTotalDTO
					.getConsAmt()));
			strings.add(NumberUtil.numberFormat("", merConsTotalDTO
					.getConsCommis()));
			strings.add(String.valueOf(merConsTotalDTO.getTradesNum()));
			lists.add(strings);
		}

		PageView pageView = new PageView(termConsDto.getPage(), merConsList
				.size());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 *@Title:export
	 *@Description:导出Excel表Action
	 *@param:@return
	 *@return:String
	 *@author:张亚运
	 *@thorws:
	 */
	public String export() {
		String title = "商户消费汇总表";
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
			headers.add("商户名称");
			headers.add("交易类型");
			headers.add("交易金额");
			headers.add("总手续费");
			headers.add("交易笔数");

			List<TermConsTotalDTO> mdtoList = merConsTotalService
					.queryMerConsTotalReport(termConsDto, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			for (int i = 0; i < mdtoList.size(); i++) {

				TermConsTotalDTO mdto = mdtoList.get(i);

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
	 *@author:张亚运
	 *@thorws:
	 */
	private List<String> retTradesViews(List<String> mdtoList,
			TermConsTotalDTO mctdto) {
		mdtoList.add(Utils.getString(mctdto.getMerId()));
		mdtoList.add(Utils.getString(mctdto.getMerName()));
		mdtoList.add(Utils.getOptionsIntegerName(OptionsValue.NEWTRADESTYPE, mctdto.getTradeType()));
		mdtoList.add(NumberUtil.numberFormat("", mctdto.getConsAmt()));
		mdtoList.add(NumberUtil.numberFormat("", mctdto.getConsCommis()));
		mdtoList.add(String.valueOf(mctdto.getTradesNum()));
		return mdtoList;
	}

}
