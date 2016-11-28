package com.paySystem.ic.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.report.MerSettTotal;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.MerSettTotalDao;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.report.MerSettTotalDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MerSettTotalDaoImpl
 * @Description:商户周期跑批Dao实现类
 * @date: 2013-12-25上午10:29:55
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(MerSettTotalDao.MERSETTTAOTALDAO)
public class MerSettTotalDaoImpl extends DaoSupport<MerSettTotal> implements MerSettTotalDao{

	
	/**
	 *@Title:saveMerSettTotal
	 *@Description:批量保存商户周期结算信息
	 *@param:@param merSettTotalList
	 *@param:@throws Exception
	 *@return:void
	 *@author: 谢
	 *@thorws:
	 */
	public void saveMerSettTotal(List<MerSettTotal> merSettTotalList)
			throws Exception {
		for(MerSettTotal merSettTotal : merSettTotalList){
			
			this.save(merSettTotal);
		}
		
	}

	/**
	 *@Title:queryMerSettTotal
	 *@Description:查询商户周期结算信息
	 *@param:@param merSettTotalDTO
	 *@param:@return
	 *@return:Query
	 *@author:谢
	 * @throws Exception 
	 *@thorws:
	 */
	public QueryResult<MerSettTotal> queryMerSettTotal(int fristindex, int pageNum,
			MerSettTotalDTO merSettTotalDTO, LinkedHashMap<String, String> orderBy) throws Exception {
		StringBuilder sql = new StringBuilder(); //封装查询where条件
		List<Object> params = new ArrayList<Object>();
		//获取UserSession
		UserSession us = Utils.getUserSession();
	    /**
	     * 限定不同级别操作员的限制条件
	     */
		switch(us.getUserLevel()){
		case 1: //机构级别
			sql.append(" and o.merchants.organs.organId = '"+us.getOrganId()+"'");
			break;
		case 2: //商户级别
			sql.append(" and o.merchants.merId = '"+us.getMerId()+"'");
			break;
		}
		

		/**判断页面条件*/
		if(StringUtils.isNotBlank(merSettTotalDTO.getMerId())){
			sql.append(" and o.merchants.merId like ?").append(params.size()+1);
			//设置参数:商户ID
			params.add("%"+merSettTotalDTO.getMerId()+"%");
		}
		if(StringUtils.isNotBlank(merSettTotalDTO.getMerName())){
			//设置参数：终端ID
			sql.append(" and o.merchants.merName like ?").append(params.size()+1);
			params.add("%"+merSettTotalDTO.getMerName()+"%");
		}
		if(merSettTotalDTO.getFlag()!=-1){
			sql.append(" and o.flag = "+merSettTotalDTO.getFlag());
		}
		
		
		QueryResult<MerSettTotal> queryResult =
			          getScrollData(fristindex, pageNum,
			        		        sql.toString(),
			        		        params.toArray(),
			        		        orderBy);
		
		return queryResult;
	}

	/**
	 *@Title:sureMerSett
	 *@Description:确认结算
	 *@param:@param merSettTotalDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:谢
	 *@thorws:
	 */
	public MerSettTotalDTO sureMerSett(MerSettTotalDTO merSettTotalDTO) {
		MerSettTotal merSettTotal = this.find(merSettTotalDTO.getId());
		//1.算出本次需要结算金额 = 本次结算金额 —— 上次结余
		BigDecimal needSettAmt = NumberUtil.sub(merSettTotal.getSupportSettAmt(),merSettTotal.getLastBalance());
		//2.算出本次结余 = 本次需要结算 — 本次实际交付金额
		BigDecimal thisBal = NumberUtil.sub( merSettTotalDTO.getActualSettAmt(),needSettAmt);
		// 本次结余金额
		merSettTotal.setThisTimeBalance(thisBal);
		//本次实际支付金额
		merSettTotal.setActualSettAmt(merSettTotalDTO.getActualSettAmt());
		//结算状态： 1-已结算
		merSettTotal.setFlag(1);
		//结算时间：当前系统时间
		merSettTotal.setSettTime(getSysTime());
		//结算操作员 userName
		UserSession us = Utils.getUserSession();
		merSettTotal.setOperator(us.getUserName());
		/**
		 * 更新 商户周期结算信息(MerSettTotal)
		 * 1.新增本次结余金额
		 * 2.新增本次实际支付金额
		 * 3.新增本次结算时间
		 * 4.更改结算状态：已结算
		 * 5.新增结算操作员
		 */
		this.update(merSettTotal);
		
		return getMerSettTotalDTO(merSettTotal);
	}
	
	private MerSettTotalDTO getMerSettTotalDTO(MerSettTotal merSettTotal){
		MerSettTotalDTO merSettDTO = new MerSettTotalDTO();
		
		merSettDTO.setId(merSettTotal.getId());
		merSettDTO.setMerId(merSettTotal.getMerchants().getMerId());
		merSettDTO.setActualSettAmt(merSettTotal.getActualSettAmt());
		merSettDTO.setBeginTime(merSettTotal.getBeginTime());
		merSettDTO.setConsAmt(merSettTotal.getConsAmt());
		merSettDTO.setConsCommis(merSettTotal.getConsCommis());
		merSettDTO.setEndTime(merSettTotal.getEndTime());
		merSettDTO.setFlag(merSettTotal.getFlag());
		merSettDTO.setLastBalance(merSettTotal.getLastBalance());
		merSettDTO.setMerName(merSettTotal.getMerchants().getMerName());
		merSettDTO.setOperator(merSettTotal.getOperator());
		merSettDTO.setThisTimeBalance(merSettTotal.getThisTimeBalance());
		merSettDTO.setSupportSettAmt(merSettTotal.getSupportSettAmt());
		merSettDTO.setSettTime(merSettTotal.getSettTime());
		return merSettDTO;
	}

	/**
	 *@Title:getAllNeedSettMerId
	 *@Description:获取所有要结算的商户集合信息
	 *@param:@param mersList  所有商户集合
	 *@param:@return
	 *@return:List<String> 所有需要结算的商户编号
	 *@author: 谢
	 *@thorws:
	 */
	public List<String> getAllNeedSettMerId(List<Merchants> mersList) {
		List<String> merIdList = new ArrayList<String>();
		
		for( Merchants merchants : mersList ){
		  	
		}
		return null;
	}

	/**
	 *@Title:getLastSettInfo
	 *@Description:获取上次结算信息
	 *@param:@param merId  商户ID
	 *@param:@return
	 *@return:MerSettTotal
	 *@author:谢
	 *@thorws:
	 */
	public MerSettTotal getLastSettInfo(String merId) {
		MerSettTotal merSettToal = null;
		StringBuilder sb = new StringBuilder(" select o from MerSettTotal o where o.merchants.merId = '"+merId+"'");
		List<MerSettTotal> merSettList = em.createQuery(sb.toString()).getResultList();
		if(merSettList.size()>0){
			merSettToal = merSettList.get(0);
		}
		return merSettToal;
	}
	
	

}
