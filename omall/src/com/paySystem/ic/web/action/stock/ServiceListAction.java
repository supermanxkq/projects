package com.paySystem.ic.web.action.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mysql.jdbc.Util;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.stock.ServiceListService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ServiceListDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omall
 * @ClassName:ServiceListAction
 * @Description:服务清单控制类
 * @date: 2014-7-22下午03:00:46
 * @author: 徐凯强
 * @version: V1.0
 */
/**
 * @ProjectName:omall
 * @ClassName:ServiceListAction
 * @Description:TODO
 * @date: 2014-7-31上午09:54:37
 * @author: 徐凯强
 * @version: V1.0
 */
@Controller("/stock/servicelist")
@Scope("prototype")
public class ServiceListAction extends BaseAction implements Serializable {

	private static final long serialVersionUID = 1L;
	@Resource
	private ServiceListService serviceListService;

	/** 服务清单DTO */
	private ServiceListDTO serviceListDTO = new ServiceListDTO();

	public ServiceListDTO getServiceListDTO() {
		return serviceListDTO;
	}

	public void setServiceListDTO(ServiceListDTO serviceListDTO) {
		this.serviceListDTO = serviceListDTO;
	}

	/**
	 *@Title:list
	 *@Description:list方法
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-22下午03:02:16
	 */
	public String list() {
		UserSession userSession=Utils.getUserSession();
		if(userSession.getUserLevel()==0){
		/** 设置提交时调用的方法addSave */
		this.setMethod("addSave");
		this.getRequest().setAttribute("publishValue",
				OptionsValue.PUBLISHSTATUS);
		this.getRequest().setAttribute("serviceType", OptionsValue.SERVICETYPE);
		return "list";
		}else{			
			return "intercepthtml";
		}
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
		UserSession userSession=Utils.getUserSession();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("servId", "desc");
		}
		/**返回结果*/
		QueryResult<ServiceListDTO> queryResult = serviceListService.queryAll(
				(serviceListDTO.getPage() - 1) * pageNum, pageNum,
				serviceListDTO, orderby);
		List<ServiceListDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			ServiceListDTO serviceListDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			/** 标题 */
			strings.add(Utils.getString(serviceListDTO.getName()));
			/** 服务类型 */
			strings.add(Utils.getOptionsIntegerName(OptionsValue.SERVICETYPE,
					serviceListDTO.getServType()));
			/** 发布状态 */
			strings.add(Utils.getOptionsIntegerName(OptionsValue.PUBLISHSTATUS,
					serviceListDTO.getStatus()));
			/** 添加人 */
			strings.add(serviceListDTO.getAddMen());
			/** 添加日期 */
			strings.add(DateTimeTool
					.dateFormat("", serviceListDTO.getAddTime()));
			/** 更新日期 */
			strings.add(DateTimeTool
					.dateFormat("", serviceListDTO.getUpdateTime()));
			String operation = "";
			if (serviceListDTO.getStatus() != 9) {
				if(serviceListDTO.getStatus()==1){
					if (Utils.checkPermission("sy-6801-02")) {
						operation += "<a href=stock/servicelist!checkUI?serviceListDTO.servId="
								+ serviceListDTO.getServId()
								+ " title='查看'>"
								+ Globals.IMG_VIEW + "</a>&nbsp;";
					}
					if (Utils.checkPermission("sy-6801-04")) {
						operation += "<a href=javascript:deleteData('stock/servicelist!delete','"
								+ serviceListDTO.getServId().toString()
								+ "') title='删除'>"
								+ Globals.IMG_DELETE
								+ "</a>&nbsp;";
					}	
				}
				if(serviceListDTO.getStatus()==0){
					if (Utils.checkPermission("sy-6801-02")) {
						operation += "<a href=stock/servicelist!checkUI?serviceListDTO.servId="
								+ serviceListDTO.getServId()
								+ " title='查看'>"
								+ Globals.IMG_VIEW + "</a>&nbsp;";
					}
					if (Utils.checkPermission("sy-6801-03")) {
						operation += "<a href=stock/servicelist!editUI?serviceListDTO.servId="
								+ serviceListDTO.getServId()
								+ " title='修改'>"
								+ Globals.IMG_EDIT + "</a>&nbsp;";
					}
					
					if(userSession.getUserLevel()==0&&serviceListDTO.getStatus() == 0){//平台具有审核功能
						operation += "<a href=javascript:verifyData('stock/servicelist!updateStatus','"
								+ serviceListDTO.getServId().toString()
								+ "') title='审核'>"
								+ Globals.IMG_AUDIT
								+ "</a>&nbsp;";
					}
					if (Utils.checkPermission("sy-6801-04")) {
						operation += "<a href=javascript:deleteData('stock/servicelist!delete','"
								+ serviceListDTO.getServId().toString()
								+ "') title='删除'>"
								+ Globals.IMG_DELETE
								+ "</a>&nbsp;";
					}
					
				}
				
			} else {
				if (Utils.checkPermission("sy-6801-02")) {
					operation += "<a href=stock/servicelist!checkUI?serviceListDTO.servId="
							+ serviceListDTO.getServId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}
			}
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(serviceListDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:addSave
	 *@Description:保存服务清单实体类
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23上午09:14:24
	 */
	public String addSave() {
		try {
			serviceListService.saveServiceList(serviceListDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getRequest().setAttribute("url", "stock/servicelist!list");
		this.getRequest().setAttribute("result", "添加成功！");
		return "success";
	}

	/**
	 *@Title:updateData
	 *@Description:更新服务清单
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23上午09:17:43
	 */
	public String updateData() {
		try {
			serviceListService.updateServiceList(serviceListDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getRequest().setAttribute("result", "修改成功!");
		this.getRequest().setAttribute("url", "stock/servicelist!list");
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
		serviceListDTO = serviceListService.findServiceListDTO(serviceListDTO
				.getServId());
		this.getRequest().setAttribute("publishValue",
				OptionsValue.PUBLISHSTATUS);
		this.getRequest().setAttribute("serviceType", OptionsValue.SERVICETYPE);
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
		this.getRequest().setAttribute("publishValue",
				OptionsValue.PUBLISHSTATUS);
		this.getRequest().setAttribute("serviceType", OptionsValue.SERVICETYPE);
		return "input";
	}

	/**
	 *@Title:delete
	 *@Description:删除
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-31上午09:57:07
	 */
	public String delete() {
		try {
			ServiceListDTO serviceListDTO2 = serviceListService
					.findServiceListDTO(Integer.valueOf(this.getId()));
			serviceListDTO2.setStatus(9);
			serviceListService.updateServiceList(serviceListDTO2);
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 *@Title:checkUI
	 *@Description:查看删除记录
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-31上午09:54:39
	 */
	public String checkUI() {
		this.setMethod("checkDetail");
		serviceListDTO = serviceListService.findServiceListDTO(serviceListDTO
				.getServId());
		if (serviceListDTO != null) {
			this.getRequest().setAttribute("publishValue",
					OptionsValue.PUBLISHSTATUS);
			this.getRequest().setAttribute("serviceType",
					OptionsValue.SERVICETYPE);
			return INPUT;
		}
		return ERROR;
	}

	/**
	 *@Title:updateStatus
	 *@Description:审核
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-31下午12:09:04
	 */
	public String updateStatus() {

		try {
			serviceListDTO = serviceListService.findServiceListDTO(Integer
					.valueOf(this.getId()));
			serviceListDTO.setStatus(1);
			serviceListService.updateServiceList(serviceListDTO);
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
	
	
	/**
	*@Title:checkName
	*@Description:检查标题名称是否重复
	*@Params:@return
	*@Return:String
	*@author:张军磊
	*@Date:2014-12-2上午10:30:35
	*/
	public String checkName() {
		ReturnDTO dto = new ReturnDTO();
		/** 获取传递过来的Json数据 */
        Integer  serviceId = serviceListDTO.getServId();
		String name = serviceListDTO.getName();
		String method = this.getMethod();
		/** 设置返回标志信息 */
		dto.setFlag(serviceListService.
				findName(name, method, serviceId));
		return Utils.printInfo(dto);
	}

}
