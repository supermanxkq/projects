package com.paySystem.ic.service.account.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.account.AccountsDAO;
import com.paySystem.ic.dao.account.AccountsRecordDao;
import com.paySystem.ic.service.account.AccountsService;
import com.paySystem.ic.web.dto.account.AccountsDTO;

@Service(AccountsService.ACCOUNTSSERVICE)
public class AccountsServiceImpl implements AccountsService {

	@Resource
	private AccountsDAO accountsDAO;
	@Resource
	private AccountsRecordDao accountsRecordDao;
	
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

	public List<AccountsDTO> findAccounts(String cardNo) {
		// TODO Auto-generated method stub
		return accountsDAO.findAccounts(cardNo);
	}

	public List<AccountsDTO> findByCardNo(String cardNo) {
		return accountsDAO.findByCardNo(cardNo);
	}

	/**
	 * 
	 *@Title:queryAccByCardNo
	 *@TODO:根据卡号查出帐户信息
	 *@param:@param cardNo
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public AccountsDTO queryAccByCardNo(AccountsDTO accountsDTO) {
		// TODO Auto-generated method stub
		return accountsDAO.queryAccByCardNo(accountsDTO);
	}

	/**
	 * 
	 *@Title:findById
	 *@TODO:通过主键返回DTO
	 *@param:@param accId
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public AccountsDTO findById(String accId) {
		// TODO Auto-generated method stub
		return accountsDAO.findById(accId);
	}

	/**
	 * 
	 *@Title:updateByDTO
	 *@TODO:通过DTO更新数据
	 *@param:@param accDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	@Transactional
	public void updateByDTO(AccountsDTO accDTO) {
		// TODO Auto-generated method stub
		accountsDAO.updateByDTO(accDTO);
	}

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
	public List<AccountsDTO> findAccByShowCardNo(String cardNo){
		return accountsDAO.findAccByShowCardNo(cardNo);
	}
}
