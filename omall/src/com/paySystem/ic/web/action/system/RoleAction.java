package com.paySystem.ic.web.action.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.bean.system.Privilege;
import com.paySystem.ic.bean.system.Role;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.system.ModuleService;
import com.paySystem.ic.service.system.PrivilegeService;
import com.paySystem.ic.service.system.RoleService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.ReadInit;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.system.RoleDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 系统角色管理
 * @author 
 * @version 2013-9-13 下午05:03:42
 */
@Controller("/system/role")
@Scope("prototype")
public class RoleAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;
	@Resource RoleService roleService;
	@Resource PrivilegeService privilegeService;
	@Resource ModuleService moduleService;
	@Resource FunctionsService functionsService;
	@Resource OrgansService organsService;
	
	private RoleDTO roleDTO = new RoleDTO();
 
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	/**
	 * 列表页面
	 * 
	 * @version 2011-9-13 下午05:05:38
	 * @return
	 */
	public String list() {
		this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUSD);
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
			sb.append(" and o.organId = '"+us.getOrganId()+"'");
			break;
		case 2:
			sb.append(" and o.organId = '"+us.getOrganId()+"'");
			break;
		}
		
		if (roleDTO.getStatus() != null
				&& roleDTO.getStatus()!= -1) {
			
			sb.append(" and o.status = ?").append(params.size() + 1);
			params.add(roleDTO.getStatus());
		} 
		
		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (StringUtils.isNotBlank(roleDTO.getName())) {
			sb.append(" and o.name like ?").append(params.size() + 1);
			params.add("%" + roleDTO.getName().trim() + "%");
		}

		//返回结果
		QueryResult<Role> queryResult = roleService.getScrollData((roleDTO.getPage() - 1) * pageNum, pageNum,sb.toString(), params.toArray(), orderby);

		List<Role> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		
		for(int i = 0;i <list.size();i++){
			Role role = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(role.getCode()));
			strings.add(Utils.getString(role.getName()));
			strings.add(StringUtils.isNotBlank(role.getOrganId())?organsService.find(role.getOrganId()).getName():"");
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUSD, role.getStatus()));
			String operation = "";
			if(role.getStatus()!=9){
				if(Utils.checkPermission("sy-9102-03")){
					operation += "<a href=system/role!editUI?roleDTO.id="+role.getId()+" title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
				}
				if(Utils.checkPermission("sy-9102-04")){
					operation += "<a href=javascript:deleteData('system/role!delete','"+role.getId()+"') title='删除'>"+Globals.IMG_DELETE+"</a>&nbsp;";
				}
			}
			strings.add(operation);
			lists.add(strings);
		}

		PageView pageView = new PageView(roleDTO.getPage(),queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));

		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	public String addUI(){
		this.setMethod("addSave");
		UserSession us = Utils.getUserSession();
		roleDTO.setCode(roleService.getCode());
		roleDTO.setOrganId(us.getOrganId());
		roleDTO.setOrganName(us.getOrganName());
		this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUSD );//状态
		this.getRequest().setAttribute("visibleValues",OptionsValue.VISIBLE_STATUS );
		if(us.getUserLevel()==0) {
			this.getRequest().setAttribute("organValues", organsService.getOption());
		}
		return INPUT;
	}

	/**
	 * 添加模块操作
	 * @version 2011-9-13 下午05:30:30
	 * @return
	 */
	public String addSave() throws Exception {
		
		if (roleService.validate(roleDTO.getCode())) {
			this.getRequest().setAttribute("result",this.getText("exist.no.notice"));
			this.getRequest().setAttribute("url","system/role!list");
			return ERROR;
		} 
		Role role = new Role();
		role.setCode(roleDTO.getCode());
		role.setName(roleDTO.getName());
		role.setStatus(roleDTO.getStatus());
		role.setIsCommon(roleDTO.getIsCommon());
		role.setOrganId(roleDTO.getOrganId());
		role.setMemo(roleDTO.getMemo());
		for (String id : this.getIds().split(",")) {
			if(StringUtils.isNotBlank(id)){
				Privilege privilege = privilegeService.find(id);
				if(privilege!=null){
					role.getPrivileges().add(privilege);
				}
			}
		}
		functionsService.saveFunction("角色管理", 1, "新增角色："+role.getName());
		roleService.save(role);
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","system/role!list");
		return SUCCESS;
	}
	
	/**
	 * 获取模块权限树
	 * @return
	 */
	public String privilegeTree() throws Exception{
		
		List<Module> modules = new ArrayList<Module>();
		
		List<Module> mainModules = moduleService.find("select o from Module o where o.parent is null order by o.sortOrder asc");
		for (int i = 0; i < mainModules.size(); i++) {
			Module module = mainModules.get(i);//系统管理
			if (module.getChildModules().size() > 0) {
				List<Module> midmodules = new ArrayList<Module>();
				for (Module midmodule : module.getChildModules()) {//操作员
					//mid
					List<Module> childModules = new ArrayList<Module>();
					for (Module m : midmodule.getChildModules()) {
						
						Set<Privilege> privileges = new HashSet<Privilege>();
						for (Privilege privilege : m.getPrivileges()) {
							if(privilege.getPrivilegeLevel()!=null&&privilege.getPrivilegeLevel()==0){
								if(roleDTO.getOrganId().equals(ReadInit.read("ORGANID"))){
									privileges.add(privilege);
								}
							}else{
								privileges.add(privilege);
							}
						}
						if(privileges.size()>0){
							m.getPrivileges().clear();
							m.setPrivileges(privileges);
							childModules.add(m);
							modules.add(m);
						}
					}
					//Collections.sort(childModules, new ModuleOrderComparator());
					midmodule.setChildModules(childModules);
					if(childModules.size()>0){
						midmodules.add(midmodule);
						modules.add(midmodule);
					}
				}
				if (midmodules.size() > 0) {
					modules.add(module);
				}
			}
		}
		
		this.getRequest().setAttribute("modules", modules);
		if(roleDTO.getId()!=null){
			Role role = roleService.find(roleDTO.getId());
			this.getRequest().setAttribute("rolePrivileges", role.getPrivileges());
		}
		
		return "privilegeTree";
	}

	/**
	 * 进入修改页面
	 * @version 2011-9-10 下午06:20:30
	 * @return
	 */
	public String editUI() throws Exception {
		this.setMethod("editSave");
		Role role = roleService.find(roleDTO.getId());
		roleDTO.setCode(Utils.getString(role.getCode()));
		roleDTO.setName(Utils.getString(role.getName()));
		roleDTO.setStatus(role.getStatus());
		roleDTO.setIsCommon(role.getIsCommon());
		roleDTO.setOrganId(role.getOrganId());
		roleDTO.setMemo(Utils.getString(role.getMemo()));
		this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUSD );//状态
		this.getRequest().setAttribute("visibleValues",OptionsValue.VISIBLE_STATUS );
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel()==0) {
			this.getRequest().setAttribute("organValues", organsService.getOption());
		}
		return INPUT;
	}

	/**
	 * 修改模块操作
	 * @version 2011-9-10 下午03:59:08
	 * @return
	 */
	public String editSave() throws Exception {

		Role role = roleService.find(roleDTO.getId());
		role.setName(roleDTO.getName());
		role.setStatus(roleDTO.getStatus());
		role.setIsCommon(roleDTO.getIsCommon());
		role.setOrganId(roleDTO.getOrganId());
		role.setMemo(roleDTO.getMemo());
		/** 先清空该角色所拥有的权限 */
		role.getPrivileges().clear();
		for (String id : this.getIds().split(",")) {
			if(StringUtils.isNotBlank(id)){
				Privilege privilege = privilegeService.find(id);
				if(privilege!=null){
					role.getPrivileges().add(privilege);
				}
			}
		}
		roleService.update(role);
		functionsService.saveFunction("角色管理", 2, "修改角色："+role.getName());
		
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","system/role!list");
		return SUCCESS;
	}

	/**
	 * 删除操作
	 * @version 2011-9-13 下午05:37:24
	 * @return
	 */
	public String delete() {

		try {
			Role role = roleService.find(Integer.valueOf(this.getId()));
			//roleService.delete(Integer.valueOf(this.getId()));
			role.setStatus(9);
			roleService.update(role);
			functionsService.saveFunction("角色管理", 3, "删除角色："+role.getName());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = "可能存在关联，不能删除!";
		}

		return this.ajaxResult;
	}
}
