package com.paySystem.ic.web.action.account;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.account.AccountsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.account.AccountsDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/** 账户信息action */
@Controller("/account/accounts")
@Scope("prototype")
public class AccountsAction extends BaseAction {
	private static final long serialVersionUID = 4852135548955087560L;
	@Resource
	private AccountsService accountsService;
	/** 帐户DTO **/
	private AccountsDTO accountsDTO = new AccountsDTO();

	/**
	 * 列表页面
	 * 
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 * @throws Exception
	 */
	public String list() {
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel()!=0){
			return "intercepthtml";
		}
		else{
			return "list";	
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
		QueryResult<AccountsDTO> result = accountsService.queryAll((accountsDTO.getPage() - 1) * pageNum,
				pageNum, accountsDTO, orderBy);

		List<AccountsDTO> list = result.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		lists = getListString(list);

		PageView<AccountsDTO> pageView = new PageView<AccountsDTO>(accountsDTO.getPage(), result
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
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
			strings.add(Utils.getString(accountsDTO.getOnAccId())); // 账户号
			strings.add(Utils.getString(accountsDTO.getMemId())); // /会员号
			strings.add(NumberUtil.numberFormat("#0.00##", accountsDTO.getOnInAmt())); // 入账总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getOnOutAmt())); // 出账总数
			strings.add(NumberUtil.numberFormat("#0.00##",accountsDTO.getPointsNum())); // 当前积分总额
			strings.add(DateTimeTool.dateFormat(null, accountsDTO.getUpdateTime())); // 更新时间

			lists.add(strings);

		}

		return lists;
	}
	
	/**
	 * @Title:queryAccByCardNo
	 *@ 根据主键查出帐户信息
	 *@data:2014-4-8
	 *@return:String
	 *@author:孟凡岭
	 *@throws Exception 
	 */
	public String queryAccByCardNo() throws Exception {
		AccountsDTO accDTO = accountsService.findById(accountsDTO.getOnAccId());
		List<Object> list = new ArrayList<Object>();
		list.add(accDTO);
		return Utils.printInfo(list);
	}

	public AccountsDTO getAccountsDTO() {
		return accountsDTO;
	}

	public void setAccountsDTO(AccountsDTO accountsDTO) {
		this.accountsDTO = accountsDTO;
	}

}
