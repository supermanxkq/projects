package com.lucene.action;

import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lucene.dto.BaseDTO;
import com.lucene.util.Globals;
import com.opensymphony.xwork2.ActionSupport;

@Controller("/system/base")
@Scope("prototype")
public class BaseAction extends ActionSupport {
	public static Logger log = Logger.getLogger(BaseAction.class);
	
	private static final long serialVersionUID = 2332804088291173718L;
	protected static int pageNum = Globals.PAGE_NUM;// 每页默认显示条数
	BaseDTO baseDTO = new BaseDTO();
	protected String ajaxResult = "ajaxfailure";
	protected String msgResult;
	protected Object objResult;
	protected String pageHTML;
	protected String tourl;
	protected String id;
	protected String ids;
	protected String method = "list";
	/** 排序字段 **/
	private String orderProperty;
	/** 升降序 **/
	private String orderDirection;
	
	public String getTourl() {
		return tourl;
	}

	public void setTourl(String tourl) {
		this.tourl = tourl;
	}

	public BaseDTO getBaseDTO() {
		return baseDTO;
	}

	public void setBaseDTO(BaseDTO baseDTO) {
		this.baseDTO = baseDTO;
	}

	public static int getPageNum() {
		return pageNum;
	}

	public static void setPageNum(int pageNum) {
		BaseAction.pageNum = pageNum;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public String getMsgResult() {
		return msgResult;
	}

	public void setMsgResult(String msgResult) {
		this.msgResult = msgResult;
	}

	public Object getObjResult() {
		return objResult;
	}

	public void setObjResult(Object objResult) {
		this.objResult = objResult;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealyPath(String path) {
		return getServletContext().getRealPath(path);
	}
	
	public String execute() throws Exception{
		this.getRequest().removeAttribute(Globals.QUERY_PARM);
		return this.executeMethod(this.getMethod());//跳转方法
	}
	
	@SuppressWarnings("unchecked")
	protected String executeMethod(String method) throws Exception {
		Class[] c = null;
		Method m = this.getClass().getMethod(method, c);
		Object[] o = null;
		String result = (String) m.invoke(this, o);
		return result;
	}
	
	/**
	 * 从session中获取查询条件
	 * @param object
	 * @return
	 */
	public Object getQueryParm(Object object){
		if(this.getSession().getAttribute(Globals.QUERY_PARM)!=null&&this.getSession().getAttribute(Globals.QUERY_PARM).getClass().getName().equals(object.getClass().getName())){
			object =  this.getSession().getAttribute(Globals.QUERY_PARM);
		}
		return object;
	}
}
