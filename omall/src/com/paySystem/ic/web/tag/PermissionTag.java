package com.paySystem.ic.web.tag;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PermissionTag extends ComponentTagSupport {
	private static final long serialVersionUID = -1143240555883979151L;
	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		return new PermissionComponent(stack, request);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		PermissionComponent pct = (PermissionComponent) component;
		pct.setKey(key);
		pct.setValue(value);
	}
}
