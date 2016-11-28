package com.paySystem.ic.dao.account.Impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.Accounts;
import com.paySystem.ic.dao.account.AccountsDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.account.AccountsDTO;

/**
 * @ProjectName: omall_back
 * @ClassName: AccountsDaoImpl
 * @Description: 线上积分账户DAO实现
 * @date: 2014-11-11
 * @author: 廖晓远 
 * @version: V1.0
 */
@Repository(AccountsDAO.ACCOUNTSDAO)
public class AccountsDaoImpl extends DaoSupport<Accounts> implements
		AccountsDAO {

	/**
	 *@Title:queryAll
	 *@Description:根据前台条件从数据库中获取相应的信息
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param accountsDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@author:王月
	 *@thorws:
	 *2014-5-28
	 *
	 */
	public QueryResult<AccountsDTO> queryAll(int firstindex, int maxresult,
			AccountsDTO accountsDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		StringBuilder sql = new StringBuilder(""); // 封装查询where条件
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotBlank(accountsDTO.getMemId())) {
			sql.append(" and o.memId like ?").append(params.size() + 1);
			params.add("%" + accountsDTO.getMemId() + "%");
		}
		
		QueryResult<Accounts> queryResult = getScrollData(firstindex, maxresult, sql
				.toString(), params.toArray(), orderby);
		List<Accounts> list = queryResult.getResultlist();
		QueryResult<AccountsDTO> query = new QueryResult<AccountsDTO>();
		List<AccountsDTO> accountsList = new ArrayList<AccountsDTO>();
		for(Accounts accounts : list){
			AccountsDTO dto = (AccountsDTO) EntityDtoConverter.bean2Dto(accounts, new AccountsDTO());
			accountsList.add(dto);
		}
		query.setResultlist(accountsList);
		query.setTotalrecord(queryResult.getTotalrecord());
		return query;
	}

	/**
	 * 
	 *@Title:findById
	 *@TODO:通过主键返回DTO
	 *@param:@param accId
	 *@param:@return
	 *@author:孟凡岭
	 * @throws Exception 
	 */
	public AccountsDTO findById(String onAccId) throws Exception {
		Accounts acc = this.find(onAccId);
		return (AccountsDTO) EntityDtoConverter.bean2Dto(acc, new AccountsDTO());
	}

}
