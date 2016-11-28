package com.paySystem.ic.web.action.member;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.service.member.MemConsPntsRuleService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemConsPntsRuleDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Controller("/member/memConsPntsRule")
@Scope("prototype")
public class MemConsPntsRuleAction extends BaseAction implements Serializable{

	private static final long serialVersionUID = -1498119692287020007L;
    @Resource
    MemConsPntsRuleService memConsPntsRuleService;
    @Resource
	FunctionsService functionsService;
    
    public MemConsPntsRuleDTO memConsPntsRuleDTO=new MemConsPntsRuleDTO();
    
	public MemConsPntsRuleDTO getMemConsPntsRuleDTO() {
		return memConsPntsRuleDTO;
	}

	public void setMemConsPntsRuleDTO(MemConsPntsRuleDTO memConsPntsRuleDTO) {
		this.memConsPntsRuleDTO = memConsPntsRuleDTO;
	}

	/**
	*@Title:list
	*@Description:页面跳转的list方法
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-10-17上午10:50:34
	*/
	public String list(){
		
		UserSession us=Utils.getUserSession();
		try {
			memConsPntsRuleDTO=memConsPntsRuleService.findList();
		} catch (Exception e) {
			e.getMessage();
		}
		if (us.getUserLevel()==0) {
			if(memConsPntsRuleDTO.getMcpId()==null){
				this.setMethod("addSave");
			}else{
				this.setMethod("editSave");
			}
		   return "input";
		} else {
		return "intercepthtml";
		}
	}
	
	/**
	*@Title:addSave
	*@Description:保存会员消费积分规则信息
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-10-17下午06:18:31
	*/
	public String addSave(){
		memConsPntsRuleService.addSave(memConsPntsRuleDTO);
		functionsService.saveFunction("会员消费积分规则", 1, "增加："+memConsPntsRuleDTO.getMcpId());
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "member/memConsPntsRule!list");
		return SUCCESS;
	}
	
	/**
	*@Title:editSave
	*@Description:修改方法
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-10-20下午04:10:58
	*/
	public String editSave(){
		ReturnDTO returnDTO =memConsPntsRuleService.updateMemConsPntsRule(memConsPntsRuleDTO);
		if(returnDTO.getFlag()){
			functionsService.saveFunction("会员消费积分规则", 2, "会员消费积分规则："
					+memConsPntsRuleDTO.getMcpId());
		   this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
		   this.getRequest().setAttribute("url","member/memConsPntsRule!list");
		   return SUCCESS;
		}else{
			this.getRequest().setAttribute("result", returnDTO.getMsg());
			this.getRequest().setAttribute("url","member/memConsPntsRule!list");
			return ERROR;
		}
	}
}
