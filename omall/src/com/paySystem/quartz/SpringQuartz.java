package com.paySystem.quartz;

import javax.annotation.Resource;
import org.apache.log4j.Logger;


import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.report.MerSettDayService;
import com.paySystem.ic.service.report.MerSettTotalService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.ReadInit;

/**
 * 公用任务调度
 * @version 2013-9-25 下午04:58:56
 */
public class SpringQuartz {
	public static Logger log = Logger.getLogger(SpringQuartz.class);
	@Resource OrgansService organsService;
	@Resource MerSettDayService merSettDayService;
	@Resource MerSettTotalService merSettTotalService;
	/**
	 * 连接数据库，防止中断
	 */
	public void conn(){
		organsService.find(ReadInit.read("ORGANID"));
	}
	
	/**
	 *@Title:merchantSettle
	 *@Description:每日调度：商户日终结算信息跑批
	 *@param:
	 *@return:void
	 *@author:谢
	 *@thorws:
	 */
	public void merchantSettleDay(){
		
		try {
			  merSettDayService.runMerSettDay();
		    }
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *@Title:merchantsSettleTotal
	 *@Description:每日调度:商户周期结算信息跑批
	 *@param:
	 *@return:void
	 *@author:谢
	 *@thorws:
	 */
	public void merchantsSettleTotal(){
		
		 try {
			   merSettTotalService.runMerSettTotal();
		     } 
		 catch (Exception e) {
			   e.printStackTrace();
		   }
	}
}