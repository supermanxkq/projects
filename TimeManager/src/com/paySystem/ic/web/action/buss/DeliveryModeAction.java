package com.paySystem.ic.web.action.buss;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.base.LogisticsService;
import com.paySystem.ic.service.buss.DeliveryModeService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.LogisticsDTO;
import com.paySystem.ic.web.dto.buss.DeliveryModeDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName: DeliveryModeAction.java
 * @Description:配送方式Action
 * @Author:yanwuyang
 * @Date: 2014-7-20 上午08:45:31
 * @Version: V1.0
 */

@Controller("/buss/deliverymode")
@Scope("prototype")
public class DeliveryModeAction extends BaseAction {

	private static final long serialVersionUID = -6466809377434771466L;

	/**配送方式service*/
	@Resource
	private DeliveryModeService deliveryModeService;

	/**物流service*/
	@Resource
	LogisticsService LogisticsService;

	/**日志service*/
	@Resource
	FunctionsService functionsService;

	/**配送方式DTO*/
	private DeliveryModeDTO deliveryModeDTO = new DeliveryModeDTO();

	/**物流DTO*/
	private LogisticsDTO logisticsDTO = new LogisticsDTO();

	/**
	 * 
	 *@Title:list
	 *@Description:打开list页面
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-7-20上午10:56:19
	 */
	public String list() {
		/** 获取当前操作员信息*/
		UserSession us = Utils.getUserSession();
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUS);
		deliveryModeDTO.setStatus(1);
		/**
		 * 根据操作员不同级别进行界面跳转
		 * 
		 * us.getUserLevel 0 : 平台操作员 1 : 机构操作员 2 : 商户操作员 操作员为平台操作员，进行界面正常跳转；
		 * 操作员为机构或商户操作员，进行拦截提示其不拥有该权限；
		 */
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			return "intercepthtml";
		case 2:
			return "intercepthtml";
		}
		
		return "list";
	}

	/**
	 * 
	 *@Title:jsonPageList
	 *@Description:获取页面数据列表
	 *@return
	 *@throws Exception
	 *@Return:String
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午11:43:24
	 */
	public String jsonPageList() throws Exception {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		/**封装排序方式参数，如界面传递排序方式则*/
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("createTime", "desc");
		}

		/**调用支付接口参数Service，根据条件查询支付接口信息列表及分页信息*/
		QueryResult<DeliveryModeDTO> dtoResult = deliveryModeService.query(
				(deliveryModeDTO.getPage() - 1) * pageNum, pageNum,
				deliveryModeDTO, orderby);

		List<DeliveryModeDTO> payList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		/**遍历DTO对象集合并生成要输出到界面的信息*/
		for (int i = 0; i < payList.size(); i++) {

			List<String> strings = new ArrayList<String>();

			DeliveryModeDTO dto = payList.get(i);

			strings.add(String.valueOf(i + 1));
			strings.add(dto.getDmName());
			strings.add(dto.getLogisticsComName());
			String status = "启用";
			if (dto.getStatus() == 0) {
				status = "禁用";
			} else if (dto.getStatus() == 9) {
				status = "删除";
			}
			strings.add(status);
			strings
					.add(NumberUtil.numberFormat(null, dto.getEnterCost())
							+ "%");
			strings.add(dto.getCashOnDeliverySign() == 0 ? "否" : "是");
			strings.add(dto.getUseCount() + "");
			String operation = "";
			
			/**如果本条记录状态为"删除"状态，则操作员只可以对本条记录进行查看操作*/
			if (Utils.checkPermission("sy-6600-02")) {
				operation += "<a href=buss/deliverymode!checkUI?deliveryModeDTO.dmId="
						+ dto.getDmId()
						+ " title='查看'>"
						+ Globals.IMG_VIEW
						+ "</a>&nbsp;";
			}
			if(dto.getStatus()!=9){
				/**检查是否有机构修改权限，如有则显示"修改"按钮*/
				if (Utils.checkPermission("sy-6600-03")) {
					operation += "<a href=buss/deliverymode!editUI?deliveryModeDTO.dmId="
							+ dto.getDmId()
							+ " title='修改'>"
							+ Globals.IMG_EDIT
							+ "</a>&nbsp;";

				}
				/**检查是否有机构删除权限，如有则显示"删除"按钮*/
				if (Utils.checkPermission("sy-6600-04")) {
					operation += "<a href=javascript:deleteData('buss/deliverymode!delete','"
							+ dto.getDmId()
							+ "') title='删除'>"
							+ Globals.IMG_DELETE
							+ "</a>&nbsp;";
				}
			}
			strings.add(operation);
			lists.add(strings);

		}
		PageView pageView = new PageView(deliveryModeDTO.getPage(), dtoResult
				.getTotalrecord());

		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		/**向前台输出查询数据结果集字符串*/
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 *@Title:addUI
	 *@Description:点击配送方式新增 跳转到新增页面
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-7-20下午04:23:48
	 */
	public String addUI() {

		this.setMethod("addSave");
		BigDecimal decimal = new BigDecimal(0);
		deliveryModeDTO.setEnterCost(decimal);
		deliveryModeDTO.setStatus(1);
		this.getRequest().setAttribute("status", OptionsValue.STATUS_ENABLE_DISABLE);
		this.getRequest().setAttribute("signs", OptionsValue.VISIBLE_STATUS);
		return INPUT;
	}

	/**
	 * 
	 *@Title:addSave
	 *@Description:保存配送方式
	 *@Params:@return
	 *@Return:String
	 *@author:yanwuyang
	 *@Date:2014-7-20下午03:52:31
	 */
	public String addSave() {
		try {
			/** 1.保存配送方式*/
			deliveryModeDTO.setCreateTime(new Date());
			deliveryModeDTO.setUseCount(0);
			deliveryModeService.save(deliveryModeDTO);

			/** 2.保存操作日志信息*/
			functionsService.saveFunction("配送方式管理", 1, "增加配送方式：");
			/** 保存成功提示内容*/
			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			/**保存成功后跳转界面*/
			this.getRequest().setAttribute("url", "buss/deliverymode!list");
			return SUCCESS;
		} catch (Exception e) {
			this.getRequest().setAttribute("result", "保存配送方式信息出错啦!");
			this.getRequest().setAttribute("url", "buss/deliverymode!list");
			return ERROR;
		}
	}

	/**
	 * 
	 *@Title:editUI
	 *@Description:打开编辑页面
	 *@return
	 *@Return:String
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午11:43:43
	 */
	public String editUI() {
		this.setMethod("editSave");

		this.getRequest().setAttribute("status", OptionsValue.STATUS_ENABLE_DISABLE);
		this.getRequest().setAttribute("signs", OptionsValue.VISIBLE_STATUS);
		/**根据Id获取支付参数信息DTO对象*/
		deliveryModeDTO = deliveryModeService.findById(deliveryModeDTO
				.getDmId());

		return INPUT;
	}

	/**
	 * 
	 *@Title:editSave
	 *@Description:编辑保存
	 *@return
	 *@Return:String
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午11:43:55
	 */
	public String editSave() {

		try {
			deliveryModeDTO.setUpdateTime(new Date());
			deliveryModeService.save(deliveryModeDTO);
			functionsService.saveFunction("配送方式管理", 1, "修改配送方式：");
			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			this.getRequest().setAttribute("url", "buss/deliverymode!list");
			return SUCCESS;
		} catch (Exception e) {
			this.getRequest().setAttribute("result", "修改配送方式信息出错啦!");
			this.getRequest().setAttribute("url", "buss/deliverymode!list");
			return ERROR;
		}

	}

	/**
	 * 
	 *@Title:checkUI
	 *@Description:查看
	 *@return
	 *@Return:String
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午11:44:10
	 */
	public String checkUI() {
		this.setMethod("checkUI");
		this.getRequest().setAttribute("status", OptionsValue.STATUS_ENABLE_DISABLE);
		this.getRequest().setAttribute("signs", OptionsValue.VISIBLE_STATUS);
		deliveryModeDTO = deliveryModeService.findById(deliveryModeDTO
				.getDmId());
		return INPUT;
	}

	/**
	 * 
	 *@Title:delete
	 *@Description:删除
	 *@return
	 *@Return:String
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午11:44:16
	 */
	public String delete() {
		try {
			deliveryModeService.delete(Integer.parseInt(this.id));
			functionsService.saveFunction("配送方式管理", 3, "删除配送方式：" + this.id);
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
	 *@Title:getLogisticsList
	 *@Description:获取物流选择信息
	 *@return
	 *@throws Exception
	 *@Return:String
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午11:44:31
	 */
	public String getLogisticsList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 封装排序方式参数，如界面传递排序方式则*/
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("createTime", "desc");
		}

		/**调用支付接口参数Service，根据条件查询支付接口信息列表及分页信息*/
		QueryResult<LogisticsDTO> dtoResult = LogisticsService
				.queryLogisticsByCond((logisticsDTO.getPage() - 1) * pageNum,
						pageNum, logisticsDTO, orderby);

		List<LogisticsDTO> payList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		/** 遍历DTO对象集合并生成要输出到界面的信息*/
		for (int i = 0; i < payList.size(); i++) {
			List<String> strings = new ArrayList<String>();
			LogisticsDTO dto = payList.get(i);
			strings.add("<input type=\"radio\" name=\"selected\" value=\""
					+ dto.getLogistId() + "_" + dto.getLogistName() + "\">");
			strings.add(dto.getLogistId().toString());
			strings.add(dto.getLogistName());
			strings.add(dto.getUrl());
			strings.add(dto.getCreateTime().toString());
			lists.add(strings);
		}
		PageView pageView = new PageView(logisticsDTO.getPage(), dtoResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		/** 向前台输出查询数据结果集字符串*/
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 *@Title:checkSameName
	 *@Description:检查配送名称是否已存在
	 *@return
	 *@Return:String
	 *@Author:yanwuyang
	 *@Date:2014-8-4下午10:02:48
	 */
	public String checkName() {
		ReturnDTO data = new ReturnDTO();
		boolean flag = deliveryModeService.checkName(deliveryModeDTO
				.getDmName());
		data.setFlag(flag);
		return Utils.printInfo(data);
	}

	public DeliveryModeDTO getDeliveryModeDTO() {
		return deliveryModeDTO;
	}

	public void setDeliveryModeDTO(DeliveryModeDTO deliveryModeDTO) {
		this.deliveryModeDTO = deliveryModeDTO;
	}

	public LogisticsDTO getLogisticsDTO() {
		return logisticsDTO;
	}

	public void setLogisticsDTO(LogisticsDTO logisticsDTO) {
		this.logisticsDTO = logisticsDTO;
	}

}
