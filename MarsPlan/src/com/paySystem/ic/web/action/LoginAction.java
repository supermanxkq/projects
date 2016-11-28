package com.paySystem.ic.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.bean.system.Privilege;
import com.paySystem.ic.bean.system.Role;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.record.OperatorsService;
import com.paySystem.ic.service.system.ModuleService;
import com.paySystem.ic.service.system.UserService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.MD5;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.LoginDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 登录Action类
 * @version 2013-9-1 上午11:12:59
 */
@Controller("/system/login")
@Scope("prototype")
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	@Resource ModuleService moduleService;
	@Resource UserService userService;
	@Resource OperatorsService operatorsService;
	@Resource FunctionsService functionsService;
	
	private LoginDTO loginDTO = new LoginDTO();

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	public String loginPage() {
		this.getSession().setAttribute(Globals.PAGE_TITLE, Globals.TITLE);
		this.getSession().setAttribute("isVerifyUkey", Globals.IS_VERIFY_UKEY);
		this.getSession().setAttribute("rnd",new Integer((int)(Math.random()*65535)+1));
		this.getSession().setAttribute("isFormal", Globals.IS_FORMAL);
		return "login";
	}

	public String login() throws Exception {

		User user = null;
		Set<String> userPrivileges = new HashSet<String>();
		Set<Module> privilegeModules = new HashSet<Module>();
		List<Module> fatherModules = new ArrayList<Module>();
		
		if(StringUtils.isBlank(loginDTO.getUserName()) || StringUtils.isBlank(loginDTO.getPassWord())){
			 this.addActionError(this.getText("login.information.is.incomplete"));
			 return this.loginPage();
		}
		
		//验证验证码
		if(Globals.IS_FORMAL){
			if(StringUtils.isBlank(loginDTO.getAuthCode())){
				 this.addActionError(this.getText("login.information.is.incomplete"));
				 return this.loginPage();
			}
			String cauthCode = (String)
			this.getRequest().getSession().getAttribute("authCode");
			this.getRequest().getSession().removeAttribute("authCode");

			if(StringUtils.isBlank(cauthCode) || !loginDTO.getAuthCode().equals(cauthCode)){
				this.addActionError(this.getText("authCode.information.is.error"));
				return this.loginPage();
			}
		}
		
		//验证用户名
		user = userService.find(loginDTO.getUserName());
		if(user == null) {
			this.addActionError(this.getText("login.information.is.error"));
			return this.loginPage();
		}
		
		//验证UKEY
		Integer keyFlag = 1;
		if(user.getDlsFlag()!=null&&user.getDlsFlag()==1){
			keyFlag = 2;
		}
		
		ReturnDTO dto = Utils.checkUKEY(keyFlag,loginDTO.getRnd(), loginDTO.getReturn_EncData(),loginDTO.getKeyID(),user.getKeyID());
		
		if(!dto.getFlag()){
			this.addActionError(dto.getMsg());
			return this.loginPage();
		}
		
		if(!user.getPassWord().equals(MD5.MD5Encode(loginDTO.getPassWord()))){
			user.setWrpass(user.getWrpass()+1);
			/*if(user.getWrpass()>=50){
				user.setStatus(2);
			}*/
			userService.update(user);
			this.addActionError(this.getText("login.information.is.error"));
			return this.loginPage();
		}
		
		if(user.getStatus()!=1){
			this.addActionError(this.getText("login.userstatus.is.error"));
			return this.loginPage();
		}
		
		HashMap<String, List<Module>> childModulesMap = new HashMap<String, List<Module>>();
		HashMap<String, Module> modulesMap = new HashMap<String, Module>();
		Set<Privilege> privilegesSet = new HashSet<Privilege>();
		List<Module> often = new ArrayList<Module>();//常用菜单
		Set<Role> roles = user.getRoles();
		if(roles.size()>0){
			for (Role role : roles) {
				if(role.getPrivileges().size()>0&&role.getStatus()==1){
					for (Privilege privilege : role.getPrivileges()) {
						privilegesSet.add(privilege);
					}
				}
			}
		}
		
		if (privilegesSet.size() > 0) {
			
			Iterator<Privilege> privileges = privilegesSet.iterator();
			while (privileges.hasNext()) {
				/** 用户的全部权限 */
				Privilege privilege = privileges.next();
				userPrivileges.add(privilege.getId());
				/** 用户的权限模块 */
				privilegeModules.add(privilege.getModule());

				if (!StringUtils.isBlank(privilege.getModule().getLinkAddr())) {
					int start = 0;
					if (privilege.getModule().getLinkAddr().lastIndexOf("/") > 0) {
						start = privilege.getModule().getLinkAddr().lastIndexOf("/") + 1;
					}
					int end = privilege.getModule().getLinkAddr().length();
					if (privilege.getModule().getLinkAddr().indexOf("!") > 0) {
						end = privilege.getModule().getLinkAddr().indexOf("!");
					}
					String key = privilege.getModule().getLinkAddr().substring(start, end);
					if (!modulesMap.containsKey(key)) {
						modulesMap.put(key, privilege.getModule());
					}
				}
			}

			List<Module> mainModules = moduleService.find("select o from Module o where o.parent is null order by o.sortOrder asc");
			for (int i = 0; i < mainModules.size(); i++) {
				Module module = mainModules.get(i);//系统管理
				if (module.getChildModules().size() > 0) {
					List<Module> midmodules = new ArrayList<Module>();
					for (Module midmodule : module.getChildModules()) {//操作员
						//mid
						List<Module> childModules = new ArrayList<Module>();
						for (Module m : midmodule.getChildModules()) {
							if (privilegeModules.contains(m)){
								//midmodule.getChildModules().add(m);
								childModules.add(m);
							}
						}
						//Collections.sort(childModules, new ModuleOrderComparator());
						midmodule.setChildModules(childModules);
						if(childModules.size()>0){
							midmodules.add(midmodule);
							if(midmodule.getIsOften()!=null&&midmodule.getIsOften()==1){
								often.add(midmodule);
							}
						}
					}
					if (midmodules.size() > 0) {
						fatherModules.add(module);
						childModulesMap.put(module.getId(), midmodules);
					}
				}
			}
			this.getSession().setAttribute("often", often);
			this.getSession().setAttribute(Globals.USER_SESSION,userService.getUserSession(user, modulesMap));
			this.getSession().setAttribute(Globals.PAGE_TITLE, Globals.TITLE);
			this.getSession().setAttribute(Globals.FATHER_MODULES,fatherModules);// 父模块
			this.getSession().setAttribute(Globals.CHILD_MODULES_MAP,childModulesMap);// 父模块下的对应子模块
			this.getSession().setAttribute(Globals.USER_PRIVILEGES,userPrivileges);// 角色的全部权限
			
			user.setLastLoginTime(new Date());
			userService.update(user);
			log.info("【用户登陆】操作员："+user.getUserName()+" 操作时间："+DateTimeTool.dateFormat("", new Date())+" IP："+Utils.getIpAddr()+" IP2："+Utils.getClientIP());
			functionsService.saveFunction("用户登陆", 0, "用户名："+user.getUserName()+" IP："+Utils.getIpAddr());
			/** 跳转页面的URL 进入主页面 */
			this.setTourl("index");
			return "main";
		}else{
			this.addActionError("您没有相关权限！");
		}
		return this.loginPage();
	}
	
	/**
	 * 安全退出
	 * 
	 * @version 2013-9-19 上午11:14:02
	 * @return
	 * @throws Exception 
	 */
	public String logout() {
		try {
			UserSession us = Utils.getUserSession();
			log.info("【用户退出】操作员："+us.getUserName()+" 操作时间："+DateTimeTool.dateFormat("", new Date())+" IP："+Utils.getIpAddr());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		this.getSession().removeAttribute(Globals.USER_SESSION);
		this.getSession().removeAttribute(Globals.USER_PRIVILEGES);
		return this.loginPage();
	}
	
	/**
	 * 驱动下载
	 * @version 2013-10-14 下午08:35:56
	 * @return
	 */
	public String download() {
		try {
			this.setInputPath("driver_2k.exe");//要下载的文件路径
			this.setFileName(java.net.URLEncoder.encode("driver_2k.exe","UTF-8"));//保存文件时的名称
			//this.setFileName(DateTimeTool.dateFormat("yyyyMMddHHmmss", new Date())+".xls");//保存文件时的名称
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.setContentType("application/octet-stream");//保存文件的类型
		return "download";
	}
	
	public InputStream getInputStream() throws Exception {
		String url = "tools" + File.separator + inputPath;
		String imageFilePath = ServletActionContext.getServletContext().getRealPath("/" + url);

		File file = new File(imageFilePath);
		if ((file.exists())) {
			return new FileInputStream(file);
		}
		return null;
	}
	
	private String fileName;
	private String contentType;
	private String inputPath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
}
