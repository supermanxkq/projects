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
import com.paySystem.ic.service.account.AccKindsService;
import com.paySystem.ic.service.account.AccountsRecordService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.account.AccountsRecordDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * 
 * @ClassName:AccountsRecordAction
 * @TODO:帐户操作记录
 * @date: 2014-4-8上午10:50:08
 * @author: 孟凡岭
 * @version: V1.0
 */
@Controller("/account/accRecord")
@Scope("prototype")
public class AccountsRecordAction extends BaseAction {
	/** 序列化 **/
	private static final long serialVersionUID = 1L;
	/** 帐户查操作记录DTO **/
	private AccountsRecordDTO recordDTO = new AccountsRecordDTO();
	/** 帐户查看Service **/
	@Resource
	private AccountsRecordService accountsRecordService;
	@Resource
	private AccKindsService accKindsService;
	

	/**
	 * 
	 *@Title:jsonPageList
	 *@TODO:查询方法
	 *@data:2014-4-10
	 *@return:String
	 *@author:孟凡岭
	 *@thorws:
	 */
	public String jsonPageList() throws Exception {
		/*** 查询结果排序参数设定 */

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/*** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			/** 如果页面没有要求排序方式，则设置创建时间排序 **/
			orderby.put("createTime", "desc");
		}
		/*** 返回结果 */
		QueryResult<AccountsRecordDTO> query = accountsRecordService
				.queryResult((recordDTO.getPage() - 1) * pageNum, pageNum,
						recordDTO, orderby);
		List<AccountsRecordDTO> list = query.getResultlist();
		List<List<String>> listt = new ArrayList<List<String>>();
		List<OptionsInteger> accTypeList=accKindsService.findType();;
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				AccountsRecordDTO a = list.get(i);
				List<String> strings = new ArrayList<String>();
				strings.add(String.valueOf(i + 1));
				strings.add(a.getCardNo());
				strings.add(a.getAmount().toString());
				strings.add(Utils.getOptionsIntegerName(accTypeList, a.getAccTypeId()));
				strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", a
						.getCreateTime()));
				strings.add(a.getOperatorId());
				strings.add(Utils.getOptionsIntegerName(
						OptionsValue.ACC_OPERTYPE, a.getOperateType()));
				listt.add(strings);
			}
		}
		PageView pageView = new PageView(recordDTO.getPage(), query
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(listt, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}


	public String list() {
		this.getRequest().setAttribute("operType", OptionsValue.LONG_SHORT);
		return "list";
	}

	public AccountsRecordDTO getRecordDTO() {
		return recordDTO;
	}

	public void setRecordDTO(AccountsRecordDTO recordDTO) {
		this.recordDTO = recordDTO;
	}

}
