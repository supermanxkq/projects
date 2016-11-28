package com.blog.web.ui;

import java.io.Serializable;

public class OptionsInt implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int key;

	private String value;

	public OptionsInt() {

	}

	public OptionsInt(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
