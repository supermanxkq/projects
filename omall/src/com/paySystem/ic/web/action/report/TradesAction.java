package com.paySystem.ic.web.action.report;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.web.action.BaseAction;

/**
 * @ClassName: TradesAction
 * @Description: 交易跑批Action
 * @author：谢洪飞
 * @date 2013-12-28 下午06:45:15
 * 
 */
@Controller("/report/trades")
@Scope("prototype")
public class TradesAction extends BaseAction {
	private static final long serialVersionUID = -6333175596566821109L;
	/*@Resource
	TradesViewService tradesViewService;*/
	@Resource
	OrgansService organsService;
	@Resource
	MerchantsService merService;

	/**
	 *@Title:list
	 *@Description:TODO
	 *@param:@return
	 *@return:String
	 *@author:谢洪飞
	 *@thorws:
	 */
	public String list() {
		this.getRequest()
				.setAttribute("organValues", organsService.getOption());
		this.getRequest().setAttribute("merValues", merService.getOption());
		return "list";
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String jsonPageList() throws Exception {
		
		
		return "";
	}
}
