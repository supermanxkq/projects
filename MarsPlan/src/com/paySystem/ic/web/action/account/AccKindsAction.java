package com.paySystem.ic.web.action.account;

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
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.account.AccKindsDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 
* @ClassName: AccKindsAction
* @Description: 账户类型管理
* @author lily
* @date 2013-12-14 下午02:45:20
* @version V1.0
 */
@Controller("/account/acckinds")
@Scope("prototype")
public class AccKindsAction extends BaseAction {
	private static final long serialVersionUID = 1449701366913329604L;
	@Resource
	AccKindsService accKindsService;
	@Resource 
	FunctionsService functionsService;
	
	private AccKindsDTO accKindsDTO = new AccKindsDTO();

	
	public AccKindsDTO getAccKindsDTO() {
		return accKindsDTO;
	}

	public void setAccKindsDTO(AccKindsDTO accKindsDTO) {
		this.accKindsDTO = accKindsDTO;
	}

	/**
	 * 
	* @Title: list
	* @Description: 加载列表页面
	* @return String    返回类型
	* @throws
	 */
	public String list() {
		
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel()!=2){	
			try {
				this.getRequest().setAttribute("accDeptTypeValues",
						OptionsValue.ACC_DEPTTYPE);
				this.getRequest().setAttribute("signValues", OptionsValue.ACCKIND_SIGN);// 账户类型标志
				this.getRequest().setAttribute("withDraType", OptionsValue.VISIBLE_STATUS);// 提现类型标志
				this.getRequest().setAttribute("transAccType", OptionsValue.VISIBLE_STATUS);// 转账类型标志
				this.getRequest().setAttribute("consType", OptionsValue.VISIBLE_STATUS);// 消费类型标志
				this.getRequest().setAttribute("rechargeType", OptionsValue.VISIBLE_STATUS);// 提现类型标志
				this.getRequest().setAttribute("statusValues",
						OptionsValue.ACC_KIND_STATUS);// 状态
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
			return "list";
		}else{
			return "intercepthtml";
		}
	}   

	/**
	 * 
	* @Title: jsonPageList
	* @Description: 异步获取列表数据
	* @return String 
	* @throws
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
		QueryResult<AccKindsDTO> terResult = accKindsService.queryAll(
				(accKindsDTO.getPage() - 1) * pageNum, pageNum, accKindsDTO,
				orderBy);
		List<AccKindsDTO> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			// 向页面赋值
			AccKindsDTO accKindsDTO = list.get(i);
			// 封装json串儿
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(accKindsDTO.getKindName())); // 账户名称
			strings.add(Utils.getString(accKindsDTO.getKindId())); //账户编号
			strings.add(Utils.getString(Utils.getOptionsIntegerName(
					OptionsValue.ACCKIND_SIGN, accKindsDTO.getAccSign()))); //账户类型标志
			strings.add(Utils.getString(Utils.getOptionsIntegerName(
					OptionsValue.ACC_KIND_STATUS, accKindsDTO.getStatus()))); //状态
			strings.add(Utils.getString(accKindsDTO.getUpdateTime())); // 更新时间
			String operation = "";
			if(Utils.checkPermission("sy-1502-01")){
				operation += "<a href=javascript:loadData('"+accKindsDTO.getKindId()+"','"+1+"') title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
			}
			if(accKindsDTO.getStatus()!=2){
				if(Utils.checkPermission("sy-1502-03")){
					operation += "<a href=javascript:loadData('"+accKindsDTO.getKindId()+"','"+3+"') title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
				}
				//if(Utils.checkPermission("sy-1502-04")){
					//operation += "<a href=javascript:deleteData('account/acckinds!delete','"+accKindsDTO.getKindId()+"') title='删除'>"+Globals.IMG_DELETE+"</a>&nbsp;";
				//}
			}
			strings.add(operation);
			lists.add(strings);

		}
		PageView pageView = new PageView(accKindsDTO.getPage(), terResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 * 
	* @Title: addUI
	* @Description: 加载添加账户类型页面 
	* @return String 
	* @throws
	 */
	public String addUI() {
		this.setMethod("addSave");
		this.objResult = accKindsDTO;
		return "objResult";
	}

	/**
	 * 
	* @Title: addSave
	* @Description: 保存新增的账户类型
	* @param accKindsDTO
	* @return String    
	* @throws
	 */
	public String addSave() {
		try {
			accKindsService.saveAccKinds(accKindsDTO);
//			functionsService.saveFunction("账户类型管理", 1,
//			 "增加账户类型："+terminals.getTermId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 * 
	* @Title: editUI
	* @Description: 加载账户类型修改页面
	* @param KindId
	* @return String    
	* @throws
	 */
	public String editUI() {
		this.setMethod("editSave");
		try {
			accKindsDTO = accKindsService.findAccKinds(accKindsDTO.getKindId());
			if(accKindsDTO!=null){
				this.objResult = accKindsDTO;
				return "objResult";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "error";
		
	}
	/**
	 * 
	* @Title: editSave
	* @Description: 修改账户类型信息
	* @return String
	* @throws
	 */
	public String editSave() {
		try {
			//accKindsDTO = accKindsService.find(accKindsDTO.getKindId());
			if(accKindsDTO!=null){
				accKindsService.update(accKindsDTO);
				this.ajaxResult = "ajaxsuccess";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
	/**
	 * 
	* @Title: delete
	* @Description: 删除操作方法
	* @param @return    kindId
	* @return String    
	* @throws
	 */
	public String delete() {
		try {
			accKindsService.deleteAccKinds(this.getId());
			functionsService.saveFunction("账户类型管理", 3, "删除账户类型："+this.getId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
}
