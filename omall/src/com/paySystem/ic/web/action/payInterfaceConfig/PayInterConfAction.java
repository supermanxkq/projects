package com.paySystem.ic.web.action.payInterfaceConfig;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.payInterfaceConfig.PayInterConfDAO;
import com.paySystem.ic.service.payInterfaceConfig.PayInterConfService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.system.AreaService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Controller("/payInterface/payInterfaceConfig")
@Scope("prototype")
public class PayInterConfAction extends BaseAction{
	@Resource
	PayInterConfService payInterConfService;
	@Resource
	FunctionsService functionsService;
	@Resource
	AreaService areaService;
	
	private static final long serialVersionUID = -5593490364354362944L;
	
	public PayInterConfDTO payInterConfDTO=new PayInterConfDTO();
	
	public PayInterConfDTO getPayInterConfDTO() {
		return payInterConfDTO;
	}

	public void setPayInterConfDTO(PayInterConfDTO payInterConfDTO) {
		this.payInterConfDTO = payInterConfDTO;
	}

	private ReturnDTO returnDTO = new ReturnDTO();

	public ReturnDTO getReturnDTO() {
		return returnDTO;
	}

	public void setReturnDTO(ReturnDTO returnDTO) {
		this.returnDTO = returnDTO;
	}

	/**
	*@Title:list
	*@Description:显示页面list方法
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-9-19上午11:44:58
	*/
	public String list(){
		UserSession us=Utils.getUserSession();
		if(us.getUserLevel()==0){
		payInterConfDTO.setStatus(1);
		this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUS );//使用状态
		return "list";
		}else{
			return "intercepthtml";
		}
	}

	/**
	*@Title:jsonPageList
	*@Description:异步获取列表数据的方法
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:王楠
	*@Date:2014-9-22下午02:46:59
	*/
	@SuppressWarnings("unchecked")
	public String jsonPageList()throws Exception{
		LinkedHashMap<String,String> orderBy=new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderDirection(), this.getOrderProperty());
		} else {
			/** 如果页面排序方式没有要求，则设置更新时间排序 */
			orderBy.put("createTime", "desc");
		}
		/**返回结果*/
		QueryResult<PayInterConfDTO> result=payInterConfService.queryAll(
				(payInterConfDTO.getPage()-1)*pageNum, pageNum, payInterConfDTO, orderBy);
		List<PayInterConfDTO> pList=result.getResultlist();
		List<List<String>> lists=new ArrayList<List<String>>();
		for(int i=0;i<pList.size();i++){
			/**向页面赋值*/
			PayInterConfDTO payInterConfDTO=pList.get(i);
			List<String> strings=new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(payInterConfDTO.getPsName()));
			strings.add(Utils.getString(payInterConfDTO.getPayOrgName()));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.STATE_STATUS,payInterConfDTO.getStatus()));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.PSTYPE,payInterConfDTO.getPsType()));
			strings.add(NumberUtil.numberFormat("", payInterConfDTO.getPsRate()));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.VISIBLE_STATUS,payInterConfDTO.getIsNotArrivPay()));
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.VISIBLE_STATUS,payInterConfDTO.getIsNotOnLinePay()));
			strings.add(DateTimeTool.dateFormat("", payInterConfDTO.getCreateTime()));
			
			String operation="";
			
				if(Utils.checkPermission("sy-1801-01")){
					operation += "<a href=payInterface/payInterfaceConfig!checkUI?payInterConfDTO.psId="
						+ payInterConfDTO.getPsId()
						+ " title='查看'>"
						+ Globals.IMG_VIEW + "</a>&nbsp;";
				    }
				if (Utils.checkPermission("sy-1801-03")&&payInterConfDTO.getStatus()!=9) {
						operation += "<a href=payInterface/payInterfaceConfig!editUI?payInterConfDTO.psId="
							+payInterConfDTO.getPsId()+" title='修改'>"
							+ Globals.IMG_EDIT + "</a>&nbsp;";
					}
				if (Utils.checkPermission("sy-1801-04")&&payInterConfDTO.getStatus()!=9) {
						operation += "<a href=javascript:deleteData('payInterface/payInterfaceConfig!delete','"
								+ payInterConfDTO.getPsId()
								+ "') title='删除'>"
								+ Globals.IMG_DELETE + "</a>&nbsp;";
					}
				strings.add(operation);
				lists.add(strings);
			}
		PageView pageView=new PageView(payInterConfDTO.getPage(),result.getTotalrecord());
		ListInfoDTO listInfoDTO=new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	*@Title:addUI
	*@Description:跳转到添加页面
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-9-23上午10:31:28
	*/
	public String addUI(){
		this.setMethod("addSave");
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUSD);
		this.getRequest().setAttribute("type", OptionsValue.PSTYPE);
		this.getRequest().setAttribute("currType", OptionsValue.CURRTYPE);
		return INPUT;
	}
	
	/**
	*@Title:addSave
	*@Description:保存支付接口配置信息
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-9-23上午11:11:38
	*/
	public String addSave(){
		payInterConfService.addSave(payInterConfDTO);
		functionsService.saveFunction("支付接口配置", 1, "增加："
				+ payInterConfDTO.getPsId());
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "payInterface/payInterfaceConfig!list");
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUSD);
		this.getRequest().setAttribute("type", OptionsValue.PSTYPE);
		this.getRequest().setAttribute("currType", OptionsValue.CURRTYPE);
		return SUCCESS;
	}
	
	/**
	*@Title:checkName
	*@Description:检测接口名称是否重复
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-9-24上午10:02:09
	*/
	public String checkName(){
		ReturnDTO dto=new ReturnDTO();
		boolean flag=false;
		String method=this.getMethod();
		String psName=payInterConfDTO.getPsName();
		Integer psId=payInterConfDTO.getPsId();
		flag=payInterConfService.queryPsName(psName, psId, method);
		dto.setFlag(flag);
		return Utils.printInfo(dto);
	}
	
	/**
	*@Title:editUI
	*@Description:跳转到修改页面
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:王楠
	*@Date:2014-9-24下午02:35:11
	*/
	public String editUI()throws Exception{
		this.setMethod("editSave");
		this.getRequest().setAttribute("url", "payInterface/payInterfaceConfig!list");
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUSD);
		this.getRequest().setAttribute("type", OptionsValue.PSTYPE);
		this.getRequest().setAttribute("currType", OptionsValue.CURRTYPE);
		payInterConfDTO=payInterConfService.findById(payInterConfDTO.getPsId());
		return INPUT;
	}
	
	/**
	*@Title:editSave
	*@Description:修改方法
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-9-24下午02:40:34
	*/
	public String editSave(){
		ReturnDTO dto=payInterConfService.updatePayInterConf(payInterConfDTO);
		if(dto.getFlag()){
			functionsService.saveFunction("支付接口配置",2, "支付接口配置："+payInterConfDTO.getPsId());
    		this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			this.getRequest().setAttribute("url", "payInterface/payInterfaceConfig!list");
			return SUCCESS;
		}else{
			this.getRequest().setAttribute("result", dto.getMsg());
			this.getRequest().setAttribute("url", "payInterface/payInterfaceConfig!list");
			return ERROR;
		}
	}
	
	/**
	*@Title:delete
	*@Description:删除接口信息
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-9-24下午03:09:50
	*/
	public String delete(){
		try{
		payInterConfService.deletePayInterConf(Integer.parseInt(this.getId()));
		functionsService.saveFunction("支付接口配置", 3, "删除支付接口配置信息"+this.getId());
    	this.ajaxResult="ajaxsuccess";
		}catch(Exception e){
			e.printStackTrace();
			this.ajaxResult="ajaxfailure";
    		this.msgResult=e.getMessage();
		}
		return this.ajaxResult;
	}
	
	/**
	*@Title:checkUI
	*@Description:查看页面
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:王楠
	*@Date:2014-9-24下午03:15:16
	*/
	public String checkUI()throws Exception{
		this.setMethod("checkUI");
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUSD);
		this.getRequest().setAttribute("type", OptionsValue.PSTYPE);
		this.getRequest().setAttribute("currType", OptionsValue.CURRTYPE);
		payInterConfDTO=payInterConfService.findById(payInterConfDTO.getPsId());
		return INPUT;
	}
}
