package com.paySystem.ic.web.action.base;

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
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.system.AreaService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.base.OrgansDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;


/**
 * @ClassName:OrgansAction
 * @Description:发卡机构管理
 * @date: 2013-11-8 下午12:01:52
 * @author: 谢洪飞
 * @version: V1.0
 */
@Controller("/base/organs") 
@Scope("prototype")
public class OrgansAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;
	@Resource OrgansService organsService;
	@Resource FunctionsService functionsService;
	@Resource AreaService areaService;
	
	private OrgansDTO organsDTO = new OrgansDTO();

	public OrgansDTO getOrgansDTO() {
		return organsDTO;
	}

	public void setOrgansDTO(OrgansDTO organsDTO) {
		this.organsDTO = organsDTO;
	}

	/**
	 * 列表页面
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 */
	public String list() {
		
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel()==0){
			return "list";
		}else if(us.getUserLevel()==1){
			//organsDTO.setOrganId(us.getOrganId());
			//return this.editUI();
			return "intercepthtml";
		}else if(us.getUserLevel()==2){
			return "intercepthtml";
		}
		return ERROR;
	}

	/**
	 * 异步获取列表数据
	 * @version 2011-9-8 下午08:51:04
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		
		if(StringUtils.isNotBlank(this.getOrderProperty())&&StringUtils.isNotBlank(this.getOrderDirection())){
			orderby.put(this.getOrderProperty(),this.getOrderDirection() );
		}else{
			orderby.put("updateTime", "desc");
		}
		//返回结果
		QueryResult<Organs> queryResult = organsService.queryOrgByCond((organsDTO.getPage() - 1) * pageNum, pageNum,organsDTO,orderby);
		List<Organs> list = queryResult.getResultlist();
		
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i = 0;i <list.size();i++){
			Organs organs = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(organs.getOrganId()));
			strings.add(Utils.getString(organs.getName()));
			strings.add(organs.getParentId().equals("0")?"顶级机构":organsService.find(organs.getParentId()).getName());
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, organs.getStatus()));
			strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", organs.getUpdateTime()));
			String operation = "";
			
			if(organs.getStatus()!=9){
				if(Utils.checkPermission("sy-1101-03")){
					operation += "<a href=base/organs!editUI?organsDTO.organId="+organs.getOrganId()+" title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
				}
				if(Utils.checkPermission("sy-1101-04")){
					operation += "<a href=javascript:deleteData('base/organs!delete','"+organs.getOrganId()+"') title='删除'>"+Globals.IMG_DELETE+"</a>&nbsp;";
				}
			}else{
				if(Utils.checkPermission("sy-1101-01")){
					operation +="<a href=base/organs!checkUI?organsDTO.organId="+organs.getOrganId()+" title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
				}
			}
			strings.add(operation);
			lists.add(strings);
		}
		
		PageView pageView = new PageView(organsDTO.getPage(),queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	/**
	 * 机构帮助查询数据方法
	 * @return
	 * @throws Exception 
	 */

	public String orgJsonPageList() throws Exception{
		this.getSession().setAttribute(Globals.QUERY_PARM, organsDTO);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		
		if(StringUtils.isNotBlank(this.getOrderProperty())&&StringUtils.isNotBlank(this.getOrderDirection())){
			orderby.put(this.getOrderProperty(),this.getOrderDirection() );
		}else{
			orderby.put("updateTime", "desc");
		}
		organsDTO.setHelpSign("1");
		//返回结果
		QueryResult<Organs> queryResult = organsService.queryOrgByCond((organsDTO.getPage() - 1) * pageNum, pageNum,organsDTO, orderby);
		List<Organs> list = queryResult.getResultlist();
		
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i = 0;i <list.size();i++){
			Organs organs = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(organs.getOrganId()));
			strings.add(Utils.getString(organs.getName()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, organs.getStatus()));
			String operation = "<a href=javascript:secOrg('"+organs.getOrganId()+"','"+organs.getName()+"') title='选择'>[选择]</a>&nbsp;";
			strings.add(operation);
			lists.add(strings);
		}
		
		PageView pageView = new PageView(organsDTO.getPage(),queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView,"orgQuery"));
		return Utils.printInfo(listInfoDTO);
	};
	/**
	 * 添加页面
	 * @return
	 */
	public String addUI(){
		
		this.setMethod("addSave");
		organsDTO.setOrganId(organsService.getOrganId());
		List<OptionsString> preOrganValues = new ArrayList<OptionsString>();
		preOrganValues.add(new OptionsString("0", "顶级机构"));
		preOrganValues.addAll(organsService.getTopOption());
		this.getRequest().setAttribute("preOrganValues",preOrganValues );
		this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUS );
		this.getRequest().setAttribute("visibleValues",OptionsValue.VISIBLE_STATUS );//是否
		this.getRequest().setAttribute("sysTypeValues",OptionsValue.SYS_TYPE );
		this.getRequest().setAttribute("areaValues",OptionsValue.AREA_VALUE );
		
		return INPUT;
	}
	
	/**
	 * 添加操作
	 * @version 2011-9-13 下午05:30:30
	 * @return
	 */
	public String addSave() throws Exception{
		
		if (organsService.validate(organsDTO.getOrganId())) {
			this.getRequest().setAttribute("result",this.getText("exist.no.notice"));
			this.getRequest().setAttribute("url","base/organs!list");
			return ERROR;
		} 
		if(organsService.validateOrgName(organsDTO.getName(),organsDTO.getOrganId())){
			this.getRequest().setAttribute("result", "机构名称已存在!");
			this.getRequest().setAttribute("url", "base/organs!list");
			return ERROR;
		}
		Organs organs = organsService.addSaveOrg(organsDTO);
		functionsService.saveFunction("发卡机构管理", 1, "增加机构："+organs.getOrganId());
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","base/organs!list");
		return SUCCESS;
	}

	/**
	 * 进入修改页面
	 * @version 2011-9-10 下午06:20:30
	 * @return
	 */
	public String editUI() {
		this.setMethod("editSave");
		Organs organs = organsService.find(organsDTO.getOrganId());
		if(organs!=null){
			organsDTO = getOrganDTO(organs);
			List<OptionsString> preOrganValues = new ArrayList<OptionsString>();
			preOrganValues.add(new OptionsString("0", "顶级机构"));
			preOrganValues.addAll(organsService.getTopOption());
			this.getRequest().setAttribute("preOrganValues",preOrganValues );
			this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUS );
			this.getRequest().setAttribute("visibleValues",OptionsValue.VISIBLE_STATUS );//是否
			this.getRequest().setAttribute("sysTypeValues",OptionsValue.SYS_TYPE );
			//this.getRequest().setAttribute("areaValues",areaService.getOption() );
			this.getRequest().setAttribute("areaValues",OptionsValue.AREA_VALUE );
			return INPUT;
		}
		return ERROR;
	}
	
	/**
	 *@Title:checkUI
	 *@Description:进入删除查看详情页面
	 *@param:@return
	 *@return:String
	 *@author:谢
	 *@thorws:
	 */
	public String checkUI(){
		
		this.setMethod("checkDetail");
		Organs organs = organsService.find(organsDTO.getOrganId());
		
		if(organs!=null){
			organsDTO = getOrganDTO(organs);
			List<OptionsString> preOrganValues = new ArrayList<OptionsString>();
			preOrganValues.add(new OptionsString("0", "顶级机构"));
			preOrganValues.addAll(organsService.getTopOption());
			this.getRequest().setAttribute("preOrganValues",preOrganValues );
			this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUS );
			this.getRequest().setAttribute("visibleValues",OptionsValue.VISIBLE_STATUS );//是否
			this.getRequest().setAttribute("sysTypeValues",OptionsValue.SYS_TYPE );
			this.getRequest().setAttribute("areaValues",OptionsValue.AREA_VALUE );
			return INPUT;
		}
		return ERROR;
	}

	/**
	 * 修改模块操作
	 * @version 2011-9-10 下午03:59:08
	 * @return
	 */
	public String editSave() throws Exception{

		Organs organs = organsService.find(organsDTO.getOrganId());
		if(organs!=null){
			UserSession us = Utils.getUserSession();
			if(us.getUserLevel()==0){
				organs.setName(Utils.getString(organsDTO.getName()));
				if(organs.getOrganId().equals(organsDTO.getParentId())){
					this.getRequest().setAttribute("result","不能选择本机构为上级发卡机构！");
					this.getRequest().setAttribute("url","base/organs!list");
					return ERROR;
				}
				if(organsService.validateOrgName(organsDTO.getName(),organsDTO.getOrganId())){
					this.getRequest().setAttribute("result", "机构名称已存在!");
					this.getRequest().setAttribute("url", "base/organs!list");
					return ERROR;
				}
				organs.setParentId(organsDTO.getParentId());
				organs.setYearServSign(organsDTO.getYearServSign());
				organs.setTrustFundSign(organsDTO.getTrustFundSign());
				organs.setAreaId(organsDTO.getAreaId());
				organs.setStatus(organsDTO.getStatus());
				organs.setSettPeriod(organsDTO.getSettPeriod());
				/*organs.setNetPayRetRate(Utils.strToBigDecimal(organsDTO.getNetPayRetRate()));*/
				
			}
			organs.setSysType(0);
			organs.setTeleNo(Utils.getString(organsDTO.getTeleNo()));
			organs.setOrgDesc(Utils.getString(organsDTO.getOrgDesc()));
			organs.setConPerName(Utils.getString(organsDTO.getConPerName()));
			organs.setConPerTeleNo(Utils.getString(organsDTO.getConPerTeleNo()));
			organs.setAddress(Utils.getString(organsDTO.getAddress()));
			organs.setZip(Utils.getString(organsDTO.getZip()));
			organs.setBankName(Utils.getString(organsDTO.getBankName()));
			organs.setBankAcctNo(Utils.getString(organsDTO.getBankAcctNo()));
			organs.setBankUser(Utils.getString(organsDTO.getBankUser()));
			organs.setSaleMRate(organsDTO.getSaleMRate());
			organs.setEmContPhone(organsDTO.getEmContPhone());
			organs.setUpdateTime(new Date());
			organsService.editSaveOrg(organs);
			functionsService.saveFunction("发卡机构管理", 2, "修改机构："+organs.getOrganId());
			this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
			this.getRequest().setAttribute("url","base/organs!list");
			return SUCCESS;
		}
		return ERROR;
	}
	
	/**
	 * 删除操作
	 * @version 2011-9-10 下午04:23:43
	 */
	public String delete() {

		try {
			//organsService.delete(this.getId());
			Organs organs = organsService.find(this.getId());
			organs.setStatus(9);
			organsService.editSaveOrg(organs);
			functionsService.saveFunction("发卡机构管理", 3, "删除机构："+organs.getOrganId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	private OrgansDTO getOrganDTO(Organs organs){
		organsDTO.setOrganId(Utils.getString(organs.getOrganId()));
		organsDTO.setName(Utils.getString(organs.getName()));
		organsDTO.setParentId(organs.getParentId());
		organsDTO.setYearServSign(organs.getYearServSign());
		organsDTO.setTrustFundSign(organs.getTrustFundSign());
		organsDTO.setTeleNo(Utils.getString(organs.getTeleNo()));
		organsDTO.setOrgDesc(Utils.getString(organs.getOrgDesc()));
		organsDTO.setConPerName(Utils.getString(organs.getConPerName()));
		organsDTO.setConPerTeleNo(Utils.getString(organs.getConPerTeleNo()));
		organsDTO.setAreaId(organs.getAreaId());
		organsDTO.setAddress(Utils.getString(organs.getAddress()));
		organsDTO.setZip(Utils.getString(organs.getZip()));
		organsDTO.setBankName(Utils.getString(organs.getBankName()));
		organsDTO.setBankAcctNo(Utils.getString(organs.getBankAcctNo()));
		organsDTO.setBankUser(Utils.getString(organs.getBankUser()));
		organsDTO.setStatus(organs.getStatus());
		organsDTO.setParentId(Utils.getString(organs.getParentId()));
		organsDTO.setSettPeriod(organs.getSettPeriod());
		organsDTO.setLastSettTime(organs.getLastSettTime());
		organsDTO.setSaleMRate(organs.getSaleMRate());
		organsDTO.setSysType(organs.getSysType());
		organsDTO.setEmContPhone(Utils.getString(organs.getEmContPhone()));
	/*	organsDTO.setNetPayRetRate(NumberUtil.numberFormat("", organs.getNetPayRetRate()));*/
		organsDTO.setUpdateTime(organs.getUpdateTime());
		return organsDTO;
	}
}
