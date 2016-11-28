package com.paySystem.ic.web.action.mail;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.web.action.BaseAction;

@Controller("/mail/timerMail")
@Scope("prototype")
public class TimerMailAction extends BaseAction{
	public String list() {
		// UserSession us = Utils.getUserSession();
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUS);
		return "list";
	}
}
