package com.paySystem.ic.service.account;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.account.AccountsDTO;

public interface AccountsService {

	public static String ACCOUNTSSERVICE = "accountsService";

	/** 查询所有的记录 */
	public QueryResult<AccountsDTO> queryAll(int firstindex, int maxresult,
			AccountsDTO accountsDTO, LinkedHashMap<String, String> orderby);

	public List<AccountsDTO> findAccounts(String cardNo);

	public List<AccountsDTO> findByCardNo(String cardNo);

	/**
	 *@Title:queryAccByCardNo
	 *@TODO:根据卡号查出帐户信息
	 *@data:2014-4-8
	 *@return:AccountsDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	public AccountsDTO queryAccByCardNo(AccountsDTO accountsDTO);
/**
 * 
 *@Title:findById
 *@TODO:通过主键返回DTO
 *@data:2014-4-9
 *@return:void
 *@author:孟凡岭
 *@thorws:
 */
	public AccountsDTO findById(String accId);

	/**
	 * 
	 *@Title:updateByDTO
	 *@TODO:通过DTO更新数据
	 *@data:2014-4-9
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
public void updateByDTO(AccountsDTO accDTO);

/**
 * 
 *@Title:findAccByShowCardNo
 *@Description:根据显示卡号查询账户
 *@param:@param cardNo
 *@param:@return
 *@return:List<AccountsDTO>
 *@author:井建国
 *@thorws:
 */
public List<AccountsDTO> findAccByShowCardNo(String cardNo) ;
}
