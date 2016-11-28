package com.paySystem.ic.service.account.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.account.AccountsDAO;
import com.paySystem.ic.service.account.AccountsService;
import com.paySystem.ic.web.dto.account.AccountsDTO;

@Service(AccountsService.ACCOUNTSSERVICE)
public class AccountsServiceImpl implements AccountsService {

	@Resource
	private AccountsDAO accountsDAO;
	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.account.AccountsService#queryAll(int, int, com.paySystem.ic.web.dto.account.AccountsDTO, java.util.LinkedHashMap)
	 *@MethodName: queryAll
	 *@Description: 查询账户信息
	 *@param firstindex
	 *@param maxresult
	 *@param accountsDTO
	 *@param orderby
	 *@return
	 *@Author: 廖晓远 
	 *@Date: 2014-11-11下午04:53:26
	 */
	public QueryResult<AccountsDTO> queryAll(int firstindex, int maxresult,
			AccountsDTO accountsDTO, LinkedHashMap<String, String> orderby) {
		QueryResult<AccountsDTO> qr = null;
		try {
			qr = accountsDAO.queryAll(firstindex, maxresult, accountsDTO,
					orderby);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qr;

	}


	/**
	 * 
	 *@Title:findById
	 *@TODO:通过主键返回DTO
	 *@param:@param accId
	 *@param:@return
	 *@author:孟凡岭
	 * @throws Exception 
	 *@thorws:
	 */
	public AccountsDTO findById(String onAccId) throws Exception {
		return accountsDAO.findById(onAccId);
	}

}
