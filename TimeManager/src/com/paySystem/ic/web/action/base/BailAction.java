	package com.paySystem.ic.web.action.base;
	
	import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.service.base.BailService;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.BailDTO;
import com.paySystem.ic.web.dto.system.UserSession;
	
	
	/***
	 * 
	 * @ClassName:BailAction
	 * @Description:保证金管理Action
	 * @date: 2014-5-14下午07:06:16
	 * @author: 井建国
	 * @version: V1.0
	 */
	@Controller("/base/bail")
	@Scope("prototype") 
	public class BailAction extends BaseAction implements Serializable{
	
		@Resource BailService bailServiceImpl;
		@Resource MerchantsService merchantsServiceImpl;
		@Resource OrgansService organsServiceImpl;
		@Resource FunctionsService functionsService;
		
		
		
		private static final long serialVersionUID = 1L;
		//@Resource BailDAO bailDAOImpl;
		/**保证金值传输对象***/
		private BailDTO bailDTO = new BailDTO();
		public BailDTO getBailDTO() {
			return bailDTO;
		}
		public void setBailDTO(BailDTO bailDTO) {
			this.bailDTO = bailDTO;
		}
		
		/****
		 * 
		 *@Title:list
		 *@Description:列表加载
		 *@param:@return
		 *@return:String
		 *@author:井建国
		 *@thorws:
		 */
		public String list(){
			this.getRequest().removeAttribute("coopStatus");
			this.getRequest().setAttribute("typeSign", OptionsValue.BAILCOMP_SIGN);
			this.getRequest().setAttribute("coopStatus",OptionsValue.COOP_STATUE);
			UserSession us = Utils.getUserSession();
			if(us.getUserLevel()==0){
				return "list";
			}else{
				return "intercepthtml";
			}
		}
		
		/***
		 * 
		 *@Title:jsonPageList
		 *@Description:分页查询列表信息
		 *@param:@return
		 *@param:@throws Exception
		 *@return:String
		 *@author:井建国
		 *@thorws:
		 */
		@SuppressWarnings("unchecked")
		public String jsonPageList() throws Exception{
			/**
			 * 查询结果排序参数设定 
			 */
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			//判断排序参数是否有值
			if(StringUtils.isNotBlank(this.getOrderProperty())
					&&StringUtils.isNotBlank(this.getOrderDirection()))
			{
				orderby.put(this.getOrderProperty(),this.getOrderDirection() );
			}else{
				//如果页面没有要求排序方式，则设置按照卡等级编号排序
				orderby.put("bailId", "desc");
			}
			//返回结果
			List<BailDTO> list = bailServiceImpl.queryMerByCond((bailDTO.getPage() - 1) * pageNum,pageNum,bailDTO,orderby);
			List<List<String>> lists = new ArrayList<List<String>>();
			for(int i = 0;i <list.size();i++){
				//向页面赋值
				BailDTO bailDTO = list.get(i);
				List<String> strings = new ArrayList<String>();
				strings.add(String.valueOf(i+1));
				//交保机构
				strings.add(Utils.getString(bailDTO.getMerOrgName()));
				//合作方式
				strings.add(bailDTO.getCoopWayName());
				//类型标志
				strings.add(Utils.getString(bailDTO.getTypeSignName()));
				//收保机构
				strings.add(Utils.getString(bailDTO.getOrgOilName()));
				//合作状态
				strings.add(Utils.getOptionsIntegerName(OptionsValue.COOP_STATUE,bailDTO.getCoopStatus()));
				//更新时间
				strings.add(DateTimeTool.dateFormat("yyyy-MM-dd", bailDTO.getUpdateTime()));
				//相关操作
				String operation = "";
				if(bailDTO.getCoopStatus() == 1){
					 if(Utils.checkPermission("sy-1210-03")&&Utils.getUserSession().getUserLevel()==0){
						operation += "<a href=base/bail!edtiUI?bailDTO.bailId="+bailDTO.getBailId()+" title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
					}
					if(Utils.checkPermission("sy-1210-04")&&Utils.getUserSession().getUserLevel()==0){
						operation += "<a href=javascript:deleteData('base/bail!delete',"+bailDTO.getBailId()+") title='删除'>"+Globals.IMG_DELETE+"</a>&nbsp;";
					}
				}else{
						if(Utils.checkPermission("sy-1210-01")&&Utils.getUserSession().getUserLevel()==0){
						operation += "<a href=base/bail!checkUI?bailDTO.bailId="+bailDTO.getBailId()+" title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
					}
				}
				strings.add(operation);
				lists.add(strings);
			}
			PageView pageView = new PageView(bailDTO.getPage(),list.size());
			ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
			return Utils.printInfo(listInfoDTO);
		}
		
		/***
		 * 
		 *@Title:edtiUI
		 *@Description:修改保证金的加载页面信息
		 *@param:@return
		 *@return:String
		 *@author:井建国
		 *@thorws:
		 */
		public String edtiUI(){
			this.setMethod("edtiSave");
			Integer bailId = bailDTO.getBailId();
			bailDTO = bailServiceImpl.findByBail(bailId);
			this.getRequest().setAttribute("bailCompSign", OptionsValue.BAILCOMP_SIGN);
			return INPUT;
		}
		
		/**
		 * 
		 *@Title:edtiSave
		 *@Description:修改保存保证金信息
		 *@param:@return
		 *@return:String
		 *@author:井建国
		 *@thorws:
		 */
		public String edtiSave(){
			try {
				if(bailDTO!=null){
					ReturnDTO dto = bailServiceImpl.update(bailDTO);
					if(dto.getFlag()){
						functionsService.saveFunction("保证金管理", 2, "修改保证金："+bailDTO.getBailId());
						this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
						this.getRequest().setAttribute("url","base/bail!list");
						return SUCCESS;
					}else{
						this.getRequest().setAttribute("result",dto.getMsg());
						this.getRequest().setAttribute("url","base/bail!list");
						return ERROR;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ERROR;
		}
		
		/**
		 * 
		 *@Title:delete
		 *@Description:删除保证金信息
		 *@param:@return
		 *@return:String
		 *@author:井建国
		 *@thorws:
		 */
		public String delete(){
		try{
			bailDTO = bailServiceImpl.findByBail(Integer.parseInt(this.getId()));
			bailDTO.setCoopStatus(2);
			bailServiceImpl.update(bailDTO);
			//merchantsService.delete(this.getId());
			functionsService.saveFunction("保证金管理", 3, "删除保证金："+this.getId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
		}
		
		/***
		 * 
		 *@Title:checkInfo
		 *@Description:查看保证金信息
		 *@param:@return
		 *@return:String
		 *@author:井建国
		 *@thorws:
		 */
		public String checkUI(){
			this.setMethod("checkUI");
			Integer bailId = bailDTO.getBailId();
			bailDTO = bailServiceImpl.findByBail(bailId);
			this.getRequest().setAttribute("bailCompSign", OptionsValue.BAILCOMP_SIGN);
			this.getRequest().setAttribute("bailCompSign", OptionsValue.BAILCOMP_SIGN);
			return INPUT;
		}
	}
