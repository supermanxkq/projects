package com.blog.web.action.system;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.system.User;
import com.blog.dto.system.UserDTO;
import com.blog.service.system.UserService;
import com.blog.util.Globals;
import com.blog.util.Utils;
import com.blog.web.action.BaseAction;

/**
 * @ProjectName:blog
 * @ClassName:UserAction
 * @Description:用户的管理
 * @date: 2015-6-14下午08:45:52
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-14下午08:45:52
 */
@Controller("/system/user")
@Scope("prototype")
public class UserAction extends BaseAction {
	/***/
	private static final long serialVersionUID = -1289007052029825283L;
	@Resource
	private UserService userService;
	private UserDTO userDTO;
	/** 表单中的rand **/
	private String rand;

	/**
	 *@MethodName:addUI
	 *@Description:跳转到注册页面
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-6-14下午08:42:17
	 */
	public String addUI() {
		this.setMethod("addSave");
		return "input";
	}

	/**
	 *@MethodName:addSave
	 *@Description:注册新用户
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-6-14下午09:16:05
	 */
	public String addSave() {
		/** 从session中取出RandomAction.java 中生成的验证码random */
		String arandom =(String) this.getSession().getAttribute("random");
		/** 判断验证码是否输入正确,验证码正确，进行登录操作 **/
		if (arandom.equals(this.getRand())) {
			User user=userService.addUser(userDTO);
			userDTO.setUserLeavel(2);
			userDTO.setUserId(user.getUserId());
			this.getSession().setAttribute(Globals.USER_SESSION,
					userService.getUserSession(userDTO));
			this.getRequest().setAttribute("url", "article/article!list?columnCode=1");
			this.getRequest().setAttribute("result",
					"注册成功！用户初始密码为123456，请尽早修改......");
			return "success";
		} 
		return "index";
	}
	
	/**
	 *@MethodName:validateCode 
	 *@Description:验证验证码是否正确
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-6-14下午11:17:26
	 */
	public String validateCode(){
		String code=(String) this.getSession().getAttribute("random");
		if(this.rand.equals(code)){
			return Utils.printInfo(1);
		}else{
			return Utils.printInfo(0);
		}
	}
	/**
	 *@MethodName:validateUserName 
	 *@Description:验证用户名是否存在
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-6-15上午12:00:38
	 */
	public String validateUserName(){
		boolean isExsit=userService.validateUserName(userDTO);
		if(isExsit){
			return Utils.printInfo(1);
		}else{
			return Utils.printInfo(0);
		}
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}

}
