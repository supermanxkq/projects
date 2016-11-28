package com.paySystem.ic.web.action.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.account.AccKindsService;
import com.paySystem.ic.service.account.AccountsRecordService;
import com.paySystem.ic.service.account.AccountsService;
import com.paySystem.ic.service.card.CardsService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.account.AccountsDTO;
import com.paySystem.ic.web.dto.account.AccountsRecordDTO;
import com.paySystem.ic.web.dto.card.CardsDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

/** 账户信息action */
@Controller("/account/accounts")
@Scope("prototype")
public class AccountsAction extends BaseAction {
	private static final long serialVersionUID = 4852135548955087560L;
	@Resource
	private AccountsService accountsService;
	@Resource
	private AccountsRecordService accountsRecordService;
	@Resource
	private AccKindsService accKindsService;
	@Resource
	private CardsService cardsService;
	@Resource
	private MemberService memberService;
	// private Double totalCashAcc = 34.342;
	/** 帐户DTO **/
	private AccountsDTO accountsDTO = new AccountsDTO();
	private AccountsRecordDTO recordDTO=new AccountsRecordDTO();
	/** 帐户操作类型 **/
	private String operType;
	/** 处理的金额 **/
	private String fund;

	/**
	 * 列表页面
	 * 
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 * @throws Exception
	 */
	public String list() {
		UserSession us = Utils.getUserSession();
		String param = this.getRequest().getParameter("type");
		if(us.getUserLevel()!=2){
			if (param != null) {
				List<OptionsInteger> accType = accKindsService.findType();
				this.getRequest().setAttribute("accType", accType);
				this.getRequest()
						.setAttribute("longShort", OptionsValue.LONG_SHORT);
				return "longShort";
			} else {
				return "list";
			}
		}
		else{
			return "intercepthtml";	
		}
	}

	/**
	 * 异步获取列表数据
	 * 
	 * @version 2011-9-8 下午08:51:04
	 * @return
	 * @throws Exception
	 */
	public String jsonPageList() throws Exception {

		/*
		 * UserSession us = Utils.getUserSession(); String ownedOrgId = null;
		 * String ownedMerId = null; if (us.getUserLevel() != null) { if
		 * (us.getOrganId() != null) { ownedOrgId = us.getOrganId();
		 * accountsDTO.setOwnedOrgId(ownedOrgId); } if (us.getMerId() != null) {
		 * ownedOrgId = us.getPreOrganId(); ownedMerId = us.getMerId();
		 * accountsDTO.setOwnedOrgId(ownedOrgId);
		 * accountsDTO.setOwnedMerId(ownedMerId); } }
		 */

		QueryResult<AccountsDTO> terResult = queryResult(
				(accountsDTO.getPage() - 1) * pageNum, pageNum);

		List<AccountsDTO> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		lists = getListString(list);
		// this.setTotalCashAcc(111111.44);

		PageView pageView = new PageView(accountsDTO.getPage(), terResult
				.getTotalrecord());
		// ListInfoDTO listInfoDTO = new ListInfoDTO(lists,
		// getPageHTML(pageView),totalCashAcc.toString());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 * 导出功能
	 * */
	public String export() {
		String title = "账户表";
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("账户号");
			headers.add("卡号");
			headers.add("账户类型");
			headers.add("入账总数");
			headers.add("出账总数");
			headers.add("上调总数");
			headers.add("下调总数");
			headers.add("余额/积分");
			headers.add("主账户余额");
			headers.add("总余额");
			headers.add("更新时间");

			QueryResult<AccountsDTO> terResult = queryResult(-1, -1);
			List<AccountsDTO> list = terResult.getResultlist();
			List<List<String>> lists = new ArrayList<List<String>>();
			lists = getListStringExcel(list);
			str = util.createXls(headers, lists, title, this.getResponse());

			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得查询结果
	 * 
	 * @param page
	 * @param pageNum
	 * @return
	 */
	public QueryResult<AccountsDTO> queryResult(int page, int pageNum) {
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("updateTime", "desc");
		}

		// List<Object> params = new ArrayList<Object>();

		QueryResult<AccountsDTO> terResult = accountsService.queryAll(page,
				pageNum, accountsDTO, orderBy);
		return terResult;
	}

	/**
	 * 封装字符串,用于前台数据显示
	 * 
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	public List<List<String>> getListString(List<AccountsDTO> list) throws Exception {
		List<List<String>> lists = new ArrayList<List<String>>();

		for (int i = 0; i < list.size(); i++) {
			// 向页面赋值
			AccountsDTO accountsDTO = list.get(i);
			// 封装json串儿
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(accountsDTO.getAccId())); // 账户号
			strings.add(Utils.getString(accountsDTO.getCardNo())); // /卡号
			// strings.add(Utils.getString(accountsDTO.getAccTypeName())); //
			// 账户类型
			strings.add(Utils.getString(accKindsService.findAccKinds(accountsDTO.getAccTypeID()).getKindName()));
			// NumberUtil.numberFormat("#0.00##",
			strings.add(NumberUtil.numberFormat("#0.00##", accountsDTO.getInAmt())); // 入账总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getOutAmt())); // 出账总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getUpAmt())); // 上调总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getDownAmt())); // 下调总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getBalPoint())); // 余额/积分
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getMainBal())); // 主账户余额
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getTotalBalPoint())); // 总余额
			strings.add(accountsDTO.getUpdateTime()); // 更新时间

			lists.add(strings);

		}

		return lists;
	}
	
	/**
	 * 封装字符串,用于导出数据
	 * 
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	public List<List<String>> getListStringExcel(List<AccountsDTO> list) throws Exception {
		List<List<String>> lists = new ArrayList<List<String>>();

		for (int i = 0; i < list.size(); i++) {
			// 向页面赋值
			AccountsDTO accountsDTO = list.get(i);
			// 封装json串儿
			List<String> strings = new ArrayList<String>();
			strings.add(Utils.getString(accountsDTO.getAccId())); // 账户号
			strings.add(Utils.getString(accountsDTO.getCardNo())); // /卡号
			// strings.add(Utils.getString(accountsDTO.getAccTypeName())); //
			// 账户类型
			strings.add(Utils.getString(accKindsService.findAccKinds(accountsDTO.getAccTypeID()).getKindName()));
			// NumberUtil.numberFormat("#0.00##",
			strings.add(NumberUtil.numberFormat("#0.00##", accountsDTO.getInAmt())); // 入账总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getOutAmt())); // 出账总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getUpAmt())); // 上调总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getDownAmt())); // 下调总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getBalPoint())); // 余额/积分
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getMainBal())); // 主账户余额
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getTotalBalPoint())); // 总余额
			strings.add(Utils.getString(accountsDTO.getUpdateTime())); // 更新时间

			lists.add(strings);

		}

		return lists;
	}

	/***
	 * 
	 *@Title:longShortFund
	 *@TODO:帐户长短款处理
	 *@data:2014-4-8
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void longShortFund() {
		AccountsRecordDTO arDTO = new AccountsRecordDTO();
		AccountsDTO accDTO = accountsService.findById(accountsDTO.getAccId());
		/** 判断操作类型 0:上调 1:下调 **/
		if (operType.equals("0")) {
			accDTO.setUpAmt(accDTO.getUpAmt().add(new BigDecimal(fund)));
			accountsService.updateByDTO(accDTO);
		} else if (operType.equals("1")) {
			accDTO.setDownAmt(accDTO.getDownAmt().add(new BigDecimal(fund)));
			accountsService.updateByDTO(accDTO);
		}
		arDTO.setAmount(new BigDecimal(fund));
		arDTO.setCardNo(accDTO.getCardNo());
		arDTO.setCreateTime(accountsRecordService.getSysTime());
		arDTO.setOperateType(Integer.valueOf(operType));
		arDTO.setAccTypeId(accDTO.getAccTypeID());
		arDTO.setOperatorId(Utils.getUserSession().getUserName());
		arDTO.setOrgId(Utils.getUserSession().getOrganId());
		accountsRecordService.saveByDTO(arDTO);
	}

	/**
	 * 根据卡号查出帐户信息
	 * 
	 * @Title:queryAccByCardNo
	 *@TODO:TODO
	 *@data:2014-4-8
	 *@return:String
	 *@author:孟凡岭
	 *@thorws:
	 */
	public String queryAccByCardNo() {
		AccountsDTO accDTO = accountsService.queryAccByCardNo(accountsDTO);
		CardsDTO cardsDTO = null;
		MemberDTO memberDTO = null;
		cardsDTO = cardsService.findByCardNo(accountsDTO.getCardNo());
		if(cardsDTO!=null){
			if (cardsDTO.getHoldmemId() != null) {
				memberDTO = memberService.findDTO(cardsDTO.getHoldmemId());
			}
		}
		List<Object> list = new ArrayList<Object>();
		list.add(accDTO);
		list.add(memberDTO);
		return Utils.printInfo(list);
	}

	public AccountsDTO getAccountsDTO() {
		return accountsDTO;
	}

	public void setAccountsDTO(AccountsDTO accountsDTO) {
		this.accountsDTO = accountsDTO;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public AccountsRecordDTO getRecordDTO() {
		return recordDTO;
	}

	public void setRecordDTO(AccountsRecordDTO recordDTO) {
		this.recordDTO = recordDTO;
	}

}
