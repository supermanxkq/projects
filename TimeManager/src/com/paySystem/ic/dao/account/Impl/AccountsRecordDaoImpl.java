package com.paySystem.ic.dao.account.Impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.AccountsRecord;
import com.paySystem.ic.dao.account.AccountsRecordDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.account.AccountsRecordDTO;
import com.paySystem.ic.web.dto.card.CardReturnDTO;
import com.paySystem.ic.web.dto.order.RechargeDTO;
import com.paySystem.ic.web.dto.order.SaleOrderDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:AccountsRecordDaoImpl
 * @TODO:帐户操作记录DAO接口实现类
 * @date: 2014-4-8上午10:57:20
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(AccountsRecordDao.ACCOUNTSRECORDDAO)
public class AccountsRecordDaoImpl extends DaoSupport<AccountsRecord> implements
		AccountsRecordDao {
	/**
	 * 
	 *@Title:queryResult
	 *@TODO:返回查询结果
	 *@param:@param i
	 *@param:@param pageNum
	 *@param:@param accountsRecordDTO
	 *@param:@param orderby
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public QueryResult<AccountsRecordDTO> queryResult(int i, int pageNum,
			AccountsRecordDTO recordDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		// TODO Auto-generated method stub
		/*** 封装查询where条件 */
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 1:
			sql.append(" and o.orgId='" + us.getOrganId() + "' ");
			break;
		}
		if (recordDTO.getOperateType() == 0) {
			sql.append(" and o.operateType = ?").append(params.size() + 1);
			params.add(recordDTO.getOperateType());
		} else if (recordDTO.getOperateType() == 1) {
			sql.append(" and o.operateType = ?").append(params.size() + 1);
			params.add(recordDTO.getOperateType());
		}else if(recordDTO.getOperateType()==-1){
			sql.append(" and (o.operateType = 0 or o.operateType = 1)");
		}
		if (recordDTO.getCardNo() != null && recordDTO.getCardNo().length() > 0) {
			sql.append(" and o.cardNo like ?").append(params.size() + 1);
			params.add("%" + recordDTO.getCardNo() + "_");
		}
		if (recordDTO.getCreateTime() != null && recordDTO.getEndTime() != null) {
			sql
					.append(" and to_date(to_char(o.createTime,'yyyy-mm-dd'),'yyyy-mm-dd') between to_date('"
							+ DateTimeTool.dateFormat("yyyy-MM-dd", recordDTO
									.getCreateTime())
							+ "','yyyy-mm-dd') and to_date('"
							+ DateTimeTool.dateFormat("yyyy-MM-dd", recordDTO
									.getEndTime()) + "','yyyy-mm-dd')");
		} else if (recordDTO.getCreateTime() != null) {
			sql
					.append(" and to_date(to_char(o.createTime,'yyyy-mm-dd'),'yyyy-mm-dd')<=to_date('"
							+ DateTimeTool.dateFormat("yyyy-MM-dd", recordDTO
									.getCreateTime()) + "','yyyy-mm-dd')");
		} /*else if (recordDTO.getEndTime() != null) {
			sql
					.append(" and to_date(to_char(o.createTime,'yyyy-mm-dd'),'yyyy-mm-dd')<=to_date('"
							+ DateTimeTool.dateFormat("yyyy-MM-dd", recordDTO
									.getEndTime()) + "','yyyy-mm-dd')");
		}*/
		QueryResult<AccountsRecord> queryResult = getScrollData(i, pageNum, sql
				.toString(), params.toArray(), orderby);
		List<AccountsRecord> list = queryResult.getResultlist();
		QueryResult<AccountsRecordDTO> query = new QueryResult<AccountsRecordDTO>();
		List<AccountsRecordDTO> listt = getListDTO(list);
		query.setResultlist(listt);
		query.setTotalrecord(queryResult.getTotalrecord());
		return query;
	}

	/***
	 * 
	 *@Title:getListDTO
	 *@TODO:返回DTOlist集合
	 *@data:2014-4-10
	 *@return:List<AccountsRecordDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	private List<AccountsRecordDTO> getListDTO(List<AccountsRecord> list) {
		// TODO Auto-generated method stub
		List<AccountsRecordDTO> listt = new ArrayList<AccountsRecordDTO>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				AccountsRecord a = list.get(i);
				AccountsRecordDTO dto = new AccountsRecordDTO();
				dto.setAmount(a.getAmount());
				dto.setCardNo(a.getCardNo());
				dto.setCreateTime(a.getCreateTime());
				dto.setId(a.getId());
				dto.setOperateType(a.getOperateType());
				dto.setOperatorId(a.getOperatorId());
				dto.setOrgId(a.getOrgId());
				dto.setAccTypeId(a.getAccTypeId());
				listt.add(dto);
			}
		}
		return listt;
	}
	
	/**
	
	* @ClassName: AccountsRecordDaoImpl.java
	
	* @Description: 单张售卡订单保存账户操作变动信息
	
	* @author  赵巧鹤
	
	* @date 2014-6-9 下午04:55:05
	
	*/
	public void saveAccountsRecordDTOSale(SaleOrderDTO saleOrderDTO){
		AccountsRecordDTO accountsRecordDTO = new AccountsRecordDTO();
		UserSession us = Utils.getUserSession();
		accountsRecordDTO.setAccTypeId(0);
		accountsRecordDTO.setAmount(saleOrderDTO.getPaidAmt());
		accountsRecordDTO.setOperateType(3);
		accountsRecordDTO.setAccFlow(4);
		accountsRecordDTO.setOrgId(us.getOrganId());
		accountsRecordDTO.setCreateTime(getSysTime());
			accountsRecordDTO.setCardNo(saleOrderDTO.getBeginCardNo());
			saveByDTO(accountsRecordDTO);
		
	}
	/****
	 * 
	 *@Title:getAccountsRecordDTO
	 *@Description:售卡订单保存账户操作变动信息
	 *@param:@param saleOrderDTO
	 *@param:@return
	 *@return:AccountsRecordDTO
	 *@author:井建国
	 *@thorws:
	 */
	public void saveAccountsRecordDTO(SaleOrderDTO saleOrderDTO){
		@SuppressWarnings("unused")
		AccountsRecordDTO accountsRecordDTO = new AccountsRecordDTO();
		UserSession us = Utils.getUserSession();
		accountsRecordDTO.setAccTypeId(0);
		accountsRecordDTO.setAmount(saleOrderDTO.getPaidAmt());
		accountsRecordDTO.setOperateType(3);
		accountsRecordDTO.setAccFlow(4);
		accountsRecordDTO.setOrgId(us.getOrganId());
		accountsRecordDTO.setCreateTime(getSysTime());
		List<String> listCardNo = saleOrderDTO.getCardNo();//到这儿就行了？
		for (int i = 0; i < listCardNo.size(); i++) {
			String cardNo = listCardNo.get(i);
			accountsRecordDTO.setCardNo(cardNo);
			saveByDTO(accountsRecordDTO);//再save这个？
		}
	}
	public void saveByDTO(AccountsRecordDTO accountsRecordDTO){
		save(getAccountsRecord(accountsRecordDTO));
	}

	/***
	 * 
	 *@Title:getAccountsRecord
	 *@TODO:DTO转实体
	 *@data:2014-4-10
	 *@return:Object
	 *@author:孟凡岭
	 *@thorws:
	 */
	private Object getAccountsRecord(AccountsRecordDTO arDTO) {
		// TODO Auto-generated method stub
		AccountsRecord a = new AccountsRecord();
		a.setAmount(arDTO.getAmount());
		a.setCardNo(arDTO.getCardNo());
		a.setCreateTime(arDTO.getCreateTime());
		a.setOperateType(arDTO.getOperateType());
		a.setOperatorId(arDTO.getOperatorId());
		a.setOrgId(arDTO.getOrgId());
		a.setAccTypeId(arDTO.getAccTypeId());
		a.setAccFlow(arDTO.getAccFlow());
		return a;
	}
	/***
	 * 
	 * @see com.paySystem.ic.dao.account.AccountsRecordDao#saveAccountsRecordDTO(com.paySystem.ic.web.dto.order.RechargeDTO)
	 * @Description:充值时进行账户操作变动信息记录
	 * @date: 2014-4-23下午04:40:26
	 * @author: 井建国
	 * @version: V1.0
	 * @param rechargeDTO
	 */
	public void saveAccountsRecordDTO(RechargeDTO rechargeDTO){
		AccountsRecordDTO accountsRecordDTO = new AccountsRecordDTO();
		UserSession us = Utils.getUserSession();
		accountsRecordDTO.setAccTypeId(0);
		accountsRecordDTO.setAmount(rechargeDTO.getOrderAmt());
		accountsRecordDTO.setOperateType(3);
		accountsRecordDTO.setAccFlow(4);
		accountsRecordDTO.setOrgId(us.getOrganId());
		accountsRecordDTO.setCreateTime(getSysTime());
		accountsRecordDTO.setCardNo(rechargeDTO.getCardNo());
		saveByDTO(accountsRecordDTO);
	}
	
	/***
	 * 
	 * @see com.paySystem.ic.dao.account.AccountsRecordDao#saveAccountsRecordDTO(com.paySystem.ic.web.dto.card.CardReturnDTO)
	 * @Description:退卡时进行账户操作变动信息记录
	 * @date: 2014-4-23下午04:44:46
	 * @author: 井建国
	 * @version: V1.0
	 * @param cardReturnDTO
	 */
	public void saveAccountsRecordDTO(CardReturnDTO cardReturnDTO){
		AccountsRecordDTO accountsRecordDTO = new AccountsRecordDTO();
		UserSession us = Utils.getUserSession();
		accountsRecordDTO.setAccTypeId(0);
		accountsRecordDTO.setAmount(cardReturnDTO.getAllAmt());
		accountsRecordDTO.setOperateType(3);
		accountsRecordDTO.setOrgId(us.getOrganId());
		accountsRecordDTO.setCreateTime(getSysTime());
		accountsRecordDTO.setAccFlow(3);
		accountsRecordDTO.setCardNo(cardReturnDTO.getCardNo());
		saveByDTO(accountsRecordDTO);
	}
}
