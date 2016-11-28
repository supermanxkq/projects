package com.paySystem.ic.web.action.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerOrgFuncSwitch;
import com.paySystem.ic.service.base.MofSwitchService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerOrgFuncSwitchDTO;
import com.paySystem.ic.web.dto.system.UserSession;
@Controller("/base/mofswitch")
@Scope("prototype")
public class MofSwitchAction extends BaseAction{

	private static final long serialVersionUID = 6078224596052025805L;
	
	@Resource
	MofSwitchService mofSwitchService;
	@Resource
	FunctionsService functionsService;
	
	MerOrgFuncSwitchDTO mofSwitchDTO = new MerOrgFuncSwitchDTO();
	
	public MerOrgFuncSwitchDTO getMofSwitchDTO() {
		return mofSwitchDTO;
	}
	public void setMofSwitchDTO(MerOrgFuncSwitchDTO mofSwitchDTO) {
		this.mofSwitchDTO = mofSwitchDTO;
	}




	/**
	 *@Title:list
	 *@Description: 菜单[机构商户功能设置]访问方法
	 *@param:@return
	 *@return:String
	 *@thorws:
	 */
	public String list(){
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 0:
			return "list";
			
		case 1:
			return "intercepthtml";
		case 2:
			return "intercepthtml";	
		}
		return "list";
	}
	
	/**
	 *@Title:jsonPageList
	 *@Description:异步加载商户/机构信息-[商户机构设置]
	 *@Param:@return
	 *@Param:@throws Exception
	 *@Return:String
	 *@Throws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception{
		
		
		/**
		 * 查询结果排序参数设定 
		 */
		LinkedHashMap<String,String> orderBy = new LinkedHashMap<String,String>();
		
		//判断排序参数是否有值
		if(StringUtils.isNotBlank(this.getOrderProperty())
				&&StringUtils.isNotBlank(this.getOrderDirection()))
		{orderBy.put(this.getOrderProperty(),this.getOrderProperty());}
		else
		{orderBy.put("updateTime","desc");}
		
		QueryResult<MerOrgFuncSwitch> terResult =
			mofSwitchService.queryMerOrgSw((mofSwitchDTO.getPage() - 1) * pageNum,pageNum, mofSwitchDTO, orderBy);
		List<MerOrgFuncSwitch> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		for(int i = 0;i <list.size();i++){
			//向页面赋值
			MerOrgFuncSwitch meSwitch = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			
			if(meSwitch.getOrgMercSign()==0)
			{
				//如果是机构
				strings.add(Utils.getString(meSwitch.getOrgans().getName()));
				strings.add(Utils.getString(meSwitch.getOrgans().getOrganId()));
				strings.add("发卡机构");
			}
			else
			{
				//如果为商户
				strings.add(Utils.getString(meSwitch.getMerchants().getMerName()));
				strings.add(Utils.getString(meSwitch.getMerchants().getMerId()));
				strings.add("商户");
			}
			strings.add(DateTimeTool.dateFormat("", meSwitch.getUpdateTime()));
			String operation = "";
			if(Utils.checkPermission("sy-1401-03")){
				operation += "<a href=base/mofswitch!editUI?mofSwitchDTO.FmoId="+meSwitch.getFmoId()+" title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		   }
		
		PageView pageView = new PageView(mofSwitchDTO.getPage(),terResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 *@Title:editUI
	 *@Description:跳转到修改页面并初始化修改页面信息
	 *@Param:@return
	 *@Return:String
	 *@Throws:
	 */
	public String editUI(){
		this.setMethod("editSave");
		//找到要修改的机构/商户功能信息
		MerOrgFuncSwitch merOrgFuncSwitch = mofSwitchService.find(mofSwitchDTO.getFmoId());
		if(merOrgFuncSwitch!=null)
		{
			mofSwitchDTO.setFmoId(merOrgFuncSwitch.getFmoId());
			mofSwitchDTO.setOrgMercSign(merOrgFuncSwitch.getOrgMercSign());
			if(merOrgFuncSwitch.getOrgMercSign()==0)
			{//存放机构信息
				mofSwitchDTO.setOrganId(merOrgFuncSwitch.getOrgans().getOrganId());
				mofSwitchDTO.setOrganName(merOrgFuncSwitch.getOrgans().getName());
			}
			else
			{//存放商户信息
				mofSwitchDTO.setMerId(merOrgFuncSwitch.getMerchants().getMerId());
				mofSwitchDTO.setMerName(merOrgFuncSwitch.getMerchants().getMerName());
			}
			mofSwitchDTO.setOpenAgencySign(merOrgFuncSwitch.getOpenAgencySign());
			mofSwitchDTO.setOpenBriGiftSign(merOrgFuncSwitch.getOpenBriGiftSign());
			mofSwitchDTO.setOpenBussManSign(merOrgFuncSwitch.getOpenBussManSign());
			mofSwitchDTO.setOpenHolGiftSign(merOrgFuncSwitch.getOpenHolGiftSign());
			mofSwitchDTO.setOpenMessSign(merOrgFuncSwitch.getOpenMessSign());
			mofSwitchDTO.setOpenPreferSign(merOrgFuncSwitch.getOpenPreferSign());
			mofSwitchDTO.setOpenPreferTSign(merOrgFuncSwitch.getOpenPreferTSign());
			mofSwitchDTO.setEnciVipMess(merOrgFuncSwitch.getEnciVipMess());
			return INPUT;
		}
		return ERROR;
	}
	
	/**
	 *@Title:editSave
	 *@Description:保存机构/商户功能设置的修改信息
	 *@Param:@return
	 *@Return:String
	 *@Throws:
	 */
	public String editSave(){
		try {
			ReturnDTO dto = mofSwitchService.updateMof(mofSwitchDTO);
			if(dto.getFlag()){
				functionsService.saveFunction("商户机构功能设置=", 2, "修改商户机构功能："+mofSwitchDTO.getFmoId());
				this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
				this.getRequest().setAttribute("url","base/mofswitch!list");
				return SUCCESS;
			}else{
				this.getRequest().setAttribute("result",dto.getMsg());
				this.getRequest().setAttribute("url","base/mofswitch!list");
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

}
