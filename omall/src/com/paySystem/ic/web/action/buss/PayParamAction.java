package com.paySystem.ic.web.action.buss;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.PaySerParam;
import com.paySystem.ic.service.buss.PaySerParamService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.PaySerParamDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**
 * @ClassName:PayParamAction
 * @Description:支付接口配置Action
 * @date: 2014-7-9下午04:42:50
 * @author: 谢洪飞
 * @version: V1.0
 */
@Controller("/buss/payparam")
@Scope("prototype")
public class PayParamAction extends BaseAction {

	private static final long serialVersionUID = 5060679856253859750L;

	@Resource
	PaySerParamService payParamService;
	@Resource 
	FunctionsService functionsService;
	
	private PaySerParamDTO payParamDTO = new PaySerParamDTO();
	
	public PaySerParamDTO getPayParamDTO() {
		return payParamDTO;
	}

	public void setPayParamDTO(PaySerParamDTO payParamDTO) {
		this.payParamDTO = payParamDTO;
	}

	public String list () {
		
		UserSession us = Utils.getUserSession();
		
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			return "intercepthtml";
		}
		
		return "list";
	}
	
	/**
	 *  列表查询
	 *@Title:jsonPageList
	 *@Description: 根据列表界面条件对数据进行分页查询
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public String jsonPageList() throws Exception{
		 UserSession us = Utils.getUserSession();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		
		if (StringUtils.isNotBlank(this.getOrderProperty())
			&& StringUtils.isNotBlank(this.getOrderDirection())) {
			
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		   
		  } else { orderby.put("createTime", "desc"); }
		
		QueryResult<PaySerParamDTO> dtoResult =
			payParamService.queryPayParamByCond((payParamDTO.getPage()-1)*pageNum, pageNum, payParamDTO, orderby);
		
		List<PaySerParamDTO> payList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		
		for(int i=0 ; i<payList.size() ; i++){
			
			List<String> strings = new ArrayList<String>();
			PaySerParamDTO payDto = payList.get(i);
			strings.add(String.valueOf(i+1));
			strings.add(payDto.getPsName());
			strings.add(payDto.getPayOrgName());
			strings.add(payDto.getIsEnable()==0?"停用":"启用");
			strings.add( NumberUtil.numberFormat("#0.00##", payDto.getFeeRate()));
			strings.add(payDto.getCodSign()==0?"不支持":"支持");
			strings.add(payDto.getPayOnlineSign()==0?"不支持":"支持");
			
			String operation = "";
				if (Utils.checkPermission("sy-6301-02")) {
					operation += "<a href=buss/payparam!checkUI?payParamDTO.psId="
							+ payDto.getPsId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}
			if (payDto.getIsEnable() != null && payDto.getIsEnable()!= 0) {
				if (Utils.checkPermission("sy-6301-03")) {
					operation += "<a href=buss/payparam!editUI?payParamDTO.psId="
							+ payDto.getPsId()
							+ " title='修改'>"
							+ Globals.IMG_EDIT + "</a>&nbsp;";
				}
				if (Utils.checkPermission("sy-6301-04")) {
					operation += "<a href=javascript:deleteData('buss/payparam!delete','"
							+ payDto.getPsId()
							+ "') title='删除'>"
							+ Globals.IMG_DELETE + "</a>&nbsp;";
				}
			} 
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(payParamDTO.getPage(), dtoResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 * 新增支付参数界面跳转
	 *@Title:addUI
	 *@Description:TODO
	 *@param:@return
	 *@return:String
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public String addUI(){
		
		this.setMethod("addSave");
		this.getRequest().setAttribute("payOrgans", OptionsValue.PayOrgans);
		
		payParamDTO.setCodSign(0);
		payParamDTO.setPayOnlineSign(1);
		
		return INPUT;
	}
	
	
	/**
	 * 新增保存
	 *@Title:addSave
	 *@Description:保存支付参数新增内容
	 *@param:@return
	 *@return:String
	 *@author: 谢
	 *@Thorws:
	 */
	public String addSave(){
		
		payParamService.savePayParam(payParamDTO);
		
		functionsService.saveFunction("支付方式管理", 1, "增加新增支付方式：");
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","buss/payparam!list");
		
		return SUCCESS;
	}
	
	/**
	 *  修改界面跳转
	 *@Title:editUI
	 *@Description:跳转到支付参数配置修改界面
	 *@param:@return
	 *@return:String
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public String editUI(){
		
		this.setMethod("editSave");
		this.getRequest().setAttribute("payOrgans", OptionsValue.PayOrgans);
		
		//根据Id获取支付参数信息Dto对象
		payParamDTO = payParamService.findById(payParamDTO.getPsId());
		
		//payParamDTO.setCodSign(0);
		//payParamDTO.setPayOnlineSign(1);
		
		return INPUT;
	}
	
	/**
	 * 修改保存方法
	 *@Title:editSave
	 *@Description:保存支付参数信息修改内容
	 *@param:@return
	 *@return:String
	 *@author: 谢
	 *@Thorws:
	 */
	public String editSave(){
		
		payParamService.savePayParam(payParamDTO);
		functionsService.saveFunction("支付方式管理", 1, "增加新增支付方式：");
		
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","buss/payparam!list");
		
		return SUCCESS;
	}
	
	/**
	 *  删除支付参数信息
	 *@Title:delete
	 *@Description:删除信息
	 *@param:@return
	 *@return:String
	 *@author: 谢
	 *@Thorws:
	 */
	public String delete(){
		try{
		PaySerParamDTO payDto = payParamService.delPayParam(Integer.parseInt(this.id));
		functionsService.saveFunction("支付参数管理", 3, "删除支付参数："
				+ payDto.getPsId());
		this.ajaxResult = "ajaxsuccess";
	} catch (Exception e) {
		e.printStackTrace();
		this.ajaxResult = "ajaxfailure";
		this.msgResult = e.getMessage();
	}
		return this.ajaxResult;
	}
	
	public String checkUI(){
		
		this.setMethod("checkUI");
		this.getRequest().setAttribute("payOrgans", OptionsValue.PayOrgans);
		payParamDTO = payParamService.findById(payParamDTO.getPsId());
		
		return INPUT;
	}
	
	
	/**
	 *@Title:checkSameName
	 *@Description:检查支付名称是否已存在
	 *@param:@return
	 *@return:String 返回是否已存在状态
	 *@author: 谢
	 *@Thorws:
	 */
	public String checkSameName(){
		
		ReturnDTO data = new ReturnDTO();
		
		
		boolean flag =payParamService.checkSameName(payParamDTO.getPsName());
		data.setFlag(flag);
		//PaySerParam pay = payParamService.getParam(payParamDTO.getPsName());
		//data.setFlag(false);
		
		return Utils.printInfo(data);
	}
}
