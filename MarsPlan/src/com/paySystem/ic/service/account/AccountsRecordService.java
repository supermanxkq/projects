package com.paySystem.ic.service.account;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.AccountsRecord;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.account.AccountsRecordDTO;

/**
 * @ClassName:AccountsRecordService
 * @TODO:TODO
 * @date: 2014-4-8上午10:53:57
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface AccountsRecordService extends DAO<AccountsRecord> {
	public static final String ACCOUNTSRECORDSERVICE = "accountsRecordService";

	/**
	 * 
	 *@Title:saveByDTO
	 *@TODO:从DTO保存实体
	 *@data:2014-4-10
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	void saveByDTO(AccountsRecordDTO arDTO);

	/***
	 * 
	 *@Title:queryResult
	 *@TODO:返回查询结果
	 *@data:2014-4-10
	 *@return:QueryResult<AccountsRecordDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	QueryResult<AccountsRecordDTO> queryResult(int i, int pageNum,
			AccountsRecordDTO accountsRecordDTO,
			LinkedHashMap<String, String> orderby) throws Exception;
	
}
