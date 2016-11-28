package com.paySystem.ic.web.action.report;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.web.action.BaseAction;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:MerSalwSumAction
 * @Description:商户销量汇总Action
 * @date: 2014-7-22
 * @author: 赵巧鹤
 * @version: V1.0
 */
@Controller("/report/merSaleSum")
@Scope("prototype")
public class MerSalwSumAction extends BaseAction{

	
	private static final long serialVersionUID = 1L;
	
	/**
	*@Title:list
	*@Description:TODO
	*@Params:@return
	*@Return:String
	*@author:赵巧鹤
	*@Date:2014-7-22下午02:08:28
	*/
	public String list(){
		
		return "list";
	}

}
