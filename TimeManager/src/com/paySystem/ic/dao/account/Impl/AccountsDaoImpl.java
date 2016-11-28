package com.paySystem.ic.dao.account.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.account.AccKinds;
import com.paySystem.ic.bean.account.Accounts;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.dao.account.AccKindsDAO;
import com.paySystem.ic.dao.account.AccountsDAO;
import com.paySystem.ic.dao.card.CardsDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.account.AccountsDTO;
import com.paySystem.ic.web.dto.card.CardsDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Repository(AccountsDAO.ACCOUNTSDAO)
public class AccountsDaoImpl extends DaoSupport<Accounts> implements
		AccountsDAO {
	@Resource
	CardsDAO cardsDAO;
	@Resource
	AccKindsDAO accKindsDAO;

	@SuppressWarnings("unchecked")
	public List<AccountsDTO> findAccounts(String cardNo) {
		String sql = "select o from Accounts o where o.cardNo = ?1 ";
		Query query = em.createQuery(sql);
		query.setParameter(1, cardNo);
		List<Accounts> list = query.getResultList();
		List<AccountsDTO> listDTO = new ArrayList<AccountsDTO>();
		if(list.size()>0){
			listDTO = getAccountDTOList(list);
			return listDTO;
		}else{
			return null;
		}
	}

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
	@SuppressWarnings("unchecked")
	public QueryResult<AccountsDTO> queryAll(int firstindex, int maxresult,
			AccountsDTO accountsDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		UserSession us = Utils.getUserSession();
		String sql = "select o from Accounts o ";
		//String sql = "select o.accountTypeId,a.kindName,a.kindId from c_accounts o,c_acc_kinds a where o.accounttypeId=a.kindId";
		QueryResult qr = new QueryResult<Accounts>();
		StringBuilder wherejpql = new StringBuilder(""); // 封装查询where条件
		List<Object> params = new ArrayList<Object>();
		/** 判断页面条件 */
		if (us.getUserLevel()==1){
			String sd = "select o from CardBIN o where o.organs.organId = '"+us.getOrganId()+"'";
			List<CardBIN> list = em.createQuery(sd).getResultList();
			for(int i=0;i<list.size();i++){
				CardBIN o = list.get(i);
				if(i==0){
					wherejpql.append(" and (o.cardNo like ?").append(params.size() + 1);
					params.add(o.getBinId()+"%");
				}else{
					wherejpql.append(" or o.cardNo like ?").append(params.size() + 1);
					params.add(o.getBinId()+"%");
				}
			}
			wherejpql.append(") ");
		}		
		if (accountsDTO.getAccTypeID() != null
				&& !("-1").equals(accountsDTO.getAccTypeID())) {
			wherejpql.append(" and o.accountType.accTId = ?").append(
					params.size() + 1);
			params.add(accountsDTO.getAccTypeID());
		}
		if (StringUtils.isNotBlank(accountsDTO.getCardNo())) {
			wherejpql.append(" and o.cardNo like ?").append(params.size() + 1);
			params.add("%" + accountsDTO.getCardNo() + "%");
		}
		sql = sql
				+ (wherejpql == null || "".equals(wherejpql.toString().trim()) ? ""
						: " where 1=1 " + wherejpql.toString())
				+ buildOrderby(orderby);
		Query query = em.createQuery(sql);
		setQueryParams(query, params.toArray());
		/** 获取所有的记录 **/
		if (firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		}
		List<Accounts> list = query.getResultList();
		List<AccountsDTO> accountsList = getAccountDTOList(list);
		qr.setResultlist(accountsList);
		String sql2 = "select count(o) from Accounts o"
				+ (wherejpql == null || "".equals(wherejpql.toString().trim()) ? ""
						: " where 1=1 " + wherejpql.toString());
		query = em.createQuery(sql2);
		setQueryParams(query, params.toArray());
		qr.setTotalrecord((Long) query.getSingleResult());
		return qr;
	}


	public List<AccountsDTO> findAccountsByCardNo(String cardNo) {
		String hql = "select o from Accounts o where o.cardNo = ? ";
		Query query = em.createQuery(hql);
		query.setParameter(1, cardNo);
		List<Accounts> list = query.getResultList();
		return getAccountDTOList(list);
	}

	public void updateAccounts(AccountsDTO accountDTO) {
		this.update(getAccounts(accountDTO));

	}

	private List<AccountsDTO> getAccountDTOList(List<Accounts> list) {
		List<AccountsDTO> accountsList = new ArrayList<AccountsDTO>();
		for (int i = 0; i < list.size(); i++) {
			Accounts accounts = list.get(i);
			AccountsDTO accountsDto = getAccountsDTO(accounts);
			accountsList.add(accountsDto);
		}
		return accountsList;
	}

	/**
	 * 实体转换DTO
	 * */
	private AccountsDTO getAccountsDTO(Accounts accounts) {
		if (accounts == null) {
			return null;
		}
		AccountsDTO accountsDto = new AccountsDTO();
		if (accounts.getAccId() != null) {
			accountsDto.setAccId(accounts.getAccId());
		}
		if (accounts.getAccountTypeId() != null) {
			accountsDto.setAccTypeID(accounts.getAccountTypeId());
		}
		if (accounts.getCardNo() != null) {
			accountsDto.setCardNo(accounts.getCardNo());
		}
		if (accounts.getInAmt() != null) {
			accountsDto.setInAmt(accounts.getInAmt());
		}
		if (accounts.getOutAmt() != null) {
			accountsDto.setOutAmt(accounts.getOutAmt());
		}
		if (accounts.getUpAmt() != null) {
			accountsDto.setUpAmt(accounts.getUpAmt());
		}
		if (accounts.getDownAmt() != null) {
			accountsDto.setDownAmt(accounts.getDownAmt());
		}
		if (accounts.getStatus() != null) {
			accountsDto.setStatus(accounts.getStatus());
		}
		if (accounts.getAccTName() != null) {
			accountsDto.setAccTypeName(accounts.getAccTName());
		}

		BigDecimal balPoint = NumberUtil.add(NumberUtil.sub(
				accounts.getInAmt(), accounts.getOutAmt()), NumberUtil.sub(
				accounts.getUpAmt(), accounts.getDownAmt()));

		accountsDto.setBalPoint(balPoint);// 余额/积分=(入账总数-出账总数)+(上调-下调)
		BigDecimal totalBalPoint = NumberUtil.add(balPoint, accounts
				.getMainBal());
		accountsDto.setTotalBalPoint(totalBalPoint);
		if (accounts.getStatus() != null) {
			accountsDto.setMainBal(accounts.getMainBal());
		}
		if (accounts.getUpdateTime() != null) {
			accountsDto.setUpdateTime(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm:ss", accounts.getUpdateTime()));
		}
		return accountsDto;

	}

	/**
	 * DTO转换成实体
	 * */
	private Accounts getAccounts(AccountsDTO accountsDTO) {
		if (accountsDTO == null) {
			return null;
		}
		Accounts accounts = new Accounts();
		if (accountsDTO.getAccId() != null) {
			accounts.setAccId(accountsDTO.getAccId());
		}
		if (accountsDTO.getAccTypeID() != null) {
			accounts.setAccountTypeId(accountsDTO.getAccTypeID());
		}
		if (accountsDTO.getAccTypeName() != null) {
			accounts.setAccTName(accountsDTO.getAccTypeName());
		}
		if (accountsDTO.getCardNo() != null) {
			accounts.setCardNo(accountsDTO.getCardNo());
		}
		if (accountsDTO.getInAmt() != null) {
			accounts.setInAmt(accountsDTO.getInAmt());
		}
		if (accountsDTO.getOutAmt() != null) {
			accounts.setOutAmt(accountsDTO.getOutAmt());
		}
		if (accountsDTO.getUpAmt() != null) {
			accounts.setUpAmt(accountsDTO.getUpAmt());
		}
		if (accountsDTO.getDownAmt() != null) {
			accounts.setDownAmt(accountsDTO.getDownAmt());
		}
		if (accountsDTO.getStatus() != null) {
			accounts.setStatus(accountsDTO.getStatus());
		}
		if (accountsDTO.getMainBal() != null) {
			accounts.setMainBal(accountsDTO.getMainBal());
		}
		accounts.setUpdateTime(this.getSysTime());
		return accounts;

	}

	/**
	 *@Title:findByCardNo
	 *@Description:通过卡号查询信息
	 *@param:@param cardNo
	 *@param:@return
	 *@author:王月
	 *@thorws:
	 *2014-5-28
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<AccountsDTO> findByCardNo(String cardNo) {
		String jpl = "select o from Accounts o where o.cardNo like '" + cardNo
				+ "'";
		Query query = super.em.createQuery(jpl);
		List<AccountsDTO> list = new ArrayList<AccountsDTO>();
		List<Accounts> resultList = query.getResultList();
		for (int i = 0; i < resultList.size(); i++) {
			Accounts accounts = resultList.get(i);
			AccountsDTO accountsDTO = getAccountsDTO(accounts);
			list.add(accountsDTO);
		}
		return list;
	}

	/**
	 * @Title:findByCardNos
	 * @Descrition:TODO
	 * @param: @param cardNo 换卡专用，获得账户类型赋值
	 * @param: @return
	 * @return: List<AccountsDTO>
	 * @author: 赵巧鹤
	 * @throws:
	 */
	@SuppressWarnings("unchecked")
	public List<AccountsDTO> findByCardNos(String cardNo) {
		/**
		 * 1、根据卡号查出一个账户集合 2、查询出所有的账户类型 3、遍历账户集合得出每一条账户
		 * */
		String jpl = "select o from Accounts o where o.cardNo like '" + cardNo
				+ "'";
		Query query = super.em.createQuery(jpl);
		List<AccountsDTO> list = new ArrayList<AccountsDTO>();
		List<Accounts> resultList = query.getResultList();

		List<AccKinds> listKinds = accKindsDAO.findByJpl("from AccKinds o");

		for (int i = 0; i < resultList.size(); i++) {
			Accounts accounts = resultList.get(i);
			AccountsDTO accountsDTO = getAccountsDTO(accounts);

			BigDecimal balPoint = NumberUtil.add(NumberUtil.sub(accounts
					.getInAmt(), accounts.getOutAmt()), NumberUtil.sub(accounts
					.getUpAmt(), accounts.getDownAmt()));

			accountsDTO.setBalPoint(balPoint);// 余额/积分=(入账总数-出账总数)+(上调-下调)
			BigDecimal totalBalPoint = NumberUtil.add(balPoint, accounts
					.getMainBal());
			accountsDTO.setTotalBalPoint(totalBalPoint);

			for (int j = 0; j < listKinds.size(); j++) {
				if (listKinds.get(j).getKindId().toString().trim().equals(
						accountsDTO.getAccTypeID().toString().trim())) {
					accountsDTO
							.setAccTypeName((listKinds.get(j).getKindName()));
				}

			}
			list.add(accountsDTO);
		}
		return list;
	}

	public BigDecimal findInitAmtByCardNoForAccKind(String beginCardNo,
			String accKind) {
		String hql = "select o.initAmt from Accounts o where 1=1 and o.cardNo = ? and o.accountTypeId = ?";
		Query query = em.createQuery(hql);
		query.setParameter(0, beginCardNo);
		query.setParameter(1, accKind);

		return (BigDecimal) query.getResultList().get(0);
	}

	/**
	
	* @ClassName: AccountsDaoImpl.java
	
	* @Description: 根据起始卡号、入账总数、积分来更新账户信息
	
	* @author  赵巧鹤
	
	* @date 2014-6-6 下午05:59:45
	
	*/
	public void updateInAmtByCardNo(String beginCardNo, BigDecimal initAmt,
			BigDecimal newPoint) {
		List<AccountsDTO> accList = findAccountsByCardNo(beginCardNo);

		for (AccountsDTO accountsDto : accList) {
			if (accountsDto.getAccTypeID() == 0 && initAmt != null) {
				accountsDto.setInAmt(NumberUtil.add(accountsDto.getInAmt(),
						initAmt));
			}
			if (accountsDto.getAccTypeID() == 1 && newPoint != null) {
				accountsDto.setInAmt(NumberUtil.add(accountsDto.getInAmt(),
						newPoint));
			}
			// ////该方法需优化
			this.updateAccounts(accountsDto);
		}
	}

	/**
	 *@Title:saveAccounts
	 *@Description:新增账户信息--确认入库时调用 默认新增两个账户：0：现金账户 1：积分账户
	 *@param:@param cardNos 账户所属卡号集合
	 *@param:@return
	 *@param:@throws Exception
	 *@return:ReturnDTO 返回ReturnDTO
	 *@author: 謝
	 *@thorws:
	 */
	public ReturnDTO saveAccounts(List<CardNo> cardNos) throws Exception {
		ReturnDTO dto = new ReturnDTO();
		for (CardNo cardNo : cardNos) {
			initAndSaveAccounts(cardNo);
		}
		dto.setFlag(true);
		return dto;
	}

	/**
	 *@Title:initAccounts
	 *@Description:封装账户信息
	 *@param:@param cardNo 账户所属卡号
	 *@param:@param accountType 账户类型 0：现金账户 1：积分账户
	 *@param:@return
	 *@return:Accounts 账户对象
	 *@author:
	 *@thorws:
	 */
	private void initAndSaveAccounts(CardNo cardNo) {
		Accounts cashaccounts = new Accounts();
		BigDecimal initNum = new BigDecimal(0.00);
		//油币账户信息
		String cashAccId = getAccountId(1);
		cashaccounts.setAccId(cashAccId);
		cashaccounts.setAccountTypeId(0);
		cashaccounts.setCardNo(cardNo.getCardNo());
		cashaccounts.setDownAmt(initNum);
		cashaccounts.setUpAmt(initNum);
		cashaccounts.setInAmt(initNum);
		cashaccounts.setOutAmt(initNum);
		cashaccounts.setStatus(0);
		cashaccounts.setUpdateTime(this.getSysTime());
		cashaccounts.setMainBal(initNum);
		this.save(cashaccounts);
		//一般账户信息
		Accounts ybaccount = new Accounts();
		String ybaccountId = getAccountId(2);
		ybaccount.setAccId(ybaccountId);
		ybaccount.setAccountTypeId(1);
		ybaccount.setCardNo(cardNo.getCardNo());
		ybaccount.setDownAmt(initNum);
		ybaccount.setUpAmt(initNum);
		ybaccount.setInAmt(initNum);
		ybaccount.setOutAmt(initNum);
		ybaccount.setStatus(1);
		ybaccount.setUpdateTime(this.getSysTime());
		ybaccount.setMainBal(initNum);
		this.save(ybaccount);
		//积分账户信息
		Accounts jfaccount = new Accounts();
		String jfAccountId = getAccountId(3);
		jfaccount.setAccId(jfAccountId);
		jfaccount.setAccountTypeId(2);
		jfaccount.setCardNo(cardNo.getCardNo());
		jfaccount.setDownAmt(initNum);
		jfaccount.setUpAmt(initNum);
		jfaccount.setInAmt(initNum);
		jfaccount.setOutAmt(initNum);
		jfaccount.setStatus(0);
		jfaccount.setUpdateTime(this.getSysTime());
		jfaccount.setMainBal(initNum);
		this.save(jfaccount);

	}

	/**
	 *@Title:getAccountId
	 *@Description:自动生成账户编号
	 *@param:@return
	 *@return:String 生成的账户编号
	 *@author:
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public String getAccountId(Integer accKind) {
		String accountId = "";
		Integer accKindCond = accKind - 1;
		List<Accounts> string = new ArrayList<Accounts>();
		String sql = "select o from Accounts o where o.accountTypeId = "
				+ accKindCond + " order by o.accId desc";
		Query query = em.createQuery(sql);
		query.setMaxResults(1);
		string = query.getResultList();

		if (string.size() < 1 || string.isEmpty()) {
			accountId = "1";
		} else {
			String max = string.get(0).getAccId();
			accountId = max.substring(2, 15);
			Long newaccountId = Long.valueOf(accountId) + 1;
			accountId = newaccountId.toString();
		}
		while (accountId.length() < 13) {
			accountId = "0" + accountId;
		}
		// 预留两位账户类型编号，作为生成账户编号前缀
		String acckindId = accKind >= 10 ? accKind.toString() : "0"
				+ accKind.toString();
		accountId = acckindId + accountId;
		while (this.find(accountId) != null) {
			Long newaccountId = Long.valueOf(accountId) + 1;
			accountId = newaccountId.toString();
			while (accountId.length() < 15) {
				accountId = "0" + accountId;
			}
		}
		return accountId;
	}


	public List<AccountsDTO> findAccountsByCardNoShow(String cardNoShow) {
		String jpl = "select o from Accounts o where o.cardNo like '%"
				+ cardNoShow + "%'";
		Query query = super.em.createQuery(jpl);
		List<AccountsDTO> list = new ArrayList<AccountsDTO>();
		List<Accounts> resultList = query.getResultList();
		for (int i = 0; i < resultList.size(); i++) {
			Accounts accounts = resultList.get(i);
			AccountsDTO accountsDTO = getAccountsDTO(accounts);
			list.add(accountsDTO);
		}
		return list;
	}

	/**
	 * 判断该账户号是否属于该卡号 根据卡号和账户类型ID进行锁定一个账户 赵巧鹤
	 * */

	public Accounts checkType(String cardNoView, Integer typeId) {
		Accounts acc = new Accounts();
		/**
		 *1. 根据短卡号查到该长卡号 2.根据该卡号 和账户号查该条信息 3.得到这个集合 ，如果这个集合的size<1 证明不存在
		 * */
		CardsDTO cardsDTO = cardsDAO.queryByCardNoShow(cardNoView);
		if (cardsDTO == null) {
			return null;
		}
		String cardNo = cardsDTO.getCardNo();
		List<Accounts> list = this
				.findByJpl("from Accounts o where o.cardNo='" + cardNo
						+ "' and o.accountTypeId = " + typeId);
		
	
		/**
		 * 三目运算 例如 "(a<b)?a:b"是一个"条件表达式",它是这样执行的:如果a<b为真,则表达式取a值,否则取b值.
		 * */
		if (list.size() > 0) {
			return acc = list.get(0);
		} else {
			return null;
		}
		// return list.size()>0?( acc =list.get(0)):null;

	}

	/**
	 * 通过账户号查找账户对象
	 * */
	public AccountsDTO queryAccountsById(String accId) {
		return getAccountsDTO(this.find(accId));
	}

	public Accounts checkType2(String cardNo, Integer typeId) {
		Accounts acc = new Accounts();
		/**
		 *1. 根据短卡号查到该长卡号 2.根据该卡号 和账户号查该条信息 3.得到这个集合 ，如果这个集合的size<1 证明不存在
		 * */
		CardsDTO cardsDTO = cardsDAO.findCards(cardNo);
		if (cardsDTO == null) {
			return null;
		}
		List<Accounts> list = this.findByJpl("from Accounts o where o.cardNo='"
				+ cardNo + "' and o.accountTypeId = " + typeId);
		/**
		 * 三目运算 例如 "(a<b)?a:b"是一个"条件表达式",它是这样执行的:如果a<b为真,则表达式取a值,否则取b值.
		 * */
		if (list.size() > 0) {
			return acc = list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 生成账户信息
	 * 
	 * 换卡时，如果新卡不存在指定账户类型的账户， 则调用本方法，生成该类型账户信息；
	 * 
	 * @Title:createAccounts
	 * @Descrition:TODO
	 * @param: @param accList 原卡号下属账户信息集合： 1.该集合中所有账户信息，为原卡号下属账户信息；
	 *         2.并且该集合是，新卡号下属账户信息中不存在的账户类型的账户信息；
	 * @param: @return
	 * @return: Accounts
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public List<Accounts> createAccounts(List<Accounts> accList) {

		List<Accounts> accountList = new ArrayList<Accounts>();
		for (int i = 0; i < accList.size(); i++) {

			Accounts account = new Accounts();
			Accounts accountNew = accList.get(i);
			// 余额/积分=(入账总数-出账总数)+(上调-下调)
			BigDecimal balPoint = NumberUtil.add(NumberUtil.sub(accountNew
					.getInAmt(), accountNew.getOutAmt()), NumberUtil.sub(
					accountNew.getUpAmt(), accountNew.getDownAmt()));
			BigDecimal totalBalPoint = NumberUtil.add(balPoint, accountNew
					.getMainBal());
			// 新账户的余额
			account.setInAmt(totalBalPoint);
			// 调用生成账户号的方法给与赋值
			account.setAccId(getAccountId(accountNew.getAccountTypeId()));
			account.setAccountTypeId(accountNew.getAccountTypeId());
			account.setCardNo(accountNew.getCardNo());

			account.setStatus(0);
			account.setUpdateTime(this.getSysTime());
			accountList.add(account);
		}

		return accountList;
	}

	/**
	 * @Title:saveAccountss
	 * @Descrition:TODO 换卡的保存方法 需要遍历这个集合保存每一条信息 参数是账户类型不一样的 账户信息集合
	 *                  把这个参数传给生成账户信息的方法里 重新生成账户信息
	 * @param: @param accList
	 * @param: @throws Exception
	 * @return: void
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public void saveAccountss(AccountsDTO nAccountDTO) throws Exception {

		this.save(getAccounts(nAccountDTO));
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
		String sql = "from Accounts a where a.cardNo like '%"
				+ accountsDTO.getCardNo() + "_' and a.accountTypeId='"
				+ accountsDTO.getAccTypeID() + "' and a.status=0 ";
		List<Accounts> list = this.findByJpl(sql);
		Accounts accounts = null;
		if (list != null && list.size() > 0) {
			accounts = list.get(0);
		}
		return getAccountsDTO(accounts);
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
		Accounts acc = this.find(accId);
		return getAccountsDTO(acc);
	}

	/**
	 * 
	 *@Title:updateByDTO
	 *@TODO:通过DTO更新数据
	 *@param:@param accDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void updateByDTO(AccountsDTO accDTO) {
		// TODO Auto-generated method stub
		Accounts acc=getAccounts(accDTO);
		this.update(acc);
	}

	/***
	 * 
	 *@Title:findAccByShowCardNo
	 *@Description:根据显示卡号查询账户信息
	 *@param:@param cardNo
	 *@param:@return
	 *@return:AccountsDaoImpl
	 *@author:井建国
	 *@thorws:
	 *@father:@see com.paySystem.ic.dao.account.AccountsDAO#findAccByShowCardNo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<AccountsDTO> findAccByShowCardNo(String cardNo) {
		String sql = "select o from Accounts o where o.cardNo like '%"+cardNo+"_'";
		Query query = em.createQuery(sql);
		List<Accounts> list = query.getResultList();
		List<AccountsDTO> listDTO = new ArrayList<AccountsDTO>();
		if(list.size()>0){
			listDTO = getAccountDTOList(list);
			return listDTO;
		}else{
			return null;
		}
	}
}
