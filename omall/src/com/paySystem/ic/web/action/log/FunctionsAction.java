package com.paySystem.ic.web.action.log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.record.Functions;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.log.FunctionsDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 功能日志
 * @version 2013-9-8 下午02:08:10
 */
@Controller("/log/functions")
@Scope("prototype")
public class FunctionsAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;
	@Resource FunctionsService functionsService;
	@Resource OrgansService organsService;
	@Resource MerchantsService merchantsService;
	 
	private FunctionsDTO functionsDTO = new FunctionsDTO();

	public FunctionsDTO getFunctionsDTO() {
		return functionsDTO;
	}

	public void setFunctionsDTO(FunctionsDTO functionsDTO) {
		this.functionsDTO = functionsDTO;
	}

	/**
	 * 列表页面
	 * @version 2013-9-8 下午08:50:23
	 * @return
	 */
	public String list() {
		return "list";
	}

	/**
	 * 异步获取列表数据
	 * @version 2013-9-8 下午08:51:04
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 1:
			List<Merchants> merList = merchantsService.getMerByOrgan(us.getOrganId());
			Organs organs = organsService.find(us.getOrganId());
			if(organs!=null&&merList!=null&&!merList.isEmpty()){
				String merIds = "";
				for(Merchants merchants :merList){
					merIds = merIds + "'" + merchants.getMerId()+ "',";
				}
				sb.append(" and (o.merId in ("+merIds.substring(0, merIds.lastIndexOf(","))+") or o.organId = '"+us.getOrganId()+"') ");
			}else{
				sb.append(" and o.organId = '"+us.getOrganId()+"' ");
			}
			break;
		case 2:
			sb.append(" and o.merId = '"+us.getMerId()+"'");
			break;
		}
		
		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (StringUtils.isNotBlank(functionsDTO.getOperId())) {
			sb.append(" and o.operId like ?").append(params.size() + 1);
			params.add("%" + functionsDTO.getOperId().trim() + "%");
		}
		
		if (StringUtils.isNotBlank(functionsDTO.getRelecontents())) {
			sb.append(" and o.relecontents like ?").append(params.size() + 1);
			params.add("%" + functionsDTO.getRelecontents().trim() + "%");
		}

		//返回结果
		QueryResult<Functions> queryResult = functionsService.getScrollData((functionsDTO.getPage() - 1) * pageNum, pageNum, sb.toString(),params.toArray(), orderby);
		
		List<Functions> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		for(int i = 0;i <list.size();i++){
			Functions functions = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(functions.getModuleName()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.OPERATE_TYPE, functions.getOperType()));
			strings.add(Utils.getString(functions.getRelecontents()));
			strings.add(Utils.getString(functions.getOperId()));
			strings.add(DateTimeTool.dateFormat("", functions.getOperTime()));
			String operation = "";
			if(Utils.checkPermission("sy-9702-02")){
				operation += "<a href=javascript:deleteData('log/functions!delete','"+functions.getId()+"') title='删除'>"+Globals.IMG_DELETE+"</a>&nbsp;";
			}
			lists.add(strings);
		}
		
		PageView pageView = new PageView(functionsDTO.getPage(),queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
		Utils.printInfo(listInfoDTO);
		return null;
	}
	
	/**
	 * 删除操作
	 * @version 2013-9-10 下午04:23:43
	 */
	public String delete() {

		try {
			functionsService.delete(Integer.valueOf(this.getId()));
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}


}
