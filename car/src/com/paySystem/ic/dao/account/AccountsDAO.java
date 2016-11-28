package com.paySystem.ic.dao.account;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.Accounts;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.account.AccountsDTO;

public interface AccountsDAO extends DAO<Accounts> {
	public static final String ACCOUNTSDAO = "accountsDAO";

	public QueryResult<AccountsDTO> queryAll(int firstindex, int maxresult,
			AccountsDTO accountsDTO, LinkedHashMap<String, String> orderby)
			throws Exception;

	public List<AccountsDTO> findAccounts(String accId);

	public List<AccountsDTO> findByCardNo(String cardNo);

	/**
	 *@Title:saveAccounts
	 *@Description:保存账户信息
	 *@param:@param cardNos
	 *@param:@return
	 *@param:@throws Exception
	 *@return:ReturnDTO
	 *@author:謝
	 *@thorws:
	 */
	public ReturnDTO saveAccounts(List<CardNo> cardNos) throws Exception;

	/**
	 * @Title:saveAccountss
	 * @Descrition:TODO 换卡的保存方法 需要遍历这个集合保存每一条信息
	 * @param: @param accList
	 * @param: @throws Exception
	 * @return: void
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public void saveAccountss(AccountsDTO nAccountDTO) throws Exception;

	public List<AccountsDTO> findAccountsByCardNo(String cardNo);

	public void updateAccounts(AccountsDTO accountDTO);

	void updateInAmtByCardNo(String beginCardNo, BigDecimal initAmt,
			BigDecimal newPoint);

	public List<AccountsDTO> findAccountsByCardNoShow(String cardNoShow);

	/**
	 * 判断该账户号是否属于该卡号
	 * */
	public Accounts checkType(String cardNo, Integer typtId);

	/**
	 * 赵巧鹤 根据账户号查找账户对象dto
	 * */
	public AccountsDTO queryAccountsById(String accId);

	/**
	 * @Title:findByCardNos
	 * @Descrition:TODO
	 * @param: @param cardNo
	 * @param: @return
	 * @return: List<AccountsDTO>
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public List<AccountsDTO> findByCardNos(String cardNo);

	public String getAccountId(Integer accKind);

	public Accounts checkType2(String cardNo, Integer typeId);

	/**
	 * 
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
	 *@return:AccountsDTO
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
