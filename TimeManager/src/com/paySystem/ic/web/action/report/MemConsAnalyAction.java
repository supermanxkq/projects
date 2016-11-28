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

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.service.report.MemConsAnalyService;
import com.paySystem.ic.service.report.MerConsTotalService;
import com.paySystem.ic.utils.ChartDesigns;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.MemConsAnalyDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MemConsAnalyAction
 * @Description:会员消费分析action
 * @date: 2014-4-12下午12:42:59
 * @author: 张亚运
 * @version: V1.0
 */
@Controller("/report/memconsanalyaction")
@Scope("prototype")
public class MemConsAnalyAction extends BaseAction {

	private static final long serialVersionUID = 485211547955087560L;

	@Resource
	MemConsAnalyService memConsAnalyService;
	@Resource
	MerConsTotalService merConsTotalService;

	private MemConsAnalyDTO memcaDto = new MemConsAnalyDTO();

	public MemConsAnalyDTO getMemcaDto() {
		return memcaDto;
	}

	public void setMemcaDto(MemConsAnalyDTO memcaDto) {
		this.memcaDto = memcaDto;
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
			memcaDto.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",DateTimeTool.nDaysAgo(7, new Date())));
			memcaDto.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",new Date()));
			this.getRequest().setAttribute("sign", OptionsValue.ACCKIND_SIGN);// 排名方式
			return "list";
	}

	/**
	 *@Title:jsonPageList
	 *@Description:显示页面数据交互方法
	 *@param:@return
	 *@return:String
	 *@author:张亚运
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照会员编号排序
			orderBy.put("memId", "desc");
		}
		List<MemConsAnalyDTO> mcaList = null;
		mcaList = memConsAnalyService.queryAll((memcaDto.getPage() - 1)
				* pageNum, pageNum, memcaDto, orderBy);
		UserSession us = Utils.getUserSession();
		MemConsAnalyDTO mcaDTO = null;
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < mcaList.size(); i++) {
			/** 向页面赋值 */
			mcaDTO = mcaList.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(mcaDTO.getMemId()));
			strings.add(Utils.getString(mcaDTO.getMemName()));
			strings.add("现金");
			if(us.getUserLevel()==0&&memcaDto.getCountType()==0){
				strings.add(Utils.getString("平台消费"));
				strings.add(Utils.getString(us.getOrganName()));
			}
			if(us.getUserLevel()==1&&memcaDto.getCountType()==0||memcaDto.getCountType()==1){
				strings.add(Utils.getString("机构消费"));
				if(us.getUserLevel()==1){
					strings.add(Utils.getString(us.getOrganName()));
				}
				else{
					strings.add(Utils.getString(mcaDTO.getOrgMerName()));
				}				
			}
			if(us.getUserLevel()==2&&memcaDto.getCountType()==0||memcaDto.getCountType()==2){
				strings.add(Utils.getString("商户消费"));
				if(us.getUserLevel()==2){
					strings.add(Utils.getString(us.getMerName()));
				}
				else{
					strings.add(Utils.getString(mcaDTO.getOrgMerName()));
				}				
			}
			strings.add(NumberUtil.numberFormat("", mcaDTO.getConsAmt()));
			strings.add(String.valueOf(mcaDTO.getConsCount()));
			lists.add(strings);
		}
		PageView pageView = new PageView(memcaDto.getPage(), mcaList.size());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:demoMap
	 *@Description:点击消费分析图表方法
	 *@param:@return
	 *@return:String
	 *@author:张亚运
	 *@throws IOException
	 *@thorws:
	 */
	public String demoMap() throws IOException {

		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照会员编号排序
			orderBy.put("memId", "desc");
		}
		List<MemConsAnalyDTO> mcaList = null;
		try {
			mcaList = memConsAnalyService.queryAll((memcaDto.getPage() - 1)
					* pageNum, pageNum, memcaDto, orderBy);
		} catch (Exception e) {
			e.getMessage();
		}
		MemConsAnalyDTO mcaDTO = null;
		List<Map<Object, Object>> mapList = new ArrayList<Map<Object, Object>>();

		for (int j = 0; j < mcaList.size(); j++) {
			/** 根据数据绘制统计图 */
			mcaDTO = mcaList.get(j);
			Map<Object, Object> demoMap = new HashMap<Object, Object>();
			if(memcaDto.getRankSign()==0){			
				if(memcaDto.getCountType()==0){
					demoMap.put("x", mcaDTO.getMemName());
					demoMap.put("y", mcaDTO.getConsAmt());
				}
				else{
					demoMap.put("x", mcaDTO.getMemName()+"("+mcaDTO.getOrgMerName()+")");
					demoMap.put("y", mcaDTO.getConsAmt());
				}
			}
			else{
				if(memcaDto.getCountType()==0){
					demoMap.put("x", mcaDTO.getMemName());
					demoMap.put("y", mcaDTO.getConsCount());
				}
				else{
					demoMap.put("x", mcaDTO.getMemName()+"("+mcaDTO.getOrgMerName()+")");
					demoMap.put("y", mcaDTO.getConsCount());
				}
			}
			mapList.add(demoMap);
		}

		ChartDesigns chartDesigns = new ChartDesigns("会员消费分析图", memcaDto
				.getBeginDate()
				+ "  至    " + memcaDto.getEndDate(), "消费次数", 1000, 600, mapList);
		//chartDesigns.setBenchmark(true, "限制数", "200");
		String xmlString = chartDesigns.drawColumn3D();
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