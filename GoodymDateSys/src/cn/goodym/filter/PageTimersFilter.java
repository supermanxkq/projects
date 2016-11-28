package cn.goodym.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.util.*;

public class PageTimersFilter implements Filter {

	FilterConfig filterConfig = null;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Date startTime,endTime;
		double duration = 0;
		startTime = new Date();//���¿�ʼʱ��
		chain.doFilter(request,response);//������Ȩת������һ������
		endTime = new Date();//���½���ʱ��
		duration = endTime.getTime()-startTime.getTime();//������ķ�ʱ��
		//System.out.println("��������/��Ӧ��ʱ��"+duration+"����!");
		//д����־
	}

	public void destroy() {
		this.filterConfig = null;
	}

}
