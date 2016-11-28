package com.paySystem.ic.web.interceptor;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.service.record.OperatorsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 模块权限控制
 * 
 * @version 2011-9-18 下午09:24:19
 */
public class PermissionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 783164607193227719L;
	public static Logger log = Logger.getLogger(PermissionInterceptor.class);
	
	@Resource OperatorsService operatorsService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ac = invocation.getInvocationContext();
		ServletContext servletContext = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		String actionName = ac.getName();
		
		boolean havePermission = false;

		if (wc == null) {
			ac.getValueStack().set("interceptError","ApplicationContext could not be found...");
			return "intercepthtml";
		} else {
			UserSession us = (UserSession) ac.getSession().get(Globals.USER_SESSION);
			Module module = us.getModulesMap().get(actionName);
			if (module != null)
				havePermission = true;
			if (havePermission) {
				log.info("【用户操作】 操作模块：【"+module.getName()+"】 操作员："+us.getUserName()+" 操作时间："+DateTimeTool.dateFormat("", new Date())+" IP："+Utils.getIpAddr());
				//operatorsService.saveOperatorLog(module.getName(), us.getUserName());
				return invocation.invoke();
			} else {
				return "intercepthtml";
			}
		}
	}
}