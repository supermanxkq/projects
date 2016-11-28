package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.report.MerSettTotal;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.report.MerSettDayDao;
import com.paySystem.ic.dao.report.MerSettTotalDao;
import com.paySystem.ic.service.report.MerSettTotalService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.report.MerSettTotalDTO;

/**
 * @ClassName:MerSettTotalServiceImpl
 * @Description:商户周期结算跑批Service实现类
 * @date: 2013-12-25下午01:53:26
 * @author: 谢洪飞
 * @version: V1.0
 */
@Service(MerSettTotalService.MERSETTTOTALSERVICE)
public class MerSettTotalServiceImpl implements MerSettTotalService{

	@Resource MerSettDayDao merSettDayDao;
	@Resource MerSettTotalDao merSettTotalDao;
	@Resource MerchantsDao merchantsDao;
	/**
	 *@Title:runMerSettTotal
	 *@Description:商户周期结算信息跑批汇总
	 *
	 *             1.跑批汇总商户日结算信息表中本周期记录
	 *             2.批量保存周期结算信息
	 *             
	 *             调度频率：一个结算周期
	 *             
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:谢
	 *@thorws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO runMerSettTotal() {
		
		ReturnDTO dto = new ReturnDTO();
		//获取所要结算的商户
		List<Merchants> needToSettMers = merchantsDao.getMerchantsNeedToSett(); 
		//1.跑批汇总商户日结算信息表中本周期记录
		List<MerSettTotal> merSettTotalList = merSettDayDao.runMerSettToTotal(needToSettMers);
		//2.批量保存周期结算信息
		try {
			 merSettTotalDao.saveMerSettTotal(merSettTotalList);
			 merchantsDao.batchUpdateMersLastSettDate(needToSettMers);
			dto.setFlag(true);
		} catch (Exception e) {
			dto.setFlag(false);
			e.printStackTrace();
		}
		return dto;
	}
	
	/**
	 *@Title:queryMerSettTotal
	 *@Description:查询商户周期结算信息
	 *@param:@param merSettTotalDTO
	 *@param:@return
	 *@return:Query 商户周期结算信息集合查询Query
	 *@author:谢
	 * @throws Exception 
	 *@thorws:
	 */
	public QueryResult<MerSettTotal> queryMerSettTotal(int fristindex, int pageNum,
            MerSettTotalDTO merSettTotalDTO, LinkedHashMap<String, String> orderBy) throws Exception {
		
		return merSettTotalDao.queryMerSettTotal(fristindex, pageNum, merSettTotalDTO, orderBy);
	}

	/**
	 *@Title:sureMerSett
	 *@Description:确认结算
	 *@param:@param merSettTotalDTO 商户结算
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:谢
	 *@thorws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO sureMerSett(MerSettTotalDTO merSettTotalDTO) {
		ReturnDTO dto = new ReturnDTO();
		MerSettTotalDTO merDto = new MerSettTotalDTO();
	    try {
	    	merDto = merSettTotalDao.sureMerSett(merSettTotalDTO);
			dto.setFlag(true);
			dto.setObj(merDto);
		} catch (Exception e) {
			dto.setFlag(false);
			dto.setObj(merDto);
			e.printStackTrace();
		}
		return dto;
	}

}
