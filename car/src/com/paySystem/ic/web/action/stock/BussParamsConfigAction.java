package com.paySystem.ic.web.action.stock;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.stock.BussParamsConfigService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.BussParamsConfigDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**
 * @ProjectName:omall
 * @ClassName:BussParamsConfigAction
 * @Description:商城业务参数配置的Action
 * @date: 2014-10-13
 * @author: 王楠
 * @version: V1.0
 */
@Controller("/buss/serviceparams")
@Scope("prototype")
public class BussParamsConfigAction extends BaseAction implements Serializable{
	
	@Resource
	BussParamsConfigService bussParamsConfigService;
	@Resource
	FunctionsService functionsService;

	private static final long serialVersionUID = 7560430598230987009L;
	
	public BussParamsConfigDTO bussParamsConfigDTO=new BussParamsConfigDTO();
	
	public BussParamsConfigDTO getBussParamsConfigDTO() {
		return bussParamsConfigDTO;
	}

	public void setBussParamsConfigDTO(BussParamsConfigDTO bussParamsConfigDTO) {
		this.bussParamsConfigDTO = bussParamsConfigDTO;
	}


	/**
	*@Title:list
	*@Description:跳转到商城业务参数配置管理页面
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-10-13下午03:53:06
	*/
	public String list(){
		UserSession us=Utils.getUserSession();
		
		try {
			bussParamsConfigDTO = bussParamsConfigService.findList();
		} catch (Exception e) {
			e.getMessage();
		}
		if(us.getUserLevel()==0){
			if(bussParamsConfigDTO.getParamsId()==null&&bussParamsConfigDTO.getMgcId()==null){
				bussParamsConfigDTO.setOperMan(us.getRealName());
				bussParamsConfigDTO.setOperTime(bussParamsConfigService.getSysTime());			
				this.setMethod("addSave");	
			}else{
				//bussParamsConfigDTO.setOperMan();
				//bussParamsConfigDTO.setOperTime(bussParamsConfigService.getSysTime());	
				this.setMethod("editSave");	
			}
			return "input";
		}else{
			return "intercepthtml";
		}
  }
	
	/**
	*@Title:addSave
	*@Description:保存业务参数配置信息
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-10-14下午04:53:53
	*/
	public String addSave(){
		bussParamsConfigService.addSave(bussParamsConfigDTO);
		functionsService.saveFunction("业务参数配置", 1, "增加："+bussParamsConfigDTO.getParamsId());
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "buss/serviceparams!list");
		return SUCCESS;
	}
	
	/**
	*@Title:editSave
	*@Description:修改方法
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-10-15下午03:54:58
	*/
	public String editSave(){
		ReturnDTO dto=bussParamsConfigService.updateBussParamsConfig(bussParamsConfigDTO);
		if(dto.getFlag()){
			functionsService.saveFunction("业务参数配置", 2, "业务参数配置:"+bussParamsConfigDTO.getParamsId());
			this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
			this.getRequest().setAttribute("url","buss/serviceparams!list");
			return SUCCESS;
		}else{
		    this.getRequest().setAttribute("result",dto.getMsg());
		    this.getRequest().setAttribute("url","buss/serviceparams!list");
			return ERROR;
		}
	}
}


