package com.paySystem.ic.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.report.MerSettDay;
import com.paySystem.ic.bean.report.Trades;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.MerSettDayDao;
import com.paySystem.ic.dao.report.TradesDao;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.quartz.SpringQuartz;

/**
 * @ClassName:TradesDaoImpl
 * @Description:联机交易Dao实现类
 * @date: 2013-12-24下午04:19:20
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(TradesDao.TRADESDAO)
public class TradesDaoImpl extends DaoSupport<Trades> implements TradesDao{
	
	@Resource MerSettDayDao merSettDayDao;

	public static Logger settLog = Logger.getLogger(SpringQuartz.class);
	
	/**
	 *@Title:RunTradesByMerchants
	 *@Description:商户结算信息日终汇总跑批
	 *          一.补漏
	 *             1.先从日跑批信息记录表中取日跑批记录最大日期的记录。
	 *             2.日跑批最大记录日期与当前日期比较
	 *               2.1 如果最大日期与当前跑批日期相差1天，则正常跑批
	 *               2.2如果最大日期与当前跑批日期相差大于1天，则循环
	 *                  统计补漏日跑批信息表中缺少的日期记录
	 *                 
	 *          二.正常跑批
	 *             1.统计本商户总消费金额
	 *             2.统计本商户总消费手续费
	 *             3.统计本商户总消费结算金额
	 *             4.统计本商户总退货金额
	 *             5.统计本商户总退货商户品 - 原交易的手续费
	 *             6.统计本商户总退货结算金额
	 *             8.统计本商户总充值金额
	 *             9.统计本商户总充值结算金额
	 *             10.统计本商户总结算金额 = （总消费结算金额 - 总退货结算金额 - 总充值结算金额）
	 *@param:@param merList  所有商户信息集合
	 *@param:@param settDate 结算时间 = ( 跑批时间 - 1 )
	 *@param:@return
	 *@return:List<MerSettDay> 返回商户日结信息集合
	 *@author:谢
	 *@thorws:
	 */
	public List<MerSettDay> runTradesByMerchants(List<Merchants> merList,
			Date settTime) {
		
		//定义要返回的集合： 商户每日跑批信息记录集合 List<MerSettDay>
		List<MerSettDay> merSettDayList = new ArrayList<MerSettDay>();
		
		/**
		 * 判断本次跑批之前所有未跑批的项目
		 * 1.获取表中跑批日期最大的记录
		 * 2.将最大记录与本日比较，如果相差一天，则正常跑批。否则：在相差日期中每日跑批一次，并插入记录到日跑批信息记录表中。
		 */
		
		
		//公用jpql查询语句对象
		StringBuilder jpql = null;
		
		/** 日跑批查漏：
		 *      对比日结算信息表中本商户最大跑批日期，
		 *      与本次跑批日期相差天数如果大于1天，
		 *      则根据相差天数循环找漏
		 **/
		for(Merchants merchants : merList){
			settLog.info("查找 商户编号为:"+merchants.getMerId()+"的补漏信息");
			MerSettDay merSettDay = merSettDayDao.queryMerSettDayBeforeToday(merchants.getMerId());//如果以前有本商户的日跑批信息
			Date lastRunDate = null;
			if(merSettDay!=null){
				lastRunDate = merSettDay.getCreateTime();//上次跑批日期
			}
			else{
				lastRunDate = DateTimeTool.nDaysAgo(1,merchants.getLastSettTime());//获取结算前一天日期
			}
				int diffNum = DateTimeTool.getDiffDay(lastRunDate, settTime).intValue();
				
				
				/**
				 * 如果上次跑批日期与昨天日期相差天数大于1天,
				 * 则从Trades表中循环统计相差天数的交易信息，
				 * 并插入到日跑批记录表
				 */
				if(diffNum>=1){
					for(int i = 0 ;i<diffNum ;i++){
						//准备汇总日期
						Date missDate = DateTimeTool.nDaysAfter(i+1, lastRunDate);//补漏日期
						Date transDate = DateTimeTool.nDaysAfter(i, lastRunDate); //补漏信息的交易日期
						
						settLog.info("商户日终结算   查漏   统计\n +" +
								     "【商户编号:"+merchants.getMerId()+"】\n" +
								     "【商户名称:"+merchants.getMerName()+"】\n" +
								     "【所补漏信息交易日期:"+missDate+"】\n" + //标记最后统计日期的n 天之后
								     "【统计时间:"+DateTimeTool.dateFormat("", new Date())+"】\n" +
								     "开始补漏统计日结算信息...\n");
						
						MerSettDay merSettDayMiss = new MerSettDay();
						String merId = merchants.getMerId();
						jpql = new StringBuilder();
						jpql.append(" select count(o.tradeId) from Trades o where o.merchants.merId = '"+merchants.getMerId()+"'");
						jpql.append(" and trunc(o.placedtime,'dd') = trunc(to_timestamp('"
								    +DateTimeTool.dateFormat("", transDate)+"','yyyy-mm-dd hh24:mi:ss:ff'),'dd')");
						
						Long merSettCount = (Long) em.createQuery(jpql.toString()).getSingleResult();
						/**如果统计补漏日期无交易记录*/
						if(merSettCount==0){
							merSettDayMiss = initNoSettDay(merchants,missDate,transDate);
							merSettDayMiss.setCreateTime(missDate);
							merSettDayMiss.setTransDate(transDate);
							merSettDayList.add(merSettDayMiss);
							continue;
						}
						
						merSettDayMiss = this.getSumDaySett(merSettDayMiss, transDate, merId);
						merSettDayMiss.setCreateTime(missDate);
						merSettDayMiss.setTransDate(transDate);
						merSettDayMiss.setMerchants(merchants);
						
						merSettDayList.add(merSettDayMiss);        
					}
				}
			
			
		}
		//准备汇总日期
		Date yesterDay = DateTimeTool.nDaysAgo(1, new Date());
		
		/**遍历所有商户，根据商户统计所属交易信息:统计前一日交易信息*/
		for (Merchants merchants : merList) {
			
			settLog.info(" 商户日终结算统计\n" +
					     "【商户编号:"+merchants.getMerId()+"】\n" +
					     "【商户名称:"+merchants.getMerName()+"】\n" +
					     "【统计时间:"+DateTimeTool.dateFormat("", new Date())+"】\n" +
					     "开始统计每日交易信息...");
			MerSettDay merSettDay = new MerSettDay();
			String merId = merchants.getMerId();
			jpql = new StringBuilder();
			jpql.append(" select count(o.tradeId) from Trades o where o.merchants.merId='"+merchants.getMerId()+"'");
			jpql.append(" and trunc(o.placedtime,'dd') = trunc(to_timestamp('"+DateTimeTool.dateFormat("", settTime)+"','yyyy-mm-dd hh24:mi:ss:ff'),'dd')");
			Long merSettCount = (Long) em.createQuery(jpql.toString()).getSingleResult();
			if(merSettCount==0){
				merSettDay = initNoSettDay(merchants,yesterDay,getSysTime());
				merSettDayList.add(merSettDay);
				continue; //结束本次循环，进入下次循环
			}
			merSettDay = this.getSumDaySett(merSettDay, settTime, merId);
			merSettDay.setMerchants(merchants);
			
			merSettDayList.add(merSettDay);
		}
		return merSettDayList;
	}
	
	/**
	 *@Title:countTradesAmt
	 *@Description:统计  消费的金额和手续费
	 *@param:@param merId       统计的商户
	 *@param:@param tradeType   交易类型
	 *@param:@param placedDate  交易日期
	 *@param:@return
	 *@return:Map<String,BigDecimal> 返回交易金额与手续费Map
	 *@author: 谢
	 *@thorws:
	 */
	private Map<String,BigDecimal> countTradesAmt(String merId,Integer tradeType,Date placedDate){
		Map<String,BigDecimal> returnMap = new HashMap<String, BigDecimal>();
		StringBuilder jpql = new StringBuilder(" select sum(o.tradeAmt),sum(o.sxf),sum(o.proxyAmt) from Trades o where 1=1 ");
		jpql.append(" and o.merchants.merId = '"+merId+"'"); //指定统计范围:所属商户
		jpql.append(" and o.tradeType = '"+tradeType+"'");//指定统计交易记录类型
		jpql.append("  and trunc(o.placedtime,'dd') = trunc(to_timestamp('"+DateTimeTool.dateFormat("", placedDate)+"','yyyy-mm-dd hh24:mi:ss:ff'),'dd')");//日期相等
		switch (tradeType)
		{
		case 15: //交易类型:消费，统计交易状态为 0:正常 和 2:已退货
			jpql.append(" and (o.flag = 0 or o.flag = 2)");
			break;
		case 1: //交易类型:退货，统计交易状态为0:正常
			jpql.append(" and o.flag = "+0);
			break;
		case 2: //交易类型:充值,不限定。（全部为正常）
			jpql.append(" and o.flag = "+0);
			break;
		}
		List<Object []> countTradeAmtList =  em.createQuery(jpql.toString()).getResultList();
		if(countTradeAmtList.size()>0){
			//如果存在此类型的交易记录,则获取
			Object [] countTradeAmtArray = countTradeAmtList.get(0);
			returnMap.put("tradeAmt",(BigDecimal) countTradeAmtArray[0]);//总消费金额
			returnMap.put("sxf", (BigDecimal)countTradeAmtArray[1]);     //总手续费
			returnMap.put("proxyAmt", (BigDecimal)countTradeAmtArray[2]);//总结算金额
		}
		else{
			//如果当日没有此类型的交易记录，则设置为 0.00
			returnMap.put("tradeAmt", new BigDecimal(0.00));
			returnMap.put("sxf", new BigDecimal(0.00));
			returnMap.put("proxyAmt", new BigDecimal(0.00));
		}
		
		return returnMap;
	};
	
	
	/**
	 *@Title:getSumDaySett
	 *@Description:封装统计信息
	 *@param:@param merSettDay
	 *@param:@param settTime
	 *@param:@param merId
	 *@param:@return
	 *@return:MerSettDay
	 *@author:谢
	 *@thorws:
	 */
	private MerSettDay getSumDaySett(MerSettDay merSettDay,Date settTime,String merId){
		 /**1.统计本商户消费金额 以及本商户消费手续费*/
		Map<String,BigDecimal> consAmtMap = this.countTradesAmt(merId,15, settTime);
		merSettDay.setOwnOrgConsAmt(consAmtMap.get("tradeAmt"));  //消费总交易金额
		merSettDay.setOwnOrgCommis(consAmtMap.get("sxf"));        //消费总手续费
		
		 /**2.统计本商户退货金额 以及（本商户退货商户品 的 原交易的手续费） */
		Map<String,BigDecimal> returnGoodMap = this.countTradesAmt(merId,1, settTime);
		merSettDay.setOwnOrgRetAmt(returnGoodMap.get("tradeAmt")); //退货商品总原交易金额
		merSettDay.setOwnOrgRetCommis(returnGoodMap.get("sxf"));   //退货商品总原手续费
		
		/**3.统计本商户充值金额*/
		Map<String,BigDecimal> rechargeMap = this.countTradesAmt(merId, 2, settTime);
		merSettDay.setOwnOrgRech(rechargeMap.get("tradeAmt"));     //充值总交易金额
		/** 计算商户日结算总金额 */
		BigDecimal big1 = NumberUtil.sub(consAmtMap.get("proxyAmt"),returnGoodMap.get("proxyAmt"));
		BigDecimal proxyAmt = NumberUtil.sub(big1, rechargeMap.get("proxyAmt"));//结算金额 = 消费总结算金额  — 退货总结算金额  — 充值总结算金额 
		/*BigDecimal proxyAmt = consAmtMap.get("proxyAmt")//结算金额 = 消费总结算金额  — 退货总结算金额  — 充值总结算金额 
		                            .subtract(returnGoodMap.get("proxyAmt"))
		                            .subtract(rechargeMap.get("proxyAmt"));*/
		
		merSettDay.setOwnOrgProxyAmt(proxyAmt);
		merSettDay.setCreateTime(getSysTime());
		merSettDay.setTotalProxyAmt(proxyAmt);
		merSettDay.setTransDate(DateTimeTool.dateFormat("yyyy-MM-dd", DateTimeTool.dateFormat("yyyy-MM-dd", settTime)));
		return merSettDay;
	}
	

	/**
	 *@Title:initNoSettDay
	 *@Description:初始化无需结算的商户日终跑批信息
	 *@param:@param merchants 统计的商户
	 *@param:@param yesterDay 统计信息发生交易日期
	 *@param:@param actRunTime 应该跑批的日期
	 *@param:@return
	 *@return:MerSettDay  返回初始化完成的对象
	 *@author: 谢
	 *@thorws:
	 */
	private MerSettDay initNoSettDay(Merchants merchants,Date yesterDay,Date actRunTime){
		MerSettDay merSettDay = new MerSettDay();
		//Date yesterDay = DateTimeTool.nDaysAgo(1, new Date());
		BigDecimal noSettAmt = new BigDecimal(0.00);
		//汇总交易的商户
		merSettDay.setMerchants(merchants);
		//本机构信息封装
		merSettDay.setOwnOrgConsAmt(noSettAmt);
		merSettDay.setOwnOrgCommis(noSettAmt);
		merSettDay.setOwnOrgRech(noSettAmt);
		merSettDay.setOwnOrgRetAmt(noSettAmt);
		merSettDay.setOwnOrgRetCommis(noSettAmt);
		//其它机构信息封装
		merSettDay.setOthOrgCommis(noSettAmt);
		merSettDay.setOthOrgConsAmt(noSettAmt);
		merSettDay.setOthOrgRetAmt(noSettAmt);
		merSettDay.setOthOrgRetCommis(noSettAmt);
		//设置交易日期 和 创建日期
		merSettDay.setTransDate(yesterDay);
		merSettDay.setCreateTime(actRunTime);
		return merSettDay;
	}
}
