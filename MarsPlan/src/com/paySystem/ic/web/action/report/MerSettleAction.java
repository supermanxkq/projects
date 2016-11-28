package com.paySystem.ic.web.action.report;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.MerSettTotal;
import com.paySystem.ic.service.report.MerSettTotalService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.report.MerSettTotalDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MerSettleAction
 * @Description:商户结算Action
 * @date: 2013-12-25下午06:36:34
 * @author: 谢洪飞
 * @version: V1.0
 */
@Controller("/report/mersettle")
@Scope("prototype")
public class MerSettleAction extends BaseAction {

	@Resource MerSettTotalService merSettTotalService;
	
	private MerSettTotalDTO merSettTotalDTO = new MerSettTotalDTO();
	
	public MerSettTotalDTO getMerSettTotalDTO() {
		return merSettTotalDTO;
	}
	public void setMerSettTotalDTO(MerSettTotalDTO merSettTotalDTO) {
		this.merSettTotalDTO = merSettTotalDTO;
	}
	private static final long serialVersionUID = 8787725508317591893L;
	
	/**
	 *@Title:list
	 *@Description:菜单[商户结算]请求方法
	 *@param:@return
	 *@return:String
	 *@author:谢洪飞
	 *@thorws:
	 */
	public String list(){
		UserSession us = Utils.getUserSession();
		this.getRequest().setAttribute("settFlag",OptionsValue.MERSETTTOTAL_FLAG );//交易类型
		switch (us.getUserLevel()) {
		case 0:
			return "list";
		case 1:
			return "list";
		case 2:
			return "intercepthtml";
		}
		return "list";
	}
	/**
	 *@Title:jsonPageList
	 *@Description:异步查询方法
	 *@param:@return
	 *@return:String
	 *@author:谢
	 * @throws Exception 
	 *@thorws:
	 */
	public String jsonPageList() throws Exception{
		
		/**
		 * 查询结果排序参数设定 
		 */
		LinkedHashMap<String,String> orderBy = new LinkedHashMap<String,String>();
		UserSession us = Utils.getUserSession();
		//判断排序参数是否有值
		if(StringUtils.isNotBlank(this.getOrderProperty())
				&&StringUtils.isNotBlank(this.getOrderDirection()))
		{orderBy.put(this.getOrderProperty(),this.getOrderProperty());}
		else{orderBy.put("id","desc");}
	
		QueryResult<MerSettTotal> terResult = merSettTotalService.queryMerSettTotal((merSettTotalDTO.getPage() - 1) * pageNum,pageNum,merSettTotalDTO,orderBy);
		List<MerSettTotal> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i = 0;i <list.size();i++){
			//向页面赋值
			MerSettTotal merSettTotal = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(merSettTotal.getMerchants().getMerId()));
			strings.add(Utils.getString(merSettTotal.getMerchants().getMerName()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.MERSETTTOTAL_FLAG,merSettTotal.getFlag()));
			strings.add(DateTimeTool.dateFormat("yyyy-MM-dd", merSettTotal.getSettTime()));
			strings.add(NumberUtil.numberFormat("#0.00##",merSettTotal.getRechAmt()));
			strings.add(NumberUtil.numberFormat("#0.00##",merSettTotal.getConsAmt()));
			strings.add(NumberUtil.numberFormat("#0.00##",merSettTotal.getConsCommis()));
			strings.add(NumberUtil.numberFormat("#0.00##",merSettTotal.getReturnAmt()));
			strings.add(NumberUtil.numberFormat("#0.00##",merSettTotal.getReturnCommis()));
			strings.add(NumberUtil.numberFormat("", merSettTotal.getSupportSettAmt()));
			String operation ="";
			if(us.getUserLevel()==0||us.getUserLevel()==1){
				if(Utils.checkPermission("sy-5301-01")){
					operation += "<a href=javascript:checkMerSett('"+merSettTotal.getId()+"'," +
							                                      "'"+Utils.getOptionsIntegerName(OptionsValue.MERSETTTOTAL_FLAG,merSettTotal.getFlag())+"'," +
							                                      		1+
							                                      		",'"+merSettTotal.getLastBalance()+"',"+
							                                      		"'"+merSettTotal.getSupportSettAmt()+"',"+
							                                      		"'"+merSettTotal.getThisTimeBalance()+"',"+
							                                      		"'"+merSettTotal.getActualSettAmt()+"'"
							                                      		+") title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
				}
				if(merSettTotal.getMerchants().getOrgans().getOrganId()==us.getOrganId()
						||us.getOrganId().equals(merSettTotal.getMerchants().getOrgans().getOrganId())){
					if(merSettTotal.getFlag()==0){
						if(Utils.checkPermission("sy-5301-02")){
							operation += "<a href=javascript:sureSett('"+merSettTotal.getId()+"'," +
									                                  "'"+Utils.getOptionsIntegerName(OptionsValue.MERSETTTOTAL_FLAG, merSettTotal.getFlag())+"'," +
									                                  2+
									                                  ",'"+merSettTotal.getLastBalance()+"',"+
									                                  "'"+merSettTotal.getSupportSettAmt()+"'"
									                                  +") title='确认结算'>"+Globals.IMG_AUDIT+"</a>&nbsp;";
						}
					}
				}
			}
			strings.add(operation);
			lists.add(strings);
		   }
		
		PageView pageView = new PageView(merSettTotalDTO.getPage(),terResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 *@Title:sureMerSett
	 *@Description:确认结算
	 *@param:@return
	 *@return:String
	 *@author: 谢
	 *@thorws:
	 */
	public String sureMerSett(){
		ReturnDTO dto = new ReturnDTO();
		dto = merSettTotalService.sureMerSett(merSettTotalDTO);
		MerSettTotalDTO merDto = (MerSettTotalDTO) dto.getObj();
		String msg = "";
		if(dto.getFlag()){
			//结算成功
			      msg = "结算完成!本次结算信息:"+
				         "\n   结算商户号: "+merDto.getMerId()+
				         "\n 结算商户名称: "+merDto.getMerName()+
				         "\n     结算状态: 已结算"+
				         "\n 本次结算金额: "+merDto.getSupportSettAmt()+
				         "\n     本次结余: "+merDto.getThisTimeBalance();
			dto.setMsg(msg);
		}
		else{
			//结算出错
			     msg = "系统错误提示：结算出错!" +
					      "\n  结算商户号: "+merDto.getMerId()+
					      "\n结算商户名称: "+merDto.getMerName()+
					      "\n    结算状态: 未结算";
			dto.setMsg(msg);
		}
		return Utils.printInfo(dto);
	}
	
	public String showReportView() throws Exception{
		
		ReturnDTO dto = new ReturnDTO();
		/**
		 * 查询结果排序参数设定 
		 */
		LinkedHashMap<String,String> orderBy = new LinkedHashMap<String,String>();
		UserSession us = Utils.getUserSession();
		//判断排序参数是否有值
		if(StringUtils.isNotBlank(this.getOrderProperty())
				&&StringUtils.isNotBlank(this.getOrderDirection()))
		{orderBy.put(this.getOrderProperty(),this.getOrderProperty());}
		else{orderBy.put("id","desc");}
	
		QueryResult<MerSettTotal> terResult = merSettTotalService.queryMerSettTotal((merSettTotalDTO.getPage() - 1) * pageNum,pageNum,merSettTotalDTO,orderBy);
		List<MerSettTotal> list = terResult.getResultlist();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		
		return null;
	}

}
