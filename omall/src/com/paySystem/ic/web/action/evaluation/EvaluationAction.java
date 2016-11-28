package com.paySystem.ic.web.action.evaluation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.evaluation.EvaluationService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.evaluation.CriticalContextDTO;
import com.paySystem.ic.web.dto.evaluation.MerCreditRecordDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**  
 * @Title: EvaluationAction.java
 * @Package: com.paySystem.ic.web.action.evaluation
 * @Description: 评价管理Action
 * @Author: yanwuyang 
 * @Date: 2014-10-9 下午11:50:14
 * @Version: V1.0  
 */
@Controller("/evaluation/evaluation")
@Scope("prototype")
public class EvaluationAction extends BaseAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8350221423037461974L;
	
	@Resource
	private EvaluationService evaluationService;
	
	@Resource
	FunctionsService functionsService;
	
	/** 评价记录DTO*/
	private MerCreditRecordDTO creditRecordDTO = new MerCreditRecordDTO();
	
	/** 评论DTO*/
	private CriticalContextDTO contextDTO;
	
	public MerCreditRecordDTO getCreditRecordDTO() {
		return creditRecordDTO;
	}

	public void setCreditRecordDTO(MerCreditRecordDTO creditRecordDTO) {
		this.creditRecordDTO = creditRecordDTO;
	}

	public CriticalContextDTO getContextDTO() {
		return contextDTO;
	}

	public void setContextDTO(CriticalContextDTO contextDTO) {
		this.contextDTO = contextDTO;
	}

	/**
	 * 
	 *@Title:list
	 *@Description:打开评价管理页面
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-11-22下午04:15:31
	 */
	public String list() {
		/** 获取当前操作员信息*/
		UserSession us = Utils.getUserSession();
		
		/**获取商户信息*/
		String userName= us.getUserName();
		List list= evaluationService.getUserByName(userName);
		if(list.size()!=0){
			Object[] user= (Object[])list.get(0);
			this.getRequest().setAttribute("userName", us.getRealName());
			this.getRequest().setAttribute("qq", user[0]==null?"":user[0]);
			this.getRequest().setAttribute("NetInFo", user[1]==null?"":user[1]);
			this.getRequest().setAttribute("Address", user[2]==null?"":user[2]);
			this.getRequest().setAttribute("OcaInfo", "");
		}
		/**
		 * 根据操作员不同级别进行界面跳转
		 * 
		 * us.getUserLevel 0 : 平台操作员 1 : 机构操作员 2 : 商户操作员 操作员为平台操作员，进行界面正常跳转；
		 * 操作员为机构或商户操作员，进行拦截提示其不拥有该权限；
		 */
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 2:
			break;
		case 1:
			return "intercepthtml";
		}
		return "list";
	}
	
	
	/**
	 * 
	 *@Title:getGriTotal
	 *@Description:获取站内统计信息
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-10-19下午06:14:58
	 */
	public String getGriTotal() throws Exception{
		UserSession us = Utils.getUserSession();
		String merId = us.getMerId();
		Calendar cal =Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		/**获取最近一周*/
		String beginDate =  df.format(cal.getTime());
		cal.set(Calendar.DATE,cal.get(Calendar.DATE)-7);
		String endDate =  df.format(cal.getTime());
		List list1 = evaluationService.queryGriTotal(merId,endDate,beginDate);
		/**获取最近一月*/
		cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-1);
		endDate =  df.format(cal.getTime());
		List list2 = evaluationService.queryGriTotal(merId,endDate,beginDate);
		
		/**获取最近六个月*/
		cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-6);
		endDate =  df.format(cal.getTime());
		List list3 = evaluationService.queryGriTotal(merId,endDate,beginDate);
		
		/**获取六个月前*/
		cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-6);
		endDate =  df.format(cal.getTime());
		List list4 = evaluationService.queryGriTotal(merId,endDate,null);
		StringBuffer sb = new StringBuffer();
		
		Object[] o1 = (Object[])list1.get(0);
		Object[] o2 = (Object[])list2.get(0);
		Object[] o3 = (Object[])list3.get(0);
		Object[] o4 = (Object[])list4.get(0);
		int o10 = o1[0]==null?0:Integer.parseInt(o1[0]+"");
		int o20 = o2[0]==null?0:Integer.parseInt(o2[0]+"");
		int o30 = o3[0]==null?0:Integer.parseInt(o3[0]+"");
		int o40 = o4[0]==null?0:Integer.parseInt(o4[0]+"");
		
		sb.append("<tr>");
		sb.append("<th>好评</th>");
		sb.append("<td>"+o10+"</td>");
		sb.append("<td>"+o20+"</td>");
		sb.append("<td>"+o30+"</td>");
		sb.append("<td>"+o40+"</td>");
		sb.append("<td>"+(o10+o20+o30+o40)+"</td>");
		sb.append("</tr>");
		
		int o11 = o1[1]==null?0:Integer.parseInt(o1[1]+"");
		int o21 = o2[1]==null?0:Integer.parseInt(o2[1]+"");
		int o31 = o3[1]==null?0:Integer.parseInt(o3[1]+"");
		int o41 = o4[1]==null?0:Integer.parseInt(o4[1]+"");
		
		sb.append("<tr>");
		sb.append("<th>中评</th>");
		sb.append("<td>"+o11+"</td>");
		sb.append("<td>"+o21+"</td>");
		sb.append("<td>"+o31+"</td>");
		sb.append("<td>"+o41+"</td>");
		sb.append("<td>"+(o11+o21+o31+o41)+"</td>");
		sb.append("</tr>");
		
		int o12 = o1[2]==null?0:Integer.parseInt(o1[2]+"");
		int o22 = o2[2]==null?0:Integer.parseInt(o2[2]+"");
		int o32 = o3[2]==null?0:Integer.parseInt(o3[2]+"");
		int o42 = o4[2]==null?0:Integer.parseInt(o4[2]+"");
		
		sb.append("<tr>");
		sb.append("<th>差评</th>");
		sb.append("<td>"+o12+"</td>");
		sb.append("<td>"+o22+"</td>");
		sb.append("<td>"+o32+"</td>");
		sb.append("<td>"+o42+"</td>");
		sb.append("<td>"+(o12+o22+o32+o42)+"</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<th>总计</th>");
		sb.append("<td>"+(o10+o11+o12)+"</td>");
		sb.append("<td>"+(o20+o21+o22)+"</td>");
		sb.append("<td>"+(o30+o31+o32)+"</td>");
		sb.append("<td>"+(o40+o41+o42)+"</td>");
		sb.append("<td>"+(o10+o11+o12+o20+o21+o22+o30+o31+o32+o40+o41+o42)+"</td>");
		sb.append("</tr>");
		
		
		HashMap map = new HashMap();
		map.put("json1", sb.toString());
		map.put("json2", list3);
		return Utils.printInfo(map);
	}
	
	/**
	 * 
	 *@Title:queryMerCreditRecord
	 *@Description:查询评价记录
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:32:17
	 */
	public String queryMerCreditRecord()throws Exception{
		UserSession us = Utils.getUserSession();
		String merId = us.getMerId();
		String type = this.getRequest().getParameter("type");
		if(type.equals("1")){
			merId = us.getUserName();
		}
		String appType = this.getRequest().getParameter("appType");
		String griType = this.getRequest().getParameter("griType");
		Map map = new HashMap();
		try{
		QueryResult<MerCreditRecordDTO> qr = evaluationService.queryMerCreditRecord(merId, Integer.parseInt(type),appType,griType,(creditRecordDTO.getPage()-1)*pageNum,pageNum);
		List<MerCreditRecordDTO>  merCreditRecordDTOs= qr.getResultlist();
		PageView pageView = new PageView(creditRecordDTO.getPage(),qr.getTotalrecord());
		
		map.put("list", merCreditRecordDTOs);
		map.put("pageHtml", getPageHTML(pageView,"getMerCreditRecord_"+type));
		}catch(Exception e){
			e.printStackTrace();
		}
		/**向前台输出查询数据结果集字符串*/
		return Utils.printInfo(map);
	}

	
	/**
	 * 
	 *@Title:addUI
	 *@Description：打开去评论界面
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-21下午3:24:47
	 */
	public String addUI() throws Exception {
		String orderId = this.getRequest().getParameter("orderId");
		List list=evaluationService.queryOrderByOrderId(orderId);
		Object[] o = (Object[])list.get(0);
		this.getRequest().setAttribute("goodsId", o[0]);
		this.getRequest().setAttribute("goodsName", o[1]);
		this.getRequest().setAttribute("goodsLittPho", o[2]);
		creditRecordDTO = new MerCreditRecordDTO();
		creditRecordDTO.setOrderId(orderId);
		creditRecordDTO.setGoodsMatch(0);
		creditRecordDTO.setServiceAtt(0);
		creditRecordDTO.setSendSpead(0);
		creditRecordDTO.setAppType(0);
		this.setMethod("addSave");
		this.getRequest().setAttribute("appTypes", OptionsValue.APPTYPE);
		return INPUT;
	}
	
	/**
	 * 
	 *@Title:addSave
	 *@Description:保存评价内容
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-8-24上午10:57:38
	 */
	public String addSave(){
		UserSession us = Utils.getUserSession();
		/**获取商户信息*/
		String userName= us.getUserName();
		List list=evaluationService.queryOrderByOrderId(creditRecordDTO.getOrderId());
		Object[] o = (Object[])list.get(0);
		Integer goodsId = 0;
		if(list.size()!=0){
			goodsId = Integer.parseInt(o[0]+"");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		creditRecordDTO.setCreateTIme(df.format(new Date()));
		creditRecordDTO.setUpdateTIme(df.format(new Date()));
		creditRecordDTO.setGoodsId(goodsId);
		creditRecordDTO.setMerId(us.getMerId());
		creditRecordDTO.setMemId(us.getUserName());
		creditRecordDTO.setFlag(2);
		creditRecordDTO.setSendSpead(0);
		creditRecordDTO.setServiceAtt(0);
		creditRecordDTO.setGoodsMatch(0);
		
		if(creditRecordDTO.getAppType()==0){
			creditRecordDTO.setGoodsapprise(1);
		}else if(creditRecordDTO.getAppType()==1){
			creditRecordDTO.setGoodsapprise(0);
		}else{
			creditRecordDTO.setGoodsapprise(-1);
		}
		creditRecordDTO.setMcrType(0);
		Integer mcrId = evaluationService.saveMerCreditRecord(creditRecordDTO);
		contextDTO.setMcrid(mcrId);
		contextDTO.setMemId(us.getUserName());
		contextDTO.setMemType(us.getUserLevel());
		contextDTO.setGriType(1);
		if(contextDTO.getContext().equals("")){
			contextDTO.setGriType(2);
			if(creditRecordDTO.getAppType()==0){
				contextDTO.setContext("好评");
			}else if(creditRecordDTO.getAppType()==1){
				contextDTO.setContext("中评");
			}else{
				contextDTO.setContext("差评");
			}
		}
		evaluationService.saveCriticalContext(contextDTO);
		
		evaluationService.updateOrder(creditRecordDTO.getOrderId());
		
		/** 2.保存操作日志信息*/
		functionsService.saveFunction("评价管理", 1, "保存评价管理");
		/** 保存成功提示内容*/
		this.getRequest().setAttribute("appTypes", OptionsValue.APPTYPE);
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		return INPUT;
	}
	/**
	 * 
	 *@Title:saveReply
	 *@Description:保存回复内容
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:33:03
	 */
	public String saveReply() throws Exception{
		UserSession us = Utils.getUserSession();
		/**获取商户信息*/
		String userName= us.getUserName();
		contextDTO.setMemId(userName);
		contextDTO.setMemType(us.getUserLevel());
		contextDTO.setGriType(1);
		evaluationService.saveCriticalContext(contextDTO);
		functionsService.saveFunction("评价管理", 1, "评价回复");
		ReturnDTO data = new ReturnDTO();
		data.setFlag(true);
		return Utils.printInfo(data);
	}
}
