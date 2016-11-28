package com.paySystem.ic.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.report.MerSettDay;
import com.paySystem.ic.bean.report.MerSettTotal;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.MerSettDayDao;
import com.paySystem.ic.dao.report.MerSettTotalDao;
import com.paySystem.ic.utils.DateTimeTool;

/**
 * @ClassName:MerSettDayDaoImpl
 * @Description:商户日终跑批信息记录表
 * @date: 2013-12-24下午02:50:36
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(MerSettDayDao.MerSettDayDao)
public class MerSettDayDaoImpl extends DaoSupport<MerSettDay> implements MerSettDayDao {

	@Resource MerSettTotalDao merSettTotalDao;
	
	/**
	 *@Title:saveMerSettDay
	 *@Description:保存商户日终跑批信息
	 *@param:@param merSettDayList
	 *@return: void 不要求返回
	 *@author:谢
	 *@thorws:
	 */
	public void saveMerSettDay(List<MerSettDay> merSettDayList) throws Exception {
		
		for (MerSettDay merSettDay : merSettDayList) {
			this.save(merSettDay);
		}
	}

	/**
	 *@Title:runMerSettToTotal
	 *@Description:周期跑批
	 *@param:@param merchantsList 商户集合
	 *@param:@param beginSettDate      结算周期开始日期
	 *@param:@param endSettDate        结算周期结算日期
	 *@param:@return 
	 *@return:List<MerSettTotal>  周期结算信息记录集合
	 *@author:  谢
	 *@thorws:
	 */
	public List<MerSettTotal> runMerSettToTotal(List<Merchants> merchantsList) {
		List<MerSettTotal> merSettTotalList = new ArrayList<MerSettTotal>();
		for(Merchants merchants : merchantsList){
			MerSettTotal merSettTotal = new MerSettTotal();
			/*Date beginPeriodDate = DateTimeTool.nDaysAgo(merchants.getSettPeriod(), new Date()); //周期开始时间*/
			Date beginPeriodDate = DateTimeTool.doDateFormatStringBeginningOfyyyymmdd000000(DateTimeTool.nDaysAfter(1, merchants.getLastSettTime())); //结算开始时间：上次结算时间后1天
			Date endPeriodDate =  
				DateTimeTool.doDateFormatStringBeginningOfyyyymmdd000000(DateTimeTool.nDaysAfter(merchants.getSettPeriod(), merchants.getLastSettTime())); //周期结束时间
			
			//汇总商户日结算信息表中信息，并获得MerSettTotal对象
			merSettTotal = this.sumMerSettDay(merchants, beginPeriodDate, endPeriodDate);
			merSettTotalList.add(merSettTotal);
		}
		return merSettTotalList;
	}
	
	

	/**
	 *@Title:sumMerSettDay
	 *@Description:汇总商户日终跑批表中信息，到商户周期结算信息表中
	 *@param:@param merchants     汇总的商户
	 *@param:@param beginSettDate 周期开始时间
	 *@param:@param endSettDate   周期结束时间
	 *@param:@return
	 *@return:MerSettTotal        商户周期结算信息
	 *@author: 谢
	 *@thorws:
	 */
	private MerSettTotal sumMerSettDay(Merchants merchants,Date beginSettDate,Date endSettDate){
		MerSettTotal merSettTotal = new MerSettTotal();
		MerSettTotal lastMerSettTotal = merSettTotalDao.getLastSettInfo(merchants.getMerId());
		
		StringBuilder jpql = new StringBuilder(" select sum(o.ownOrgConsAmt),sum(o.ownOrgCommis),");
		                                  jpql.append(" sum(o.ownOrgRetAmt),sum(o.ownOrgRetCommis),");
		                                  jpql.append(" sum(o.ownOrgRech),sum(o.ownOrgProxyAmt) from MerSettDay o ");
		                                  jpql.append(" where 1=1 ");
		                                  jpql.append(" and trunc(o.createTime) " +
		                                  		      "  between trunc(to_timestamp('"
								                        +DateTimeTool.dateFormat("", beginSettDate)+"','yyyy-mm-dd hh24:mi:ss:ff'),'dd')"
								                        +" and trunc(to_timestamp('"
								                        +DateTimeTool.dateFormat("", endSettDate)+"','yyyy-mm-dd hh24:mi:ss:ff'),'dd')");
		                                  
		                                  jpql.append(" and o.merchants.merId = '" + merchants.getMerId()+"'");
		                                
		List<Object []> sumObjList = em.createQuery(jpql.toString()).getResultList();
		if(sumObjList.size()>0){
		  Object [] sumObj = sumObjList.get(0);
		  merSettTotal.setConsAmt((BigDecimal)sumObj[0]);     //消费总金额
		  merSettTotal.setConsCommis((BigDecimal)sumObj[1]);  //消费总手续费
		  merSettTotal.setReturnAmt((BigDecimal)sumObj[2]);   //退货总金额
		  merSettTotal.setReturnCommis((BigDecimal)sumObj[3]);//退货总手续费
		  merSettTotal.setRechAmt((BigDecimal)sumObj[4]);     //充值总金额
		  merSettTotal.setSupportSettAmt((BigDecimal)sumObj[5]); //本次结算金额
		  merSettTotal.setActualSettAmt(new BigDecimal(0.00));
		  //上次结余
		  BigDecimal lastBal = lastMerSettTotal==null?new BigDecimal(0.00):lastMerSettTotal.getThisTimeBalance();
		  merSettTotal.setLastBalance(lastBal);
		  merSettTotal.setMerchants(merchants);
		  merSettTotal.setFlag(0);
		  merSettTotal.setBeginTime(beginSettDate);
		  merSettTotal.setEndTime(endSettDate);
		  merSettTotal.setUpdateTime(getSysTime());
		}
		return merSettTotal;
	}

	/**
	 *@Title:queryMerSettDayBeforeToday
	 *@Description:查询商户日结算表中跑批日期中跑批日期最大的记录
	 *@param:@param merId  商户编号
	 *@param:@return
	 *@return:MerSettDay    本商户日跑批结算记录统计日期最大的记录
	 *@author:谢
	 *@thorws:
	 */
	public MerSettDay queryMerSettDayBeforeToday(String merId) {
		MerSettDay merSettDay = null;
		StringBuilder jpql = new StringBuilder("select o from MerSettDay o where 1=1 and o.merchants.merId = '"
				                                +merId+"' order by o.createTime desc ");
		
		List<MerSettDay> merSettDayList = em.createQuery(jpql.toString()).getResultList();
		if(merSettDayList.size()>0){
			merSettDay = merSettDayList.get(0);
		}
		return merSettDay;
	}

	
	
}
