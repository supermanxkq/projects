package com.paySystem.ic.dao.account;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.AccountsRecord;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.account.AccountsRecordDTO;
import com.paySystem.ic.web.dto.card.CardReturnDTO;
import com.paySystem.ic.web.dto.order.RechargeDTO;
import com.paySystem.ic.web.dto.order.SaleOrderDTO;

/**
 * @ClassName:AccountsRecordDao
 * @TODO:帐户操作记录DAO接口
 * @date: 2014-4-8上午10:56:56
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface AccountsRecordDao extends DAO<AccountsRecord> {
	public static final String ACCOUNTSRECORDDAO = "accountsRecordDao";

	/**
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
	
	/***
	 * 
	 *@Title:getAccountsRecordDTO
	 *@Description:售卡订单保存账户操作变动信息
	 *@param:@param saleOrderDTO
	 *@param:@return
	 *@return:AccountsRecordDTO
	 *@author:井建国
	 *@thorws:
	 */
	public void saveAccountsRecordDTO(SaleOrderDTO saleOrderDTO);
	/**
	 * 
	 *@Title:saveByDTO
	 *@Description:TODO
	 *@param:@param accountsRecordDTO
	 *@return:void
	 *@author: 孟凡岭
	 *@thorws:
	 */
	public void saveByDTO(AccountsRecordDTO accountsRecordDTO);
	/***
	 * 
	 *@Title:saveAccountsRecordDTO
	 *@Description: 充值后进行账户操作变动信息记录
	 *@param:@param rechargeDTO
	 *@return:void
	 *@author:井建国
	 *@thorws:
	 */
	public void saveAccountsRecordDTO(RechargeDTO rechargeDTO);
	/***
	 * 
	 *@Title:saveAccountsRecordDTO
	 *@Description:退卡时进行账户操作变动信息记录
	 *@param:@param cardReturnDTO
	 *@return:void
	 *@author:井建国
	 *@thorws:
	 */
	public void saveAccountsRecordDTO(CardReturnDTO cardReturnDTO);
	/**
	
	* @ClassName: AccountsRecordDao.java
	
	* @Description: 单张售卡订单保存账户操作变动信息
	
	* @author  赵巧鹤
	
	* @date 2014-6-9 下午04:58:46
	
	*/
	public void saveAccountsRecordDTOSale(SaleOrderDTO saleOrderDTO);
}
