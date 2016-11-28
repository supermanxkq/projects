package com.blog.web.ui;


import java.io.Serializable;

public class OptionsBoolean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean key;

	private String value;

	public OptionsBoolean() {

	}

	public OptionsBoolean(Boolean key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Boolean getKey() {
		return key;
	}

	public void setKey(Boolean key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
