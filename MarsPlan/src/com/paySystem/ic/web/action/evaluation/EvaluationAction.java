package com.paySystem.ic.web.action.evaluation;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.service.evaluation.EvaluationService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
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

	public String list() {
		/** 获取当前操作员信息*/
		UserSession us = Utils.getUserSession();
		/**
		 * 根据操作员不同级别进行界面跳转
		 * 
		 * us.getUserLevel 0 : 平台操作员 1 : 机构操作员 2 : 商户操作员 操作员为平台操作员，进行界面正常跳转；
		 * 操作员为机构或商户操作员，进行拦截提示其不拥有该权限；
		 */
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			return "intercepthtml";
		case 2:
			return "intercepthtml";
		}
		return "list";
	}

}
