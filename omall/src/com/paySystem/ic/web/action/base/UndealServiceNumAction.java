package com.paySystem.ic.web.action.base;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.service.base.UndealServiceNumService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.base.UndealServiceNumDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Controller("/service/undealService")
@Scope("prototype")
public class UndealServiceNumAction extends BaseAction {

	private static final long serialVersionUID = 1289056517302182926L;

	@Resource
	UndealServiceNumService undealServiceNumService;
	@Resource
	FunctionsService functionsService;

	public UndealServiceNumDTO undealServiceNumDTO = new UndealServiceNumDTO();

	public UndealServiceNumDTO getUndealServiceNumDTO() {
		return undealServiceNumDTO;
	}

	public void setUndealServiceNumDTO(UndealServiceNumDTO undealServiceNumDTO) {
		this.undealServiceNumDTO = undealServiceNumDTO;
	}

	/**
	 *@Title:list
	 *@Description:页面跳转的list方法
	 *@Params:@return
	 *@Return:String
	 *@author:王楠
	 * @throws Exception
	 *@Date:2014-10-22下午02:39:00
	 */
	public String list() throws Exception {
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel()==2){
		try {
			undealServiceNumDTO = undealServiceNumService.findList(us
					.getMerId());
		} catch (Exception e) {
		 }

			return "list";
	}
        return "intercepthtml";
    }
	
}
