package com.paySystem.ic.service.account.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.AccountsRecord;
import com.paySystem.ic.dao.account.AccountsRecordDao;
import com.paySystem.ic.service.account.AccountsRecordService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.account.AccountsRecordDTO;

/**
 * @ClassName:AccountsRecordServiceImpl
 * @TODO:帐户操作记录Service接口实现类
 * @date: 2014-4-8上午10:54:20
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(AccountsRecordService.ACCOUNTSRECORDSERVICE)
public class AccountsRecordServiceImpl extends DaoSupport<AccountsRecord>
		implements AccountsRecordService {
	/** 帐户操作记录DAO接口实现类 **/
	@Resource
	private AccountsRecordDao accountsRecordDao;
	/**
	 * 
	 *@Title:saveByDTO
	 *@TODO:从DTO保存实体
	 *@param:@param arDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	@Transactional
	public void saveByDTO(AccountsRecordDTO arDTO) {
		accountsRecordDao.saveByDTO(arDTO);
	}


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
			AccountsRecordDTO accountsRecordDTO,
			LinkedHashMap<String, String> orderby) throws Exception{
		// TODO Auto-generated method stub
		return accountsRecordDao.queryResult(i,pageNum,accountsRecordDTO,orderby);
	}
	
}
