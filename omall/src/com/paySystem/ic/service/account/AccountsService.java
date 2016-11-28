package com.paySystem.ic.service.account;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.account.AccountsDTO;

/**
 * @ProjectName: omall_back
 * @ClassName: AccountsService
 * @Description: 线上账户Services
 * @date: 2014-11-11
 * @author: 廖晓远 
 * @version: V1.0
 */
public interface AccountsService {

	public static String ACCOUNTSSERVICE = "accountsService";

	/** 查询所有的记录 */
	public QueryResult<AccountsDTO> queryAll(int firstindex, int maxresult,
			AccountsDTO accountsDTO, LinkedHashMap<String, String> orderby);

	/**
	 * 
	 *@Title:findById
	 *@TODO:通过主键返回DTO
	 *@data:2014-4-9
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	public AccountsDTO findById(String onAccId) throws Exception;

}
