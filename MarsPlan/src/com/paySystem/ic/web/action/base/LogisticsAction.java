package com.paySystem.ic.web.action.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.base.LogisticsService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.LogisticsDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**
 *   物流公司Action
 * 
 * @ClassName:LogisticsAction
 * @Description:物流公司Action
 * @date: 2014-7-21 下午04:42:50
 * @author: 赵瑞群
 * @version: V1.0
 */
@Controller("/base/logistics")
@Scope("prototype")
public class LogisticsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource
	LogisticsService LogisticsService;
	@Resource 
	FunctionsService functionsService;
	
	private LogisticsDTO logisticsDTO = new LogisticsDTO();
	
	public LogisticsDTO getLogisticsDTO() {
		return logisticsDTO;
	}

	public void setLogisticsDTO(LogisticsDTO logisticsDTO) {
		this.logisticsDTO = logisticsDTO;
	}

	/**
	 *  物流公司菜单访问方法
	 *  
	 * @Title: list
	 * @Description:
	 *                 物流公司菜单访问默认方法，
	 *                 方法中对列表界面所需数据进行准备
	 * @Param: @return 
	 * @Return: String 跳转参数标识
	 * @Author: 赵瑞群
	 * @Thorws:
	 */
	public String list () {
		
		//获取当前操作员信息
		UserSession us = Utils.getUserSession();
		
		/**
		 * 根据操作员不同级别进行界面跳转
		 * 
		 *    us.getUserLevel
		 *    0 : 平台操作员
		 *    2 : 商户操作员
		 *    操作员为平台操作员，进行界面正常跳转；
		 *    操作员为机构或商户操作员，进行拦截提示其不拥有该权限；
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
	 *  列表查询
	 *  
	 *@Title:jsonPageList
	 *@Description: 根据列表界面条件对数据进行分页查询
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception{
		
		LinkedHashMap<String, String> orderby
		                       = new LinkedHashMap<String, String>();
		
		//封装排序方式参数，如界面传递排序方式则
		if (StringUtils.isNotBlank(this.getOrderProperty())
			&& StringUtils.isNotBlank(this.getOrderDirection())) {
			
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		   
		  } else { orderby.put("updateTime", "desc"); }
		
		
		
		//调用物流公司Service，根据条件查询物流公司信息列表及分页信息
		QueryResult<LogisticsDTO> dtoResult =
			LogisticsService.queryLogisticsByCond((logisticsDTO.getPage()-1)*pageNum, pageNum, logisticsDTO, orderby);
		
		//获取物流公司DTO对象集合
		List<LogisticsDTO> logisticsList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		
		//遍历DTO对象集合并生成要输出到界面的信息
		for(int i=0 ; i<logisticsList.size() ; i++){
			
			List<String> strings = new ArrayList<String>();
			
			LogisticsDTO logisticsDTO = logisticsList.get(i);
			
			strings.add(String.valueOf(i+1));
			strings.add(logisticsDTO.getLogistId().toString());
			strings.add(logisticsDTO.getLogistName());
			strings.add(logisticsDTO.getUrl());
			strings.add(DateTimeTool.dateFormat("", logisticsDTO.getCreateTime()));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.STATE_STATUS,logisticsDTO.getStatus() ));
			strings.add(logisticsDTO.getOperatorId());
			strings.add(DateTimeTool.dateFormat("", logisticsDTO.getUpdateTime()));
			
			String operation = "";
			
			//判断状态是否为 "删除" 状态，如不是删除状态  则可进行修改及删除操作
			if (logisticsDTO.getStatus() != null && logisticsDTO.getStatus()!= 9) {
				//检查是否有物流公司查看权限，如有则显示"查看"按钮
				if (Utils.checkPermission("sy-1901-01")) {
					operation += "<a href=base/logistics!checkUI?logisticsDTO.logistId="
							+ logisticsDTO.getLogistId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}
				
				//检查是否有物流公司修改权限，如有则显示"修改"按钮
				if (Utils.checkPermission("sy-1901-03")) {
					
					operation += "<a href=base/logistics!editUI?logisticsDTO.logistId="
							+ logisticsDTO.getLogistId()+ " title='修改'>"
							+ Globals.IMG_EDIT + "</a>&nbsp;";
				
				}
				//检查是否有物流公司删除权限，如有则显示"删除"按钮
				if (Utils.checkPermission("sy-1901-04")) {
					
					operation += "<a href=javascript:deleteData('base/logistics!delete','"
							+ logisticsDTO.getLogistId()+ "') title='删除'>"
							+ Globals.IMG_DELETE + "</a>&nbsp;";
				}
			
			}
			else {
				//如果本条记录状态为"删除"状态，则操作员只可以对本条记录进行查看操作				
				if (Utils.checkPermission("sy-1901-01")) {
					operation += "<a href=base/logistics!checkUI?logisticsDTO.logistId="
							+ logisticsDTO.getLogistId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}
			}
			
			strings.add(operation);
			lists.add(strings);
			
		}
		
		PageView pageView = new PageView(logisticsDTO.getPage(), dtoResult.getTotalrecord());
		
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		//向前台输出查询数据结果集字符串
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 *  新增物流公司界面跳转请求方法
	 *  
	 *      1.返回跳转信息
	 *      2.为添加界面准备数据
	 * 
	 *@Title:addUI
	 *@Description:列表界面中"添加"按钮出发方法
	 *@param:@return
	 *@return:String
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public String addUI(){
		
		this.setMethod("addSave");
		this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUSIMP);// 状态
		return INPUT;
	}
	
	
	/**
	 *    新增保存
	 *    
	 *      1.保存物流公司信息
	 *      2.保存操作日志信息
	 * 
	 *@Title:addSave
	 *@Description:保存物流公司新增内容
	 *@param:@return
	 *@return:String
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public String addSave(){
		
	try{
		//1.保存物流公司信息
		LogisticsService.saveLogistics(logisticsDTO);
		
		//2.保存操作日志信息
		functionsService.saveFunction("物流公司管理", 1, "增加物流公司信息：");
		
		//保存成功提示内容
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		//保存成功后跳转界面
		this.getRequest().setAttribute("url","base/logistics!list");
		
		return SUCCESS;
	}
	catch(Exception e)
	{
		this.getRequest().setAttribute("result", "保存物流公司出错啦!");
		this.getRequest().setAttribute("url", "base/logistics!list");
		return ERROR;
	}
  }
	
	/**
	 *  修改界面跳转
	 *  
	 *@Title:editUI
	 *@Description:跳转到物流公司修改界面
	 *@param:@return
	 *@return:String
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public String editUI(){
		
		this.setMethod("editSave");
		this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUSIMP);// 状态
		//根据Id获取支付参数信息DTO对象
		logisticsDTO = LogisticsService.findById(logisticsDTO.getLogistId());
		
		
		return INPUT;
	}
	
	/**
	 *   修改保存方法
	 *   
	 *@Title:editSave
	 *@Description:保存物流公司信息修改内容
	 *@param:@return
	 *@return:String
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public String editSave(){
		
	try{	
		LogisticsService.saveLogistics(logisticsDTO);
		functionsService.saveFunction("物流公司管理", 1, "修改物流公司信息：");
		
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","base/logistics!list");
		
		return SUCCESS;
	}
	catch(Exception e)
	{
		this.getRequest().setAttribute("result", "保存物流公司信息出错啦!");
		this.getRequest().setAttribute("url", "base/logistics!list");
		return ERROR;
	}
		
   }
	
	/**
	 *  删除支付参数信息
	 *  
	 *@Title:delete
	 *@Description:删除信息
	 *@param:@return
	 *@return:String
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public String delete(){

      try{
		 
    	   LogisticsDTO logisticsDTO = LogisticsService.delLogistics(Integer.parseInt(this.id));
		
		   functionsService.saveFunction("物流公司管理", 3, "删除物流公司："+ logisticsDTO.getLogistId());
		
		   this.ajaxResult = "ajaxsuccess";
		   
	    } catch (Exception e) {
		
	    	 e.printStackTrace();
		     this.ajaxResult = "ajaxfailure";
		     this.msgResult = e.getMessage();
	    }
	    
		return this.ajaxResult;
	}
	
	
	/**
	 *   删除信息查看跳转请求方法
	 *   
	 * @Title: checkUI
	 * @Description:  删除信息查看跳转请求方法
	 * @Param: @return
	 * @Return: String
	 * @Author: 赵瑞群
	 * @Thorws:
	 */
	public String checkUI(){
		
		this.setMethod("checkUI");
		
		this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUS);// 状态
		logisticsDTO = LogisticsService.findById(logisticsDTO.getLogistId());
		
		return INPUT;
	}
	
	
	/**
	 *  检查物流公司名称是否已存在
	 * 
	 *@Title:checkSameName
	 *@Description:检查物流公司名称是否已存在
	 *@param:@return
	 *@return:String 返回是否已存在状态
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public String checkSameName(){
		
		ReturnDTO data = new ReturnDTO();
		
		boolean flag =LogisticsService.checkSameName(logisticsDTO.getLogistName());
		
		data.setFlag(flag);
		
		return Utils.printInfo(data);
	}
}
