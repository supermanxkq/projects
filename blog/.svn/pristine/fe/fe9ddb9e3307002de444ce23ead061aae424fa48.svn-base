package com.blog.web.action.timeline;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.blog.bean.timeline.Admin;
import com.blog.dao.timeline.TimeLineDAO;
import com.blog.util.MD5;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/** 系统管理员处理控制器 */
@SuppressWarnings("serial")
public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
	/** 通过依赖注入DAO组件实例 */
	private TimeLineDAO dao;	
	/** 系统用户管理所有请求中常用的参数值 */
	private String rand; 		//随机验证码
	private String actionMsg;	//Action间传递的消息参数
	
	
	//采用模型驱动
	private Admin model=new Admin();//用于封装系统用户属性模型
	public Admin getModel() {
		return model;
	}

	/** 处理登录请求 */
	public String login(){
		try {
			if(!rand.equalsIgnoreCase((String)ServletActionContext.getRequest().getSession().getAttribute("rand"))){
				addActionError(getText("login_rand_error"));
				return "login";
			}else{
				System.out.println(MD5.MD5Encode(model.getLoginpwd()));
				Admin tempAdmin= (Admin)dao.loadObject("from Admin as a where a.loginname='"+model.getLoginname()+"' and a.loginpwd='"+MD5.MD5Encode(model.getLoginpwd())+"'" );
				HttpServletRequest request = ServletActionContext.getRequest();
				if(tempAdmin!=null){
					//保存当前操作员到session值中
					ServletActionContext.getRequest().getSession().setAttribute("admin",tempAdmin);							
					return "index";
				}else{
					addActionError(getText("login_fail"));
					return "login";				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	/** 处理注销请求 */
	public String logout(){	
		HttpServletRequest request = ServletActionContext.getRequest();
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		if(admin!=null){
	    	ServletActionContext.getRequest().getSession().invalidate();
		}	
		return "login";
	}
	
	
	public TimeLineDAO getDao() {
		return dao;
	}

	public void setDao(TimeLineDAO dao) {
		this.dao = dao;
	}

	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}
	
	

	
}
