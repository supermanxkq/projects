package com.paySystem.ic.web.action.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.base.MerStoreApplyService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.base.MerStoreApplyDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 
 * @ProjectName: backomall
 * @ClassName: AuditMerchantsAction
 * @Description: 批量批量修改 修改商户状态，批量审核Action
 * @date: 2014-11-17 下午02:56:06
 * @author: 郭营
 * @version: V1.0
 */

@Controller("/base/auditMerchants")
@Scope("prototype")
public class MerStoreApplyAction extends BaseAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	MerStoreApplyService merStoreApplyService;

	@Resource
	FunctionsService functionsService;

	MerStoreApplyDTO merStoreApplyDTO = new MerStoreApplyDTO();

	public MerStoreApplyDTO getMerStoreApplyDTO() {
		return merStoreApplyDTO;
	}

	public void setMerStoreApplyDTO(MerStoreApplyDTO merStoreApplyDTO) {
		this.merStoreApplyDTO = merStoreApplyDTO;
	}

	/**
	 * 
	 *@Title: list
	 *@Description: 页面数据加载
	 *@Params: @return
	 *@Return: String
	 *@author: 郭营
	 *@Date: 2014-11-17下午03:05:31
	 */
	public String list() {
		UserSession us = Utils.getUserSession();
		this.getRequest().setAttribute("applyStatus",
				OptionsValue.MERSTOREAPPLY_STATUS);// 状态
		if (us.getUserLevel() == 0) {
			return "list";
		} else if (us.getUserLevel() == 1) {
			return "intercepthtml";
		} else if (us.getUserLevel() == 2) {
			return "intercepthtml";
		}
		return ERROR;
	}

	/**
	 * 
	 *@Title: jsonPageList
	 *@Description: 查询列表
	 *@Params: @return
	 *@Params: @throws Exception
	 *@Return: String
	 *@author: 郭营
	 *@Date: 2014-11-21上午09:21:05
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 设置排序方式 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("applyId", "asc");
		}
		/** 调用service层的借口查询出结果集 */
		QueryResult<MerStoreApplyDTO> queryResult = merStoreApplyService
				.queryAll((merStoreApplyDTO.getPage() - 1) * pageNum, pageNum,
						merStoreApplyDTO, orderBy);
		List<MerStoreApplyDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		lists = getListString(list);
		PageView pageView = new PageView(merStoreApplyDTO.getPage(),
				queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 *@Title: getListString
	 *@Description: 页面展示的封装
	 *@Params: @param list
	 *@Params: @return
	 *@Return: List<List<String>>
	 *@author: 郭营
	 *@Date: 2014-9-18上午09:05:47
	 */
	public List<List<String>> getListString(List<MerStoreApplyDTO> list) {
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			merStoreApplyDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add("<input type='checkbox'  value='"
					+ merStoreApplyDTO.getApplyId() + "' name='checkbox'>");
			strings.add("" + (i + 1));
			strings.add(merStoreApplyDTO.getCardNo());
			strings.add(merStoreApplyDTO.getName());
			strings.add(merStoreApplyDTO.getEmail());
			strings.add(merStoreApplyDTO.getPhone());
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.MERSTOREAPPLY_STATUS, merStoreApplyDTO.getStatus()));
			lists.add(strings);
		}
		return lists;
	}

	/**
	 * 
	 *@Title: MerApplyAction
	 *@Description:批量更审核
	 *@Params: @return
	 *@Params: @throws Exception
	 *@Return: String
	 *@author: 郭营
	 *@Date: 2014-11-21上午09:22:08
	 */
	public String MerApplyAction() throws Exception {
		String modfiyValue = this.getRequest().getParameter("modfiyValue");
		String status = this.getRequest().getParameter("status");
		String backString=  merStoreApplyService.MerApplyAction(modfiyValue, status);
		return Utils.printInfo(backString);

	}

	
	

	
}
