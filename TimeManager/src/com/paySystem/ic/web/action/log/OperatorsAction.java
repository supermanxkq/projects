package com.paySystem.ic.web.action.log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.record.OperRecord;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.record.OperatorsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.log.OperatorsDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 操作日志
 * @version 2013-9-8 下午02:08:10
 */
@Controller("/log/operators")
@Scope("prototype")
public class OperatorsAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;
	@Resource OperatorsService operatorsService;
	@Resource OrgansService organsService;
	@Resource MerchantsService merchantsService;
	  
	private OperatorsDTO operatorsDTO = new OperatorsDTO();
	public OperatorsDTO getOperatorsDTO() {
		return operatorsDTO;
	}

	public void setOperatorsDTO(OperatorsDTO operatorsDTO) {
		this.operatorsDTO = operatorsDTO;
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
			Organs organs = organsService.find(us.getOrganId());
			List<Merchants> merList = merchantsService.getMerByOrgan(us.getOrganId());
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
		if (StringUtils.isNotBlank(operatorsDTO.getUserName())) {
			sb.append(" and o.operId like ?").append(params.size() + 1);
			params.add("%" + operatorsDTO.getUserName().trim() + "%");
		}

		//返回结果
		QueryResult<OperRecord> queryResult = operatorsService.getScrollData((operatorsDTO.getPage() - 1) * pageNum, pageNum, sb.toString(),params.toArray(), orderby);
		
		List<OperRecord> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		for(int i = 0;i <list.size();i++){
			OperRecord operRecord = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(operRecord.getOperId()));
			strings.add(Utils.getString(operRecord.getModuleName()));
			strings.add(DateTimeTool.dateFormat("", operRecord.getOperTime()));
			strings.add(Utils.getString(operRecord.getLoginIP()));
			String operation = "";
			if(Utils.checkPermission("sy-9701-02")){
				operation += "<a href=javascript:deleteData('log/operators!delete','"+operRecord.getId()+"') title='删除'>"+Globals.IMG_DELETE+"</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		
		PageView pageView = new PageView(operatorsDTO.getPage(),queryResult.getTotalrecord());
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
			operatorsService.delete(Integer.valueOf(this.getId()));
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

}
