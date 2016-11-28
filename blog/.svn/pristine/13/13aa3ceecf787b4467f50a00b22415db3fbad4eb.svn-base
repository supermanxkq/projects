package com.blog.web.action.system;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.dto.system.UserDTO;
import com.blog.service.system.UserService;
import com.blog.util.Globals;
import com.blog.web.action.BaseAction;

@Controller("/system/login")
@Scope("prototype")
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

	@Resource
	private UserService userService;

	private UserDTO userDTO=new UserDTO();
	private String userName;
	/**
	 *@MethodName:loginPage
	 *@Description:跳转到登录页面
	 *@author:枫雪工作室
	 *@return String
	 *@date:2015-4-26下午10:27:54
	 *@version: V1.0
	 */
	public String loginPage() {
		 return "success";
	}

	
	/**
	 *@MethodName:login
	 *@Description:登录
	 *@author:枫雪工作室
	 *@return String
	 *@date:2015-4-26下午10:28:22
	 *@version: V1.0
	 */
	public String login() {
		userName=userDTO.getUserName();
		userDTO = userService.login(userDTO);
		if (userDTO != null) {
			this.getSession().setAttribute(Globals.USER_SESSION,
					userService.getUserSession(userDTO));
			this.getRequest().setAttribute("url", "article/article!list?columnCode=1");
			this.getRequest().setAttribute("result", "登录成功！");
			return "index";
		}
		this.getRequest().setAttribute("url", "article/article!list?columnCode=1&userName="+userName);
		this.getRequest().setAttribute("result", "登录失败！");
		this.addFieldError("error", "用户名或密码错误！");
		return "success";
	}
	
	
	//注销
	public String logout() {
		this.getSession().removeAttribute(Globals.USER_SESSION);
		 return "index";
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

}
