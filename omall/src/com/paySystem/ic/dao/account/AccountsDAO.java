package com.paySystem.ic.dao.account;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.Accounts;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.account.AccountsDTO;

/**
 * @ProjectName: omall_back
 * @ClassName: AccountsDAO
 * @Description: 线上积分账户DAO
 * @date: 2014-11-11
 * @author: 廖晓远 
 * @version: V1.0
 */
public interface AccountsDAO extends DAO<Accounts> {
	public static final String ACCOUNTSDAO = "accountsDAO";

	/**
	*@Title: queryAll
	*@Description: 根据前台条件从数据库中获取相应的信息
	*@Params: @param firstindex
	*@Params: @param maxresult
	*@Params: @param accountsDTO
	*@Params: @param orderby
	*@Params: @return
	*@Params: @throws Exception
	*@Return: QueryResult<AccountsDTO>
	*@author: 廖晓远 
	*@Date: 2014-11-11下午04:42:17
	*/
	public QueryResult<AccountsDTO> queryAll(int firstindex, int maxresult,
			AccountsDTO accountsDTO, LinkedHashMap<String, String> orderby)
			throws Exception;

	/**
	 * 
	 *@Title:findById
	 *@TODO:通过主键返回DTO
	 *@param:@param accId
	 *@param:@return
	 *@author:孟凡岭
	 * @throws Exception 
	 */
	public AccountsDTO findById(String onAccId) throws Exception;
}
