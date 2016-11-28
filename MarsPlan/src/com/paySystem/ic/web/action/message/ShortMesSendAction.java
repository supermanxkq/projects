package com.paySystem.ic.web.action.message;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.app.ShortMesSend;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.service.message.SMSService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.stock.MessServParamConfigService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;
import com.paySystem.ic.web.dto.stock.MessServParamConfigDTO;
import com.paySystem.ic.web.dto.system.UserSession;



@Controller("/message/shortMesSend")
@Scope("prototype")
public class ShortMesSendAction extends BaseAction{
		
	private static final long serialVersionUID = 1157838270801154543L;
	private ShortMesSendDTO smsDto = new ShortMesSendDTO();
	@Resource
	SMSService smsService;
	@Resource
	FunctionsService functionsService;
	@Resource
	MemberService memService;
	@Resource
	MessServParamConfigService messServParamConfigService;
	MemberDTO mem;
	private MessServParamConfigDTO messServParamConfigDTO=new MessServParamConfigDTO();
	
	public MessServParamConfigDTO getMessServParamConfigDTO() {
		return messServParamConfigDTO;
	}
	public void setMessServParamConfigDTO(
			MessServParamConfigDTO messServParamConfigDTO) {
		this.messServParamConfigDTO = messServParamConfigDTO;
	}
	public ShortMesSendDTO getSmsDto() {
		return smsDto;
	}
	public void setSmsDto(ShortMesSendDTO smsDto) {
		this.smsDto = smsDto;
	}

	/**
	 *@Description:显示页面list方法
	 *@author:张国法
	 */
	public String list(){
		
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel()!=1){
			this.getRequest().setAttribute("status",OptionsValue.OilORDER_STATUS );//使用状态
			this.getRequest().setAttribute("type",OptionsValue.PEOPLE_TYPE);//套餐类型
			return "list";
		}else{
			return "intercepthtml";
		}
	}
	
	/**
	 *@Title:jsonPageList
	 *@Description:短信参数查询jsonPage
	 *@author:张国法
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception{
		UserSession us = Utils.getUserSession();		
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		//判断排序参数是否有值
		if(StringUtils.isNotBlank(this.getOrderProperty())
				&&StringUtils.isNotBlank(this.getOrderDirection()))
		{orderBy.put(this.getOrderProperty(),this.getOrderProperty());}
		else{orderBy.put("id","desc");}
	
		QueryResult<ShortMesSend> terResult = smsService.querySmsByCond((smsDto.getPage() - 1) * pageNum,pageNum,smsDto,orderBy);
		List<ShortMesSend> smslist = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		for (int i = 0; i < smslist.size(); i++) {
			// 向页面赋值
			ShortMesSend smsDto = smslist.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(smsDto.getSmsTitle()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.PEOPLE_TYPE,smsDto.getSmsType()));
			strings.add(Utils.getString(smsDto.getNum()));
			strings.add(Utils.getString(smsDto.getTotalPrice()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.OilORDER_STATUS, smsDto.getSmsStatus()));
			strings.add(Utils.getString(us.getRealName()));
			strings.add(Utils.getString(smsDto.getCreateTime()));
			strings.add(Utils.getString(smsDto.getUpdateTime()));
			String operation = "";
			if(us.getUserLevel()==0){		
				if(smsDto.getSmsStatus()!=0){
					
					if(Utils.checkPermission("sy-1611-01")){
						operation += "<a href=message/shortMesSend!checkUI?smsDto.smsId="+smsDto.getSmsId()+"  title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
					}
				}else{
					if(Utils.checkPermission("sy-1611-05")) {
						operation += "<a href=javascript:void(0) onclick=loadData('" + smsDto.getSmsId() + "',this) title='审核'>" + Globals.IMG_AUDIT + "</a> &nbsp;";						
					}
				}
				
				
			 
			}else{
				if(smsDto.getSmsStatus()!=3&&smsDto.getSmsStatus()!=1){
					 if(Utils.checkPermission("sy-1611-03")){
							operation += "<a href=message/shortMesSend!editUI?smsDto.smsId="+smsDto.getSmsId()+" title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
						  }
						  if(Utils.checkPermission("sy-1611-04")){
							operation += "<a href=javascript:deleteData('message/shortMesSend!delete','"+smsDto.getSmsId()+"') title='删除'>"+Globals.IMG_DELETE+"</a>&nbsp;";
						  }
				}else if(smsDto.getSmsStatus()==1){
					if(Utils.checkPermission("sy-1611-01")){
						operation += "<a href=message/shortMesSend!checkUI?smsDto.smsId="+smsDto.getSmsId()+"  title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
					}
				}else{
					if(Utils.checkPermission("sy-1611-01")){
						operation += "<a href=message/shortMesSend!checkUI?smsDto.smsId="+smsDto.getSmsId()+"  title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
					}
				}
			}
			strings.add(operation);			
			lists.add(strings);
		}

		PageView pageView = new PageView(smsDto.getPage(), terResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
		
	}
	
	public String checkUI(){		
		this.setMethod("checkDetail");
		//this.setMethod("editSave");
		if(smsDto!=null){
			//int stockId = smsDto.getStockId();
			ShortMesSend shortMesSend = smsService.find(smsDto.getSmsId());
			System.out.println(shortMesSend.getCreateTime());
			if(smsDto!=null){
				smsDto.setSmsTitle(Utils.getString(shortMesSend.getSmsTitle()));
				smsDto.setSmsContent(shortMesSend.getSmsContent());
				smsDto.setSmsType(shortMesSend.getSmsType());
				smsDto.setTotalPrice(shortMesSend.getTotalPrice());
				smsDto.setNum(shortMesSend.getNum());
				smsDto.setOilId(shortMesSend.getOilId());
			}

			this.getRequest().setAttribute("type",OptionsValue.PEOPLE_TYPE );
			this.getRequest().setAttribute("status",OptionsValue.OilORDER_STATUS );//使用状态
			return INPUT;
		}
		return ERROR;
	}
	
    /**
     *@Title:addUI
     *@Description:添加按钮执行方法
     *@param:@return
     *@return:String
     *@author:张国法
     *@thorws:
     */
    public String addUI(){		
		this.setMethod("addSave");
		this.getRequest().setAttribute("status",OptionsValue.OilORDER_STATUS );//使用状态
		this.getRequest().setAttribute("type",OptionsValue.PEOPLE_TYPE);//套餐类型
		return INPUT;
	}
    
 
	/**
	 *@Title:addSave
	 *@Description:保存群发短信信息 
	 */
	public String addSave() throws Exception{
		UserSession us=Utils.getUserSession();
		
		if(smsDto.getSmsType()==0){
			List<MemberDTO> memList = memService.findByOrgId(us.getOrganId());
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			for(int i=1;i<memList.size();i++){
				mem=memList.get(i);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("telephone", mem.getTeleNo());
				list.add(map);
				if(smsDto.getSmsType()==0){
					smsDto.setNum(i);
					}
				}
		}else if(smsDto.getSmsType()==1){
				List<MemberDTO> memList1 = memService.findAll();
				for(int j=0;j<memList1.size();j++){
					mem=memList1.get(j);
					smsDto.setNum(j+1);
				}
			
		}else if(smsDto.getSmsType()==2){
				//smsDto.setNum(1);
		}
		
		BigDecimal bigDecimal = null;
		if(smsDto.getNum()!=null){
			bigDecimal = new BigDecimal(smsDto.getNum()*0.07);
		}
		
		smsDto.setTotalPrice(bigDecimal);
		smsDto.setSmsStatus(0);
		//SmsType=0表示加油站所属会员；1表示平台所属会员；2表示加油站操作员
		smsDto.setSmsType(0);
		smsService.addSMSInfo(smsDto);
		functionsService.saveFunction("短信群发管理",1, "增加群发短信："+smsDto.getSmsId());
		this.getRequest().setAttribute("status",OptionsValue.OilORDER_STATUS );//使用状态
		this.getRequest().setAttribute("type",OptionsValue.PEOPLE_TYPE );//状态
		this.getRequest().setAttribute("url","message/shortMesSend!list");
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		return SUCCESS;
	}
	
	/**
	 * 进入修改短信信息
	 * @return
	 * @throws Exception 
	 */
	public String editUI() throws Exception{
		//设置form中表单的方法
		this.setMethod("editSave");
		if(smsDto!=null){
			//int stockId = smsDto.getStockId();
			ShortMesSend shortMesSend = smsService.find(smsDto.getSmsId());
			System.out.println(shortMesSend.getCreateTime());
			if(smsDto!=null){
				//smsDto.setStockId(stockAdjustment.getStockId());
				smsDto.setSmsTitle(Utils.getString(shortMesSend.getSmsTitle()));
				smsDto.setSmsContent(shortMesSend.getSmsContent());
				smsDto.setSmsType(shortMesSend.getSmsType());
//				if(smsDto.getSmsType()==0){
//					List<MemberDTO> memList = memService.findByOrgId(us.getOrganId());
//					List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//					for(int i=1;i<memList.size();i++){
//						mem=memList.get(i);
//						Map<String,Object> map=new HashMap<String, Object>();
//						map.put("telephone", mem.getTeleNo());
//						list.add(map);
//						if(smsDto.getSmsType()==0){
//							smsDto.setNum(i);
//							}
//						}
//				}else if(smsDto.getSmsType()==1){
//						List<MemberDTO> memList1 = memService.findAll();
//						for(int j=1;j<memList1.size();j++){
//							mem=memList1.get(j);
//							smsDto.setNum(j);
//						}
//					
//				}else if(smsDto.getSmsType()==2){
//						//smsDto.setNum(1);
//				}
//				if(smsDto.getNum()!=null){
//					bigDecimal = new BigDecimal(smsDto.getNum()*0.07);
//				}
				smsDto.setOilId(shortMesSend.getOilId());
			}

			this.getRequest().setAttribute("type",OptionsValue.PEOPLE_TYPE );//状态
			this.getRequest().setAttribute("status",OptionsValue.OilORDER_STATUS );//使用状态
			this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
			return INPUT;
		}
		return ERROR;
	}
	

	/**编辑保存*/
	public String editSave(){
		try {
			ShortMesSend shortMesSend = smsService.find(smsDto.getSmsId());
			UserSession us=Utils.getUserSession();
			//判断发送目标群体
			if(smsDto.getSmsType()==0){
				//获取本次发送短信数量
				List<MemberDTO> memList = memService.findByOrgId(us.getOrganId());
				for(int i=1;i<memList.size();i++){
					mem=memList.get(i);
					smsDto.setNum(i);
					}
			}else if(smsDto.getSmsType()==1){
					//获取本次发送短信数量
					List<MemberDTO> memList1 = memService.findAll();
					for(int j=0;j<memList1.size();j++){
						mem=memList1.get(j);
						smsDto.setNum(j+1);
					}
				
			}else if(smsDto.getSmsType()==2){
					smsDto.setNum(1);
			}
			BigDecimal bigDecimal = null;
			//计算本次发送费用
			if(smsDto.getNum()!=null){
				bigDecimal = new BigDecimal(smsDto.getNum()*0.07);
			}
			smsDto.setTotalPrice(bigDecimal);
			smsDto.setOilId(shortMesSend.getOilId());
			smsDto.setSmsStatus(0);
			ReturnDTO dto = smsService.update(smsDto);
			if(dto.getFlag()){
				functionsService.saveFunction("修改群发短信管理", 2, "修改群发短信内容："+Utils.getString(smsDto.getSmsId()));
				this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
				this.getRequest().setAttribute("url","message/shortMesSend!list");
				this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
				return SUCCESS;
			}else{
				this.getRequest().setAttribute("result",dto.getMsg());
				this.getRequest().setAttribute("url","message/shortMesSend!list");
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}
	//删除短信发送信息
	public String delete() {
		try { 
			ShortMesSend sms = smsService.find(Integer.parseInt(this.getId()));
			smsDto.setSmsTitle(Utils.getString(sms.getSmsTitle()));
			smsDto.setSmsId(sms.getSmsId());
			smsDto.setSmsContent(sms.getSmsContent());
			smsDto.setSmsType(sms.getSmsType());
			smsDto.setTotalPrice(sms.getTotalPrice());
			smsDto.setNum(sms.getNum());
			smsDto.setOilId(sms.getOilId());
			smsDto.setCreateTime(sms.getCreateTime());
			smsDto.setUpdateTime(sms.getUpdateTime());
			smsDto.setSmsStatus(3);
			smsService.update(smsDto);
			//merchantsService.delete(this.getId());
			functionsService.saveFunction("删除短信群发管理", 3, "删除短信："+sms.getSmsId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
	
	/**
	 *@Title:loadOrder
	 *@Description:加载需审核的短信信息
	 *@param:@return
	 *@return:String
	 *@author:张国法
	 *@thorws:
	 */
	public String loadOrder(){
			ReturnDTO dto = new ReturnDTO();
			smsDto = smsService.findShortMesSend(smsDto.getSmsId());
			if (smsDto != null) {
				dto.setFlag(true);
				dto.setObj(smsDto);
			}
			return Utils.printInfo(dto);
	}
	
	/**
	 *@Description:审核通过方法
	 *@author:张国法
	 */
	@SuppressWarnings("null")
	public String auditSuccess(){
		ShortMesSend sms = smsService.find(smsDto.getSmsId());
		smsDto.setSmsTitle(Utils.getString(sms.getSmsTitle()));
		smsDto.setSmsId(sms.getSmsId());
		smsDto.setSmsContent(sms.getSmsContent());
		smsDto.setSmsType(sms.getSmsType());
		smsDto.setTotalPrice(sms.getTotalPrice());
		smsDto.setNum(sms.getNum());
		smsDto.setOilId(sms.getOilId());
		smsDto.setCreateTime(sms.getCreateTime());
		smsDto.setUpdateTime(sms.getUpdateTime());
		smsDto.setSmsStatus(1);
		smsService.update(smsDto);
		this.getRequest().setAttribute("type",OptionsValue.PEOPLE_TYPE );//目标人群
		this.getRequest().setAttribute("status",OptionsValue.OilORDER_STATUS );//使用状态
		
		StringBuffer sb=new StringBuffer();
		//获取本次发送短信数量
		List<MemberDTO> memList1 = memService.findAll();
		for(int j=0;j<memList1.size();j++){
			mem=memList1.get(j);
			sb.append(mem.getTeleNo()+",");
//			smsDto.setNum(j);
		}
		String phone=sb.substring(0,sb.length()-1);
		
		messServParamConfigDTO.setMobileValue(phone);
		messServParamConfigDTO.setContent(sms.getSmsContent());
		messServParamConfigService.sendMessageByParamType(messServParamConfigDTO);
		/*MemberDTO mem;
		UserSession us=Utils.getUserSession();
		List<MemberDTO> memList = memService.findByOrgId(us.getOrganId());
		for(int i=1;i<memList.size();i++){
			mem=memList.get(i);
		}*/
//		String url = "http://Admin.esoftsms.com/sdk/BatchSend.aspx";
//		String result = HttpUtil.sendGetRequestWithResult(url,"CorpID=TEST00233&Pwd=123456&Mobile="+ mem.getTeleNo()+"&Content="+smsDto.getSmsContent()+"&Cell=&SendTime=");

		//this.getRequest().setAttribute("url","message/shortMesSend!list");
		return "list";
		
	}
	
	/**
	 *@Description:审核失败调用方法
	 *@author:张国法
	 */
	public String auditFailure(){
		
		ShortMesSend sms = smsService.find(smsDto.getSmsId());
		smsDto.setSmsTitle(Utils.getString(sms.getSmsTitle()));
		smsDto.setSmsId(sms.getSmsId());
		smsDto.setSmsContent(sms.getSmsContent());
		smsDto.setSmsType(sms.getSmsType());
		smsDto.setTotalPrice(sms.getTotalPrice());
		smsDto.setNum(sms.getNum());
		smsDto.setOilId(sms.getOilId());
		smsDto.setCreateTime(sms.getCreateTime());
		smsDto.setUpdateTime(sms.getUpdateTime());
		smsDto.setSmsStatus(2);
		smsService.update(smsDto);
		this.getRequest().setAttribute("type",OptionsValue.PEOPLE_TYPE );//目标人群
		this.getRequest().setAttribute("status",OptionsValue.OilORDER_STATUS );//使用状态
		return "list";
		
	}
}
