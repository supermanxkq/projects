package com.paySystem.ic.web.action.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.service.report.MerConsumeAnalyzeService;
import com.paySystem.ic.utils.ChartDesigns;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.MerConsumeAnalyzeDTO;

@Controller("/report/merconsumeanalyze")
@Scope("prototype")
public class MerConsumeAnalyzeAction extends BaseAction {

	@Resource
	MerConsumeAnalyzeService merConsumeAnalyzeService;

	private static final long serialVersionUID = 1L;
	
	private MerConsumeAnalyzeDTO merConsumeAnalyzeDTO = new MerConsumeAnalyzeDTO();

	/***
	 * 显示列表页面
	 * 
	 * @Title:list
	 *@param:@return
	 *@return:String
	 *@author:解文侠
	 *@thorws:
	 */
	public String list() {
				
		this.getRequest().setAttribute("sign", OptionsValue.ACCKIND_SIGN);// 排名方式
		this.getRequest().setAttribute("statuss",
				OptionsValue.MERCONSUME_STATUS);// 所按条件
		merConsumeAnalyzeDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		merConsumeAnalyzeDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		return "list";
	}

	/***
	 * 异步获取列表页面
	 * 
	 * @Title:jsonPageList
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author:解文侠
	 *@thorws:
	 */
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照商户编号排序
			orderBy.put("merId", "desc");
		}
		ListInfoDTO listInfoDTO = null;
		listInfoDTO = queryQuarter();		
		return Utils.printInfo(listInfoDTO);
	}

	/***
	 * 查询
	 * 
	 * @Title:queryQuarter
	 *@param:@return
	 *@return:ListInfoDTO
	 *@author:解文侠
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public ListInfoDTO queryQuarter() {
		 LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
			// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照商户编号排序
			orderBy.put("merId", "desc");
		}
		List<MerConsumeAnalyzeDTO> merConsumeList = null;
		try {
			merConsumeList = merConsumeAnalyzeService.queryAll((merConsumeAnalyzeDTO.getPage()-1)*pageNum, pageNum, merConsumeAnalyzeDTO, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<List<String>> list = new ArrayList<List<String>>();
		for (int i = 0; i < merConsumeList.size(); i++) {
			List<String> s = new ArrayList<String>();
			MerConsumeAnalyzeDTO m = merConsumeList.get(i);
			s.add(String.valueOf(i + 1));
			s.add(m.getMerId());
			s.add(m.getMerRealName());
			s.add(m.getTradeAmt().toString());
			s.add(m.getTradesNum().toString());
			s.add(m.getStringDate().toString());
			if(merConsumeAnalyzeDTO.getStatus()==0){
				s.add(m.getYearly()+"季度");
			}
			if(merConsumeAnalyzeDTO.getStatus()==1){
				s.add(m.getYearly()+"月份");
			}
			if(merConsumeAnalyzeDTO.getStatus()==2){
				s.add(m.getYearly()+"日");
			}
			list.add(s);
		}
		PageView pageView = new PageView(merConsumeAnalyzeDTO.getPage(), merConsumeList
				.size());
		ListInfoDTO listInfoDTO = new ListInfoDTO(list, getPageHTML(pageView));
		return listInfoDTO;
	}

	/***
	 * 导出Excel表Action
	 * 
	 * @Title:export
	 *@param:@return
	 *@return:String
	 *@author:解文侠
	 *@thorws:
	 */
	public String export() {
		String title = "商户消费分析报表";
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
			headers.add("交易金额");
			headers.add("交易笔数");
			headers.add("对应时间");
			headers.add("所按时间（季度/月份/日）");

			List<MerConsumeAnalyzeDTO> consumeList = merConsumeAnalyzeService
					.queryMerConsumeAnalyzeReport(merConsumeAnalyzeDTO, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			for (int i = 0; i < consumeList.size(); i++) {

				MerConsumeAnalyzeDTO consume = consumeList.get(i);

				List<String> slist = new ArrayList<String>();
				slist = retConsumeViews(slist, consume);
				lists.add(slist);
			}
			str = util.createXls(headers, lists, title, this.getResponse());
			return str;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/***
	 * 封装String方法
	 * 
	 * @Title:retConsumeViews
	 *@param:@param consumeList
	 *@param:@param consume
	 *@param:@return
	 *@return:List<String>
	 *@author:解文侠
	 *@thorws:
	 */
	private List<String> retConsumeViews(List<String> consumeList,
			MerConsumeAnalyzeDTO consume) {
		consumeList.add(consume.getMerId());
		consumeList.add(consume.getMerRealName());
		consumeList.add(String.valueOf(consume.getTradeAmt()));
		consumeList.add(String.valueOf(consume.getTradesNum()));
		consumeList.add(consume.getStringDate());
		if(merConsumeAnalyzeDTO.getStatus()==0){
			consumeList.add(String.valueOf(consume.getYearly()+"季度"));
		}
		if(merConsumeAnalyzeDTO.getStatus()==1){
			consumeList.add(String.valueOf(consume.getYearly()+"月份"));
		}
		if(merConsumeAnalyzeDTO.getStatus()==2){
			consumeList.add(String.valueOf(consume.getYearly()+"日"));
		}
		return consumeList;
	}

	public MerConsumeAnalyzeDTO getMerConsumeAnalyzeDTO() {
		return merConsumeAnalyzeDTO;
	}

	public void setMerConsumeAnalyzeDTO(
			MerConsumeAnalyzeDTO merConsumeAnalyzeDTO) {
		this.merConsumeAnalyzeDTO = merConsumeAnalyzeDTO;
	}

	/** 显示图表信息 **/
	public String chart() throws IOException {

		 LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
			// 判断排序参数是否有值
			if (StringUtils.isNotBlank(this.getOrderProperty())
					&& StringUtils.isNotBlank(this.getOrderDirection())) {
				orderBy.put(this.getOrderProperty(), this.getOrderDirection());
			} else {// 如果页面没有要求排序方式，则设置按照商户编号排序
				orderBy.put("merId", "desc");
			}
			List<MerConsumeAnalyzeDTO> merConsumeList = null;
			try {
				merConsumeList = merConsumeAnalyzeService.queryAll((merConsumeAnalyzeDTO.getPage()-1)*pageNum, pageNum, merConsumeAnalyzeDTO, orderBy);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Map<Object, Object>> mapList = new ArrayList<Map<Object, Object>>();
			String MerName = "";
			for (int j = 0; j < merConsumeList.size(); j++) {
				// ** 根据数据绘制统计图 *//
				MerConsumeAnalyzeDTO merConsumeAnalyzeDto = merConsumeList.get(j);
				Map<Object, Object> demoMap = new HashMap<Object, Object>();
				if(merConsumeAnalyzeDTO.getStatus()==0){
					demoMap.put("x",merConsumeAnalyzeDto.getStringDate()+"第"+merConsumeAnalyzeDto.getYearly()+"季度");
				}
				if(merConsumeAnalyzeDTO.getStatus()==1){
					demoMap.put("x",merConsumeAnalyzeDto.getStringDate()+"月份");
				}
				if(merConsumeAnalyzeDTO.getStatus()==2){
					demoMap.put("x",merConsumeAnalyzeDto.getStringDate()+"日");
				}
				if(merConsumeAnalyzeDTO.getSign()==0){
					demoMap.put("y", merConsumeAnalyzeDto.getTradeAmt());
				}
				if(merConsumeAnalyzeDTO.getSign()==1){
					demoMap.put("y", merConsumeAnalyzeDto.getTradesNum());
				}
				MerName = merConsumeAnalyzeDto.getMerRealName();
				mapList.add(demoMap);
			}
			ChartDesigns chartDesigns = new ChartDesigns("商户消费分析图("+MerName+")",
					merConsumeAnalyzeDTO.getBeginDate() + " - "
							+ merConsumeAnalyzeDTO.getEndDate()+"(消费分析)", "交易金额", 800, 400,
					mapList);
			chartDesigns.setBenchmark(true, "限制数", "200");
			String xmlString = chartDesigns.drawColumn2D();
		this.getRequest().setAttribute("twoColumn", xmlString);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(xmlString);
		out.flush();
		out.close();
		return null;
	}
	
}
