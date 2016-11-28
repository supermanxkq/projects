package com.paySystem.ic.web.action.system;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.service.system.ModuleService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.system.ModuleDTO;

/**
 * 模块管理
 * @version 2013-9-8 下午02:08:10
 */
@Controller("/system/module")
@Scope("prototype")
public class ModuleAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;
	@Resource ModuleService moduleService;
	private ModuleDTO moduleDTO = new ModuleDTO();
 
	public ModuleDTO getModuleDTO() {
		return moduleDTO;
	}

	public void setModuleDTO(ModuleDTO moduleDTO) {
		this.moduleDTO = moduleDTO;
	}

	/**
	 * 列表页面
	 * @version 2013-9-8 下午08:50:23
	 * @return
	 */
	public String list() {
		this.getRequest().setAttribute("visibleValues",OptionsValue.VISIBLE_STATUS );//是否
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
		orderby.put("sortOrder", "asc");
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (StringUtils.isNotBlank(moduleDTO.getName())) {
			sb.append(" and o.name like ?").append(params.size() + 1);
			params.add("%" + moduleDTO.getName().trim() + "%");
		}
		if (StringUtils.isNotBlank(moduleDTO.getParentName())) {
			sb.append(" and o.parent.name like ?").append(params.size() + 1);
			params.add("%" + moduleDTO.getParentName().trim() + "%");
		}

		//返回结果
		QueryResult<Module> queryResult =moduleService.getScrollData((moduleDTO.getPage() - 1) * pageNum, pageNum, sb.toString(),params.toArray(), orderby);
		
		List<Module> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		for(int i = 0;i <list.size();i++){
			Module module = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(module.getId()));
			strings.add(Utils.getString(module.getName()));
			strings.add(module.getParent() != null ? module.getParent().getName() : "");
			strings.add(Utils.getString(module.getSortOrder()));
			strings.add(Utils.getString(module.getLinkAddr()));
			String operation = "";
			if(Utils.checkPermission("sy-9101-02")){
				operation += "<a href=javascript:loadData('"+module.getId()+"') title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
			}
			if(Utils.checkPermission("sy-9101-02")){
				operation += "<a href=javascript:deleteData('system/module!delete','"+module.getId()+"') title='删除'>"+Globals.IMG_DELETE+"</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		
		PageView pageView = new PageView(moduleDTO.getPage(),queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
		Utils.printInfo(listInfoDTO);

		return null;
	}


	/**
	 * 进入修改页面
	 * @version 2013-9-10 下午06:20:30
	 * @return
	 */
	public String editUI() {

		Module module = moduleService.getModule(moduleDTO.getId());
		if(module!=null){
			moduleDTO.setId(Utils.getString(module.getId()));
			moduleDTO.setName(Utils.getString(module.getName()));
			moduleDTO.setSortOrder(Utils.getString(module.getSortOrder()));
			moduleDTO.setLinkAddr(Utils.getString(module.getLinkAddr()));
			moduleDTO.setIsOften(module.getIsOften());
			this.objResult = moduleDTO;
			return "objResult";
		}
		return ERROR;
	}

	/**
	 * 修改模块操作
	 * @version 2013-9-10 下午03:59:08
	 * @return
	 */
	public String update() {

		try {
			Module module = moduleService.getModule(moduleDTO.getId());
			if(module!=null){
				module.setName(moduleDTO.getName());
				module.setSortOrder(Utils.strToInt(moduleDTO.getSortOrder()));
				module.setLinkAddr(moduleDTO.getLinkAddr());
				module.setIsOften(moduleDTO.getIsOften());
				moduleService.update(module);
				this.ajaxResult = "ajaxsuccess";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
	
	/**
	 * 删除操作
	 * @version 2013-9-10 下午04:23:43
	 */
	public String delete() {

		try {
			moduleService.deleteModule(this.getId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}
}
