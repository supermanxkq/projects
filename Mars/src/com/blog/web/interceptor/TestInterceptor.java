package com.blog.web.interceptor;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.bean.log.LogBean;
import com.blog.service.log.LogService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class TestInterceptor extends ActionSupport implements Interceptor {
	@Autowired
	private LogService logService;
	Logger logger = Logger.getLogger(TestInterceptor.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8293816560970240170L;

	// 销毁
	public void destroy() {

	}

	// 初始化
	public void init() {

	}

	// 自定义拦截器
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		String page= ServletActionContext.getRequest().getRequestURI();
		String ip = ServletActionContext.getRequest().getRemoteAddr();     
		LogBean  logBean = new LogBean();
		logBean.setId(UUID.randomUUID().toString());
		logBean.setSessionId(session.getId());
		logBean.setIp(ip);
		logBean.setPage(page);
		logBean.setAccessTime(new Timestamp(new Date().getTime()));
		logBean.setStayTime(0);
		
		//通过session id 和 ip，查出最近的一条访问记录
		LogBean bean = null;
		try {
			bean = logService.getLatestLog(session.getId(), ip);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		//更改最近访问记录的停留时间，这里把两次访问记录的间隔时间算成上一次页面访问的停留时间
		if(bean != null){
			long stayTime = (System.currentTimeMillis() - bean.getAccessTime().getTime())/1000;
			try {
				logService.updateLog(bean.getId(), stayTime);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		
		//保存当前访问记录
		try {
				logService.saveLog(logBean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		//统计网站的PV（页面浏览量），UV（独立访客数）
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String format = df.format(new Date());
		Date parse = null;
		try {
			parse = df.parse(format);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Timestamp startTime = new Timestamp(parse.getTime());
		Timestamp endTime = new Timestamp(parse.getTime() + 24*3600*1000);
		try {
			int pv = logService.getPV(startTime, endTime);
			int uv = logService.getUV(startTime, endTime);
			System.out.println(pv+"pv***********************************************");
			System.out.println(uv+"uv***********************************************");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
			return arg0.invoke();
	}

}
