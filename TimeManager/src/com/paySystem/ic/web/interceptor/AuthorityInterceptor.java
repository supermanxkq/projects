package com.paySystem.ic.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.web.dto.system.UserSession;

public class AuthorityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -482648861385188390L;

	private String tourl;

	public String getTourl() {
		return tourl;
	}

	public void setTourl(String tourl) {
		this.tourl = tourl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/** 取得请求相关的ActionContext实例 */
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		UserSession usersession = (UserSession) session.get(Globals.USER_SESSION);

		/** 如果没有登陆，或者登陆超时，都返回重新登陆 */
		if (usersession != null) {
			return invocation.invoke();
		}
		return "outlinehtml";
	}

}
