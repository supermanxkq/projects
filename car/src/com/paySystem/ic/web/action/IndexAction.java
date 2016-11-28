package com.paySystem.ic.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.bean.system.Role;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.system.ModuleService;
import com.paySystem.ic.service.system.UserService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.MD5;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.system.UserDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 主体框架Action类
 * @version 2013-9-1 上午11:12:59
 */
@Controller("/system/index")
@Scope("prototype")
public class IndexAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

	@Resource ModuleService moduleService;
	@Resource UserService userService;
	@Resource OrgansService organsService;
	
	private UserDTO userDTO = new UserDTO();
	private String pid;
	private String id;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * 进入主页面页面
	 * 
	 * @version 2011-10-10 下午09:07:50
	 * @return
	 */
	@Override
	public String execute() {
		return "main";
	}

	/**
	 * 头部页面
	 * 
	 * @version 2011-10-10 下午09:07:50
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String top() {
		List<Module> modules = null;
		modules = (List) this.getRequest().getSession().getAttribute(Globals.FATHER_MODULES);
		this.getRequest().setAttribute("modules", modules);
		UserSession us = Utils.getUserSession();
		Organs organs = organsService.find(us.getOrganId());
		String logoImg = "images/logo.gif";
		if(organs.getSysType()==1){
			logoImg = "images/member_logo.gif";
		}
		this.getRequest().setAttribute("logoImg", logoImg);
		
		return "top";
	}

	public String right() {
		return "right";
	}

	public String tool() {
		return "tool";
	}

	public String firstleft() {
		// UserSession usersession = (UserSession)
		// this.getRequest().getSession().getAttribute(Globals.USER_SESSION);
		return "firstleft";
	}

	@SuppressWarnings("unchecked")
	public String left() {
		try {
			List<Module> modules = null;
			Module module = moduleService.getModule(pid);
			Map childModulesMap = (Map) this.getRequest().getSession().getAttribute(Globals.CHILD_MODULES_MAP);
			modules = (List) childModulesMap.get(pid);
			
			this.getRequest().setAttribute("module", module.getName());
			this.getRequest().setAttribute("modules", modules);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "left";
	}

	public String separate() {
		return "separate";
	}

	public String footer() {
		return "footer";
	}

	public String myself(){
		UserSession us = (UserSession) this.getRequest().getSession().getAttribute(Globals.USER_SESSION);
		User user = userService.find(us.getUserName());
		if(user!=null){
			userDTO.setUserName(user.getUserName());
			userDTO.setRealName(user.getRealName());
			userDTO.setUserLevelName(Utils.getOptionsIntegerName(OptionsValue.USER_LEVEL, user.getUserLevel()));
			userDTO.setTeleNo(user.getTeleNo());
			userDTO.setLastLoginTime(DateTimeTool.dateFormat("", user.getLastLoginTime()));
			
			Set<Role> roles = user.getRoles();
			if(roles!=null&&!roles.isEmpty()){
				String roleName = "";
				for (Role role : roles) {
					roleName += role.getName();
				}
				userDTO.setRoleName(roleName);
			}
		}
		
		return "myself";
	}
	
	public String saveMyself(){
		
		User user = userService.find(userDTO.getUserName());
		if(user!=null){
			if(StringUtils.isNotBlank(userDTO.getPassWord())&&StringUtils.isNotBlank(userDTO.getNewPassWord())){
				if(!user.getPassWord().equals(MD5.MD5Encode(userDTO.getPassWord()))){
					this.getRequest().setAttribute("result","密码错误，请重新输入！");
					this.getRequest().setAttribute("url","system/index!myself");
					return SUCCESS;
				}
				user.setPassWord(MD5.MD5Encode(userDTO.getNewPassWord()));
			}
			user.setRealName(userDTO.getRealName());
			user.setTeleNo(userDTO.getTeleNo());
			userService.update(user);
		}
		
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","system/index!myself");
		return SUCCESS;
	}
	
	/**
	 * 小贴士
	 * @return
	 * @throws IOException 
	 */
	public String tips() throws IOException{
		StringBuffer sb = new StringBuffer();
		User user = userService.find(userDTO.getUserName());
		if(user!=null){
			
		}
		
		return Utils.printInfo(sb.toString());
	}
	
	public String getTipsHtml(String typetext,String num,String text,String url){
		StringBuffer sb = new StringBuffer();
		String title = typetext+num+text;
		sb.append("<li><span class=\"Fl\">");
		sb.append(typetext);
		sb.append("<a href='"+url+"' target='main' title='"+title+"'><b class=\"Color1\">");
		sb.append(num);
		sb.append("</b></a>");
		
		sb.append(text);
		
		sb.append("</span><span class=\"Fr\"><a href='"+url+"' target='main' class='color_blue2' title='"+title+"' > ");
		sb.append("[&nbsp;查看&nbsp;]");
		sb.append("</a></span></li>");
		return sb.toString();
	}
}
