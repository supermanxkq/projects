package com.paySystem.ic.service.report.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.report.MerSettDay;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.report.MerSettDayDao;
import com.paySystem.ic.dao.report.TradesDao;
import com.paySystem.ic.service.report.MerSettDayService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.web.dto.ReturnDTO;

/**
 * @ClassName:MerSettDayServiceImpl
 * @Description:商户日终结算信息汇总跑批Service实现类
 * @date: 2013-12-25上午10:11:08
 * @author: 谢洪飞
 * @version: V1.0
 */
@Service(MerSettDayService.MERSETTDAYSERV)
public class MerSettDayServiceImpl implements MerSettDayService {
	
	@Resource MerSettDayDao merSettDayDao;
	@Resource TradesDao tradesDao;
	@Resource MerchantsDao merchantsDao;

	/**
	 *@Title:runMerSettDay
	 *@Description:日跑批汇总调度
	 *             
	 *             1.汇总交易表中所有前日信息
	 *             2.保存商户日结算信息
	 *             
	 *             调度周期：每天
	 *             
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:
	 *@thorws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO runMerSettDay() {
		ReturnDTO dto = new ReturnDTO();
		Date settDate = DateTimeTool.nDaysAgo(1, new Date());
		//获取所有商户记录（日终跑批所有商户交易信息）
		List<Merchants> merList = merchantsDao.getAllMerchants();
		//跑批获取商户日终结算信息
		List<MerSettDay> merSettDayList = tradesDao.runTradesByMerchants(merList, settDate);
		//保存商户日终结算信息
		try {
			merSettDayDao.saveMerSettDay(merSettDayList);
			dto.setFlag(true);
		} catch (Exception e) {
			dto.setFlag(false);
			e.printStackTrace();
		}
		return dto;
	}

}
