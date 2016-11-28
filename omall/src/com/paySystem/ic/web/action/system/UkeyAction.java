package com.paySystem.ic.web.action.system;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.web.action.BaseAction;

/**
 * UKEY管理
 * @version 2011-9-8 下午02:08:10
 */
@Controller("/system/ukey")
@Scope("prototype")
public class UkeyAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;

	@Override
	public String execute(){	
		return INPUT;
	}
}
