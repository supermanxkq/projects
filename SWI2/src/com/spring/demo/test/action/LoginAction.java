package com.spring.demo.test.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;
import com.opensymphony.xwork.ModelDriven;
import com.spring.demo.test.object.TUser;
import com.spring.demo.test.service.iface.UserService;

/**
 * @ProjectName:SWI
 * @ClassName:LoginAction
 * @Description:登录控制类
 * @date: 2015-7-10下午03:26:12
 * @author: 半仙儿
 * @version: V1.0
 * @date:2015-7-10下午03:26:12
 */
public class LoginAction extends ActionSupport implements Action, ModelDriven {
	/** 序列化 */
	private static final long serialVersionUID = 4121316715544092724L;

	/** 创建slf4j的log对象 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 实例化TUser对象，并生成get/set方法 */
	private TUser user = new TUser();
	/** 创建UserService */
	private UserService userService;
	/**infoIbatisDAO*/
	private UserService infoIbatisDAO;
	/** 返回的FieldError的信息 */
	private String message;
	/** 人员信息列表 */
	private List infoList = new ArrayList();

	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 *@MethodName:login
	 *@Description:用户登录
	 *@author:半仙儿
	 *@return String
	 *@date:2015-7-10下午04:46:51
	 */
	public String login() {
		/** 获取Opensymphony的ActionContext */
		ActionContext actionContext = ActionContext.getContext();
		/** HttpServletRequest是Struts2工作流程请求的起点 */
		HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext
				.get(ServletActionContext.HTTP_REQUEST);
		/** 通过httpServeletRequest的getSession方法获取httpSession对象 */
		HttpSession httpSession = httpServletRequest.getSession();
		/** 获取用户名\密码 */
		String name = user.getUname();
		String pwd = user.getPwd();
		/** SQL语句 */
		String sql = "from TUser where uname='" + name + "' and pwd='" + pwd
				+ "'";
		/** 打印SQL语句到控制台 */
		logger.info(">>>>login..sql=" + sql);
		/** 根据用户名进行获取用户 */
		List list = userService.Find(name);
		/** 当前输入用户名数据库中所有的集合不为空 */
		if (null != list && list.size() > 0) {
			/** 判断密码是否相同 */
			for (int i = 0; i < list.size(); i++) {
				TUser user = (TUser) list.get(i);
				if (null != user) {
					if (pwd.equals(user.getPwd())) {
						/** 将user对象保存到Session中 */
						httpSession.setAttribute("user_bean", user);
						logger
								.info(
										"--------------login-----success---------login is ok ! name={} password {} ",
										name, pwd);
						return SUCCESS;
					}
				}
			}
		}
		message = "你输入的用户名或密码不正确！";
		logger.info(">>>>login..sql=" + sql + "---" + message);
		addFieldError("name", message);
		return INPUT;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Object getModel() {
		return user;
	}

	public void setInfoList(List infoList) {
		this.infoList = infoList;
	}

	/**
	 *@MethodName:getInfoList
	 *@Description:获取人员信息列表
	 *@author:徐半仙儿
	 *@return List
	 *@date:2015-7-12下午08:38:36
	 */
	public List getInfoList() {
		List list=infoIbatisDAO.FindByArr(user.getUname());
		setInfoList(list);
		return infoList;
	}

	public UserService getInfoIbatisDAO() {
		return infoIbatisDAO;
	}

	public void setInfoIbatisDAO(UserService infoIbatisDAO) {
		this.infoIbatisDAO = infoIbatisDAO;
	}
	
}
