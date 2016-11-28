package com.blog.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.system.User;
import com.blog.service.system.UserService;
import com.blog.util.Globals;
import com.blog.util.MD5;

@Controller("/system/init")
@Scope("prototype")
public class InitAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	public static Logger log = Logger.getLogger(InitAction.class);
	@Resource
	private UserService userService;

	private String sign;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String init() {
		try {
			/** 初始化状态 */
			if (this.getSign()!=null
					&& this.getSign().equals(Globals.ISINIT_SIGN)) {
				if (Globals.ISINIT_MODULE_PRIVILEGE) {
					System.out.println("系统初始化开始...");
					// 创建系统管理员角色
					if (Globals.ISCREATE_ADMIN_ROLE_USER) {
						// 创建用户
						User user = userService.find(1);
						if (user == null) {
							user = new User();
							user.setPassWord(MD5.MD5Encode(Globals.PASSWORD));
							user.setUserName(Globals.USER_NAME);
							user.setUserLeavel(1);
							userService.save(user);
						}
					}
					this.getRequest().setAttribute("result",
							this.getText("system.init.success"));
					System.out.println("初始化成功!");
				} else {
					this.getRequest().setAttribute("result",
							this.getText("system.init.not.open"));
				}
			} else {
				this.getRequest().setAttribute("result",
						this.getText("system.init.sign.error"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result",
					this.getText("system.init.fail"));
		}
		this.getRequest().setAttribute("url", "system/login!loginPage");
		return SUCCESS;
	}
}
