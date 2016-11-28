package com.paySystem.ic.web.action.stock;

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
import com.paySystem.ic.service.stock.MessServParamConfigService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.MessServParamConfigDTO;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigAction
 * @Description:短信服务器配置控制类
 * @date: 2014-7-22下午03:00:46
 * @author: 徐凯强
 * @version: V1.0
 */
@Controller("/stock/messServParamConfig")
@Scope("prototype")
public class MessServParamConfigAction extends BaseAction implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	MessServParamConfigService messServParamConfigService;

	/** 实例化数据传输对象 */
	private MessServParamConfigDTO messServParamConfigDTO = new MessServParamConfigDTO();
	private ReturnDTO returnDTO = new ReturnDTO();

	public ReturnDTO getReturnDTO() {
		return returnDTO;
	}

	public void setReturnDTO(ReturnDTO returnDTO) {
		this.returnDTO = returnDTO;
	}

	public MessServParamConfigDTO getMessServParamConfigDTO() {
		return messServParamConfigDTO;
	}

	public void setMessServParamConfigDTO(
			MessServParamConfigDTO messServParamConfigDTO) {
		this.messServParamConfigDTO = messServParamConfigDTO;
	}

	/**
	 *@Title:list
	 *@Description:list方法
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-22下午03:02:16
	 */
	public String list() {
		/** 设置提交时调用的方法addSave */
		this.setMethod("addSave");
		/** 传递服务器名称参数 */
		this.getRequest().setAttribute("severNameValue",
				OptionsValue.SMS_SERVERNAME);
		this.getRequest().setAttribute("serverStatus",
				OptionsValue.SMS_SERVERSTATUS);
		return "list";
	}

	/**
	 *@Title:jsonPageList
	 *@Description:异步加载数据
	 *@throws Exception
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23下午05:08:26
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("mspId", "desc");
		}
		// 返回结果
		QueryResult<MessServParamConfigDTO> queryResult = messServParamConfigService
				.queryAll((messServParamConfigDTO.getPage() - 1) * pageNum,
						pageNum, messServParamConfigDTO, orderby);
		List<MessServParamConfigDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			MessServParamConfigDTO messServParamConfigDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			/** 编号 */
			strings.add(Utils.getString(messServParamConfigDTO.getMspId()));
			/** 服务商编号、名称 */
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.SMS_SERVERNAME, messServParamConfigDTO
							.getServiceId()));
			/** 通道类型 */
			if (messServParamConfigDTO.getParamType() == 1) {
				strings.add("普通短信服务器");
			} else {
				strings.add("验证码短信服务器 ");
			}
			/** 账户号 */
			strings.add(messServParamConfigDTO.getAccount());
			/** 短信服务器状态1为开启0为停用 */
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.SMS_SERVERSTATUS, messServParamConfigDTO
							.getStatus()));
			String operation = "";
			if (Utils.checkPermission("sy-1101-03")) {
				operation += "<a href=stock/messServParamConfig!editUI?messServParamConfigDTO.mspId="
						+ messServParamConfigDTO.getMspId()
						+ " title='修改'>"
						+ Globals.IMG_EDIT + "</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(messServParamConfigDTO.getPage(),
				queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:addSave
	 *@Description:保存服务器配置信息
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23上午09:14:24
	 */
	public String addSave() {
		try {
			messServParamConfigService
					.saveMessServParamConfig(messServParamConfigDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getRequest().setAttribute("url", "stock/messServParamConfig!list");
		this.getRequest().setAttribute("result", "添加成功！");
		return "success";
	}

	/**
	 *@Title:updateData
	 *@Description:更新服务器配置信息
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23上午09:17:43
	 */
	public String updateData() {
		try {
			messServParamConfigService
					.updateMessServParamConfig(messServParamConfigDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getRequest().setAttribute("result", "修改成功!");
		this.getRequest().setAttribute("url", "stock/messServParamConfig!list");
		return "success";
	}

	/**
	 *@Title:editUi
	 *@Description:跳转到编辑界面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:10:37
	 */
	public String editUI() {
		this.setMethod("updateData");
		/** 传递服务器名称参数 */
		this.getRequest().setAttribute("severNameValue",
				OptionsValue.SMS_SERVERNAME);
		this.getRequest().setAttribute("serverStatus",
				OptionsValue.SMS_SERVERSTATUS);
		messServParamConfigDTO = messServParamConfigService
				.findConfigDTO(messServParamConfigDTO);
		return "input";
	}

	/**
	 *@Title:addUI
	 *@Description:跳转到添加界面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:10:37
	 */
	public String addUI() {
		this.setMethod("addSave");
		/** 传递服务器名称参数 */
		this.getRequest().setAttribute("severNameValue",
				OptionsValue.SMS_SERVERNAME);
		this.getRequest().setAttribute("serverStatus",
				OptionsValue.SMS_SERVERSTATUS);
		/** 服务器的默认的选择状态为停用 */
		messServParamConfigDTO.setStatus(0);
		messServParamConfigDTO.setStatus1(0);
		return "input";
	}

	/**
	 *@Title:testShortMessServConfig
	 *@Description:测试普通短信服务器配置
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-25下午03:11:41
	 */
	public String testShortMessServCon() {
		messServParamConfigService
				.testShortMessServConfig(messServParamConfigDTO);
		returnDTO.setFlag(true);
		return Utils.printInfo(returnDTO);
	}

	/**
	 *@Title:testValidateMessServCon
	 *@Description:测试验证码短信服务器配置
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-29下午04:59:58
	 */
	public String testValidateMessServCon() {
		messServParamConfigService
				.testValidateMessServCon(messServParamConfigDTO);
		returnDTO.setFlag(true);
		return Utils.printInfo(returnDTO);
	}

	/**
	 *@Title:changeStatus
	 *@Description:改变其他服务器的状态为停用
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-29上午10:35:01
	 */
	public String changeStatus() {
		returnDTO = messServParamConfigService.changeStatus();
		return Utils.printInfo(returnDTO);
	}

	/**
	 *@Title:changeShortMessStatus
	 *@Description：改变其他普通服务器的状态为停用
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-30上午10:26:43
	 */
	public String changeShortMessStatus() {
		returnDTO = messServParamConfigService.changeShortMessStatus();
		return Utils.printInfo(returnDTO);
	}

	/**
	 *@Title:changeValidateMessStatus
	 *@Description：改变其他验证码服务器的状态为停用
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-30上午10:26:43
	 */
	public String changeValidateMessStatus() {
		returnDTO = messServParamConfigService.changeValidateMessStatus();
		return Utils.printInfo(returnDTO);
	}

	/**
	 *@Title:sendMessageByParamType
	 *@Description:外部调用发送短信
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-8-5下午02:39:46
	 */
	public String sendMessageByParamType(
			MessServParamConfigDTO messServParamConfigDTO) {
		returnDTO = messServParamConfigService
				.sendMessageByParamType(messServParamConfigDTO);
		return Utils.printInfo(returnDTO);
	}
}
