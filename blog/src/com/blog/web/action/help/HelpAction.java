package com.blog.web.action.help;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.util.OrderUtils;
import com.blog.web.action.BaseAction;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/help/help")
@Scope("prototype")
public class HelpAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	/**
	 *@MethodName:list
	 *@Description:跳转到捐助页面
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-9-13下午04:15:43
	 */
	public String toHelpPage() {
		String out_trade_no = OrderUtils.getOrderNo();
		this.getRequest().setAttribute("out_trade_no", out_trade_no);
		return "list";
	}
}
